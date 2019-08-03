package com.viagra.chapter12.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: viagra
 * @Date: 2019/8/3 09:31
 * @Description:
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    private NettyMarshallingDecoder decoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset,
                               int lengthFieldLength,int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
        decoder = MarshallingCodeCFactory.buildMarshallingDecoder();
    }

    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception{

        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null){
            return null;
        }

        NettyMessage message = new NettyMessage();
        NettyMessageHeader header = new NettyMessageHeader();
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionId(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());

        int size = frame.readInt();
        if (size>0){
            Map<String,Object> attach =
                    new HashMap<String, Object>();
            int keySize = 0;
            byte[] keyArray = null;
            String key = null;
            for (int i=0;i<size;i++){
                keySize = frame.readInt();
                keyArray = new byte[keySize];
                in.readBytes(keyArray);
                key = new String(keyArray,"UTF-8");
                attach.put(key, decoder.decode(ctx,frame));
            }

            key = null;
            keyArray = null;
            header.setAttachment(attach);
        }

        if (frame.readableBytes() > 0){
            message.setBody(decoder.decode(ctx,frame));
        }

        message.setHeader(header);
        return message;

    }
}
