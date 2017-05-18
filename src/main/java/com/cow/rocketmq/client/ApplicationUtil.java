package com.cow.rocketmq.client;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationUtil
  implements ApplicationContextAware
{
  private static ApplicationContext applicationContext;
  
  public void setApplicationContext(ApplicationContext applicationContext)
    throws BeansException
  {
	  ApplicationUtil.applicationContext = applicationContext;
  }
  
  public static ApplicationContext getApplicationContext()
  {
    return applicationContext;
  }
}
