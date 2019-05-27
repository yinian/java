package com.viagra.java.concurrent.java7Concurrencycookbook.chapter04.thread_executors.demo04.multitask_get_first_result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 运行多个任务,只取得第一个返回的结果, invokeAny()
public class Main {

    public static void main(String[] args) {
        String username = "test";
        String password = "test";

        // 两个验证对象
        UserValidator ladpValidator = new UserValidator("LDAP");
        UserValidator dbValidator = new UserValidator("DataBase");

        TaskValidator ldapTask = new TaskValidator(ladpValidator, username, password);
        TaskValidator dbTask = new TaskValidator(dbValidator, username, password);

        List<TaskValidator> taskList = new ArrayList<>();
        taskList.add(ldapTask);
        taskList.add(dbTask);

        ExecutorService executor = Executors.newCachedThreadPool();
        String result;

        // 返回第一个结束的任务的结果
        try {
            result = executor.invokeAny(taskList);

            System.out.printf("Main: Result: %s\n", result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.printf("Main: End of the Execution\n");

    }
}
