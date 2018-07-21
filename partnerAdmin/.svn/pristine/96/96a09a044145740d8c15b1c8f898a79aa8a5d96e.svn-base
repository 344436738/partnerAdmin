<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${msUrl}/js/weixinAttention/weixinAttention.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
			//初始化校验
			$.validator.setDefaults( {
				ignore : []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
			$("#editForm").validate();
			
			
																																																																laydate({
				    elem: '#subscribe_time', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
				    event: 'focus', //响应事件。如果没有传入event，则按照默认的click
				    format: 'YYYY/MM/DD hh:mm:ss',
				    istime: true,
				    istoday: true,
				    choose: function(datas){ //选择日期完毕的回调
				    	$("#editForm").validate().element($('#subscribe_time'))
				    }
				});
																								
		});
</script>
				
<!--查询条件  -->
	<div class="panel">
	   <div class="panel-heading">

			<form action="" class="form-inline" id="searchForm">
					<span class="manage_unit clearfix"> <span class="manage_span_name">类型：</span> <input
						type="text" name="roleName" id="new_type" class="form-control" />
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
			<span class="btn btn-primary" id="btnfh" fun="sameMenu"
					onclick="sync()" >
				<i class="fa  fa-upload"></i> 
					同步微信粉丝
			</span>
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
				关注用户信息
				</h4>
			 </div>
			 <div class="modal-body">
				<form action="${msUrl}/weixinAttention/save.do" method="post"
					class="list_con_form clearfix" id="editForm">
					
					
					
										<input id="id" name="id" type="hidden" class="gldint texwd160">
							
									       <p class="clearfix mtb8">
								<label class="control-label">用户唯一标识:</label>
								<input id="openid" name="openid" type="text"
									class="form-control" data-rule-required="true">
							</p>
							
									       <p class="clearfix mtb8">
								<label class="control-label">昵称:</label>
								<input id="nickname" name="nickname" type="text"
									class="form-control" data-rule-required="true">
							</p>
							
									       <p class="clearfix mtb8">
								<label class="control-label">性别:</label>
								<input id="sex" name="sex" type="text"
									class="form-control" data-rule-required="true">
							</p>
							
									       <p class="clearfix mtb8">
								<label class="control-label">城市:</label>
								<input id="city" name="city" type="text"
									class="form-control" data-rule-required="true">
							</p>
							
									       <p class="clearfix mtb8">
								<label class="control-label">国家:</label>
								<input id="country" name="country" type="text"
									class="form-control" data-rule-required="true">
							</p>
							
									       <p class="clearfix mtb8">
								<label class="control-label">身份:</label>
								<input id="province" name="province" type="text"
									class="form-control" data-rule-required="true">
							</p>
							
									       <p class="clearfix mtb8">
								<label class="control-label">语言:</label>
								<input id="language" name="language" type="text"
									class="form-control" data-rule-required="true">
							</p>
							
									       <p class="clearfix mtb8">
								<label class="control-label">用户头像:</label>
								<input id="headimgurl" name="headimgurl" type="text"
									class="form-control" data-rule-required="true">
							</p>
							
										
						<p class="manage_unit clearfix mtb15">
								<label class="control-label">手机号:</label>
								<input id="subscribe_time" name="subscribe_time" type="text"
									class="form-control" data-rule-required="true">
							</p>
							
							
									       <p class="clearfix mtb8">
								<label class="control-label">备注名称:</label>
								<input id="remark" name="remark" type="text"
									class="form-control" data-rule-required="true">
							</p>
							
									       <p class="clearfix mtb8">
								<label class="control-label">是否关注:</label>
								<input id="subscribe" name="subscribe" type="text"
									class="form-control" data-rule-required="true">
							</p>
							
									       <p class="clearfix mtb8">
								<label class="control-label">用户所在的分组ID:</label>
								<input id="groupid" name="groupid" type="text"
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


