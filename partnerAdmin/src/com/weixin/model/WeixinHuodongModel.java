package com.weixin.model;
import com.base.model.BaseModel;

public class WeixinHuodongModel extends BaseModel {
	

		private Integer id;//   键主
	private String title;//   活动名称
	private String description;//   活动描述
	private String priceone;//   一等奖奖品
	private Integer onetotal;//   一等奖数量
	private String pricetwo;//   二等奖奖品
	private Integer twototal;//   二等奖数量
	private String pricethree;//   三等奖奖品
	private Integer threetotal;//   三等奖数量
	private java.util.Date starttime;//   开始时间
	private java.util.Date endtime;//   结束时间
	private String type;//   活动类型
	private String gl;//   
	private String count;//   
	private int accountid;//    
	private String glone;
	private String gltwo;
	private String glthree;
	
	
	
	
	public String getGlone() {
		return glone;
	}
	public void setGlone(String glone) {
		this.glone = glone;
	}
	public String getGltwo() {
		return gltwo;
	}
	public void setGltwo(String gltwo) {
		this.gltwo = gltwo;
	}
	public String getGlthree() {
		return glthree;
	}
	public void setGlthree(String glthree) {
		this.glthree = glthree;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPriceone() {
		return priceone;
	}
	public void setPriceone(String priceone) {
		this.priceone = priceone;
	}
	public Integer getOnetotal() {
		return onetotal;
	}
	public void setOnetotal(Integer onetotal) {
		this.onetotal = onetotal;
	}
	public String getPricetwo() {
		return pricetwo;
	}
	public void setPricetwo(String pricetwo) {
		this.pricetwo = pricetwo;
	}
	public Integer getTwototal() {
		return twototal;
	}
	public void setTwototal(Integer twototal) {
		this.twototal = twototal;
	}
	public String getPricethree() {
		return pricethree;
	}
	public void setPricethree(String pricethree) {
		this.pricethree = pricethree;
	}
	public Integer getThreetotal() {
		return threetotal;
	}
	public void setThreetotal(Integer threetotal) {
		this.threetotal = threetotal;
	}
	public java.util.Date getStarttime() {
		return starttime;
	}
	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}
	public java.util.Date getEndtime() {
		return endtime;
	}
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGl() {
		return gl;
	}
	public void setGl(String gl) {
		this.gl = gl;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}	
}
