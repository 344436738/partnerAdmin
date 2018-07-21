<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>个人中心-个人管理</title>
   
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="<%=path %>/js/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="<%=path %>/js/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="<%=path %>/js/data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="<%=path %>/js/files/geRenGuanli/styles.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/files/hhrnew/reset.css"/>
     <script src="<%=path %>/js/resources/scripts/jquery-1.7.1.min.js"></script>
     <script src="<%=path %>/js/resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/axQuery.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/globals.js"></script>
     <script src="<%=path %>/js/resources/scripts/axutils.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/annotation.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/axQuery.std.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/doc.js"></script>
     <script src="<%=path %>/js/data/document.js"></script>
     <script src="<%=path %>/js/resources/scripts/messagecenter.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/events.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/recording.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/action.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/expr.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/geometry.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/flyout.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/ie.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/model.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/repeater.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/sto.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/utils.temp.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/variables.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/drag.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/move.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/visibility.js"></script>

     <script src="<%=path %>/js/resources/scripts/axure/style.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/adaptive.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/tree.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/init.temp.js"></script>
     <script src="<%=path %>/js/files/geRenGuanli/data.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/legacy.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/viewer.js"></script>
     <script src="<%=path %>/js/resources/scripts/axure/math.js"></script>
    <script type="text/javascript">
      $axure.utils.getTransparentGifPath = function() { return 'resources/images/transparent.gif'; };
      $axure.utils.getOtherPath = function() { return 'resources/Other.html'; };
      $axure.utils.getReloadPath = function() { return 'resources/reload.html'; };
    </script>
  </head>
  <body>
	<div class="box" >
    	<!--头部-->
    	<header>
    		<img src="<%=path %>/image/hhrnewgr/logo.png"  class="logo"/>
    		<ul>
    			<li ><a href=" ">首页</a></li>
    			<li><a href="商机推荐.html" >合伙人中心</a></li>
    			<li><a href="">简历库</a></li>
    			<li><a href="${pageContext.request.contextPath}/stBusinessCenter/confirm.do" style="font-family: PingFangSC-Medium;font-size: 18px;color: #1BA3F8;letter-spacing: 0px; ">商机中心</a></li>
    		</ul>
    		<p>
    			<span><a href="${pageContext.request.contextPath}/partnerCenter/personalInformation.do"">
    				<img src="<%=path %>/image/persionone/bell.png" style="width: 17px; height: 21px; margin-top: 11px;"/>
    			</a>
    			<a href="">
    				
    				<img src="<%=path %>/image/persionone/user.png" style="width: 40px; height: 40px;" />
    			</a></span>
    		</p>
    	</header>
  </div> 
  <nav>
  	<div>
			<ul>
				<li><a href="<%=path %>/view/common/updatePwd.jsp">修改密码</a></li>
				<li style="border-bottom: solid 3px #FFFFFF;"><a href="${pageContext.request.contextPath}/partnerCenter/personalInformation.do">个人设置</a></li>
			</ul>
		</div>
	</nav>
	
	<div class="main">
		<h2 style="margin-top: 40px;">个人设置</h2>
		<p>提示：请在安全的网络环境下上网</p>
		<form action="${pageContext.request.contextPath}/partnerCenter/personalInformationSave.do"
						method="post" enctype="multipart/form-data"
						class="list_con_form clearfix" id="editForm">

						<p><input id="id" name="id" type="hidden" value="${data.id}" /> </p>
						<span>昵称</span><p><input id="nickname" name="nickname" value="${data.nickname}" /> </p>
						<span>手机号</span><p><input id="phone" name="phone" value="${data.phone}"data-rule-required="true" /> </p>
						<span>邮箱</span> <p><input id="email" name="email" value="${data.email}" /></p>
						 <span>所在单位名称</span> <p><input id="companyname" name="companyname" value="${data.companyname}" /></p> 
					 <span>QQ号</span><p><input id="qq" name="qq" value="${data.qq}" /> </p>
						 <span>微信号</span><p><input id="wechat" name="wechat" value="${data.wechat}" />  </p>
						<span>收款人姓名</span><p><input id="recipient" name="recipient" value="${data.recipient}" data-rule-required="true" /></p>
						<span>银行类型</span><p><input id="banktype" name="banktype" value="${data.banktype}" data-rule-required="true" />  </p>
						<span>银行卡号</span><p><input id="cradno" name="cardno" value="${data.cardno}" data-rule-required="true" /></p>
						
					<p><button>
							<a href="">登录</a>
						</button></p>
		</form>
		
	</div>
	
  </body>
	<script>

		
		
		
		
	</script>

</html>
