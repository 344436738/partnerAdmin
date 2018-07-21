package com.business.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.mapper.StResumeActivitiesJoinMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StResumeActivitiesJoinService<br>
 */
@Service("stResumeActivitiesJoinService")
public class StResumeActivitiesJoinService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StResumeActivitiesJoinService.class);
	

	

	@Autowired
    private StResumeActivitiesJoinMapper<T> mapper;

		
	public StResumeActivitiesJoinMapper<T> getMapper() {
		return mapper;
	}

}
