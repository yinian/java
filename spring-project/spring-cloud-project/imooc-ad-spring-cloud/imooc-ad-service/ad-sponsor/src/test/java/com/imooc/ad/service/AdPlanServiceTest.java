package com.imooc.ad.service;

import com.imooc.ad.Application;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.AdPlanGetRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * @Author: HASEE
 * @Description: 对AdPlanService进行的测试用例
 * @Date: Created in 10:38 2019/4/25
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
	webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AdPlanServiceTest {

	@Autowired
	private IAdPlanService planService;

	@Test
	public void testGetAdPlan() throws AdException {

		System.out.println(

				planService.getAdPlanByIds(
						new AdPlanGetRequest(15L, Collections.singletonList(10L))
				)
		);
	}

}
