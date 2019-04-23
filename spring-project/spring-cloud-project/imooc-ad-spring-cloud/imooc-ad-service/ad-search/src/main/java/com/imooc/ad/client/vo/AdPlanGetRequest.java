package com.imooc.ad.client.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: HASEE
 * @Description: 演示Ribbon客户端访问其他服务
 * @Date: Created in 10:05 2019/4/23
 * @Modified By:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanGetRequest {

	private Long userId;
	private List<Long> ids;
}
