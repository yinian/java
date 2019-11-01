package com.yinian.rcp.remoting.net.impl.jetty.client;

import com.yinian.rcp.remoting.invoker.XxlRpcInvokerFactory;
import com.yinian.rcp.remoting.net.Client;
import com.yinian.rcp.remoting.net.params.*;
import com.yinian.rcp.util.XxlRpcException;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.BufferingResponseListener;
import org.eclipse.jetty.client.util.BytesContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import java.util.concurrent.TimeUnit;

//import com.xxl.rpc.registry.ZkServiceDiscovery;

/**
 * jetty client
 *
 * @author xuxueli 2015-11-24 22:25:15
 */
public class JettyClient extends Client {

	@Override
	public void asyncSend(String address, XxlRpcRequest xxlRpcRequest) throws Exception {

		// reqURL
		String reqURL = "http://" + address;

		// do invoke
		postRequestAsync(reqURL, xxlRpcRequest);
	}

    /**
     * post request (async)
     *
     * @param reqURL
     * @return
     * @throws Exception
     */
    private void postRequestAsync(String reqURL, XxlRpcRequest xxlRpcRequest) throws Exception {

    	// serialize request
		byte[] requestBytes = xxlRpcReferenceBean.getSerializer().serialize(xxlRpcRequest);

		// httpclient
		HttpClient httpClient = getJettyHttpClient();

		// request
		Request request = httpClient.newRequest(reqURL);
		request.method(HttpMethod.POST);
		request.timeout(xxlRpcReferenceBean.getTimeout() + 1000, TimeUnit.MILLISECONDS);
		// async not need timeout
		request.content(new BytesContentProvider(requestBytes));

		// invoke
		request.send(new BufferingResponseListener() {
			@Override
			public void onComplete(Result result) {
				try {

					// valid status
					if (result.isFailed()) {
						throw new XxlRpcException("xxl-rpc remoting request error.", result.getResponseFailure());
					}

					// valid HttpStatus
					if (result.getResponse().getStatus() != HttpStatus.OK_200) {
						throw new XxlRpcException("xxl-rpc remoting request fail, http HttpStatus["+ result.getResponse().getStatus() +"] invalid.");
					}

					// valid response bytes
					byte[] responseBytes = getContent();
					if (responseBytes == null || responseBytes.length==0) {
						throw new XxlRpcException("xxl-rpc remoting request fail, response bytes is empty.");
					}

					// deserialize response
					XxlRpcResponse xxlRpcResponse = (XxlRpcResponse) xxlRpcReferenceBean.getSerializer().deserialize(responseBytes, XxlRpcResponse.class);

					// wait response
					XxlRpcFutureResponse futureResponse = XxlRpcFutureResponseFactory.getInvokerFuture(xxlRpcResponse.getRequestId());
					if (futureResponse != null) {
						futureResponse.setResponse(xxlRpcResponse);
					}

				} catch (Exception e){

					// fail, request finish, remove request
					if (result.getRequest().getContent() instanceof BytesContentProvider) {
						try {
							BytesContentProvider requestCp = (BytesContentProvider) result.getRequest().getContent();
							XxlRpcRequest requestTmp = (XxlRpcRequest) xxlRpcReferenceBean.getSerializer().deserialize(requestCp.iterator().next().array(), XxlRpcRequest.class);

							XxlRpcFutureResponseFactory.removeInvokerFuture(requestTmp.getRequestId());
						} catch (Exception e2) {
							logger.info(">>>>>>>>>>> xxl-rpc, jetty async request fail and remove invoke future error: ", e2.getMessage());
						}
					}

					throw e;
				}
			}
		});

	}

	/**
	 * make jetty http client
	 *
	 * @return
	 * @throws Exception
	 */
	private static HttpClient jettyHttpClient;
	public synchronized static HttpClient getJettyHttpClient() throws Exception {
		if (jettyHttpClient != null) {
			return jettyHttpClient;
		}

		// init jettp httpclient
		jettyHttpClient = new HttpClient();
		jettyHttpClient.setFollowRedirects(false);	                // avoid redirect-302
		jettyHttpClient.setExecutor(new QueuedThreadPool());		// default maxThreads 200, minThreads 0
		jettyHttpClient.setMaxConnectionsPerDestination(10000);	    // limit conn per desc
		jettyHttpClient.start();						            // start

		// stop callback
		XxlRpcInvokerFactory.addStopCallBack(new BaseCallback() {
			@Override
			public void run() throws Exception {
				if (jettyHttpClient != null) {
					jettyHttpClient.stop();
					jettyHttpClient = null;
				}
			}
		});

		return jettyHttpClient;
	}




}
