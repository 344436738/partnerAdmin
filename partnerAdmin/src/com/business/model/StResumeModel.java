package com.business.model;
import com.base.model.BaseModel;

public class StResumeModel extends BaseModel {
	

		private Integer id;//   	private String fullname;//   	private String tel;//   	private Integer sex;//   	private Integer age;//   	private String highestdegree;//   	private String education;//   	private String currentlocation;//   	private String email;//   	private String worktime;//   	private String company;//   	private String position;//   	private String nowmoney;//   	private String keyword;//   	private String keywordmessage;//   	private String curindustry;//   	private java.util.Date uploadtime;//   	private String intentionjob;//   	private Integer isworking;//   	private Integer marryed;//   	private Integer uploadby;//   	private String url;//   	private Integer status;//   是否审核通过 0是刚上传            1是审核通过         9是被过滤程序过滤
	

	//以下为非数据库字段，用于简历查询
	private String searchcondition;
	
		public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getFullname() {	    return this.fullname;	}	public void setFullname(String fullname) {	    this.fullname=fullname;	}	public String getTel() {	    return this.tel;	}	public void setTel(String tel) {	    this.tel=tel;	}	public Integer getSex() {	    return this.sex;	}	public void setSex(Integer sex) {	    this.sex=sex;	}	public Integer getAge() {	    return this.age;	}	public void setAge(Integer age) {	    this.age=age;	}	public String getHighestdegree() {	    return this.highestdegree;	}	public void setHighestdegree(String highestdegree) {	    this.highestdegree=highestdegree;	}	public String getEducation() {	    return this.education;	}	public void setEducation(String education) {	    this.education=education;	}	public String getCurrentlocation() {	    return this.currentlocation;	}	public void setCurrentlocation(String currentlocation) {	    this.currentlocation=currentlocation;	}	public String getEmail() {	    return this.email;	}	public void setEmail(String email) {	    this.email=email;	}	public String getWorktime() {	    return this.worktime;	}	public void setWorktime(String worktime) {	    this.worktime=worktime;	}	public String getCompany() {	    return this.company;	}	public void setCompany(String company) {	    this.company=company;	}	public String getPosition() {	    return this.position;	}	public void setPosition(String position) {	    this.position=position;	}	public String getNowmoney() {	    return this.nowmoney;	}	public void setNowmoney(String nowmoney) {	    this.nowmoney=nowmoney;	}	public String getKeyword() {	    return this.keyword;	}	public void setKeyword(String keyword) {	    this.keyword=keyword;	}	public String getKeywordmessage() {	    return this.keywordmessage;	}	public void setKeywordmessage(String keywordmessage) {	    this.keywordmessage=keywordmessage;	}	public String getCurindustry() {	    return this.curindustry;	}	public void setCurindustry(String curindustry) {	    this.curindustry=curindustry;	}	public java.util.Date getUploadtime() {	    return this.uploadtime;	}	public void setUploadtime(java.util.Date uploadtime) {	    this.uploadtime=uploadtime;	}	public String getIntentionjob() {	    return this.intentionjob;	}	public void setIntentionjob(String intentionjob) {	    this.intentionjob=intentionjob;	}	public Integer getIsworking() {	    return this.isworking;	}	public void setIsworking(Integer isworking) {	    this.isworking=isworking;	}	public Integer getMarryed() {	    return this.marryed;	}	public void setMarryed(Integer marryed) {	    this.marryed=marryed;	}	public Integer getUploadby() {	    return this.uploadby;	}	public void setUploadby(Integer uploadby) {	    this.uploadby=uploadby;	}	public String getUrl() {	    return this.url;	}	public void setUrl(String url) {	    this.url=url;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}
	public String getSearchcondition() {
		return searchcondition;
	}
	public void setSearchcondition(String searchcondition) {
		this.searchcondition = searchcondition;
	}
	
}
