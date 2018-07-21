package com.base.test;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.base.bean.SiteMain;
import com.base.bean.SiteNewsColumn;
import com.base.bean.SiteNewsData;
import com.base.mapper.SiteNewsColumnMapper;
import com.base.model.SiteMainModel;
import com.base.model.SiteNewsColumnModel;
import com.base.model.SiteNewsDataModel;
import com.base.service.SiteNewsColumnService;
import com.base.service.SiteNewsDataService;

/**
 * 测试
 * 
 * @author
 * @version <b>1.0</b>
 */

@ContextConfiguration(locations = { "classpath:spring-common.xml" })
public class TestSiteNewsData extends AbstractJUnit4SpringContextTests {

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
		SiteNewsData newsData = new SiteNewsData();
		newsData.setId(4);
		newsData.setContent("CDN的全称是Content Delivery Network，即内容分发网络。");
		newsData.setDescription("不错");
		newsData.setFile("index.jsp");
		newsData.setKeywords("嘿嘿");
		siteNewsDataService.add(newsData);
	}

	/**
	 * 修改 <br>
	 * ------------------------------<br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		SiteNewsData newsData = new SiteNewsData();
		newsData.setId(1);
		newsData.setContent("CDN的全称是Content Delivery Network，即内容分发网络。");
		newsData.setDescription("不错");
		newsData.setFile("index.jsp");
		newsData.setKeywords("呵呵");
		siteNewsDataService.update(newsData);
		System.out.println("id" + newsData.getId());
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
		siteNewsDataService.delete(1);

	}

	/**
	 * 获取 <br>
	 * ------------------------------<br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testQeuryList() throws Exception {
		SiteNewsDataModel model = new SiteNewsDataModel();
		model.setRows(10);
		model.setPage(1);
		List<SiteNewsColumn> list = siteNewsDataService.queryByList(model);
		Assert.assertNotNull(list);

	}

	/**
	 * 获取 <br>
	 * ------------------------------<br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGet() throws Exception {

	}

	/**
	 * 设置userService
	 * 
	 * @param userService
	 *            the userService to set
	 */

	public SiteNewsDataService getSiteNewsDataService() {
		return siteNewsDataService;
	}

	public void setSiteNewsDataService(SiteNewsDataService siteNewsDataService) {
		this.siteNewsDataService = siteNewsDataService;
	}

}
