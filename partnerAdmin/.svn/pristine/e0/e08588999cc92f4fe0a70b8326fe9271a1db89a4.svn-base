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
import com.weixin.bean.WeixinAccount;
import com.weixin.model.WeixinAccountModel;
import com.weixin.service.WeixinAccountService;
import com.weixin.utils.WeiXinConstants;
 
@Controller
@RequestMapping("/weixinAccount") 
public class WeixinAccountAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(WeixinAccountAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinAccountService<WeixinAccount> weixinAccountService; 
	
	
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(WeixinAccountModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		int count = weixinAccountService.findByUsername(SessionUtils.getUser(request).getEmail()).size();
		context.put("accountCount", count);
		return forword("weixinAccount/weixinAccountList",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(WeixinAccountModel model,HttpServletResponse response,HttpServletRequest request) throws Exception{
//		model.setDeleted(0);
		// 判断是否超级管理员
				if (!SessionUtils.isAdmin(request)) {
					model.setUserid(SessionUtils.getUserId(request));
				} 
				
				List<WeixinAccount> dataList = weixinAccountService.queryByList(model);
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
	public void save(WeixinAccount bean,Integer[] typeIds,HttpServletResponse response,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
//		bean.setDeleted(DELETED.NO.key);
		if(bean.getId() == null||"".equals(bean.getId())){
//			String UserName = ;
				// 判断当前帐号是否已经添加微信公众账户
				int count = weixinAccountService.findByUsername(SessionUtils.getUser(request).getEmail()).size();
				if (count == 0) {
					bean.setUSERNAME(SessionUtils.getUser(request).getEmail());
					bean.setUserid(SessionUtils.getUser(request).getId());
					weixinAccountService.add(bean);
					//重置session中的微信公众帐号ID
					//List<WeixinAccount> acclst = weixinAccountService.findByProperty(WeixinAccount.class, "accountnumber", bean.getAccountnumber());
					//request.getSession().setAttribute(WeiXinConstants.WEIXIN_ACCOUNT, acclst.get(0));
					
					SessionUtils.setAttr(request, WeiXinConstants.WEIXIN_ACCOUNT,  bean);
				} else {
					sendFailureMessage(response, "微信公众帐号信息添加失败,每个用户只能添加一个微信公众帐号");
					return;
				}
		}else{
				weixinAccountService.updateBySelective(bean);
				WeixinAccount  weixinAccountEntity = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
				SessionUtils.setAttr(request, WeiXinConstants.WEIXIN_ACCOUNT,  weixinAccountEntity);
			
			
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		WeixinAccount bean  = weixinAccountService.queryById(id);
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
		weixinAccountService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
