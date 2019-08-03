package com.viagra.chapter12.protocol;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
/**
 * @Auther: viagra
 * @Date: 2019/8/3 10:59
 * @Description:
 */
public class LoginAuthRespHandler extends ChannelHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if(message!=null&&message.getHeader()!=null){
            if(message.getHeader().getType()==MessageType.LOIGN_REQ){
                System.out.println("server recevied login auth msg from client :" + message);
                ctx.writeAndFlush(buildLoginResponse());
            }else{
                ctx.fireChannelRead(msg);
            }
        }
    }

    private NettyMessage buildLoginResponse() {
        NettyMessage message = new NettyMessage();
        NettyMessageHeader header = new NettyMessageHeader();
        header.setType(MessageType.LOGIN_RESP_SUCCESS);
        message.setHeader(header);
        return message;
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}

