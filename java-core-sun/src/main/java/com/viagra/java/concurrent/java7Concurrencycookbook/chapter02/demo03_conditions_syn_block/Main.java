package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo03_conditions_syn_block;

public class Main {


    public static void main(String[] args) {

        EventStorage storage = new EventStorage();

        // 生产者
        Producer producer = new Producer(storage);
        Thread thread1 = new Thread(producer);

        // 消费者
        Consumer consumer = new Consumer(storage);
        Thread thread2 = new Thread(consumer);

        thread2.start();
        thread1.start();
    }
}
