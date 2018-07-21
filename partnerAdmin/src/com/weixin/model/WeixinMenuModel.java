package com.weixin.model;
import com.base.model.BaseModel;

public class WeixinMenuModel extends BaseModel {
	

		private Integer id;//   	private String menukey;//   	private String msgtype;//   消息类型	private String name;//   菜单名称	private Integer sorting;//   排序	private Integer templateid;//   模板ID	private String type;//   点击事件类型	private String url;//   请求地址	private Integer fatherid;//   父节点	private Integer accountid;//   用户ID	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getMenukey() {	    return this.menukey;	}	public void setMenukey(String menukey) {	    this.menukey=menukey;	}	public String getMsgtype() {	    return this.msgtype;	}	public void setMsgtype(String msgtype) {	    this.msgtype=msgtype;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public Integer getSorting() {	    return this.sorting;	}	public void setSorting(Integer sorting) {	    this.sorting=sorting;	}	public Integer getTemplateid() {	    return this.templateid;	}	public void setTemplateid(Integer templateid) {	    this.templateid=templateid;	}	public String getType() {	    return this.type;	}	public void setType(String type) {	    this.type=type;	}	public String getUrl() {	    return this.url;	}	public void setUrl(String url) {	    this.url=url;	}	public Integer getFatherid() {	    return this.fatherid;	}	public void setFatherid(Integer fatherid) {	    this.fatherid=fatherid;	}	public Integer getAccountid() {	    return this.accountid;	}	public void setAccountid(Integer accountid) {	    this.accountid=accountid;	}
	
}
