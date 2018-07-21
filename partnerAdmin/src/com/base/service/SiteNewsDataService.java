package com.base.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.mapper.SiteNewsDataMapper;

/**
 * 
 * <br>
 * <b>功能：</b>SiteNewsDataService<br>
 * <b>作者：</b>罗泽军<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 * <b>版权所有：<b>版权所有(C) 2011，WWW.VOWO.COM<br>
 */
@Service("siteNewsDataService")
public class SiteNewsDataService<T> extends BaseService<T> {
	private final static Logger log = Logger
			.getLogger(SiteNewsDataService.class);

	@Autowired
	private SiteNewsDataMapper<T> mapper;

	public SiteNewsDataMapper<T> getMapper() {
		return mapper;
	}

}
