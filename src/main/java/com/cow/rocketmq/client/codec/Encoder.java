package com.cow.rocketmq.client.codec;

import java.io.IOException;

import io.netty.buffer.ByteBuf;

/**
 * 
 * @author YangHuisong
 *
 */
public interface Encoder {

	public void encoder(Object msg , ByteBuf buf , CodecContext ctx) throws IOException;
}
