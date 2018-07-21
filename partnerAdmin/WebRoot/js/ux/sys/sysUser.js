$(function() {
	var layerPop;// 弹窗

	// 初始化Table
	$('#table').bootstrapTable({
		method : "post",
		contentType : "application/x-www-form-urlencoded",// 远程数据请求的“contentType”类型,默认请写此
		dataType : "json",// 远程数据请求返回的数据类型
		url : 'sysUser/dataList.do',
		idField : "id",
		sidePagination : "server",// 定义表格分页的位置，只能是“client”（客户端）和“server”（服务器端）。
		pagination : true,// 设置True在表格底部显示分页工具栏。
		pageNumber : 1,
		pageSize : 10,
		pageList : [ 10, 25, 50, 100 ],
		queryParamsType : "RESTFul",// 设置为“limit”可以发送标准的RESTFul类型的参数请求。
		// 默认请使用这个，如果项目上分页为limit方式时，写limit
		queryParams : queryParams,// 远程数据请求时，可以通过queryParams来格式化所需要的数据信息，参数（对象）包含了：
		// pageSize, pageNumber, searchText, sortName, sortOrder。返回 false
		// 可以禁用请求。
		smartDisplay : true,// 设置为True智能显示分页或者Card View。
		// cardView:true,//启用cardView模式
		showToggle : true,// 设置为True可显示切换普通表格和名片（card）布局。
		// search: true,
		// searchAlign: 'right',
		selectItemName : 'btSelectItem',// 单选框或者复选框的name，用于多个表格使用radio的情况。
		showHeader : true,// 显示表头
		showColumns : true,// 设置为True可显示表格显示/隐藏列表。
		// minimumCountColumns:1,//表格显示/隐藏列表时可设置最小隐藏的列数。
		// undefinedText:"---",//数据为空时显示
		striped : true,// 使表格带有条纹。
		showRefresh : true,// 设置为True可显示刷新按钮。
		toolbar : "#toolbar",// 定义toolbar是哪个
		toolbarAlign : "left",// 对齐方式
		clickToSelect : true,// 设置为True时点击行即可选中单选/复选框。
		// singleSelect:true,//只能选择一条记录
		// checkboxHeader:true,//设置为False时隐藏表头中的全选复选框。
		// maintainSelected:true,//设置为True当换页或者搜索时保持选中的行。
		// sortable:true,//设置为False时禁用所有列的排序。
		showExport : true,// 设置为true，显示导出按钮。
		// exportTypes:['json', 'xml', 'csv', 'txt', 'sql',
		// 'excel'],//出口类型，支持类型：“JSON”，“XML”，“PNG”，“CSV”，“TXT”，“SQL”，“文档”，“Excel的”，“简报”，“PDF”。
		// height:"auto",
		onLoadSuccess : function(data) {// 加载成功后所做的事情
			if (data.success == false) {
				layer.msg(data.msg, {
					tips : 2
				});
			}
		},
		onLoadError : function(status) {
			if (status == "504") {
				layer.msg("登录超时，请重新登录", {
					tips : 2
				}, function() {
					location.reload();
				});
			}
		},
		columns : [ {
			checkbox : true
		}, {
			field : "id",
			title : 'ID',
			width : 50,
			align : 'center',
			sortable : true
		}, {
			field : "email",
			title : "邮箱",
			width : 120,
			align : 'center',
			sortable : true
		}, {
			field : "nickName",
			title : "昵称",
			align : 'center',
			width : 120,
			sortable : true
		}, {
			field : 'state',
			title : '状态',
			width : 80,
			align : 'center',
			sortable : true,
			styler : function(value, row, index) {
				if (value == 1) {
					return 'color:red;';
				}
			},
			formatter : function(value, row, index) {
				if (value == 0) {
					return "可用";
				}
				if (value == 1) {
					return "禁用";
				}
			}
		}, {
			field : "createTime",
			title : "创建时间",
			width : 150,
			align : 'center',
			sortable : true
		}, {
			field : "isTeamLeader",
			title : "是否项目经理",
			width : 80,
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				if (value == 0) {
					return "否";
				}
				if (value == 1) {
					return "是";
				}
			}
		}, {
			field : "loginTime",
			title : "最后登录时间",
			width : 150,
			align : 'center',
			sortable : true
		}, {
			field : 'orgName',
			title : '所属机构',
			width : 80,
			align : 'center',
			sortable : true
		}, {
			field : 'roleStr',
			title : '角色',
			width : 150,
			align : 'center',
			sortable : true
		} ]
	});
});

// 查询方法，供其他位置点击触发Table的查询效果
function search() {
	$('#table').bootstrapTable('refresh');
}

// table的查询参数
function queryParams(params) {
	return {
		rows : params.pageSize,// 每页多少条
		page : params.pageNumber,// 当前页
		sort : params.sortName,// 根据哪个字段排序
		order : params.sortOrder,// 升降序
		email : $("#email").val(),// 自定义查询属性
		nickName : $("#nicknName").val(),
		orgId : $("#queryOrgId").val()
	};
}

// 添加记录
function add() {
	var treeObj = $.fn.zTree.getZTreeObj("editTree");
	treeObj.checkAllNodes(true);
	var nodes = treeObj.getCheckedNodes(true);
	if (nodes.length > 0) {
		for (var i = 0; i < nodes.length; i++) {
			treeObj.checkNode(nodes[i], false, true, false);
		}
	}
	$("#editForm").clearForm(true);
	openBModal('#editDiv');
	var treeObj = $.fn.zTree.getZTreeObj("editTree");
	treeObj.expandAll(true);
	 
	//$("#editDiv").removeClass("hidden");
	/*layerPop = layer.open({
		type : 1,
		area : [ '450px', 'auto' ], // 宽高
		shade : [0.5 , '#000' , true],
		content : $("#editDiv"),
		success : function(layero, index) {
			var treeObj = $.fn.zTree.getZTreeObj("editTree");
			treeObj.expandAll(true);
		}

	});*/
}
// 删除记录
function del() {
	var array = $('#table').bootstrapTable('getSelections');
	if (array.length <= 0) {
		layer.msg('请选择项目',{tips:2});
		return;
	}

	layer.confirm('您确定要删除吗？', {
		btn : [ '删除', '取消' ] // 按钮
		//,shade : false
	// 不显示遮罩
	}, function() {
		$('#table').bootstrapTable('showLoading');

		var arr = [], idKey = 'id'; // 主键名称
		$.each(array, function(i, record) {
			arr.push(idKey + '=' + record[idKey]);
		});
		var data = arr.join("&");

		$.post("sysUser/delete.do", data, function(result) {
			$('#table').bootstrapTable('hideLoading');
			if (result.success) {
				$('#table').bootstrapTable('refresh');
				layer.msg('已删除',{tips:2});
			} else {
				layer.msg(result.msg,{tips:2});
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
	}, function() {
		// layer.msg('已取消', {icon: 1});
	});
}

// 修改--根据记录打开弹窗
function update() {
	$("input[type='radio']").attr("checked",false);
	$("#editForm").validate();
	$("#editForm").clearForm();
	var array = $('#table').bootstrapTable('getSelections');
	if (array.length < 1) {
		layer.msg('请选择一条记录', {tips:2});
		// todo
		return;
	}

	if (array.length > 1) {
		// todo
		layer.msg('请选择一条记录', {tips:2});
		return;
	};
	
	openBModal('#editDiv');
	loadData(array[0].id);
	var treeObj = $.fn.zTree.getZTreeObj("editTree");
	treeObj.expandAll(true);
	
	
	/*$("#editDiv").removeClass("hidden");
	layerPop = layer.open({
		type : 1,
		area : [ '420px', 'auto' ], // 宽高
		content : $("#editDiv"),
		success : function(layero, index) {
			loadData(array[0].id);
			var treeObj = $.fn.zTree.getZTreeObj("editTree");
			treeObj.expandAll(true);
		}
	});*/
}
 

// 修改--保存按钮
function updateSave() {
	if ($("#editForm").valid()) {
		var zTree = $.fn.zTree.getZTreeObj("editTree")
		var nodes = zTree.getCheckedNodes(true);
		$("#orgId").val(typeof(nodes[0])=="undefined"?"":nodes[0].id);
		var option = {
			dataType : 'json',
			success : function(data) {
				if (!data.success) {
					layer.msg(data.msg, {
						icon : -1,
						time : 1000
					// 2秒关闭（如果不配置，默认是3秒）
					});

				}else{
					// 关闭layer
					layer.msg(data.msg);
					closeBModal('#editDiv');
					$('#table').bootstrapTable('refresh');
				}
			},
			error: function(xhr, status, text){
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
			}
		};
		$("#editForm").ajaxSubmit(option);
	}

}



 
// 修改--根据id读取数据方法
function loadData(id) {
	var loading = layer.load(2);
	$.post("sysUser/getId.do", {
		id : id
	}, function(result) {
		layer.close(loading);
		if (result.success) {
			$("#editForm").fill(result.data);
			var ztreeRadio = $.fn.zTree.getZTreeObj("editTree");
			var node = ztreeRadio.getNodesByParam("id", result.data.orgId,
					null);
			ztreeRadio.checkNode(node[0], true, true, null);
		} else {
			layer.msg(result.msg, {tips:2});
		}
	},"json").error(function(xhr, status, text){
		if (xhr.status == "504") {
			layer.msg("登录超时，请重新登录", {
				tips : 2
			}, function() {
				location.reload();
			});
		}else{
			layer.msg("系统错误，请稍后再试", layer.close(loading),{
				tips : 2
			});
		}
	});
}
 

// 授权--打开弹窗
function authorRole() {
	var array = $('#table').bootstrapTable('getSelections');
	if (array.length < 1) {
		layer.msg('请选择一条记录',{tips:2});
		// todo
		return;
	}

	if (array.length > 1) {
		// todo
		layer.msg('请选择一条记录',{tips:2});
		return;
	}
	//$("#authorizeid").removeClass("hidden");
	 openBModal('#authorizeid');
	 
	/*layerPop = layer.open({
		type : 1,
		area : [ '450px', 'auto' ], // 宽高
		content : $("#authorizeid"),
		success : function(layero, index) {
		}
	});*/

	$("#emailEdit").val('');
	$("#editId").val('');
	$('#roleIds').multiselect('deselectAll', false);
	// loading
	var loading = layer.load(2);
	$.post("sysUser/getUser.do", {
		id : array[0].id
	}, function(result) {
		// close loading
		layer.close(loading);
		if (result.success) {
			$("#emailEdit").val(array[0].email);
			$("#editId").val(array[0].id);
			$('#roleIds').multiselect('select', result.data.roleIds);
		} else {
			layer.msg('网络错误',{tips:2});
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

// 授权--保存按钮
function authorRoleSave() {
	var arr = []; // 主键名称
	if($('#roleIds').val()!=null){
		$.each($('#roleIds').val(), function(i, record) {
			arr.push('roleIds=' + record);
		});
	}
	arr.push('id=' + $('#editId').val());
	var data = arr.join("&");
	var loading = layer.load(2);// loading
	$.post("sysUser/addUserRole.do", data, function(result) {
		layer.close(loading);
		// close loading
		if (result.success) {
			// 关闭layer
			closeBModal('#authorizeid');
			layer.msg(result.msg, {tips:2});
			// datalist 刷新
			$('#table').bootstrapTable('refresh');
		} else {
			layer.msg(result.msg,{tips:2});
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
// 修改密码--打开弹窗
function updateUserPwd() {
	$("#updateUserPwdForm").validate();
	$("#updateUserPwdForm").clearForm();
	var array = $('#table').bootstrapTable('getSelections');
	if (array.length < 1) {
		layer.msg('请选择一条记录',{tips:2});
		// todo
		return;
	}

	if (array.length > 1) {
		// todo
		layer.msg('请选择一条记录',{tips:2});
		return;
	}
	;

	$('#updateUserPwdId').val(array[0].id);
	openBModal('#updatepassword');
	/*layerPop = layer.open({
		type : 1,
		area : [ '420px', '240px' ], // 宽高
		content : $("#updatepassword"),
		success : function(layero, index) {
			$("#updatepassword").removeClass("hidden");
		}
	});
*/
}
// 修改密码--保存按钮
function updateUserPwdSave() {
	if (!$("#updateUserPwdForm").valid()) {
		return;
	}
	var id = $("#updateUserPwdId").val();
	var oldPwd = $("#oldPwd").val();
	var newPwd = $("#newPwd").val();
	var loading = layer.load(2);
	$.post("sysUser/updatePwd.do", {
		"id" : id,
		"oldPwd" : oldPwd,
		"newPwd" : newPwd
	}, function(result) {
		if (result.success) {
			layer.msg(result.msg,{tips:2});
			layer.close(loading);
			closeBModal('#updatepassword');

		} else {
			layer.msg(result.msg,{tips:2});
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

// 关闭弹窗
function closePop() {
	layer.close(layerPop);
}

// 清空按组织机构查询中的查询条件
function clearOrgQueryParam() {
	$("#orgName").val("");
	$("#queryOrgId").val("");
}
