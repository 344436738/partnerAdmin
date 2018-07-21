<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	 <script type="text/javascript" src="<%=path %>/js/ux/sys/sysUserRole.js"></script> 
	 <script src="${msUrl}/js/bootstraptable/bootstrap-multiselect.js"></script>
	
	<script type="text/javascript">
	var layerPop;
	function authorRole(){

		//loading
		
		
		var arr = []; //主键名称
			$.each($('#roleIds').val(),function(i,record){
				arr.push('roleIds='+record);
			});
			arr.push('id='+$('#editId').val());
			var data = arr.join("&");
		
		$.post("${msUrl}/sysUser/addUserRole.do",data,function(result){
			//close loading
			if(result.success){
				//关闭layer
				layer.close(layerPop);
				//datalist 刷新
				layer.msg(result.msg, {tips:2});
				$('#table').bootstrapTable('refresh');
			}else{
				layer.msg(result.msg, {tips:2});
			}
		},"json");
		
	}

	function Authorize(){
		var array=$('#table').bootstrapTable('getSelections');
		if(array.length<1){
			layer.msg('请选择一条记录', {tips:2});
			//todo
			return;
		}	

		if(array.length>1){
			//todo
			layer.msg('请选择一条记录', {tips:2});
			return;
		}

	
		$("#emailEdit").val('');
		$("#editId").val('');
		 $('#roleIds').multiselect('deselectAll', false);
		//loading
		$.post("${msUrl}/sysUser/getUser.do",{id:array[0].id},function(result){
			//close loading
			if(result.success){
				$("#emailEdit").val(array[0].email);
				$("#editId").val(array[0].id);
				$('#roleIds').multiselect('select', result.data.roleIds);
			}else{
				layer.msg('网络错误', {tips:2});
			}
		},"json");
		
		layerPop=layer.open({
		    type: 1,
		    area: ['450px', '210px'], //宽高
		    content: $("#authorizeid"),
		    success: function(layero, index){
			  
			 $("#authorizeid").removeClass("hidden");
	    }
		});
		
	}
		
	
	function search(){
		$('#table').bootstrapTable('refresh');
	}

	 $(document).ready(function() {
         $('#roleIds').multiselect();
     });
     
	
	</script>
	
 
                                

<form action="" class="list_con_form clearfix" id="searchForm">
  <span class="manage_unit clearfix">
    <span class="manage_span_name">Email：</span>
    <input type="text" name="email" id="email" class="manage_input wd160">
  </span>
  <span class="manage_unit clearfix">
    <span class="manage_span_name">名称：</span>
    <input type="text" name="nickName" id="nickName" class="manage_input wd160">
  </span>
  <span class="manage_unit clearfix">
    <input type="button" value="查询" onclick="search()" class="h32 inline_blo flole prl20 orage_bg" >
  </span>
</form>
<div class="dashed-line"></div>
<div class="tacz" id="toolbar">
  <span class="inline_inblo mr15 pl15 bg_btn btnimg20" onclick="Authorize()" fun="authRole"><span class="authorize_btn inline_inblo pl15">授权</span></span>
 
</div>

<div id="authorizeid" class="hidden">
 
			     <p class="manage_unit clearfix mtb15">
				    <span class="manage_span_name inline_inblo pl15 wd120 txtri">Email:</span>
				    
				    <input  id="editId" name="id" type="hidden" class="manage_input wd160">
			     	<input  id="emailEdit" name="email" type="text" class="manage_input wd160">
				  </p>
			      <p class="manage_unit clearfix mtb15">
				    <span class="manage_span_name inline_inblo pl15 wd120 txtri">角色:</span>
						<select id="roleIds" name="roleIds" multiple="multiple">
							<c:forEach items="${roleList}" var="type">
								<option value="${type.id }">
									${type.roleName}
								</option>
							</c:forEach>
		
						</select>

					</p>
	           <p class="pb30">
			      <span class="manage_span_name inline_inblo pl15 wd120 txtri"></span>
			      <input type="button" value="确定" onclick="authorRole()" id="test1" class="orage_bg prl20 mr15 h32">
			      <input type="reset" value="取消" class="bglv prl20 mr15 h32">
			    </p>
	  </div>
	
 <table id="table"  class="table_data"></table>
 
