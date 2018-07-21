package com.base.SMSMail;

import com.base.utils.Constant;

public class SendEmail {

	/***
	 * 
	 * @param toAddress 要发送的地址
	 * @param subject 标题
	 * @param content 内容
	 */
	public static void SendEmail(String toAddress,String subject,String content){		
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(Constant.MAIL_SERVER_HOST); //要用smtp的方式发送邮件,服务器
		mailInfo.setMailServerPort(Constant.MAIL_SERVER_PORT);//邮件发送端口
		mailInfo.setValidate(true);
		mailInfo.setUserName(Constant.MAIL_LOGIN_ADDRESS);//登录邮件地址
		mailInfo.setPassword(Constant.MAIL_LOGIN_PASSWORD);//登录邮件密码
		mailInfo.setFromAddress(Constant.MAIL_FROM_ADDRESS);//发送的邮件地址
		mailInfo.setToAddress(toAddress);//接收邮件的地址
		mailInfo.setSubject(subject); //发送标题
		mailInfo.setContent(content);//发送内容
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		//sms.sendTextMail(mailInfo);// 发送文体格式
		sms.sendHtmlMail(mailInfo);// 发送html格式
	}
}
