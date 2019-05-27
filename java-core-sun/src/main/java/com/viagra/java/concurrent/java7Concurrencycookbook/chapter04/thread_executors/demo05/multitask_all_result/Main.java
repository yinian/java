package com.viagra.java.concurrent.java7Concurrencycookbook.chapter04.thread_executors.demo05.multitask_all_result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        // 创建3个任务并放在List中
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Task task = new Task("Task-" + i);
            taskList.add(task);
        }

        //接收所有的返回结果
        List<Future<Result>> resultList = null;

        // 没有空格居然也行,编译没有问题
        // List<Future<Result>>resultList = null;

        System.out.println(new Date());

        try {
            resultList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Finish the executor
        executor.shutdown();
        System.out.println(new Date());

        System.out.printf("Core: Printing the results\n");

        for (int i = 0; i < resultList.size(); i++) {
            Future<Result> future = resultList.get(i);

            try {
                Result result = future.get();
                System.out.printf("%s: %s\n", result.getName(), result.getValue());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }
}
