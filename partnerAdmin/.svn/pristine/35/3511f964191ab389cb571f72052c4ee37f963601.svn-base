package com.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.base.bean.SiteNews;
import com.base.bean.SiteNewsColumn;
import com.base.bean.SiteNewsData;
import com.base.mapper.SiteNewsDataMapper;
import com.base.mapper.SiteNewsMapper;
import com.base.model.SiteNewsModel;
import com.base.redis.RedisDao;
import com.base.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

/**
 * 
 * <br>
 * <b>功能：</b>SiteNewsService<br>
 *
 */
@Scope("singleton")
@Service("siteNewsService")

public class SiteNewsService<T> extends BaseService<T> {
	//private final static Logger log = Logger.getLogger(SiteNewsService.class);

	public static List<SiteNews> viewlist = new ArrayList();

	public static int testcount = 0;

	@Autowired
	private SiteNewsMapper<T> mapper;

	@Autowired
	private SiteNewsDataMapper<SiteNewsData> newsDatamapper;

//	@Autowired
//	private RedisDao redisDao;

	@Override
	public void add(T t) throws Exception {
		SiteNews n = (SiteNews) t;
		if(n.getNewsData()==null){
			SiteNewsData snd=new SiteNewsData();
			snd.setContent("");
			n.setNewsData(snd);
		}
		newsDatamapper.add(n.getNewsData());
		n.setContentid(n.getNewsData().getId());
		super.add((T) n);
	}

	@Override
	public void updateBySelective(T t) throws Exception {
		SiteNews n = (SiteNews) t;
		if(n.getNewsData()==null){
			SiteNewsData snd=new SiteNewsData();
			snd.setContent("");
			n.setNewsData(snd);
		}
		n.getNewsData().setId(n.getContentid());
		newsDatamapper.updateBySelective(n.getNewsData());
		super.updateBySelective((T) n);
	}

	@Override
	public void delete(Object... ids) throws Exception {
		if (ids == null || ids.length < 1) {
			return;
		}
		for (Object id : ids) {
			newsDatamapper.delete(((SiteNews) getMapper().queryById(id))
					.getContentid());
			getMapper().delete(id);
		}
	}

	@Override
	public T queryById(Object id) throws Exception {

		Object news = mapper.queryById(id);
		if (news != null) {
			((SiteNews) news).setNewsData(newsDatamapper.queryById(((SiteNews) news).getContentid()));
		}
		return (T) news;
	}

	/**
	 * 增加新闻的访问量(基础方式、不启用redis时使用)
	 * 
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public void updateViewsBasic(Integer id) {
		
		if(Constant.UPDATEVIEW_TYPE==0){
			//判断，是否启用redis  ，配置文件，里是否启用redis
			SiteNews sn =new  SiteNews();
			sn.setId(id);
			sn.setView(1);
			this.updateView(sn);
		}else if(Constant.UPDATEVIEW_TYPE==1){
			//不启用的时候
			SiteNews sn = findNewsInCacheBasic(id);//
			if (sn == null) {
				SiteNews news = new SiteNews();
				news.setId(id);
				news.setView(1);
				viewlist.add(news);
			} else {
				sn.setViewAdd(); 
			}
		}else if(Constant.UPDATEVIEW_TYPE==2){
			//启用的时候
			//从redis中找，是否有该值
			//如果没有，创建，有的话，更新加一
//			String newsNumJson=this.findNewsInCacheRedis();
			String newsNumJson=null;
			Map<Integer, Integer> map=null;
			if(newsNumJson==null){
				map=new ConcurrentHashMap<Integer, Integer>();
				map.put(id, 1);
			}else {
				map = new Gson().fromJson(newsNumJson, ConcurrentHashMap.class);
				if(map.containsKey(id)){
					Integer val = map.get(id);
					map.put(id, val+1);
				}else{
					map.put(id, 1);
				}
			}
			newsNumJson = new Gson().toJson(map);
//			this.setNewsInCacheRedis(newsNumJson);
				
		}
		
		for(int i=0;i<viewlist.size();i++){
			System.out.println("访问id为"+viewlist.get(i).getId()+"的新闻"+viewlist.get(i).getView()+"次");
		}
	}
	

	/**
	 * 在cache中找到该元素(非redis方式)
	 * 
	 * @param id
	 * @return
	 */
	private SiteNews findNewsInCacheBasic(Integer id) {
		//从静态变量  cache里找
		for (int i = 0; i < viewlist.size(); i++) {
			if (id.equals(viewlist.get(i).getId())) {
				return viewlist.get(i);
			}
		}
		return null;
	}

//
//	/**
//	 * 在cache中找到该元素(redis方式)
//	 * 
//	 * @param id
//	 * @return
//	 */
//	private String findNewsInCacheRedis() {
//		
//		
//		return  redisDao.get("newsNumJson");
//	}
//	/**
//	 * 增加新闻的访问量(启用redis时使用)
//	 * 
//	 * @param id
//	 */
//	private void setNewsInCacheRedis(String newsNumJson) {
//			redisDao.set("newsNumJson", newsNumJson);
//	}

	/**
	 * 更新新闻的访问量
	 * 
	 * @param siteNews
	 */
	public void updateView(SiteNews siteNews){
		mapper.updateView( (T) siteNews);
	}
	
	public SiteNewsMapper<T> getMapper() {
		return mapper;
	}

	public List<SiteNews> queryColunmnPVList(SiteNewsModel siteNewsModel) throws Exception{
		return mapper.queryColunmnPVList(siteNewsModel);
	}
	
	public List<SiteNews> queryColunmnReleaseList(SiteNewsModel siteNewsModel) throws Exception{
		return mapper.queryColunmnReleaseList(siteNewsModel);
	}
}
