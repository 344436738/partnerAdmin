<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${msUrl}/js/testcreate/testcreate.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
			//初始化校验
			$.validator.setDefaults( {
				ignore : []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
			$("#editForm").validate();
			
			
																						laydate({
				    elem: '#creatTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
				    event: 'focus', //响应事件。如果没有传入event，则按照默认的click
				    format: 'YYYY/MM/DD hh:mm:ss',
				    istime: true,
				    istoday: true,
				    choose: function(datas){ //选择日期完毕的回调
				    	$("#editForm").validate().element($('#creatTime'))
				    }
				});
																		
		});
</script>
				
<!--查询条件  -->
<div class="panel">
   <div class="panel-heading">
		<form action="" class="form-inline" id="searchForm">
		<div class="form-group">
						<label class="control-label">标题：</label>
          	<input type="text" id="title" class="form-control"> 
						<button type="button"  onclick="search()" class="btn btn-primary">
						 <i class="fa  fa-search"></i>查询</button>
		</div>
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
           	信息
            </h4>
         </div>
         <div class="modal-body">
				<form action="${msUrl}/testcreate/save.do" method="post"
					class="form-horizontal form-bordered" id="editForm">
					
																<input id="id" name="id" type="hidden" class="gldint texwd160">
										
															       <p class="manage_unit clearfix mtb15">
								<label class="control-label">标题:</label>
								<input id="title" name="title" type="text"
									 data-rule-required="true" class="form-control">
							</p>
										
																
						<p class="manage_unit clearfix mtb15">
								<label class="control-label">创建时间:</label>
								<input id="creatTime" name="creatTime" type="text"
									 data-rule-required="true" class="form-control">
							</p>
							
										
															       <p class="manage_unit clearfix mtb15">
								<label class="control-label">是否删除:</label>
								<input id="isDelete" name="isDelete" type="text"
									 data-rule-required="true" class="form-control">
							</p>
										
															       <p class="manage_unit clearfix mtb15">
								<label class="control-label">内容:</label>
								<input id="content" name="content" type="text"
									 data-rule-required="true" class="form-control">
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

