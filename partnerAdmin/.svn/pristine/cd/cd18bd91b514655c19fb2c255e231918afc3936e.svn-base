<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript" src="${msUrl}/js/ux/siteNews/siteNews.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/ztree/jquery.ztree.exedit-3.5.js" /></script>
	
	<script type="text/javascript">

	var setting = {
		async: {
			enable: true,
			dataType: "json",
			url:"<c:url value="siteNewsColumn/findNewsColumnList.do"/>"//加载树
		},
		data: {
			simpleData: {
				enable: true,//设置初始化是否为简单数据模式  如果设置为 true，请务必设置 setting.data.simpleData 
				pIdKey: "pid",//	内的其他参数: idKey / pIdKey rootPId，并且让数据满足父子关系
				rootPId: 0				
			}
		},
		callback: {
			onClick: zTreeOnClick,
			onAsyncSuccess:expandAllTree //展开所有节点方法
		}		
	 }
	var zNodes =[{name:"全部",pid:"0",isParent:true}];
	
	function zTreeOnClick(event, treeId, treeNode) {
	    $("#pid").val(treeNode.id);
		search();
	}

	//展开全部节点
	function expandAllTree(){
		var treeAll = $.fn.zTree.getZTreeObj("treeDemo");
		treeAll.expandAll(true);		
	}
	
	$(document).ready(function(){			
		$.fn.zTree.init($("#treeDemo"), setting ,zNodes);
	});
		
</script>
<!-- <div style="float: left">
	<div class="zTreeDemoBackground left" style="width: 200px;float: left;margin-top: 20px;">
		<ul id="treeDemo" class="ztree"></ul>	
	</div>
</div>
 -->
<div class="panel">
	
	<div class="col-sm-3" >
	<div class="panel">
	<div class="panel-body">
		<ul id="treeDemo" class="ztree"></ul>	
	</div>
	</div>
	</div>
	<div class="col-sm-9"   id="ifdiv" >
			<div class="panel">
  				<div class="panel-heading">
					<form action="" class="form-inline" id="searchForm">
						<label class="control-label">标题：</label> <input
								type="text" name="title" id="title" class="form-control">
					
						</span>
						<label class="control-label">栏目：</label>
						<!--火狐浏览器30以上的版本下拉框宽高合适，30以下不合适  --> <select class="form-control"
								name="pid" id="pid">
								<option value="" style="font-weight: bold;">
									&nbsp;&nbsp;全部
								</option>
								<c:forEach items="${columnList}" var="column">
									<option value="${column.id}" style="font-weight: bold;" <c:if test="${column.id==queryPid}">selected</c:if>>
										&nbsp;&nbsp;${column.name}
									</option>
									<c:forEach items="${column.childList}" var="child">
										<option value="${child.id}" <c:if test="${child.id==queryPid}">selected</c:if>>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${child.name}
										</option>
									</c:forEach>
								</c:forEach>
							</select> 
						 <button type="button"  onclick="search()" class="btn btn-primary">
						 <i class="fa  fa-search"></i>查询</button>
					</form>
					<div class="xxfj"></div>
					<div class="tacz" id="toolbar">
					<span class="btn btn-primary" onclick="add()" fun="add">
						<i class="fa glyphicon-plus"></i> 
						增加</span>
					<span class="btn btn-primary" onclick="update()" fun="edit">
						<i class="fa glyphicon-pencil"></i> 
						修改</span> 
					<span class="btn btn-maroon" onclick="del()" fun="remove">
						删除</span>
						<span class="btn btn-primary hidden" id="btnfh"
							onclick="returnFid()" >
						<i class="fa fa-mail-reply"></i> 
							返回
						</span>
					
										</div>
					</div>
					<div class="panel-body">
					<table id="table" class="table_data"></table>
					</div>
					</div>
	</div>	
	</div>						
</div>

<div style="float: right">


<style type="text/css">
	.ztree{border:1px solid #AEB9BF;padding-bottom:90px;}
	.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	#treeDemo{min-height:380px;}
	div .ztree *{font-size:14px;}
</style>
