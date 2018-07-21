package com.business.mapper;
import java.util.List;

import com.base.mapper.BaseMapper;
import com.business.bean.StOpportunitiesDictionaryOppgrade;

/**
 * StOpportunitiesDictionaryScale Mapper
 * @author Administrator
 *
 */
public interface StOpportunitiesDictionaryScaleMapper<T> extends BaseMapper<T> {
	
	public List<T> queryForShow(Integer userid);
}
