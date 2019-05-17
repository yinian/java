package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo07_multiconditions_lock;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        FileMock mock = new FileMock(101,10);
        Buffer buffer = new Buffer(20);

        Producer producer = new Producer(mock,buffer);
        Thread threadProducer = new Thread(producer, "Producer");
        
        Consumer consumers[] = new Consumer[3];
        Thread threadConsumers[] = new Thread[3];

        for (int i = 0; i < 3; i++) {
            consumers[i] = new Consumer(buffer);
            threadConsumers[i] = new Thread(consumers[i], "Consumer " + i);
            
        }

        threadProducer.start();
        for (int i = 0; i < 3; i++) {
            threadConsumers[i].start();

        }
    }
    /*
    所 有Condition对象都与锁有关，并且使用声明在Lock接口中的newCondition()方法来创建。使用condition做任何操作之前， 你必须获取与这个condition相关的锁的控制。所以，condition的操作一定是在以调用Lock对象的lock()方法为开头，
    以调用相同 Lock对象的unlock()方法为结尾的代码块中。

当一个线程在一个condition上调用await()方法时，它将自动释放锁的控制，
所以其他线程可以获取这个锁的控制并开始执行相同操作，
或者由同个锁保护的其他临界区。

注释：当一个线程在一个condition上调用signal()或signallAll()方法，
一个或者全部在这个condition上等待的线程将被唤醒。
这并不能保证的使它们现在睡眠的条件现在是true，
所以你必须在while循环内部调用await()方法。你不能离开这个循环，
直到 condition为true。当condition为false，你必须再次调用 await()方法。
     */
}
