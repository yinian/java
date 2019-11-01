package com.yinian.rcp.remoting.net.impl.netty.client;

import com.yinian.rcp.remoting.net.params.XxlRpcFutureResponse;
import com.yinian.rcp.remoting.net.params.XxlRpcFutureResponseFactory;
import com.yinian.rcp.remoting.net.params.XxlRpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * rpc netty client handler
 *
 * @author xuxueli 2015-10-31 18:00:27
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<XxlRpcResponse> {
	private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);


	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	logger.error(">>>>>>>>>>> xxl-rpc netty client caught exception", cause);
        ctx.close();
    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, XxlRpcResponse xxlRpcResponse) throws Exception {
		XxlRpcFutureResponse futureResponse = XxlRpcFutureResponseFactory.getInvokerFuture(xxlRpcResponse.getRequestId());
		if (futureResponse != null) {
			futureResponse.setResponse(xxlRpcResponse);
		}

	}

}
