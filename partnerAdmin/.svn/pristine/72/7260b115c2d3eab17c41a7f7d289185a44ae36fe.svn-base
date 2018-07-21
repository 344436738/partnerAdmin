package com.base.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.base.annotation.Module;
import com.base.bean.SysOrg;
import com.base.bean.SysRole;
import com.base.bean.SysRoleRel;
import com.base.bean.SysUser;
import com.base.bean.BaseBean.DELETED;
import com.base.bean.BaseBean.STATE;
import com.base.model.SysUserModel;
import com.base.service.SysOrgService;
import com.base.service.SysRoleService;
import com.base.service.SysUserService;
import com.base.utils.Constant;
import com.base.utils.HtmlUtil;
import com.base.utils.MethodUtil;
import com.base.utils.ResultUtil;
import com.base.utils.SessionUtils;

@Controller
@RequestMapping("/sysUser")
public class SysUserAction extends BaseAction {

	private final static Logger log = Logger.getLogger(SysUserAction.class);

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SysUserService<SysUser> sysUserService;

	// Servrice start
	@Autowired(required = false)
	private SysRoleService<SysRole> sysRoleService;

	@Autowired(required=false)
	private SysOrgService<SysOrg> orgService;
	
	 /**
	 * ilook 首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(SysUserModel model, HttpServletRequest request)
			throws Exception {
		Map<String, Object> context = getRootMap();
		// 设置页面数据
		context.put("roleList", sysRoleService.queryAllList());
		
		return forword("sys/sysUser", context);
	}

	/**
	 * json 列表页面
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void dataList(SysUserModel model, HttpServletResponse response)
			throws Exception {
		List<SysUser> dataList = new ArrayList<SysUser>();
			 dataList = sysUserService.queryByList(model);
			for (SysUser user : dataList) {
				List<SysRole> list = sysRoleService.queryByUserid(user.getId());
				user.setRoleStr(rolesToStr(list));
			}

			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", model.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			HtmlUtil.writerJson(response, jsonMap);
		
		
	}

	/**
	 * 角色列表转成字符串
	 * 
	 * @param list
	 * @return
	 */
	private String rolesToStr(List<SysRole> list) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			SysRole role = list.get(i);
			str.append(role.getRoleName());
			if ((i + 1) < list.size()) {
				str.append(",");
			}
		}
		return str.toString();
	}

	/**
	 * 添加或修改数据
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@Module(remark=("保存用户"),module=("用户管理"))
	@RequestMapping("/save")
	public void save(HttpServletResponse response,@Validated SysUser bean, BindingResult result)
			throws Exception {
		if(result.hasErrors()){
			sendFailureMessage(response, ResultUtil.getErrorMessage(result));
			return;
		}
		int count = sysUserService.getUserCountByEmail(bean.getEmail());
		if (bean.getId() == null) {
			if (count > 0) {
				sendFailureMessage(response, "用户已存在。");
				return;
			}
			bean.setDeleted(DELETED.NO.key);
			bean.setPwd(MethodUtil.MD5(Constant.SITE_USER_BASEPASSWORD));// 初始化密码
			sysUserService.add(bean);
		} else {
			if (count > 1) {
				sendFailureMessage(response, "用户已存在。");
				return;
			}
			sysUserService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}

	@RequestMapping("/getId")
	public void getId(Integer id, HttpServletResponse response)
			throws Exception {
		Map<String, Object> context = getRootMap();
		SysUser bean = sysUserService.queryById(id);
		if (bean == null) {
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	@Module(remark=("删除用户"),module=("用户管理"))
	@RequestMapping("/delete")
	public void delete(Integer[] id, HttpServletResponse response)
			throws Exception {
		sysUserService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

	/**
	 * 添加或修改数据
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@Module(remark=("修改密码"),module=("用户管理"))
	@RequestMapping("/updatePwd")
	public void updatePwd(Integer id, String oldPwd, String newPwd,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean isAdmin = SessionUtils.isAdmin(request); // 是否超级管理员
			SysUser bean = sysUserService.queryById(id);
			if (bean.getId() == null || DELETED.YES.key == bean.getDeleted()) {
				sendFailureMessage(response, "對不起，用戶不存在。");
				return;
			}
			if (StringUtils.isBlank(newPwd)) {
				sendFailureMessage(response, "密碼是必填。");
				return;
			}
			// 不是超级管理员，匹配旧密码
			if (!isAdmin && !MethodUtil.ecompareMD5(oldPwd, bean.getPwd())) {
				sendFailureMessage(response, "错误的旧密码。");
				return;
			}
			bean.setPwd(MethodUtil.MD5(newPwd));
			sysUserService.updateBySelective(bean);
			sendSuccessMessage(response, "保存成功~");
	}

	/**
	 * 用户授权页面
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userRole")
	public ModelAndView userRole(HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		context.put("roleList", sysRoleService.queryAllList());
		return forword("/sys/sysUserRole", context);
	}

	/**
	 * 用户授权列表
	 * 
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/userList")
	public void userList(SysUserModel model, HttpServletResponse response)
			throws Exception {
		model.setState(STATE.ENABLE.key);
		dataList(model, response);
	}
	
	
	/**
	 * json 列表页面
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryUser")
	public ModelAndView querUser(SysUserModel model,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> context = getRootMap();
		context.put("nameobj", request.getParameter("nameobj"));
		context.put("idobj", request.getParameter("idobj"));
		context.put("callback", request.getParameter("callback"));
		context.put("excludeManager", request.getParameter("excludeManager"));
		return forword("sys/queryUser", context);
	}
	

	/**
	 * 查询用户信息
	 * 
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getUser")
	public void getUser(Integer id, HttpServletResponse response)
			throws Exception {
		Map<String, Object> context = getRootMap();
		SysUser bean = sysUserService.queryById(id);
		if (bean == null) {
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		Integer[] roleIds = null;
		List<SysRoleRel> roles = sysUserService.getUserRole(bean.getId());
		if (roles != null) {
			roleIds = new Integer[roles.size()];
			int i = 0;
			for (SysRoleRel rel : roles) {
				roleIds[i] = rel.getRoleId();
				i++;
			}
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", bean.getId());
		data.put("email", bean.getEmail());
		data.put("roleIds", roleIds);
		context.put(SUCCESS, true);
		context.put("data", data);
		HtmlUtil.writerJson(response, context);
	}

	/**
	 * 添加或修改数据
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@Module(remark=("用户授权"),module=("用户管理"))
	@RequestMapping("/addUserRole")
	public void addUserRole(Integer id, Integer roleIds[],
			HttpServletResponse response) throws Exception {
		sysUserService.addUserRole(id, roleIds);
		sendSuccessMessage(response, "保存成功");
	}
	 
}
