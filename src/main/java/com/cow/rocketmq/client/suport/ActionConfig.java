package com.cow.rocketmq.client.suport;

import com.cow.rocketmq.client.Action;

public class ActionConfig
{
  private String msgCode;
  private Action action;
  private String msgFormat;
  private Class<?> reqCls = null;
  private Class<?> repCls;
  
  public String getMsgCode()
  {
    return this.msgCode;
  }
  
  public void setMsgCode(String msgCode)
  {
    this.msgCode = msgCode;
  }
  
  public Action getAction()
  {
    return this.action;
  }
  
  public void setAction(Action action)
  {
    this.action = action;
  }
  
  public String getMsgFormat()
  {
    return this.msgFormat;
  }
  
  public void setMsgFormat(String msgFormat)
  {
    this.msgFormat = msgFormat;
  }
  
  public Class<?> getReqCls()
  {
    return this.reqCls;
  }
  
  public void setReqCls(Class<?> reqCls)
  {
    this.reqCls = reqCls;
  }
  
  public Class<?> getRepCls()
  {
    return this.repCls;
  }
  
  public void setRepCls(Class<?> repCls)
  {
    this.repCls = repCls;
  }
}
