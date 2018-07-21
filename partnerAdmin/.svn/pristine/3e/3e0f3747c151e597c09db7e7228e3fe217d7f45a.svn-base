<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>测试异常</title>
	<script type="text/javascript">
	function ajaxTestException1(){
		$.ajaxSetup({ //设置ajax请求全局默认设置
			async : true,
			error : function(jqXHR, textStatus, errorThrown){
				var msg = $.parseJSON(jqXHR.responseText).error;
				alert(msg);
			},
			traditional : true,
			dataType : "json",
			type : "POST"
		});
	}
	function ajaxTestException2(){
		$.ajax({ //请求将成功
			url: "demoAjax.action", 
			data: {name: "you param"},
			dataType : "json",
			type : "POST",
			success: function(data){
			alert("请求发送成功，返回数据：" + data.name);
			}
		});
	}
	function ajaxTestException3(){
		$.ajax({ //请求将失败，弹出人性化的错误信息
			url: "demoAjax.action", 
			dataType : "json",
			type : "POST",
			success: function(data){
			alert("请求发送成功，返回数据：" + data.name);
			}
		});
	}
	</script>
</head>
	
<body>
		<input type=button value="Ajax Exception Test" onclick="ajaxTestException1();"></input>
		<input type=button value="Ajax Exception Test" onclick="ajaxTestException2();"></input>
		<input type=button value="Ajax Exception Test" onclick="ajaxTestException3();"></input>
		<a href="demoPage.action?name=you name">将返回demo页面</a>
		<a href="demoPage.action">将返回错误页面</a> 
</body>
</html>