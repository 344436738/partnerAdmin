package com.weixin.model;
import com.base.model.BaseModel;

public class WeixinAutoresponseModel extends BaseModel {
	

	private Integer id;//    
	private java.util.Date addtime;//    
	private String keyword;//   
	private String msgType;//   
	private Integer rescontent;//   
	private String templatename;//   
	private Integer accountid;//    
	 
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAccountid() {
		return accountid;
	}
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	
	public java.util.Date getAddtime() {
		return addtime;
	}
	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public Integer getRescontent() {
		return rescontent;
	}
	public void setRescontent(Integer rescontent) {
		this.rescontent = rescontent;
	}
	public String getTemplatename() {
		return templatename;
	}
	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	 	
	
}
