package com.imooc.ad.index.keyword;

import com.imooc.ad.index.IndexAware;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @Author: HASEE
 * @Description: 关键词--推广单元：一对多
 * 关键词限制的索引服务
 * @Date: Created in 11:33 2019/4/23
 * @Modified By:
 */
@Slf4j
@Component
public class UnitKeywordIndex implements IndexAware<String, Set<Long>>{

	private static Map<String, Set<Long>> keywordUnitMap;//keyword到unit的map
	private static Map<Long, Set<String>> unitKeywordMap;//unit到keyword的map

	static {
		keywordUnitMap = new ConcurrentHashMap<>();
		unitKeywordMap = new ConcurrentHashMap<>();
	}



	@Override
	public Set<Long> get(String key) {

		if (StringUtils.isEmpty(key)){
			return Collections.emptySet();
		}
		Set<Long> result = keywordUnitMap.get(key);
		if (result == null){
			return Collections.emptySet();
		}
		return result;
	}

	@Override
	public void add(String key, Set<Long> value) {

		log.info("UnitKeywordIndex, before add: {}", unitKeywordMap);

		Set<Long> unitIdSet = CommonUtils.getorCreate(
				key, keywordUnitMap,
				ConcurrentSkipListSet::new
		);

		unitIdSet.addAll(value);

		for(Long unitId : value){
			Set<String> keywordSet = CommonUtils.getorCreate(
					unitId,unitKeywordMap,
					ConcurrentSkipListSet::new
			);
			keywordSet.add(key);
		}
		log.info("UnitKeywordIndex, after add: {}", unitKeywordMap);
	}

	@Override
	public void update(String key, Set<Long> value) {

		log.error("keyword index can not support update");

	}

	/**
	 * 删除部分keyword到unitid的映射，不一定是全部
	 * @param key
	 * @param value
	 */
	@Override
	public void delete(String key, Set<Long> value) {

		log.info("UnitKeywordIndex, before delete: {}", unitKeywordMap);

		Set<Long> unitIds = CommonUtils.getorCreate(
				key, keywordUnitMap,
				ConcurrentSkipListSet::new
		);

		unitIds.removeAll(value);//删除unitid
		//删除unitid对应的关键词
		for (Long unitId : value){
			Set<String> keywordSet = CommonUtils.getorCreate(
					unitId,unitKeywordMap,
					ConcurrentSkipListSet::new
			);
			keywordSet.remove(key);
		}
		log.info("UnitKeywordIndex, after delete: {}", unitKeywordMap);

	}
	// 判断 unitId,里面是否包含某个关键词
	public boolean match(Long unitId, List<String> keywords) {

		if(unitKeywordMap.containsKey(unitId)
				&& CollectionUtils.isNotEmpty(unitKeywordMap.get(unitId))){
			Set<String> unitKeywords = unitKeywordMap.get(unitId);
			return CollectionUtils.isSubCollection(keywords, unitKeywords);// unitKeywords 是否包含keywords .
		}
		return false;

	}

}

