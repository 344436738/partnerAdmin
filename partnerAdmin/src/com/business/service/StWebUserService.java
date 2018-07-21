package com.business.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.business.bean.StResumeExchangerecord;
import com.business.bean.StResumeScorerecord;
import com.business.bean.StWebUser;
import com.business.mapper.StResumeScorerecordMapper;
import com.business.mapper.StWebUserMapper;
import com.business.model.StWebUserModel;
import com.base.service.BaseService;
import com.base.utils.DateUtil;
import com.thoughtworks.xstream.core.BaseException;


/**
 * 
 * <br>
 * <b>功能：</b>StWebUserService<br>
 */
@Service("stWebUserService")
public class StWebUserService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StWebUserService.class);
	
	public void updateScore(Object userid,Object score,StResumeExchangerecord bean) throws Exception {
		getMapper().updateScore(userid,score);
		StResumeScorerecord cord=new StResumeScorerecord();
		cord.setUserid(bean.getUserid());
		cord.setGetscore(-(bean.getExchangescore()));
		cord.setReson("兑换");
		cord.setStatus(1);
		cord.setGettime(new Date());
		String str=" userid = "+bean.getUserid().toString();
		double tol=getTolmappeer().queryDouble(str);
		cord.setTotalscore(tol-bean.getExchangescore());
		getSmapper().add(cord);
	}
	
	@Autowired
    private StResumeScorerecordMapper<StResumeScorerecord> smapper;
	
	public StResumeScorerecordMapper<StResumeScorerecord> getSmapper(){
		return smapper;
	}	
	public List<T> queryByPhone(String phone){
		return getMapper().queryByPhone(phone);
	}

	public StWebUser queryForLogin(StWebUserModel model){
		return getMapper().queryForLogin(model);
	}
	
	public List<T> queryForPayoff(StWebUserModel model){
		return getMapper().queryForPayoff(model);
	}
	
	public StWebUser queryRCid(StWebUserModel model){
		return getMapper().queryRCid(model);
	}
	
	public StWebUser queryForRegister(StWebUserModel model){
		return getMapper().queryForRegister(model);
	}
	
	public List<T> queryForAdmin(StWebUserModel model){
		
		Integer rowCount = getMapper().queryForAdminCount(model);
		model.getPager().setRowCount(rowCount);
		
		return getMapper().queryForAdmin(model);
	}
	
	public List<T> queryForScore(StWebUserModel model){
		return getMapper().queryForScore(model);
	}
	
	@Autowired
    private StWebUserMapper<T> mapper;

		
	public StWebUserMapper<T> getMapper() {
		return mapper;
	}
	
	
	@Autowired
    private StResumeScorerecordMapper<T> tolmapper;
	public StResumeScorerecordMapper<T> getTolmappeer(){
		return tolmapper;
	}
	
	/**
	 * 下载并保存头像
	 * @param request
	 * @param path
	 * @param file
	 * @return
	 */
	public String uploadFile(HttpServletRequest request,String path,MultipartFile file){
		StringBuffer dataPath = new StringBuffer();
		dataPath.append(path);
		String savePath = request.getSession().getServletContext().getRealPath("/" + dataPath.toString());
		
		if(file != null){
			String fileName = file.getOriginalFilename(); 
			String extName =  fileName.substring(fileName.lastIndexOf("."));
			if (!extName.equals("jpg") || !extName.equals("png")){
				return new String("错误的图片格式");
			}
			String fileSaveName = System.currentTimeMillis() + extName;
			
			File fileDirectory = new File(savePath);
			//路径不存在，新创建
			if (!fileDirectory.exists() && !fileDirectory.isDirectory()) {
				fileDirectory.mkdirs();
			}
			//File saveFile = new File(savePath + "/"+fileSaveName);
			try {
				//FileCopyUtils.copy(file.getBytes(), saveFile);
				FileUtils.writeByteArrayToFile(new File(fileDirectory,fileSaveName), file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return path+"/"+fileSaveName;
		}
		return null;		 
		
	}
	
	/**
	 * 图片下载
	 * @param response
	 * @param path
	 */
	public void downloadFile(HttpServletResponse response,String path){
		OutputStream out = null;
		try { 
			response.reset(); 
			response.setContentType("application/octet-stream; charset=utf-8"); 
			File file= new File(path);
			response.setHeader("Content-Disposition", "attachment; filename=" +  URLEncoder.encode(file.getName(), "UTF-8")); 
			out = response.getOutputStream(); 
			out.write(FileUtils.readFileToByteArray(file)); 
			out.flush(); 
			}catch (IOException e) { 
				e.printStackTrace(); 
			} finally { 
				if (out != null) { 
					try { out.close(); 
					} catch (IOException e) {
						e.printStackTrace(); 
					} 
				} 
			}
	}

}
