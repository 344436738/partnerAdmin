<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${msUrl}/js/weixinSubscribe/weixinSubscribe.js"></script>

<script type="text/javascript">

	var textList = "${textList}";
	var newsList = "${newsList}";
	$(document).ready(function() {
			/* //初始化校验
			$.validator.setDefaults( {
				ignore : []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
			$("#editForm").validate(); */
		var newsHtml = $("#newsId").html();
  	    var textHtml = $("#textId").html();
  	    
  		$("#msgType").change(function(){
  			var value = $(this).val();
  			
  			if("text"==value){
  				$("#textId").removeAttr("style");
  				$("#textContent").removeAttr("disabled");
  				$("#newsId").attr("style","display:none");
  				$("#newsContent").attr("disabled","disabled");
  			}else{
  			  
  				$("#textId").attr("style","display:none");
  				$("#textContent").attr("disabled","disabled");
  				$("#newsId").removeAttr("style");	
  				$("#newsContent").removeAttr("disabled");	
  				
  			}
  			
  		});
  	  
  	  var lx = "${lx}";
  	  if(lx=='text'){
  	  	$("#textId").removeAttr("style");
		$("#textContent").removeAttr("disabled");
		$("#newsId").attr("style","display:none");
		$("#newsContent").attr("disabled","disabled");
  	  }else if(lx=='news'){
  	  	$("#textId").attr("style","display:none");
		$("#textContent").attr("disabled","disabled");
		$("#newsId").removeAttr("style");	
		$("#newsContent").removeAttr("disabled");	
  	  }
			
		});
</script>
<!--查询条件  -->
<div class="panel">
   <div class="panel-heading">				
		<input type="text" id="count" class="hidden" value="${count}" />
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
<%--	 编辑窗口   --%>
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
				欢迎语信息
				</h4>
			 </div>
			 <div class="modal-body">
				<form id="editForm"  action="<%=request.getContextPath() %>/weixinSubscribe/save.do" method="post" >
				   <input id="id" name="id" type="hidden">
				   <input id="accountid" name="accountid" type="hidden"  >
				   <input id="templateName" name="templateName"  type="hidden" />
						  	 <p class="clearfix mtb8">
						  	 		<label class="control-label">消息类型:</label>
									<select name="msgType" id="msgType" class="form-control" >
							          	<option value="text"  <c:if test="${subscribe.msgType=='text'}">selected="selected"</c:if>>文本消息</option>
							          	<option value="news"  <c:if test="${subscribe.msgType=='news'}">selected="selected"</c:if>>图文消息</option>
				        			 </select>
								</p>
						     <p class="clearfix mtb8">
								<label class="control-label">消息模板:</label>
								<span id="textId">
								<select name="templateid" id="textContent" class="form-control">
					          		<c:forEach items="${textList}" var="textVal">
					          			<option value="${textVal.id}" <c:if test="${templateid==textVal.id}">selected="selected"</c:if>>${textVal.templatename}</option>
					          		</c:forEach>
				        			 </select>
				        			 </span>
				       			 <span style="display:none" id="newsId" >
					       			  <select name="templateid" id="newsContent" class="form-control">
						          		<c:forEach items="${newsList}" var="newsVal">
						          			<option value="${newsVal.id}" <c:if test="${templateid==newsVal.id}">selected="selected"</c:if>>${newsVal.templatename}</option>
						          		</c:forEach>
					        		 </select>
				        	 	 </span>
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

