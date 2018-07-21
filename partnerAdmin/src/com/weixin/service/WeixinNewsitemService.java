package com.weixin.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.bean.WeixinNewstemplate;
import com.weixin.mapper.WeixinNewsitemMapper;
import com.weixin.mapper.WeixinNewstemplateMapper;
import com.base.bean.SiteNews;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>WeixinNewsitemService<br>
 */
@Service("weixinNewsitemService")
public class WeixinNewsitemService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(WeixinNewsitemService.class);
	

	

	@Autowired
    private WeixinNewsitemMapper<T> mapper;

	@Autowired
    private WeixinNewstemplateMapper<T> NewsitemMapper;
		
	public WeixinNewsitemMapper<T> getMapper() {
		return mapper;
	}


}
