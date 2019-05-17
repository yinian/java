package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo03_conditions_syn_block;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventStorage {

    // 最大存储量
    private int maxSize;

    // 存储的项目
    private List<Date> storage;

    public EventStorage() {
        maxSize = 10;
        // JDK7的新泛型语法
        storage = new LinkedList<>();
    }
    // 存储一个项目
    /*
     实现synchronized方法set()，用来在storage上存储一个事件。首先，检查storage是否已满。如果满了，
     调用wait()方 法，直到storage有空的空间。在方法的尾部，
     我们调用notifyAll()方法来唤醒，所有在wait()方法上睡眠的线程。
     */
    public synchronized void set(){
        while (storage.size() == maxSize){
            try {
                wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        storage.add(new Date());
        System.out.printf("Set: %d \n",storage.size());
        notifyAll();
    }
    /*
    实现synchronized方法get()，
    用来在storage上获取一个事件。首先，检查storage是否有事件。
    如果没有，调用wait()方 法直到，storage有一些事件，
    在方法的尾部，我们调用notifyAll()方法来唤醒，所有在wait()方法上睡眠的线程。
     */
    // 删除一个项目
    public synchronized void get(){
        while (storage.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Get: %d: %s \n",storage.size(), ((LinkedList<?>)storage).poll());
        notifyAll();
    }



}
