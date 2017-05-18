package com.cow.rocketmq.client.codec;

public interface CodecFactory {
	
	public static final String REQ_CLASS = "REQ_CLASS";
	
	public static final String RES_CLASS = "RES_CLASS";

	public Encoder createEncoder();
	
	public Decoder createDecoder();
}
