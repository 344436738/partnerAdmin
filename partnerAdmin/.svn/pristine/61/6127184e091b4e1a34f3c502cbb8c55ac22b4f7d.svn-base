package com.base.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.base.bean.SiteNews;
import com.base.service.SiteNewsService;
@ContextConfiguration(locations = { "classpath:spring-quartz.xml" })
public class TestQuartz {

	
	@Autowired
	private SiteNewsService<SiteNews> siteNewsService;
	
	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		System.out.println("Test start." + "\n");
		 ApplicationContext context = new
		ClassPathXmlApplicationContext("spring-quartz.xml");
		System.out.print("Test end..");
	}

	public void work() throws Exception {

		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		SiteNewsService.viewlist.size();
//		for (int i = 0; i < SiteNewsService.viewlist.size(); i++) {
//			siteNewsService.updateView(SiteNewsService.viewlist.get(i));
//			System.out.println("为1时。。。。访问id为"+SiteNewsService.viewlist.get(i).getId()+"为1时。。。。访问量"+SiteNewsService.viewlist.get(i).getView());
//		}
//		 SiteNewsService.viewlist.clear();
		System.out.println("----------"+SiteNewsService.viewlist.size());
		System.out.println("----------");
		System.out.println(formatter.format(new Date()) + "在制定的时间做指定的事情！！");

		System.out.println("每10秒查看一下！！！");

		System.out.println("\n");
	}

}