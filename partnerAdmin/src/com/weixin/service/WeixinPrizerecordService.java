package com.weixin.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.mapper.WeixinPrizerecordMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>WeixinPrizerecordService<br>
 */
@Service("weixinPrizerecordService")
public class WeixinPrizerecordService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(WeixinPrizerecordService.class);
	

	

	@Autowired
    private WeixinPrizerecordMapper<T> mapper;

		
	public WeixinPrizerecordMapper<T> getMapper() {
		return mapper;
	}

}
