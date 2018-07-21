package com.business.model;
import com.base.model.BaseModel;

public class StOpportunitiesCooperationModel extends BaseModel {
	

		private Integer id;//   	private Integer opprecommendid;//   	private Integer userid;//   	private String title;//   	private Double contractamount;//   	private Integer rewardrule;//   0是定额,1是按比例
            	private Double bonuses;//   	private String remark;//   	private java.util.Date regtime;//   	private Integer status;//   
	private String oppname;	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getUserid() {	    return this.userid;	}	public void setUserid(Integer userid) {	    this.userid=userid;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public Double getContractamount() {	    return this.contractamount;	}	public void setContractamount(Double contractamount) {	    this.contractamount=contractamount;	}	public Integer getRewardrule() {	    return this.rewardrule;	}	public void setRewardrule(Integer rewardrule) {	    this.rewardrule=rewardrule;	}	public Double getBonuses() {	    return this.bonuses;	}	public void setBonuses(Double bonuses) {	    this.bonuses=bonuses;	}	public String getRemark() {	    return this.remark;	}	public void setRemark(String remark) {	    this.remark=remark;	}	public java.util.Date getRegtime() {	    return this.regtime;	}	public void setRegtime(java.util.Date regtime) {	    this.regtime=regtime;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}
	public String getOppname() {
		return oppname;
	}
	public void setOppname(String oppname) {
		this.oppname = oppname;
	}
	public Integer getOpprecommendid() {
		return opprecommendid;
	}
	public void setOpprecommendid(Integer opprecommendid) {
		this.opprecommendid = opprecommendid;
	}
	
}
