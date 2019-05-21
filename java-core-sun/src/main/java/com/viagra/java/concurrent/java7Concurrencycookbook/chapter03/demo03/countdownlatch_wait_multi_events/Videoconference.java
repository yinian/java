package com.viagra.java.concurrent.java7Concurrencycookbook.chapter03.demo03.countdownlatch_wait_multi_events;

import java.util.concurrent.CountDownLatch;

/**
 * 实现了控制视频-会议
 * <p>
 * 使用CountDownLatch控制所有的参与者达到
 */
public class Videoconference implements Runnable{

    private final CountDownLatch controller;

    public Videoconference(int number) {
        //初始 CountDownLatch 属性。Videoconference 类接收将要等待的参与者的量为参数。
        controller = new CountDownLatch(number);
    }

    public void arrive(String name) {
        System.out.printf("%s has arrived.\n", name);

        // 计数器减1
        controller.countDown();
        //使用CountDownLatch对象的 getCount() 方法输出另一条关于还未确定到达的参与者数
        System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
    }


    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n\n", controller.getCount());

        try {
            // 等待所有的参与者，CountDownLatch内部的计数器为0立即返回
            controller.await();

            // 开始视频—会议
            //最后，输出信息表明全部参与者已经到达
            System.out.printf("\r\nVideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
