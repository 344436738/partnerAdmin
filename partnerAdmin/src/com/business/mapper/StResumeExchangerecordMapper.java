package com.business.mapper;
import java.util.List;

import com.base.mapper.BaseMapper;
import com.business.bean.StResumeExchangerecord;
import com.business.model.StResumeExchangerecordModel;

/**
 * StResumeExchangerecord Mapper
 * @author Administrator
 *
 */
public interface StResumeExchangerecordMapper<T> extends BaseMapper<T> {
	
	public int queryFrozenScore(int userid) ;
	public List<T> scoreExchangeList(StResumeExchangerecordModel model);
	
	public T scoreExchange(StResumeExchangerecord model);
	
	public int queryByScoreExchangeCount(StResumeExchangerecordModel model);
	
	public List<T> tongJiResumeExchangerecord(String str);
}
