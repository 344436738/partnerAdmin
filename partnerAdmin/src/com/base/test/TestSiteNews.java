package com.base.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.base.bean.SiteNews;
import com.base.bean.SiteNewsData;
import com.base.model.SiteNewsModel;
import com.base.service.SiteNewsDataService;
import com.base.service.SiteNewsService;

/**
 * 测试
 * 
 * @author http://blog.csdn.net/java2000_wl
 * @version &lt;b&gt;1.0&lt;/b&gt;
 */

@ContextConfiguration(locations = { "classpath:spring-common.xml" })
public class TestSiteNews extends AbstractJUnit4SpringContextTests {

	@Autowired
	private SiteNewsService<SiteNews> siteNewsService;
	@Autowired
	private SiteNewsDataService siteNewsDataService;

	/**
	 * 新增
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testAdd() throws Exception {
		SiteNews news = new SiteNews();
		news.setCreateUser(1);
		news.setContentid(1);
		news.setPic("a.jpg");
		news.setPid(1);
		news.setStatus(0);
		news.setSummary("啊哈哈哈");
		news.setTitle("亚冠-生死战!18:30视频播鲁能客战全北");
		news.setView(3);

		SiteNewsData snd = new SiteNewsData();
		snd.setContent("测试新闻内容");
		news.setNewsData(snd);
		siteNewsService.add(news);

		int newsid = news.getId();
		int contentid = news.getNewsData().getId();
		Assert.assertNotNull(siteNewsService.queryById(newsid));
		Assert.assertNotNull(siteNewsDataService.queryById(contentid));
	}

	/**
	 * 修改 <br>
	 * ------------------------------<br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		SiteNews news = new SiteNews();
		news.setId(1);
		news.setContentid(3);
		news.setCreateUser(1);
		news.setPic("a.jpg");
		news.setPid(1);
		news.setStatus(0);
		news.setSummary("嘿嘿额黑");
		news.setTitle("亚冠-生死123123211");
		// siteNewsService.update(news);

		SiteNewsData snd = new SiteNewsData();
		snd.setId(2);
		snd.setContent("哈哈哈哈");
		news.setNewsData(snd);
		siteNewsService.update(news);

		System.out.println("id=" + news.getId());
		Assert.assertEquals(((SiteNews) siteNewsService.queryById(3))
				.getTitle(), "亚冠-生死123123211");
	}

	/**
	 * 通过key删除单个 <br>
	 * ------------------------------<br>
	 * 
	 * @throws Exception
	 *             待修改加条件
	 */
	@Test
	public void testDelete() throws Exception {
		SiteNews news = new SiteNews();
		news.setId(4);
		SiteNewsData snd = new SiteNewsData();
		snd.setId(2);
		news.setNewsData(snd);
		siteNewsService.delete(news);
		Assert.assertNotNull(news);
	}

	/**
	 * 获取 <br>
	 * ------------------------------<br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testQeuryList() throws Exception {
		SiteNewsModel model = new SiteNewsModel();
		model.setRows(10);
		model.setPage(1);
		List<SiteNews> list = siteNewsService.queryByList(model);
		Assert.assertNotNull(list);

	}

	/**
	 * 获取 <br>
	 * ------------------------------<br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetId() throws Exception {
		SiteNews news = siteNewsService.queryById(4);
		Assert.assertNotNull(news);
		Assert.assertNotNull(news.getNewsData());

	}

	/**
	 * 设置userService
	 * 
	 * @param userService
	 *            the userService to set
	 */
	public SiteNewsService getSiteNewsService() {
		return siteNewsService;
	}

	public void setSiteNewsService(SiteNewsService siteNewsService) {
		this.siteNewsService = siteNewsService;
	}

	/**
	 * 增加新闻的访问量
	 * 
	 * @param
	 */
	@Test
	public void updateView() {
		for (int i = 0; i < 50; i++)
			siteNewsService.updateViewsBasic(8);
		for (int i = 0; i < 15; i++)
			siteNewsService.updateViewsBasic(3);
		for (int i = 0; i < 8; i++)
			siteNewsService.updateViewsBasic(8);

		System.out.println(siteNewsService.viewlist.size());

	}

}
