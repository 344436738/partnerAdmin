
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<%@ include file="head.jsp" %>

<script type="text/javascript">
	$(document).ready(function() {
			//初始化校验
			$.validator.setDefaults( {
				ignore : []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
			$("#editForm").validate();				
		});
</script>
<script type="text/javascript" src="${msUrl}/js/stLogin/personalRegister.js"></script>


<!-- form表单 ，用于录入信息存入数据库-->
<div>
<label >-----------------------注册-------------------------</label>

	
		<form action="${msUrl}/stLogin/personalSave.do" method="post"
			class="form-horizontal form-bordered" id="registerForm">
			
<!-- 	      		<label for="nickname" class="col-sm-2 control-label">用户名</label> -->
<!-- 	         		<input type="text" class="form-control" id="nickname" name="nickname"  -->
<!-- 	            		data-rule-required="true" /> -->
	   		
	      		<label for="phone" class="col-sm-2 control-label">手机号</label>  		
	         		<input type="text" class="form-control" id="phone" name="phone"
	            		data-rule-required="true" />
	      
	      		<label for="password" class="col-sm-2 control-label">密码</label>
	         		<input type="password" class="form-control" id="password" name="password" 
	            		data-rule-required="true" />
	      
	      		<label for="confirmpassword" class="col-sm-2 control-label">重复密码</label>
	         		<input type="password" class="form-control" 
	            		placeholder="确认密码" data-rule-required="true" data-rule-equalTo="#password"/>
	      
<!-- 	      		<label for="referee" class="col-sm-2 control-label">推荐人</label> -->
<!-- 	         		<input type="text" class="form-control" id="referee" name="referee"/> -->
	      
		</form>
	
	 <div class="modal-footer">
<!-- 		 <input type="checkbox" id="read"  class="form-control"  -->
<!-- 		 	data-rule-required="true"/>我已阅读《XXXXXXX》协议并同意 -->
	     <button type="button" class="btn btn-default" data-dismiss="modal" >
			关闭  </button>
	     <button type="button" onclick="Register()" class="btn btn-primary">
	                           立即注册</button>
	</div>
	
</div>