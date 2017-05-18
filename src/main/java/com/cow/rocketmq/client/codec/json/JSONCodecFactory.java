package com.cow.rocketmq.client.codec.json;

import com.cow.rocketmq.client.codec.CodecFactory;
import com.cow.rocketmq.client.codec.Decoder;
import com.cow.rocketmq.client.codec.Encoder;


public class JSONCodecFactory implements CodecFactory{

	public Encoder createEncoder() {
		return new JSONEncoder();
	}

	public Decoder createDecoder() {
		return new JSONDecoder();
	}

}
