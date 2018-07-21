<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${msUrl}/js/weixinHuodong/weixinHuodong.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		//初始化校验
			$.validator.setDefaults( {
				ignore : []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
			$("#editForm").validate();
			
			
																																																																laydate({
				    elem: '#starttime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
				    event: 'focus', //响应事件。如果没有传入event，则按照默认的click
				    format: 'YYYY/MM/DD hh:mm:ss',
				    istime: true,
				    istoday: true,
				    choose: function(datas){ //选择日期完毕的回调
				    	$("#editForm").validate().element($('#starttime'))
				    }
				});
													laydate({
				    elem: '#endtime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
				    event: 'focus', //响应事件。如果没有传入event，则按照默认的click
				    format: 'YYYY/MM/DD hh:mm:ss',
				    istime: true,
				    istoday: true,
				    choose: function(datas){ //选择日期完毕的回调
				    	$("#editForm").validate().element($('#endtime'))
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
				活动信息
				</h4>
			 </div>
			 <div class="modal-body">
			<form action="${msUrl}/weixinHuodong/save.do" method="post"
				class="list_con_form clearfix" id="editForm">
				
				
				
					 <input id="id" name="id" type="hidden" class="gldint texwd160">
					 <input id="type" name="type" type="hidden" class="gldint texwd160" value="${type}"/>
						
				      <p class="clearfix mtb8">
							<label class="control-label">活动名称:</label>
							<input id="title" name="title" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
				      <p class="clearfix mtb8">
							<label class="control-label">活动描述:</label>
							<input id="description" name="description" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
				      <p class="clearfix mtb8">
							<label class="control-label">一等奖奖品:</label>
							<input id="priceone" name="priceone" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
				      <p class="clearfix mtb8">
							<label class="control-label">一等奖数量:</label>
							<input id="onetotal" name="onetotal" type="text"
								class="form-control" data-rule-required="true">
						</p>
						<p class="manage_unit clearfix mtb8 flole wd375">
							<label class="control-label">一等奖概率:</label>
							<input id="glone" name="glone" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
				      <p class="clearfix mtb8">
							<label class="control-label">二等奖奖品:</label>
							<input id="pricetwo" name="pricetwo" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
				      <p class="clearfix mtb8">
							<label class="control-label">二等奖数量:</label>
							<input id="twototal" name="twototal" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
						<p class="manage_unit clearfix mtb8 flole wd375">
							<label class="control-label">二等奖概率:</label>
							<input id="gltwo" name="gltwo" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
				      <p class="clearfix mtb8">
							<label class="control-label">三等奖奖品:</label>
							<input id="pricethree" name="pricethree" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
				      <p class="clearfix mtb8">
							<label class="control-label">三等奖数量:</label>
							<input id="threetotal" name="threetotal" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
						<p class="clearfix mtb8">
							<label class="control-label">三等奖概率:</label>
							<input id="glthree" name="glthree" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
						<p class="clearfix mtb8">
							<label class="control-label">抽奖次数:</label>
							<input id="count" name="count" type="text"
								class="form-control" data-rule-required="true">
						</p>
									
						<p class="clearfix mtb8">
							<label class="control-label">开始时间:</label>
							<input id="starttime" name="starttime" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
									
						<p class="clearfix mtb8">
							<label class="control-label">结束时间:</label>
							<input id="endtime" name="endtime" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
						
				      <p class="clearfix mtb8">
							<label class="control-label">中间概率:</label>
							<input id="gl" name="gl" type="text"
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



