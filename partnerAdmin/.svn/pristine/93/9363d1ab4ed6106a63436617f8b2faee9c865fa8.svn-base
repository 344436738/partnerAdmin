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
			           <form action="${msUrl}/sysMenu/save.do" class="form-inline"
					id="editForm" method="post" style="padding-top: 0px;" enctype="multipart/form-data">
					 
					<input type="hidden" name="id" id="id" />
					<input type="hidden" name="deleted" id="deleted" />
					<input type="hidden" name="parentId" id='edit_parentId' />
					<p class="clearfix mtb8">
						<label class="control-label">菜单名：</label>
						<input id="name" name="name" type="text" class="form-control"
						data-rule-required="true" data-rule-maxlength="20" /> 
					</p>
					<p class="clearfix mtb8">
						<span class="manage_span_name inline_inblo pl15 wd120 txtri">菜单URL:</span>
						<input id="url" name="url" type="text" class="manage_input wd160" />
					</p>
					<p class="clearfix mtb8">
						<span class="manage_span_name inline_inblo pl15 wd120 txtri">排序序号:</span>
						<input id="rank" name="rank" type="text" class="manage_input wd160"
							data-rule-required="true" data-rule-digits="true" data-rule-maxlength="10" />
					</p>
					<p class="clearfix mtb8">
						<span class="manage_span_name inline_inblo pl15 wd120 txtri">菜单图标:</span>
						<input name="file" type="file" class="sub_text" />
						<input type="hidden" name="icon" />
					</p>
					
					<p class="sub_form_list clearfix">
			      		<span class="sub_list_name">所属菜单：</span>
			     		  <select class="manage_input wd160" name="parentId" id="parentId">
								   <c:forEach items="${menuList}" var="menu">
									  <option value="${menu.id}" style="font-weight:bold;">&nbsp;&nbsp;${menu.name}</option>
								   </c:forEach>
						  </select>
			    	</p>
					
					<p class="manage_unit clearfix mtb8">
						<span class="manage_span_name inline_inblo pl15 wd120 txtri">访问的action:</span>
						<input id="actions" name="actions" type="text"
							class="manage_input wd160" />
						<span>注册action按“|”分割</span>
					</p>
					<div class="tacz mtb8"
						style="border-top: 2px solid #eaeaea; padding-left: 25px;">
						<span class="inline_inblo mr15 pl15 bg_btn btnimg20" onclick="addLine()"> <span
							class="add_btn inline_inblo pl15">增加</span> </span>
						<span class="inline_inblo mr15 pl15 bg_btn btnimg20" onclick="addDefBtns()"> <span
							class="add_btn inline_inblo pl15">增加默认</span> </span>
						<span class="inline_inblo mr15 pl15 bg_btn btnimg20" onclick="delAllLine()"> <span
							class="add_btn inline_inblo pl15">全部删除</span> </span>
					</div>
					<div style="min-height: 120px;">
						<table class="table_data table_popup" id="tyshuju">
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
					<p class="pb30" style="text-align: center;">
						<input type="button" value="确定" onclick="updateSave()" id="test1" class="orage_bg prl20 mr15 h32">
						<input type="reset" value="取消" onclick="closePop()" class="bglv prl20 mr15 h32">
					</p>
				</form>
         </div>
         <div class="modal-footer">
            <button type="button" onclick="closePop()" class="btn btn-default" 
               data-dismiss="modal">关闭
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
</div>