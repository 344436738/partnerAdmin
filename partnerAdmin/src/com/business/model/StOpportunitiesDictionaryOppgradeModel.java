package com.business.model;
import com.base.model.BaseModel;
import com.business.bean.StOpportunitiesDictionaryOppgrade;

public class StOpportunitiesDictionaryOppgradeModel extends BaseModel {
	

		private Integer id;//   	private Integer userid;//   	private Integer scaleid;//   	private double cashback;//   	private String status;//   
	
	//beans用于传递参数
	private StOpportunitiesDictionaryOppgrade[] beans;
	
	
	
		public StOpportunitiesDictionaryOppgrade[] getBeans() {
		return beans;
	}
	public void setBeans(StOpportunitiesDictionaryOppgrade[] beans) {
		this.beans = beans;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}		public double getCashback() {	    return this.cashback;	}	public void setCashback(double cashback) {	    this.cashback=cashback;	}	public String getStatus() {	    return this.status;	}	public void setStatus(String status) {	    this.status=status;	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getScaleid() {
		return scaleid;
	}
	public void setScaleid(Integer scaleid) {
		this.scaleid = scaleid;
	}

}
