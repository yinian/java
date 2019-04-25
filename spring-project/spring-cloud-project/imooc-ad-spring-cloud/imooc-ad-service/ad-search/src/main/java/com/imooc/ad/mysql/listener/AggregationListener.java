package com.imooc.ad.mysql.listener;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.imooc.ad.mysql.TemplateHolder;
import com.imooc.ad.mysql.dto.BinlogRowData;
import com.imooc.ad.mysql.dto.TableTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: HASEE
 * @Description: 解析 binlog
 * @Date: Created in 9:56 2019/4/24
 * @Modified By:
 */
@Slf4j
@Component
public class AggregationListener implements BinaryLogClient.EventListener{


	private String dbName;
	private String tableName;

	private Map<String, Ilistener> listenerMap = new HashMap<>();

	private final TemplateHolder templateHolder;

	@Autowired
	public AggregationListener(TemplateHolder templateHolder) {
		this.templateHolder = templateHolder;
	}
	private String genKey(String dbName, String tableName) {
		return dbName + ":" + tableName;
	}
	public void register(String _dbName, String _tableName,
	                     Ilistener ilistener) {
		log.info("register : {}-{}", _dbName, _tableName);
		this.listenerMap.put(genKey(_dbName,_tableName),ilistener);
	}


	@Override
	public void onEvent(Event event) {

		EventType type = event.getHeader().getEventType();
		log.debug("event type: {}", type);
		if (type == EventType.TABLE_MAP){// 操作类型TABLE_MAP，包括数据库、数据表的信息
			TableMapEventData data = event.getData();
			this.tableName = data.getTable();
			this.dbName = data.getDatabase();
			return;
		}

		if (type != EventType.EXT_UPDATE_ROWS
				&& type != EventType.EXT_WRITE_ROWS
				&& type != EventType.EXT_DELETE_ROWS) {
			return;
		}

		// 表名和库名是否已经完成填充
		if (StringUtils.isEmpty(dbName) || StringUtils.isEmpty(tableName)){
			log.error("no meta data event");
			return;
		}

		// 找出对应表有兴趣的监听器
		String key = genKey(this.dbName,this.tableName);
		Ilistener listener = this.listenerMap.get(key);
		if(null == listener){
			log.debug("skip {}", key);
			return;
		}
		log.info("trigger event: {}", type.name());

		try {
			BinlogRowData rowData = buildRowData(event.getData());
			if (rowData == null){
				return;
			}
			rowData.setEventType(type);
			listener.onEvent(rowData);
		}catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		} finally {
			this.dbName = "";
			this.tableName = "";
		}


	}

	private BinlogRowData buildRowData(EventData eventData) {

		TableTemplate table = templateHolder.getTable(tableName);

		if (null == table){
			log.warn("table {} not found", tableName);
			return null;
		}

		List<Map<String,String>> afterMapList = new ArrayList<>();

		for (Serializable[] after : getAfterValues(eventData)){
			Map<String, String> afterMap = new HashMap<>();
			int colLen = after.length;
			for(int ix = 0; ix < colLen; ++ix){
				// 取出当前位置对应的列名
				String colName = table.getPosMap().get(ix);

				//如果没有则说明不关心这个列
				if (null == colName){
					log.debug("ignore position: {}", ix);
					continue;
				}
				String colValue = after[ix].toString();
				afterMap.put(colName,colValue);
			}
			afterMapList.add(afterMap);
		}

		BinlogRowData rowData = new BinlogRowData();
		rowData.setAfter(afterMapList);
		rowData.setTable(table);
		return rowData;
	}

	private List<Serializable[]> getAfterValues(EventData eventData) {
		if (eventData instanceof WriteRowsEventData){
			return ((WriteRowsEventData) eventData).getRows();
		}
		if (eventData instanceof UpdateRowsEventData){
			return ((UpdateRowsEventData) eventData).getRows().stream()
					.map(Map.Entry::getValue)
					.collect(Collectors.toList());
		}
		if (eventData instanceof DeleteRowsEventData) {
			return ((DeleteRowsEventData) eventData).getRows();
		}

		return Collections.emptyList();
	}


}
