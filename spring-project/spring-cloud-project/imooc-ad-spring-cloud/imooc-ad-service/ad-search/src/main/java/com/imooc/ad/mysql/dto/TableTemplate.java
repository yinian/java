package com.imooc.ad.mysql.dto;

import com.imooc.ad.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: HASEE
 * @Description: 方便读取一些表的信息
 * @Date: Created in 22:17 2019/4/23
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableTemplate {
	private String tableName;
	private String level;
	// 操作类型 - 多个列 组成 Map
	private Map<OpType, List<String>> opTypeFieldSetMap = new HashMap<>();

	/**
	 * 字段索引 -> 字段名
	 * */
	private Map<Integer, String> posMap = new HashMap<>();
}
