package com.cow.rocketmq.client.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cow.rocketmq.client.AbstractConsumerContainer;
import com.cow.rocketmq.client.Action;
import com.cow.rocketmq.client.ApplicationUtil;
import com.cow.rocketmq.client.IActionContainer;
import com.cow.rocketmq.client.annotation.Service;
import com.cow.rocketmq.client.suport.ActionConfig;

/**
 * 
 * @author huisong
 *
 */
public class ActionContainer implements IActionContainer {

	private AbstractConsumerContainer messageConsumerContainer;
	private ConcurrentHashMap<String, ActionConfig> table = new ConcurrentHashMap<String, ActionConfig>();

	public ActionContainer() {

	}

	public ActionContainer(AbstractConsumerContainer c) {
		this.messageConsumerContainer = c;
	}

	public void init() {

		Map<String, Object> ats = getObjs();
		for (Object obj : ats.values()) {
			Action process = (Action) obj;
			Service info = (Service) process.getClass().getAnnotation(
					Service.class);
			ActionConfig cfg = new ActionConfig();
			cfg.setMsgCode(info.serviceCode());
			cfg.setReqCls(info.requestClass());
			cfg.setAction(process);
			if (table.containsKey(info.serviceCode())) {
				throw new RuntimeException("serverCode配置重复");
			}
			table.put(info.serviceCode(), cfg);
		}
	}

	private Map<String, Object> getObjs() {
		Map<String, Object> actions = ApplicationUtil.getApplicationContext()
				.getBeansWithAnnotation(Service.class);
		validate(actions);
		return actions;
	}

	private void validate(Map<String, Object> obj) {
		Iterator<Map.Entry<String, Object>> iterator = obj.entrySet()
				.iterator();

		Map.Entry<String, Object> next = null;
		while (iterator.hasNext() && (next = iterator.next()) != null) {
			Object o = next.getValue();
			if (!(o instanceof Action)) {
				throw new RuntimeException(obj + "必须实现Action接口");
			}
		}
	}

	public List<Action> getActions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Action getAction(String actionCode) {
		if(table.containsKey(actionCode)){
			return table.get(actionCode).getAction();
		}
		return null;
	}

	public AbstractConsumerContainer getConsumerContainer() {
		return this.messageConsumerContainer;
	}

	public ActionConfig getActionCfg(String actionCode) {
		if(table.containsKey(actionCode)){
			return table.get(actionCode);
		}
		return null;
	}

}
