package com.viagra.chapter12.protocol;

/**
 * @Auther: viagra
 * @Date: 2019/8/3 09:29
 * @Description:
 */

public final class NettyMessage {

    private NettyMessageHeader header;
    private Object body;

    public NettyMessageHeader getHeader() {
        return header;
    }

    public void setHeader(NettyMessageHeader header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString(){
        return "NettyMessage [header="+header+"]";
    }
}
