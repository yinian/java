package com.viagra.synchronous;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class GenericSpringEvent<T> {

    @Getter
    private final T what;

    protected final boolean success;
    public boolean isSuccess() {
        return success;
    }




}
