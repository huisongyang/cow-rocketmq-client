package com.cow.rocketmq.client;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.cow.rocketmq.client.annotation.Service;

@Service(serviceCode="PDA_TO_FOSS_LOGIN" , requestClass = String.class)
public class OneAction implements Action{
	
	int count = 0 ;
	
	public void action(Object paramObject) throws MQClientException {
		
		System.out.println((count++) +":::" + ((String)paramObject));
		
	}
}
