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
   
   
<script type="text/javascript">
	var pid,id,name,userid;
	$(document).ready(function(){
		$.fn.zTree.init($("#selectTree"), querySetting);
	});
	var querySetting = {
			async: {
				enable: true,
				url: '<%=request.getContextPath()%>/sysOrg/queryByUser.do',
				otherParam:["name",function(){return document.getElementById("userNickName").value;},
							"excludeManager","${excludeManager}"
							]
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
 		pid=treeNode.pid;
 		name=treeNode.name;
 		id = treeNode.id;
 		userid=treeNode.userId;
	}

	//展开全部节点
	function expandAllTree(){
		zTree = $.fn.zTree.getZTreeObj("selectTree");
		zTree.expandAll(true);
	}
	

	//选择组织机构负责人
  	function selectPrincipal(){
  	  	if(typeof(userid) =='undefined'){
  	  		layer.msg("请您选择的人员！");
  	  	}else{
	  		window.parent.document.getElementById('${nameobj}').value=name;
	  		window.parent.document.getElementById('${idobj}').value=userid;
	  		<c:if test="${callback!=null}">
	  		window.parent.${callback};
	  		</c:if>
	  		window.parent.closeChooseUserPop();
  	  	}
  	}

	//取消
	function cancelBut() {
		location.reload();
	}
	
	//关闭窗口
  	function closePop() {
  		window.parent.closeChooseUserPop();
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
	.table_data tr{border:1px solid #A0BBCC;}
	.manage_span_name{float:none;}
	.outabtn{float:none;display:inline;}
	input.bglv{background:#f2f2f2;}
	td textarea{float:left;}
	textarea.row{margin-top:6px;;height:100px;width:240px;}
</style>
 
<div id="editDiv">
	<form action=""  id="searchForm" method="post">	
	
		<input type="hidden" name="ordId" id="ordId"/>
		<input type="hidden" name="orgUserNickeName" id="orgUserNickeName"/>
		<input type="hidden" name="sysOrgId" id="sysOrgId"/>
			 			
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
