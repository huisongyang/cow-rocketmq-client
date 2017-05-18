package com.cow.rocketmq.client.codec;

import java.util.HashMap;
import java.util.Map;

public class CodecContext
{
  private Map<String, Object> props = new HashMap<String, Object>();
  
  public void putProperty(String name, Object value)
  {
    this.props.put(name, value);
  }
  
  public Object getProperty(String name)
  {
    return this.props.get(name);
  }
}
