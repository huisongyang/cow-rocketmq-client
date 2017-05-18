package com.cow.rocketmq.client;

import com.alibaba.rocketmq.client.exception.MQClientException;

public abstract interface Action
{
  public abstract void action(Object paramObject)
    throws MQClientException;
}
