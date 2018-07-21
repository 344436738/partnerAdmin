package com.business.mapper;
import java.util.List;
import java.util.Map;

import com.base.mapper.BaseMapper;
import com.business.bean.StOpportunitiesRecommend;
import com.business.model.StOpportunitiesRecommendModel;

/**
 * StOpportunitiesRecommend Mapper
 * @author Administrator
 *
 */
public interface StOpportunitiesRecommendMapper<T> extends BaseMapper<T> {
	
	public Map<String, Integer> oppSum(StOpportunitiesRecommendModel model);
	
	public List<StOpportunitiesRecommend> queryConfirmInformation(StOpportunitiesRecommendModel model);
	
	public StOpportunitiesRecommend queryConfirmInformationById(StOpportunitiesRecommendModel model);
	
	public List<StOpportunitiesRecommend> queryOpptunitiesDetail(StOpportunitiesRecommendModel model);
	
	public StOpportunitiesRecommend queryInfoById(Integer id);
	
	public Map<String, Integer> oppCount(Integer issuserid);
}
