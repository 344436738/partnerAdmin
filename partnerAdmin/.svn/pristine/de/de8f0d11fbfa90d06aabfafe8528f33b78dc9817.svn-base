package com.weixin.utils;

import net.sf.json.JSONObject;

import com.weixin.bean.WeixinSendMessage;

public class CreatSendMsgJson {
	
    
	public static String sendText(WeixinSendMessage model) throws Exception{
		if(model.getMediaId()!=null&&"".equals(model.getMediaId())){
			
			return "";
		}
		String json = "{\"filter\":{\"is_to_all\":true},\"text\":{\"content\":'"+model.getParam()+"'},\"msgtype\":'"+model.getMsgtype()+"'}";
		JSONObject jsonMenu = JSONObject.fromObject(json);
		return jsonMenu.toString();
	}
	

}
