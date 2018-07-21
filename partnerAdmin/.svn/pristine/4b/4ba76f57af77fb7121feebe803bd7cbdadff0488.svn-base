<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ux/sys/sysLog.js"></script>

<script type="text/javascript">
	laydate({
	    elem: '#maxOperationTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	    event: 'focus', //响应事件。如果没有传入event，则按照默认的click
	});
	laydate({
	    elem: '#minOperationTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	    event: 'focus' ,//响应事件。如果没有传入event，则按照默认的click
	});
</script>
  <div class="panel">
<div class="panel-heading">
<!--查询条件  -->
<form action="" class="list_con_form clearfix" id="searchForm">
	<span class="manage_unit clearfix"> <span class="manage_span_name">模块：</span> <input
			type="text" name="moduleName" id="moduleName"  class="manage_input wd160" />
	</span>
	<span class="manage_unit clearfix"> <span class="manage_span_name">操作：</span> <input
			type="text" name="operation" id="operation" class="manage_input wd160" />
	</span>
	<span class="manage_unit clearfix"> <span class="manage_span_name">时间：</span> 
		<input type="text" name="minOperationTime" id="minOperationTime" readonly="readonly" class="manage_input wd160 Wdate laydate-icon" />到
	</span>
	<span class="manage_unit clearfix"> 
		<input type="text" name="maxOperationTime" id="maxOperationTime" readonly="readonly" class="manage_input wd160 Wdate laydate-icon" />
	</span>

	<span class="manage_unit clearfix"> <input type="button" value="查询" onclick="search()" class="h32 inline_blo flole prl20 orage_bg"> </span>
</form>
</div>

<div class="dashed-line"></div>
<div class="panel-body">
<table id="table" class="table_data"></table>
</div></div>


