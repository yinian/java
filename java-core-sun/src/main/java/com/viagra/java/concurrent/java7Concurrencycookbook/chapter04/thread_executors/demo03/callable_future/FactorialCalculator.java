package com.viagra.java.concurrent.java7Concurrencycookbook.chapter04.thread_executors.demo03.callable_future;

import java.util.concurrent.Callable;

public class FactorialCalculator implements Callable<Integer> {

    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {

        int num, result;

        num = number.intValue();
        result = 1;

        if (num == 0 || num == 1) {
            result = 1;
        } else {
            for (int i = 2; i <= number; i++) {
                result *= i;
                Thread.sleep(20);
            }
        }
        System.out.printf("%s: %d\n", Thread.currentThread().getName(), result);

        return result;
    }
}
