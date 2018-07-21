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





import com.weixin.bean.WeixinAccount;
import com.weixin.bean.WeixinPrizerecord;
import com.weixin.model.WeixinPrizerecordModel;
import com.weixin.service.WeixinPrizerecordService;
import com.weixin.utils.WeiXinConstants;
import com.base.utils.HtmlUtil;
import com.base.utils.SessionUtils;
import com.base.utils.StringUtil;
import com.weixin.bean.WeixinPrizerecord;
import com.weixin.model.WeixinPrizerecordModel;
import com.base.action.BaseAction;
 
@Controller
@RequestMapping("/weixinPrizerecord") 
public class WeixinPrizerecordAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(WeixinPrizerecordAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinPrizerecordService<WeixinPrizerecord> weixinPrizerecordService; 
	
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public ModelAndView  list(WeixinPrizerecordModel model,HttpServletRequest request) throws Exception{
		String hdid = request.getParameter("hdid");
		String type = request.getParameter("type");
		WeixinPrizerecord bean=new WeixinPrizerecord();
		Map<String,Object>  context = getRootMap();
		context.put("hdid",hdid);
		context.put("type",type);
		return forword("weixinPrizerecord/weixinPrizerecordList",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(WeixinPrizerecordModel model,HttpServletResponse response,HttpServletRequest request) throws Exception{
//		model.setDeleted(0);
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		model.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		List<WeixinPrizerecord> dataList = weixinPrizerecordService.queryByList(model);
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
	public void save(WeixinPrizerecord bean,Integer[] typeIds,HttpServletResponse response,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
//		bean.setDeleted(DELETED.NO.key);
		if(bean.getId() == null){
			WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
			bean.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
			weixinPrizerecordService.add(bean);
		}else{
			weixinPrizerecordService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		WeixinPrizerecord bean  = weixinPrizerecordService.queryById(id);
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
		weixinPrizerecordService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
