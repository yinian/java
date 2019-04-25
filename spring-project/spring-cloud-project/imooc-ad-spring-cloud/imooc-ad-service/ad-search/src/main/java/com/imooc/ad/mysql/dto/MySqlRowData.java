package com.imooc.ad.mysql.dto;

import com.imooc.ad.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: HASEE
 * @Description: binlog 增量索引
 * @Date: Created in 10:27 2019/4/24
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MySqlRowData {
	private String tableName;

	private String level;

	private OpType opType;

	private List<Map<String, String>> fieldValueMap = new ArrayList<>();
}
