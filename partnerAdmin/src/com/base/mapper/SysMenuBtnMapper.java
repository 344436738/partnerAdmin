package com.base.mapper;

import java.util.List;

/**
 * SysMenuBtn Mapper
 * 
 * @author
 * 
 */
public interface SysMenuBtnMapper<T> extends BaseMapper<T> {

	public List<T> queryByMenuid(Integer menuid);

	public List<T> queryByMenuUrl(String url);

	public void deleteByMenuid(Integer menuid);

	public List<T> getMenuBtnByUser(Integer userid);

	public List<T> queryByAll();

	public List<T> getMenuBtnByRoleId(Integer roleid);
}
