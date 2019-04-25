package com.imooc.ad.sender;

import com.imooc.ad.mysql.dto.MySqlRowData;

/**
 * @Author: HASEE
 * @Description: 增量数据投递接口
 * @Date: Created in 11:01 2019/4/24
 * @Modified By:
 */
public interface ISender {
	void sender(MySqlRowData rowData);
}
