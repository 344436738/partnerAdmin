<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/headinclude.jsp" %>

<script type="text/javascript" src="${msUrl}/js/adminCenter/zhoutongji.js"></script>
<jsp:include page="../common/header.jsp" />
<style>
.btn-default{height: 35px;}
</style>
 <body>

	<div>
	
						<jsp:include page="../common/partnerLeft.jsp"/>
						<div style="font-size:16px;color:#333;float: left;margin-left: 20px;border-bottom: 1px solid #ccc;margin-bottom: 20px;">简历上传周统计</div>
							
					<div style=" width: 70%; float: left;">
			 
			
							   <p class="manage_unit clearfix mtb15" style="margin-left:20px;float:left;">
								<label class="control-label">时间：</label>
								 <input id="start" name="start" type="date" >至
								  <input id="end" name="end"  type="date">
							  </p>
							  
							  		
							 <button type="button" onclick="userSearch()" class="btn btn-primary" style="margin: 4px 20px 20px 20px;float:left;">
							                       查询
							</button>   <br/>
							
							
							
							
							     
							<div class="panel-body">
								<table id="personUserTable" class="table_data"></table>
							</div> 
				</div>
		</div>	
</body>