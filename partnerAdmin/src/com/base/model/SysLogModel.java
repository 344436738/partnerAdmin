package com.base.model;


public class SysLogModel extends BaseModel {

	private Integer id; //
	private Integer userId; // 操作人
	private java.sql.Timestamp operationTime; // 操作时间
	private String operation; // 操作
	private String url; // 操作路径
	private String details; // 操作详情
	private String moduleName;//操作模块
	
	private String maxOperationTime;
	private String minOperationTime;
	
	private java.sql.Timestamp loginTime;
	private String loginCount;
	
	
	public java.sql.Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(java.sql.Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	

	public java.sql.Timestamp getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(java.sql.Timestamp operationTime) {
		this.operationTime = operationTime;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getMaxOperationTime() {
		return maxOperationTime;
	}

	public void setMaxOperationTime(String maxOperationTime) {
		this.maxOperationTime = maxOperationTime;
	}

	public String getMinOperationTime() {
		return minOperationTime;
	}

	public void setMinOperationTime(String minOperationTime) {
		this.minOperationTime = minOperationTime;
	}
	
}
