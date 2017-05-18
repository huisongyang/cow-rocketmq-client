package com.cow.rocketmq.client.core.send;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.cow.rocketmq.client.codec.CodecFactory;
import com.cow.rocketmq.client.codec.Encoder;
import com.cow.rocketmq.client.codec.MarshallingCodecFactory;
import com.cow.rocketmq.client.codec.json.JSONCodecFactory;

/**
 * 
 * @author huisong
 *
 */
public class DefaultMQProducerTemplate extends DefaultMQProducer implements
		InitializingBean {
	private String topic;
	private String serialize = "BINARY";
	private CodecFactory factory;

	public CodecFactory getFactory() {
		return this.factory;
	}

	public void setFactory(CodecFactory factory) {
		this.factory = factory;
	}

	public String getSerialize() {
		return this.serialize;
	}

	public void setSerialize(String serialize) {
		this.serialize = serialize;
	}

	private static final Logger LOG = Logger
			.getLogger(DefaultMQProducerTemplate.class);
	public Encoder encoder;

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void start() throws MQClientException{
		doStart();
		super.start();
		LOG.info("DefaultMQProducerTemplate started.");
	}

	public void doStart() {
		initFactory();
		this.encoder = this.factory.createEncoder();
	}

	private void initFactory() {
		if (this.serialize.equals("JSON")) {
			this.factory = new JSONCodecFactory();
		} else if (this.serialize.equals("BINARY")) {
			this.factory = new MarshallingCodecFactory();
		}
	}

	public SendResult send(String serverCode, Object msg) {
		if (msg == null) {
			throw new NullPointerException("msg must not be null.");
		}
		if ((serverCode == null) || ("".equals(serverCode))) {
			throw new IllegalArgumentException("serverCode must not be null");
		}
		Message message = new Message();
		message.setTopic(getTopic());
		message.setTags(serverCode);
		String key = java.util.UUID.randomUUID().toString();
		message.setKeys(key);
		ByteBuf buf = Unpooled.buffer();
		try {
			this.encoder.encoder(msg, buf, null);
			message.setBody(buf.array());
			return super.send(message);
		} catch (IOException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} catch (MQClientException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} catch (RemotingException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} catch (MQBrokerException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} finally {
			buf.release();
		}
		return null;
	}

	public void stop() {
		shutdown();
	}

	public void afterPropertiesSet() throws Exception {
		start();
	}
}
