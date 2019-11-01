package com.github.yinian.xxl.rpc.netcom.netty.server;

import com.github.yinian.xxl.rpc.netcom.netty.codec.NettyRequest;
import com.github.yinian.xxl.rpc.netcom.netty.codec.NettyResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * rpc netty server handler
 * @author yinian 2015-10-29 20:07:37
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<NettyRequest> {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
    
    /** rpc handler services */
    private final Map<String, Object> serviceMap;
    public NettyServerHandler(Map<String, Object> serviceMap) {
        this.serviceMap = serviceMap;
    }

    @Override
    public void channelRead0(final ChannelHandlerContext ctx, NettyRequest request) throws Exception {
        NettyResponse response = new NettyResponse();
        response.setRequestId(request.getRequestId());
        try {
            Object result = handle(request);
            response.setResult(result);
        } catch (Throwable t) {
            response.setError(t);
        }
        ctx.writeAndFlush(response);
    }

    /**
     * netty server handler request
     */
    private Object handle(NettyRequest request) throws Throwable {
      String className = request.getClassName();
      Object serviceBean = serviceMap.get(className);
      Class<?> serviceClass = serviceBean.getClass();
      String methodName = request.getMethodName();
      Class<?>[] parameterTypes = request.getParameterTypes();
      Object[] parameters = request.getParameters();

      FastClass serviceFastClass = FastClass.create(serviceClass);
      FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
      return serviceFastMethod.invoke(serviceBean, parameters);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.error(">>>>>>>>>>> xxl-rpc provider netty server caught exception", cause);
        ctx.close();
    }
}
