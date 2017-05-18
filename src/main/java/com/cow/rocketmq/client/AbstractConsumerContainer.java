package com.cow.rocketmq.client;

import java.util.concurrent.atomic.AtomicBoolean;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public abstract class AbstractConsumerContainer extends DefaultMQPushConsumer
		implements InitializingBean, BeanNameAware,
		ApplicationListener<ContextRefreshedEvent> {
	private String beanName;
	private AtomicBoolean isInited = new AtomicBoolean(false);
	protected static final Logger LOG = Logger
			.getLogger(AbstractConsumerContainer.class);

	public void start() {
		try {
			doStart();
			super.start();
		} catch (MQClientException e) {
			throw new RuntimeException(e);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	public String getBeanName() {
		return this.beanName;
	}

	public void stop() {
		shutdown();
	}

	public void afterPropertiesSet() {
		initialize();
	}

	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (!isInited.compareAndSet(false, true)) {
			return;
		}
		start();
	}

	public abstract void doStart() throws MQClientException;

	public abstract void initialize();

	public void setBeanName(String name) {
		this.beanName = name;
	}
}
