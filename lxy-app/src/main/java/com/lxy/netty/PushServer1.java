package com.lxy.netty;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
//@Component 暂时不使用
public class PushServer1 implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Before------------"+bean.getClass());
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("After------------");
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

	
}
