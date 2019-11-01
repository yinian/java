package com.github.yinian.xxl.rpc.netcom.netty.client;

import com.github.yinian.xxl.rpc.netcom.netty.codec.NettyResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * rpc netty client
 * @author yinian 2015-10-31 18:00:27
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<NettyResponse> {
	private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, NettyResponse response) throws Exception {
		NettyClientCallbackFuture future = NettyClientCallbackFuture.futurePool.get(response.getRequestId());
		future.setResponse(response);
		NettyClientCallbackFuture.futurePool.put(response.getRequestId(), future);
    }
	
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	logger.error(">>>>>>>>>>> xxl-rpc netty client caught exception", cause);
        ctx.close();
    }

}
