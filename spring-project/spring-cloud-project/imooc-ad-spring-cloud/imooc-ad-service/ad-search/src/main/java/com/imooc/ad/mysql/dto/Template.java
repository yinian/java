package com.imooc.ad.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 22:03 2019/4/23
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Template {

	private String database;
	private List<JsonTable> tableList;
}

