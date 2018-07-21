package com.business.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.bean.StOpportunitiesRecommend;
import com.business.mapper.StOpportunitiesRecommendMapper;
import com.business.model.StOpportunitiesRecommendModel;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StOpportunitiesRecommendService<br>
 */
@Service("stOpportunitiesRecommendService")
public class StOpportunitiesRecommendService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StOpportunitiesRecommendService.class);
	
	@Autowired
    private StOpportunitiesRecommendMapper<T> mapper;

		
	public StOpportunitiesRecommendMapper<T> getMapper() {
		return mapper;
	}
	public Map<String, Integer> oppSum(StOpportunitiesRecommendModel model){
		return getMapper().oppSum(model);
	}

	public List<StOpportunitiesRecommend> queryConfirmInformation(StOpportunitiesRecommendModel model){
		return getMapper().queryConfirmInformation(model);
	}
	
	public List<StOpportunitiesRecommend> queryOpptunitiesDetail(StOpportunitiesRecommendModel model){
		return getMapper().queryOpptunitiesDetail(model);
	}
	
	public StOpportunitiesRecommend queryInfoById(Integer id){
		return getMapper().queryInfoById(id);
	}
	
	public Map<String, Integer> oppCount(Integer issuserid){
		return getMapper().oppCount(issuserid);
	}
	
	
	

}
