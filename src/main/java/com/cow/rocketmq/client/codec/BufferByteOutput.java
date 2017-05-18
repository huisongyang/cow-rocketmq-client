package com.cow.rocketmq.client.codec;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import org.jboss.marshalling.ByteOutput;

public class BufferByteOutput implements ByteOutput {

	private final ByteBuf buffer;
	
	public BufferByteOutput(ByteBuf buf){
		this.buffer = buf;
	}
	
	public void close() throws IOException {

	}

	public void flush() throws IOException {

	}

	public void write(int arg0) throws IOException {
		 this.buffer.writeByte(arg0);
	}

	public void write(byte[] arg0) throws IOException {
		this.buffer.writeBytes(arg0);
	}

	public void write(byte[] arg0, int arg1, int arg2) throws IOException {
		this.buffer.writeBytes(arg0, arg1, arg2);
	}

}
