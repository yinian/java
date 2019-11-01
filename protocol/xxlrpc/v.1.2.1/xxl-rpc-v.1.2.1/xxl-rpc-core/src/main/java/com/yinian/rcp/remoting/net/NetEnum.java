package com.yinian.rcp.remoting.net;


import com.yinian.rcp.remoting.net.impl.jetty.client.JettyClient;
import com.yinian.rcp.remoting.net.impl.jetty.server.JettyServer;
import com.yinian.rcp.remoting.net.impl.mina.client.MinaClient;
import com.yinian.rcp.remoting.net.impl.mina.server.MinaServer;
import com.yinian.rcp.remoting.net.impl.netty.client.NettyClient;
import com.yinian.rcp.remoting.net.impl.netty.server.NettyServer;

/**
 * remoting net
 * @description 这个是很好的枚举类，将Netty,MINA,Jetty的客户端和服务端放在一起，
 * 而且各自类的服务端和客户端分别继承Server和Client抽象类
 *
 * @author xuxueli 2015-11-24 22:09:57
 */
public enum NetEnum {

	NETTY(NettyServer.class, NettyClient.class),

	MINA(MinaServer.class, MinaClient.class),

	JETTY(JettyServer.class, JettyClient.class),

	JETTY_HTTP2(null, null);	// TODO
	public final Class<? extends Server> serverClass;
	public final Class<? extends Client> clientClass;

	NetEnum(Class<? extends Server> serverClass, Class<? extends Client> clientClass){
		this.serverClass = serverClass;
		this.clientClass = clientClass;
	}

	public static NetEnum autoMatch(String name, NetEnum defaultEnum) {
		for (NetEnum item : NetEnum.values()){
			if (item.name().equals(name)){
				return item;
			}
		}

		return defaultEnum;
	}









	}