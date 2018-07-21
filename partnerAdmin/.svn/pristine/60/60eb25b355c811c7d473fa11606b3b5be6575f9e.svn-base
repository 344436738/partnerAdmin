
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<%@ include file="head.jsp" %>

<script type="text/javascript" src="${msUrl}/js/stLogin/companyRegister.js"></script>
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
<label >-----------------------注册-------------------------</label>

	<div >
		<form action="${msUrl}/stLogin/companySave.do" method="post"
			class="form-horizontal form-bordered" id="registerForm">
			
			<input id="id" name="id" type="hidden" class="gldint texwd160">`
			
			<label>账户信息：</label>
			<label>用户名</label>
			<input id="nickname" name="nickname" class="form-control" data-rule-required="true"/>			
			<label>密码</label>
			<input id="password" name="password" type="password" class="form-control" data-rule-required="true"/>
			<label>确认密码</label>
			<input type="password" class="form-control" data-rule-required="true" data-rule-equalTo="#password"/>
			
			<label>公司信息：</label>
			<label>公司名称</label>
			<input id="companyname" name="companyname" class="form-control" data-rule-required="true"/>
			<label>公司行业</label>
			<input id="industry" name="industry"  class="form-control" data-rule-required="true"/>
			<label>员工人数</label>
			<input id="insize" name="insize" class="form-control" data-rule-required="true"/>
			
			<label>联络信息：</label>
			<label>联系人</label>
			<input id="linkman" name="linkman" class="form-control" data-rule-required="true"/>
			<label></label>
			<label>联系电话</label>
			<input id="phone" name="phone" class="form-control" data-rule-required="true"/>
			<label>电子邮箱</label>
			<input id="email" name="email" class="form-control" />
			
			<label>公司介绍：</label>
			<label>公司理念</label>
			<input id="intro" name="intro" class="form-control" data-rule-required="true" />
			<label>官方网站地址</label>
			<input id="websiteaddress" name="websiteaddress" />
			
			<div class="form-group">
      			<label class="sr-only" for="charter">上传营业执照</label>
      			<input type="file" id="charter" name="charter"  /> <!-- data-rule-required="true" -->
   			</div>
			
		</form>
	</div>
	
	<div id="toolbar">		
		 <input type="checkbox" id="read" name="read" class="form-control" onchange="open()" 
		 	data-rule-required="true"/> 我已阅读《XXXXXXX》协议并同意
		 
	     <button type="button" class="btn btn-default" data-dismiss="modal" >
			关闭 </button>
	     <button id="register" type="button" onclick="Register()" class="btn btn-primary" >
	                           立即注册</button>
	</div>
	
</div>