package com.viagra.java.concurrent.java7Concurrencycookbook.chapter04.thread_executors.demo10.separate_tasks;
// 提交任务, 通过CompletionService执行

import java.util.concurrent.CompletionService;

public class ReportRequest implements Runnable{

    private String name;

    private CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {

        // ReportGenerator
        ReportGenerator reportGenerator =
                new ReportGenerator(name, "Report");
        // 使用CompletionService提交任务
        service.submit(reportGenerator);



    }
}