package com.viagra.java.concurrent.java7Concurrencycookbook.chapter04.thread_executors.demo02.fixsize_executor;

public class Main {


    public static void main(String[] args) {
        Server server = new Server();

        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task " + i);
            server.executeTask(task);
            
        }
        server.endServer();
    }
}
