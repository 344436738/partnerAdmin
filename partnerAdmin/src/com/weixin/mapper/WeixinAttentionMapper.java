package com.weixin.mapper;
import com.base.mapper.BaseMapper;
import com.weixin.bean.WeixinAttention;

/**
 * WeixinAttention Mapper
 * @author Administrator
 *
 */
public interface WeixinAttentionMapper<T> extends BaseMapper<T> {

	WeixinAttention queryOpenId(String openid);

	void updateSubscribeSelective(WeixinAttention bean);
	
	
}
