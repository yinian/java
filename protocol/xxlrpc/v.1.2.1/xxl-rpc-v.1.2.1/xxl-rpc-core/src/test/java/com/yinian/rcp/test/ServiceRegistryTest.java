package com.yinian.rcp.test;


import com.yinian.rcp.registry.ServiceRegistry;
import com.yinian.rcp.registry.impl.ZkServiceRegistry;
import com.yinian.rcp.util.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xuxueli 2018-10-17
 */
public class ServiceRegistryTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InterruptedException {

        Map<String, String> param = new HashMap<>();
        param.put(Environment.ZK_ADDRESS, "127.0.0.1:2181");
        param.put(Environment.ZK_DIGEST, "");
        param.put(Environment.ENV, "test");


        Class<? extends ServiceRegistry> serviceRegistryClass = ZkServiceRegistry.class;

        ServiceRegistry serviceRegistry = serviceRegistryClass.newInstance();
        serviceRegistry.start(param);


        String servicename = "demo_service";
        System.out.println(serviceRegistry.discovery(servicename));

        serviceRegistry.registry(servicename, "127.0.0.1:8888");
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(serviceRegistry.discovery(servicename));

        serviceRegistry.registry(servicename, "127.0.0.1:9999");
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(serviceRegistry.discovery(servicename));

        serviceRegistry.remove(servicename, "127.0.0.1:9999");
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(serviceRegistry.discovery(servicename));

    }

}
