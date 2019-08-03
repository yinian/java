package com.viagra.chapter12.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;
import java.util.Map;

/**
 * @Auther: viagra
 * @Date: 2019/8/3 09:49
 * @Description:
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    private NettyMarshallingEncoder encoder;
    public NettyMessageEncoder(){
        encoder = MarshallingCodeCFactory.buildMarshallingEncoder();
    }
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
                          NettyMessage nettyMessage,
                          List<Object> list) throws Exception {

        if(nettyMessage == null || nettyMessage.getHeader() == null){
            throw new Exception("The encode message is null");
        }

        ByteBuf sendBuf = Unpooled.buffer();
        sendBuf.writeInt(nettyMessage.getHeader().getCrcCode());
        sendBuf.writeInt(nettyMessage.getHeader().getLength());
        sendBuf.writeLong(nettyMessage.getHeader().getSessionId());
        sendBuf.writeByte(nettyMessage.getHeader().getType());
        sendBuf.writeByte(nettyMessage.getHeader().getPriority());
        sendBuf.writeInt(nettyMessage.getHeader().getAttachment().size());


        String key = null;
        byte[] keyArray = null;
        Object value = null;

        for (Map.Entry<String,Object> param : nettyMessage.getHeader().getAttachment().entrySet()){
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = param.getValue();
            encoder.encode(channelHandlerContext, value, sendBuf);
        }

        key = null;
        keyArray = null;
        value = null;
        if(nettyMessage.getBody() != null){
            encoder.encode(channelHandlerContext, nettyMessage.getBody(), sendBuf);
        }

        // 在第4个字节处写入 Buffer 的长度
        int readableBytes = sendBuf.readableBytes();
        sendBuf.setInt(4,readableBytes);
        // 把 Message 添加到 List 传递到下一个 Handler
        list.add(sendBuf);


    }
}
