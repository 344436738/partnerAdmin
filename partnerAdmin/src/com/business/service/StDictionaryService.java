package com.business.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.mapper.StDictionaryMapper;
import com.business.model.StDictionaryModel;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StDictionaryService<br>
 */
@Service("stDictionaryService")
public class StDictionaryService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StDictionaryService.class);
	

	public T queryByDkey(StDictionaryModel model){
		return getMapper().queryByDkey(model);
	}

	@Autowired
    private StDictionaryMapper<T> mapper;

		
	public StDictionaryMapper<T> getMapper() {
		return mapper;
	}

}
