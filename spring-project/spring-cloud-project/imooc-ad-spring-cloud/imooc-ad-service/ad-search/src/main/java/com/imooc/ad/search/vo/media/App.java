package com.imooc.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: HASEE
 * @Description: 终端信息
 * @Date: Created in 12:52 2019/4/24
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class App {

	// 应用编码
	private String appCode;
	// 应用名称
	private String appName;
	// 应用包名
	private String packageName;
	// activity 名称
	private String activityName;
}
