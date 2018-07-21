package com.weixin.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.bean.WeixinAttention;
import com.weixin.mapper.WeixinAttentionMapper;
import com.weixin.model.WeixinAttentionModel;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>WeixinAttentionService<br>
 */
@Service("weixinAttentionService")
public class WeixinAttentionService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(WeixinAttentionService.class);
	

	

	@Autowired
    private WeixinAttentionMapper<T> mapper;

		
	public WeixinAttentionMapper<T> getMapper() {
		return mapper;
	}


	public void saveOrUpdate(WeixinAttention bean) {
		// TODO Auto-generated method stub
		WeixinAttentionModel wam=new WeixinAttentionModel();
		wam.setOpenid(bean==null?null:bean.getOpenid());
		int usercount=mapper.queryByCount(wam);
//		WeixinAttention queryById =mapper.queryOpenId(bean.getOpenid());
		if(usercount>0){
			bean.setSubscribe(1);
			mapper.updateSubscribeSelective( bean);
		}else{
			mapper.add((T) bean);
		}
	}


	public void updateSubscribeSelective(WeixinAttention bean) {
		// TODO Auto-generated method stub
		mapper.updateSubscribeSelective(bean);
	}

}
