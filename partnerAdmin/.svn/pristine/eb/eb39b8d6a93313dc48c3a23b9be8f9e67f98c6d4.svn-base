package com.weixin.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.bean.WeixinAutoresponse;
import com.weixin.mapper.WeixinAutoresponseMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>WeixinAutoresponseService<br>
 */
@Service("weixinAutoresponseService")
public class WeixinAutoresponseService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(WeixinAutoresponseService.class);
	

	

	@Autowired
    private WeixinAutoresponseMapper<T> mapper;

		
	public WeixinAutoresponseMapper<T> getMapper() {
		return mapper;
	}


	public List<WeixinAutoresponse> findByProperty(Integer sys_accountId) {
		// TODO Auto-generated method stub
		return mapper.findByProperty(sys_accountId);
	}

}
