package com.viagra.java.concurrent.java7Concurrencycookbook.chapter04.thread_executors.demo09.control_task_finished;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * FutureTask实现了Runnable, Future<V>两个接口
 * 管理ExecutableTask的执行.覆盖了done()方法,在任务结束后调用
 */
public class ResultTask extends FutureTask<String> {
    private String name;


    public ResultTask(Callable<String> callable) {
        // ExecutableTask任务
        super(callable);
        //取得ExecutableTask任务的名称
        this.name = ((ExecutableTask)callable).getName();

    }

    @Override
    protected void done() {
        if (isCancelled()){
            System.out.printf("%s: Has been cancelled\n", name);
        } else {
            System.out.printf("%s: Has finished\n", name);
        }
    }
}
