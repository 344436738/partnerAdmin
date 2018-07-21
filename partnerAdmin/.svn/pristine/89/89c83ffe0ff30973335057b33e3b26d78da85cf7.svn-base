<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/headinclude.jsp" %>

<script type="text/javascript" src="${msUrl}/js/partnerCenter/incomeCountDetail.js"></script>

<p>
  <select  id="resumestatus" onchange="searchResume()">
    <option value="">所有简历</option>
    <option value ="0">正在审核中</option>
    <option value ="1">审核通过</option>
	<option value="2">审核不通过</option>
  </select>
</p>

<!-- 简历表格展示 -->         
<div class="panel-body">
	<table id="resumetable" class="table_data"></table>
</div>

<p>
  <select  id="oppstatus" onchange="searchOpp()">
	<option value ="">所有推荐商机</option>
	<option value ="0">正在确认中</option>
	<option value ="1">审核不通过</option>
	<option value="2">审核通过未支付</option>
	<option value="3">审核通过已支付</option>
  </select>
</p>
<!-- 商机表格展示 -->         
<div class="panel-body">
	<table id="opptable" class="table_data"></table>
</div> 


<!-- 摸态框，弹出评价窗口 -->
<div class="modal fade" id="evaluteModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
           	评价
            </h4>
         </div>
         <div class="modal-body">
			<form class="form-horizontal form-bordered" id="myevaluate">
				  <p class="manage_unit clearfix mtb15">
					<label class="control-label">确认速度:</label>
					
					<input id="confirmspeed" name="confirmspeed" type="text" data-size="xs" readonly>
				  </p>
										
																
				  <p class="manage_unit clearfix mtb15">
					<label class="control-label">付款金额:</label>
					<input id="paynum" name="paynum" type="text"  data-size="xs" readonly>
				  </p>
							
				  <p class="manage_unit clearfix mtb15">
					<label class="control-label">合作态度:</label>
					<input id="coopattitude" name="coopattitude" type="text" data-size="xs" readonly>
				  </p>
				  
				  <p class="manage_unit clearfix mtb15">
					<label class="control-label">评价内容:</label>
					<input id="evaluatecontent" name="evaluatecontent" type="text" readonly/>
				  </p>
										
			</form>
		</div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" 
              data-dismiss="modal" >关闭
            </button>
         </div>
      </div>
     </div><!-- /.modal -->
</div>


<form action="${msUrl}/partnerCenter/evaluate.shtml" method="post"
			class="form-horizontal form-bordered" id="evaluateForm">
	<input id="oppid" name="oppid" type="hidden" class="gldint texwd160">
	<input id="compid" name="userid" type="hidden" class="gldint texwd160">			
</form>