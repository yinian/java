package com.yinian.rcp.remoting.invoker.reference.impl;

import com.yinian.rcp.remoting.invoker.call.CallType;
import com.yinian.rcp.remoting.invoker.call.XxlRpcInvokeCallback;
import com.yinian.rcp.remoting.invoker.reference.XxlRpcReferenceBean;
import com.yinian.rcp.remoting.net.NetEnum;
import com.yinian.rcp.serialize.Serializer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * rpc reference bean, use by spring xml and annotation (for spring)
 *
 * @author xuxueli 2015-10-29 20:18:32
 */
public class XxlRpcSpringReferenceBean implements FactoryBean<Object>, InitializingBean {


    // ---------------------- config ----------------------

    private String netType = NetEnum.JETTY.name();
    private String serialize = Serializer.SerializeEnum.HESSIAN.name();
    private String callType = CallType.SYNC.name();

    private Class<?> iface;
    private String version;

    private long timeout = 1000;

    private String address;
    private String accessToken;

    private XxlRpcInvokeCallback invokeCallback;

    // set
    public void setNetType(String netType) {
        this.netType = netType;
    }

    public void setSerialize(String serialize) {
        this.serialize = serialize;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public void setIface(Class<?> iface) {
        this.iface = iface;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setInvokeCallback(XxlRpcInvokeCallback invokeCallback) {
        this.invokeCallback = invokeCallback;
    }

    // ---------------------- init ----------------------

    private XxlRpcReferenceBean xxlRpcReferenceBean;
    private void init() {

        // prepare config
        NetEnum netTypeEnum = NetEnum.autoMatch(netType, null);
        Serializer.SerializeEnum serializeEnum = Serializer.SerializeEnum.match(serialize, null);
        Serializer serializer = serializeEnum!=null?serializeEnum.getSerializer():null;
        CallType callTypeEnum = CallType.match(callType, null);
        if (timeout <= 0) {
            timeout = 10;
        }

        // init config
        xxlRpcReferenceBean = new XxlRpcReferenceBean(netTypeEnum, serializer, callTypeEnum, iface, version, timeout, address, accessToken, invokeCallback);
    }

    // ---------------------- util ----------------------

    @Override
    public void afterPropertiesSet() {
        init();
    }

    @Override
    public Object getObject() {
        return xxlRpcReferenceBean.getObject();
    }

    @Override
    public Class<?> getObjectType() {
        return xxlRpcReferenceBean.getObjectType();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }


    /**
     *	public static <T> ClientProxy ClientProxy<T> getFuture(Class<T> type) {
     *		<T> ClientProxy proxy = (<T>) new ClientProxy();
     *		return proxy;
     *	}
     */


}
