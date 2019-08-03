package com.viagra.chapter12.protocol;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
/**
 * @Auther: viagra
 * @Date: 2019/8/3 09:33
 * @Description:
 */
public class MarshallingCodeCFactory {

    public static NettyMarshallingEncoder buildMarshallingEncoder() {
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration config = new MarshallingConfiguration();
        config.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(factory, config);
        NettyMarshallingEncoder encoder = new NettyMarshallingEncoder(provider);
        return encoder;
    }

    public static NettyMarshallingDecoder buildMarshallingDecoder() {
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration config = new MarshallingConfiguration();
        config.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(factory, config);
        NettyMarshallingDecoder decoder = new NettyMarshallingDecoder(provider, 1024<<2);
        return decoder;
    }




}
