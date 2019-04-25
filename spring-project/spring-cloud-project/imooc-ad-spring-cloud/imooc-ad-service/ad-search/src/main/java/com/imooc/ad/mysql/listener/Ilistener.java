package com.imooc.ad.mysql.listener;

import com.imooc.ad.mysql.dto.BinlogRowData;

/**
 * @Author: HASEE
 * @Description: 主要是实现对binlog 增量索引更新等操作；参考 myslq-binlog-connector-java 实现的
 * @Date: Created in 9:54 2019/4/24
 * @Modified By:
 */
public interface Ilistener {

	void register();

	void onEvent(BinlogRowData eventData);
}
