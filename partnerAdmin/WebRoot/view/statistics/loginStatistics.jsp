<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ux/statistics/loginStatistics.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ichartjs/ichart.1.2.min.js"></script>

<script type="text/javascript">

	//折线图
	$(function(){
		var data =[
		        	{
		        		name : '北京',
		        		value:[
				        		<c:forEach items="${rows}" var="login" varStatus="stat">${login.loginCount}<c:if test="${!stat.last}">,</c:if></c:forEach>
		        			   ],
		        		color:'#1f7e92',
		        		line_width:2
		        	}
		       	 ];  
		var chart = new iChart.Area2D({
				render : 'canvasDiv',
				animation:true,//产生动画效果
				data: data,
				title : '员工日常登录情况统计',
				width : 800,
				height : 400,
				coordinate:{height:'93%',background_color:'#edf8fa'},
				sub_option:{
					hollow_inside:false,//设置一个点的亮色在外环的效果
					point_size:15//设置数据点的大小
				},
				labels:[
							<c:forEach items="${rows}" var="login" varStatus="stat">"${login.loginTime}~${login.loginTime+1}时"<c:if test="${!stat.last}">,</c:if></c:forEach>
						]
				});		
			chart.draw();
	});

</script>

<script type="text/javascript">
	laydate({
	    elem: '#minOperationTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	    
	});
	
	laydate({
	    elem: '#maxOperationTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	     
	});
</script>
  <div class="panel">
<div class="panel-heading">
<!--查询条件  -->
<form action="statistics/loginList.shtml" class="list_con_form clearfix" id="searchForm">
	<span class="manage_unit clearfix"> <span class="manage_span_name">时间：</span> 
		<input type="text" name="minOperationTime" id="minOperationTime" readonly="readonly" class="manage_input wd160 Wdate laydate-icon" />-
	</span>
	<span class="manage_unit clearfix"> 
		<input type="text" name="maxOperationTime" id="maxOperationTime" readonly="readonly" class="manage_input wd160 Wdate laydate-icon" />
	</span>
	<span class="manage_unit clearfix"> <input type="button" value="查询" onclick="search()" class="h32 inline_blo flole prl20 orage_bg"> </span>
	<span class="manage_unit clearfix"> <input type="button" value="前一天" onclick="searchYesterday()" class="h32 inline_blo flole prl20 orage_bg"> </span>
	<span class="manage_unit clearfix"> <input type="button" value="前一周" onclick="searchLastWeek()" class="h32 inline_blo flole prl20 orage_bg"> </span>
</form>

</div>
<div class="panel-body">
<div class="dashed-line"></div>
<div id='canvasDiv' align="center" style="margin-top: 20px;margin-bottom: 20px;"></div>
<table id="table" class="table_data"></table>
</div></div>