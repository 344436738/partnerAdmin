package com.base.SMSMail;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.base.utils.Constant;

public class SendSMS {
	
	public static void sendSMS(String smsMob,String smsText)throws Exception{
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(Constant.SMS_POST_METHOD); //UTF-8编码发送接口地址：
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");//在头文件中设置转码
		NameValuePair[] data ={ new NameValuePair("Uid", Constant.SMS_LOGIN_ADDRESS),// 注册的用户名  
								new NameValuePair("Key", Constant.SMS_KEY),//注册成功后，登录网站后得到的密钥
								new NameValuePair("smsMob",smsMob),// 手机号码 
								new NameValuePair("smsText",smsText)};// 短信内容
		post.setRequestBody(data);
	
		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		//System.out.println("statusCode:"+statusCode);
		for(Header h : headers){
			//System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("UTF-8")); 
		//System.out.println(result); //打印返回消息状态
		post.releaseConnection();
	}
}
