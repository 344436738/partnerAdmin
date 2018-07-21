package com.weixin.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.action.BaseAction;
import com.base.utils.HtmlUtil;
import com.base.utils.SessionUtils;
import com.base.utils.StringUtil;
import com.weixin.bean.WeixinAccount;
import com.weixin.bean.WeixinAutoresponse;
import com.weixin.bean.WeixinNewstemplate;
import com.weixin.bean.WeixinTexttemplate;
import com.weixin.model.WeixinAutoresponseModel;
import com.weixin.model.WeixinNewstemplateModel;
import com.weixin.model.WeixinTexttemplateModel;
import com.weixin.service.WeixinAutoresponseService;
import com.weixin.service.WeixinNewstemplateService;
import com.weixin.service.WeixinTexttemplateService;
import com.weixin.utils.WeiXinConstants;
 
@Controller
@RequestMapping("/weixinAutoresponse") 
public class WeixinAutoresponseAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(WeixinAutoresponseAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinAutoresponseService<WeixinAutoresponse> weixinAutoresponseService; 
	@Autowired
	private WeixinTexttemplateService<WeixinTexttemplate> weixinTexttemplateService; 
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinNewstemplateService<WeixinNewstemplate> weixinNewstemplateService;

	
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(WeixinAutoresponseModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		
		WeixinTexttemplateModel modelText=new WeixinTexttemplateModel();
		modelText.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		List<WeixinTexttemplate> textList = weixinTexttemplateService.queryByList(modelText);
		
		WeixinNewstemplateModel modelNews=new WeixinNewstemplateModel();
		modelNews.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		List<WeixinNewstemplate> newsList = weixinNewstemplateService.queryByList(modelNews);
		context.put("textList", textList);
		context.put("newsList", newsList);
		
		return forword("weixinAutoresponse/weixinAutoresponseList",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(WeixinAutoresponseModel model,HttpServletResponse response,HttpServletRequest request) throws Exception{
//		model.setDeleted(0);
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		model.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		List<WeixinAutoresponse> dataList = weixinAutoresponseService.queryByList(model);
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(WeixinAutoresponse bean,Integer[] typeIds,HttpServletResponse response,HttpServletRequest request) throws Exception{
		
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		if(bean.getId() == null){
			bean.setAddtime(new Date());
			String msgType = bean.getMsgType();
			Integer templateId = bean.getRescontent();
			String templateName = "";
			if ("text".equals(msgType)) {
				WeixinTexttemplate textTemplate = weixinTexttemplateService.queryById(templateId);
				
				if (textTemplate != null) {
					templateName = textTemplate.getTemplatename();
				}
			} else if ("news".equals(msgType)) {
				WeixinNewstemplate newsTemplate = weixinNewstemplateService.queryById(templateId);
				if (newsTemplate != null) {
					templateName = newsTemplate.getTemplatename();
				}
			}
			bean.setTemplatename(templateName);
			bean.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
			if (!"-1".equals(weixinaccount)) {
				weixinAutoresponseService.add(bean);
			} else {
				
				sendFailureMessage(response, "请添加一个公众帐号。");
				return;
			}
			
		}else{
			String msgType = bean.getMsgType();
			Integer templateId = bean.getRescontent();
			String templateName = "";
			if ("text".equals(msgType)) {
				WeixinTexttemplate textTemplate = weixinTexttemplateService.queryById(templateId);
				
				if (textTemplate != null) {
					templateName = textTemplate.getTemplatename();
				}
			} else if ("news".equals(msgType)) {
				WeixinNewstemplate newsTemplate = weixinNewstemplateService.queryById(templateId);
				if (newsTemplate != null) {
					templateName = newsTemplate.getTemplatename();
				}
			}			bean.setTemplatename(templateName);
			weixinAutoresponseService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	/**
	 * 获取模板文件名字
	 * @param msgType
	 * @param templateId
	 * @return
	 * @throws Exception 
	 */
	private String getTempName(String msgType,String templateId) throws Exception{
		
		String templateName = "";
		if ("text".equals(msgType)) {
			WeixinTexttemplate textTemplate = weixinTexttemplateService.queryById(templateId);
			
			if (textTemplate != null) {
				templateName = textTemplate.getTemplatename();
			}
		} else if ("news".equals(msgType)) {
			WeixinNewstemplate newsTemplate = weixinNewstemplateService.queryById(templateId);
			if (newsTemplate != null) {
				templateName = newsTemplate.getTemplatename();
			}
		}
		return templateName;
		
	}
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		WeixinAutoresponse bean  = weixinAutoresponseService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		weixinAutoresponseService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
