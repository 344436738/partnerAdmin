package com.business.model;
import com.base.model.BaseModel;

public class StResumeExchangerecordModel extends BaseModel {
	

		private Integer id;//   	private Integer userid;//   	private Double exchangescore;//   	private Double rmb;//   	private String currentrule;//   	private java.util.Date exchangetime;//   	private Integer paystatus;//   	private Integer status;//   	private String cardno;//   	private String recipient;//   	private String banktype;//   
	private Integer scorerecordId;  //明细id	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getUserid() {	    return this.userid;	}	public void setUserid(Integer userid) {	    this.userid=userid;	}
	public Double getExchangescore() {
		return exchangescore;
	}
	public void setExchangescore(Double exchangescore) {
		this.exchangescore = exchangescore;
	}
	public Double getRmb() {	    return this.rmb;	}	public void setRmb(Double rmb) {	    this.rmb=rmb;	}	public String getCurrentrule() {	    return this.currentrule;	}	public void setCurrentrule(String currentrule) {	    this.currentrule=currentrule;	}	public java.util.Date getExchangetime() {	    return this.exchangetime;	}	public void setExchangetime(java.util.Date exchangetime) {	    this.exchangetime=exchangetime;	}	public Integer getPaystatus() {	    return this.paystatus;	}	public void setPaystatus(Integer paystatus) {	    this.paystatus=paystatus;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}	public String getCardno() {	    return this.cardno;	}	public void setCardno(String cardno) {	    this.cardno=cardno;	}	public String getRecipient() {	    return this.recipient;	}	public void setRecipient(String recipient) {	    this.recipient=recipient;	}	public String getBanktype() {	    return this.banktype;	}	public void setBanktype(String banktype) {	    this.banktype=banktype;	}
	public Integer getScorerecordId() {
		return scorerecordId;
	}
	public void setScorerecordId(Integer scorerecordId) {
		this.scorerecordId = scorerecordId;
	}
	
}
