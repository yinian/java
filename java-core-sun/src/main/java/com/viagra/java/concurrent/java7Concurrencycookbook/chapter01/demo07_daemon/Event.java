package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo07_daemon;

import java.util.Date;

/**
 * @Author: HASEE
 * @Description: 存储了事件信息
 * @Date: Created in 11:47 2019/5/5
 * @Modified By:
 */
	// 事件的时间
public class Event {

	private Date date;

	// 事件的信息
	private String event;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}


}
