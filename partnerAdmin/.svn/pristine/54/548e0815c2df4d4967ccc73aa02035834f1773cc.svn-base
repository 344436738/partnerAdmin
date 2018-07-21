package com.business.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.mapper.StResumeActivitiesMapper;
import com.base.model.BaseModel;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StResumeActivitiesService<br>
 */
@Service("stResumeActivitiesService")
public class StResumeActivitiesService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StResumeActivitiesService.class);
	

	public List<T> queryByTime() throws Exception {
		return getMapper().queryByTime();
	}

	@Autowired
    private StResumeActivitiesMapper<T> mapper;

		
	public StResumeActivitiesMapper<T> getMapper() {
		return mapper;
	}

}
