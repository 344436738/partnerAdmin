package com.base.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.bean.SysMenuBtn;
import com.base.mapper.SysMenuBtnMapper;

/**
 * 
 * <br>
 * <b>功能：</b>SysMenuBtnService<br>
 * <b>作者：</b><br>
 * <b>日期：</b> 2013-03-01 <br>
 */
@Service("sysMenuBtnService")
public class SysMenuBtnService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(SysMenuBtnService.class);

	public List<T> queryByAll() {
		return getMapper().queryByAll();
	}

	public List<T> queryByMenuid(Integer menuid) {
		return getMapper().queryByMenuid(menuid);
	}

	public List<T> queryByMenuUrl(String url) {
		return getMapper().queryByMenuUrl(url);
	}

	public void deleteByMenuid(Integer menuid) {
		getMapper().deleteByMenuid(menuid);
	}

	public List<T> getMenuBtnByUser(Integer userid) {
		return getMapper().getMenuBtnByUser(userid);
	}

	@Autowired
	private SysMenuBtnMapper<T> mapper;

	public SysMenuBtnMapper<T> getMapper() {
		return mapper;
	}

	public List<T> getMenuBtnByRoleId(Integer roleid){
		return getMapper().getMenuBtnByRoleId(roleid);
	}

}
