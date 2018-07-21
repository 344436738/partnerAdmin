package com.weixin.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.weixin.bean.WeixinNewsitem;
import com.weixin.bean.WeixinNewstemplate;
import com.weixin.model.WeixinNewsitemModel;
import com.weixin.model.WeixinNewstemplateModel;
import com.weixin.service.WeixinNewsitemService;
import com.weixin.service.WeixinNewstemplateService;
import com.weixin.utils.WeiXinConstants;
 
@Controller
@RequestMapping("/weixinNewstemplate") 
public class WeixinNewstemplateAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(WeixinNewstemplateAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinNewstemplateService<WeixinNewstemplate> weixinNewstemplateService; 
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinNewsitemService<WeixinNewsitem> weixinNewsitemService; 
	
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(WeixinNewstemplateModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("weixinNewstemplate/weixinNewstemplateList",context); 
	}
	/**
	 * 微信单图消息列表 页面跳转
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "goMessage")
	public ModelAndView goMessage(HttpServletRequest request,WeixinNewsitemModel model,WeixinNewstemplateModel newsModel) throws Exception {
		Map<String,Object>  context = getRootMap();
		model.setTemplateid(StringUtil.getStrToInt(request.getParameter("templateId")));
		//model.setSort(sort);
		List<WeixinNewsitem> headerList = weixinNewsitemService.queryByList(model);
		if(headerList.size()>0){
			context.put("headerNews",  headerList.get(0));
			if(headerList.size()>1){
				ArrayList list = new ArrayList(headerList);
				list.remove(0);
				context.put("newsList", list);
			}
		} 
		List<WeixinNewstemplate> queryById = weixinNewstemplateService.queryByList(newsModel);
		Date temp = queryById.get(0).getAddtime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		context.put("addtime", sdf.format(temp));
		return forword("weixinNewstemplate/showmessage",context);
	}
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(WeixinNewstemplateModel model,HttpServletResponse response,HttpServletRequest request) throws Exception{
//		model.setDeleted(0);
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		model.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		List<WeixinNewstemplate> dataList = weixinNewstemplateService.queryByList(model);
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
	public void save(WeixinNewstemplate bean,Integer[] typeIds,HttpServletResponse response,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
//		bean.setDeleted(DELETED.NO.key);
		if(bean.getId() == null){
			WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
			bean.setAccountid(weixinaccount.getId());
			weixinNewstemplateService.add(bean);
		}else{
			weixinNewstemplateService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		WeixinNewstemplate bean  = weixinNewstemplateService.queryById(id);
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
		weixinNewstemplateService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
