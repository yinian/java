package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo07_multiconditions_lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者共享的数据
 */
public class Buffer {

    // 缓冲，存储共享数据
    private LinkedList<String> buffer;

    // 缓冲的最大长度
    private int maxSize;

    // 控制访问缓冲的锁
    private ReentrantLock lock;

    // 控制缓冲是否还有一行数据和可用空间的条件
    private Condition lines;

    private Condition space;

    // 缓冲中是否还有行
    private boolean pendingLines;

    public Buffer(int maxSize) {
        // 初始化属性
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines = true;
    }

    /*
    实现insert()方法，接收一个String类型参数并试图将它存储到缓冲区。首先，它获得锁的控制。
    当它有锁的控制，它将检查缓冲区是否有空闲空间。如果缓冲区已满，它将调用await()方法在space条件上等待释放空间。
    如果其他线程在space条件上调用signal()或 signalAll()方法，这个线程将被唤醒。
    当这种情况发生，这个线程在缓冲区上存储行并且在lines条件上调用signallAll()方法，
    稍 后我们将会看到，这个条件将会唤醒所有在缓冲行上等待的线程。
     */
    public void insert(String line) {

        // 取得锁的控制
        lock.lock();

        try {
            // 缓冲区已满
            while (buffer.size() == maxSize) {

                // 注意：调用的是await()方法. 这个方法是Condition类的. 不要误写成wait(), 那是Object类的方法
                // 当然了也属于是Condition的方法,但是这里要注意这两个单词只有1个字母的区别,容易搞错
                // await()对应的是signal()、signalAll()
                // 调用await()方法会自动释放锁的控制
                space.await();  // 等待可用空间
                // 如果有另一线程调用signal()或者signalAll()方法，将被唤醒
            }

            buffer.offer(line);
            System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(), buffer.size());
            lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /*
    实现get()方法，它返回存储在缓冲区上的第一个字符串。首先，它获取锁的控制。当它拥有锁的控制，
    它检查缓冲区是否有行。如果缓冲区是空的，它调用 await()方法在lines条件上等待缓冲区中的行。
    如果其他线程在lines条件上调用signal()或signalAll()方法，
    这个线程将 被唤醒。当它发生时，这个方法获取缓冲区的首行，
    并且在space条件上调用signalAll()方法，然后返回String。
     */
    public String get() {

        String line = null;
        lock.lock();

        try {
            while ((buffer.size() == 0) && hasPendingLines()) {
                lines.await();// 被lines.signalAll()唤醒
            }

            if (hasPendingLines()) {
                line = buffer.poll();
                System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(), buffer.size());

                space.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return line;
    }


    /**
     * 是否有更多的行数据要处理
     */
    public boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }

    /**
     * 有生产者调用, 不会有更多的行数据
     */
    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }


}
