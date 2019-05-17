package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo05_read_write_lock;

public class Main {

    public static void main(String[] args) {

        PricesInfo pricesInfo = new PricesInfo();

        Reader readers[] = new Reader[5];
        Thread threadsReader[] = new Thread[5];

        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(pricesInfo);
            threadsReader[i] = new Thread(readers[i]);

        }

        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);

        for (int i = 0; i < 5; i++) {
            threadsReader[i].start();
            
        }

        threadWriter.start();
    }

    /*

    ReentrantReadWriteLock类有两把锁，一把用于读操作，一把用于写操作。用于读操作的锁，
    是通过在 ReadWriteLock接口中声明的readLock()方法获取的。这个锁是实现Lock接口的一个对象，所以我们可以使用lock()，
     unlock() 和tryLock()方法。用于写操作的锁，是通过在ReadWriteLock接口中声明的writeLock()方法获取的。
     这个锁是实现Lock接 口的一个对象，所以我们可以使用lock()， unlock() 和tryLock()方法。确保正确的使用这些锁，
     使用它们与被设计的目的是一样的，这是程序猿的职责。
    当你获得Lock接口的读锁时，不能修改这个变量的值。否则，你可能会有数据不一致的错误。
     */
}
