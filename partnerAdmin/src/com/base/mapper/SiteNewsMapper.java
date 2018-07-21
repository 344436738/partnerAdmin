package com.base.mapper;

import java.util.List;
import com.base.bean.SiteNews;
import com.base.bean.SiteNewsColumn;
import com.base.model.SiteNewsModel;

/**
 * SiteNews Mapper
 * 
 * @author Administrator
 * 
 */
public interface SiteNewsMapper<T> extends BaseMapper<T> {
	
	public List<SiteNews> queryColunmnPVList(SiteNewsModel siteNewsModel);
	
	public List<SiteNews> queryColunmnReleaseList(SiteNewsModel siteNewsModel);
	
}
