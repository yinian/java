package com.viagra.sun.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class OffHeapArray {
    private final static int BYTE = 1;
    private long size;
    private long address;

    private Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    public OffHeapArray(long size) throws Exception {
        this.size = size;
        address = getUnsafe().allocateMemory(size * BYTE);
    }

    public void set(long i, byte value) throws IllegalAccessException, NoSuchFieldException {
        getUnsafe().putByte(address + i * BYTE, value);
    }

    public int get(long idx) throws IllegalAccessException, NoSuchFieldException {
        return getUnsafe().getByte(address + idx * BYTE);
    }

    public long size() {
        return size;
    }

    void freeMemory() throws IllegalAccessException, NoSuchFieldException {
        getUnsafe().freeMemory(address);
    }
}