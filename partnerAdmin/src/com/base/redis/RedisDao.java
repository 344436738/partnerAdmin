package com.base.redis;

import java.util.List;

import com.base.bean.SiteNews;

public interface RedisDao {
	/**
	 * 新增 <br>
	 * ------------------------------<br>
	 * 
	 * @param user
	 * @return
	 */
	boolean add(String key, String value);

	/**
	 * set <br>
	 * ------------------------------<br>
	 * 
	 * @param user
	 * @return
	 */
	Object set(String key, String value);

	/**
	 * 批量新增 使用pipeline方式 <br>
	 * ------------------------------<br>
	 * 
	 * @param list
	 * @return
	 */
	// boolean add(List<User> list);

	/**
	 * 删除 <br>
	 * ------------------------------<br>
	 * 
	 * @param key
	 * @return
	 */
	void delete(String key);

	/**
	 * 删除多个 <br>
	 * ------------------------------<br>
	 * 
	 * @param keys
	 */
	// void delete(List<String> keys);

	/**
	 * 修改 <br>
	 * ------------------------------<br>
	 * 
	 * @param user
	 * @return
	 */
	boolean update(String key, String value);

	// boolean update(User user);

	/**
	 * 通过key获取 <br>
	 * ------------------------------<br>
	 * 
	 * @param keyId
	 * @return
	 */

	String get(String keyId);
	
	
	/**
	 * 设置超时
	 * @param key
	 * @param timeout  时间单位为秒
	 */
	public void expire(String key,long timeout);
	/**
	 * 通过i获取 <br>
	 * ------------------------------<br>
	 * 
	 * @param keyId
	 * @return
	 */

//	SiteNews getId(String keyId);
	
}
