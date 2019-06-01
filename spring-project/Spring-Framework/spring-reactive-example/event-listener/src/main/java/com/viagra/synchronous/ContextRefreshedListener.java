package com.viagra.synchronous;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.ApplicationListener;


@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    // for tests
    private boolean hitContextRefreshedHandler = false;

    boolean isHitContextRefreshedHandler() {
        return hitContextRefreshedHandler;
    }

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        System.out.println("Handling context re-freshed event. ");

        hitContextRefreshedHandler = true;

    }
}
