package com.weixin.mapper;
import java.util.List;

import com.base.mapper.BaseMapper;
import com.weixin.bean.WeixinAccount;

/**
 * WeixinAccount Mapper
 * @author Administrator
 *
 */
public interface WeixinAccountMapper<T> extends BaseMapper<T> {


	List<WeixinAccount> findByUsername(String username);

	WeixinAccount queryByUserid(Integer userid);
	void update(WeixinAccount bean);

	WeixinAccount findUniqueByProperty(String toUserName);
}
