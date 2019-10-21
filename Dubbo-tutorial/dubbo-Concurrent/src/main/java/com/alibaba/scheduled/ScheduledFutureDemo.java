package com.alibaba.scheduled;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Auther: viagra
 * @Date: 2019/7/20 10:34
 * @Description: 主要是参考dubbo-2.6.1 中 MulticastRegistry.java 源文件
 */
public class ScheduledFutureDemo {

    ConcurrentMap<String, Set<String>> received =
            new ConcurrentHashMap<>();
   ScheduledExecutorService cleanExecutor =
            Executors.newScheduledThreadPool(1,
                    new NamedThreadFactory("viagra", true));

    ScheduledFuture<?> cleanFuture;

    int cleanPeriod;

    ConcurrentMap<String, String> receivedMap = new ConcurrentHashMap<String, String>();

    boolean flag;
    private volatile boolean admin = false;


    public ScheduledFutureDemo() {
    }

    @Test
    public void test1() {

        receivedMap.put("1","1");
        receivedMap.put("2","2");
        receivedMap.put("3","1");
        receivedMap.put("4","1");
        receivedMap.put("5","1");
        receivedMap.put("6","1");
        receivedMap.put("7","1");
        try {
            try {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("发送数据");
                    }
                },"ScheduledFutureDemoReceiver");
                // 设置为守护线程
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 优先从url里获取清理延时配置，若没有，则默认为60s
            this.cleanPeriod = 60 * 1000;
            this.flag = false;
            // 如果配置了参数需要清理
            if(flag){
                // 开启计时器
                this.cleanFuture = cleanExecutor.scheduleWithFixedDelay(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    // 清理过期的任务
                                    clean(receivedMap);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println("出现异常");

                                }
                            }
                        },cleanPeriod,cleanPeriod,TimeUnit.MICROSECONDS);
            }else{
                this.cleanFuture = null;
                System.out.println("什么也不做");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("list的个数："+received.isEmpty());
    }

    public void clean(ConcurrentMap<String, String> receivedMap) {

        if (admin){
            if (!receivedMap.isEmpty()){
                System.out.println("移除元素开始");
                receivedMap.remove("1","1");
                receivedMap.remove("2","1");
                receivedMap.remove("3","1");
                receivedMap.remove("4","1");
                receivedMap.remove("5","1");
                receivedMap.remove("6","1");
                receivedMap.remove("7","1");

            }
        }
    }
    public void destroy() {

        try {
            if (cleanFuture !=null){
                cleanFuture.cancel(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }









}
