package com.imooc.ad.mysql.dto;
import com.github.shyiko.mysql.binlog.event.EventType;
import lombok.Data;

import java.util.List;
import java.util.Map;
/**
 * @Author: HASEE
 * @Description: 解析binlog对象，解析成java对象
 * @Date: Created in 9:48 2019/4/24
 * @Modified By:
 */
@Data
public class BinlogRowData {

	private TableTemplate table;

	private EventType eventType;

	private List<Map<String, String>> after;

	private List<Map<String, String>> before;


}
