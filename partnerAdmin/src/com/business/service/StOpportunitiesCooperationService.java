package com.business.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.mapper.StOpportunitiesCooperationMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StOpportunitiesCooperationService<br>
 */
@Service("stOpportunitiesCooperationService")
public class StOpportunitiesCooperationService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StOpportunitiesCooperationService.class);
	

	public void confirmCooperation(Object... ids) throws Exception {
		if (ids == null || ids.length < 1) {
			return;
		}
		for (Object id : ids) {
			getMapper().confirmCooperation(id);
		}
	}

	public void confirmNotCooperation(Object... ids) throws Exception {
		if (ids == null || ids.length < 1) {
			return;
		}
		for (Object id : ids) {
			getMapper().confirmNotCooperation(id);
		}
	}
	
	@Autowired
    private StOpportunitiesCooperationMapper<T> mapper;

		
	public StOpportunitiesCooperationMapper<T> getMapper() {
		return mapper;
	}

}
