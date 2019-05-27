package com.viagra.java.concurrent.java7Concurrencycookbook.chapter04.thread_executors.demo11.control_rejected_tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {


    public static void main(String[] args) {

        RejectedTaskController controller = new RejectedTaskController();
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();
        // 设置被拒绝的任务的处理
//        executor.setRejectedExecutionHandler(controller);


        // 设置ThreadPoolExecutors实现的策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.printf("Main: Starting.\n");
        for (int i = 0; i < 3; i++) {
            Task task = new Task("Task" + i);
            executor.submit(task);
        }

        // 关闭执行者
        System.out.printf("Main: Shuting down the Executor.\n");
        executor.shutdown();
        System.out.println("执行者状态：" + executor.isShutdown());

        // 发送任务
        System.out.printf("Main: Sending another Task.\n");
        Task task = new Task("RejectedTask");
        executor.submit(task);

        System.out.printf("Main: End.\n");




    }


}
