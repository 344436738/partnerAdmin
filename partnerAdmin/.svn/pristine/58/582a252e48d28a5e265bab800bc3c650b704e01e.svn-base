package com.business.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.mapper.StOpportunitiesIncomeMapper;
import com.business.model.StOpportunitiesIncomeModel;
import com.base.model.BaseModel;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StOpportunitiesIncomeService<br>
 */
@Service("stOpportunitiesIncomeService")
public class StOpportunitiesIncomeService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StOpportunitiesIncomeService.class);
	

	public Map<String, Integer> oppIncomeSum(StOpportunitiesIncomeModel model) throws Exception {
		return getMapper().oppIncomeSum(model);
	} 

	@Autowired
    private StOpportunitiesIncomeMapper<T> mapper;

		
	public StOpportunitiesIncomeMapper<T> getMapper() {
		return mapper;
	}

}
