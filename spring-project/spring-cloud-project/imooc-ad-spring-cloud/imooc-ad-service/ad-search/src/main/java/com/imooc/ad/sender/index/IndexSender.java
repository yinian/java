package com.imooc.ad.sender.index;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.dump.table.*;
import com.imooc.ad.handler.AdLevelDataHandler;
import com.imooc.ad.index.DataLevel;
import com.imooc.ad.mysql.constant.Constant;
import com.imooc.ad.mysql.dto.MySqlRowData;
import com.imooc.ad.sender.ISender;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: HASEE
 * @Description: 增量索引数据投递
 * @Date: Created in 11:17 2019/4/24
 * @Modified By:
 */
@Slf4j
@Component("indexSender")
public class IndexSender implements ISender{
	@Override
	public void sender(MySqlRowData rowData) {

		String level = rowData.getLevel();

		if (DataLevel.LEVEL2.getLevel().equals(level)) {
			Level2RowData(rowData);
		} else if (DataLevel.LEVEL3.getLevel().equals(level)) {
			Level3RowData(rowData);
		} else if (DataLevel.LEVEL4.getLevel().equals(level)) {
			Level4RowData(rowData);
		} else {
			log.error("MysqlRowData ERROR: {}", JSON.toJSONString(rowData));
		}

	}

	private void Level2RowData(MySqlRowData rowData) {
		if (rowData.getTableName().equals(
				Constant.AD_CREATIVE_TABLE_INFO.TABLE_NAME
		)){
			List<AdPlanTable> planTables = new ArrayList<>();
			for (Map<String,String> fieldValueMap : rowData.getFieldValueMap()){
				AdPlanTable planTable = new AdPlanTable();

				fieldValueMap.forEach((k, v)-> {
					switch (k){
						case Constant.AD_PLAN_TABLE_INFO.COLUMN_ID:
							planTable.setId(Long.valueOf(v));
							break;
						case Constant.AD_PLAN_TABLE_INFO.COLUMN_USER_ID:
							planTable.setUserId(Long.valueOf(v));
							break;
						case Constant.AD_PLAN_TABLE_INFO.COLUMN_PLAN_STATUS:
							planTable.setPlanStatus(Integer.valueOf(v));
							break;
						case Constant.AD_PLAN_TABLE_INFO.COLUMN_START_DATE:
							planTable.setStartDate(
									CommonUtils.parseStringDate(v)
							);
							break;
						case Constant.AD_PLAN_TABLE_INFO.COLUMN_END_DATE:
							planTable.setEndDate(
									CommonUtils.parseStringDate(v)
							);
							break;
					}
				});
			}
		}
	}
	private void Level3RowData(MySqlRowData rowData) {

		if (rowData.getTableName().equals(
				Constant.AD_UNIT_TABLE_INFO.TABLE_NAME)) {

			List<AdUnitTable> unitTables = new ArrayList<>();

			for (Map<String, String> fieldValueMap :
					rowData.getFieldValueMap()) {

				AdUnitTable unitTable = new AdUnitTable();

				fieldValueMap.forEach((k, v) -> {
					switch (k) {
						case Constant.AD_UNIT_TABLE_INFO.COLUMN_ID:
							unitTable.setUnitId(Long.valueOf(v));
							break;
						case Constant.AD_UNIT_TABLE_INFO.COLUMN_UNIT_STATUS:
							unitTable.setUnitStatus(Integer.valueOf(v));
							break;
						case Constant.AD_UNIT_TABLE_INFO.COLUMN_POSITION_TYPE:
							unitTable.setPositionType(Integer.valueOf(v));
							break;
						case Constant.AD_UNIT_TABLE_INFO.COLUMN_PLAN_ID:
							unitTable.setPlanId(Long.valueOf(v));
							break;
					}
				});

				unitTables.add(unitTable);
			}

			unitTables.forEach(u ->
					AdLevelDataHandler.handleLevel3(u, rowData.getOpType()));
		} else if (rowData.getTableName().equals(
				Constant.AD_CREATIVE_UNIT_TABLE_INFO.TABLE_NAME
		)) {
			List<AdCreativeUnitTable> creativeUnitTables = new ArrayList<>();

			for (Map<String, String> fieldValueMap :
					rowData.getFieldValueMap()) {

				AdCreativeUnitTable creativeUnitTable = new AdCreativeUnitTable();

				fieldValueMap.forEach((k, v) -> {
					switch (k) {
						case Constant.AD_CREATIVE_UNIT_TABLE_INFO.COLUMN_CREATIVE_ID:
							creativeUnitTable.setAdId(Long.valueOf(v));
							break;
						case Constant.AD_CREATIVE_UNIT_TABLE_INFO.COLUMN_UNIT_ID:
							creativeUnitTable.setUnitId(Long.valueOf(v));
							break;
					}
				});

				creativeUnitTables.add(creativeUnitTable);
			}

			creativeUnitTables.forEach(
					u -> AdLevelDataHandler.handleLevel3(u, rowData.getOpType())
			);
		}
	}

	private void Level4RowData(MySqlRowData rowData) {

		switch (rowData.getTableName()) {

			case Constant.AD_UNIT_DISTRICT_TABLE_INFO.TABLE_NAME:
				List<AdUnitDistrictTable> districtTables = new ArrayList<>();

				for (Map<String, String> fieldValueMap :
						rowData.getFieldValueMap()) {

					AdUnitDistrictTable districtTable = new AdUnitDistrictTable();

					fieldValueMap.forEach((k, v) -> {
						switch (k) {
							case Constant.AD_UNIT_DISTRICT_TABLE_INFO.COLUMN_UNIT_ID:
								districtTable.setUnitId(Long.valueOf(v));
								break;
							case Constant.AD_UNIT_DISTRICT_TABLE_INFO.COLUMN_PROVINCE:
								districtTable.setProvince(v);
								break;
							case Constant.AD_UNIT_DISTRICT_TABLE_INFO.COLUMN_CITY:
								districtTable.setCity(v);
								break;
						}
					});

					districtTables.add(districtTable);
				}

				districtTables.forEach(
						d -> AdLevelDataHandler.handleLevel4(d, rowData.getOpType())
				);
				break;
			case Constant.AD_UNIT_IT_TABLE_INFO.TABLE_NAME:
				List<AdUnitItTable> itTables = new ArrayList<>();

				for (Map<String, String> fieldValueMap :
						rowData.getFieldValueMap()) {

					AdUnitItTable itTable = new AdUnitItTable();

					fieldValueMap.forEach((k, v) -> {
						switch (k) {
							case Constant.AD_UNIT_IT_TABLE_INFO.COLUMN_UNIT_ID:
								itTable.setUnitId(Long.valueOf(v));
								break;
							case Constant.AD_UNIT_IT_TABLE_INFO.COLUMN_IT_TAG:
								itTable.setItTag(v);
								break;
						}
					});
					itTables.add(itTable);
				}
				itTables.forEach(
						i -> AdLevelDataHandler.handleLevel4(i, rowData.getOpType())
				);
				break;
			case Constant.AD_UNIT_KEYWORD_TABLE_INFO.TABLE_NAME:

				List<AdUnitKeywordTable> keywordTables = new ArrayList<>();

				for (Map<String, String> fieldValueMap :
						rowData.getFieldValueMap()) {
					AdUnitKeywordTable keywordTable = new AdUnitKeywordTable();

					fieldValueMap.forEach((k, v) -> {
						switch (k) {
							case Constant.AD_UNIT_KEYWORD_TABLE_INFO.COLUMN_UNIT_ID:
								keywordTable.setUnitId(Long.valueOf(v));
								break;
							case Constant.AD_UNIT_KEYWORD_TABLE_INFO.COLUMN_KEYWORD:
								keywordTable.setKeyword(v);
								break;
						}
					});
					keywordTables.add(keywordTable);
				}

				keywordTables.forEach(
						k -> AdLevelDataHandler.handleLevel4(k, rowData.getOpType())
				);
				break;
		}
	}
}
