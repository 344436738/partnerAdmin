package com.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.base.annotation.Module;
import com.base.bean.SysOrg;
import com.base.bean.SysUser;
import com.base.model.SysOrgModel;
import com.base.service.SysOrgService;
import com.base.service.SysUserService;
import com.base.utils.Constant;
import com.base.utils.SessionUtils;
import com.google.gson.Gson;
import com.ibm.icu.text.SimpleDateFormat;

@Controller
@RequestMapping("/sysOrg")
public class SysOrgAction extends BaseAction {

	@Autowired
	private SysOrgService<SysOrg> orgService;
	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SysUserService<SysUser> sysUserService;
	/**
	 * 获取所有节点信息
	 * @param orgModel
	 * @param response
	 * @throws JSONException
	 * @throws IOException
	 */
	@RequestMapping("/findList")
	@ResponseBody
	public void findList(SysOrgModel orgModel,HttpServletResponse response,HttpServletRequest request) throws JSONException, IOException{
		List<SysOrg> list  =new ArrayList<SysOrg>();
		list = orgService.getOrgList(orgModel,request);	
		String json = new Gson().toJson(list);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json.toString());	
	}
	
	/**
	 * 跳转页面
	 * @return
	 */
	@RequestMapping("/sysOrgJsp")
	public String sysOrgJsp(){
		return "sys/sysOrg";
	}
	
	/**
	 * 删除节点信息
	 * @param id
	 * @return
	 */
	@Module(remark=("删除组织机构"),module=("组织机构管理"))
	@RequestMapping("/deleteOrgNodes")
	@ResponseBody
	public String deleteSysOrgNodes(String id){
		SysOrg sysOrg=new SysOrg();
		sysOrg.setDeleted(Constant.SYSORG_INVALID);
		sysOrg.setId(Integer.parseInt(id));
		orgService.updateOrgNodes(sysOrg);
		return "success";
	}
	
	/**
	 * 增加节点信息
	 * @param sysOrg
	 * @return
	 */
	@Module(remark=("增加组织机构"),module=("组织机构管理"))
	@RequestMapping("/addOrgNodes")
	@ResponseBody
	public String addSysOrgNodes(SysOrg sysOrg,Integer ordId,String orgUserNickeName,HttpServletRequest request){
		if(null!=ordId && null!=orgUserNickeName){
			SysUser user=new SysUser();
			user.setOrgId(ordId);
			user.setNickName(orgUserNickeName);
			user=sysUserService.getUserByOrgIdOrNickName(user);
			sysOrg.setPrincipal(user.getId());
		}
		sysOrg.setDeleted(0);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String xDate = sdf.format(date);
		sysOrg.setCreatedTime(xDate);
		sysOrg.setCreatedBy(SessionUtils.getUser(request).getId());
		orgService.addOrgNodes(sysOrg);	
		return "success";
	}
	
	/**
	 * 修改节点信息
	 * @param sysOrg
	 * @return
	 */
	@Module(remark=("修改组织机构"),module=("组织机构管理"))
	@RequestMapping("/updateOrgNodes")
	@ResponseBody
	public String updateSysOrgNodes(SysOrg sysOrg,Integer ordId,String orgUserNickeName,HttpServletRequest request){
		if(null!=ordId && null!=orgUserNickeName){
			SysUser user=new SysUser();
			user.setOrgId(ordId);
			user.setNickName(orgUserNickeName);
			user=sysUserService.getUserByOrgIdOrNickName(user);
			sysOrg.setPrincipal(user.getId());
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String xDate = sdf.format(date);
		sysOrg.setCreatedTime(xDate);
		sysOrg.setCreatedBy(SessionUtils.getUser(request).getId());
		orgService.updateOrgNodes(sysOrg);
		return "success";
	}
	
	/**
	 * 移动节点
	 * @param sysOrg
	 * @return
	 */
	@RequestMapping("/zTreeOnDrop")
	@ResponseBody
	public String zTreeOnDrop(SysOrg sysOrg){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String xDate = sdf.format(date);
		sysOrg.setCreatedTime(xDate);
		orgService.zTreeOnDrop(sysOrg);
		return "success";
	}
	
	
	/**
	 * 查询单条节点信息
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping("/getOrgById")
	@ResponseBody
	public ModelAndView getOrgById(Integer id,HttpServletRequest request, HttpServletResponse response){
		Map<String,Object>  context = getRootMap();
		SysOrg sysOrg = orgService.getOrgById(id);
		context.put("org", sysOrg);
		context.put("type", request.getParameter("type"));
		return forword("sys/sysOrgInfo", context);
	}
	
	/**
	 * 查询组织机构下的人员
	 * @param orgModel
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("queryByUser")
	public void queryOrgByUserName(SysOrgModel orgModel,HttpServletResponse response,HttpServletRequest request) throws Exception{
		List<SysOrg> orgList  =new ArrayList<SysOrg>();
		orgList = orgService.queryByUser(orgModel,request); 
		String json = new Gson().toJson(orgList);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json.toString());		
	}

}
