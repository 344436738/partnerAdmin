package com.business.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.mapper.TestcreateMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>TestcreateService<br>
 */
@Service("testcreateService")
public class TestcreateService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(TestcreateService.class);
	

	@Autowired
    private TestcreateMapper<T> mapper;

		
	public TestcreateMapper<T> getMapper() {
		return mapper;
	}

}
