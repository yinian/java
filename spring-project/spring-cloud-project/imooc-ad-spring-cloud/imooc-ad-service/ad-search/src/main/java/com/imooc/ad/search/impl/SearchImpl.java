package com.imooc.ad.search.impl;

import com.imooc.ad.index.CommonStatus;
import com.imooc.ad.index.DataTable;
import com.imooc.ad.index.adunit.AdUnitIndex;
import com.imooc.ad.index.adunit.AdUnitObject;
import com.imooc.ad.index.creative.CreativeObject;
import com.imooc.ad.index.district.UnitDistrictIndex;
import com.imooc.ad.index.interest.UnitItIndex;
import com.imooc.ad.index.keyword.UnitKeywordIndex;
import com.imooc.ad.search.ISearch;
import com.imooc.ad.search.vo.SearchRequest;
import com.imooc.ad.search.vo.SearchResponse;
import com.imooc.ad.search.vo.feature.DistrictFeature;
import com.imooc.ad.search.vo.feature.FeatureRelation;
import com.imooc.ad.search.vo.feature.ItFeature;
import com.imooc.ad.search.vo.feature.KeywordFeature;
import com.imooc.ad.search.vo.media.AdSlot;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 14:20 2019/4/24
 * @Modified By:
 */
@Slf4j
@Service
public class SearchImpl implements ISearch {
	@Override
	public SearchResponse fetchAds(SearchRequest request) {

		// 请求的广告位信息
		List<AdSlot> adSlots = request.getRequestInfo().getAdslots();
		//三个Feature
		// 三个 Feature
		KeywordFeature keywordFeature =
				request.getFeatureInfo().getKeywordFeature();
		DistrictFeature districtFeature =
				request.getFeatureInfo().getDistrictFeature();
		ItFeature itFeature =
				request.getFeatureInfo().getItFeature();

		FeatureRelation relation = request.getFeatureInfo().getRelation();

		// 构造响应对象
		SearchResponse response = new SearchResponse();
		Map<String, List<SearchResponse.Creative>> adSlots2Ads =
				response.getAdSlot2Ads();

		for(AdSlot adSlot : adSlots){
			Set<Long> targetUnitIdSet;
			//根据流量类型获取初始 AdUnit
			Set<Long> adUnitIdSet = DataTable.of(AdUnitIndex.class)
					.match(adSlot.getPositionType());

			if (relation == FeatureRelation.AND) {

				filterKeywordFeature(adUnitIdSet, keywordFeature);
				filterDistrictFeature(adUnitIdSet, districtFeature);
				filterItTagFeature(adUnitIdSet, itFeature);

				targetUnitIdSet = adUnitIdSet;

			} else {
				targetUnitIdSet = getORRelationUnitIds(
						adUnitIdSet,
						keywordFeature,
						districtFeature,
						itFeature
				);
			}
		}
		return null;
	}

	private Set<Long> getORRelationUnitIds(Set<Long> adUnitIdSet,
	                                       KeywordFeature keywordFeature,
	                                       DistrictFeature districtFeature,
	                                       ItFeature itFeature) {

		if (CollectionUtils.isEmpty(adUnitIdSet)) {
			return Collections.emptySet();
		}

		Set<Long> keywordUnitIdSet = new HashSet<>(adUnitIdSet);
		Set<Long> districtUnitIdSet = new HashSet<>(adUnitIdSet);
		Set<Long> itUnitIdSet = new HashSet<>(adUnitIdSet);

		filterKeywordFeature(keywordUnitIdSet, keywordFeature);
		filterDistrictFeature(districtUnitIdSet, districtFeature);
		filterItTagFeature(itUnitIdSet, itFeature);

		return new HashSet<>(
				CollectionUtils.union(
						CollectionUtils.union(keywordUnitIdSet, districtUnitIdSet),
						itUnitIdSet
				)
		);
	}

	private void filterKeywordFeature(
			Collection<Long> adUnitIds, KeywordFeature keywordFeature) {
		if (CollectionUtils.isEmpty(adUnitIds)){
			return;
		}

		if (CollectionUtils.isNotEmpty(keywordFeature.getKeywords())){
			CollectionUtils.filter(
					adUnitIds,
					adUnitId ->
							DataTable.of(UnitKeywordIndex.class)
					.match(adUnitId,
							keywordFeature.getKeywords())
			);
		}
	}

	private void filterDistrictFeature(
			Collection<Long> adUnitIds, DistrictFeature districtFeature
	) {
		if (CollectionUtils.isEmpty(adUnitIds)) {
			return;
		}

		if (CollectionUtils.isNotEmpty(districtFeature.getDistricts())) {

			CollectionUtils.filter(
					adUnitIds,
					adUnitId ->
							DataTable.of(UnitDistrictIndex.class)
									.match(adUnitId,
											districtFeature.getDistricts())
			);
		}
	}

	private void filterItTagFeature(Collection<Long> adUnitIds,
	                                ItFeature itFeature) {

		if (CollectionUtils.isEmpty(adUnitIds)) {
			return;
		}

		if (CollectionUtils.isNotEmpty(itFeature.getIts())) {

			CollectionUtils.filter(
					adUnitIds,
					adUnitId ->
							DataTable.of(UnitItIndex.class)
									.match(adUnitId,
											itFeature.getIts())
			);
		}
	}

	private void filterAdUnitAndPlanStatus(List<AdUnitObject> unitObjects,
	                                       CommonStatus status) {

		if (CollectionUtils.isEmpty(unitObjects)) {
			return;
		}

		CollectionUtils.filter(
				unitObjects,
				object -> object.getUnitStatus().equals(status.getStatus())
						&& object.getAdPlanObject().getPlanStatus().equals(status.getStatus())
		);
	}

	private void filterCreativeByAdSlot(List<CreativeObject> creatives,
	                                    Integer width,
	                                    Integer height,
	                                    List<Integer> type) {

		if (CollectionUtils.isEmpty(creatives)) {
			return;
		}

		CollectionUtils.filter(
				creatives,
				creative ->
						creative.getAuditStatus().equals(CommonStatus.VALID.getStatus())
								&& creative.getWidth().equals(width)
								&& creative.getHeight().equals(height)
								&& type.contains(creative.getType())
		);
	}

	private List<SearchResponse.Creative> buildCreativeResponse(
			List<CreativeObject> creatives
	) {

		if (CollectionUtils.isEmpty(creatives)) {
			return Collections.emptyList();
		}

		CreativeObject randomObject = creatives.get(
				Math.abs(new Random().nextInt()) % creatives.size()
		);

		return Collections.singletonList(
				SearchResponse.convert(randomObject)
		);
	}
}
