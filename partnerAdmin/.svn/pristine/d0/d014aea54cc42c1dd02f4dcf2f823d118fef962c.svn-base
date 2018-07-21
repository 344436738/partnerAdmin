package com.base.action;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.bean.SysMenu;
import com.base.bean.SysMenuBtn;
import com.base.bean.TreeNode;
import com.base.bean.BaseBean.DELETED;
import com.base.model.SysMenuModel;
import com.base.service.SysMenuBtnService;
import com.base.service.SysMenuService;
import com.base.utils.HtmlUtil;
import com.base.utils.SessionUtils;
import com.base.utils.TreeUtil;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.codehaus.xfire.client.Client;

@Controller
@RequestMapping("/sysClass")
public class SysClassAction extends BaseAction {

	private final static Logger log = Logger.getLogger(SysClassAction.class);

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SysMenuService<SysMenu> sysMenuService;

	@Autowired
	private SysMenuBtnService<SysMenuBtn> sysMenuBtnService;

	/**
	 * 打开Connect首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 */

	@RequestMapping("/class")
	public ModelAndView menu(SysMenuModel model, HttpServletRequest request)
			throws Exception {
		System.out.println("@@@@" + SessionUtils.getUser(request).getEmail());

		Map<String, Object> context = getRootMap();
		return forword("sys/sysMenu", context);
	}

}
