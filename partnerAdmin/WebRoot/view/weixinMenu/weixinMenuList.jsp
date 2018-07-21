<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript" src="${msUrl}/js/weixinMenu/weixinMenu.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
			//初始化校验
			/*  $.validator.setDefaults( {
				ignore : []
			});//如果有display:none的情况，加上这个，会验证display none的必填项 */
			$("#editForm").validate(); 
			//根据不同消息类型作出判断
			var inputAttr = $("input[name='msgtype']");
			for(var i=0;i<inputAttr.length;i++){
				$(inputAttr[i]).click(function(){
				   var textVal = $(this).val();
				   if("text"==textVal){
				   		getTemplates("text");
				   }/* else if("expand"==textVal){//扩展接口
				   		getTemplates("expand");
				   } */else if("news"==textVal){
				   		getTemplates("news");
				   }
				});
			}
																																																													
		});
	function ChooseMsgType(){

		if($('#type').val()=='click')
		{
		$('#click_hidde').hide();
		$('#click_show').show();
		}else
		{
			$('#click_hidde').show();
			$('#click_show').hide();
		}
				
	}
	function getTemplates(msgType){
		var loading=layer.load(2);
 		var templateObj = $("#templateid");
 		templateObj.html("");
 		$.ajax({
 			url:"${msUrl}/weixinMenu/gettemplate.do",
 			data:{"msgType":msgType},
 			dataType:"JSON",
 			type:"POST",
 			success:function(result){
 				if (result.success) {
 					//关闭layer
					var msg="";
	 				msg += "<option value=''>------</option>";
	 				for(var i=0;i<result.data.length;i++){
	 					
	 					msg += "<option value='"+result.data[i].id+"'>"+result.data[i].templatename+"</option>";
	 					/* if(msgType=="expand"){
	 					 	msg += "<option value='"+result.data[i].id+"'>"+result.data[i].name+"</option>";
	 					}else{
	 					 	msg += "<option value='"+result.data[i].id+"'>"+result.data[i].templateName+"</option>";
	 					} */
	 				}
	 				templateObj.html(msg);
	 				layer.close(loading);
				}else{
					layer.msg(result.msg, {
						icon : -1,
						time : 1000
					//2秒关闭（如果不配置，默认是3秒）
							});
				};
 			},
 			error: function() {
 				layer.msg("网络异常，请稍后再试！", {
					icon : -1,
					time : 1000
				//2秒关闭（如果不配置，默认是3秒）
						});
 				}
 		});
 	}
	function sameMenu(){
		var loading=layer.load(2);
		$.ajax({
			url:"${msUrl}/weixinMenu/sameMenu.do",
			type:"GET",
			dataType:"JSON",
			success:function(data){
				if(data.success){
					layer.msg(data.msg, {tips:2});
					layer.close(loading);
				}else{
					layer.msg(data.msg, {tips:2});
					layer.close(loading);
				}
			},
			error: function() {
 				layer.msg("网络异常，请稍后再试！", {
					icon : -1,
					time : 1000
				//2秒关闭（如果不配置，默认是3秒）
						});
 				layer.close(loading);
 				}
		});
	}
</script>
				
<!--查询条件  -->
<div class="panel">
   <div class="panel-heading">
	<form action="" class="form-inline" id="searchForm" >
			<span class="manage_unit clearfix"> <span class="manage_span_name">菜单名称：</span> <input
				type="text" name="name" id="name" class="form-control" />
				
				<input type="hidden" name="fatherid" id="_querypid" class="manage_input wd160">
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
		<span class="btn btn-primary"  fun="sameMenu"
			onclick="sameMenu()" >
		<i class="fa  fa-upload"></i> 
			菜单同步到微信
		</span>
		
		
		 <span class="btn btn-primary hidden" id="btnfh"
					onclick="returnFid()" >
		<i class="fa fa-mail-reply"></i> 
			返回
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
				菜单信息
				</h4>
			 </div>
			 <div class="modal-body">
					<form action="${msUrl}/weixinMenu/save.do" method="post"
						class="list_con_form clearfix" id="editForm">
						
									<input id="id" name="id" type="hidden" class="gldint texwd160">
									<input type=hidden name="fatherid" id="edit_parentId" class="manage_input wd160"> 
								  <p class="clearfix mtb8">
										<label class="control-label">菜单名：</label>
										<input id="name" name="name" type="text" class="form-control"
										data-rule-required="true" data-rule-maxlength="20" /> 
									</p>
								
						       <p class="clearfix mtb8">
									<label class="control-label">动作设置：</label>
									<!-- <input id="type" name="type" type="text" class="manage_input wd160" data-rule-required="true"> -->
									<select name="type" id="type"  class="form-control" onchange="ChooseMsgType()">
								     	<option value="click"  <c:if test="${type=='click'}">selected="selected"</c:if>>消息触发类</option>
								     	<option value="view"  <c:if test="${type=='view'}">selected="selected"</c:if>>网页链接类</option>
								     </select>	
								</p>
								<div id="click_show">
									<!-- <input id="msgtype" name="msgtype" type="text" class="manage_input wd160" data-rule-required="true"> -->
									 <p class="clearfix mtb8">
									 	<span class="rdio rdio-success">
										 	<input type="radio" value="text" name="msgtype" id="msgTypeText" datatype="*"  <c:if test="${msgType=='text'}">checked="checked"</c:if>/>
										 	<label for="msgTypeText">文本</label>
									 	</span>
									 	<span class="rdio rdio-success">
								        	<input type="radio" value="news" name="msgtype" id="msgTypeNews" <c:if test="${msgType=='news'}">checked="checked"</c:if>/>
								        	<label for="msgTypeNews">图文</label>
								        </span>
								        <%-- <input type="radio" value="expand" name="msgType"  <c:if test="${msgType=='expand'}">checked="checked"</c:if>/>扩展&nbsp; --%>
									</p>
									
							        <p class="clearfix mtb8">
							        	<label class="control-label">选择模版:</label>
										<select name="templateid" class="form-control" id="templateid" class="manage_input wd160" >
								     	</select>	
									</p>
								</div>
								<div id="click_hidde" style="display:none;"> 
									<p class="clearfix mtb8">
										<label class="control-label">URL:</label>
										<input id="url" name="url" type="text" 
											class="form-control" >
									</p>
								</div>
								<p class="clearfix mtb8">
									<label class="control-label">菜单标识:</label>
									<input id="menukey" name="menukey" type="text"
										class="form-control" data-rule-required="true">
								</p>
								
								
						       <p class="clearfix mtb8">
									<label class="control-label">顺序:</label>
									<input id="sorting" name="sorting" type="text"
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

