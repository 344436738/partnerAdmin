package com.business.model;
import com.base.model.BaseModel;

public class StOpportunitiesIncomeModel extends BaseModel {
	

		private Integer id;//   	private Integer userid;//   	private Double income;//   	private Integer objid;//   	private Integer type;//   1.商机确认收入2.合作汇款收入3.推荐人收入	private Integer status;//   	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getUserid() {	    return this.userid;	}	public void setUserid(Integer userid) {	    this.userid=userid;	}	public Double getIncome() {	    return this.income;	}	public void setIncome(Double income) {	    this.income=income;	}	public Integer getObjid() {	    return this.objid;	}	public void setObjid(Integer objid) {	    this.objid=objid;	}	public Integer getType() {	    return this.type;	}	public void setType(Integer type) {	    this.type=type;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}
	
}
