package com.cow.rocketmq.client.core;

import com.cow.rocketmq.client.AbstractConsumerContainer;
import com.cow.rocketmq.client.codec.CodecFactory;
import com.cow.rocketmq.client.codec.Decoder;
import com.cow.rocketmq.client.codec.MarshallingCodecFactory;
import com.cow.rocketmq.client.codec.json.JSONCodecFactory;

public abstract class AbstractMessageListenerContainer
  extends AbstractConsumerContainer
{
  private String topic;
  private String serverCode;
  private String selector = "*";
  private String serialize = "BINARY";
  private CodecFactory factory;
  protected Decoder decoder;
  
  public CodecFactory getFactory()
  {
    return this.factory;
  }
  
  public void setFactory(CodecFactory factory)
  {
    this.factory = factory;
  }
  
  public String getSerialize()
  {
    return this.serialize;
  }
  
  public void setSerialize(String serialize)
  {
    this.serialize = serialize;
  }
  
  public String getSelector()
  {
    return this.selector;
  }
  
  public void setSelector(String selector)
  {
    this.selector = selector;
  }
  
  public Decoder getDecoder()
  {
    return this.decoder;
  }
  
  public void setDecoder(Decoder decoder)
  {
    this.decoder = decoder;
  }
  
  public String getServerCode()
  {
    return this.serverCode;
  }
  
  public void setServerCode(String serverCode)
  {
    this.serverCode = serverCode;
  }
  
  public String getTopic()
  {
    return this.topic;
  }
  
  public void setTopic(String topic)
  {
    this.topic = topic;
  }
  
  public void initialize()
  {
    if (this.serialize.equals("JSON")) {
      this.factory = new JSONCodecFactory();
    } else if (this.serialize.equals("BINARY")) {
      this.factory = new MarshallingCodecFactory();
    }
  }
}
