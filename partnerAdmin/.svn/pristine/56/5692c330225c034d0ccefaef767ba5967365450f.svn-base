package com.business.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.business.bean.StOpportunities;
import com.business.bean.StResumeScorerecord;
import com.business.mapper.StOpportunitiesMapper;
import com.business.mapper.StWebUserMapper;
import com.business.model.StResumeModel;
import com.base.service.BaseService;
import com.base.utils.DateUtil;

/**
 * 
 * <br>
 * <b>功能：</b>StOpportunitiesService<br>
 */
@Service("stOpportunitiesService")
public class StOpportunitiesService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StOpportunitiesService.class);
	
	/**
	 * 商机详情
	 * @param oppid
	 * @return
	 */
	public StOpportunities queryByOppid(Integer oppid){
		return getMapper().queryByOppid(oppid);
	}
	
	/**
	 * 移动端个人我的商机
	 * @param userid
	 * @return
	 */
	public List<T> queryOpp(Integer userid){
		return getMapper().queryOpp(userid);
	}
	
	/**
	 * 将附件上传到服务器，返回值为附件存储的相对路径，存到数据库
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
			String filename = file.getOriginalFilename().replaceAll("\\s{1,}", "");
			String fileSaveName =  DateUtil.format(new java.util.Date(), "yyyyMMddHHmmss") + filename;
			
			File fileDirectory = new File(savePath);
			//路径不存在，新创建
			if (!fileDirectory.exists() && !fileDirectory.isDirectory()) {
				fileDirectory.mkdirs();
			}
			File saveFile = new File(savePath + "/"+fileSaveName);
			try {
				FileCopyUtils.copy(file.getBytes(), saveFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return path+"/"+fileSaveName;
		}
		return null;		 
		
	}
	
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
	

	@Autowired
    private StOpportunitiesMapper<T> mapper;

		
	public StOpportunitiesMapper<T> getMapper() {
		return mapper;
	}

	
	@Autowired(required=false)
    private StWebUserMapper<StResumeScorerecord> smapper;

		
	public StWebUserMapper<StResumeScorerecord> getSapper() {
		return smapper;
	}
	
	
	
}
