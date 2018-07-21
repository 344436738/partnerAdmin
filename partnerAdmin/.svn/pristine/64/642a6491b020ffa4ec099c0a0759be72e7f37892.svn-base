<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
  <head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.exedit-3.5.js"/></script>
	<SCRIPT type="text/javascript">
		<!--
		var settingDemo = {
			view: {
				dblClickExpand: false
			},
			edit: {
				enable: true //设置ztree是否处于编辑状态
			},
			check: {
				enable: true
			},
			callback: {
				onRightClick: OnRightClick,
				zTreeOnDrop: zTreeOnDrop
			}
		};

		function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
		    //alert("此节点的id为："+treeNodes[0].id+"父id为："+treeNodes[0].pId+"移动到的节点id"+targetNode.id);
		}

		var zNodes =[
			{id:1, name:"无右键菜单 1", open:true, noR:true,
				children:[
					   {id:11, name:"节点 1-1", noR:true},
					   {id:12, name:"节点 1-2", noR:true}

				]},
			{id:2, name:"右键操作 2", open:true,
				children:[
					   {id:21, name:"节点 2-1"},
					   {id:22, name:"节点 2-2"},
					   {id:23, name:"节点 2-3"},
					   {id:24, name:"节点 2-4"}
				]},
			{id:3, name:"右键操作 3", open:true,
				children:[
					   {id:31, name:"节点 3-1"},
					   {id:32, name:"节点 3-2"},
					   {id:33, name:"节点 3-3"},
					   {id:34, name:"节点 3-4"}
				]}
  	 	];

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
				$("#m_check").hide();
				$("#m_unCheck").hide();
			} else {
				$("#m_del").show();
				$("#m_check").show();
				$("#m_unCheck").show();
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
		var addCount = 1;
		function addTreeNode() {
			hideRMenu();
			var newNode = { name:"增加" + (addCount++)};
			if (zTree.getSelectedNodes()[0]) {
				newNode.checked = zTree.getSelectedNodes()[0].checked;
				zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
			} else {
				zTree.addNodes(null, newNode);
			}
		}
		function removeTreeNode() {
			hideRMenu();
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				if (nodes[0].children && nodes[0].children.length > 0) {
					var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
					if (confirm(msg)==true){
						zTree.removeNode(nodes[0]);
					}
				} else {
					zTree.removeNode(nodes[0]);
				}
			}
		}
		function checkTreeNode(checked) {
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				zTree.checkNode(nodes[0], checked, true);
			}
			hideRMenu();
		}
		function resetTree() {
			hideRMenu();
			$.fn.zTree.init($("#treeDemo"), settingDemo, zNodes);
		}

		var zTree, rMenu;
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), settingDemo, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
		});
		//-->
	</SCRIPT>
	<style type="text/css">
div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
div#rMenu ul li{
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #DFDFDF;
}
	</style>
  </head>
  
  <body>
    

<div class="content_wrap">
	<div class="zTreeDemoBackground left" style="margin-top: 20px;">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div class="right">
		<ul class="info">
			<li class="title">
				<ul class="list">

				<li class="highlight_red"></li>
				</ul>
			</li>
		</ul>
	</div>
</div>
<div id="rMenu">
	<ul>
		<li id="m_add" onclick="addTreeNode();">增加节点</li>
		<li id="m_del" onclick="removeTreeNode();">删除节点</li>
		<li id="m_check" onclick="checkTreeNode(true);">Check节点</li>
		<li id="m_unCheck" onclick="checkTreeNode(false);">unCheck节点</li>
		<li id="m_reset" onclick="resetTree();">恢复zTree</li>
	</ul>
</div>
<h5>注：右键菜单的实现,可拖拽节点</h5>
  </body>
</html>
