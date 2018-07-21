package com.business.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.mapper.StOpportunitiesEvaluateCompanyMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StOpportunitiesEvaluateCompanyService<br>
 */
@Service("stOpportunitiesEvaluateCompanyService")
public class StOpportunitiesEvaluateCompanyService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StOpportunitiesEvaluateCompanyService.class);
	

	

	@Autowired
    private StOpportunitiesEvaluateCompanyMapper<T> mapper;

		
	public StOpportunitiesEvaluateCompanyMapper<T> getMapper() {
		return mapper;
	}

}
