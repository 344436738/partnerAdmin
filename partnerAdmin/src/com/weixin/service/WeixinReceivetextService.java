package com.weixin.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.mapper.WeixinReceivetextMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>WeixinReceivetextService<br>
 */
@Service("weixinReceivetextService")
public class WeixinReceivetextService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(WeixinReceivetextService.class);
	

	

	@Autowired
    private WeixinReceivetextMapper<T> mapper;

		
	public WeixinReceivetextMapper<T> getMapper() {
		return mapper;
	}

}
