package com.base.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  







import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;  
import org.springframework.web.servlet.ModelAndView;  

import com.base.utils.HtmlUtil;
  
public class BaseExceptionHandler implements HandlerExceptionResolver {  
  
    @Override  
    public ModelAndView resolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) {  
        
    	
    	if(handler==null){
    		return null;
    	}
    	
    	ex.printStackTrace();
    	
    	/**
		 * 错误信息设置
		 */
		String msg = "";
		if (ex instanceof ServiceException) {
			msg = ex.getMessage();
		}else if (ex instanceof NullPointerException) {
			msg = "空指针异常,请您联系管理员";
		}else if (ex instanceof IOException) {
			msg = "文件读写异常,请您联系管理员";
		}else {
			msg = ex.getMessage();
		}
		
		 
		 
    	/**
		 * 这里把出错部分分成两种，一种是ModelAndView类型的报错，是由页面展示
		 * 不是的，都按json处理返回错误信息
		 */
		HandlerMethod hm=(HandlerMethod)handler;
		
		
		
		//如果是使用responseBody注解来返回json，可以用此方法来区分
//		ResponseBody rb= AnnotationUtils.findAnnotation(hm.getMethod(), ResponseBody.class);
//		if(rb!=null){
//			
//		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("msg", msg);
		//如果是ModelAndView类型  ，我们项目是按返回类型区分异步同步
		if("org.springframework.web.servlet.ModelAndView".equals(hm.getMethod().getReturnType().getName())){
			 return new ModelAndView("error",result);  
		}else{//如果是json类型
			HtmlUtil.writerJson(response, result);
			return null;
		}
    	 
    }  
  
}  