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
import com.base.mapper.SiteNewsColumnMapper;
import com.base.model.SiteMainModel;
import com.base.model.SiteNewsColumnModel;
import com.base.service.SiteNewsColumnService;

/**
 * 测试
 * 
 * @author
 * @version <b>1.0</b>
 */

@ContextConfiguration(locations = { "classpath:spring-common.xml" })
public class TestSiteNewsColumn extends AbstractJUnit4SpringContextTests {

	@Autowired
	private SiteNewsColumnService siteNewsColumnService;

	/**
	 * 新增
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testAdd() throws Exception {
		SiteNewsColumn newsColumn = new SiteNewsColumn();
		newsColumn.setId(5);
		newsColumn.setPid(2);
		newsColumn.setName("娱乐");
		newsColumn.setCreateUer(3);
		newsColumn.setDescription("目前,案件在进一步审理中。");
		newsColumn.setForbidden(1);
		newsColumn.setSorting(1);
		siteNewsColumnService.add(newsColumn);
	}

	/**
	 * 修改 <br>
	 * ------------------------------<br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		SiteNewsColumn newsColumn = new SiteNewsColumn();
		newsColumn.setId(1);
		newsColumn.setPid(1);
		newsColumn.setName("体育");
		newsColumn.setCreateUer(1);
		newsColumn.setDescription("嘿嘿嘿嘿");
		newsColumn.setForbidden(1);
		newsColumn.setSorting(2);
		siteNewsColumnService.update(newsColumn);
		System.out.println("id" + newsColumn.getId());
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
		siteNewsColumnService.delete(1);
	}

	/**
	 * 查询数据（带分页） <br>
	 * ------------------------------<br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testQeuryList() throws Exception {
		SiteNewsColumnModel model = new SiteNewsColumnModel();
		model.setRows(10);
		model.setPage(1);
		List<SiteNewsColumn> list = siteNewsColumnService.queryByList(model);
		Assert.assertNotNull(list);

	}

	/**
	 * 查询数据（不带分页） <br>
	 * ------------------------------<br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testQeuryAllColumn() throws Exception {
		List<SiteNewsColumn> list = siteNewsColumnService.queryAllColumn();
		Assert.assertNotNull(list);
	}

	/**
	 * 设置userService
	 * 
	 * @param userService
	 *            the userService to set
	 */

	public SiteNewsColumnService getSiteNewsColumnService() {
		return siteNewsColumnService;
	}

	public void setSiteNewsColumnService(
			SiteNewsColumnService siteNewsColumnService) {
		this.siteNewsColumnService = siteNewsColumnService;
	}
}
