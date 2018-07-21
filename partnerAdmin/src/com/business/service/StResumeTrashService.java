package com.business.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.mapper.StResumeScorerecordMapper;
import com.business.mapper.StResumeTrashMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StResumeTrashService<br>
 */
@Service("stResumeTrashService")
public class StResumeTrashService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StResumeTrashService.class);
	
	@Autowired
    private StResumeScorerecordMapper<T> tolmapper;
	

	@Autowired
    private StResumeTrashMapper<T> mapper;

		
	public StResumeTrashMapper<T> getMapper() {
		return mapper;
	}

	public StResumeScorerecordMapper<T> getTolmapper() {
		return tolmapper;
	}
	
	
	
}
