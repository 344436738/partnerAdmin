package com.business.bean;
import com.base.bean.BaseBean;


public class StResume extends BaseBean {
	
	private Integer id;//   
	
	//非数据库字段，返回上传人姓名
	private String uploader;
	
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
}