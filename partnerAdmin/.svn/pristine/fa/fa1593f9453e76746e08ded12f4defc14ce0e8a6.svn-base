package com.base.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.base.annotation.Auth;

/**
 * 
 * @desc 消费者
 * @author xulihong  
 * @date 2016年6月13日 上午11:53:04
 */

public class MessageConsumer implements MessageListener {
	
	private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
	@Resource
	private AmqpTemplate amqpTemplate;
	@Override
	public void onMessage(Message message) {
		String message11 = new String(message.getBody());
		logger.info("receive message:{}",message11);
		Thread current = Thread.currentThread();
		System.out.print("当前线程为:"+current.getName()+",得到的message为：");
		System.out.println(message11+",接受时间："+new Date());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}


}