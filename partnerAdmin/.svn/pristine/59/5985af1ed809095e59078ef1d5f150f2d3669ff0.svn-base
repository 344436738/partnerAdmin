<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Document</title>
		<script src="<%=path %>/js/jquery-1.9.1.js"></script>
		<script src="<%=path %>/js/bootstraptable/layer.js"></script>
		<script src="<%=path %>/js/bootstraptable/laycode.js"></script>
		<script src="<%=path %>/js/jquery-form.js"></script>
		<script type="text/javascript" src="js/coyote.js"></script> 
		<link rel="stylesheet" type="text/css" href="${msUrl}/css/hebase.css">
		<link rel="stylesheet" type="text/css" href="${msUrl}/css/liststyle.css">
		<link rel="stylesheet" type="text/css" href="${msUrl}/css/bootstraptable/layer.css">
		<link rel="stylesheet" type="text/css" href="${msUrl}/css/bootstraptable/laycode.css">
		 <script type="text/javascript">
		 function toggleMessage(){
			$('#msgTable').toggle('fast');			 
		 }
		 </script>
	</head>
	<body class="login_body">
		<div class="login_con" style="background:none;" >
		 
		 <table border="0" cellspacing="0" cellpadding="0" width="300" style="margin:auto">
		  <tr>
			<td rowspan="2"> <img src="<%=path %>/images/error.png" /> </td>
			<td align="left"><span style="color:blue;font-size:28px;">出错啦</span> &nbsp; &nbsp; <a href="javascript:location.reload();" style="color:blue">刷新</a></td>
		  </tr>
		  <tr>
			<td align="left"> 非常抱歉，页面加载失败 <a href="javascript:toggleMessage()" style="color:blue">[详情]</a> </td>
		  </tr>
		  <tr style="display:none" id="msgTable">
		  	<td colspan="2">
		  	${msg}<br />
		  	</td>
		  </tr>
		</table>
		</div>
	</body>
</html>
