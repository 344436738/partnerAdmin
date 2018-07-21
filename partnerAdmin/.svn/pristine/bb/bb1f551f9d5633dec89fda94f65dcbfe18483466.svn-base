<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/headinclude.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminCenter/resumeManage.js"></script>
<jsp:include page="../common/header.jsp" />
<style>
.btn-default{height: 35px;}
</style>
<body>

	
	
			<jsp:include page="../common/partnerLeft.jsp"/>
				<div style="font-size:16px;color:#333;float: left;margin-left: 20px;border-bottom: 1px solid #ccc;margin-bottom: 20px;">简历管理</div>
		
				
				<div style=" width: 70%; float: left;">
					 <button type="button" onclick="checkpass()" class="btn btn-primary" style="margin-left: 20px;">
						                         审核通过
						</button>
						 <button type="button" onclick="checknotpass()" class="btn btn-primary" style="margin-left: 20px;">
						                         审核不通过
						</button>
						     
						<select id="status" name="status" onchange="changestatus()" style="margin-left: 20px;width: 100px;height: 35px;">
							<option value="0" selected>全部</option>
							<option value="1" >审核通过</option>
							<option value="2" >审核未通过</option>
							<option value="3" >未审核简历</option>
						</select>
						
						  <input id="ssid" name="ssid" type="hidden" class="gldint texwd160" value=${ssid} >
						<div class="panel-body">
							<table id="resumetable" class="table_data"></table>
						</div>
			
					
			
				</div>
		
		
</body>

