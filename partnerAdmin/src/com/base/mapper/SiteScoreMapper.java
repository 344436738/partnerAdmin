package com.base.mapper;

import java.util.Map;

/**
 * SiteScore Mapper
 * 
 * @author
 * 
 */
public interface SiteScoreMapper<T> extends BaseMapper<T> {

	public void saveScore(Map<String, Object> map);

}
