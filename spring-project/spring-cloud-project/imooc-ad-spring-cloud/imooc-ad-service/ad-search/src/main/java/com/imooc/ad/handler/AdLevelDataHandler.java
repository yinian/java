package com.imooc.ad.handler;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.dump.table.*;
import com.imooc.ad.index.DataTable;
import com.imooc.ad.index.IndexAware;
import com.imooc.ad.index.adplan.AdPlanIndex;
import com.imooc.ad.index.adplan.AdPlanObject;
import com.imooc.ad.index.adunit.AdUnitIndex;
import com.imooc.ad.index.adunit.AdUnitObject;
import com.imooc.ad.index.creative.CreativeIndex;
import com.imooc.ad.index.creative.CreativeObject;
import com.imooc.ad.index.creativeunit.CreativeUnitIndex;
import com.imooc.ad.index.creativeunit.CreativeUnitObject;
import com.imooc.ad.index.district.UnitDistrictIndex;
import com.imooc.ad.index.interest.UnitItIndex;
import com.imooc.ad.index.keyword.UnitKeywordIndex;
import com.imooc.ad.mysql.constant.OpType;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: HASEE
 * @Description:
 * 1. 索引之间存在着层级的划分, 也就是依赖关系的划分
 * 2. 加载全量索引其实是增量索引 "添加" 的一种特殊实现
 * @Date: Created in 16:23 2019/4/23
 * @Modified By:
 */
@Slf4j
public class AdLevelDataHandler {

	// 二级索引的实现，主要是这个索引不依赖其他，其他索引可能依赖这个
	// 如果是全量索引，传进去的参数：Optype
	public static void handleLevel2(AdPlanTable planTable, OpType type) {
		// 将导出的AdPlanTable转化为索引文件的Object
		AdPlanObject planObject = new AdPlanObject(
				planTable.getId(),
				planTable.getUserId(),
				planTable.getPlanStatus(),
				planTable.getStartDate(),
				planTable.getEndDate()
		);

		handleBinlogEvent(
				DataTable.of(AdPlanIndex.class),
				planObject.getPlanId(),
				planObject,
				type
		);

	}
	public static void handleLevel2(AdCreativeTable creativeTable,
	                                OpType type) {

		CreativeObject creativeObject = new CreativeObject(
				creativeTable.getAdId(),
				creativeTable.getName(),
				creativeTable.getType(),
				creativeTable.getMaterialType(),
				creativeTable.getHeight(),
				creativeTable.getWidth(),
				creativeTable.getAuditStatus(),
				creativeTable.getAdUrl()
		);
		handleBinlogEvent(
				DataTable.of(CreativeIndex.class),
				creativeObject.getAdId(),
				creativeObject,
				type
		);
	}

	/**
	 * 第三层级：AdUnit 依赖于 推广单元，推广单元依赖于推广计划
	 * @param unitTable
	 * @param type
	 */
	public static void handleLevel3(AdUnitTable unitTable, OpType type) {

		// 如果无法获取单元，那么推广计划就没有建立，所以推广单元不应该被加载进来。
		AdPlanObject adPlanObject = DataTable.of(
				AdPlanIndex.class
		).get(unitTable.getPlanId());

		if (null == adPlanObject){
			log.error("handleLevel3 found AdPlanObject error: {}",
					unitTable.getPlanId());
			return;
		}
		AdUnitObject unitObject = new AdUnitObject(
				unitTable.getUnitId(),
				unitTable.getUnitStatus(),
				unitTable.getPositionType(),
				unitTable.getPlanId(),
				adPlanObject
		);

		handleBinlogEvent(
				DataTable.of(AdUnitIndex.class),
				unitTable.getUnitId(),
				unitObject,
				type
		);

	}

	/**
	 * creativeUnitTable 会依赖于 创意本身creative, 也会依赖于第三层级的AdUnitTable，所以归在第三层级
	 * @param creativeUnitTable
	 * @param type
	 */
	public static void handleLevel3(AdCreativeUnitTable creativeUnitTable,
	                                OpType type) {

		if(type == OpType.UPDATE){// 不支持更新
			log.error("CreativeUnitIndex not support update");
			return;
		}

		AdUnitObject unitObject = DataTable.of(
				AdUnitIndex.class
		).get(creativeUnitTable.getUnitId());
		CreativeObject creativeObject = DataTable.of(
				CreativeIndex.class
		).get(creativeUnitTable.getAdId());

		if (null == unitObject || null == creativeObject) {
			log.error("AdCreativeUnitTable index error: {}",
					JSON.toJSONString(creativeUnitTable));
			return;
		}

		CreativeUnitObject creativeUnitObject = new CreativeUnitObject(
				creativeUnitTable.getAdId(),
				creativeUnitTable.getUnitId()
		);
		handleBinlogEvent(
				DataTable.of(CreativeUnitIndex.class),
				CommonUtils.stringConcat(
						creativeUnitObject.getAdId().toString(),
						creativeUnitObject.getUnitId().toString()
				),
				creativeUnitObject,
				type
		);

	}
	// 以下是第四层级的索引：分别与地域限制、兴趣限制、关键词限制；与第三层索引有关系
	public static void handleLevel4(AdUnitDistrictTable unitDistrictTable,
	                                OpType type) {

		if (type == OpType.UPDATE) {
			log.error("district index can not support update");
			return;
		}

		AdUnitObject unitObject = DataTable.of(
				AdUnitIndex.class
		).get(unitDistrictTable.getUnitId());
		if (unitObject == null) {
			log.error("AdUnitDistrictTable index error: {}",
					unitDistrictTable.getUnitId());
			return;
		}

		String key = CommonUtils.stringConcat(
				unitDistrictTable.getProvince(),
				unitDistrictTable.getCity()
		);
		Set<Long> value = new HashSet<>(
				Collections.singleton(unitDistrictTable.getUnitId())
		);
		handleBinlogEvent(
				DataTable.of(UnitDistrictIndex.class),
				key, value,
				type
		);



	}

	public static void handleLevel4(AdUnitItTable unitItTable, OpType type) {

		if (type == OpType.UPDATE) {
			log.error("it index can not support update");
			return;
		}

		AdUnitObject unitObject = DataTable.of(
				AdUnitIndex.class
		).get(unitItTable.getUnitId());
		if (unitObject == null) {
			log.error("AdUnitItTable index error: {}",
					unitItTable.getUnitId());
			return;
		}

		Set<Long> value = new HashSet<>(
				Collections.singleton(unitItTable.getUnitId())
		);
		handleBinlogEvent(
				DataTable.of(UnitItIndex.class),
				unitItTable.getItTag(),
				value,
				type
		);
	}
	public static void handleLevel4(AdUnitKeywordTable keywordTable,
	                                OpType type) {


		if (type == OpType.UPDATE) {
			log.error("keyword index can not support update");
			return;
		}

		AdUnitObject unitObject = DataTable.of(
				AdUnitIndex.class
		).get(keywordTable.getUnitId());
		if (unitObject == null) {
			log.error("AdUnitKeywordTable index error: {}",
					keywordTable.getUnitId());
			return;
		}

		Set<Long> value = new HashSet<>(
				Collections.singleton(keywordTable.getUnitId())
		);
		handleBinlogEvent(
				DataTable.of(UnitKeywordIndex.class),
				keywordTable.getKeyword(),
				value,
				type
		);

	}


	// 实现对索引的添加、删除、更新，主要是通过IndexAware<K, V>实现的
	private static <K, V> void handleBinlogEvent(
			IndexAware<K, V> index,
	        K key,
	        V value,
	        OpType type){

		switch (type){
			case ADD:
				index.add(key, value);
				break;
			case UPDATE:
				index.update(key, value);
				break;
			case DELETE:
				index.delete(key, value);
				break;
			default:
					break;
		}


	}
}
