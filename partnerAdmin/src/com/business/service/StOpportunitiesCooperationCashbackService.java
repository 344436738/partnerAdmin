package com.business.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.mapper.StOpportunitiesCooperationCashbackMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StOpportunitiesCooperationCashbackService<br>
 */
@Service("stOpportunitiesCooperationCashbackService")
public class StOpportunitiesCooperationCashbackService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StOpportunitiesCooperationCashbackService.class);
	

	public double queryPercentSum(Integer cooid){
		return getMapper().queryPercentSum(cooid);
	}

	@Autowired
    private StOpportunitiesCooperationCashbackMapper<T> mapper;

		
	public StOpportunitiesCooperationCashbackMapper<T> getMapper() {
		return mapper;
	}

}
