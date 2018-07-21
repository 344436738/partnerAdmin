package com.weixin.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.bean.WeixinMenu;
import com.weixin.mapper.WeixinMenuMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>WeixinMenuService<br>
 */
@Service("weixinMenuService")
public class WeixinMenuService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(WeixinMenuService.class);
	

	

	@Autowired
    private WeixinMenuMapper<T> mapper;
		
	public WeixinMenuMapper<T> getMapper() {
		return mapper;
	}

	public WeixinMenu findUniqueByProperty(String key) {
		// TODO Auto-generated method stub
		return mapper.findUniqueByProperty(key);
	}

}
