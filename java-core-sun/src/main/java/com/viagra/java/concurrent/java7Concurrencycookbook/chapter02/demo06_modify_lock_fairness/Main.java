package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo06_modify_lock_fairness;

public class Main {


    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        Thread thread[] = new Thread[10];

        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);

        }

        for (int i = 0; i < 10; i++) {

            thread[i].start();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    /*
    所有线程都创建一个0.1秒的差异，第一需要获取锁的控制权的线程是Thread0，然后是Thread1，以此类推。当
    Thread0正在运行第一个由锁 保护的代码块时，
    有9个线程正在那个代码块上等待执行。当Thread0释放锁，它需要马上再次获取锁，所以我们有10个线程试图获取这个锁。
    当启用代码 模式，Lock接口将会选择Thread1，它是在这个锁上等待最长时间的线程。然后，选择Thread2，
    然后是Thread3，以此类推。直到所有线 程都通过了这个锁保护的第一个代码块，否则，没有一个线程能执行该锁保护的第二个代码块。

一旦所有线程已经执行完由这个锁保护的第一个代码块，再次轮到Thread0。然后，轮到Thread1，以此类推。
为了看与非公平模式的差异，改变传入锁构造器的参数，传入false值
在这种情况下，线程按被创建的顺序执行，但每个线程各自执行两个受保护的代码块。然而，这种行为的原因是没有保证的，
正如之前解释的，这个锁将选择任意一个线程获得访问保护代码块。在这种情况下，JVM不能保证线程的执行顺序。
     */
}
