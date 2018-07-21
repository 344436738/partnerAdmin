package com.weixin.action;

import java.text.SimpleDateFormat;
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
import com.weixin.bean.WeixinNewstemplate;
import com.weixin.bean.WeixinSubscribe;
import com.weixin.bean.WeixinTexttemplate;
import com.weixin.model.WeixinNewstemplateModel;
import com.weixin.model.WeixinSubscribeModel;
import com.weixin.model.WeixinTexttemplateModel;
import com.weixin.service.WeixinNewstemplateService;
import com.weixin.service.WeixinSubscribeService;
import com.weixin.service.WeixinTexttemplateService;
import com.weixin.utils.WeiXinConstants;
 
@Controller
@RequestMapping("/weixinSubscribe") 
public class WeixinSubscribeAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(WeixinSubscribeAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinSubscribeService<WeixinSubscribe> weixinSubscribeService; 
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
	public ModelAndView  list(WeixinSubscribeModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		model.setAccountid(StringUtil.ObjectToInteger(weixinaccount));
		int count = weixinSubscribeService.queryByCount(model);
		context.put("accountCount", count);
		
		WeixinTexttemplateModel modelText=new WeixinTexttemplateModel();
		modelText.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		List<WeixinTexttemplate> textList = weixinTexttemplateService.queryByList(modelText);
		
		WeixinNewstemplateModel modelNews=new WeixinNewstemplateModel();
		modelNews.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		List<WeixinNewstemplate> newsList = weixinNewstemplateService.queryByList(modelNews);
		context.put("textList", textList);
		context.put("newsList", newsList);
		
		return forword("weixinSubscribe/weixinSubscribeList",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(WeixinSubscribeModel model,HttpServletResponse response,HttpServletRequest request) throws Exception{
//		model.setDeleted(0);
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		model.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		List<WeixinSubscribe> dataList = weixinSubscribeService.queryByList(model);
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
	public void save(WeixinSubscribe bean,Integer[] typeIds,HttpServletResponse response,HttpServletRequest request) throws Exception{
		
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		
		if(bean.getId()==null){
			//判断当前公众帐号是否已经配置欢迎语
			WeixinSubscribeModel model=new WeixinSubscribeModel();
			model.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
			int size=weixinSubscribeService.queryByCount(model);
			if(size!=0){
				sendFailureMessage(response, "每个公众帐号只能配置一个欢迎语。");
				return;
			}
			
			bean.setAddTime(new Date());
			Integer templateId = bean.getTemplateid();
			String msgType = bean.getMsgType();
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
			bean.setTemplateName(templateName);
			bean.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
			if (!"-1".equals(weixinaccount)) {
				weixinSubscribeService.add(bean);
			} else {
				
				sendFailureMessage(response, "请添加一个公众帐号。");
			}
			
		}else{
			Integer templateId = bean.getTemplateid();
			String msgType = bean.getMsgType();
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
			bean.setTemplateName(templateName);
			weixinSubscribeService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		WeixinSubscribe bean  = weixinSubscribeService.queryById(id);
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
		weixinSubscribeService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
