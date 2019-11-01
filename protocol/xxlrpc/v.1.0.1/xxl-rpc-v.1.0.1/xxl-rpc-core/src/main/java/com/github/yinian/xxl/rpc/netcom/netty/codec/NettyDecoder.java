package com.github.yinian.xxl.rpc.netcom.netty.codec;

import com.github.yinian.xxl.rpc.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;


/**
 * decoder
 * @author yinian 2015-10-29 19:02:36
 * @description 开始netty源码的阅读呗
 */
public class NettyDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;
    private Serializer serializer;

    public NettyDecoder(Class<?> genericClass, Serializer serializer) {
        this.genericClass = genericClass;
        this.serializer = serializer;
    }

    @Override
    public final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < 4){
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0){
            ctx.close();
        }

        if (in.readableBytes() < dataLength){
            in.resetReaderIndex();
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);

        Object obj = serializer.deserialize(data, genericClass);
        out.add(obj);
    }
}
