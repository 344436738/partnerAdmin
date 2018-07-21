package com.base.redis.impl;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.base.bean.SiteNews;
import com.base.redis.RedisDao;
import com.base.redis.template.AbstractBaseRedisDao;

public class RedisDaoImpl extends AbstractBaseRedisDao<String, Object> implements
		RedisDao {

	/**
	 * Dao
	 * 
	 * @author http://blog.csdn.net/java2000_wl
	 * @version <b>1.0</b>
	 */

	
	/**
	 * 设置时间超时
	 * @param key
	 * @param timeout
	 */
//	public void expire(String key,long timeout){
//		redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
//	}
//	
	
	/**
	 * 超时<br>
	 * ------------------------------<br>
	 * 
	 * @param list2
	 */

	@Override
	public void expire(final String key,final long timeout){
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.expire(redisTemplate.getStringSerializer().serialize(
						key),timeout);
				return null;
			}
		});
	}

	/**
	 * set <br>
	 * ------------------------------<br>
	 * 
	 * @param user
	 * @return
	 */
	public Object set(final String key, final String value) {
		return redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keys = serializer.serialize(key);
				byte[] names = serializer.serialize(value);
				connection.set(keys, names);
				return null;
			}
		});
	}
	
	

	/**
	 * add <br>
	 * ------------------------------<br>
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(final String key, final String value) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keys = serializer.serialize(key);
				byte[] names = serializer.serialize(value);
				return connection.setNX(keys, names);
			}
		});
		return result;
	}

	/**
	 * 批量新增 使用pipeline方式 <br>
	 * ------------------------------<br>
	 * 
	 * @param list
	 *@return
	 */
	/*
	 * public boolean add(final List<User> list) { Assert.notEmpty(list);
	 * boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
	 * public Boolean doInRedis(RedisConnection connection) throws
	 * DataAccessException { RedisSerializer<String> serializer =
	 * getRedisSerializer(); for (User user : list) { byte[] key =
	 * serializer.serialize(user.getId()); byte[] name =
	 * serializer.serialize(user.getName()); connection.setNX(key, name); }
	 * return true; } }, false, true); return result; }
	 */
	/**
	 * 删除 <br>
	 * ------------------------------<br>
	 * 
	 * @param list2
	 */

	@Override
	public void delete(final String keyId) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.del(redisTemplate.getStringSerializer().serialize(
						keyId));
				return null;
			}
		});
	}

	/**
	 * 删除多个 <br>
	 * ------------------------------<br>
	 * 
	 * @param keys
	 */
	/*
	 * public void delete(List<String> keys) { redisTemplate.delete(keys); }
	 */
	/**
	 * 修改 <br>
	 * ------------------------------<br>
	 * 
	 * @param user
	 * @return
	 */
	public boolean update(final String key, final String value) {
		if (get(key) == null) {
			throw new NullPointerException("数据行不存在, key = " + key);
		}
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keys = serializer.serialize(key);
				byte[] names = serializer.serialize(value);
				connection.set(keys, names);
				return true;
			}
		});
		return result;
	}

	/**
	 * 通过key获取 <br>
	 * ------------------------------<br>
	 * 
	 * @param keyId
	 * @return
	 */
	@Override
	public String get(final String keyId) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(keyId);
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				String name = serializer.deserialize(value);
				return new String(name);
			}
		});
		return result;
	}
/*
	@Override
	public SiteNews getId(final String keyId) {
		SiteNews result = redisTemplate.execute(new RedisCallback<SiteNews>() {

			@Override
			public SiteNews doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(keyId);
				connection.keys(key);
				serializer.deserialize(key);
				return null ;
			}
		});
		return null;
	}*/

	
}
