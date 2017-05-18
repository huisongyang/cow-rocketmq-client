package com.cow.rocketmq.client.codec;

import io.netty.buffer.ByteBuf;

import java.util.List;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

/**
 * 
 * @author YangHuisong
 *
 */
public class MarshallingDecoder implements Decoder {

	MarshallerFactory factory;
	final MarshallingConfiguration configuration ;

	public MarshallingDecoder(MarshallerFactory factory , MarshallingConfiguration configuration) {
		this.factory = factory;
		this.configuration = configuration;
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

	public void decoder(ByteBuf buf, List<Object> out , CodecContext ctx) throws Exception {
		Unmarshaller unmarshaller = factory.createUnmarshaller(configuration);
		try {
			unmarshaller.start(new BufferByteInput(buf));
			Object obj = unmarshaller.readObject();
			unmarshaller.finish();
			out.add(obj);
		} catch (Exception e) {
			throw e;
		} finally {
			unmarshaller.close();
		}
	}

}
