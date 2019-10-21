package com.alibaba.scheduled;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: viagra
 * @Date: 2019/7/20 10:40
 * @Description: InternalThreadFactory： 复制dubbo的 NamedThreadFactory.java
 */
public class NamedThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

    private static final AtomicInteger mThreadNum = new AtomicInteger(1);

    private final String mPrefix;

    private final boolean mDaemon;

    private final ThreadGroup mGroup;

    public NamedThreadFactory() {
        this("pool-" + POOL_SEQ.getAndIncrement(), false);
    }

    public NamedThreadFactory(String prefix) {
        this(prefix,false);
    }

    public NamedThreadFactory(String prefix, boolean daemon) {
        mPrefix = prefix + "-thread-";
        mDaemon = daemon;
        SecurityManager s = System.getSecurityManager();
        mGroup = (s==null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {

        String name = mPrefix + mThreadNum.getAndIncrement();
        Thread ret = new Thread(mGroup,r,name,0);
        ret.setDaemon(mDaemon);
        return ret;
    }

    public ThreadGroup getmGroup() {
        return mGroup;
    }
}
