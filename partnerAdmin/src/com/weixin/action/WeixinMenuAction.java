package com.weixin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import com.weixin.bean.WeixinMenu;
import com.weixin.bean.WeixinNewstemplate;
import com.weixin.bean.WeixinTexttemplate;
import com.weixin.model.WeixinMenuModel;
import com.weixin.model.WeixinNewstemplateModel;
import com.weixin.model.WeixinTexttemplateModel;
import com.weixin.service.WeixinAccountService;
import com.weixin.service.WeixinMenuService;
import com.weixin.service.WeixinNewstemplateService;
import com.weixin.service.WeixinTexttemplateService;
import com.weixin.utils.Button;
import com.weixin.utils.CommonButton;
import com.weixin.utils.ComplexButton;
import com.weixin.utils.Menu;
import com.weixin.utils.ViewButton;
import com.weixin.utils.WeiXinConstants;
import com.weixin.utils.WeixinUtil;
 
@Controller
@RequestMapping("/weixinMenu") 
public class WeixinMenuAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(WeixinMenuAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinMenuService<WeixinMenu> weixinMenuService; 
	@Autowired
	private WeixinTexttemplateService<WeixinTexttemplate> weixinTexttemplateService; 
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinNewstemplateService<WeixinNewstemplate> weixinNewstemplateService; 
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
	public ModelAndView  list(WeixinMenuModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("weixinMenu/weixinMenuList",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(WeixinMenuModel model,HttpServletResponse response,HttpServletRequest request) throws Exception{
//		model.setDeleted(0);
		if (model.getSort() == null) {
			model.setSort("sorting");
			model.setOrder("asc");
		}
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		model.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		List<WeixinMenu> dataList = weixinMenuService.queryByList(model);
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
	public void save(WeixinMenu bean,Integer[] typeIds,Integer fatherid,HttpServletResponse response,HttpServletRequest request) throws Exception{
		//Map<String,Object>  context = new HashMap<String,Object>();
//		bean.setDeleted(DELETED.NO.key);
		if(bean.getId() == null){
			 WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
				bean.setAccountid(weixinaccount.getId());
				bean.setFatherid(fatherid);
			weixinMenuService.add(bean);
		}else{
			weixinMenuService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		WeixinMenu bean  = weixinMenuService.queryById(id);
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
		weixinMenuService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

	/**
	 * 根据不同消息类型作出判断
	 * 
	 */
	@RequestMapping("/gettemplate")
	public void gettemplate(String msgType,HttpServletResponse response,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = new HashMap();
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
			if ("text".equals(msgType)) {//文本消息
				WeixinTexttemplateModel modelText=new WeixinTexttemplateModel();
				modelText.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
				List<WeixinTexttemplate> queryByTextList = weixinTexttemplateService.queryByList(modelText);
				context.put(SUCCESS, true);
				context.put("data", queryByTextList);
				HtmlUtil.writerJson(response, context);
			} else if ("news".equals(msgType)) {//图文消息
				WeixinNewstemplateModel modelNews=new WeixinNewstemplateModel();
				modelNews.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
				List<WeixinNewstemplate> queryByNewsList = weixinNewstemplateService.queryByList(modelNews);
				context.put(SUCCESS, true);
				context.put("data", queryByNewsList);
				HtmlUtil.writerJson(response, context);
			}/*else if("expand".equals(msgType)){//扩展接口
					待续。。。。
			}*/
		
	}
	/**
	 * 菜单信息同步到微信
	 * sameMenu
	 * @throws Exception 
	 */
	@RequestMapping("/sameMenu")
	public void sameMenu(WeixinMenuModel model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		model.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		List<WeixinMenu> menuList = weixinMenuService.queryByList(model);
		Menu menu = new Menu();
		Button firstArr[] = new Button[menuList.size()];
		for (int a = 0; a < menuList.size(); a++) {
			WeixinMenu entity = menuList.get(a);
			model.setFatherid(entity.getId());
			List<WeixinMenu> childList = weixinMenuService.queryByList(model);
			if (childList.size() == 0) {
				if("view".equals(entity.getType())){
					ViewButton viewButton = new ViewButton();
					viewButton.setName(entity.getName());
					viewButton.setType(entity.getType());
					viewButton.setUrl(entity.getUrl());
					firstArr[a] = viewButton;
				}else if("click".equals(entity.getType())){
					CommonButton cb = new CommonButton();
					cb.setKey(entity.getMenukey());
					cb.setName(entity.getName());
					cb.setType(entity.getType());
					firstArr[a] = cb;
				}
			
			} else {
				ComplexButton complexButton = new ComplexButton();
				complexButton.setName(entity.getName());

				Button[] secondARR = new Button[childList.size()];
				for (int i = 0; i < childList.size(); i++) {
					WeixinMenu children = childList.get(i);
					String type = children.getType();
					if ("view".equals(type)) {
						ViewButton viewButton = new ViewButton();
						viewButton.setName(children.getName());
						viewButton.setType(children.getType());
						viewButton.setUrl(children.getUrl());
						secondARR[i] = viewButton;

					} else if ("click".equals(type)) {

						CommonButton cb1 = new CommonButton();
						cb1.setName(children.getName());
						cb1.setType(children.getType());
						cb1.setKey(children.getMenukey());
						secondARR[i] = cb1;

					}

				}
				complexButton.setSub_button(secondARR);
				firstArr[a] = complexButton;
			}
		}
		menu.setButton(firstArr);
		JSONObject jsonMenu = JSONObject.fromObject(menu);
		String accessToken = weixinAccountService.getAccessToken(request);
		String url = WeixinUtil.menu_create_url.replace("ACCESS_TOKEN",
				accessToken);
		JSONObject jsonObject= new JSONObject();
			jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu.toString());
//			LogUtil.info(jsonObject);
			if(jsonObject!=null){
				if (0 == jsonObject.getInt("errcode")) {
						sendSuccessMessage(response, "同步菜单信息数据成功！");
				}
				else {
					sendFailureMessage(response,"同步菜单信息数据失败！错误码为："+jsonObject.getInt("errcode")+"错误信息为："+jsonObject.getString("errmsg"));
				}
			}else{
				sendFailureMessage(response, "同步菜单信息数据失败！同步自定义菜单URL地址不正确。");
			}
			sendFailureMessage(response, "同步菜单信息数据失败！"); 
	}
	
	
	
	
}
