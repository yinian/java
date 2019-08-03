package com.viagra.chapter07.updatedMessagePack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: viagra
 * @Date: 2019/7/31 20:50
 * @Description:
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {


    // sendNumber为写入发送缓冲区的对象数量
    private int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    /**
     * 构建长度为userNum的User对象数组
     * @param userNum
     * @return
     */
    private User[] getUserArray(int userNum) {
        User[] users = new User[userNum];
        User user = null;
        for(int i = 0; i < userNum; i++) {
            user = new User();
            user.setName("ABCDEFG --->" + i);
            user.setAge(i);
            users[i] = user;
        }
        return users;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        User[] users = getUserArray(sendNumber);
        for (User user : users) {
            ctx.writeAndFlush(user);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client receive the msgpack message : " + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
