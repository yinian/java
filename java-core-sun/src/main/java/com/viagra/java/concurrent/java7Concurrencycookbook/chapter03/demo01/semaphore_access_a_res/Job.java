package com.viagra.java.concurrent.java7Concurrencycookbook.chapter03.demo01.semaphore_access_a_res;

public class Job implements Runnable{

    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        printQueue.printJob(new Object());
    }
}
