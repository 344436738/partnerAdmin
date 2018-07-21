package com.business.model;

import com.base.model.*;

/**
 * 对象功能:BPM_RESUM_USER Model对象 开发公司:腾信软创 开发人员:葛齐林 创建时间:2014-06-26 16:14:51
 */
public class ResumeUpload extends BaseModel {
	private long id;// id
	private long professoerid;// 顾问ID
	private long resumeid;// 简历ID
	private String resumename;// 简历名称
	private String resumefilename;// 上传文件的新名称
	private java.sql.Timestamp uploadtime;// 上传时间
	private String batchid;// 批次号
	private String msgcode;// 上传失败错误码
	private String msgtext;// 上传失败原因
	private Status status;// 上传状态
	
	private int successUploaded;//成功上传数
	private int faildUploaded;//上传失败数
	private int alreadyExists;//库中已存在简历数
	
	
	public enum Status{
		EXISYENCE,
		SUCCESS,
		ERROR
	}
	
	
	public int getSuccessUploaded() {
		return successUploaded;
	}
	public void setSuccessUploaded(int successUploaded) {
		this.successUploaded = successUploaded;
	}
	public int getFaildUploaded() {
		return faildUploaded;
	}
	public void setFaildUploaded(int faildUploaded) {
		this.faildUploaded = faildUploaded;
	}
	public int getAlreadyExists() {
		return alreadyExists;
	}
	public void setAlreadyExists(int alreadyExists) {
		this.alreadyExists = alreadyExists;
	}
	/**
	 * 
	 * @param id        		id		
	 * @param resumeid			简历ID
	 * @param status			上传状态
	 * @param resumefilename	上传文件的新名称
	 */
	public void setParamValue(long id, long resumeid, Status status,String resumefilename) {
		this.id = id;
		this.resumeid = resumeid;
		this.status = status;
		this.resumefilename = resumefilename;
	}	
	/**
	 * 
	 * @param resumeid		简历ID
	 * @param status		上传状态
	 * @param msgcode		上传失败错误码
	 * @param msgtext		上传失败说明
	 */
	public void setParamCode(long resumeid, Status status,String msgcode,String msgtext) {
		this.resumeid = resumeid;
		this.status = status;
		this.msgcode = msgcode;
		this.msgtext = msgtext;
	}
	public String getResumefilename() {
		return resumefilename;
	}

	public void setResumefilename(String resumefilename) {
		this.resumefilename = resumefilename;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProfessoerid() {
		return this.professoerid;
	}

	public void setProfessoerid(long professoerid) {
		this.professoerid = professoerid;
	}

	public long getResumeid() {
		return this.resumeid;
	}

	public void setResumeid(long resumeid) {
		this.resumeid = resumeid;
	}

	public String getResumename() {
		return this.resumename;
	}

	public void setResumename(String resumename) {
		this.resumename = resumename;
	}

	public java.sql.Timestamp getUploadtime() {
		return this.uploadtime;
	}

	public void setUploadtime(java.sql.Timestamp uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getBatchid() {
		return this.batchid;
	}

	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

	public String getMsgcode() {
		return this.msgcode;
	}

	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}

	public String getMsgtext() {
		return this.msgtext;
	}

	public void setMsgtext(String msgtext) {
		this.msgtext = msgtext;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
