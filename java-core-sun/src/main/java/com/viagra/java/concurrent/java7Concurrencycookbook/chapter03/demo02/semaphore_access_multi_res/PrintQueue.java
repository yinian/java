package com.viagra.java.concurrent.java7Concurrencycookbook.chapter03.demo02.semaphore_access_multi_res;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

    // 信号量访问打印者的访问
    private Semaphore semaphore;

    // 控制空闲打印者的数组
    private boolean freePrinters[];

    // 控制访问freePrinters数组的锁
    private Lock lockPrinters;

    private final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");

    public PrintQueue() {
        semaphore = new Semaphore(3);
        freePrinters = new boolean[3];
        for (int i = 0; i < 3; i++) {
            freePrinters[i] = true;

        }

        lockPrinters = new ReentrantLock();
    }

    public void printJob(Object document) {
        String name = Thread.currentThread().getName();

        try {
            // 获取信号量，会抛出InterruptedException异常
            semaphore.acquire();

            System.out.println(name + " 获得信号量  at : " + sdf.format(new Date()));

            // 取得空闲打印者的数目
            int assignedPrinter = getPrinter();

            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job in Printer %d during %d seconds\n", name,
                    assignedPrinter, duration);
            TimeUnit.SECONDS.sleep(duration);

            freePrinters[assignedPrinter] = true;


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(name + " 释放信号量  at : " + sdf.format(new Date()) + "\r\n");

            /*
            最后，调用release() 方法来解放semaphore并标记打印机为空闲，通过在对应的freePrinters array引索内分配真值。
             */
            semaphore.release();
        }
    }

    private int getPrinter() {
        int ret = -1;

        try {
            lockPrinters.lock();
            /*
            在freePrinters array内找到第一个真值并在一个变量中保存这个引索值。修改值为false，因为等会这个打印机就会被使用。
             */
            for (int i = 0; i < freePrinters.length; i++) {
                if (freePrinters[i]){
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
            //13. 最后，解放lockPrinters对象并返回引索对象为真值
             */
            lockPrinters.unlock();
        }
        return ret;
    }


}
