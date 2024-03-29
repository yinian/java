package com.imooc.ad.index;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 14:47 2019/4/24
 * @Modified By:
 */
import lombok.Getter;

/**
 * Created by Qinyi.
 */
@Getter
public enum CommonStatus {

	VALID(1, "有效状态"),
	INVALID(0, "无效状态");

	private Integer status;
	private String desc;

	CommonStatus(Integer status, String desc) {
		this.status = status;
		this.desc = desc;
	}
}