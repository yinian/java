package com.viagra.sun.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CASCounter {
    private final Unsafe unsafe;
    private volatile long counter = 0;
    private long offset;

    private Unsafe getUnsafe()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    public CASCounter() throws Exception {
        unsafe = getUnsafe();
        offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
    }

    public long getCounter() {
        return counter;
    }

    public void increment() {
        long before = counter;
        while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
            before = counter;
        }
    }

}