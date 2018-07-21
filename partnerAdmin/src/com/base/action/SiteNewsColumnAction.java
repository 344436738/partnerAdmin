package com.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.base.annotation.Module;
import com.base.bean.SiteNewsColumn;
import com.base.model.SiteNewsColumnModel;
import com.base.service.SiteNewsColumnService;
import com.base.utils.HtmlUtil;
import com.base.utils.SessionUtils;

@Controller
@RequestMapping("/siteNewsColumn")
public class SiteNewsColumnAction extends BaseAction {

	private final static Logger log = Logger
			.getLogger(SiteNewsColumnAction.class);

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SiteNewsColumnService<SiteNewsColumn> siteNewsColumnService;
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(SiteNewsColumnModel model,
			HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		return forword("siteNews/siteNewsColumn", context);
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
	public void datalist(SiteNewsColumnModel model, HttpServletResponse response)
			throws Exception {
		// model.setDeleted(0);
		List<SiteNewsColumn> dataList = siteNewsColumnService.queryByList(model);
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
	@Module(remark=("保存新闻栏目"),module=("栏目管理"))
	@RequestMapping("/save")
	public void save(SiteNewsColumn bean, Integer[] typeIds,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		// bean.setDeleted(DELETED.NO.key);
			if (bean.getId() == null) {
				bean.setCreateUer(SessionUtils.getUserId(request));
				siteNewsColumnService.add(bean);
			} else {
				siteNewsColumnService.updateBySelective(bean);
			}
			
			sendSuccessMessage(response, "保存成功~");
			return;
	}
	
	@RequestMapping("/getId")
	public void getId(Integer id, HttpServletResponse response)
			throws Exception {
		Map<String, Object> context = new HashMap();
		SiteNewsColumn bean = siteNewsColumnService.queryById(id);
		if (bean == null) {
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}

	@Module(remark=("删除新闻栏目"),module=("栏目管理"))
	@RequestMapping("/delete")
	public void delete(Integer[] id, HttpServletResponse response)
			throws Exception {
		siteNewsColumnService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	
	/**
	 * 新闻栏目树
	 * @param model
	 * @param response
	 * @param request
	 * @throws JSONException
	 * @throws IOException
	 */
	
	@RequestMapping("/findNewsColumnList")
	@ResponseBody
	public void findNewsColumnList(SiteNewsColumnModel model,HttpServletResponse response) throws JSONException, IOException{
		List<SiteNewsColumn> list  =new ArrayList<SiteNewsColumn>();
		list = siteNewsColumnService.findNewsColumnList(model);
		HtmlUtil.writerJson(response, list);	
	}
}
