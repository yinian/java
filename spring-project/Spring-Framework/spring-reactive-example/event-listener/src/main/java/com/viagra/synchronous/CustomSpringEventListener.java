package com.viagra.synchronous;


import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {
    public void onApplicationEvent(final CustomSpringEvent customSpringEvent) {
        System.out.println("Received spring custom event - " + customSpringEvent.getMessage());

    }
}
