package com.viagra.synchronous;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;


@Component
public class GenericSpringEventListener implements ApplicationListener<GenericSpringAppEvent<String>> {

    // for testing
    private boolean hitEventHandler = false;



    boolean isHitEventHandler() {
        return hitEventHandler;
    }

    public void onApplicationEvent(GenericSpringAppEvent<String> stringGenericSpringAppEvent) {
        System.out.println("Received spring generic event - " + stringGenericSpringAppEvent.getWhat());
        hitEventHandler = true;
    }
}
