package com.viagra.synchronous;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CustomSpringEvent extends ApplicationEvent {

    private String message;

    public CustomSpringEvent(final Object source,final String message) {
        super(source);
        this.message = message;
    }
}
