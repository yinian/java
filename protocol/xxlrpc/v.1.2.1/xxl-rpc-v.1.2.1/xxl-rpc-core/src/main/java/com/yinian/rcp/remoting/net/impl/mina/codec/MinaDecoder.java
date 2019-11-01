package com.yinian.rcp.remoting.net.impl.mina.codec;

import com.yinian.rcp.serialize.Serializer;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;


public class MinaDecoder extends CumulativeProtocolDecoder {

	private Class<?> genericClass;
    private Serializer serializer;

    public MinaDecoder(Class<?> genericClass, Serializer serializer) {
        this.genericClass = genericClass;
        this.serializer = serializer;
    }
    
    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {

    	if (in.remaining() < 4) {
            return false;
        }
    	in.mark();	// mark 2 reset, reset call rollback to mark place
    	
        int dataLength = in.getInt();	// data length
        if (dataLength < in.remaining()) {
            return false;
        }
        if (dataLength > in.remaining()) {
            in.reset();
        }
        
        byte[] datas = new byte[dataLength];	// data
        in.get(datas, 0, dataLength);
        
        Object obj = serializer.deserialize(datas, genericClass);
        out.write(obj);
    	return true;
    }

}
