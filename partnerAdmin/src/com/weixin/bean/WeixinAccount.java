package com.weixin.bean;
import com.base.bean.BaseBean;


public class WeixinAccount extends BaseBean {
	
		private Integer id;//   主键	private String accountname;//   公众帐号名称	private String accounttoken;//   公众帐号TOKEN	private String accountnumber;//   公众微信号	private String accounttype;//   公众号类型	private String accountemail;//   电子邮箱	private String accountdesc;//   公众帐号描述	private String accountaccesstoken;//   ACCESS_TOKEN	private String accountappid;//   公众帐号APPID	private String accountappsecret;//   公众帐号APPSECRET	private java.util.Date ADDTOEKNTIME;//   	private String USERNAME;//   	private String WEIXIN_ACCOUNTID;//   	private Integer userid;//   	
		public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccountname() {	    return this.accountname;	}	public void setAccountname(String accountname) {	    this.accountname=accountname;	}	public String getAccounttoken() {	    return this.accounttoken;	}	public void setAccounttoken(String accounttoken) {	    this.accounttoken=accounttoken;	}	public String getAccountnumber() {	    return this.accountnumber;	}	public void setAccountnumber(String accountnumber) {	    this.accountnumber=accountnumber;	}	public String getAccounttype() {	    return this.accounttype;	}	public void setAccounttype(String accounttype) {	    this.accounttype=accounttype;	}	public String getAccountemail() {	    return this.accountemail;	}	public void setAccountemail(String accountemail) {	    this.accountemail=accountemail;	}	public String getAccountdesc() {	    return this.accountdesc;	}	public void setAccountdesc(String accountdesc) {	    this.accountdesc=accountdesc;	}	public String getAccountaccesstoken() {	    return this.accountaccesstoken;	}	public void setAccountaccesstoken(String accountaccesstoken) {	    this.accountaccesstoken=accountaccesstoken;	}	public String getAccountappid() {	    return this.accountappid;	}	public void setAccountappid(String accountappid) {	    this.accountappid=accountappid;	}	public String getAccountappsecret() {	    return this.accountappsecret;	}	public void setAccountappsecret(String accountappsecret) {	    this.accountappsecret=accountappsecret;	}	public java.util.Date getADDTOEKNTIME() {	    return this.ADDTOEKNTIME;	}	public void setADDTOEKNTIME(java.util.Date ADDTOEKNTIME) {	    this.ADDTOEKNTIME=ADDTOEKNTIME;	}	public String getUSERNAME() {	    return this.USERNAME;	}	public void setUSERNAME(String USERNAME) {	    this.USERNAME=USERNAME;	}	public String getWEIXIN_ACCOUNTID() {	    return this.WEIXIN_ACCOUNTID;	}	public void setWEIXIN_ACCOUNTID(String WEIXIN_ACCOUNTID) {	    this.WEIXIN_ACCOUNTID=WEIXIN_ACCOUNTID;	}	public Integer getUserid() {	    return this.userid;	}	public void setUserid(Integer userid) {	    this.userid=userid;	}
}
