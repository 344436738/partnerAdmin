
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<%@ include file="head.jsp" %>
<script type="text/javascript" src="${msUrl}/js/stLogin/login.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
			//初始化校验
			$.validator.setDefaults( {
				ignore : []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
			$("#editForm").validate();				
		});
</script>

<!-- form表单 ，用于录入信息存入数据库-->
<div>
<label >-----------------------用户登录-------------------------</label>

	<div id="basic">
		<form action="${msUrl}/stLogin/loginDo.do" method="post"
			class="form-horizontal form-bordered" id="loginForm">
			<label>用户名</label>		
			<input id="nickname" class="form-control" data-rule-required="true"/>

			<label>密码</label>
			<input id="password" type="password" class="form-control" data-rule-required="true"/>
			
		</form>
		
	</div>

	<div>
		 <button type="button" onclick="Login()" class="btn btn-primary">
	                          登陆
	     </button>
	     
	     <button type="button" onclick="Forget()" class="btn btn-primary">
	                           忘记密码
	     </button>
	     
	     <button type="button" onclick="Register()" class="btn btn-primary">
	                           立即注册
	     </button>
	</div>
	
	
	<div id="forget">
	
		<form action="${msUrl}/stLogin/forget.do" method="post"
			class="form-horizontal form-bordered" id="forgetForm">
			<label>用户名</label>		
			<input id="nickname" class="form-control" data-rule-required="true"/>

			<label>手机号/邮箱</label>
			<input id="phone" class="form-control" data-rule-required="true"/>
			
		</form>
		
	</div>
	<div>
		<button type="button" onclick="Confirm()" class="btn btn-primary">
	                          确认
	     </button>
	    
	</div>
</div>