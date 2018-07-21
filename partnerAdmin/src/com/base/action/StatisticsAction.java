package com.base.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.base.bean.SiteNews;
import com.base.bean.SysLog;
import com.base.bean.SysUser;
import com.base.model.SiteNewsModel;
import com.base.model.SysLogModel;
import com.base.service.SiteNewsService;
import com.base.service.SysLogService;
import com.base.utils.HtmlUtil;

@Controller
@RequestMapping("/statistics")
public class StatisticsAction extends BaseAction{
	
	@Autowired(required = false)
	private SysLogService<SysUser> sysLogService;
	
	@Autowired(required = false)
	private SiteNewsService<SiteNews> newsService;
	
	/**
	 * 登录统计页
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/loginList")
	public ModelAndView loginList(HttpServletResponse response,SysLogModel sysLogModel) throws Exception{
		Map<String, Object> context = new HashMap<String, Object>();
		sysLogModel.getMinOperationTime();
		sysLogModel.getMaxOperationTime();
		List<SysLog> dataList = sysLogService.foundByList(sysLogModel);
		context.put("rows", dataList);
		return forword("statistics/loginStatistics", context);
	}
		
	/**
	 * 登录统计
	 * @param response
	 * @param sysUserModel
	 * @throws Exception
	 */
	@RequestMapping("/statisticSdataList")
	public void statisticSdataList(HttpServletResponse response,SysLogModel sysLogModel) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		List<SysLog> dataList = sysLogService.foundByList(sysLogModel);
		jsonMap.put("total", sysLogModel.getPager().getRowCount());
		jsonMap.put("pageSize", sysLogModel.getPager().getPageSize());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	

	/**
	 * 栏目访问量统计页面
	 * @param response
	 * @param newsColumnModel
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/columnPVList")
	public ModelAndView columnPVList(HttpServletResponse response,SiteNewsModel siteNewsModel) throws Exception{
		Map<String,Object> context = new HashMap<String, Object>();
		List<SiteNews> dataList = newsService.queryColunmnPVList(siteNewsModel);
		context.put("rows", dataList);
		return forword("statistics/columnPVStatistics", context);
	}
	
	/**
	 * 栏目访问量统计
	 * @param response
	 * @param siteNewsColumnModel
	 * @throws Exception
	 */
		@RequestMapping("/columnPVStatisticsDateList")
		public void columnPVStatistics(HttpServletResponse response,SiteNewsModel siteNewsModel) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		List<SiteNews> dataList = newsService.queryColunmnPVList(siteNewsModel);
//		jsonMap.put("total", siteNewsModel.getPager().getRowCount());
//		jsonMap.put("pageSize", siteNewsModel.getPager().getPageSize());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	
	/**
	 * 栏目访问量统计页面
	 * @param response
	 * @param newsColumnModel
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/columnReleaseList")
	public ModelAndView columnReleaseList(HttpServletResponse response,SiteNewsModel siteNewsModel) throws Exception{
		Map<String,Object> context = new HashMap<String, Object>();
		List<SiteNews> dataList = newsService.queryColunmnReleaseList(siteNewsModel);
		context.put("rows", dataList);
		return forword("statistics/columnReleaseStatistics", context);
	}
	
	/**
	 * 栏目访问量统计
	 * @param response
	 * @param siteNewsColumnModel
	 * @throws Exception
	 */
		@RequestMapping("/columnReleaseStatisticsDateList")
		public void columnReleaseStatisticsDateList(HttpServletResponse response,SiteNewsModel siteNewsModel) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		List<SiteNews> dataList = newsService.queryColunmnReleaseList(siteNewsModel);
//		jsonMap.put("total", siteNewsModel.getPager().getRowCount());
//		jsonMap.put("pageSize", siteNewsModel.getPager().getPageSize());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}

}
