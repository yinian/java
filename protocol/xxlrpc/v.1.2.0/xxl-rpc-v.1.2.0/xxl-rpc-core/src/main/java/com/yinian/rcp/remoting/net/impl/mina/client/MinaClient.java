package com.yinian.rcp.remoting.net.impl.mina.client;

import com.yinian.rcp.remoting.net.Client;
import com.yinian.rcp.remoting.net.params.XxlRpcRequest;
import com.yinian.rcp.remoting.net.pool.ClientPooled;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * mina client
 *
 * @author xuxueli 2015-11-24 22:25:15
 */
public class MinaClient extends Client {

	@Override
	public void asyncSend(String address, XxlRpcRequest xxlRpcRequest) throws Exception {

		// client pool
    	GenericObjectPool<ClientPooled> clientPool = ClientPooled.getPool(address, xxlRpcReferenceBean.getSerializer(), MinaPooledClient.class);
    	// client proxy
		ClientPooled clientPoolProxy = null;

		try {
			// proxy borrow
			clientPoolProxy = clientPool.borrowObject();

			// do invoke
			clientPoolProxy.send(xxlRpcRequest);
		} catch (Exception e) {
			throw e;
		} finally{
			// proxy return
			if (clientPoolProxy != null) {
				clientPool.returnObject(clientPoolProxy);
			}
		}

	}

}
