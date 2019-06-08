package com.viagra.java.concurrent.java7Concurrencycookbook.chapter05.fork_join.demo02.join_results_task;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class LineTask extends RecursiveTask<Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String[] line;

    private int start, end;

    private String word;

    public LineTask(String[] line, int start, int end, String word) {
        this.line = line;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {

        int counter = 0;
        for (int i = start; i < end; i++) {

            if (line[i].equals(word)) {
                counter++;
            }

        }

        try {
            // 减缓程序的执行，休眠一会儿
            TimeUnit.MICROSECONDS.sleep(10);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        return counter;

    }

    private Integer groupResults(Integer number1, Integer number2) {
        Integer result;

        result = number1 + number2;
        return result;
    }

}
