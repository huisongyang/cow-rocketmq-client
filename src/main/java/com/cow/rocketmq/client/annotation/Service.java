package com.cow.rocketmq.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.codehaus.jackson.node.NullNode;


/**
 * 
 * @author huisong
 * @date 2016-09-30
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

	/**
	 * 服务编码
	 * @return
	 */
	String serviceCode();
		
	/**
	 * 请求类
	 * @return
	 */
	Class<?> requestClass() default NullNode.class;
//	
//	/**
//	 * 响应消息编码类
//	 * @return
//	 */
//	Class<?> responseClass();
}
