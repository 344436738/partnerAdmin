package com.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.bean.SiteNewsColumn;
import com.base.bean.SysMenu;
import com.base.bean.SysMenuBtn;
import com.base.bean.SysRole;
import com.base.bean.SysRoleRel;
import com.base.bean.SysRoleRel.RelType;
import com.base.mapper.SysMenuMapper;
import com.base.model.BaseModel;
import com.base.model.SiteNewsColumnModel;
import com.base.model.SysMenuModel;

/**
 * 
 * <br>
 * <b>功能：</b>SysMenuService<br>
 * <b>作者：</b><br>
 * <b>日期：</b>2013-03-01<br>
 * 
 */
@Service("sysMenuService")
public class SysMenuService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(SysMenuService.class);

	@Autowired
	private SysRoleRelService<SysRoleRel> sysRoleRelService;

	@Autowired
	private SysMenuBtnService<SysMenuBtn> sysMenuBtnService;

	@Autowired
	private SysMenuMapper<T> mapper;
	
	 

	/**
	 * 保存菜单btn
	 * 
	 * @param btns
	 * @throws Exception
	 */
	public void saveBtns(Integer menuid, List<SysMenuBtn> btns)
			throws Exception {
		if (btns == null || btns.isEmpty()) {
			return;
		}
		for (SysMenuBtn btn : btns) {
			if (btn.getId() != null && "1".equals(btn.getDeleteFlag())) {
				sysMenuBtnService.delete(btn.getId());
				continue;
			}
			btn.setMenuid(menuid);
			if (btn.getId() == null) {
				sysMenuBtnService.add(btn);
			} else {
				sysMenuBtnService.update(btn);
			}
		}

	}
	@Override
	public void add(T t) throws Exception {
		super.add(t);
		SysMenu menu=(SysMenu)t;
		saveBtns(menu.getId(), (menu).getBtns());
	}

	@Override
	public void update(T t) throws Exception {
		super.update(t);
		SysMenu menu=(SysMenu)t;
		saveBtns(menu.getId(), menu.getBtns());
	}
	
	@Override
	public void updateBySelective(T t) throws Exception {
		super.updateBySelective(t);
		SysMenu menu=(SysMenu)t;
		saveBtns(menu.getId(), menu.getBtns());
	}
	

	/**
	 * 查询所有系统菜单列表
	 * 
	 * @return
	 */
	public List<T> queryByAll() {
		return mapper.queryByAll();
	}

	/**
	 * 获取顶级菜单
	 * 
	 * @return
	 */
	public List<T> getRootMenu(Integer menuId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("menuId", menuId);
		return mapper.getRootMenu(map);
	}

	/**
	 * 获取子菜单
	 * 
	 * @return
	 */
	public List<T> getChildMenu() {
		return mapper.getChildMenu();
	}

	/**
	 * 根据用户id查询父菜单
	 * 
	 * @param roleId
	 * @return
	 */
	public List<T> getRootMenuByUser(Integer userId) {
		return getMapper().getRootMenuByUser(userId);
	}

	/**
	 * 根据用户id查询子菜单
	 * 
	 * @param roleId
	 * @return
	 */
	public List<T> getChildMenuByUser(Integer userId) {
		return getMapper().getChildMenuByUser(userId);
	}

	/**
	 * 根据权限id查询菜单
	 * 
	 * @param roleId
	 * @return
	 */
	public List<T> getMenuByRoleId(Integer roleId) {
		return getMapper().getMenuByRoleId(roleId);
	}

	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
		// 删除关联关系
		for (Object id : ids) {
			sysRoleRelService.deleteByObjId((Integer) id, RelType.MENU.key);
			sysMenuBtnService.deleteByMenuid((Integer) id);
		}
	}

	public SysMenuMapper<T> getMapper() {
		return mapper;
	}

	public List<SysMenu> queryAllMenuWithChild() throws Exception {
		List<SysMenu> list=(List<SysMenu>)getMapper().queryByAll();
		List<SysMenu> flist=new ArrayList();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getParentId()==null){
				flist.add(list.get(i));
			}
		}
		for(int i=0;i<flist.size();i++){
			for(int j=0;j<list.size();j++){
				if(flist.get(i).getId().equals(list.get(j).getParentId())){
					flist.get(i).getChildList().add(list.get(j));
				}
			}
		}
		
		return flist;
	}

	/**
	 * 根据权限id查询菜单(仅含父菜单)
	 * @param roleId
	 * @return
	 */
	public List<T> getRootMenuByRoleId(Integer roleId){
		return getMapper().getRootMenuByRoleId(roleId);
	}
}
