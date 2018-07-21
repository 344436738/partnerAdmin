<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/headinclude.jsp" %>

<script type="text/javascript" src="${msUrl}/js/adminCenter/personUserManage.js"></script>
<jsp:include page="../common/header.jsp" />
<style>
.btn-default{height: 35px;}
</style>
 <body>

	<div>
	
						<jsp:include page="../common/partnerLeft.jsp"/>
						<div style="font-size:16px;color:#333;float: left;margin-left: 20px;border-bottom: 1px solid #ccc;margin-bottom: 20px;">个人用户管理</div>
							
					<div style=" width: 70%; float: left;">
			 
			
							 <input id="type" name="type" type="hidden" value="1">
							  <p class="manage_unit clearfix mtb15" style="margin-left: 20px;float:left;">
								<label class="control-label">姓名：</label>
								 <input id="username" name="username" >
							  </p>
							  
							   <p class="manage_unit clearfix mtb15" style="margin-left: 20px;float:left;">
								<label class="control-label">手机号：</label>
								 <input id="phone" name="phone" >
							  </p>
							  
							  		
							 <button type="button" onclick="userSearch()" class="btn btn-primary" style="margin: 20px;clear:both;float:left;">
							                       查询
							</button>   <br/>
							
							<button type="button" onclick="enableOrDisable()" class="btn btn-primary" style="margin: 20px;float:left;">
							                       启用/禁用
							</button>
							
							
							     
					<div class="panel-body">
						<table id="personUserTable" class="table_data"></table>
					</div> 
		</div>
	</div>	
</body>