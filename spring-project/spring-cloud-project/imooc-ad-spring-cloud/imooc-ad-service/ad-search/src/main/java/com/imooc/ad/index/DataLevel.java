package com.imooc.ad.index;

import lombok.Getter;

/**
 * @Author: HASEE
 * @Description: 数据层级
 * @Date: Created in 10:55 2019/4/24
 * @Modified By:
 */
@Getter
public enum  DataLevel {

	LEVEL2("2", "level 2"),
	LEVEL3("3", "level 3"),
	LEVEL4("4", "level 4");

	private String level;
	private String desc;

	DataLevel(String level, String desc) {
		this.level = level;
		this.desc = desc;
	}
}
