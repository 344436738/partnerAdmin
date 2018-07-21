package com.business.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.bean.StOpportunitiesDictionaryOppgrade;
import com.business.mapper.StOpportunitiesDictionaryScaleMapper;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StOpportunitiesDictionaryScaleService<br>
 */
@Service("stOpportunitiesDictionaryScaleService")
public class StOpportunitiesDictionaryScaleService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StOpportunitiesDictionaryScaleService.class);
	

	public List<T> queryForShow(Integer userid){
		return getMapper().queryForShow(userid);
	}

	@Autowired
    private StOpportunitiesDictionaryScaleMapper<T> mapper;

		
	public StOpportunitiesDictionaryScaleMapper<T> getMapper() {
		return mapper;
	}

}
