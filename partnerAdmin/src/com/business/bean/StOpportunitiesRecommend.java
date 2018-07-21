package com.business.bean;
import com.base.bean.BaseBean;


public class StOpportunitiesRecommend extends BaseBean {
	
	private Integer id;//   	private Integer oppid;//   	private Integer userid;//   	private java.util.Date recommendtime;//	private Integer checkstatus;//   	private Integer paystatus;//   	private Integer gradeid;//商机等级   	private Double paynum;//   	private Integer personevaluate;//
	private Integer companyevaluate;//  
	private String oppname;
	private String issuserid;//推荐人id
	
	//以下为非数据库字段，作为联合查询时使用
	private String customercompname;//客户公司名称
	private Integer customercompsizeid;
	private String customercompsize;
	private String customername;//客户联系人
	private String customerposition;//客户职位
	private String customertel;//客户电话
	private String customeremail;//客户邮箱
	private String customercity;//  
	private String requirementstype;//需求类型
	private String requirementsdescribe;//需求描述
	private String recommendfile;
	
	private String nickname;//推荐人
	private String recipient;
	private String phone;//推荐人电话
	
	private String levelname;//等级名称
	private String compsize;//公司规模
	private Double cashback;//返现金额
	
	public StOpportunitiesRecommend(){
	}
	public StOpportunitiesRecommend(String customercompname,
			Integer customercompsizeid, String customername,
			String customerposition, String customertel,
			String requirementstype, String requirementsdescribe,Integer companyid) {
		this.customercompname = customercompname;
		this.customercompsizeid = customercompsizeid;
		this.customername = customername;
		this.customerposition = customerposition;
		this.customertel = customertel;
		this.requirementstype = requirementstype;
		this.requirementsdescribe = requirementsdescribe;
		this.userid=companyid;
	}
	
		public String getCustomercompname() {
		return customercompname;
	}
	public void setCustomercompname(String customercompname) {
		this.customercompname = customercompname;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCustomertel() {
		return customertel;
	}
	public void setCustomertel(String customertel) {
		this.customertel = customertel;
	}
	public String getRequirementstype() {
		return requirementstype;
	}
	public void setRequirementstype(String requirementstype) {
		this.requirementstype = requirementstype;
	}
	public String getRequirementsdescribe() {
		return requirementsdescribe;
	}
	public void setRequirementsdescribe(String requirementsdescribe) {
		this.requirementsdescribe = requirementsdescribe;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLevelname() {
		return levelname;
	}
	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}
	public Double getCashback() {
		return cashback;
	}
	public void setCashback(Double cashback) {
		this.cashback = cashback;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getOppid() {	    return this.oppid;	}	public void setOppid(Integer oppid) {	    this.oppid=oppid;	}	public Integer getUserid() {	    return this.userid;	}	public void setUserid(Integer userid) {	    this.userid=userid;	}	public java.util.Date getRecommendtime() {	    return this.recommendtime;	}	public void setRecommendtime(java.util.Date recommendtime) {	    this.recommendtime=recommendtime;	}	public Integer getCheckstatus() {	    return this.checkstatus;	}	public void setCheckstatus(Integer checkstatus) {	    this.checkstatus=checkstatus;	}	public Integer getPaystatus() {	    return this.paystatus;	}	public void setPaystatus(Integer paystatus) {	    this.paystatus=paystatus;	}		
	public Integer getCustomercompsizeid() {
		return customercompsizeid;
	}
	public void setCustomercompsizeid(Integer customercompsizeid) {
		this.customercompsizeid = customercompsizeid;
	}
	public Integer getPersonevaluate() {
		return personevaluate;
	}
	public void setPersonevaluate(Integer personevaluate) {
		this.personevaluate = personevaluate;
	}
	public Integer getCompanyevaluate() {
		return companyevaluate;
	}
	public void setCompanyevaluate(Integer companyevaluate) {
		this.companyevaluate = companyevaluate;
	}
	public String getIssuserid() {
		return issuserid;
	}
	public void setIssuserid(String issuserid) {
		this.issuserid = issuserid;
	}

	public String getCustomerposition() {
		return customerposition;
	}
	public void setCustomerposition(String customerposition) {
		this.customerposition = customerposition;
	}
	public String getOppname() {
		return oppname;
	}
	public void setOppname(String oppname) {
		this.oppname = oppname;
	}
	public Integer getGradeid() {
		return gradeid;
	}
	public void setGradeid(Integer gradeid) {
		this.gradeid = gradeid;
	}
	public String getCompsize() {
		return compsize;
	}
	public void setCompsize(String compsize) {
		this.compsize = compsize;
	}
	public Double getPaynum() {
		return paynum;
	}
	public void setPaynum(Double paynum) {
		this.paynum = paynum;
	}
	public String getCustomercompsize() {
		return customercompsize;
	}
	public void setCustomercompsize(String customercompsize) {
		this.customercompsize = customercompsize;
	}
	public String getCustomeremail() {
		return customeremail;
	}
	public void setCustomeremail(String customeremail) {
		this.customeremail = customeremail;
	}
	public String getCustomercity() {
		return customercity;
	}
	public void setCustomercity(String customercity) {
		this.customercity = customercity;
	}
	public String getRecommendfile() {
		return recommendfile;
	}
	public void setRecommendfile(String recommendfile) {
		this.recommendfile = recommendfile;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
}
