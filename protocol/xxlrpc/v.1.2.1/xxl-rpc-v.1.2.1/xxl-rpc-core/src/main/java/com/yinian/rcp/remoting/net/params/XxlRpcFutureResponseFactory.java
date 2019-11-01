package com.yinian.rcp.remoting.net.params;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * future response factory
 *
 * @author yinian 2018-10-22 18:52:21
 */
public class XxlRpcFutureResponseFactory {

    private static ConcurrentMap<String, XxlRpcFutureResponse> futureResponsePool = new ConcurrentHashMap<String, XxlRpcFutureResponse>();

    public static void setInvokerFuture(String requestId, XxlRpcFutureResponse futureResponse){
        futureResponsePool.put(requestId, futureResponse);
    }

    public static void removeInvokerFuture(String requestId){
        futureResponsePool.remove(requestId);
    }

    public static void notifyInvokerFuture(String requestId, XxlRpcResponse xxlRpcResponse){
       XxlRpcFutureResponse futureResponse = futureResponsePool.get(requestId);
       if (futureResponse != null){
           futureResponse.setResponse(xxlRpcResponse);
           futureResponsePool.remove(requestId);
       }
    }

}
