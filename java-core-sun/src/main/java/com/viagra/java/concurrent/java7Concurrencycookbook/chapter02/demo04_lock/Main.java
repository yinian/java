package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo04_lock;

public class Main {

    public static void main(String[] args) {

        PrintQueue printQueue = new PrintQueue();

        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }

    /*
    在 printJob()中，PrintQueue类是这个示例的关键所在。当我们通过锁来实现一个临界区并且保证只有一个执行线程能运行一个代码块，
    我们必 须创建一个ReentrantLock对象。在临界区的起始部分，我们必须通过使用lock()方法来获得锁的控制权。
    当一个线程A调用这个方法时，如果 没有其他线程持有这个锁的控制权，
    那么这个方法就会给线程A分配这个锁的控制权并且立即返回允许线程A执行这个临界区。
    否则，如果其他线程B正在执行由这 个锁控制的临界区，lock()方法将会使线程A睡眠直到线程B完成这个临界区的执行。

在临界区的尾部，我们必须使用unlock()方法来释放锁的控制权，允许其他线程运行这个临界区。如果你在临界区的尾部没有调用unlock()方法，那么其他正在等待该代码块的线程将会永远等待，造成 死锁情况。如果你在临界区使用try-catch代码块，别忘了在finally部分的内部包含unlock()方法的代码。
     */
}
