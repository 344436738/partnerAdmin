package com.base.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
/**
 * 
 * @desc
 * @author xulihong  
 * @date 2016年6月13日 上午11:53:59
 */
@Service("messageProducer")
public class MessageProducer {
	private Logger logger = LoggerFactory.getLogger(MessageProducer.class);

	@Resource
	private AmqpTemplate amqpTemplate;

	public void sendMessage(Object message){
	  logger.info("to send message:{}",message);
	  amqpTemplate.convertAndSend("queueTestKey",message);
	}

}
