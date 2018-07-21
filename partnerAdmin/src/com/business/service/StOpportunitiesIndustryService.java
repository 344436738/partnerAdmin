package com.business.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.mapper.StOpportunitiesIndustryMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StOpportunitiesIndustryService<br>
 */
@Service("stOpportunitiesIndustryService")
public class StOpportunitiesIndustryService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StOpportunitiesIndustryService.class);
	

	

	@Autowired
    private StOpportunitiesIndustryMapper<T> mapper;

		
	public StOpportunitiesIndustryMapper<T> getMapper() {
		return mapper;
	}

}
