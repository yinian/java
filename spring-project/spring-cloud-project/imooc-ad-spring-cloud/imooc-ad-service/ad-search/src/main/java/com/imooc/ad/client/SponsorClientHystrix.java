package com.imooc.ad.client;

import com.imooc.ad.client.vo.AdPlan;
import com.imooc.ad.client.vo.AdPlanGetRequest;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 10:48 2019/4/23
 * @Modified By:
 */
@Component
public class SponsorClientHystrix implements SponsorClient {
	@Override
	public CommonResponse<List<AdPlan>> getAdPlans(AdPlanGetRequest request) {
		return new CommonResponse<>(-1,"eureka-client-ad-sponsor error");
	}
}
