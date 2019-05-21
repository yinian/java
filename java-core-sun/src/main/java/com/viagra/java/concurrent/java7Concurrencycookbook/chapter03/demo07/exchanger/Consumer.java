package com.viagra.java.concurrent.java7Concurrencycookbook.chapter03.demo07.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {

    private List<String> buffer;

    /**
     * 同步与生产者的交互
     */
    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        int cycle = 1;

        for (int i = 0; i < 10; i++) {
            System.out.printf("Consumer: Cycle %d\n", cycle);

            try {
                // 等待生产者的数据,然后发送空的缓冲给生产者
                /*
                在每次循环，首先调用exchange()方法来与producer同步。
                Consumer需要消耗数据。此方法可能会抛出InterruptedException异常, 加上处理代码
                 */
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("Consumer: %d\n", buffer.size());

            for (int j = 0; j < 10; j++) {
                String message = buffer.get(0);
                System.out.printf("Consumer: %s\n", message);
                buffer.remove(0);
            }

            cycle++;
        }
    }
}
