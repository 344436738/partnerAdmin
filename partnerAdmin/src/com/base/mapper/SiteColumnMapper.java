package com.base.mapper;

import java.util.List;

/**
 * SiteColumn Mapper
 * 
 * @author
 * 
 */
public interface SiteColumnMapper<T> extends BaseMapper<T> {

	/**
	 * 根据站点id查询栏目
	 * 
	 * @param siteId
	 */
	public List<T> queryBySiteId(Integer siteId);
}
