package com.viagra.java.concurrent.java7Concurrencycookbook.chapter04.thread_executors.demo01.create_a_thread_executor;

import java.util.Date;

/*
实现能被服务器执行的任务
 */
public class Task implements Runnable{


    // 任务开始时间
    private Date initDate;

    // 任务名称
    private String name;

    public Task(String name) {
        initDate = new Date();
        this.name = name;
    }
    @Override
    public void run() {

    }
}
