package com.base.mapper;

import java.util.List;

import com.base.bean.SysRole;
import com.base.model.BaseModel;

/**
 * SysMenu Mapper
 * 
 * @author
 * 
 */
public interface SysMenuMapper<T> extends BaseMapper<T> {

	/**
	 * 查询所有系统菜单列表
	 * 
	 * @return
	 */
	public List<T> queryByAll();

	/**
	 * 获取顶级菜单
	 * 
	 * @return
	 */
	public List<T> getRootMenu(java.util.Map map);

	/**
	 * 获取子菜单
	 * 
	 * @return
	 */
	public List<T> getChildMenu();

	/**
	 * 根据权限id查询菜单
	 * 
	 * @param roleId
	 * @return
	 */
	public List<T> getMenuByRoleId(Integer roleId);

	/**
	 * 根据用户id查询父菜单菜单
	 * 
	 * @param userId
	 * @return
	 */
	public List<T> getRootMenuByUser(Integer userId);

	/**
	 * 根据用户id查询子菜单菜单
	 * 
	 * @param userId
	 * @return
	 */
	public List<T> getChildMenuByUser(Integer userId);
	
	/**
	 * 检查是否有数据查看权限
	 * @param userId
	 * @return
	 */
	public List<SysRole> getViewData(Integer userId);

	public List<T> getRootMenuByRoleId(Integer roleId);

}
