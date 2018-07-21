package com.weixin.action;

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
import com.weixin.bean.WeixinHuodong;
import com.weixin.model.WeixinHuodongModel;
import com.weixin.service.WeixinHuodongService;
import com.weixin.utils.WeiXinConstants;
 
@Controller
@RequestMapping("/weixinHuodong") 
public class WeixinHuodongAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(WeixinHuodongAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinHuodongService<WeixinHuodong> weixinHuodongService; 
	
	
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(WeixinHuodongModel model,HttpServletRequest request) throws Exception{
		//2：大转盘 1：刮刮乐
		String type = request.getParameter("type");
		Map<String,Object>  context = getRootMap();
		context.put("type", type);
		return forword("weixinHuodong/weixinHuodongList",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(WeixinHuodongModel model,HttpServletResponse response,HttpServletRequest request) throws Exception{
//		model.setDeleted(0);
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		model.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		List<WeixinHuodong> dataList = weixinHuodongService.queryByList(model);
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
	public void save(WeixinHuodong bean,Integer[] typeIds,HttpServletResponse response,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		
//		bean.setDeleted(DELETED.NO.key);
		if(bean.getId() == null){
			WeixinHuodongModel model=new WeixinHuodongModel();
			model.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
			int size=weixinHuodongService.queryByCount(model);
			if(size!=0){
				sendFailureMessage(response, "每个公众帐号只能配置一活动。");
				return;
			}
			if (!"-1".equals(weixinaccount)) {
				weixinHuodongService.add(bean);
			} else {
				sendFailureMessage(response, "请添加一个公众帐号。");
			}
			
		}else{
			weixinHuodongService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		WeixinHuodong bean  = weixinHuodongService.queryById(id);
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
		weixinHuodongService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
