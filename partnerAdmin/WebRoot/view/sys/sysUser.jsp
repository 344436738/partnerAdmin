<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${msUrl}/css/bootstraptable/bootstrap-multiselect.css">
<script type="text/javascript" src="${msUrl}/js/ux/sys/sysUser.js"></script>
<script src="${msUrl}/js/bootstraptable/bootstrap-multiselect.js"></script>
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
	//查询条件区域中的查询树的初始化设置
	var settingQuery = {
		async: {
			enable: true,
			dataType: "json",
			url:"<%=request.getContextPath()%>/sysOrg/findList.do"
		},	
		check:{
			enable:true,
			chkStyle: "radio",
			radioType: "all"
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true,//设置初始化是否为简单数据模式  如果设置为 true，请务必设置 setting.data.simpleData 内的其他参数: idKey / pIdKey rootPId，并且让数据满足父子关系
				pIdKey: "pid"
			}
		},
		callback: {
			onCheck:onCheck,
			onClick:onClick,
			onAsyncError: zTreeOnAsyncError
		}
	};
		//判断异步请求error
		function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
			if (XMLHttpRequest.status == "504") {
				layer.msg("登录超时，请重新登录", {
					tips : 2
				}, function() {
					location.reload();
				});
			}else{
				layer.msg("系统错误，请稍后再试", {
					tips : 2
				});
			} 
		}	
	
	//查询条件区域中的查询树的点击事件，调用check方法触发onCheck
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("queryTree");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	//查询条件区域中的查询树的选择事件，将选中值赋给查询区域中的input
	function onCheck(e, treeId, treeNode) {
		if(treeNode.checked==true){
			$("#orgName").val(treeNode.name);
			$("#queryOrgId").val(treeNode.id);
		}else{
			$("#orgName").val('');
			$("#queryOrgId").val('');		
		}
		hideMenu();
	}
	
	//查询条件区域中的查询树--显示
	function showMenu() {
		var cityObj = $("#orgName");
		var cityOffset = $("#orgName").position();
		$("#menuContent").css({
			left : cityOffset.left + "px",
			top : (cityOffset.top + cityObj.outerHeight()) + "px"
		}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
		var treeObj = $.fn.zTree.getZTreeObj("queryTree");
		treeObj.expandAll(true);
	}
	//查询条件区域中的查询树--隐藏
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	//设置页面的点击事件，如果在查询树打开的情况下点击树外边的区域，隐藏该树
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "orgName"
				|| event.target.id == "menuContent" || $(event.target).parents(
				"#menuContent").length > 0)) {
			hideMenu();
		}
	}
	//角色树的配置
	var settingEdit = {
			async: {
				enable: true,
				dataType: "json",
				url:"<%=request.getContextPath()%>/sysOrg/findList.do"
		},
		check : {
			enable : true,
			chkStyle : "radio",
			radioType : "all"
		},
		view : {
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true,//设置初始化是否为简单数据模式  如果设置为 true，请务必设置 setting.data.simpleData 内的其他参数: idKey / pIdKey rootPId，并且让数据满足父子关系
				pIdKey : "pid"
			}
		}
	};
	
	//初始化
	 $(document).ready(function() {
		 //初始化角色多选框
         $('#roleIds').multiselect({
		      enableFiltering: true
		  });
		 //初始化两个form的验证
         $.validator.setDefaults({
				ignore: []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
		 $("#editForm").validate();
		 $("#updateUserPwdForm").validate();
		 
		 //初始化权限编辑时的权限选择树
		 $.fn.zTree.init($("#editTree"), settingEdit);
		 //初始化查询树
		 $.fn.zTree.init($("#queryTree"), settingQuery);
		 
		 
     });
</script>
<style>
.ztree{padding:0px;}
</style>

<%-- 查询条件区域 --%>
<div class="panel">
   <div class="panel-heading">
<form action="" class="form-inline" id="searchForm">

<label class="control-label">账号：</label>
			               		<input type="text" name="email" id="email" class="form-control" /> 
						<label class="control-label">姓名：</label>
			               		<input type="text" name="nicknName" id="nicknName" class="form-control" /> 
						
							<!-- 样式不好调，先注释  -->
							
							<!-- <label class="control-label">组织机构：</label>
			               		<input type="text" name="orgName" id="orgName"  onclick="showMenu();" readonly class="form-control" /> 
						<a id="menuBtn" href="#" class="greentxt flole mr5"
		onclick="showMenu(); return false;">选择</a><a href="#" class="greentxt flole"
		onclick="clearOrgQueryParam();">清除</a>
		<span class="manage_unit clearfix"> 
	 <input type="text" id="queryOrgId" type="hidden"
		class="hidden" />
	</span>
						 -->
						 <button type="button"  onclick="search()" class="btn btn-primary">
						 <i class="fa  fa-search"></i>查询</button>
	 
</form>
<%--   查询条件区域---组织机构选择树 --%>
<div id="menuContent" class="menuContent"
	style="display:none; position: absolute;background-color: white;border:1px solid;z-index:9999">
	<ul id="queryTree" class="ztree"
		style="margin-top:0; width:180px; height: auto; ooverflow-y:scroll ;"></ul>
</div>


<div class="dashed-line"></div>
<%-- toolbar--%>
<div class="tacz" id="toolbar">

<span class="btn btn-primary" onclick="add()" fun="add">
				<i class="fa glyphicon-plus"></i> 
				增加</span>
				<span class="btn btn-primary" onclick="update()" fun="edit">
				<i class="fa glyphicon-pencil"></i> 
				修改</span> 
				<span class="btn btn-primary" onclick="updateUserPwd()" fun="editPwd">
				<i class="fa glyphicon-pencil"></i> 
				修改密码</span> 
				<span class="btn btn-maroon" onclick="del()" fun="remove">
				删除</span>
				
<span class="btn btn-primary" onclick="authorRole()" fun="chmod">
				<i class="fa glyphicon-plus"></i> 
				授权</span>
				
				 
		 
		
</div>
</div>
<%-- 授权窗口 --%>
<div class="modal fade" id="authorizeid" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
           	授权信息
            </h4>
         </div>
         <div class="modal-body">
	<p class="manage_unit clearfix mtb15">
		<input id="editId" name="id" type="hidden" class="gldint texwd160">
		<label class="control-label">email：</label>
		 <input
			id="emailEdit" name="email" type="text" class="form-control"
			readonly>
	</p>
	<p class="manage_unit clearfix mtb15">
		<label class="control-label">角色：</label> <select
			id="roleIds" name="roleIds" multiple="multiple" enableFiltering="true" class="form-control" >
			<c:forEach items="${roleList}" var="type">
				<option value="${type.id }">${type.roleName}</option>
			</c:forEach>

		</select>

	</p>
	</div>
	<div class="modal-footer">
            <button type="button" class="btn btn-default" 
              data-dismiss="modal" >关闭
            </button>
            <button type="button" onclick="authorRoleSave()" class="btn btn-primary">
               提交更改
            </button>
         </div>
          </div></div>
</div>
<%-- 编辑窗口  --%>
<div class="modal fade" id="editDiv" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
           		用户信息
            </h4>
         </div>
         <div class="modal-body">
	<form action="${msUrl}/sysUser/save.do" class="list_con_form clearfix"
		id="editForm" method="post">
		<input type="hidden" class="sub_text" id="id" name="id"> <input
			type="hidden" id="orgId" name="orgId">
			
		<p class="clearfix mtb8">
				<label class="control-label">Email：</label>
				<input id="Emailedit" name="email" type="text" class="form-control"
				data-rule-required="true" 
				data-rule-maxlength="50"/> 
		</p>
		<p class="clearfix mtb8">
			<label class="control-label">姓名:</label>
			<input id="nickName" name="nickName" type="text" class="form-control"
			 data-rule-required="true" data-rule-maxlength="50" /> 
		</p>
		
		<p class="clearfix mtb8">
			<label class="control-label">电话:</label>
			<input id="phone" name="phone" type="text" class="form-control"
			  data-rule-maxlength="50" /> 
		</p>
		
		  
		<p class="manage_unit clearfix mtb5">
			<label class="control-label">性别:</label>
			
			<span class="rdio rdio-success">
					<input name="sex" type="radio" id="sex0"  value="1" checked />
					<label for="sex0">男</label>
			</span>
			<span class="rdio rdio-success">
				
					<input name="sex" type="radio"  value="0" id="sex1" />
				<label for="sex1">女</label>
			 </span>
		 
		</p>
		<p class="manage_unit clearfix mtb5">
			<label class="control-label">状态：</label>
			<span class="rdio rdio-success">
					<input name="state" type="radio" id="state0"  value="0" checked />
					<label for="state0">可用</label>
			</span>
			<span class="rdio rdio-success">
				
					<input name="state" type="radio"  value="1" id="state1" />
				<label for="state1">禁用</label>
			 </span>
		</p>
		 
	
		<p class="manage_unit clearfix mtb5">
			<label class="control-label">组织机构：</label>
			<div class="control-label">
			<span
				class="form-control"
				style="height:200px;overflow: auto; display:inline-block;">
				<span id="editTree" class="ztree"> </span> 
			</span>
			</div>
		</p>
	</form>
	</div>
	
	<div class="modal-footer">
            <button type="button" class="btn btn-default" 
              data-dismiss="modal" >关闭
            </button>
            <button type="button" onclick="updateSave()" class="btn btn-primary">
               提交更改
            </button>
    </div>
</div>
</div>
</div>
 

<%-- 修改密码 --%>


<div class="modal fade" id="updatepassword" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
           	修改密码
            </h4>
         </div>
         <div class="modal-body">
         
	<form action="" id="updateUserPwdForm">
		<input class="hidden" name="id" id="updateUserPwdId"> <input
			class="hidden" name="email">
			
		<p class="clearfix mtb8">
			<label class="control-label">旧密码:</label>
			 <input
				 type="text" id="oldPwd" name="oldPwd" type="password" class="form-control">
		</p>
		<p class="clearfix mtb8">
			<label class="control-label">新密码:</label>
			 <input
				 type="text" id="newPwd" name="newPwd" type="password" 
				 data-rule-pwd="true" data-rule-required="true"
				data-rule-rangelength="[6,20]" class="form-control" />
		</p>
		<p class="clearfix mtb8">
			<label class="control-label">重复密码:</label>
			 <input
				 type="text" id="rpwd" name="rpwd" type="password"
				data-rule-pwd="true" data-rule-equalTo="#newPwd"  class="form-control" />
		</p>
	</form>
	  </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
              data-dismiss="modal" >关闭
            </button>
            <button type="button" onclick="updateUserPwdSave()" class="btn btn-primary">
               提交更改
            </button>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<div class="panel-body">
<table id="table" class="table_data"></table>
</div></div>
