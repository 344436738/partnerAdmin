package com.base.bean;

import java.util.ArrayList;
import java.util.List;

public class SysMenu extends BaseBean{

	private Integer id;// 主键
	private String name;// 菜单名称
	private String url;// 系统url
	private Integer parentId;// 父id 关联sys_menu.id
	private Integer deleted;// 是否删除,0=未删除，1=已删除
	private java.sql.Timestamp createTime;// 创建时间
	private java.sql.Timestamp updateTime;// 修改时间
	private Integer rank;// 排序
	private String actions; // 注册Action 按钮|分隔

	private int subCount;// 子菜单总数
	private Integer isopen;//是否弹出新页面 0默认 不弹出  ，1弹出
	
	// 菜单按钮
	private List<SysMenuBtn> btns;
	private String icon;// 菜单图标
	
	//以下是非数据库字段
	private List<SysMenu> childList;//子菜单
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}

	public java.sql.Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public List<SysMenuBtn> getBtns() {
		return btns;
	}

	public void setBtns(List<SysMenuBtn> btns) {
		this.btns = btns;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public int getSubCount() {
		return subCount;
	}

	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<SysMenu> getChildList() {
		if(childList==null){
			childList=new ArrayList();
		}
		return childList;
	}

	public void setChildList(List<SysMenu> childList) {
		this.childList = childList;
	}

	public Integer getIsopen() {
		return isopen;
	}

	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}

	
}
