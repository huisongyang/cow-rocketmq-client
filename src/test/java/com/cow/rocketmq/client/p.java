package com.cow.rocketmq.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cow.rocketmq.client.core.send.DefaultMQProducerTemplate;

public class p {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext context =  new ClassPathXmlApplicationContext("classpath:spring-p.xml");
		DefaultMQProducerTemplate template = (DefaultMQProducerTemplate) context.getBean("template");
		for(;;){
			template.send("PDA_TO_FOSS_LOGIN", "test message.");
			System.out.println("send succeed.");
		}
	}

}
