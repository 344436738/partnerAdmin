package com.business.bean;
import com.base.bean.BaseBean;


public class StOpportunitiesDictionaryOppgrade extends BaseBean {
	
	
	
	public StOpportunitiesDictionaryOppgrade() {
		
	}
	
public StOpportunitiesDictionaryOppgrade(Integer id,Integer userid,
		Integer scaleid,double cashback,String status) {
		this.id=id;
		this.userid=userid;
		this.scaleid=scaleid;
		this.cashback=cashback;
		this.status=status;
	}
	
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