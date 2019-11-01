package com.yinian.rcp.remoting.net.impl.netty.client;

import com.yinian.rcp.remoting.net.impl.netty.codec.NettyDecoder;
import com.yinian.rcp.remoting.net.impl.netty.codec.NettyEncoder;
import com.yinian.rcp.remoting.net.params.XxlRpcRequest;
import com.yinian.rcp.remoting.net.params.XxlRpcResponse;
import com.yinian.rcp.remoting.net.pool.ClientPooled;
import com.yinian.rcp.serialize.Serializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * netty pooled client
 *
 * @author xuxueli
 */
public class NettyPooledClient extends ClientPooled {


	private Channel channel;


	@Override
	public void init(String host, int port, final Serializer serializer) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
    	Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline()
                        .addLast(new NettyEncoder(XxlRpcRequest.class, serializer))
                        .addLast(new NettyDecoder(XxlRpcResponse.class, serializer))
                        .addLast(new NettyClientHandler());
                }
            })
            .option(ChannelOption.TCP_NODELAY, true)
			.option(ChannelOption.SO_REUSEADDR, true)
            .option(ChannelOption.SO_KEEPALIVE, true);
        this.channel = bootstrap.connect(host, port).sync().channel();
	}


	@Override
	public boolean isValidate() {
		if (this.channel != null) {
			return this.channel.isActive();
		}
		return false;
	}


	@Override
	public void close() {
		if (this.channel != null) {
			if (this.channel.isOpen()) {
				this.channel.close();
			}
		}
		logger.debug(">>>>>>>>>>>> xxl-rpc netty client close.");
	}


	@Override
	public void send(XxlRpcRequest xxlRpcRequest) throws Exception {
    	this.channel.writeAndFlush(xxlRpcRequest).sync();
    }
}
