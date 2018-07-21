package com.business.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.business.bean.StResume;
import com.business.bean.StResumeTrash;
import com.business.mapper.StResumeMapper;
import com.business.model.ResumeUpload.Status;
import com.business.model.StResumeModel;
import com.base.model.BaseModel;
import com.base.service.BaseService;
import com.base.utils.DateUtil;
import com.base.utils.SessionUtils;
import com.business.model.ResumeUpload;
import com.business.util.resume.*;

/**
 * 
 * <br>
 * <b>功能：</b>StResumeService<br>
 */
@Service("stResumeService")
public class StResumeService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StResumeService.class);
	

	public Map<String, Integer> resumeSum(StResumeModel model){
		return getMapper().resumeSum(model);
		
	}

	public List<T> resumeSearch(StResumeModel model){
		Integer rowCount = getMapper().queryStResumeCount(model);
		model.getPager().setRowCount(rowCount);
		return getMapper().resumeSearch(model);
	}
	
	public int getResumByTel(Map<String, Object> map) {
		return  mapper.getResumByTel(map);
	}
	
	public void addResume(StResume stResume)  throws Exception{
		mapper.addResume(stResume);
	}
	public void addResumeTrash(StResume stResume)  throws Exception{
		mapper.addResumeTrash(stResume);
	}
	
	@Autowired
    private StResumeMapper<T> mapper;

		
	public StResumeMapper<T> getMapper() {
		return mapper;
	}
	
	/**
	 * 上传并分析简历入库
	 * @param filePath
	 * @param myset
	 * @return
	 */
	public ResumeUpload uploadResume(HttpServletRequest request,String filePath, MultipartFile myfile){
		ResumeUpload re=new ResumeUpload();		
		//设置文件保存的路径
		StringBuffer dataPath = new StringBuffer();
		dataPath.append(filePath);
		String savePath = request.getSession().getServletContext().getRealPath("/" + dataPath.toString());
		
		String name = "";
		if (!myfile.isEmpty()) {
						
			//文件名设置为日期+上传文件名称的方式
			name = myfile.getOriginalFilename().replaceAll("\\s{1,}", "");
			if (name == null || name.trim().equals("")) {
			}
			String fileRealName = DateUtil.format(new java.util.Date(), "yyyyMMddHHmmss") + name;
			
			try {

				//step 1，将file解析成map
				Map<String,Object> parsedResume= ResumePro1.txtDis_Map(myfile);
				if(parsedResume==null){//如果未能解析出任何数据
					
					re.setStatus(Status.ERROR);
				}
				//step 2，判断数据库中是否已存在此记录
				//此处需要改成自己的判断 todo
				
				String tel=(String) parsedResume.get("tel");  //获取解析好的数据的tel字段，即简历的手机号码
				
				if (!tel.equals("")) {
					//从数据库查询该手机号码出现的次数，0表示不存在，大于0表示已结存在
					int tellist = this.getResumByTel(parsedResume);

					//step 3，将数据和文件保存至服务器
					//如果号码不存在把解析出来的数据插入表中
					StResume srbean = new StResume();
					if (tellist==0) {
						
						//文件保存到服务器 /upload/resume文件夹下
						savePath +="/resume";
						File resumeDirectory = new File(savePath);
						//路径不存在，新创建
						if (!resumeDirectory.exists() && !resumeDirectory.isDirectory()) {
							resumeDirectory.mkdirs();
						}
						File saveFile = new File(savePath + "/"+fileRealName);
						FileCopyUtils.copy(myfile.getBytes(), saveFile);
						
						//将解析出来的数据载入到简历表的Bean中
						loadStResume(request,saveFile,srbean,parsedResume);
						//插入数据库
						this.addResume(srbean);
						re.setStatus(Status.SUCCESS);
						
					}else{//如果号码存在，把解析出来的数据插入到垃圾数据中
						//文件保存到服务器 /upload/resumetrash 文件夹下
						savePath +="/resumetrash";
						File resumetrashDirectory = new File(savePath);
						//路径不存在，新创建
						if (!resumetrashDirectory.exists() && !resumetrashDirectory.isDirectory()) {
							resumetrashDirectory.mkdirs();
						}
						File saveFile = new File(savePath + "/"+fileRealName);
						FileCopyUtils.copy(myfile.getBytes(), saveFile);
						
						//将解析出来的数据载入到简历表的Bean中
						loadStResume(request,saveFile,srbean,parsedResume);
						//插入数据库
						this.addResumeTrash(srbean);
						
						re.setStatus(Status.EXISYENCE);
					}

				}
				else{
					re.setStatus(Status.ERROR);
				}
			} catch (Exception e) {
				log.error("简历上传失败，失败原因:"+e.getMessage()+","+new Date());
				//异常    状态设为错误
				re.setStatus(Status.ERROR);
				e.printStackTrace();
//				
			}
		}
		return re; 
	}

	//将解析出来的数据载入到Bean中
	public void loadStResume(HttpServletRequest request,File saveFile,StResume stresume,Map<String, Object> map){
		try{			
			stresume.setFullname((String) map.get("name"));
			stresume.setSex(Opinion.sexOpin((String) map.get("sex")));
			String age = (String) map.get("age"); //map 中的年龄格式为 yyyy-MM 
			if (age.indexOf("-") > -1 && age.split("-").length == 2) {
				stresume.setAge(Integer.valueOf(age.split("-")[0]).intValue());	
				
			}

			stresume.setHighestdegree((String) map.get("highestdegree")); // 最高学历
			List<Map<String, String>> listEducation = (List<Map<String, String>>) map.get("教育经历"); // 毕业院校
			if (listEducation != null && listEducation.size() > 0) {
				stresume.setEducation(listEducation.get(0).get("学校名称"));
			}
			if (stresume.getEducation() == null || stresume.getEducation().equals("")) {
				stresume.setEducation((String) map.get("education"));
			}
			// 目前从事地区
			stresume.setCurrentlocation(Opinion.areaOpin2((String) map.get("currentlocation")));
			stresume.setTel((String) map.get("tel")); // 电话
			stresume.setEmail((String) map.get("email")); // 邮箱
			stresume.setWorktime(((String) map.get("worktime")).replace("年", "")); // 工作年限
			stresume.setCompany(((String) map.get("company"))); // 当前公司
			stresume.setPosition((String) map.get("position")); // 当前职位
			stresume.setNowmoney(((String) map.get("nowmoney"))); // 当前年薪
			stresume.setKeyword(((String) map.get("keyword"))); // 专业领域
			String keymessage = (String) map.get("keywordmessage"); // 专业领域关键词
			if (keymessage.indexOf("自我评价") > -1) {
				keymessage = keymessage.substring(0, keymessage.indexOf("自我评价"));
			}
			if (keymessage.indexOf("工作经历") > -1) {
				keymessage = keymessage.substring(0, keymessage.indexOf("工作经历"));
			}
			if (keymessage.indexOf("求职意向") > -1) {
				keymessage = keymessage.substring(0, keymessage.indexOf("求职意向"));
			}
			if (keymessage.length() > 1000) {
				keymessage = keymessage.substring(0, 1000);
			}
			stresume.setKeywordmessage(keymessage);

			stresume.setCurindustry((String) map.get("curindustry")); // 目前从事行业
			stresume.setUploadtime(DateUtil.getDateByString("")); // 上传时间
			
			// 当前用户的id作为上传人id
			stresume.setUploadby(SessionUtils.getWebUserId(request));	
			
			//文件保存地址    
			String savePath = saveFile.getPath();  //获取文件的绝对路径
			//获取相对路径
			if (savePath.indexOf("upload") > -1) {
				savePath = savePath.substring(savePath.indexOf("upload"),
						savePath.length());
			} else {
				savePath = savePath.substring(3, savePath.length());
			}
			savePath = savePath.replaceAll("\\\\", "/"); 
			stresume.setUrl(savePath);    //得到的相对路径为  upload/xxx.doc
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}






