<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <link rel="stylesheet" type="text/css" href="${msUrl}/css/easyui/themes/default/tree.css">
 
 
 <script type="text/javascript" src="${msUrl}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/sys/sysRole.js"></script>
<script type="text/javascript" src="${msUrl}/js/coyote.js"></script>

<script type="text/javascript">

	//设置树的值
	function setTreeValue(id) {
		var node = $('#menu-tree').tree("find", id);
		if (node && node.target) {
			//判断是否选择或者半选状态 
			if ($(node.target).find(".tree-checkbox0")[0]) {
				$('#menu-tree').tree('check', node.target);
			}
		}
	}
	
	//构建树
	function buildTreeData(nodes) {
		$.each(nodes, function(i, note) {
			var id = note.attributes.id;
			var type = note.attributes.type;
			var $id = $("<input type='hidden' name='menuIds' class='c_menus'>");
			if (type == 0) {
				$id.attr('name', 'menuIds');
			} else if (type == 1) {
				$id.attr('name', 'btnIds');
			}
			$id.val(id);
			$('#editForm').append($id);
		});
	}

	function clearTreeData() {
		$(".tree-checkbox1", $('#menu-tree')).removeClass("tree-checkbox1")
				.addClass("tree-checkbox0")
		$(".tree-checkbox2", $('#menu-tree')).removeClass("tree-checkbox2")
				.addClass("tree-checkbox0");
		$('.c_menus').remove();
	}

	$(document).ready(function() {
		//初始化校验
			$.validator.setDefaults( {
				ignore : []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
			$("#editForm").validate();
			//初始化树
			var loading = layer.load(2);
			$('#menu-tree').tree( {
				url : 'sysMenu/getMenuTree.do',//初始化树
				checkbox : true
			});
			layer.close(loading);

		});
</script>

<!--查询条件  -->
<div class="panel">
   <div class="panel-heading">
			<form action="" class="form-inline" id="searchForm">
				   <label class="control-label">名称：</label>
						               		<input type="text" name="roleName" id="roleName" class="form-control"> 
									 <button type="button"  onclick="search()" class="btn btn-primary">
									 <i class="fa  fa-search"></i>查询</button>
			</form>
		<div class="dashed-line"></div>
		<!--toolbar-->
		<div  id="toolbar">
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
<!-- 编辑窗口   ------权限树----->
<!-- 模态框（Modal） -->
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
           	角色信息
            </h4>
         </div>
         <div class="modal-body">
				<form action="${msUrl}/sysRole/save.do" method="post"
					class="list_con_form clearfix" id="editForm">
					<input id="id" name="id" type="hidden" class="gldint texwd160">
					
					<p class="clearfix mtb8">
						<label class="control-label">角色名：</label>
						<input id="roleName" name="roleName" type="text" class="form-control"
						data-rule-required="true" data-rule-maxlength="50" /> 
					</p>
					
					<p class="manage_unit clearfix mtb5">
						<label class="control-label">状态：</label>
						<span class="rdio rdio-success">
								<input name="state" type="radio" id="state0"  value="0" checked />
								<label for="state0">可用</label>
						</span>
						<span class="rdio rdio-success">
								<input name="state" type="radio"  value="1" id="state1" />
								<label for="state1">禁用</label>
						 </span>
					</p>
					
					
					<p class="clearfix mtb8">
						<label class="control-label">角色描述:</label>
						<textarea id="descr" name="descr" class="form-control"
							 data-rule-maxlength="200"  rows="5" ></textarea>
					</p>
					
					<p class="manage_unit clearfix mtb15">
						<label class="control-label">权限菜单:</label>
						<div> 
						<span class="form-control"
							style=" height: 200px; overflow: auto; display: inline-block;">
							<span id="menu-tree"> </span> </span>
						</div>
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



