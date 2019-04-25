package com.imooc.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: HASEE
 * @Description: 地理位置信息
 * @Date: Created in 12:53 2019/4/24
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Geo {

	private Float latitude;
	private Float longitude;

	private String city;
	private String province;
}