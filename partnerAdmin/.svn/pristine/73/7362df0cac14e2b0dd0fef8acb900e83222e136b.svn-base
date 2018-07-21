package com.base.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.base.annotation.Auth;
import com.base.bean.Demo;
import com.base.service.DemoService;

@Controller
public class DemoAction extends BaseAction {
	@Autowired(required = false)
	private DemoService<Demo> demoService;

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/addDemoUser")
	public ModelAndView addUser(Demo user) throws Exception {
		// String username = request.getParameter("un");
		// String password = request.getParameter("pd");
		demoService.add(user);
		Map<String, Object> context = getRootMap();
		context.put(MSG, "增加成功");
		return forword("demo", context);
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/deleteDemoUserById")
	public ModelAndView deleteUserById(Integer id) throws Exception {
		demoService.deleteByUserId(new Integer(id));
		Map<String, Object> context = getRootMap();
		context.put(MSG, "删除成功");
		return forword("demo", context);
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/updateDemoUserById")
	public ModelAndView modifyUserById(Demo user) throws Exception {
		demoService.update(user);
		Map<String, Object> context = getRootMap();
		context.put(MSG, "修改成功");
		return forword("demo", context);
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/queryDemoUserById")
	public String queryDemoUserById(@RequestParam("id") Integer iid)
			throws Exception {
		System.out.println(demoService.queryById(iid));
		return "redirect:http://www.baidu.com";
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/testException")
	public void testException(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int i = 0;
		int j = 3 / i;
		sendFailureMessage(response, "验证码输入错误.");
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/demoAjax")
	@ResponseBody
	public Map demoAjax(String name) {

		if (StringUtils.isEmpty(name))
			throw new RuntimeException();

		return Collections.singletonMap("name", name);

	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/demoPage")
	public ModelAndView demoPage(String name) {

		if (StringUtils.isEmpty(name))
			throw new RuntimeException();

		ModelAndView mav = new ModelAndView();

		mav.setViewName("demo");

		return mav;

	}

}
