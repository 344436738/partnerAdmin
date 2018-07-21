package com.base.test;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.base.bean.SiteMain;
import com.base.bean.SiteNews;
import com.base.bean.SiteNewsData;
import com.base.model.SiteNewsModel;
import com.base.service.SiteMainService;
import com.base.service.SiteNewsDataService;
import com.base.service.SiteNewsService;

/** 
 * 测试
 * @author http://blog.csdn.net/java2000_wl 
 * @version &lt;b&gt;1.0&lt;/b&gt; 
 */ 
public class TestThread extends Thread{
	
	
	static ApplicationContext context = new ClassPathXmlApplicationContext(
			new String[]{"spring-common.xml"}); 
	
	SiteNewsService<SiteNews> siteNewsService= (SiteNewsService)context.getBean("siteNewsService");
	
	private int id;
	private int threadId;
	
	public TestThread(){
		
	}
	
	/**
	 * 测试多线程更新访问量
	 * @param id
	 */
	public TestThread(int id,int threadId){
		this.id=id;
		this.threadId=threadId;
	}
	
	//没人访问10次
	public void run(){
			long begin=System.currentTimeMillis();
			for(int i=0;i<10;i++){
//				siteNewsService.updateViews(id);
//				Iterator<SiteNews> iter3 = siteNewsService.viewlist.iterator();
//				System.out.print("第"+(i+1)+"次:");
//				while (iter3.hasNext()) {
//					SiteNews news=iter3.next();
//					System.out.print(threadId+"###"+news.getView());
//				}
//				System.out.println("");
			}
			
			Iterator<SiteNews> iter3 = siteNewsService.viewlist.iterator();
			while (iter3.hasNext()) {
				SiteNews news=iter3.next();
				System.out.println();
				System.out.println(threadId+"###final###"+news.getView());
				System.out.println(threadId+"###time###"+(System.currentTimeMillis()-begin));
				System.out.println();
			}
			
			
	}
	

	public static void main(String[] args) {
		for(int i=0;i<1000;i++){
			new TestThread(3,i).start();
		}
		
	}
	
	
}
