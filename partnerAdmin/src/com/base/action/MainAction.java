package com.base.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.annotation.Auth;
import com.base.annotation.Module;
import com.base.bean.SysMenu;
import com.base.bean.SysMenuBtn;
import com.base.bean.SysUser;
import com.base.bean.TreeNode;
import com.base.bean.BaseBean.DELETED;
import com.base.bean.BaseBean.STATE;
import com.base.model.SiteMainModel;
import com.base.service.SysMenuBtnService;
import com.base.service.SysMenuService;
import com.base.service.SysUserService;
import com.base.utils.DateUtil;
import com.base.utils.HtmlUtil;
import com.base.utils.MethodUtil;
import com.base.utils.SessionUtils;
import com.base.utils.TreeUtil;
import com.base.utils.URLUtils;
import com.base.utils.Constant.SuperAdmin;
import com.weixin.service.WeixinAccountService;

@Controller
public class MainAction extends BaseAction {
	private final static Logger log = Logger.getLogger(MainAction.class);

	@Autowired(required = false)
	private SysMenuService<SysMenu> sysMenuService;
	
	@Autowired(required = false)
	private SysUserService<SysUser> sysUserService;
	
	@Autowired(required = false)
	private SysMenuBtnService<SysMenuBtn> sysMenuBtnService;
	
	@Autowired(required = false)
	private WeixinAccountService weiXinAccountService;
	
	/**
	 * 访问登录页面
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = getRootMap();
		return forword("login", context);
	}

	/**
	 * 登录
	 * 
	 * @param email
	 *            邮箱登录账号
	 * @param pwd
	 *            密码
	 * @param verifyCode
	 *            验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Module(remark=("用户登录"))
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/toLogin")
	public void toLogin(String email, String pwd, String verifyCode,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String vcode = SessionUtils.getValidateCode(request);
		SessionUtils.removeValidateCode(request);// 清除验证码，确保验证码只能用一次
		if (StringUtils.isBlank(verifyCode)) {
			sendFailureMessage(response, "验证码不能为空.");
			return;
		}
//		 判断验证码是否正确
		if (!verifyCode.toLowerCase().equals(vcode)) {
			sendFailureMessage(response, "验证码输入错误.");
			return;
		}
		if (StringUtils.isBlank(email)) {
			sendFailureMessage(response, "账号不能为空.");
			return;
		}
		if (StringUtils.isBlank(pwd)) {
			sendFailureMessage(response, "密码不能为空.");
			return;
		}
		String msg = "用户登录日志:";
		SysUser user = sysUserService.queryLogin(email, MethodUtil.MD5(pwd));
		if (user == null) {
			// 记录错误登录日志
			log.debug(msg + "[" + email + "]" + "账号或者密码输入错误.");
			sendFailureMessage(response, "账号或者密码输入错误.");
			return;
		}
		if (STATE.DISABLE.key == user.getState()) {
			sendFailureMessage(response, "账号已被禁用.");
			return;
		}
		// 登录次数加1 修改登录时间
		int loginCount = 0;
		if (user.getLoginCount() != null) {
			loginCount = user.getLoginCount();
		}
		user.setLoginCount(loginCount + 1);
		user.setLoginTime(DateUtil.getDateByString(""));
		sysUserService.updateBySelective(user);
		// 设置User到Session
		SessionUtils.setUser(request, user);
		// 记录成功登录日志
		log.debug(msg + "[" + email + "]" + "登录成功");
		sendSuccessMessage(response, "登录成功.");
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SessionUtils.removeUser(request);
		response.sendRedirect(URLUtils.get("msUrl") + "/login.shtml");
	}

	/**
	 * 获取Action下的按钮
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyURL = false)
	@RequestMapping("/getActionBtn")
	public void getActionBtn(String url, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> actionTypes = new ArrayList<String>();
		// 判断是否超级管理员
		if (SessionUtils.isAdmin(request)) {
			result.put("allType", true);
		} else {
//			String menuUrl = URLUtils.getReqUri(url);// 旧版用iframe时的写法
			String menuUrl = url;//新的写法，直接传过来即可
			
			menuUrl = StringUtils.remove(menuUrl, request.getContextPath());
			// 获取权限按钮
			actionTypes = SessionUtils.getMemuBtnListVal(request, StringUtils
					.trim(menuUrl));
			result.put("allType", false);
			result.put("types", actionTypes);
		}
		result.put(SUCCESS, true);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 修改密码
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyURL = false)
	@RequestMapping("/modifyPwd")
	public void modifyPwd(String oldPwd, String newPwd,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user = SessionUtils.getUser(request);
		if (user == null) {
			sendFailureMessage(response, "对不起,登录超时.");
			return;
		}
		SysUser bean = sysUserService.queryById(user.getId());
		if (bean.getId() == null || DELETED.YES.key == bean.getDeleted()) {
			sendFailureMessage(response, "对不起,用户不存在.");
			return;
		}
		if (StringUtils.isBlank(newPwd)) {
			sendFailureMessage(response, "密码不能为空.");
			return;
		}
		// 不是超级管理员，匹配旧密码
		if (!MethodUtil.ecompareMD5(oldPwd, bean.getPwd())) {
			sendFailureMessage(response, "旧密码输入不匹配.");
			return;
		}
		bean.setPwd(MethodUtil.MD5(newPwd));
		sysUserService.update(bean);
		sendSuccessMessage(response, "修改成功.");
	}

	/**
	 * ilook 首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyURL = false)
	@RequestMapping("/main")
	public ModelAndView main(SiteMainModel model, HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		SysUser user = SessionUtils.getUser(request);
		List<SysMenu> rootMenus = null;
		List<SysMenu> childMenus = null;
		List<SysMenuBtn> childBtns = null;
		// 超级管理员
		/**********************微信获取account信息*************************/
		weiXinAccountService.setSessionAccount(user.getId(), request);
		
		/********************以上是微信部分代码(若项目不用可以直接删除或者注释)*****************************/
		if (user != null && SuperAdmin.YES.key == user.getSuperAdmin()) {
			rootMenus = sysMenuService.getRootMenu(null);// 查询所有根节点
			childMenus = sysMenuService.getChildMenu();// 查询所有子节点
			
		} else {
			rootMenus = sysMenuService.getRootMenuByUser(user.getId());// 根节点
			childMenus = sysMenuService.getChildMenuByUser(user.getId());// 子节点
			childBtns = sysMenuBtnService.getMenuBtnByUser(user.getId());// 按钮操作
			
			buildData(childMenus, childBtns, request); // 构建必要的数据
		}
		
		context.put("user", user);
		context.put("menuList", treeMenu(rootMenus, childMenus));
		return forword("main/main", context);
	}

	/**
	 * 构建树形数据
	 * 
	 * @return
	 */
	private List<TreeNode> treeMenu(List<SysMenu> rootMenus,
			List<SysMenu> childMenus) {
		TreeUtil util = new TreeUtil(rootMenus, childMenus);
		return util.getTreeNode();
	}

	/**
	 * 构建树形数据
	 * 
	 * @return
	 */
	private void buildData(List<SysMenu> childMenus,
			List<SysMenuBtn> childBtns, HttpServletRequest request) {
		// 能够访问的url列表
		List<String> accessUrls = new ArrayList<String>();
		// 菜单对应的按钮
		Map<String, List> menuBtnMap = new HashMap<String, List>();
		for (SysMenu menu : childMenus) {
			// 判断URL是否为空
			if (StringUtils.isNotBlank(menu.getUrl())) {
				List<String> btnTypes = new ArrayList<String>();
				for (SysMenuBtn btn : childBtns) {
					if (menu.getId().equals(btn.getMenuid())) {
						btnTypes.add(btn.getBtnType());
						URLUtils.getBtnAccessUrls(menu.getUrl(), btn
								.getActionUrls(), accessUrls);
					}
				}
				menuBtnMap.put(menu.getUrl(), btnTypes);
				URLUtils.getBtnAccessUrls(menu.getUrl(), menu.getActions(),
						accessUrls);
				accessUrls.add(menu.getUrl());
			}
		}
		SessionUtils.setAccessUrl(request, accessUrls);// 设置可访问的URL
		SessionUtils.setMemuBtnMap(request, menuBtnMap); // 设置可用的按钮
	}
}