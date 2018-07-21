package com.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.business.mapper.StCompanyBusinessMapper;

@Service("stCompanyBusinessService")
public class StCompanyBusinessService<T> extends BaseService<T> {

	@Autowired
    private StCompanyBusinessMapper<T> mapper;

		
	public StCompanyBusinessMapper<T> getMapper() {
		return mapper;
	}

}
