package com.business.model;
import com.base.model.BaseModel;

public class TestcreateModel extends BaseModel {
	

		private Integer id;//   	private String title;//   标题	private java.util.Date creatTime;//   创建时间	private Integer isDelete;//   是否删除	private String content;//   内容	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public java.util.Date getCreatTime() {	    return this.creatTime;	}	public void setCreatTime(java.util.Date creatTime) {	    this.creatTime=creatTime;	}	public Integer getIsDelete() {	    return this.isDelete;	}	public void setIsDelete(Integer isDelete) {	    this.isDelete=isDelete;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}
	
}
