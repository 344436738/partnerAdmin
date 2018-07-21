<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/valid.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstraptable/layer.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/hebase.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/liststyle.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/coyote.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.formFill.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validate/valadate-message.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstraptable/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/jquery.ztree.exedit-3.5.js"/></script>
   <script src="<%=request.getContextPath()%>/js/bjs/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bcss/bootstrap.min.css"/>
     
<script type="text/javascript">

  	$(function(){
  		var id = $("#ids").val();
  		if(id.length>0){
  			$(".row").attr("readonly",false);
  			$(".row").css("border-style","inset");
  			$(".row").css("display","inline");		 
  			$(".batn").css("display","inline");
  			$("#nickName").attr("disabled",false);  			 			
  		}
	 })


	//组织机构下的用户
  	function selectUser(){
	  	
  		var querySetting = {
  				async: {
  					enable: true,
  					url:"<c:url value="queryByUser.do"/>",
  					otherParam:["name",function(){return window.document.getElementById("userNickName").value;}]
  				},		
  				data: {
  					simpleData: {
  						enable: true,//设置初始化是否为简单数据模式  如果设置为 true，请务必设置 setting.data.simpleData 内的其他参数: idKey / pIdKey rootPId，并且让数据满足父子关系
  						pIdKey: "pid"						
  					}
  				},
  				callback: {
  					onAsyncSuccess:expandAllTree,
  					onClick: zTreeOnClick,
  					onAsyncError: zTreeOnAsyncError
  				} 				
  			}

  	  		function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
  				if (XMLHttpRequest.status == "504") {
  					layer.msg("登录超时，请重新登录", {
  						tips : 2
  					}, function() {
  						location.reload();
  					});
  				}else{
  					layer.msg("系统错误，请稍后再试", {
  						tips : 2
  					});
  				} 
  			}

  	  		function zTreeOnClick(event, treeId, treeNode) {
  	    		var pid=treeNode.pid;
  	    		var name=treeNode.name;
  	    		var id = treeNode.id;
  		    		$("#ordId").val(pid);
  		    		$("#orgUserNickeName").val(name);
  		    		$("#sysOrgId").val(id);
  			}

  			//展开全部节点
  	  		function expandAllTree(){
  				zTree = $.fn.zTree.getZTreeObj("selectTree");
  				zTree.expandAll(true);
  			}
  			
  			$(document).ready(function(){
  				$.fn.zTree.init($("#selectTree"), querySetting);
  			});
	
		
		$("#editDiv").removeClass("hidden");
		layerPop = layer.open({
			type : 1,
			area : [ '430px', 'auto' ], // 宽高
			offset: ['28px' ,'10px'],
			moveOut: true, 
			shade: [0],
			offset: ['20px' ,'50px'],
			content : $("#editDiv"),
			success : function(layero, index) {				
			}			
		});
  	}

  	//保存
  	$(function(){
  	$('#sub').click(function(){   		
  	  	var id = $("#ids").val(); 
  	  	var url = "";
        if(id.length>0) {  
        	url="<%=request.getContextPath() %>/sysOrg/updateOrgNodes.shtml";                                           
        }else{
			url="<%=request.getContextPath() %>/sysOrg/addOrgNodes.shtml";        
        }
       	 subForm(url);  
        });    	
  	})

    //保存
  	function subForm(url){
  		if ($("#formId").valid()) {
  		$.ajax({
            url:url,
            data:$("#formId").serialize(),
            type:"post",
            success:function(data){
  				layer.msg('保存成功！');
  				parent.refreshTree();
            }
       },"json").error(function(xhr, status, text){
		if (xhr.status == "504") {
			layer.msg("登录超时，请重新登录", {
				tips : 2
			}, function() {
				location.reload();
			});
		}else{
			layer.msg("系统错误，请稍后再试", {
				tips : 2
			});
		}
	}); 
  		} 		
  	 }

  	$(function(){
        <% if(request.getAttribute("type")!=null&&("add".equals(request.getAttribute("type")))){
        	%>
        		$('#orgName').val($('#name').val());
    			$('#describe').val('');
    			$('#createdBy').val('');
    			$('#createdTime').val('');
    			$('#createdBy').val('');
    			$('#nickName').val('');
    			$('#name').val('');
    			$('#pids').val($('#ids').val());
    			$('#ids').val('');    			
        	<%
        }
      %>    
    });

	//选择组织机构负责人
  	function selectPrincipal(){
  		var id = $("#sysOrgId").val();
  	  	if(id!=0){
  	  		layer.msg("您选择的不是负责人！");
  	  	}else{
	  		var name=$("#orgUserNickeName").val();
	  		$("#nickName").val(name);
	  		$("#formId").validate().element("#nickName");	  		
	  		layer.close(layerPop);
  	  	}
  	}

	//取消
	function cancelBut() {
		location.reload();
	}
	
	//关闭窗口
  	function closePop() {
  		layer.close(layerPop);
  	}

  	//模糊查询组织机构下的人员
  	function queryNickName(){      
       zTree = $.fn.zTree.getZTreeObj("selectTree");
       zTree.reAsyncChildNodes(null, "refresh");  		
  	}

</script>

<style>
	.ztree{padding:0px;}
	/*.borlef{border-left:2px solid #eaeaea;}*/
	.manage_span_name{display:inline-block;width:90px;text-align:right;}
	.outh3{color:#333;margin-bottom:10px;}
	.table_data{margin-bottom:25px;}
	.qinbg{background:#edf6fd;font-weight:bold;color:#333;text-align:right;width:18%;}
	td{/*border-right:1px solid #A0BBCC;*/width:32%;border:1px solid #A0BBCC;}
	td div{position:relative;}
	td label{position:absolute;left:25px;top:5px;}
	.table{border:1px solid #A0BBCC;}
	.manage_span_name{float:none;}
	.outabtn{float:none;display:inline;}
	input.bglv{background:#f2f2f2;}
	td textarea{float:left;}
	textarea.row{margin-top:6px;;height:100px;width:240px;}
	body{background-color:transparent;}
</style>

	<form action="" method="post" id="formId" class="borlef" style="margin-top:5px;">	
	 		<input type="hidden" name="id" id="ids" value="${org.id}"/>
	 		<input type="hidden" name="pid" id="pids" value="${org.pid}"/>
	 		<input type="hidden" name="principal" value="${org.principal}"/>
	 		<h3 class="outh3 h32">组织机构</h3>
	 		
	 		<table class="table">
	 		<tbody>
	 			<tr>
	 				<td class="qinbg">
	 					<div class="clearfix"><span class="manage_span_name">上级组织 ：</span></div></td>
	 				<td>
		 				<div class="clearfix">
		 					<input type="text" id="orgName" name="orgName" value="${org.orgName}" readonly="readonly" class="manage_input wd160" style="border-style:none"/>
		 				</div>
	 				</td>
	 				<td class="qinbg"><div  class="clearfix"><span class="manage_span_name">组织名称：</span></div></td>
			 		<td><div class="clearfix"><input  type="text" id="name" name="name" value="${org.name}" data-rule-required="true" maxlength="50"  class="manage_input wd160 row" style="border-style:none;margin-left: 10px;" readonly="readonly"/></div></td>
	 			</tr>
	 			<tr>
	 				<td class="qinbg"><div class="clearfix"><span class="manage_span_name">创建时间：</span></div></td>
	 				<td><div class="clearfix"><input type="text" id="createdTime" name="createdTime" value="${org.createdTime }" readonly="readonly" class="manage_input wd160" style="border-style:none"/></div></td>
	 				<td class="qinbg"><div class="clearfix"><span class="manage_span_name">创建人：</span></div></td>
	 				<td><div class="clearfix"><input type="text" id="createdBy" value="${org.createName }" readonly="readonly" class="manage_input wd160 mr15" style="border-style:none"/></div></td>
	 			</tr>
	 			<tr>
	 				<td class="qinbg"><div class="clearfix"><span class="manage_span_name">负责人：</span></div></td>
	 				<td colspan="3"><div class="clearfix">
	 					<input type="text" id="nickName"  value="${org.principalName }"class="manage_input wd160 mr15 row" data-rule-required="true" disabled="disabled" style="border-style:none;margin-left:10px " onclick="selectUser()"/>
			 			<input type="hidden" name="ordId" id="ordId"/>
			 			<input type="hidden" name="orgUserNickeName" id="orgUserNickeName"/>
			 			<input type="hidden" name="id" id="sysOrgId"/>
			 			<input type="button" value="选择" onclick="selectUser()" class="btn btn-primary batn" id="selectBtn" style="display:none;"/>
			 		</div></td>
	 			</tr>
	 			<tr>
	 				<td class="qinbg"><div class="clearfix"><span class="manage_span_name">组织描述 ：</span></div></td>
	 				<td colspan="3"><div class="clearfix" style="height:112px;">
	 					<textarea id="describe" name="describe" readonly="readonly" data-rule-required="true"  style="border-style: none; display:none; margin-left: 10px;" class="row">${org.describe}</textarea>
	 				</div></td>
	 			</tr>
	 		</tbody>
	 	</table>	
	 				
		<p style="text-align:center;">
 	    	<input type="button" id="sub" value="保存" class="btn btn-primary batn h32" style="display:none;"/>
 	    	<input type="reset"  value="重置" onclick="cancelBut()"  class="btn btn-default batn h32" style="display:none;"/>
		</p>
			
	</form>
 
<div id="editDiv" class="hidden">
	<form action=""  id="searchForm" method="post">	
		<p class="gldx clearfix mtb15">
			<span class="gldx clearfix"> <span class="manage_span_name">昵称：</span> 
            <input type="text" id="userNickName" name="nickName"  class="manage_input wd160 mr15" style="float:none;display:inline;">
            <input type="button" value="查询" class="bglv prl20 mr15 h28" onclick="queryNickName()"/>
   		 	</span>				
		</p>	
			
		<p class="gldx clearfix mtb15">
			<span class="manage_span_name inline_inblo txtri" style="float:left;">请选择：</span>
			 <span class="zTreeDemoBackground left flole"
				style="border: 1px solid #A4BED4; width:220px;height:200px;overflow: auto; display:inline-block;">
				<span id="selectTree" class="ztree"> </span>
			</span>
		</p>

		<p class="" style="margin-bottom: 20px;">
			<span class="manage_span_name inline_inblo pl15 wd120 txtri"></span> 
			<input type="button" value="确定" onclick="selectPrincipal()" class="orage_bg prl20 mr15 h32"/> 
			<input type="reset" value="取消" onclick="closePop()" class="bglv prl20 mr15 h32"/>			
		</p>
		
	</form>
</div>
