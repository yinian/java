package com.viagra.chapter12.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: viagra
 * @Date: 2019/8/3 09:30
 * @Description:
 */

public class NettyMessageHeader {
    private int crcCode = 0xabef0101;

    private int length;

    private long sessionId;

    private byte type;

    private byte priority;

    private Map<String, Object> attachment = new HashMap<String, Object>();

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    public String toString(){
        return "Header [crcCode=" + crcCode + ", length=" + length + ", sessionID=" + sessionId
                + ", type=" + type + ", priority=" + priority + ", attachment=" + attachment + "]";
    }
}