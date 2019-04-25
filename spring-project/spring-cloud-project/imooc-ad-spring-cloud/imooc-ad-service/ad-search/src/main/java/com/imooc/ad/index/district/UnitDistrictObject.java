package com.imooc.ad.index.district;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 14:07 2019/4/23
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDistrictObject {

	private Long unitId;
	private String province;
	private String city;

	// <String, Set<Long>>
	// province-city
}
