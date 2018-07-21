package com.business.model;
import com.base.model.BaseModel;

public class StOpportunitiesDictionaryScaleModel extends BaseModel {
	

		private Integer id;//   	private String levelname;//   	private String compsize;//   
	
	//非数据库字段
	private Integer scaleid;
	private double cashback;
	
		public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getLevelname() {	    return this.levelname;	}	public void setLevelname(String levelname) {	    this.levelname=levelname;	}	public String getCompsize() {	    return this.compsize;	}	public void setCompsize(String compsize) {	    this.compsize=compsize;	}
	public double getCashback() {
		return cashback;
	}
	public void setCashback(double cashback) {
		this.cashback = cashback;
	}
	public Integer getScaleid() {
		return scaleid;
	}
	public void setScaleid(Integer scaleid) {
		this.scaleid = scaleid;
	}
	
	
}
