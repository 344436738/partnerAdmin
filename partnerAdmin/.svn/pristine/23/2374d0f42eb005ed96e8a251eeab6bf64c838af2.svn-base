<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstraptable/layer.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.exedit-3.5.js"/></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstraptable/layer.js"></script>	
	
<script type="text/javascript">

	var setting = {
			async: {
				enable: true,
				dataType: "json",
				url:"<c:url value="sysOrg/findList.do"/>"//加载树
			},
			edit: {
				enable: true, //设置ztree是否处于编辑状态
				showRenameBtn: false,
				showRemoveBtn: false
			},
			data: {
				simpleData: {
					enable: true,//设置初始化是否为简单数据模式  如果设置为 true，请务必设置 setting.data.simpleData 
					pIdKey: "pid"//	内的其他参数: idKey / pIdKey rootPId，并且让数据满足父子关系					
				}
			},
			callback: {
			    onDrop: zTreeOnDrop,
				onClick: zTreeOnClick,
				onRightClick: OnRightClick,
				onAsyncSuccess:expandAllTree,
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

		//右键判断菜单是否在节点上点击
		function OnRightClick(event, treeId, treeNode) {
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				//zTree.cancelSelectedNode();
				//var scrollTop=document.documentElement.scrollTop||document.body.scrollTop;
				//var clienWi=document.documentElement.clientWidth||document.body.offsetWidth;
				//var clienHx=clienWi/2-672;
				//showRMenu("root", event.clientX-265-clienHx, event.clientY-110+scrollTop);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				var scrollTop=document.documentElement.scrollTop||document.body.scrollTop;
				var clienWi=document.documentElement.clientWidth||document.body.offsetWidth;
				var clienHx=clienWi/2-672;
				showRMenu("node", event.clientX-220-clienHx, event.clientY-110+scrollTop);
			}
		}

		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_del").hide();
				$("#m_add").hide();
			} else {
				$("#m_del").show();
				$("#m_add").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}

		//增加组织机构节点
		function addTreeNode() {
			hideRMenu();
			var nodes = zTree.getSelectedNodes();
			var id = nodes[0].id;
			$("#ifr1").attr("src","sysOrg/getOrgById.shtml?id="+id+"&type=add");
		}

		//删除组织机构节点
		function removeTreeNode(){
			hideRMenu();
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				if (nodes[0].children && nodes[0].children.length > 0){
					layer.msg('父节点您不能删除！');
				}else{
					layer.confirm('您确定要删除吗？', {icon: 3}, function(index){
						layer.close(index);
						$.post(
							"sysOrg/deleteOrgNodes.do",
							{"id":nodes[0].id},
							function(data){
								if(data!=null){
									layer.msg('删除成功！');
									//删除成功后刷新树
									zTree = $.fn.zTree.getZTreeObj("treeDemo");
									zTree.reAsyncChildNodes(null, "refresh");
								}
							}).error(function(xhr, status, text){
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
					});
			     } 
		    }
		 }
		

		//获取组织机构节点信息	
		function zTreeOnClick(event, treeId, treeNode) {
    		//alert(treeNode.tId + ", " + treeNode.name);
   			var id=treeNode.id;
			$("#ifr1").attr("src","sysOrg/getOrgById.shtml?id="+id);
		}
		
		//移动组织机构节点
		function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
		    //alert("此节点的id为："+treeNodes[0].id+"父id为："+treeNodes[0].pId+"移动到的节点id"+targetNode.id);
			var id = treeNodes[0].id;
			var pid = targetNode.id;
			$.post(
				"sysOrg/zTreeOnDrop.do",
				{"id":id,"pid":pid},
				function(data){
					if(data!=null){
						layer.msg('移动成功！');
					}
				}).error(function(xhr, status, text){
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

		//展开全部节点
		function expandAllTree(){
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandAll(true);
		} 

		//刷新树
		function refreshTree(){
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.reAsyncChildNodes(null, "refresh");
		}

		var zTree, rMenu;
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
			rMenu = $("#rMenu"); 
		});
		
</script>
	
	
<style type="text/css">
	.ztree{border:1px solid #AEB9BF;padding-bottom:100px;}
	.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	div#rMenu {position:absolute; visibility:hidden; top:0; /*background-color: #555;text-align: left;padding: 2px;*/}
	div#rMenu ul li{
		margin: 1px 0;
		padding: 0 5px;
		cursor: pointer;
		list-style: none outside none;
		background-color: #DFDFDF;
	}
	#treeDemo{min-height:380px;}
	div .ztree *{font-size:14px;}
</style>
	

<div class="panel">
	<div class="panel-body">
	<div class="col-sm-3" >
		<ul id="treeDemo" class="ztree"></ul>	
	</div>
	<div class="col-sm-7"   id="ifdiv" >
		<iframe src="sysOrg/getOrgById.shtml" id="ifr1" width="730px;" height="550px;" frameborder="no" border="0" scrolling="no"></iframe>
	</div>	
	</div>						
</div>

<div id="rMenu">	
	<div>
	<button class="btn btn-primary btn-sm" onclick="addTreeNode();">增加节点</button>
	<br />
	<button class="btn btn-maroon btn-sm" onclick="removeTreeNode();">删除节点</button>
	</div>
	<!-- <ul>
		<li id="m_add" onclick="addTreeNode();">增加节点</li>
		<li id="m_del" onclick="removeTreeNode();">删除节点</li>
	</ul> -->
</div>