package com.viagra.dubbo.spi.consumer;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;

import java.util.List;

/**
 * @Auther: viagra
 * @Date: 2019/7/28 16:48
 * @Description:
 */
public class DemoLoadBalance implements LoadBalance {
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> list, URL url, Invocation invocation) throws RpcException {
        System.out.println("DemoLoadBalance : Select the first invoker...");
        return list.get(0);
    }
}
