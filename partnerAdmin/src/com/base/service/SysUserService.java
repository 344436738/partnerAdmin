package com.base.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.bean.SysRoleRel;
import com.base.bean.SysUser;
import com.base.bean.SysRoleRel.RelType;
import com.base.mapper.SysOrgMapper;
import com.base.mapper.SysUserMapper;
import com.base.model.BaseModel;
import com.base.model.SysUserModel;

/**
 * 
 * <br>
 * <b>功能：</b>SysUserService<br>
 * <b>作者：</b><br>
 * <b>日期：</b> 2013-03-01 <br>
 */
@Service("sysUserService")
public class SysUserService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(SysUserService.class);

	@Autowired
	private SysRoleRelService<SysRoleRel> sysRoleRelService;

	@Autowired
	private SysOrgMapper<T> orgMapper;
	
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
		for (Object id : ids) {
			sysRoleRelService.deleteByObjId((Integer) id, RelType.USER.key);
		}
	}

	/**
	 * 检查登录
	 * 
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryLogin(String email, String pwd) {
		SysUserModel model = new SysUserModel();
		model.setEmail(email);
		model.setPwd(pwd);
		return getMapper().queryLogin(model);
	}

	/**
	 * 查询邮箱总数，检查是否存在
	 * 
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email) {
		return getMapper().getUserCountByEmail(email);
	}

	/**
	 * 查询用户权限
	 * 
	 * @param userId
	 * @return
	 */
	public List<SysRoleRel> getUserRole(Integer userId) {
		return sysRoleRelService.queryByObjId(userId, RelType.USER.key);
	}

	/**
	 * 添加用户权限
	 * 
	 * @param userId
	 * @param roleIds
	 * @throws Exception
	 */
	public void addUserRole(Integer userId, Integer[] roleIds) throws Exception {
		if (userId == null ) {
			return;
		} 
		// 清除关联关系
		sysRoleRelService.deleteByObjId(userId, RelType.USER.key);
		if(roleIds == null || roleIds.length < 1){
			return;//清除了关联关系，就返回
		} 
		for (Integer roleId : roleIds) {
			SysRoleRel rel = new SysRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(userId);
			rel.setRelType(RelType.USER.key);
			sysRoleRelService.add(rel);
		}
	}
	
	/**
	 * 查询用户列表
	 * 
	 * @param 
	 * @param 
	 * @throws Exception
	 */
	@Override
	public List<T> queryByList(BaseModel sysUserModel) throws Exception {
		SysUserModel model= (SysUserModel)sysUserModel;
		if(model.getOrgId()!=null){
			List childIds=this.getOrgByOrgId(model.getOrgId());
			childIds.add(model.getOrgId());//add FatherId in it
			model.setSysOrgIdList(childIds);
		}
		Integer rowCount = queryByCount(model);
		model.getPager().setRowCount(rowCount);
		return getMapper().queryByList(model);
	}
	
	

	/**
	 * 根据pid递归查询id
	 * 
	 * @param 
	 * @param 
	 * @throws Exception
	 */
	public List<Integer> getOrgByOrgId(Integer id){
		List<Integer> list=new ArrayList<Integer>();
		List<Integer> childList=new ArrayList<Integer>();
		  childList = orgMapper.getOrgByOrgId(id);
		 if(null!=childList && childList.size()>0){
				for (Integer childId : childList) {
					list.add(childId);
					list.addAll(getOrgByOrgId(childId));
				}
			}
		return   list;
	}
	
	
	public List<T> queryByListAll(BaseModel model) throws Exception {
		return getMapper().queryByListAll(model);
	}
	/**
	 * 查询用户 
	 * @param user
	 * @return
	 */
	public SysUser getUserByOrgIdOrNickName(SysUser user) {
		return	getMapper().getUserByOrgIdOrNickName(user);
	}
	
	
	@Autowired
	private SysUserMapper<T> mapper;

	public SysUserMapper<T> getMapper() {
		return mapper;
	}
	
	
}
