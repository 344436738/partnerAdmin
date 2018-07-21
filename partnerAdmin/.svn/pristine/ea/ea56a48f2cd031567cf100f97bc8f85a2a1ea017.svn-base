package com.base.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.mapper.WebPageMapper;

/**
 * 
 * <br>
 * <b>功能：</b>WebPageService<br>
 * <b>作者：</b><br>
 * <b>日期：</b> 2013-03-01 <br>
 */
@Service("webPageService")
public class WebPageService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(WebPageService.class);

	@Autowired
	private WebPageMapper<T> mapper;

	public WebPageMapper<T> getMapper() {
		return mapper;
	}

}
