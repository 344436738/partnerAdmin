package com.business.model;
import java.util.List;

import com.base.model.BaseModel;
import com.business.bean.StDictionary;

public class StDictionaryModel extends BaseModel {
	

		private Integer id;//   	private String dkey;//   	private String dvalue;//   	private String desc;//   	private Integer status;//   
	
	private List<StDictionary> bean;	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getDkey() {	    return this.dkey;	}	public void setDkey(String dkey) {	    this.dkey=dkey;	}	public String getDvalue() {	    return this.dvalue;	}	public void setDvalue(String dvalue) {	    this.dvalue=dvalue;	}	public String getDesc() {	    return this.desc;	}	public void setDesc(String desc) {	    this.desc=desc;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}
	public List<StDictionary> getBean() {
		return bean;
	}
	public void setBean(List<StDictionary> bean) {
		this.bean = bean;
	}
	
}
