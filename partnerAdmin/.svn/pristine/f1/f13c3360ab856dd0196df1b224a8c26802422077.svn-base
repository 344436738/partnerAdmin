package com.weixin.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.bean.WeixinSubscribe;
import com.weixin.mapper.WeixinSubscribeMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>WeixinSubscribeService<br>
 */
@Service("weixinSubscribeService")
public class WeixinSubscribeService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(WeixinSubscribeService.class);
	

	

	@Autowired
    private WeixinSubscribeMapper<T> mapper;

		
	public WeixinSubscribeMapper<T> getMapper() {
		return mapper;
	}


	public List<WeixinSubscribe> queryByAccountId(Integer sys_accountId) {
		// TODO Auto-generated method stub
		return mapper.queryByAccountId(sys_accountId);
	}
	
}
