package com.base.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.base.action.BaseAction;
import com.base.annotation.Auth;
import com.base.bean.SysUser;
import com.base.utils.HtmlUtil;
import com.base.utils.SessionUtils;

/**
 * 权限拦截器
 * 
 * @author
 * 
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	private final static Logger log = Logger.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod method = (HandlerMethod) handler;
	Auth auth = method.getMethod().getAnnotation(Auth.class);
		// 验证登陆超时问题 auth = null，默认验证
		if (auth == null || auth.verifyLogin()) {// 如果auth为空或者方法需要验证
			String baseUri = request.getContextPath();
			String path = request.getServletPath();
			SysUser user = SessionUtils.getUser(request);
//			System.out.println("AuthInterceptor-->user:" + user);
			if (user == null) {
				if (path.endsWith(".shtml")) {
					//iframe或其他页面跳转方式
//					response.setStatus(response.SC_GATEWAY_TIMEOUT);
//					response.sendRedirect(baseUri + "/login.shtml");
					
					//jquery Load的加载方式
					
					StringBuffer html=new StringBuffer();
					html.append("<script language=\"javascript\">")
					.append("var goto=\""+baseUri+"/login.shtml\";")
					.append("if(window !=top){top.location.href=goto;}else{")
					.append("window.location.href=goto;}")
					.append("</script>");
					HtmlUtil.writerHtml(response, html.toString());

					return false;
				} else {
					response.setStatus(response.SC_GATEWAY_TIMEOUT);
					Map<String, Object> result = new HashMap<String, Object>();
					result.put(BaseAction.SUCCESS, false);
					result.put(BaseAction.LOGOUT_FLAG, true);// 登录标记 true 退出
					result.put(BaseAction.MSG, "登录超时.");
					HtmlUtil.writerJson(response, result);
					return false;
				}
			}
		}
		// 验证URL权限
		if (auth == null || auth.verifyURL()) {
			// 判断是否超级管理员
			if (!SessionUtils.isAdmin(request)) {
				String menuUrl = StringUtils.remove(request.getRequestURI(),
						request.getContextPath());
				;
				if (!SessionUtils.isAccessUrl(request, StringUtils
						.trim(menuUrl))) {
					// 日志记录
					String userMail = SessionUtils.getUser(request).getEmail();
					String msg = "URL权限验证不通过:[url=" + menuUrl + "][email ="
							+ userMail + "]";
					log.error(msg);

					response.setStatus(response.SC_FORBIDDEN);
					Map<String, Object> result = new HashMap<String, Object>();
					result.put(BaseAction.SUCCESS, false);
					result.put(BaseAction.MSG, "没有权限访问,请联系管理员.");
					HtmlUtil.writerJson(response, result);
					return false;
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
}
