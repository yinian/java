package com.viagra.java.concurrent.java7Concurrencycookbook.chapter04.thread_executors.demo09.control_task_finished;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        // 创建5个任务
        ResultTask[] resultTasks = new ResultTask[5];
        for (int i = 0; i < 5; i++) {
            ExecutableTask executableTask = new ExecutableTask("Task " + i);
            resultTasks[i] = new ResultTask(executableTask);
            executor.submit(resultTasks[i]);

        }

        try {
            // 休眠5秒
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        // 取消所有的任务,如果在这之前有的任务已经结束了,那么这里的取消操作不会有任何的影响
        for (int i = 0; i < resultTasks.length; i++) {
            // 会调用ResultTask类的done()方法
            resultTasks[i].cancel(true);

        }

        System.out.println("\r\nPrint the results:\n");
// 打印没有取消的任务
        for (int i = 0; i < resultTasks.length; i++) {

            if (!resultTasks[i].isCancelled()) {
                // 取得返回结果
                try {
                    System.out.printf("%s\n", resultTasks[i].get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        // Finish the executor
        executor.shutdown();
    }
}
