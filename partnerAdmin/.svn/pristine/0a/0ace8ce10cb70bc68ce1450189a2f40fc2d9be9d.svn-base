package com.base.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.base.bean.SysLog;
import com.base.model.SysLogModel;
import com.base.service.SysLogService;
import com.base.utils.HtmlUtil;

@Controller
@RequestMapping("/sysLog")

public class SysLogAction extends BaseAction{
	
	@Autowired
	private SysLogService<SysLog> logService;
	/**
	 * ibook首页
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpServletResponse response){
		Map<String, Object> context = new HashMap<String, Object>();		
		return forword("sys/sysLog", context);
	}
	
	/**
	 * json列表页
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void dataList(HttpServletResponse response,SysLogModel model) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		List<SysLog> dataList = logService.queryByList(model);
		jsonMap.put("total", model.getPager().getRowCount());
		jsonMap.put("pageSize", model.getPager().getPageSize());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	
//	/**
//	 * 单个对象查询
//	 * @param id
//	 * @param response
//	 * @throws Exception
//	 */
//	@RequestMapping("/getId")
//	public void getId(Integer id,HttpServletResponse response) throws Exception{
//		Map<String, Object> context = new HashMap<String, Object>();
//		SysLog bean = logService.queryById(id);
//		context.put(SUCCESS, true);
//		context.put("data", bean);
//		HtmlUtil.writerJson(response, context);
//	}
}
