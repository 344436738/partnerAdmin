package com.business.model;
import com.base.model.BaseModel;

public class StOpportunitiesDictionaryScaleModel extends BaseModel {
	

	
	
	//非数据库字段
	private Integer scaleid;
	private double cashback;
	
	
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