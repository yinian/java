package com.viagra.chapter08.protobufDemo;

import com.viagra.chapter08.protobuf.SubscribeReqProto;
import com.viagra.chapter08.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: viagra
 * @Date: 2019/8/1 22:00
 * @Description:
 */
public class SubReqProHandler extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SubscribeReqProto.SubcribeReq req = null;
        for (int i=0;i<10;i++) {
            req = createSubcribeReq(i);
            ctx.write(req);
        }
        ctx.flush();
    }
    private SubscribeReqProto.SubcribeReq createSubcribeReq(int subSeqID) {
        SubscribeReqProto.SubcribeReq.Builder builder = SubscribeReqProto.SubcribeReq.newBuilder();
        builder.setSubReqID(subSeqID);
        builder.setUserName("j.tommy");
        builder.setProductName("netty权威指南");
        List<String> addressList = new ArrayList<String>();
        addressList.add("北京市");
        addressList.add("西安市");
        addressList.add("深圳市");
        return builder.build();
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeRespProto.SubcribeResp resp = (SubscribeRespProto.SubcribeResp) msg;
        System.out.println("接收到服务端响应：" + resp.getSubReqID() + ",responseCode:" + resp.getRespCode() + ",desc:" + resp.getDesc());
    }
}
