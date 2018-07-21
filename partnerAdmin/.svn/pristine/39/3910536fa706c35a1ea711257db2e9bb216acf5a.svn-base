package com.base.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.base.annotation.Auth;
import com.base.annotation.Module;
import com.base.bean.SiteNews;
import com.base.bean.SiteNewsColumn;
import com.base.model.SiteNewsModel;
import com.base.service.SiteNewsColumnService;
import com.base.service.SiteNewsService;
import com.base.utils.FileUploadUtil;
import com.base.utils.HtmlUtil;
import com.base.utils.SessionUtils;

@Controller
@RequestMapping("/siteNews")
public class SiteNewsAction extends BaseAction {

	//private final static Logger log = Logger.getLogger(SiteNewsAction.class);

	// Servrice start
	@Autowired
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SiteNewsService<SiteNews> siteNewsService;
	@Autowired(required = false)
	private SiteNewsColumnService<SiteNewsColumn> siteNewsColumnService;

	
	
	/**
	 * 后台管理页面
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bkManage")
	public ModelAndView childColumnList(SiteNewsModel model,
			HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		context.put("columnList", siteNewsColumnService.queryAllColumnWithChild());
		context.put("queryPid", model.getPid());
		return forword("siteNews/siteNews", context);
	}
	
	/**
	 * 添加新闻内容
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toAddNews")
	public ModelAndView addNews(SiteNewsModel model,
			HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		context.put("columnList", siteNewsColumnService.queryAllColumnWithChild());
		context.put("id", model.getId());
		return forword("siteNews/add", context);
	}

	/**
	 * 后天管理 列表数据
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void datalist(SiteNewsModel model, HttpServletResponse response)
			throws Exception {
		// model.setDeleted(0);
		Map<String, Object> context = getRootMap();
		context.put("columnList", siteNewsColumnService.queryAllColumnWithChild());
		List<SiteNews> dataList = siteNewsService.queryByList(model);
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
	@Module(remark=("保存内容"),module=("内容管理"))
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/save")
	public void save(SiteNews bean, Integer[] typeIds,MultipartFile file,String oldpath,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		// bean.setDeleted(DELETED.NO.key);
		
		if (bean.getId() == null) {
			bean.setCreateUser(SessionUtils.getUserId(request));
			String reladdress=FileUploadUtil.upload(request, file, "/ColumnPicture/",null);	
			String picName = reladdress.substring(reladdress.lastIndexOf("."));	//截取字符串后缀名		
			if(!".jpg".equals(picName) && !".jpeg".equals(picName) && !".gif".equals(picName) && !".png".equals(picName)){
				sendFailureMessage(response, "只能上传jpg、jpeg、gif,png格式的图片!");
			}else{
				bean.setPic(reladdress);
				siteNewsService.add(bean);
			}			
		} else {
			String reladdress=FileUploadUtil.upload(request, file, "/ColumnPicture/",oldpath);
			String picName = reladdress.substring(reladdress.lastIndexOf("."));	//截取字符串后缀名		
			if(!".jpg".equals(picName) && !".jpeg".equals(picName) && !".gif".equals(picName) && !".png".equals(picName)){
				sendFailureMessage(response, "只能上传jpg、jpeg、gif、png格式的图片!");
			}else{
				bean.setPic(reladdress);
				siteNewsService.add(bean);
			}			
		}		
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, "保存成功~");
		HtmlUtil.writerJsonForJqueryFormMultipartFile(response, result);		
	}


	@RequestMapping("/getId")
	public void getId(Integer id, HttpServletResponse response)
			throws Exception {
		Map<String, Object> context = new HashMap();
		SiteNews bean = siteNewsService.queryById(id);
		if (bean == null) {
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	@Module(remark=("删除内容"),module=("内容管理"))
	@RequestMapping("/delete")
	public void delete(Integer[] id, HttpServletResponse response)
			throws Exception {
		siteNewsService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	
	/**
	 * 前台列表页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("list")
	public ModelAndView list(SiteNewsModel model,Integer pid,HttpServletRequest request)
			throws Exception {			
		if(null!=pid){
			SiteNewsColumn siteNewsColumn = siteNewsColumnService.getSiteNewsColumn(pid);
			ModelAndView mv = new ModelAndView(siteNewsColumn.getTemplate());
			mv.addObject("columnList", siteNewsColumnService.queryAllColumnWithChild());
			model.setOrder("desc");
			model.setSort("createTime");
			mv.addObject("sitenewsList",  siteNewsService.queryByList(model));
			mv.addObject("total", model.getPager().getRowCount());
			mv.addObject("totalPage", model.getPager().getPageCount());
			mv.addObject("currentPage", model.getPage());
			return mv;
		}
		Map<String, Object> context = getRootMap();
		context.put("columnList", siteNewsColumnService.queryAllColumnWithChild());
		model.setOrder("desc");
		model.setSort("createTime");
		context.put("sitenewsList",  siteNewsService.queryByList(model));
		context.put("total", model.getPager().getRowCount());
		context.put("totalPage", model.getPager().getPageCount());
		context.put("currentPage", model.getPage());
		return forword("siteNews/list", context);	
	}


	/**
	 * 前台列表内容页
	 * 
	 * @param url
	 * @param classifyIdl
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/show")
	public ModelAndView show(SiteNewsModel model, HttpServletRequest request)
			throws Exception {
		Map<String, Object> context = getRootMap();
//		 model.setStatus(1);//正式 非草稿新闻
		 context.put("news", siteNewsService.queryById(model.getId()));//
		// context.put("comments", arg1)//取出评论放入内容页 //目前一期先不需要
		siteNewsService.updateViewsBasic(model.getId());//增加新闻的访问量

		// System.out.println(siteNewsService.viewlist.get(0).getView());
		context.put("columnList", siteNewsColumnService.queryAllColumnWithChild());
//		context.put("sitenewsList",  siteNewsService.queryByList(model));
		return forword("siteNews/show", context);
	}
	
	/**
	 * 新闻内容预览
	 * @param news
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/preview")
	public ModelAndView preview(SiteNews news, HttpServletRequest request)
			throws Exception {
		Map<String, Object> context = getRootMap();
		Date date =  new Date();
		news.setCreateTime(date);
		news.setCreateUser(SessionUtils.getUserId(request));
		context.put("news",  news);//		 
		context.put("columnList", siteNewsColumnService.queryAllColumnWithChild());
		return forword("siteNews/show", context);
	}
}
