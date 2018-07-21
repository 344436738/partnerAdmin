package com.base.action;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.base.redis.RedisDao;
import com.base.utils.HtmlUtil;

@Controller
@RequestMapping("/redisDemo")
public class RedisAction extends BaseAction {
	//取redis配置文件
	private static ResourceBundle res = ResourceBundle.getBundle("redis");
	
	@Autowired
	private RedisDao redisDao;
	
	/**
	 * 向redis缓存中存入数据
	 * @param key
	 * @param value
	 * @param response
	 */
	@RequestMapping("/insertRedis")
	public void insertRedis(String key,String value,HttpServletResponse response){		
		redisDao.set("demo"+key,value);
		sendSuccessMessage(response, "保存成功");		
	}
	
	/**
	 * 从redis缓存中读取数据
	 * @param model
	 * @param key
	 * @param response
	 */
	@RequestMapping("getRedisInfo")
	public void getRedisInfo(Model model,String key,HttpServletResponse response){		
		String value = redisDao.get("demo"+key);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, "获取成功");
		result.put("value", value);
		HtmlUtil.writerJson(response, result);
	}
	
	/**
	 * 读取redis配置文件中的数据
	 * @param response
	 * @return
	 */
	@RequestMapping("/showRedisInfo")
	public ModelAndView showRedisInfo(HttpServletResponse response){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("host", res.getString("redis.pool.host"));
		context.put("port", res.getString("redis.pool.port"));
		context.put("maxIdle", res.getString("redis.pool.maxIdle"));
		context.put("maxTotal", res.getString("redis.pool.maxTotal"));
		context.put("timeBetweenEvictionRunsMillis", res.getString("redis.pool.timeBetweenEvictionRunsMillis"));
		context.put("minEvictableIdleTimeMillis", res.getString("redis.pool.minEvictableIdleTimeMillis"));
		context.put("testOnBorrow", res.getString("redis.pool.testOnBorrow"));
		context.put("encode", res.getString("redis.encode"));
		context.put("expire", res.getString("redis.expire"));
		context.put("unlock", res.getString("redis.unlock"));		
		return forword("demo/redisDemo", context);
  	}	  
}
