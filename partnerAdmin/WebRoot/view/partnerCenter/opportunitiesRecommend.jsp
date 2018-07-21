<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/headinclude.jsp" %>
	
<script type="text/javascript" src="${msUrl}/js/partnerCenter/opportunitiesRecommend.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
			//初始化校验
			$.validator.setDefaults( {
				ignore : []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
			$("#editForm").validate();
																		
		});
</script>


<!-- form表单 ，用于录入信息存入数据库-->
<div id="basic">
<form action="${msUrl}/partnerCenter/saveOpportunities.do" method="post"
	class="form-horizontal form-bordered" id="editForm" enctype="multipart/form-data">
					
	<input id="id" name="id" type="hidden" class="gldint texwd160">
										
		<p class="manage_unit clearfix mtb15">
		    <label class="control-label">客户单位名称:</label>
		    <input id="customercompname" name="customercompname" type="text"
			   data-rule-required="true" >
	    </p>
																								
		<p class="manage_unit clearfix mtb15">
			<label class="control-label">客户公司规模:</label>
			<select id="customercompsizeid" name= "customercompsizeid">
			<c:forEach items="${Scales}" var="scale" varStatus="scales">
			 <option value="${scale.id }">${scale.compsize } &nbsp; </option>
			</c:forEach>
			 </select>
		 	<!--  
				<option value="5000+">5000+</option>
				<option value="1000-5000">1000-5000</option>
				<option value="100-1000">100-1000</option>
				<option value="50-100">50-100</option>
				<option value="<50"><50</option> 
			人
			-->

		</p>
							
		<p class="manage_unit clearfix mtb15">
			<label class="control-label">客户姓名:</label>
			<input id="customername" name="customername" type="text"
				data-rule-required="true" >
		</p>
		
		<p class="manage_unit clearfix mtb15">
			<label class="control-label">客户职位:</label>
			<input id="customerposition" name="customerposition" type="text"
				data-rule-required="true" >
		</p>
		
		<p class="manage_unit clearfix mtb15">
			<label class="control-label">客户电话:</label>
			<input id="customertel" name="customertel" type="text"
				data-rule-required="true" >
		</p>
			
		<p class="manage_unit clearfix mtb15">
			<label class="control-label">客户邮箱:</label>
			<input id="customeremail" name="customeremail" type="text"
				data-rule-required="true" >
		</p>
		
		<p class="manage_unit clearfix mtb15">
			<label class="control-label">所在城市:</label>
			<input id="customercity" name="customercity" type="text"
				data-rule-required="true" >
		</p>
		
		<p>
		<label class="control-label">行业:</label>
		 <select  id="industry">
		 <c:forEach items="${Industry}" var="stat" varStatus="sta">
			 <option value="${stat.parentid }">	${stat.title } &nbsp; </option>
		 </c:forEach>
		 </select>
		</p>
		
		<p class="manage_unit clearfix mtb15">
			<label class="control-label">需求类型:</label>
			<input id="requirementstype" name="requirementstype" type="text"
				data-rule-required="true">
		</p>
		
		<p class="manage_unit clearfix mtb15">
			<label class="control-label">需求描述:</label>
			<input id="requirementsdescribe" name="requirementsdescribe" type="text"
				data-rule-required="true" >
		</p>
		
		<p class="manage_unit clearfix mtb15">
			<label class="control-label">推荐客户情况说明:</label>
			<input id="recommenddescribe" name="recommenddescribe" type="text"
				data-rule-required="true" >
		</p>
		
		<p class="manage_unit clearfix mtb15">
			<label class="control-label">合伙人附件上传:</label>
			<input id="uploadfile" name="uploadfile" type="file">
		</p>
		<input id="issuserid" name="issuserid" type="hidden" value="${UserId}"
				data-rule-required="true"  >
		
		<input id="Compid" name="userid" type="hidden"
				data-rule-required="true" >																							
</form>

    <button type="button" onclick="recommend()" class="btn btn-primary">
                          推荐
     </button>
        <button type="button" onclick="next()" class="btn btn-primary">
                           下一步
     </button>
     
     <label class="control-label">选择推荐公司<br/> </label>
	<input id="searchComp" name="searchComp" type="text"
	     data-rule-required="true" >
     <button type="button" onclick="search()" class="btn btn-primary">
                             查询
     </button>
     
     </div>搜索结果：
<div id="seachresult">
</div>
<HR />
推荐企业：
<div id="recommend">
</div>


<!-- 摸态框，弹出评价窗口 -->
<div class="modal fade" id="editDiv" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
           	评价
            </h4>
         </div>
         <div class="modal-body" >
         	<table id="evaluatetable" class="table_data" ></table>
		 </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" 
              data-dismiss="modal" >确定
            </button>
         </div>
      </div>
     </div><!-- /.modal -->
</div>
