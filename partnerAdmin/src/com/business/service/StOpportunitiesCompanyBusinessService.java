package com.business.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.mapper.StOpportunitiesCompanyBusinessMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StOpportunitiesCompanyBusinessService<br>
 */
@Service("stOpportunitiesCompanyBusinessService")
public class StOpportunitiesCompanyBusinessService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StOpportunitiesCompanyBusinessService.class);
	

	

	@Autowired
    private StOpportunitiesCompanyBusinessMapper<T> mapper;

		
	public StOpportunitiesCompanyBusinessMapper<T> getMapper() {
		return mapper;
	}

}
