<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/headinclude.jsp" %>
	
<script type="text/javascript" src="${msUrl}/js/partnerCenter/personalInformation.js"></script>

<div class="panel">
	<form action="${msUrl}/partnerCenter/personalInformationSave.do" method="post" enctype="multipart/form-data"
				class="list_con_form clearfix" id="editForm">
		
		<input id="id" name="id" type="hidden" value="${data.id}"/>
		<label>昵称</label>
		<input id="nickname" name="nickname" value="${data.nickname}"/>
		<label>手机号</label>
		<input id="phone" name="phone" value="${data.phone}"
			data-rule-required="true"/>
		<label>邮箱</label>
		<input id="email" name="email" value="${data.email}"/>
		<label>所在单位名称</label>
		<input id="companyname" name="companyname" value="${data.companyname}"/>
		<label>QQ号</label>
		<input id="qq" name="qq" value="${data.qq}"/>
		<label>微信号</label>
		<input id="wechat" name="wechat" value="${data.wechat}"/>
		<label>收款人姓名</label>
		<input id="recipient" name="recipient" value="${data.recipient}" data-rule-required="true" />
		<label>银行类型</label>
		<input id="banktype" name="banktype" value="${data.banktype}" data-rule-required="true" /> 
		<label>银行卡号</label>
		<input id="cradno" name="cardno" value="${data.cardno}" data-rule-required="true" />
<!-- 		<label>上传图片</label> -->
<!-- 		<input id="logo" name="logo" type="file" /> -->
		
	</form>

	
	<button type="button" onclick="Update()" class="btn btn-primary">
           	信息更新</button>
	<button type="button" onclick="Open()" class="btn btn-primary">
			修改密码</button>
			
	<form action="${msUrl}/partnerCenter/passwordUpdate.do" method="post"
				class="list_con_form clearfix" id="updateForm">
				
			<input id="id" name="id" type="hidden" value="${data.id}" />
			<label>原始密码</label>
			<input id="password" name="password" type="password" data-rule-required="true" />
			<label>新密码 </label>
			<input id="newpassword" name="newpassword" type="password" data-rule-required="true" />
			<label>再次输入新密码</label>
			<input type="password" data-rule-equalTo="#newpassword" />
	</form>
	<button type="button" onclick="UpdatePassword()" class="btn btn-primary">
			修改密码</button>		
</div>

