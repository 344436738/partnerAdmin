package com.business.mapper;
import com.base.mapper.BaseMapper;

/**
 * StOpportunitiesCooperation Mapper
 * @author Administrator
 *
 */
public interface StOpportunitiesCooperationMapper<T> extends BaseMapper<T> {

	public void confirmCooperation(Object id);
	public void confirmNotCooperation(Object id);
}
