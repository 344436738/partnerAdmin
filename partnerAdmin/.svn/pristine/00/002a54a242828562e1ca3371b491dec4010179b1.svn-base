package com.base.bean;

import java.util.ArrayList;
import java.util.List;

public class SiteNewsColumn extends BaseBean {

	private Integer id;//   	private Integer pid;// 父节点id	private String name;//   	private String description;// 描述	private Integer sorting;// 排序	private Integer forbidden;// 是否禁用，默认是0，1为禁用	private Integer createUer;// 创建用户	private java.util.Date createTime;// 
	private Integer type;//栏目类别
	private String template;//模版
	// 以下为非数据库字段，方便前台传数据过来组装
	private String creatUserName;
	
	private int subCount;//统计子菜单总数
	private List<SiteNewsColumn> childList;//子栏目
	
	
	public List<SiteNewsColumn> getChildList() {
		if(childList==null){
			childList=new ArrayList();
		}
		return childList;
	}

	public void setChildList(List<SiteNewsColumn> childList) {
		this.childList = childList;
	}

	public int getSubCount() {
		return subCount;
	}

	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}
	
	
	public String getCreatUserName() {
		return creatUserName;
	}

	public void setCreatUserName(String creatUserName) {
		this.creatUserName = creatUserName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSorting() {
		return this.sorting;
	}

	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}

	public Integer getForbidden() {
		return this.forbidden;
	}

	public void setForbidden(Integer forbidden) {
		this.forbidden = forbidden;
	}

	public Integer getCreateUer() {
		return this.createUer;
	}

	public void setCreateUer(Integer createUer) {
		this.createUer = createUer;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
}
