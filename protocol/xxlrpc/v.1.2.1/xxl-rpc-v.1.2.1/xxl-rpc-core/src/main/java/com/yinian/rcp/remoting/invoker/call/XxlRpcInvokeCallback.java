package com.yinian.rcp.remoting.invoker.call;


/**
 * @author yinian 2018-10-23
 * @description 这个是重点，通过引入ThreadLocal线程类，来控制XxlRpcInvokeCallback的传递参数
 */
public abstract class XxlRpcInvokeCallback<T> {

    public abstract void onSuccess(T result);

    public abstract void onFailure(Throwable exception);

    // ---------------------------------thread invoke callback -----------
    private static ThreadLocal<XxlRpcInvokeCallback> threadInvokerFuture =
            new ThreadLocal<XxlRpcInvokeCallback>();

    /**
     * get callback
     *
     * @return
     */
    public static XxlRpcInvokeCallback getCallback() {

        XxlRpcInvokeCallback invokeCallback = threadInvokerFuture.get();
        threadInvokerFuture.remove();
        return invokeCallback;

    }

    /**
     * set future
     *
     * @param invokeCallback
     */
    public static void setCallback(XxlRpcInvokeCallback invokeCallback) {
        threadInvokerFuture.set(invokeCallback);
    }

    /**
     * remove future
     */
    public static void removeCallback() {
        threadInvokerFuture.remove();
    }



}
