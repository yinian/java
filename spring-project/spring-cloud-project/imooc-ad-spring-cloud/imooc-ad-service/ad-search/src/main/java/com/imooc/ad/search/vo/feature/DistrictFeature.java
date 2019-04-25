package com.imooc.ad.search.vo.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 12:59 2019/4/24
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictFeature {

	private List<ProvinceAndCity> districts;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ProvinceAndCity {

		private String province;
		private String city;
	}
}
