<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
	<title>新闻列表页</title>
	<link rel="stylesheet" href="${msUrl}/css/bootstrap-responsive.css">
	<link rel="stylesheet" href="${msUrl}/css/bootstrap.css">
	<!--  <link rel="stylesheet" href="${msUrl}/css/documentation.css">-->
	<link rel="stylesheet" type="text/css" href="${msUrl}/css/hebase.css">
	<link rel="stylesheet" type="text/css" href="${msUrl}/css/liststyle.css">
	<script src="${msUrl}/js/coyote.js"></script>
	<script src="${msUrl}/js/jquery-1.9.1.js"></script>
	<script src="${msUrl}/js/bootstraptable/bootstrap.min.js"></script>
	<script src="${msUrl}/js/bootstraptable/bootstrap-paginator.js"></script>
 
<script type="text/javascript">
	$(document).ready(function() {
		var options = {
	            currentPage: ${currentPage},
	            totalPages: ${totalPage},
	            alignment:"right",
	            useBootstrapTooltip:true,
          		pageUrl: function(type, page, current){
		        	return "${msUrl}/siteNews/list.shtml?page="+page;
		        }
		    }
		$('#example').bootstrapPaginator(options);
	});
</script>

<body class="headbody">
<!-- 头部开始 -->
<div class="header">
	<div class="wid1000 clearfix headerdiv">
		<img src="${msUrl}/images/he_logo.png" class="header_logo" height="48" width="144">
		<ul class="header_ul clearfix">
			<li><a href="首页.htm" class="ft18">首页</a></li>
			<li><a href="帮助中心.htm" class="ft18">帮助中心</a></li>
			<li><a href="关于我们.htm" class="ft18">关于我们</a></li>
			<li><a href="企业中心.htm" class="ft18">企业中心</a></li>
		</ul>
	</div>
</div>
<!-- 头部结束 -->

<!-- 内容开始 -->
<div class="cont_news clearfix wid1000">
<div class="news_left" >
	<h3 class="news_tit ft24 ftno">新闻列表</h3>
	<ul class="news_con_list">
		<c:forEach items="${sitenewsList}" var="sitenews">
			<li class="clearfix">
			    <a href="show.do?id=${sitenews.id}" class="news_img_con mr15"><img src="${msUrl}/${sitenews.pic}"></a>
			    <div class="news_con_rig flole">
			    <h4 class="news_con_tit ft24"><a href="show.do?id=${sitenews.id}">${sitenews.title }</a></h4>
			    <p class="newscon">
			        <a href="show.do?id=${sitenews.id}" class="news_con_a">
			        	<span class="newsjs ft18">${sitenews.summary}</span>
			        </a>
			         	<span class="newstim ft18">发布时间：<fmt:formatDate value="${sitenews.createTime}" type="date"/> </span>
			    </p>
			    </div>
			</li>
		</c:forEach>
	</ul>
<div id="example" ></div>
</div>
	<div class="news_rig">
	    <h4 class="news_rig_tit ft18">新闻分类</h4>
	    <ul id="newsul">
			<c:forEach items="${columnList}" var="column">
				<li>
					<span class="inline_blo news_rig_a ft16 pointer">
			        	<span class="inline_inblo news_tit_rig_lef">${column.name}</span>
			        	<span class="inline_inblo news_tit_rig_rig"></span>
			        </span>
			        <c:forEach items="${column.childList}" var="child">
					 	<a href="${msUrl}/siteNews/list.do?pid=${child.id}" class="inline_blo news_son_tit chacol h32">${child.name}</a>
					</c:forEach>
				</li>
			</c:forEach>
	    </ul>
	</div>
</div>
<!-- 内容结束 -->

<!-- 底部开始 -->
<div class="footer">
	<p class="footp ft12">All Rights Reserved. Copyright 2015 XX科技有限股份有限公司</p>
</div>
<!-- 底部结束 -->
 
<script type="text/javascript">
	var oLisUl=document.getElementById('newsul');
	var aLisA=oLisUl.getElementsByTagName('a');
	//alert(aLisA.length);
	for(var i=0;i<aLisA.length;i++){
		aLisA[i].index=i;
		aLisA[i].style.display='none';
		aLisA[i].onclick=function(){
			for(var j=0;j<aLisA.length;j++){
				aLisA[j].style.color='#99a8ac';
		  	}
		  	this.style.color="#4dbfe5";
		}
	}
	//列表点击收缩展开
	var oLisLi=oLisUl.getElementsByTagName('li');
	var aLisSpa=getByInClass(oLisUl,'news_rig_a');
	
	for(var k=0;k<aLisA.length;k++){
	  aLisA[k].style.display='none';
	}
	
	for(var i=0;i<oLisLi.length;i++){
		aLisSpa[i].index=i;
		oLisLi[i].index=i;
		aLisSpa[i].onclick=function(){
			//alert(this.index);
			var curSp=this.index;
			var aLiSpA=oLisLi[this.index].getElementsByTagName('a');
			if(aLiSpA.length>0){
				if(aLiSpA[0].style.display=='none'){
					for(var k=0;k<aLisA.length;k++){
						aLisA[k].style.display='none';
					}
					for(var j=0;j<aLiSpA.length;j++){
					   	aLiSpA[j].style.display='block';
					}
			  	}else{
				    for(var k=0;k<aLisA.length;k++){
				     	aLisA[k].style.display='none';
				    }
		  	 	}
			}
		}
    }
</script>
</body>