package com.imooc.ad.search;

import com.imooc.ad.search.vo.SearchRequest;
import com.imooc.ad.search.vo.SearchResponse;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 12:43 2019/4/24
 * @Modified By:
 */
public interface ISearch {

	SearchResponse fetchAds(SearchRequest request);
}

