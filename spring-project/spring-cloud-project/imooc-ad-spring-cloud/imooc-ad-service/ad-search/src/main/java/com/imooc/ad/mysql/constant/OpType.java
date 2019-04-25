package com.imooc.ad.mysql.constant;
import com.github.shyiko.mysql.binlog.event.EventType;
/**
 * @Author: HASEE
 * @Description: Mysql操作类型
 * @Date: Created in 16:42 2019/4/23
 * @Modified By:
 */
public enum OpType {
	ADD,
	UPDATE,
	DELETE,
	OTHER;

	public static OpType to(EventType eventType) {

		switch (eventType) {
			case EXT_WRITE_ROWS:
				return ADD;
			case EXT_UPDATE_ROWS:
				return UPDATE;
			case EXT_DELETE_ROWS:
				return DELETE;
			default:
				return OTHER;
		}
	}
}
