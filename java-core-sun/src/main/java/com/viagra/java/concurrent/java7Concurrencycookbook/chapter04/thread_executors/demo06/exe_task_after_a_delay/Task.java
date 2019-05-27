package com.viagra.java.concurrent.java7Concurrencycookbook.chapter04.thread_executors.demo06.exe_task_after_a_delay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

public class Task implements Callable<String> {

    private final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.printf("%s: Starting at : %s\n", name, sdf.format(new Date()));
        return "Hello, world";
    }
}
