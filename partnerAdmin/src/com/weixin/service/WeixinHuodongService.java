package com.weixin.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.bean.WeixinHuodong;
import com.weixin.mapper.WeixinHuodongMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>WeixinHuodongService<br>
 */
@Service("weixinHuodongService")
public class WeixinHuodongService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(WeixinHuodongService.class);
	

	

	@Autowired
    private WeixinHuodongMapper<T> mapper;

		
	public WeixinHuodongMapper<T> getMapper() {
		return mapper;
	}



}
