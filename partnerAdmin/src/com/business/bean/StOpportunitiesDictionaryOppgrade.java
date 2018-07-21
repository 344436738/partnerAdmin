package com.business.bean;
import com.base.bean.BaseBean;


public class StOpportunitiesDictionaryOppgrade extends BaseBean {
	
		private Integer id;//   	private Integer userid;//      	private Integer scaleid;//   	private double cashback;//   	private String status;//   
	
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
