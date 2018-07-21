package com.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.weixin.bean.WeixinAccount;
import com.weixin.utils.Article;
import com.weixin.utils.MessageUtil;
import com.weixin.utils.NewsMessageResp;
import com.weixin.utils.TextMessageResp;


public class DaZhuanPanService implements KeyServiceI {

	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinAccountService<WeixinAccount> weixinAccountService;
	
	@Override
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		
//		WeixinAccountService weixinAccountService = (weixinAccountService) ApplicationContextUtil.getContext().getBean("weixinAccountService");
//		String accountid = weixinAccountService.findByToUsername(defaultMessage.getFromUserName()).getId();
		Integer accountid = weixinAccountService.findByToUsername(defaultMessage.getFromUserName()).getId();
		ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("大转盘");
		article.setDescription("大转盘抽奖咯");
		article.setPicUrl(bundler.getString("domain")
						+ "images/activity-lottery-1.png");
		article.setUrl(bundler.getString("domain")
				+ "/zpController.do?goZhuanpan&accountid="+accountid+"&openid="+defaultMessage.getToUserName());
		articleList.add(article);
		NewsMessageResp newsMessage = new NewsMessageResp();
		newsMessage.setToUserName(defaultMessage.getToUserName());
		newsMessage.setFromUserName(defaultMessage.getFromUserName());
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return MessageUtil.newsMessageToXml(newsMessage);
	}

	@Override
	public String getKey() {
		return "大转盘";
	}

}
