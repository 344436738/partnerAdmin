package com.business.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




































import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;




































import sun.rmi.transport.proxy.HttpReceiveSocket;

import com.business.bean.StDictionary;
import com.business.bean.StResume;
import com.business.bean.StResumeActivities;
import com.business.bean.StResumeExchangerecord;
import com.business.bean.StResumeScorerecord;
import com.business.bean.StWebUser;
import com.business.mapper.StWebUserMapper;
import com.business.model.StDictionaryModel;
import com.business.model.StResumeExchangerecordModel;
import com.business.model.StResumeModel;
import com.business.model.StWebUserModel;
import com.business.model.UserDetailedModel;
import com.business.service.StDictionaryService;
import com.business.service.StOpportunitiesService;
import com.business.service.StResumeActivitiesService;
import com.business.service.StResumeExchangerecordService;
import com.business.service.StResumeScorerecordService;
import com.business.service.StResumeService;
import com.business.service.StResumeTrashService;
import com.business.service.StWebUserService;
import com.base.utils.HtmlUtil;
import com.business.bean.StResume;
import com.business.model.StResumeModel;
import com.base.action.BaseAction;
import com.base.annotation.Auth;
 
@Controller
@RequestMapping("/admin") 
public class StAdminAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(StAdminAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private StResumeService<StResume> stResumeService; 
	
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private StResumeExchangerecordService<StResumeExchangerecord> stResumeExchangerecordService; 
	
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private StWebUserService<StWebUser> stWebUserService; 
	
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private StDictionaryService<StDictionary> stDictionaryService;
	
	
	
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private StResumeScorerecordService<UserDetailedModel> stResumeScorerecordService;
	
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private StOpportunitiesService<StResumeScorerecord> stOpportunitiesService;
	

	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private StResumeActivitiesService<StResumeActivities> stResumeActivitiesService;
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 * **/
	@RequestMapping("/stResumeScorere")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView StResumeScorere(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		
		return forword("adminCenter/stResumeScorere", context);
	}
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 * **/
	@RequestMapping("/stResumeScorerePage")
	@Auth(verifyLogin=false,verifyURL=false)
	public void StResumeScorerePage(StWebUserModel model,HttpServletResponse response) throws Exception{
		Map<String, Object> json=new HashMap<String, Object>();
			List<UserDetailedModel>  datalist=stResumeScorerecordService.queryOrderBy(model);
			
			
			json.put("total",model.getPager().getRowCount());
			json.put(SUCCESS, true);
			json.put("rows", datalist);
			HtmlUtil.writerJson(response, json);
			
	}
	
	
	
	

	/**
	 * @param request
	 * @return
	 * @throws Exception
	 * **/
	@RequestMapping("/resumeActivity")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView resumeActivity(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		
		return forword("adminCenter/ResumeActivity", context);
	}
	
	
	
	
	

	@RequestMapping("saveUploadResumeActivity")
	@Auth(verifyLogin=false,verifyURL=false)
	public @ResponseBody Object saveUploadResumeActivity(StResumeActivities bean, 
			@RequestParam MultipartFile homeimgUrl,
			HttpServletRequest request,String t1,
			String t2,String t3,String t4) throws ParseException{
		JSONObject result = new JSONObject();
				String file=FilenameUtils.getExtension(homeimgUrl.getOriginalFilename());
				SimpleDateFormat simple=new SimpleDateFormat("yyyyMMddHHmmss"); 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");  
			    Date signupend = sdf.parse(bean.getT1()); 
			    Date uploadstart = sdf.parse(bean.getT2());
			    Date uploadend = sdf.parse(bean.getT3());
			    Date downloadstart = sdf.parse(bean.getT4());
			    Date startdate = sdf.parse(bean.getT5());
			    Date enddate = sdf.parse(bean.getT6());
			    bean.setSignupend(signupend);
			    bean.setUploadend(uploadend);
			    bean.setUploadstart(uploadstart);
			    bean.setDownloadstart(downloadstart);
			    bean.setStartdate(startdate);
			    bean.setEnddate(enddate);
				String FileName=simple.format(new Date())+UUID.randomUUID().toString().replace("-","")+"."+file;
				String path1=request.getRealPath("/upload");
		System.out.print(bean.toString()+"、、、、、、、、、、、、、、、、、、、、、、、、、、、、、"+path1+"///"+t1);
		try {
			homeimgUrl.transferTo(new File(path1+"/"+FileName));
			bean.setHomeimg(path1+"/"+FileName);
			stResumeActivitiesService.add(bean);
			result.put("msg", "保存成功");
			}  catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "保存失败");
		}
		
		return result;
		
	}
	
	
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 * **/
	@RequestMapping("integralRule")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView integralRule(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		return forword("adminCenter/integralRule", context);
	}
	
	
	
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 * **/
	@RequestMapping("scoringRules")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView scoringRules(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		return forword("adminCenter/scoringRules", context);
	}

	
	
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 * **/
	@RequestMapping("downloadresumeUploadScore")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView downloadresumeUploadScore(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		return forword("adminCenter/downloadresumeUploadScore", context);
	}
	
	

	@RequestMapping("saveUploadScore")
	@Auth(verifyLogin=false,verifyURL=false)
	public void saveUploadScore(StDictionary bean,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(bean.getId()!=null){
			stDictionaryService.updateBySelective(bean);
			sendSuccessMessage(response, "修改成功");
		}else{
			stDictionaryService.add(bean);
			sendSuccessMessage(response, "设置成功");
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/resumeManage")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView resumeManage(HttpServletRequest request,String id) throws Exception{
		Map<String, Object> context=getRootMap();
		request.setAttribute("ssid",id);
		return forword("adminCenter/resumeManage", context);
	}
	
	@RequestMapping("/resumeList")
	@Auth(verifyLogin=false,verifyURL=false)
	public void resumeList(StResumeModel model,HttpServletResponse response) throws Exception{
		Map<String, Object> jsonMap=new HashMap<String, Object>();
		List<StResume> dataList=stResumeService.resumeSearch(model);
		
		jsonMap.put("total",model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response,jsonMap);
	}
	
	/**
	 * 
	 * @param beans
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("resumeCheck")
	@Auth(verifyLogin=false,verifyURL=false)
	public void resumeCheck(@RequestBody List<StResume> beans,HttpServletResponse response) throws Exception{
		
		for(StResume bean:beans){
			if(bean.getStatus()==0){
				bean.setStatus(1);
				stResumeService.updateBySelective(bean);
				StDictionaryModel model=new StDictionaryModel();
				model.setDkey("简历上传获取积分");
				StDictionary datalist = stDictionaryService.queryByDkey(model);
				String dat=	datalist.getDvalue();
				StResume st=new StResume();
				st.setId(bean.getId());
				StResume	res=stResumeService.queryById(st);
				StResumeScorerecord cord=new StResumeScorerecord();
				cord.setUserid(res.getUploadby());
				cord.setGetscore(Double.parseDouble(dat));
				cord.setReson("上传简历");
				cord.setStatus(2);
				cord.setGettime(new Date());
				String str=" userid= "+res.getUploadby().toString();
				double tol=stResumeScorerecordService.queryDouble(str);
				cord.setTotalscore(tol+Double.parseDouble(dat));
				
				stResumeScorerecordService.insert(cord);
			
				
			}else{
				sendFailureMessage(response, "已经审核的简历不能重复操作");
			}	
		}
		sendSuccessMessage(response, "审核通过成功");
	}
	
	@RequestMapping("resumeCheckNot")
	@Auth(verifyLogin=false,verifyURL=false)
	public void resumeCheckNot(@RequestBody List<StResume> beans,HttpServletResponse response) throws Exception{
		
		for(StResume bean:beans){
			if(bean.getStatus()==0){
				bean.setStatus(2);
				stResumeService.updateBySelective(bean);
			}else{
				sendFailureMessage(response, "已经审核的简历不能重复操作");
			}	
		}
		sendSuccessMessage(response, "审核不通过成功");
	}
	
	@RequestMapping("scoreExchange")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView scoreExchange(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		return forword("adminCenter/scoreExchange", context);
	}
	
	@RequestMapping("scoreExchangeList")
	@Auth(verifyLogin=false,verifyURL=false)
	public void scoreExchangeList(StResumeExchangerecordModel model,HttpServletResponse response) throws Exception{
		Map<String, Object> json= new HashMap<String, Object>();
		List<StResumeExchangerecord> datalist = stResumeExchangerecordService.scoreExchangeList(model);
		json.put("rows", datalist);
		json.put("total",model.getPager().getRowCount());
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("scoreExchangeHandle")
	@Auth(verifyLogin=false,verifyURL=false)
	public void scoreExchangeHandle(StResumeExchangerecord bean,HttpServletResponse response) throws Exception{
		
		if(bean.getPaystatus()==0){
			bean.setPaystatus(1);
			stResumeExchangerecordService.updateBySelective(bean);
			stWebUserService.updateScore(bean.getUserid(), bean.getExchangescore(),bean);
			
		}else{
			sendFailureMessage(response, "您已处理");
		}	
		sendSuccessMessage(response, "处理成功");
	}
	
	@RequestMapping("scoreExchangeRule")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView scoreExchangeRule(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		return forword("adminCenter/scoreExchangeRule", context);
	}
	
	@RequestMapping("scoreExchangeRuleList")
	@Auth(verifyLogin=false,verifyURL=false)
	public void scoreExchangeRuleList(StDictionaryModel model,HttpServletResponse response) throws Exception{
		Map<String, Object> json= new HashMap<String, Object>();
		StDictionary datalist = stDictionaryService.queryByDkey(model);
		if(datalist!=null){
			json.put(SUCCESS, true);
		}
		
		json.put("rows", datalist);
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("saveScoreRule")
	@Auth(verifyLogin=false,verifyURL=false)
	public void saveScoreRule(StDictionary bean,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(bean.getId()!=null){
			stDictionaryService.updateBySelective(bean);
			sendSuccessMessage(response, "修改成功");
		}else{
			stDictionaryService.add(bean);
			sendSuccessMessage(response, "设置成功");
		}
	}
	
	@RequestMapping("resumeUploadScore")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView resumeUploadScore(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		return forword("adminCenter/resumeUploadScore", context);
	}
	
	@RequestMapping("uploadScoreRuleList")
	@Auth(verifyLogin=false,verifyURL=false)
	public void uploadScoreRuleList(StDictionaryModel model,HttpServletResponse response) throws Exception{
		Map<String, Object> json= new HashMap<String, Object>();
		StDictionary datalist = stDictionaryService.queryByDkey(model);
		if(datalist!=null){
			json.put(SUCCESS, true);
		}
		
		json.put("rows", datalist);
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("saveUploadScoreRule")
	@Auth(verifyLogin=false,verifyURL=false)
	public void saveUploadScoreRule(StDictionary bean,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(bean.getId()!=null){
			stDictionaryService.updateBySelective(bean);
			sendSuccessMessage(response, "修改成功");
		}else{
			stDictionaryService.add(bean);
			sendSuccessMessage(response, "设置成功");
		}
	}
	
	@RequestMapping("resumeDownloadSetting")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView resumeDownloadSetting(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		return forword("adminCenter/resumeDownloadSetting", context);
	}
	
	@RequestMapping("downloadSettingList")
	@Auth(verifyLogin=false,verifyURL=false)
	public void downloadSettingList(@RequestBody List<StDictionaryModel> models,HttpServletResponse response) throws Exception{
		Map<String, Object> json= new HashMap<String, Object>();
		List<StDictionary> datalist = new ArrayList<StDictionary>();
		for(StDictionaryModel model:models){
			datalist.add(stDictionaryService.queryByDkey(model));
		}
		if(datalist.get(0)!=null || datalist.get(1)!=null){
			json.put(SUCCESS, true);
		}
		
		json.put("rows", datalist);
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("saveDownloadSetting")
	@Auth(verifyLogin=false,verifyURL=false)
	public void saveDownloadSetting(StDictionaryModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<StDictionary> beans=model.getBean();
		for(StDictionary bean:beans){
			if(bean.getId()!=null){
				stDictionaryService.updateBySelective(bean);
				sendSuccessMessage(response, "修改成功");
			}else{
				stDictionaryService.add(bean);
				sendSuccessMessage(response, "设置成功");
			}
		}
		
	}
	
	@RequestMapping("personUserManage")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView personUserManage(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		return forword("adminCenter/personUserManage", context);
	}
	
	@RequestMapping("compUserManage")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView compUserManage(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		return forword("adminCenter/compUserManage", context);
	}
	
	@RequestMapping("userInfoList")
	@Auth(verifyLogin=false,verifyURL=false)
	public void userInfoList(StWebUserModel model,HttpServletResponse response) throws Exception{
		Map<String, Object> json=new HashMap<String, Object>();
		List<StWebUser> datalist=stWebUserService.queryForAdmin(model);
		json.put("total",model.getPager().getRowCount());
		json.put(SUCCESS, true);
		json.put("rows", datalist);
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("changeUserStatus")
	@Auth(verifyLogin=false,verifyURL=false)
	public void changeUserStatus(StWebUser bean,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(bean.getId()!=null){
			stWebUserService.updateBySelective(bean);
			sendSuccessMessage(response, "修改成功");
		}else{
			sendFailureMessage(response, "记录不存在");
		}
	
		
	}
	
	@RequestMapping("scoreGetUse")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView scoreGetUse(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		return forword("adminCenter/scoreGetUse", context);
	}
	
	@RequestMapping("scoreInfoList")
	@Auth(verifyLogin=false,verifyURL=false)
	public void scoreInfoList(StWebUserModel model,HttpServletResponse response) throws Exception{
		Map<String, Object> json=new HashMap<String, Object>();
		
		List<StWebUser> datalist=stWebUserService.queryForScore(model);
		json.put(SUCCESS, true);
		json.put("total",model.getPager().getRowCount());
		json.put("rows", datalist);
		HtmlUtil.writerJson(response, json);
	}
	
	
	
	@RequestMapping("zhoutongji")
	@Auth(verifyLogin=false,verifyURL=false)
	public ModelAndView Zhoutongji(HttpServletRequest request) throws Exception{
		Map<String, Object> context=getRootMap();
		
		return forword("adminCenter/zhoutongji", context);
	}
	
	@RequestMapping("tongjiInfoList")
	@Auth(verifyLogin=false,verifyURL=false)
	public void tongjiInfoList(StWebUserModel model,HttpServletResponse response) throws Exception{
		Map<String, Object> json=new HashMap<String, Object>();
		
		List<StWebUserModel> datalist=stResumeScorerecordService.queryZhoutongji(model);
		json.put(SUCCESS, true);
		json.put("total",model.getPager().getRowCount());
		json.put("rows", datalist);
		HtmlUtil.writerJson(response, json);
	}
}
