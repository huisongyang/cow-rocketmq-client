package com.cow.rocketmq.client.codec.json;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.cow.rocketmq.client.codec.CodecContext;
import com.cow.rocketmq.client.codec.Encoder;

public class JSONEncoder implements Encoder{

	private static ObjectMapper mapper = new ObjectMapper();
	
	public void encoder(Object msg, ByteBuf buf, CodecContext ctx) throws IOException {
		String json = mapper.writeValueAsString(msg);
		byte[] bytes = json.getBytes("UTF-8");
		buf.writeBytes(bytes);
	}

}
