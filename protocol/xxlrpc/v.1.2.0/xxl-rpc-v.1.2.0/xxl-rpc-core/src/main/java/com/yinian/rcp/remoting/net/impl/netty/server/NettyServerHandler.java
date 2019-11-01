package com.yinian.rcp.remoting.net.impl.netty.server;

import com.yinian.rcp.remoting.net.params.XxlRpcRequest;
import com.yinian.rcp.remoting.net.params.XxlRpcResponse;
import com.yinian.rcp.remoting.provider.XxlRpcProviderFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * netty server handler
 *
 * @author yinian 2015-10-29 20:07:37
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<XxlRpcRequest> {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);


    private XxlRpcProviderFactory xxlRpcProviderFactory;
    public NettyServerHandler(XxlRpcProviderFactory xxlRpcProviderFactory) {
        this.xxlRpcProviderFactory = xxlRpcProviderFactory;
    }


    @Override
    public void channelRead0(final ChannelHandlerContext ctx, XxlRpcRequest xxlRpcRequest) throws Exception {

        // invoke + response
        XxlRpcResponse xxlRpcResponse = xxlRpcProviderFactory.invokeService(xxlRpcRequest);
    	
        ctx.writeAndFlush(xxlRpcResponse);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.error(">>>>>>>>>>> xxl-rpc provider netty server caught exception", cause);
        ctx.close();
    }
}
