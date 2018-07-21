<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/headinclude.jsp" %>

<script type="text/javascript" src="${msUrl}/js/partnerCenter/evaluate.js"></script>



<div id="evaluateDiv">
<form action="${msUrl}/partnerCenter/saveEvaluate.do" method="post"
	class="form-horizontal form-bordered" id="evaluateForm">
	<input id="oppid" name="oppid" type="hidden" value="${Bean.oppid }" class="gldint texwd160">
	<input id="compid" name="userid" type="hidden" value="${Bean.userid }" class="gldint texwd160">
	  <p class="manage_unit clearfix mtb15">
		<label class="control-label">确认速度:</label>
		 <input id="confirmspeed" name="confirmspeed" type="number" class="rating" min=0 max=5 step=1 data-size="xs">
	  </p>																		
	  <p class="manage_unit clearfix mtb15">
		<label class="control-label">付款金额:</label>
		<input id="paynum" name="paynum" type="number" class="rating" min=0 max=5 step=1 data-size="xs">
	  </p>
				
	  <p class="manage_unit clearfix mtb15">
		<label class="control-label">合作态度:</label>
		<input id="coopattitude" name="coopattitude" type="number" class="rating" min=0 max=5 step=1 data-size="xs">
	  </p>
	  
	  <p class="manage_unit clearfix mtb15">
		<label class="control-label">评价内容:</label>
		<input id="evaluatecontent" name="evaluatecontent" type="text" />
	  </p>
							
</form>
	<button type="button" class="btn btn-default" 
	       data-dismiss="modal" onclick="cancel()" >
		取消 </button>
	            
	<button type="button" onclick="saveevaluate()" 
	        class="btn btn-primary">
	   	确认</button>
</div>	   	

<div id="successMsg" >评价成功！<br/>
 页面将在<span id="num" ></span>秒后跳转...<br/></div>
