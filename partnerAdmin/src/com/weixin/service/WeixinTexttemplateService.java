package com.weixin.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.mapper.WeixinTexttemplateMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>WeixinTexttemplateService<br>
 */
@Service("weixinTexttemplateService")
public class WeixinTexttemplateService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(WeixinTexttemplateService.class);
	

	

	@Autowired
    private WeixinTexttemplateMapper<T> mapper;

		
	public WeixinTexttemplateMapper<T> getMapper() {
		return mapper;
	}

}
