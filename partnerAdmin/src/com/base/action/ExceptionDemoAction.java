package com.base.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.model.SiteNewsModel;
@Controller
@RequestMapping("/exceptionDemo")
public class ExceptionDemoAction extends BaseAction{
	
	@RequestMapping("/list")
	public ModelAndView list(HttpServletResponse response){
		Map<String, Object> context = new HashMap<String, Object>();		
		return forword("demo/exceptionDemo", context);
	}
	
	@RequestMapping("/exceptionJson")
	public void exceptionDemos(HttpServletResponse response){
		int i =0;
		int j=1/i; //此方法一定会出现异常
	}
	
	
	@RequestMapping("/exceptionPage")
	public ModelAndView newsmanage(SiteNewsModel model,
			HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		int i=0;
		int j=1/i;
		return forword("随便写，反正也走不到这里", context);
	}
	
}
