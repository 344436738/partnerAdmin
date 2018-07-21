<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/headinclude.jsp" %>

<script type="text/javascript" src="${msUrl}/js/adminCenter/scoreExchange.js"></script>
<jsp:include page="../common/header.jsp" />
<style>
.btn-default{height: 35px;}
</style>
 <body>

	<div>
	
			<jsp:include page="../common/partnerLeft.jsp"/>
				<div style="font-size:16px;color:#333;float: left;margin-left: 20px;border-bottom: 1px solid #ccc;margin-bottom: 20px;">金币兑现申请</div>
			
				
				<div style=" width: 70%; float: left;">
						 <button type="button" onclick="handle()" class="btn btn-primary" style="margin-left: 20px;">
						                         处理
						</button>
						
						     
						<select id="paystatus" name="paystatus" onchange="changestatus() "style="margin-left: 20px;width: 80px;height: 35px;">
							<option value="0" selected>未处理</option>
							<option value="1" >已处理</option>
						</select>
						
						     
						<div class="panel-body">
							<table id="scoreexchangetable" class="table_data"></table>
						</div> 
						
			</div>
	</div>	
</body>