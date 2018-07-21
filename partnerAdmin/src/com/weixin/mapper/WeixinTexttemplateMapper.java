package com.weixin.mapper;
import com.base.mapper.BaseMapper;
import com.weixin.bean.WeixinTexttemplate;

/**
 * WeixinTexttemplate Mapper
 * @author Administrator
 *
 */
public interface WeixinTexttemplateMapper<T> extends BaseMapper<T> {

	WeixinTexttemplate getTextTemplate(Integer sys_accountId, String templatename);

	
	
}
