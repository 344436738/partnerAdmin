package com.weixin.bean;
import com.base.bean.BaseBean;


public class WeixinAutoresponse extends BaseBean {
	
		private Integer id;//    	private java.util.Date addtime;//    	private String keyword;//   	private String msgType;//   	private Integer rescontent;//   	private String templatename;//   	private Integer accountid;//    	 	  		public String getKeyword() {	    return this.keyword;	}	public void setKeyword(String keyword) {	    this.keyword=keyword;	}			public String getMsgType() {
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
	public String getTemplatename() {	    return this.templatename;	}	public void setTemplatename(String templatename) {	    this.templatename=templatename;	}		public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public java.util.Date getAddtime() {	    return this.addtime;	}	public void setAddtime(java.util.Date addtime) {	    this.addtime=addtime;	}	public Integer getAccountid() {	    return this.accountid;	}	public void setAccountid(Integer accountid) {	    this.accountid=accountid;	}
}
