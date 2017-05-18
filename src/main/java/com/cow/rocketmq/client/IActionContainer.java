package com.cow.rocketmq.client;

import java.util.List;

import com.cow.rocketmq.client.suport.ActionConfig;

public interface IActionContainer {
	
	void init();

	List<Action> getActions();
	
	ActionConfig getActionCfg(String actionCode);
	
	Action getAction(String actionCode);
	
	AbstractConsumerContainer getConsumerContainer();
}
