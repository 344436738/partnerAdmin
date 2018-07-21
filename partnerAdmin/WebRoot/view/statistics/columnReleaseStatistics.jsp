<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ux/statistics/columnReleaseStatistics.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ichartjs/ichart.1.2.min.js"></script>
<script type="text/javascript">
 //饼图  	 
	$(function(){
		var data2 = [

					<c:forEach items="${rows}" var="column" varStatus="stat">
		     		{name : '${column.columnName}',value : '${column.releaseCount}',color:'#'+('00000'+(Math.random()*0x1000000<<0).toString(16)).slice(-6)}<c:if test="${!stat.last}">,</c:if>
		     		</c:forEach>
		    	];
		
		new iChart.Pie2D({
			render : 'canvasDiv2',
			animation:true,//产生动画效果
			data: data2,
			title : '新闻栏目发布量统计',
			legend : {
				enable : true
			},
			showpercent:true,
			decimalsnum:2,
			width : 800,
			height : 400,
			radius:140
		}).draw();
		});
	</script>

  </head>
  <div class="panel">
<div class="panel-body">
  	<div class="dashed-line"></div>
	<div id='canvasDiv2' align="center" style="margin-top: 20px;margin-bottom: 20px;"></div>
	<table id="table" class="table_data"></table>
</div></div></html>
