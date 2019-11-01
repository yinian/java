package com.yinian.rcp.serialize.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinian.rcp.serialize.Serializer;
import com.yinian.rcp.util.XxlRpcException;

import java.io.IOException;

/**
 * Jackson工具类
 * 1、obj need private and set/get；2、do not support inner class；
 * @author xuxueli 2015-9-25 18:02:56
 */
public class JacksonSerializer extends Serializer {
    private final static ObjectMapper objectMapper = new ObjectMapper();
    
    /** bean、array、List、Map --> json 
     * @param <T>*/
    @Override
	public <T> byte[] serialize(T obj) {
		try {
			return objectMapper.writeValueAsBytes(obj);
		} catch (JsonProcessingException e) {
			throw new XxlRpcException(e);
		}
	}

    /** string --> bean、Map、List(array) */
    @Override
	public <T> Object deserialize(byte[] bytes, Class<T> clazz)  {
		try {
			return objectMapper.readValue(bytes, clazz);
		} catch (JsonParseException e) {
			throw new XxlRpcException(e);
		} catch (JsonMappingException e) {
			throw new XxlRpcException(e);
		} catch (IOException e) {
			throw new XxlRpcException(e);
		}
	}

}
