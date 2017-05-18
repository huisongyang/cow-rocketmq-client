package com.cow.rocketmq.client.codec;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * 
 * @author YangHuisong
 *
 */
public class MarshallingCodecFactory implements CodecFactory{
	
	static MarshallerFactory factory =  Marshalling.getProvidedMarshallerFactory("serial");

	public MarshallingEncoder createEncoder(){
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		return new MarshallingEncoder(factory, configuration);
	}
	
	public MarshallingDecoder createDecoder(){
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		return new MarshallingDecoder(factory, configuration);
	}
}
