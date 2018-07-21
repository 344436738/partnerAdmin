<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${msUrl}/js/weixinNewstemplate/weixinNewstemplate.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
			//初始化校验
			$.validator.setDefaults( {
				ignore : []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
			$("#editForm").validate();
		});
</script>
				
<!--查询条件  -->
<div class="panel">
   <div class="panel-heading">
			<form action="" class="form-inline" id="searchForm">
					<span class="manage_unit clearfix"> <span class="manage_span_name">图文名称：</span> <input
						type="text" name="tempatename" id="tempatename" class="form-control"  />
				</span>
					<button type="button"  onclick="search()" class="btn btn-primary">
						 <i class="fa  fa-search"></i>查询</button>
			</form>
			<div class="dashed-line"></div>
			<!--toolbar-->
			<div class="tacz" id="toolbar">
				<span class="btn btn-primary" onclick="add()" fun="add">
					<i class="fa glyphicon-plus"></i> 
					增加</span>
				<span class="btn btn-primary" onclick="update()" fun="edit">
					<i class="fa glyphicon-pencil"></i> 
					修改</span> 
				<span class="btn btn-maroon" onclick="del()" fun="remove">
					删除</span>
			</div>
	</div>
<!-- 编辑窗口   ----->
<!-- 编辑窗口   ----->
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
				图文信息
				</h4>
			 </div>
			 <div class="modal-body">
			<form action="${msUrl}/weixinNewstemplate/save.do" method="post"
				class="list_con_form clearfix" id="editForm">
				<input id="id" name="id" type="hidden" class="gldint texwd160">
				<p class="clearfix mtb8">
					<label class="control-label">模板名称：</label>
					<input id="tempatename" name="tempatename" type="text"
						class="form-control" data-rule-required="true">
				</p>
				
				<p class="clearfix mtb8">
					<label class="control-label">模板类型：</label>
					<input id="type" name="type" type="text"
						class="form-control" data-rule-required="true">
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

