package com.cow.rocketmq.client.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.cow.rocketmq.client.AbstractConsumerContainer;
import com.cow.rocketmq.client.Action;
import com.cow.rocketmq.client.IActionContainer;
import com.cow.rocketmq.client.codec.CodecContext;
import com.cow.rocketmq.client.codec.CodecFactory;
import com.cow.rocketmq.client.impl.ActionContainer;

/**
 * 
 * @author YangHuisong
 * 
 */
public class RocketmqMessageListenerContainer extends
		AbstractMessageListenerContainer {
	
	static {
		System.setProperty("rocketmq.client.log4j.resource.fileName", "dpap_log4j_rocketmq_client.xml");
		System.setProperty("rocketmq.client.logback.resource.fileName", "dpap_logback_rocketmq_client.xml");
	}

	IActionContainer actionContainer;

	@Override
	public void doStart() throws MQClientException {
		init();
	}

	private void init() throws MQClientException {
		actionContainer = new ActionContainer(this);
		actionContainer.init();

		this.subscribe(this.getTopic(), getSelector());
		this.registerMessageListener(new SimpMessageListenerConcurrently());
		setDecoder(getFactory().createDecoder());
		AbstractConsumerContainer.LOG.info("RocketMQ监听容器启动成功");
	}

	class SimpMessageListenerConcurrently implements
			MessageListenerConcurrently {

		boolean stoped = false;

		public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
				ConsumeConcurrentlyContext context) {
			Iterator<MessageExt> i = msgs.iterator();
			MessageExt next;
			while (i.hasNext() && (next = i.next()) != null) {

				LOG.info("receive RMQ message:" + next.getTags());

				String serverCode = next.getTags();
				
				if(serverCode == null){
					LOG.info("serverCode为空");
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				}
				
				Action action = actionContainer.getAction(serverCode);
				if (action == null) {
					LOG.error("找不到服务编码为:" + serverCode + "的业务处理器");
					return ConsumeConcurrentlyStatus.RECONSUME_LATER;
				}

				byte[] bytes = next.getBody();
				ByteBuf buf = Unpooled.copiedBuffer(bytes);
				List<Object> out = new ArrayList<Object>(0);
				try {
					CodecContext ctx = new CodecContext();
					ctx.putProperty(CodecFactory.REQ_CLASS, actionContainer
							.getActionCfg(serverCode).getReqCls());
					getDecoder().decoder(buf, out, ctx);
					action.action(out.get(0));
				} catch (Exception e) {
					LOG.error("消息处理异常:msgkey[" + next.getKeys() + "]"
							+ e.getMessage());
					e.printStackTrace();
				} finally {
					buf.release();
				}
			}
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
	}
}
