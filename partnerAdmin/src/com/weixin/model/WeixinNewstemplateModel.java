package com.weixin.model;
import com.base.model.BaseModel;

public class WeixinNewstemplateModel extends BaseModel {
	

	
	private java.util.Date addtime;//  
	private String templatename;//   
	private String type;//   
	private Integer accountid;//
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public java.util.Date getAddtime() {
		return addtime;
	}
	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}
	
	public String getTemplatename() {
		return templatename;
	}
	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getAccountid() {
		return accountid;
	}
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	
	
}