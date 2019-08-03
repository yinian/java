package com.viagra.chapter08.protobufDemo;

import com.viagra.chapter08.protobuf.SubscribeReqProto;
import com.viagra.chapter08.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerAdapter;


/**
 * @Auther: viagra
 * @Date: 2019/8/1 21:59
 * @Description:
 */
public class SubRespProHandler extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
    private SubscribeRespProto.SubcribeResp createSubcribeResp(int subReqID) {
        SubscribeRespProto.SubcribeResp.Builder builder = SubscribeRespProto.SubcribeResp.newBuilder();
        builder.setSubReqID(subReqID);
        builder.setRespCode(0);
        builder.setDesc("Order success.");
        return builder.build();
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubcribeReq req = (SubscribeReqProto.SubcribeReq) msg;
        System.out.println("接收到客户端请求：" + req.getSubReqID() + ",userName:" + req.getUserName() + ",productName:" + req.getProductName());
        SubscribeRespProto.SubcribeResp resp = createSubcribeResp(req.getSubReqID());
        ctx.writeAndFlush(resp);
    }
}