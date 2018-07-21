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

import com.base.bean.WebPage;
import com.base.bean.BaseBean.DELETED;
import com.base.model.WebPageModel;
import com.base.service.WebPageService;
import com.base.utils.DateUtil;
import com.base.utils.HtmlUtil;
import com.base.utils.SessionUtils;

@Controller
@RequestMapping("/webPage")
public class WebPageAction extends BaseAction {

	private final static Logger log = Logger.getLogger(WebPageAction.class);

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WebPageService<WebPage> webPageService;

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(WebPageModel model, HttpServletRequest request)
			throws Exception {
		Map<String, Object> context = getRootMap();
		return forword("web/webPage", context);
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
	public void datalist(WebPageModel model, HttpServletResponse response)
			throws Exception {
		model.setDeleted(0);
		List<WebPage> dataList = webPageService.queryByList(model);
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
	public void save(WebPage bean, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		bean.setDeleted(DELETED.NO.key);
		Integer userId = SessionUtils.getUserId(request);
		if (bean.getId() == null) {
			bean.setCreateBy(userId);
			webPageService.add(bean);
		} else {
			bean.setUpdateBy(userId);
			bean.setUpdateTime(DateUtil.getDateByString(""));
			webPageService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}

	/**
	 * 保存页面模板
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveTemplate")
	public void saveTemplate(WebPage bean, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		Integer userId = SessionUtils.getUserId(request);
		if (bean.getId() == null) {
			sendFailureMessage(response, "保存失败,没有对应页面.");
			return;
		} else {
			bean.setUpdateBy(userId);
			bean.setUpdateTime(DateUtil.getDateByString(""));
			webPageService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}

	@RequestMapping("/getId")
	public void getId(Integer id, HttpServletResponse response)
			throws Exception {
		Map<String, Object> context = new HashMap();
		WebPage bean = webPageService.queryById(id);
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
		webPageService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
