package com.base.utils;

import java.util.List;
import java.util.ResourceBundle;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ResultUtil {

	public static String getErrorMessage(BindingResult result) {
		StringBuffer errMsg=new StringBuffer();
		List<FieldError> errors=result.getFieldErrors();
		for(int i=0;i<errors.size();i++){
			errMsg.append(errors.get(i).getDefaultMessage()+"<br />");
		}
		return errMsg.toString();
	}
	

}