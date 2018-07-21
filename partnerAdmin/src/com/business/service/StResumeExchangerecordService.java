package com.business.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.mapper.StResumeExchangerecordMapper;
import com.business.model.StResumeExchangerecordModel;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StResumeExchangerecordService<br>
 */
@Service("stResumeExchangerecordService")
public class StResumeExchangerecordService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StResumeExchangerecordService.class);
	

	public int queryFrozenScore(int userid){
		return getMapper().queryFrozenScore(userid);
	}
	public List<T> scoreExchangeList(StResumeExchangerecordModel model){
		Integer rowCount = getMapper().queryByScoreExchangeCount(model);
		model.getPager().setRowCount(rowCount);

		return getMapper().scoreExchangeList(model);
	}
	
	@Autowired
    private StResumeExchangerecordMapper<T> mapper;

		
	public StResumeExchangerecordMapper<T> getMapper() {
		return mapper;
	}

}
