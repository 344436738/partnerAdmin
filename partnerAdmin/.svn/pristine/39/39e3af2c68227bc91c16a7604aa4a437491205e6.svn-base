<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${msUrl}/js/bjs/jquery-1.11.1.min.js"></script>
<script src="${msUrl}/js/jquery-form.js"></script>
<script type="text/javascript" src="${msUrl}/js/jquery.formFill.js"></script>
<script src="${msUrl}/js/bootstraptable/layer.js"></script>
<script type="text/javascript" src="${msUrl}/js/laydate/laydate.dev.js"></script>
<script src="${msUrl}/js/bootstraptable/laycode.js"></script>
<script type="text/javascript" src="${msUrl}/js/validate/jquery.validate.js"></script >
<script type="text/javascript" src="${msUrl}/js/validate/valadate-message.js"></script>

	
<script src="${msUrl}/js/bjs/bootstrap.min.js"></script>
<script src="${msUrl}/js/bootstraptable/bootstrap-table.js"></script>
<script src="${msUrl}/js/bootstraptable/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="${msUrl}/js/bootstraptable/bootstrap-table-export.js"></script>
<script type="text/javascript" src="${msUrl}/js/bootstraptable/export-plugin/tableExport.js"></script>
<script type="text/javascript" src="${msUrl}/js/bootstraptable/export-plugin/jquery.base64.js"></script>
<script type="text/javascript">$.base64.utf8encode = true;</script>  
<script type="text/javascript" src="${msUrl}/js/bootstraptable/export-plugin/html2canvas.js"></script>
	
	
<script type="text/javascript" src="${msUrl}/js/partnerCenter/scoreQuery.js"></script>
<link rel="stylesheet" type="text/css" href="${msUrl}/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${msUrl}/css/bootstraptable/layer.css">
<link rel="stylesheet" type="text/css" href="${msUrl}/css/bootstraptable/laycode.css">
<link rel="stylesheet" href="${msUrl}/css/bootstraptable/bootstrap-table.css">
<link rel="stylesheet" type="text/css" href="${msUrl}/css/valid.css">



您当前的积分为：${WebUser.score }分(其中${FrozenScore}为冻结积分)  可兑换金额≈￥${WebUser.score/ExchangeRule }

<!-- 表格展示 -->         
<div class="panel-body">
	<table id="table" class="table_data"></table>
</div> 


<form action="${msUrl}/partnerCenter/saveScoreExchange.do" method="post"
	class="form-horizontal form-bordered" id="editForm">
	<p class="manage_unit clearfix mtb15">
		    <label class="control-label">兑换积分:</label>
		    <input id="exchangescore" name="exchangescore" type="text"
			   data-rule-required="true"  onblur="setInputValue('getmoney',this.value/${ExchangeRule})" class="form-control">
	    </p>
																								
		<p class="manage_unit clearfix mtb15">
			<label class="control-label">折合金额:</label>
			<input id="getmoney" name="rmb" type="text"
				data-rule-required="true" class="form-control" readonly="readonly" >
		</p>
							
		<p class="manage_unit clearfix mtb15">
			<label class="control-label">银行卡号:</label>
			<input id="cardnum" name="cardno" type="text"
				data-rule-required="true" class="form-control" value="${WebUser.cardno }" readonly="readonly">
		</p>
		
		<!-- 隐藏的 需要添加的字段 -->
		<input id="userid" name="userid" type="hidden"
				data-rule-required="true" class="form-control" value="${WebUser.id }">	
		<input id="banktype" name="banktype" type="hidden"
				data-rule-required="true" class="form-control" value="${WebUser.banktype }">	
		<input id="recipient" name="recipient" type="hidden"
				data-rule-required="true" class="form-control" value="${WebUser.recipient }">
		<input id="currentrule" name="currentrule" type="hidden"
				data-rule-required="true" class="form-control" value="${ExchangeRule }">		
</form>

     <button type="button" onclick="save()" class="btn btn-primary">
                          申请兑换
     </button>
