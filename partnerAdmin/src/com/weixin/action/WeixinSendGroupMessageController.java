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
import com.base.utils.SessionUtils;
import com.base.utils.StringUtil;
import com.weixin.bean.WeixinAccount;
import com.weixin.bean.WeixinAttention;
import com.weixin.bean.WeixinSendMessage;
import com.weixin.model.WeixinAttentionModel;
import com.weixin.model.WeixinMenuModel;
import com.weixin.service.WeixinAccountService;
import com.weixin.service.WeixinAttentionService;
import com.weixin.utils.CreatSendMsgJson;
import com.weixin.utils.WeiXinConstants;
import com.weixin.utils.WeixinUtil;
 
@Controller
@RequestMapping("/WeixinSendGroupMessage") 
public class WeixinSendGroupMessageController extends BaseAction{
	
	private final static Logger log= Logger.getLogger(WeixinSendGroupMessageController.class);

	private static final int String = 0;
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinAttentionService<WeixinAttention> weixinAttentionService; 
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
	public ModelAndView  list(HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("weixinAttention/weixinSendGroupMessageList",context); 
	}
	
	
	@RequestMapping("/sendMessage")
	public void sendMessage(WeixinSendMessage model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		WeixinMenuModel accountModel=new WeixinMenuModel();
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		accountModel.setAccountid(StringUtil.ObjectToInteger(weixinaccount.getId()));
		String accessToken = weixinAccountService.getAccessToken(request);
		String url = WeixinUtil.sendMsg_url.replace("ACCESS_TOKEN", accessToken);
		String sendText = CreatSendMsgJson.sendText(model);
	    JSONObject jsonObject= new JSONObject();
		jsonObject = WeixinUtil.httpRequest(url, "POST", sendText);
//			LogUtil.info(jsonObject);
		if(jsonObject!=null){
				if (0 == jsonObject.getInt("errcode")) {
						sendSuccessMessage(response, "消息群发成功！");
				}
				else {
					sendFailureMessage(response,"消息群发失败！错误码为："+jsonObject.getInt("errcode")+"错误信息为："+jsonObject.getString("errmsg"));
				}
			}
			sendFailureMessage(response, "消息群发失败！"); 
	}
	

}
