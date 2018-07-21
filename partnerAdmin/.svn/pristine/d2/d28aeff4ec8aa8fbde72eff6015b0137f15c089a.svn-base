package com.weixin.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.mapper.WeixinHdrecordMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>WeixinHdrecordService<br>
 */
@Service("weixinHdrecordService")
public class WeixinHdrecordService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(WeixinHdrecordService.class);
	

	

	@Autowired
    private WeixinHdrecordMapper<T> mapper;

		
	public WeixinHdrecordMapper<T> getMapper() {
		return mapper;
	}

}
