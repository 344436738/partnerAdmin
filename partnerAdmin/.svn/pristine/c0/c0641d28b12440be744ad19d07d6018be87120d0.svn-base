<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${msUrl}/js/ux/siteNews/siteNewsColumn.js"></script> 
		
<script type="text/javascript">
	$(document).ready(function() {
		 $.validator.setDefaults({
				ignore: []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
		 $("#editForm").validate();
	});
</script>
<div class="panel">
   <div class="panel-heading">
		<form action="" class="form-inline" id="searchForm">
			<span class="manage_unit clearfix">
				<span class="manage_span_name">栏目名称：</span>
				<input type="text" name="name" id="name" class="form-control" >
				<input type="hidden" name="pid" id="_querypid" class="manage_input wd160">
				<input type="hidden" name="id" id="_id" class="manage_input wd160">
			</span>	 
			<button type="button"  onclick="search()" class="btn btn-primary">
						 <i class="fa  fa-search"></i>查询</button>
		</form>
		
		<div class="dashed-line"></div>
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
				返回顶部
			</span>
			
			<span class="btn btn-primary hidden" id="btnfhs"
				onclick="returnGo()" >
			<i class="fa fa-mail-reply"></i> 
				返回上一级
			</span>
		</div>
	</div>

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
				<form action="${msUrl}/siteNewsColumn/save.do" class="list_con_form clearfix" id="editForm" method="post">
					<input type="hidden" class="sub_text" id="id" name="id">
					<input type="hidden" name="pid" id='edit_parentId' />
					
					<p class="clearfix mtb8">
						<label class="control-label">栏目名称:</label>
						<input id="name" name="name" type="text" class="form-control" 
						data-rule-required="true" data-rule-maxlength="50">
					</p>
					
					<p class="clearfix mtb8">
						<label class="control-label">排序:</label>
						<input id="sorting" name="sorting" type="text"
						 class="form-control"  data-rule-required="true" 
						 data-rule-digits="true" data-rule-maxlength="20">
					</p>
					
					<p class="clearfix mtb8">
						<label class="control-label">描述:</label>
						<textarea id="description" name="description" class="form-control" 
						style="height: 50px;" data-rule-maxlength="100" ></textarea>
					</p>
					
					<p class="clearfix mtb8">
						<label class="control-label">类别:</label>
						<select class="form-control" name="type" id="type">
							<option value=" ">-请选择-</option>
							<option value="0">列表</option>
							<option value="1">内容</option>
						</select>
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
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<div class="panel-body">
<table id="table" class="table_data"></table>
</div></div>