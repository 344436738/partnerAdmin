package com.business.mapper;
import com.base.mapper.BaseMapper;
import com.base.model.BaseModel;

/**
 * StOpportunitiesCooperationCashback Mapper
 * @author Administrator
 *
 */
public interface StOpportunitiesCooperationCashbackMapper<T> extends BaseMapper<T> {
	
	public double queryPercentSum(Integer cooid);
}
