<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/ichartjs/ichart.1.2.min.js"></script>
  	<script type="text/javascript">
	    var data1 = [
	        		{name : 'H',value : 7,color:'#a5c2d5'},
	        	   	{name : 'E',value : 5,color:'#cbab4f'},
	        	   	{name : 'L',value : 12,color:'#76a871'},
	        	   	{name : 'L',value : 12,color:'#76a871'},
	        	   	{name : 'O',value : 15,color:'#a56f8f'},
	        	   	{name : 'W',value : 13,color:'#c12c44'},
	        	   	{name : 'O',value : 15,color:'#a56f8f'},
	        	   	{name : 'R',value : 18,color:'#9f7961'},
	        	   	{name : 'L',value : 12,color:'#76a871'},
	        	   	{name : 'D',value : 4,color:'#6f83a5'}
	        	 ];
	        	 $(function(){	
	        		var chart1 = new iChart.Column2D({
	            		animation:true,//产生动画效果
	        			render : 'canvasDiv1',//渲染的Dom目标,canvasDiv为Dom的ID
	        			data: data1,//绑定数据
	        			title : 'Hello World\'s Height In Alphabet',//设置标题
	        			width : 450,//设置宽度，默认单位为px
	        			height : 220,//设置高度，默认单位为px
	        			shadow:true,//激活阴影
	        			shadow_color:'#c7c7c7',//设置阴影颜色
	        			coordinate:{//配置自定义坐标轴
	        				scale:[{//配置自定义值轴
	        					 position:'left',//配置左值轴	
	        					 start_scale:0,//设置开始刻度为0
	        					 end_scale:26,//设置结束刻度为26
	        					 scale_space:2,//设置刻度间距
	        					 listeners:{//配置事件
	        						parseText:function(t,x,y){//设置解析值轴文本
	        							return {text:t+" cm"}
	        						}
	        					}
	        				}]
	        			}
	        		});
	        		//调用绘图方法开始绘图
	        		chart1.draw();
	        	});
	
	
	        	 //折线图
	 			$(function(){
					var data = [
					        	{
					        		name : '北京',
					        		value:[-9,1,12,20,26,30,32,29,22,12,0,-6],
					        		color:'#1f7e92',
					        		line_width:2
					        	}
					       ];
				       
					var chart = new iChart.Area2D({
							render : 'canvasDiv',
							animation:true,//产生动画效果
							data: data,
							title : '北京2012年平均温度情况',
							width : 450,
							height : 220,
							coordinate:{height:'90%',background_color:'#edf8fa'},
							sub_option:{
								hollow_inside:false,//设置一个点的亮色在外环的效果
								point_size:10
							},
							labels:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
						});
					
					chart.draw();
				});
	
	
	 	       	
	 			 //饼图  	 
	 	   			$(function(){
					var data2 = [
					        	{name : 'IE',value : 35.75,color:'#9d4a4a'},
					        	{name : 'Chrome',value : 29.84,color:'#5d7f97'},
					        	{name : 'Firefox',value : 24.88,color:'#97b3bc'},
					        	{name : 'Safari',value : 6.77,color:'#a5aaaa'},
					        	{name : 'Opera',value : 2.02,color:'#778088'},
					        	{name : 'Other',value : 0.73,color:'#6f83a5'}
				        	];
		        	
					new iChart.Pie2D({
						render : 'canvasDiv2',
						animation:true,//产生动画效果
						data: data2,
						title : 'Top 5 Browsers from 1 to 29 Feb 2012',
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
  <div class="panel"><div class="panel-body">
	<%--  	饼图--%>
	  	<div id='canvasDiv2' align="center" style="margin-top: 20px;margin-bottom: 20px;"></div>
	<%--  折线图	--%>
		<div id='canvasDiv' style="float: left;"></div>
	<%--  柱状图	--%>
		<div id='canvasDiv1' style="float: right;"></div>	
</div></div>
