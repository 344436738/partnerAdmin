package com.base.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.base.bean.SiteNews;
import com.base.mapper.SiteNewsMapper;
import com.base.redis.RedisDao;

/**
 * 测试
 * 
 * @author
 * @version <b>1.0</b>
 */

@ContextConfiguration(locations = { "classpath:spring-redis.xml" })
public class TestRedis extends AbstractJUnit4SpringContextTests {

	@Autowired
	private RedisDao redisDao;
	
	/**
	 * 新增
	 * 
	 */
	@Test
	public void testAdd() {
		for(int i=0;i<100000;i++){
			String key = "aaa"+i;
			String value = "bbb"+i;
			boolean result = redisDao.add(key, value);
		}
		
		Assert.assertTrue(true);
	}
	
	
	/**
	 * 过期
	 * 
	 */
	@Test
	public void testExpire() {
		String key = "aaaq";
		long time = 3;
		redisDao.expire(key, time);
		
		Assert.assertTrue(true);
	}

	/**
	 * set
	 * 
	 */
	@Test
	public void set() {
		String user3 = "a3";
		String user4 = "b1";
		Object result = redisDao.set(user3, user4);
		Assert.assertNull(result);
	}

	
	 /**
	 * 批量新增 pipeline方式 <br>
	 * ------------------------------<br>
	 */
	
	/*@Test
	public void testAddUsers2() {
		List<User> list = new ArrayList<User>();
		for (int i = 10; i < 1500000; i++) {
			User user = new User();
			user.setId("user" + i);
			user.setName("java2000_wl" + i);
			list.add(user);
		}
		long begin = System.currentTimeMillis();
		boolean result = userDao.add(list);
		System.out.println(System.currentTimeMillis() - begin);
		Assert.assertTrue(result);
	}*/
	 /**
	 * 修改 <br>
	 * ------------------------------<br>
	 */
	@Test
	public void testUpdate() {
		String key = "user1";
		String value = "test";
		redisDao.set(key, "aaaa");
		Assert.assertEquals(redisDao.get(key), "aaaa");
		boolean result = redisDao.update(key, value);
		Assert.assertTrue(result);
		Assert.assertEquals(redisDao.get(key), value);
	}

	/*
	 * //** 修改2 <br>------------------------------<br>
	 */
	@Test
	public void testUpdate2() {
		String key = "user9999999999";
		String value = "test";
		boolean result = redisDao.update(key, value);
		Assert.assertFalse(result);
	}

	/**
	 * 通过key删除单个 <br>
	 * ------------------------------<br>
	 */
	@Test
	public void testDelete() {
		String keyId = "aaa10";
		redisDao.delete(keyId);
		
	}

	/**
	 * 批量删除 <br>
	 * ------------------------------<br>
	 */
	/*
	 * @Test public void testDeletes() { List<String> list = new
	 * ArrayList<String>(); for (int i = 0; i < 10; i++) { list.add("user" + i);
	 * } userDao.delete(list); }
	 */

	/**
	 * 获取 <br>
	 * ------------------------------<br>
	 */
	@Test
	public void testGet() {
		String keyId = "aaa";
		String user = redisDao.get(keyId);
		Assert.assertNotNull(user);
		Assert.assertEquals(user, "bbb");	
	}

	/**
	 * 设置userDao
	 * 
	 * @param userDao
	 *            the userDao to set
	 */
	public void setRedisDao(RedisDao redisDao) {
		this.redisDao = redisDao;
	}
}
