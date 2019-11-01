package com.xxl.sample.client.test;

import com.yinian.rcp.remoting.invoker.XxlRpcInvokerFactory;
import com.yinian.rcp.remoting.invoker.call.CallType;
import com.yinian.rcp.remoting.invoker.call.XxlRpcInvokeCallback;
import com.yinian.rcp.remoting.invoker.call.XxlRpcInvokeFuture;
import com.yinian.rcp.remoting.invoker.reference.XxlRpcReferenceBean;
import com.yinian.rcp.remoting.net.NetEnum;
import com.yinian.rcp.serialize.Serializer;
import com.yinian.rpc.sample.api.DemoService;
import com.yinian.rpc.sample.api.dto.UserDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author xuxueli 2018-10-21 20:48:40
 */
public class ClientTest {


	XxlRpcInvokerFactory invokerFactory = null;
	@Before
	public void before() throws Exception {
		// init invoker factory
		invokerFactory = new XxlRpcInvokerFactory();
		invokerFactory.start();
	}

	@After
	public void after() throws Exception {
		// stop invoker factory
		invokerFactory.stop();
	}


	/**
	 * CallType.SYNC
	 */
	@Test
	public void testSYNC(){
		// init client
		DemoService demoService = (DemoService) new XxlRpcReferenceBean(NetEnum.JETTY, Serializer.SerializeEnum.HESSIAN.getSerializer(), CallType.SYNC,
				DemoService.class, null, 500, "127.0.0.1:2181", null, null).getObject();

		// test
        UserDTO userDTO = demoService.sayHi("[SYNC]jack");
		System.out.println(userDTO);


		// test mult
		/*int count = 100;
		long start = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			UserDTO userDTO = demoService.sayHi("[SYNC]jack"+i );
			System.out.println(i + "##" + userDTO.toString());
		}
		long end = System.currentTimeMillis();
    	System.out.println("run count:"+ count +", cost:" + (end - start));*/

	}


	/**
	 * CallType.FUTURE
	 */
	@Test
	public void testFUTURE() throws ExecutionException, InterruptedException {
		// client test
		DemoService demoService = (DemoService) new XxlRpcReferenceBean(NetEnum.JETTY, Serializer.SerializeEnum.HESSIAN.getSerializer(), CallType.FUTURE,
				DemoService.class, null, 500, "127.0.0.1:2181", null, null).getObject();

		// test
		demoService.sayHi("[FUTURE]jack" );
        Future<UserDTO> userDTOFuture = XxlRpcInvokeFuture.getFuture(UserDTO.class);
		UserDTO userDTO = userDTOFuture.get();

		System.out.println(userDTO.toString());

	}


	/**
	 * CallType.CALLBACK
	 */
	@Test
	public void testCALLBACK() throws ExecutionException, InterruptedException {
		// client test
		DemoService demoService = (DemoService) new XxlRpcReferenceBean(NetEnum.JETTY, Serializer.SerializeEnum.HESSIAN.getSerializer(), CallType.CALLBACK,
				DemoService.class, null, 500, "127.0.0.1:2181", null, null).getObject();


        // test
        XxlRpcInvokeCallback.setCallback(new XxlRpcInvokeCallback<UserDTO>() {
            @Override
            public void onSuccess(UserDTO result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable exception) {
                exception.printStackTrace();
            }
        });

        demoService.sayHi("[CALLBACK]jack");


		TimeUnit.SECONDS.sleep(3);
	}


	/**
	 * CallType.ONEWAY
	 */
	@Test
	public void testONEWAY() throws ExecutionException, InterruptedException {
		// client test
		DemoService demoService = (DemoService) new XxlRpcReferenceBean(NetEnum.JETTY, Serializer.SerializeEnum.HESSIAN.getSerializer(), CallType.ONEWAY,
				DemoService.class, null, 500, "127.0.0.1:2181", null, null).getObject();

		// test
        demoService.sayHi("[ONEWAY]jack");

		TimeUnit.SECONDS.sleep(3);
	}


}
