package com.business.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.mapper.BaseMapper;
import com.business.bean.StWebUser;
import com.business.model.StOpportunitiesRecommendModel;
import com.business.model.StWebUserModel;

/**
 * StWebUser Mapper
 * @author Administrator
 *
 */
public interface StWebUserMapper<T> extends BaseMapper<T> {
	
	public void updateScore(@Param("userid") Object userid,@Param("score") Object score);
	
	public List<T> queryByPhone(String phone);
	
	public StWebUser queryForLogin(StWebUserModel model);
	
	public List<T> queryForPayoff(StWebUserModel model);
	
	public StWebUser queryRCid(StWebUserModel model);
	
	public StWebUser queryForRegister(StWebUserModel model);
	
	public List<T> queryForAdmin(StWebUserModel model);
	public List<T> queryForScore(StWebUserModel model);
	
	public List<T> selectStWebUser(String str); 
	
	public StWebUser selectStWebUserByphone(String str);
	
	
	public int queryForAdminCount(StWebUserModel model);
	
	
	public int  queryByCountUser(String str);
	
	
	
	
	
	
}
