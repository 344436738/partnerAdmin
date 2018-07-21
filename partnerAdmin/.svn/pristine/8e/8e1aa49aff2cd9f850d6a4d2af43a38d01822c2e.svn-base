package com.base.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.bean.SiteColumn;
import com.base.model.SiteColumnModel;
import com.base.service.SiteColumnService;

@Controller
@RequestMapping("/sys/siteColumn")
public class SiteColumnAction extends BaseAction {

	private final static Logger log = Logger.getLogger(SiteColumnAction.class);

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SiteColumnService<SiteColumn> siteColumnService;

	/**
	 * ilook 首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(SiteColumnModel model, HttpServletRequest request)
			throws Exception {
		Map<String, Object> context = getRootMap();
		List<SiteColumn> dataList = siteColumnService.queryByList(model);
		// 设置页面数据
		context.put("dataList", dataList);
		return forword("siteColumn/list", context);
	}
}
