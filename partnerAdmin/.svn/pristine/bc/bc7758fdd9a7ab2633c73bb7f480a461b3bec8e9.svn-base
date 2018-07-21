package com.weixin.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;







import com.weixin.bean.WeixinAttention;
import com.weixin.model.WeixinAttentionModel;
import com.weixin.service.WechatService;
import com.weixin.service.WeixinAccountService;
import com.weixin.service.WeixinAttentionService;
import com.weixin.utils.WeixinUtil;
import com.base.utils.HtmlUtil;
import com.weixin.bean.WeixinAttention;
import com.weixin.model.WeixinAttentionModel;
import com.base.action.BaseAction;
 
@Controller
@RequestMapping("/weixinAttention") 
public class WeixinAttentionAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(WeixinAttentionAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinAttentionService<WeixinAttention> weixinAttentionService; 
	@Autowired
	private WechatService wechatService;
	@Autowired
	private WeixinAccountService weixinAccountService;
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(WeixinAttentionModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("weixinAttention/weixinAttentionList",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(WeixinAttentionModel model,HttpServletResponse response) throws Exception{
//		model.setDeleted(0);
		List<WeixinAttention> dataList = weixinAttentionService.queryByList(model);
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
	public void save(WeixinAttention bean,Integer[] typeIds,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
//		bean.setDeleted(DELETED.NO.key);
		if(bean.getId() == null){
			weixinAttentionService.add(bean);
		}else{
			weixinAttentionService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		WeixinAttention bean  = weixinAttentionService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	
	
	@RequestMapping("/sync")
	public void sync(HttpServletResponse response,String fromUserName,String toUserName) throws Exception{
		//weixinAttentionService.saveOrUpdate(bean);
		
	}

	

}
