package com.base.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.bean.SiteNewsData;
import com.base.bean.SiteNewsData;
import com.base.bean.SiteType;
import com.base.model.SiteNewsDataModel;
import com.base.model.SiteNewsDataModel;
import com.base.service.SiteNewsDataService;
import com.base.utils.HtmlUtil;
import com.base.bean.SiteNewsData;
import com.base.bean.BaseBean.DELETED;
import com.base.model.SiteNewsDataModel;

@Controller
@RequestMapping("/siteNewsData")
public class SiteNewsDataAction extends BaseAction {

	private final static Logger log = Logger
			.getLogger(SiteNewsDataAction.class);

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SiteNewsDataService<SiteNewsData> siteNewsDataService;

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(SiteNewsDataModel model, HttpServletRequest request)
			throws Exception {
		Map<String, Object> context = getRootMap();
		return forword("siteNews/sitenewsData", context);
	}

	/**
	 * ilook 首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void datalist(SiteNewsDataModel model, HttpServletResponse response)
			throws Exception {
		// model.setDeleted(0);
		List<SiteNewsData> dataList = siteNewsDataService.queryByList(model);
		// 设置页面数据
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}

	/**
	 * 添加或修改数据
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public void save(SiteNewsData bean, Integer[] typeIds,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		// bean.setDeleted(DELETED.NO.key);
		if (bean.getId() == null) {
			siteNewsDataService.add(bean);
		} else {
			siteNewsDataService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}

	@RequestMapping("/getId")
	public void getId(Integer id, HttpServletResponse response)
			throws Exception {
		Map<String, Object> context = new HashMap();
		SiteNewsData bean = siteNewsDataService.queryById(id);
		if (bean == null) {
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}

	@RequestMapping("/delete")
	public void delete(Integer id, HttpServletResponse response)
			throws Exception {
		siteNewsDataService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
