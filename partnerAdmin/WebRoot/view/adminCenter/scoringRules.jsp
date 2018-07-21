<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/headinclude.jsp" %>

<script type="text/javascript" src="${msUrl}/js/adminCenter/scoringRules.js"></script>
<jsp:include page="../common/header.jsp" />
 <body>

	<div>
			
					<jsp:include page="../common/partnerLeft.jsp"/>
				
					<div style="font-size:16px;color:#333;float: left;margin-left: 20px;border-bottom: 1px solid #ccc;margin-bottom: 20px;">推荐获得积分</div>	
						<div style=" width: 70%; float: left;">
		<form action="${msUrl}/admin/saveUploadScore.do" method="post"
			class="form-horizontal form-bordered" id="uploadScoreForm">
			<input id="id" name="id" type="hidden" class="gldint texwd160">
			<input id="dkey" name="dkey" type="hidden" value="推荐获得积分" class="gldint texwd160">
			  <p class="manage_unit clearfix mtb15">
				<label class="control-label" style="margin-left: 20px;margin-bottom: 20px;">推荐获得积分</label>
				 <input id="dvalue" name="dvalue" >分
			  </p>		
		</form>
		
		<button type="button" class="btn btn-default"  onclick="cancel()" style="margin-left: 20px;" >
				取消 
		</button>
			            
		<button type="button" onclick="saveUploadRule()" 
			        class="btn btn-primary" style="margin-left: 20px;">
			   	确认
		</button>
	</div>
</body>