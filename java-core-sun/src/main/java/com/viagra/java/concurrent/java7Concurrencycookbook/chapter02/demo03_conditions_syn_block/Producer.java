package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo03_conditions_syn_block;

public class Producer implements Runnable {
    private EventStorage storage;
    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.set();
        }
    }
}
