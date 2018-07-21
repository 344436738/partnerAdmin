package com.weixin.model;
import com.base.model.BaseModel;

public class WeixinTexttemplateModel extends BaseModel {
	

		private Integer id;//   
	private java.util.Date addtime;//     
	private String content;//   
	private String templatename;//   
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTemplatename() {
		return templatename;
	}
	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	public Integer getAccountid() {
		return accountid;
	}
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	

}
