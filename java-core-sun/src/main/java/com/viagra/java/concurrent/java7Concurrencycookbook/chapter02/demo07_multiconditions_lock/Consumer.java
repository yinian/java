package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo07_multiconditions_lock;

import java.util.Random;

public class Consumer implements Runnable{

    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        // ¶ÁÈ¡bufferÖÐµÄÊý¾Ý
        while (buffer.hasPendingLines()) {
            String line = buffer.get();
            processLine(line);
        }
    }

    private void processLine(String line) {
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
