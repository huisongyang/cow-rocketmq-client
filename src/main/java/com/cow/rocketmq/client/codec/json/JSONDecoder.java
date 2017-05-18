package com.cow.rocketmq.client.codec.json;

import io.netty.buffer.ByteBuf;

import java.util.List;

import org.codehaus.jackson.node.NullNode;

import com.alibaba.fastjson.JSONObject;
import com.cow.rocketmq.client.codec.CodecContext;
import com.cow.rocketmq.client.codec.CodecFactory;
import com.cow.rocketmq.client.codec.Decoder;

public class JSONDecoder implements Decoder{

	
	public JSONDecoder(){
	}
	
	public void decoder(ByteBuf buf, List<Object> out, CodecContext ctx) throws Exception {
		String json = new String(buf.array(),"UTF-8").trim();
		
		Class<?> cls = (Class<?>) ctx.getProperty(CodecFactory.REQ_CLASS);
		if(cls.getClass().getName().equals(NullNode.class.getName())){
			throw new IllegalArgumentException("注解@Service requestClass 不能为空.");
		}
		out.add(JSONObject.parseObject(json,cls));
	}

}
