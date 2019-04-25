package com.imooc.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: HASEE
 * @Description: 设备信息
 * @Date: Created in 12:54 2019/4/24
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

	// 设备 id
	private String deviceCode;

	// mac
	private String mac;

	// ip
	private String ip;

	// 机型编码
	private String model;

	// 分辨率尺寸
	private String displaySize;

	// 屏幕尺寸
	private String screenSize;

	// 设备序列号
	private String serialName;
}

