package com.viagra.java.concurrent.java7Concurrencycookbook.chapter04.thread_executors.demo08.cancel_task;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        while (true) {
            System.out.printf("Task: Test\n");
            Thread.sleep(100);
        }
    }
}
