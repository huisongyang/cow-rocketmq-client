package com.cow.rocketmq.client.codec;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * 
 * @author YangHuisong
 *
 */
public class MarshallingEncoder implements Encoder{
	
	MarshallerFactory factory;
	final MarshallingConfiguration configuration ;
	
	public MarshallingEncoder(MarshallerFactory factory,MarshallingConfiguration config){
		this.factory = factory;
		this.configuration = config;
	}
	

	public MarshallerFactory getFactory() {
		return factory;
	}


	public void setFactory(MarshallerFactory factory) {
		this.factory = factory;
	}


	public MarshallingConfiguration getConfiguration() {
		return configuration;
	}


	public void encoder(Object msg, ByteBuf buf , CodecContext ctx) throws IOException {
		Marshaller marshaller = factory.createMarshaller(configuration);
		marshaller.start(new BufferByteOutput(buf));
		marshaller.writeObject(msg);
		marshaller.finish();
		marshaller.close();
	}
}
