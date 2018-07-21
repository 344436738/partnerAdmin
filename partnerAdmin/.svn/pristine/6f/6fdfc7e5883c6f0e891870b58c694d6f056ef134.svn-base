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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.base.action.BaseAction;
import com.base.utils.FileUploadUtil;
import com.base.utils.HtmlUtil;
import com.base.utils.SessionUtils;
import com.base.utils.StringUtil;
import com.weixin.bean.WeixinAccount;
import com.weixin.bean.WeixinNewsitem;
import com.weixin.model.WeixinNewsitemModel;
import com.weixin.service.WeixinNewsitemService;
import com.weixin.utils.WeiXinConstants;
 
@Controller
@RequestMapping("/weixinNewsitem") 
public class WeixinNewsitemAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(WeixinNewsitemAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinNewsitemService<WeixinNewsitem> weixinNewsitemService; 
	
	
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	/*@RequestMapping("/list") 
	public ModelAndView  list(WeixinNewsitemModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		WeixinNewstemplate queryById = weixinNewstemplateService.queryById(model);
		context.put("addtime", queryById.getAddtime());
		return forword("weixin/guanjia/newstemplate/showmessage",context); 
	}*/
	
	/**
	 * 微信单图消息新增页面跳转
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinNewsitemModel model, HttpServletRequest req,String templateId) throws Exception {
		//设置页面数据
		Map<String,Object>  context = getRootMap();
		context.put("templateId", templateId);
		/*if (model.getId()!=null) {
			WeixinNewsitem weixinArticle = weixinNewsitemService.queryById(model);
			context.put("weixinArticlePage", weixinArticle);
		}*/
		return forword("weixinNewstemplate/weixinArticle-add",context);
	}
	/**
	 * 微信单图消息编辑页面跳转
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinNewsitemModel model, HttpServletRequest req) throws Exception {
		//设置页面数据
				Map<String,Object>  context = getRootMap();
		if (StringUtil.isNotEmpty(model.getId())) {
			WeixinNewsitem weixinArticle = weixinNewsitemService.queryById(model);
			context.put("weixinArticle", weixinArticle);
		}
		return forword("weixinNewstemplate/weixinArticle-update",context);
	}
	

	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(WeixinNewsitemModel model,HttpServletResponse response,HttpServletRequest request) throws Exception{
//		model.setDeleted(0);
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		model.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		List<WeixinNewsitem> dataList = weixinNewsitemService.queryByList(model);
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
	public void save(WeixinNewsitem bean,Integer[] typeIds,HttpServletResponse response,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
//		bean.setDeleted(DELETED.NO.key);
			int temp = StringUtil.getStrToInt(request.getParameter("templateId"));
			bean.setTemplateid(temp);
			WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
			if (!"-1".equals(weixinaccount.getId())) {
				weixinNewsitemService.add(bean);
				}else{
					sendFailureMessage(response,"请添加一个公众帐号。");
			}
			//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			
			
			//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			
		sendSuccessMessage(response, "保存成功~");
	}
	/**
	 * 更新微信单图消息
	 * 
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "doUpdate")
	public void doUpdate( HttpServletRequest request,WeixinNewsitem bean,HttpServletResponse response) throws Exception {		
		Map<String,Object>  context = new HashMap();
		WeixinNewsitem weixinNewsitem = weixinNewsitemService.queryById(bean);
		weixinNewsitemService.updateBySelective(bean);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		context.put(SUCCESS, true);
		context.put("data", weixinNewsitem);
		sendSuccessMessage(response, "保存成功~");
		HtmlUtil.writerJson(response, context);
	}
	
	
	
//	@RequestMapping("/getId")
//	public void getId(Integer id,HttpServletResponse response) throws Exception{
//		Map<String,Object>  context = new HashMap();
//		WeixinNewsitem bean  = weixinNewsitemService.queryById(id);
//		if(bean  == null){
//			sendFailureMessage(response, "没有找到对应的记录!");
//			return;
//		}
//		context.put(SUCCESS, true);
//		context.put("data", bean);
//		HtmlUtil.writerJson(response, context);
//	}
	
	
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		weixinNewsitemService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	  /**
     * 保存文件信息
     * 图片上传
     * @param request
     * @param response
     * @return
	 * @throws Exception 
     */
	  @RequestMapping("/upload")
	    public  void upload(MultipartHttpServletRequest request, HttpServletResponse response,MultipartFile file,WeixinNewsitem bean) throws Exception {
			Map<String, Object> attributes = new HashMap<String, Object>();
			String reladdress=FileUploadUtil.upload(request, file, "/ColumnPicture/",null);
			
			attributes.put("url", reladdress);
//			attributes.put("fileKey", document.getId());
//			attributes.put("name", document.getAttachmenttitle());
			attributes.put("viewhref",reladdress);
//			attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + document.getId());
			
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put(SUCCESS, true);
			result.put(MSG, "文件添加成功");
			result.put("attributes",attributes);
			HtmlUtil.writerJson(response, result);
	    }
}
