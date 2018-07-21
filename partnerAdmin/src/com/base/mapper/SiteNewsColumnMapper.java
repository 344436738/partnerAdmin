package com.base.mapper;

import java.util.List;

import com.base.bean.SiteNewsColumn;
import com.base.model.SiteNewsColumnModel;

/**
 * SiteNewsColumn Mapper
 * 
 * @author Administrator
 * 
 */
public interface SiteNewsColumnMapper<T> extends BaseMapper<T> {

	public List<SiteNewsColumn> findNewsColumnList(SiteNewsColumnModel model);

	public SiteNewsColumn getSiteNewsColumn(Integer id);
	
}
