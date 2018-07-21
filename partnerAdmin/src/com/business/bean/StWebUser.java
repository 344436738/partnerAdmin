package com.business.bean;
import java.io.File;
import java.sql.Blob;

import org.apache.velocity.runtime.directive.Evaluate;

import com.base.bean.BaseBean;


public class StWebUser extends BaseBean {
	
	
	private String companyname;//
	private String qq;//
	private String email;//
	private String website;//
	private String wechat;//
	private String logo;//路径
	private String recommendcode;//推荐码
	
	public String getRecommendcode() {
		return recommendcode;
	}
	public void setRecommendcode(String recommendcode) {
		this.recommendcode = recommendcode;
	}
	//非数据库字段，用于显示评价信息
	private Double evaluatescore;  //评价分数
	private String cashbackid;//回款记录id
	private String opprecommendid;//推荐商机id
	private String cooid;//合作id
	private Double money;//需结金额
	private String details;//明细
	private String newpassword;//新密码
	
	//管理员查询用户信息
	private Integer uploadresumesum;
	private Integer recommendoppsum;
	private Integer recieveoppnum;
	
	//积分消费获得
	private java.util.Date gettime;//
	private String reson;//
	private Double getscore;// 
	private Double exchangescore;//
	
	public java.util.Date getGettime() {
			return gettime;
		}
		public void setGettime(java.util.Date gettime) {
			this.gettime = gettime;
		}
		public String getReson() {
			return reson;
		}
		public void setReson(String reson) {
			this.reson = reson;
		}
		public Double getGetscore() {
			return getscore;
		}
		public void setGetscore(Double getscore) {
			this.getscore = getscore;
		}
		public Double getExchangescore() {
			return exchangescore;
		}
		public void setExchangescore(Double exchangescore) {
			this.exchangescore = exchangescore;
		}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Integer getId() {
	public Double getEvaluatescore() {
	public void setEvaluatescore(Double evaluatescore) {
	
	public String getOpprecommendid() {
		return opprecommendid;
	}
	public void setOpprecommendid(String opprecommendid) {
		this.opprecommendid = opprecommendid;
	}
	public String getCooid() {
		return cooid;
	}
	public void setCooid(String cooid) {
		this.cooid = cooid;
	}
	public String getCashbackid() {
		return cashbackid;
	}
	public void setCashbackid(String cashbackid) {
		this.cashbackid = cashbackid;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getUploadresumesum() {
		return uploadresumesum;
	}
	public void setUploadresumesum(Integer uploadresumesum) {
		this.uploadresumesum = uploadresumesum;
	}
	public Integer getRecommendoppsum() {
		return recommendoppsum;
	}
	public void setRecommendoppsum(Integer recommendoppsum) {
		this.recommendoppsum = recommendoppsum;
	}
	public Integer getRecieveoppnum() {
		return recieveoppnum;
	}
	public void setRecieveoppnum(Integer recieveoppnum) {
		this.recieveoppnum = recieveoppnum;
	}
	
	
	
}