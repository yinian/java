package com.yinian.rcp.test;

import com.yinian.rcp.util.XxlZkClient;

public class XxlZkClientTest {

    public static void main(String[] args) throws InterruptedException {
        XxlZkClient client = new XxlZkClient("127.0.0.1:7080", "/xxl-rpc/test", null, null);

        System.out.println(client.getClient());
        System.out.println(client.getClient());
    }

}