package com.weixin.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.utils.StringUtil;
import com.weixin.bean.WeixinAttention;
import com.weixin.bean.WeixinAutoresponse;
import com.weixin.bean.WeixinMenu;
import com.weixin.bean.WeixinNewsitem;
import com.weixin.bean.WeixinNewstemplate;
import com.weixin.bean.WeixinReceivetext;
import com.weixin.bean.WeixinSubscribe;
import com.weixin.bean.WeixinTexttemplate;
import com.weixin.mapper.WeixinTexttemplateMapper;
import com.weixin.model.WeixinNewsitemModel;
import com.weixin.model.WeixinSubscribeModel;
import com.weixin.utils.Article;
import com.weixin.utils.MessageUtil;
import com.weixin.utils.NewsMessageResp;
import com.weixin.utils.TextMessageResp;
import com.weixin.utils.WeixinUtil;

@Service("wechatService")
public class WechatService {

	@Autowired
	private WeixinAccountService weixinAccountService;
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinReceivetextService<WeixinReceivetext> weixinReceivetextService;
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinAutoresponseService<WeixinAutoresponse> weixinAutoresponseService; 
	@Autowired
	private WeixinTexttemplateService<WeixinTexttemplate> weixinTexttemplateService; 
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinNewstemplateService<WeixinNewstemplate> weixinNewstemplateService;
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinNewsitemService<WeixinNewsitem> weixinNewsitemService; 
	@Autowired
    private WeixinTexttemplateMapper<WeixinTexttemplate> mapper;
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinSubscribeService<WeixinSubscribe> weixinSubscribeService; 
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinMenuService<WeixinMenu> weixinMenuService; 

	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinAttentionService<WeixinAttention> weixinAttentionService; 
	
	
	public String coreService(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			String msgId = requestMap.get("MsgId");
			//消息内容
			String content = requestMap.get("Content");
			System.out.println("------------微信客户端发送请求---------------------   |   fromUserName:"+fromUserName+"   |   ToUserName:"+toUserName+"   |   msgType:"+msgType+"   |   msgId:"+msgId+"   |   content:"+content);
			//根据微信ID,获取配置的全局的数据权限ID
			System.out.println("-toUserName--------"+toUserName);
			Integer sys_accountId = weixinAccountService.findByToUsername(toUserName).getId();
			System.out.println("-sys_accountId--------"+sys_accountId);
			ResourceBundle bundler = ResourceBundle.getBundle("sysconfig");
			// 默认回复此文本消息
			TextMessageResp textMessage = new TextMessageResp();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		//	textMessage.setContent(getMainMenu());
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			//【微信触发类型】文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				System.out.println("------------微信客户端发送请求------------------【微信触发类型】文本消息---");
				respMessage = doTextResponse(content,toUserName,textMessage,bundler,
						sys_accountId,respMessage,fromUserName,request,msgId,msgType);
			}
			//【微信触发类型】图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			//【微信触发类型】地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			//【微信触发类型】链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			//【微信触发类型】音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			//【微信触发类型】事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				System.out.println("------------微信客户端发送请求------------------【微信触发类型】事件推送---");
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respMessage = doDingYueEventResponse(requestMap, textMessage, bundler, respMessage, toUserName, fromUserName, respContent, sys_accountId);
					System.out.println("嘿嘿嘿嘿嘿额"+respMessage);
					weixinAttentionService.saveOrUpdate(getAttentionFromOpenid(fromUserName,toUserName));
					//TODO 取消关注重新关注时，是用update 
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
					/*WeixinAttention bean=new WeixinAttention();
					bean.setSubscribe(0);
					bean.setOpenid(fromUserName);
					weixinAttentionService.updateSubscribeSelective(bean);*/
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					respMessage = doMyMenuEvent(requestMap, textMessage, bundler, respMessage, toUserName, fromUserName, respContent, sys_accountId, request);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
	/**
	 * 获取用户关注信息
	 * @param fromUserName
	 * @param toUsername
	 * @return
	 * @throws Exception
	 */

	private WeixinAttention getAttentionFromOpenid(String fromUserName,String toUsername) throws Exception {
		// TODO Auto-generated method stub
		//https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
		WeixinAttention bean=new WeixinAttention();
		String accessToken = weixinAccountService.getAccessToken(toUsername);
		String url = WeixinUtil.user_getinfo_url.replace("ACCESS_TOKEN",
				accessToken).replace("OPENID", fromUserName);
		JSONObject jsonObject= new JSONObject();
			jsonObject = WeixinUtil.httpRequest(url, "POST", null);
//			LogUtil.info(jsonObject);
			if(jsonObject!=null){
			
					bean.setOpenid(jsonObject.getString("openid"));
					bean.setNickname(jsonObject.getString("nickname"));
					bean.setSex(jsonObject.getInt("sex"));
					bean.setCity(jsonObject.getString("city"));
					bean.setProvince(jsonObject.getString("province"));
					bean.setCountry(jsonObject.getString("country"));
					bean.setLanguage(jsonObject.getString("language"));
					bean.setHeadimgurl(jsonObject.getString("headimgurl"));
					bean.setSubscribe_time(new Date(jsonObject.getInt("subscribe_time")));
					bean.setRemark(jsonObject.getString("remark"));
					bean.setSubscribe(jsonObject.getInt("subscribe"));
					bean.setGroupid(jsonObject.getInt("groupid"));
					return bean;
			}else{
//				sendFailureMessage(response, "同步菜单信息数据失败！同步自定义菜单URL地址不正确。");
			}
			
		
		return null;
	}
	/**
	 * 同步用户关注信息
	 * @param fromUserName
	 * @param toUsername
	 * @return
	 * @throws Exception
	 */
	
	/*public String getAttentionFromListOpenid(String fromUserName,String toUsername) throws Exception {
		// TODO Auto-generated method stub
		//https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
		//WeixinAttention bean=new WeixinAttention();
		List<String> openIdList=new ArrayList<String>();
		String accessToken = weixinAccountService.getAccessToken(toUsername);
		String url = WeixinUtil.user_getinfolist_url.replace("ACCESS_TOKEN",
				accessToken).replace("OPENID", fromUserName);
		JSONObject jsonObject= new JSONObject();
			jsonObject = WeixinUtil.httpRequest(url, "POST", null);
			if(jsonObject!=null){
			  int total=jsonObject.getInt("total");
			  int count=jsonObject.getInt("count");
			  JSONArray nameList = jsonObject.getJSONArray("data");//获取JSONArray
			  int length = nameList.size();
	             
	            for(int i = 0; i < length; i++){//遍历JSONArray.
	            	openIdList.add(nameList.getString(i));
	                
	            }
	             
			}
			return openIdList;
	}*/

	/**
	 * Q译通使用指南
	 * 
	 * @return
	 */
	public static String getTranslateUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("微译使用指南").append("\n\n");
		buffer.append("微译为用户提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
		buffer.append("    中 -> 英").append("\n");
		buffer.append("    英 -> 中").append("\n");
		buffer.append("    日 -> 中").append("\n\n");
		buffer.append("使用示例：").append("\n");
		buffer.append("    翻译我是中国人").append("\n");
		buffer.append("    翻译dream").append("\n");
		buffer.append("    翻译さようなら").append("\n\n");
		buffer.append("回复“?”显示主菜单");
		return buffer.toString();
	}

	/**
	 * 遍历关键字管理中是否存在用户输入的关键字信息
	 * 
	 * @param content
	 * @return
	 */
	private WeixinAutoresponse findKey(String content, String toUsername) {
		System.out.println("---------sys_accountId--------"+toUsername+"|");
		//获取全局的数据权限ID
		Integer sys_accountId = weixinAccountService.findByToUsername(toUsername).getId();
		System.out.println("---------sys_accountId--------"+sys_accountId);
		// 获取关键字管理的列表，匹配后返回信息
		List<WeixinAutoresponse> autoResponses = weixinAutoresponseService.findByProperty(sys_accountId);
		System.out.println("---------sys_accountId----关键字查询结果条数：----"+autoResponses!=null?autoResponses.size():0);
		for (WeixinAutoresponse r : autoResponses) {
			// 如果包含关键字
			String kw = r.getKeyword();
			String[] allkw = kw.split(",");
			for (String k : allkw) {
				if (k.equals(content)) {
					System.out.println("---------sys_accountId----查询结果----"+r);
					return r;
				}
			}
		}
		return null;
	}

	/**
	 * 针对文本消息
	 * @param content
	 * @param toUserName
	 * @param textMessage
	 * @param bundler
	 * @param sys_accountId
	 * @param respMessage
	 * @param fromUserName
	 * @param request
	 * @throws Exception 
	 */
	String doTextResponse(String content,String toUserName,TextMessageResp textMessage,ResourceBundle bundler,
			Integer sys_accountId,String respMessage,String fromUserName,HttpServletRequest request,String msgId,String msgType) throws Exception{
		//=================================================================================================================
		// 保存接收到的信息
		WeixinReceivetext receiveText = new WeixinReceivetext();
		receiveText.setContent(content);
		
		receiveText.setCreatetime(new Date());
		receiveText.setFromusername(fromUserName);
		receiveText.setTousername(toUserName);
		receiveText.setMsgid(msgId);
		receiveText.setMsgid(msgType);
		receiveText.setResponse("0");
		receiveText.setAccountid(toUserName);
		
		weixinReceivetextService.add(receiveText);
		//=================================================================================================================
		//Step.1 判断关键字信息中是否管理该文本内容。有的话优先采用数据库中的回复
		System.out.println("------------微信客户端发送请求--------------Step.1 判断关键字信息中是否管理该文本内容。有的话优先采用数据库中的回复---");
		WeixinAutoresponse autoResponse = findKey(content, toUserName);
		// 根据系统配置的关键字信息，返回对应的消息
		if (autoResponse != null) {
			String resMsgType = autoResponse.getMsgType();
			if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(resMsgType)) {
				//根据返回消息key，获取对应的文本消息返回给微信客户端
				WeixinTexttemplate textTemplate = mapper.getTextTemplate(sys_accountId, autoResponse.getTemplatename());
				textMessage.setContent(textTemplate.getContent());
				respMessage = MessageUtil.textMessageToXml(textMessage);
			} else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(resMsgType)) {
				WeixinNewsitemModel wnms=new WeixinNewsitemModel();
				wnms.setTemplateid(autoResponse.getRescontent());
				List<WeixinNewsitem> newsList = (List<WeixinNewsitem>) weixinNewsitemService.queryByList(wnms);
				//WeixinNewstemplate newsTemplate = weixinNewstemplateService.queryById(autoResponse.getRescontent());
				List<Article> articleList = new ArrayList<Article>();
				for (WeixinNewsitem news : newsList) {
					Article article = new Article();
					article.setTitle(news.getTitle());
					article.setPicUrl(bundler.getString("weixin.domain") + "/"+ news.getImagePath());
					String url = "";
					if (StringUtil.isEmpty(news.getUrl())) {
						url = bundler.getString("weixin.domain")+ "/newsItemController.do?newscontent&id="+ news.getId();
					} else {
						url = news.getUrl();
					}
					article.setUrl(url);
					article.setDescription(news.getDescription());
					articleList.add(article);
				}
				NewsMessageResp newsResp = new NewsMessageResp();
				newsResp.setCreateTime(new Date().getTime());
				newsResp.setFromUserName(toUserName);
				newsResp.setToUserName(fromUserName);
				newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsResp.setArticleCount(newsList.size());
				newsResp.setArticles(articleList);
				respMessage = MessageUtil.newsMessageToXml(newsResp);
			}
		} else {
			// Step.2  通过微信扩展接口（支持二次开发，例如：翻译，天气）
			System.out.println("------------微信客户端发送请求--------------Step.2  通过微信扩展接口（支持二次开发，例如：翻译，天气）---");
			/*List<WeixinExpandconfigEntity> weixinExpandconfigEntityLst = weixinExpandconfigService.findByQueryString("FROM WeixinExpandconfigEntity");
			if (weixinExpandconfigEntityLst.size() != 0) {
				for (WeixinExpandconfigEntity wec : weixinExpandconfigEntityLst) {
					boolean findflag = false;// 是否找到关键字信息
					// 如果已经找到关键字并处理业务，结束循环。
					if (findflag) {
						break;// 如果找到结束循环
					}
					String[] keys = wec.getKeyword().split(",");
					for (String k : keys) {
						if (content.indexOf(k) != -1) {
							String className = wec.getClassname();
							KeyServiceI keyService = (KeyServiceI) Class.forName(className).newInstance();
							respMessage = keyService.excute(content,textMessage, request);
							findflag = true;// 改变标识，已经找到关键字并处理业务，结束循环。
							break;// 当前关键字信息处理完毕，结束当前循环
						}
					}
				}
			}*/

		}
		return respMessage;
	}
	
	/**
	 * 针对事件消息
	 * @param requestMap
	 * @param textMessage
	 * @param bundler
	 * @param respMessage
	 * @param toUserName
	 * @param fromUserName
	 * @throws Exception 
	 */
	String doDingYueEventResponse(Map<String, String> requestMap,TextMessageResp textMessage ,ResourceBundle bundler,String respMessage
			,String toUserName,String fromUserName,String respContent,Integer sys_accountId) throws Exception{
		respContent = "谢谢您的关注！回复\"?\"进入主菜单。";
		System.out.println("谢谢您的关注！回复\"?\"进入主菜单"+respContent+sys_accountId);
		
		WeixinSubscribeModel wsm=new WeixinSubscribeModel();
		wsm.setAccountid(sys_accountId);
		List<WeixinSubscribe> lst = weixinSubscribeService.queryByList(wsm);
		System.out.println(lst.get(0).getAccountid()+"微信账号ID");
		if (lst.size() != 0) {
			WeixinSubscribe subscribe = lst.get(0);
			String type = subscribe.getMsgType();
			if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(type)) {
				WeixinTexttemplate textTemplate = this.weixinTexttemplateService.queryById(subscribe.getTemplateid());
				String content = textTemplate.getContent();
				textMessage.setContent(content);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			} else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(type)) {
				WeixinNewsitemModel wnm=new WeixinNewsitemModel();
				wnm.setTemplateid(subscribe.getTemplateid());
				List<WeixinNewsitem> newsList = (List<WeixinNewsitem>) this.weixinNewsitemService.queryByList(wnm);
				List<Article> articleList = new ArrayList<Article>();
				WeixinNewstemplate newsTemplate =this.weixinNewstemplateService.queryById(subscribe.getTemplateid());
				for (WeixinNewsitem news : newsList) {
					Article article = new Article();
					article.setTitle(news.getTitle());
					article.setPicUrl(bundler.getString("weixin.domain")+ "/" + news.getImagePath());
					String url = "";
					if ("commond".equals(newsTemplate.getType())) {
						url = bundler.getString("weixin.domain")+ "/newsItemController.do?newscontent&id="+ news.getId();
					} else {
						url = news.getContent();
					}
					article.setUrl(url);
					article.setDescription(news.getContent());
					articleList.add(article);
				}
				NewsMessageResp newsResp = new NewsMessageResp();
				newsResp.setCreateTime(new Date().getTime());
				newsResp.setFromUserName(toUserName);
				newsResp.setToUserName(fromUserName);
				newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsResp.setArticleCount(newsList.size());
				newsResp.setArticles(articleList);
				respMessage = MessageUtil.newsMessageToXml(newsResp);
			}
		}
		return respMessage;
	}
	
	/**
	 * 
	 * @param requestMap
	 * @param textMessage
	 * @param bundler
	 * @param respMessage
	 * @param toUserName
	 * @param fromUserName
	 * @param respContent
	 * @param sys_accountId
	 * @param request
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	String doMyMenuEvent(Map<String, String> requestMap,TextMessageResp textMessage ,ResourceBundle bundler,String respMessage
			,String toUserName,String fromUserName,String respContent,Integer sys_accountId,HttpServletRequest request) throws Exception{
		String key = requestMap.get("EventKey");
		//自定义菜单CLICK类型
		WeixinMenu menuEntity =weixinMenuService.findUniqueByProperty(key);
		System.out.println(menuEntity.getMenukey()+"关键字");
		if (menuEntity != null&& StringUtil.isNotEmpty(menuEntity.getTemplateid())) {
			String type = menuEntity.getMsgtype();
			System.out.println(type+"类型");
			if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(type)) {
				System.out.println(menuEntity.getTemplateid()+"模板");
				WeixinTexttemplate textTemplate =weixinTexttemplateService.queryById(menuEntity.getTemplateid());
				String content = textTemplate.getContent();
				System.out.println("模板内容"+content);
				textMessage.setContent(content);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			} else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(type)) {
				WeixinNewsitemModel newsmodel=new WeixinNewsitemModel();
				newsmodel.setTemplateid(menuEntity.getTemplateid());
				List<WeixinNewsitem> newsList = (List<WeixinNewsitem>) this.weixinNewsitemService.queryByList(newsmodel);
				System.out.println(newsList.get(0).getTemplateid()+"消息模板");
				List<Article> articleList = new ArrayList<Article>();
				WeixinNewstemplate newsTemplate =this.weixinNewstemplateService.queryById(menuEntity.getTemplateid());
				for (WeixinNewsitem news : newsList) {
					Article article = new Article();
					article.setTitle(news.getTitle());
					article.setPicUrl(bundler.getString("weixin.domain")+ "/" + news.getImagePath());
					String url = "";
					if ("commond".equals(newsTemplate.getType())) {
						url = bundler.getString("weixin.domain")+ "/newsItemController.do?newscontent&id="+ news.getId();
					} else {
						url = news.getContent();
					}
					article.setUrl(url);
					article.setDescription(news.getContent());
					articleList.add(article);
				}
				NewsMessageResp newsResp = new NewsMessageResp();
				newsResp.setCreateTime(new Date().getTime());
				newsResp.setFromUserName(toUserName);
				newsResp.setToUserName(fromUserName);
				newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsResp.setArticleCount(newsList.size());
				newsResp.setArticles(articleList);
				respMessage = MessageUtil
						.newsMessageToXml(newsResp);
			} else if ("expand".equals(type)) {
				/*WeixinExpandconfigEntity expandconfigEntity = weixinExpandconfigService.getEntity(WeixinExpandconfigEntity.class,menuEntity.getTemplateId());
				String className = expandconfigEntity.getClassname();
				KeyServiceI keyService = (KeyServiceI) Class.forName(className).newInstance();
				respMessage = keyService.excute("", textMessage,request);*/

			}
		}
		return respMessage;
	}
	
	/**
	 * 欢迎语
	 * @return
	 */
//	public static String getMainMenu() {
//		// 复杂字符串文本读取，采用文件方式存储
//		String html = new FreemarkerHelper().parseTemplate("/weixin/welcome.ftl", null);
//		return html;
//	}
}
