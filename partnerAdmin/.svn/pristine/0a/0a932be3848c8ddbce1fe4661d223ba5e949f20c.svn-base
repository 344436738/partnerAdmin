package com.base.mapper;

import java.util.List;

import com.base.bean.SysUser;
import com.base.model.BaseModel;
import com.base.model.SysUserModel;

/**
 * SysUser Mapper
 * 
 * @author
 * 
 */
public interface SysUserMapper<T> extends BaseMapper<T> {
	/**
	 * 查询全部用户
	 */
	public List<T> queryByListAll(BaseModel model);
	

	/**
	 * 检查登录
	 * 
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryLogin(SysUserModel model);

	/**
	 * 查询邮箱总数，检查是否存在
	 * 
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email);
	
	/**
	 * 查询用户 
	 * @param user
	 * @return
	 */
	public SysUser getUserByOrgIdOrNickName(SysUser user);

}
