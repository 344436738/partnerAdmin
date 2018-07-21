<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript" src="<%=path %>/js/ux/sys/sysMenu.js"></script>

<script type="text/javascript">
	//初始化
	$(document).ready(function() {
		//校验
		 $.validator.setDefaults({
				ignore: []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
		 $("#editForm").validate();
		 
	});	
	
	</script>
<%--     查询条件  --%>
<div class="panel">
   <div class="panel-heading">
				<form action="" class="form-inline" id="searchForm">
				
				
				  <div class="form-group">
	              		
			              <label class="control-label">名称：</label>
			               		<input type="text" name="name" id="name" class="form-control"> 
								<input type="hidden" name="pid" id="_querypid" class="manage_input wd160">
						 <button type="button"  onclick="search()" class="btn btn-primary">
						 <i class="fa  fa-search"></i>查询</button>
            	</div>
            
				</form>
			
			<div class="dashed-line"></div>
			<%--   toolbar --%>
			<div   id="toolbar">
			<%--	<button type="button" class="btn btn-primary glyphicon glyphicon-plus">增加</button>--%>
			<%--	<button type="button" class="btn btn-primary glyphicon glyphicon-pencil">修改</button>--%>
			<%--	<button type="button" class="btn btn-primary glyphicon glyphicon-trash">删除</button>--%>
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
        
			<%--	 编辑窗口   --%>
			</div>
	</div>
	 
	 
	 

<!-- 模态框（Modal） -->
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
           	菜单信息
            </h4>
         </div>
         <div class="modal-body">
			           <form action="${msUrl}/sysMenu/save.do" class="form-horizontal form-bordered"
					id="editForm" method="post" style="padding-top: 0px;" enctype="multipart/form-data">
					 
					<input type="hidden" name="id" id="id" />
					<input type="hidden" name="deleted" id="deleted" />
					<p class="clearfix mtb8">
						<label class="control-label">菜单名：</label>
						<input id="name" name="name" type="text" class="form-control"
						data-rule-required="true" data-rule-maxlength="20" /> 
					</p>
					<p class="clearfix mtb8">
						<label class="control-label">菜单URL:</label>
						<input id="url" name="url" type="text" class="form-control"/> 
					</p>
					
					<p class="clearfix mtb8">
						<label class="control-label">排序序号:</label>
						<input id="rank" name="rank" type="text" class="form-control"
						data-rule-required="true" data-rule-digits="true" data-rule-maxlength="10"/> 
					</p>
					
					
					<p class="clearfix mtb8">
						<label class="control-label">菜单图标:</label>
						<!-- <input name="file" type="file" class="sub_text" /> -->
						<input  name="icon" type="text" class="form-control" />
					</p>
					
					
					<p class="clearfix mtb8">
						<label class="control-label">是否弹出新窗口:</label>
						<span class="rdio rdio-success">
								<input name="isopen" type="radio" id="isopen0"  value="0" checked />
								<label for="isopen0">否</label>
						</span>
						<span class="rdio rdio-success">
								<input name="isopen" type="radio"  value="1" id="isopen1" />
							<label for="isopen1">是</label>
						 </span>
					</p>
					
					
					<p class="clearfix mtb8">
			      		<label class="control-label">所属菜单:</label>
			     		  <select class="form-control" name="parentId" id="parentId">
								   <c:forEach items="${menuList}" var="menu">
									  <option value="${menu.id}" style="font-weight:bold;">&nbsp;&nbsp;${menu.name}</option>
								   </c:forEach>
						  </select>
			    	</p>
			    	
			    	<p class="clearfix mtb8">
						<label class="control-label">访问的action:</label>
						<input  name="actions" type="text" class="form-control" />
						<span>注册action按“|”分割</span>
					</p>
					
					
					<div class="tacz mtb8"
						style="border-top: 2px solid #eaeaea; padding-left: 25px;">
					</div>
					<p class="clearfix mtb8" style="margin-top: 10px;">
						<span class="btn btn-primary btn-sm" onclick="addLine()">
						 <i class="fa glyphicon-plus"></i> 增加 </span>
						<span class="btn btn-primary btn-sm" onclick="addDefBtns()">
						 <i class="fa glyphicon-plus"></i> 增加默认 </span>
						<span class="btn btn-maroon btn-sm" onclick="delAllLine()">
						全部删除 </span>
					</p>
					<div style="min-height: 120px;">
						<table class="table mb30" id="tyshuju">
							<thead>
								<tr>
									<th>按钮名称</th>
									<th>按钮类型</th>
									<th>注册action按“|”分割</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					 
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
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<div class="panel-body">
	<table id="table" class="table_data"></table>
</div></div>