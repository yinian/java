package com.imooc.ad.index.creativeunit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Author: HASEE
 * @Description: 创意与推广单元索引关联
 * @Date: Created in 14:36 2019/4/23
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitObject {

	private Long adId;
	private Long unitId;

	// adId-unitId
}
