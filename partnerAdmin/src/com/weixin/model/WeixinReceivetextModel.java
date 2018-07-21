package com.weixin.model;
import com.base.model.BaseModel;

public class WeixinReceivetextModel extends BaseModel {
	

		private Integer id;//   ID	private String content;//   消息内容	private java.util.Date createtime;//   消息创建时间	private String fromusername;//   发送方账号	private String msgid;//   消息ID	private String msgtype;//   消息类型	private String rescontent;//   回复内容	private String response;//   是否回复	private String tousername;//   开发者微信号	private Integer accountid;//   微信账号ID	private String nickname;//   用户昵称	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public java.util.Date getCreatetime() {	    return this.createtime;	}	public void setCreatetime(java.util.Date createtime) {	    this.createtime=createtime;	}	public String getFromusername() {	    return this.fromusername;	}	public void setFromusername(String fromusername) {	    this.fromusername=fromusername;	}	public String getMsgid() {	    return this.msgid;	}	public void setMsgid(String msgid) {	    this.msgid=msgid;	}	public String getMsgtype() {	    return this.msgtype;	}	public void setMsgtype(String msgtype) {	    this.msgtype=msgtype;	}	public String getRescontent() {	    return this.rescontent;	}	public void setRescontent(String rescontent) {	    this.rescontent=rescontent;	}	public String getResponse() {	    return this.response;	}	public void setResponse(String response) {	    this.response=response;	}	public String getTousername() {	    return this.tousername;	}	public void setTousername(String tousername) {	    this.tousername=tousername;	}		
	public Integer getAccountid() {
		return accountid;
	}
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}
	
}
