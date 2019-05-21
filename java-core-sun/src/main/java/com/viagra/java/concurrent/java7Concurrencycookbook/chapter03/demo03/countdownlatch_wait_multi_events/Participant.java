package com.viagra.java.concurrent.java7Concurrencycookbook.chapter03.demo03.countdownlatch_wait_multi_events;

import java.util.concurrent.TimeUnit;

public class Participant implements Runnable {
    private Videoconference conference;

    /**
     * 参与者的名称.只是用来做日志记录的
     */
    private String name;

    public Participant(Videoconference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    @Override
    public void run() {
//让线程随机休眠一段时间
        Long duration = (long) (Math.random() * 10);
        try {
            // 休眠
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//使用Videoconference 对象的arrive() 方法来表明参与者的到达
        conference.arrive(name);

    }
}
