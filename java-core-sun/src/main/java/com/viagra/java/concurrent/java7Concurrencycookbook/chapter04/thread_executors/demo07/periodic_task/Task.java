package com.viagra.java.concurrent.java7Concurrencycookbook.chapter04.thread_executors.demo07.periodic_task;

import java.util.Date;

public class Task implements Runnable{

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Executed at: %s\n", name, new Date());
    }
}
