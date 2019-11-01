package com.yinian.rcp.remoting.net.pool;

import com.yinian.rcp.remoting.invoker.XxlRpcInvokerFactory;
import com.yinian.rcp.remoting.net.params.BaseCallback;
import com.yinian.rcp.remoting.net.params.XxlRpcRequest;
import com.yinian.rcp.serialize.Serializer;
import com.yinian.rcp.util.IpUtil;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yinian 2018-10-19
 */
public abstract class ClientPooled {
    protected static transient Logger logger = LoggerFactory.getLogger(ClientPooled.class);


    // ---------------------- iface ----------------------

    public abstract void init(String host, int port, Serializer serializer) throws Exception;

    public abstract void close();

    public abstract boolean isValidate();

    public abstract void send(XxlRpcRequest xxlRpcRequest) throws Exception ;


    // ---------------------- client pool map ----------------------

    private static ConcurrentHashMap<String, GenericObjectPool<ClientPooled>> clientPoolMap;
    public static GenericObjectPool<ClientPooled> getPool(String address, Serializer serializer, final Class<? extends ClientPooled> clientPoolImpl) throws Exception {

        if (clientPoolMap == null){
            // init
            clientPoolMap = new ConcurrentHashMap<String, GenericObjectPool<ClientPooled>>();
            // stop callback
            XxlRpcInvokerFactory.addStopCallBack(new BaseCallback() {
                @Override
                public void run() throws Exception {
                    if (clientPoolMap.size() > 0){
                        for (String key : clientPoolMap.keySet()){
                            GenericObjectPool<ClientPooled> clientPool =
                                    clientPoolMap.get(key);
                            clientPool.close();
                        }
                        clientPoolMap.clear();
                    }
                }
            });
        }

        // get pool
        GenericObjectPool<ClientPooled> clientPool = clientPoolMap.get(address);
        if (clientPool!=null){
            return clientPool;
        }

        // parse address
        Object[] array = IpUtil.parseIpPort(address);
        String host = (String) array[0];
        int port = (int) array[1];

        // set pool
        clientPool = new GenericObjectPool<ClientPooled>(new ClientPoolFactory(host,port,serializer,clientPoolImpl));
        clientPool.setTestOnBorrow(true);
        clientPool.setMaxTotal(2);

        clientPoolMap.put(address,clientPool);

        return clientPool;
    }

}
