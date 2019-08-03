package com.viagra.chapter07.updatedMessagePack;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @Auther: viagra
 * @Date: 2019/7/30 22:41
 * @Description:
 */
public class MsgPackEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        // 创建MessagePack对象
        MessagePack msgpack = new MessagePack();
        // 将对象编码为MessagePack格式的字节数组
        byte[] raw = msgpack.write(msg);
        // 将字节数组写入到ByteBuf中
        out.writeBytes(raw);
    }

}