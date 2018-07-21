$(function(){
	$('#resumetable').bootstrapTable( {
		method : "post",
		contentType : "application/x-www-form-urlencoded",//远程数据请求的“contentType”类型,默认请写此
		dataType : "json",//远程数据请求返回的数据类型
		url : 'queryResumeInformation.do',
		idField : "id",
		sidePagination : "server",//定义表格分页的位置，只能是“client”（客户端）和“server”（服务器端）。
		pagination : true,//设置True在表格底部显示分页工具栏。
		pageNumber : 1,
		pageSize : 10,
		pageList : [ 10, 25, 50, 100 ],
		queryParamsType : "RESTFul",//设置为“limit”可以发送标准的RESTFul类型的参数请求。  默认请使用这个，如果项目上分页为limit方式时，写limit
		queryParams : queryResumeParams,//远程数据请求时，可以通过queryParams来格式化所需要的数据信息，参数（对象）包含了：
		//pageSize, pageNumber, searchText, sortName, sortOrder。返回 false 可以禁用请求。
		smartDisplay : true,//设置为True智能显示分页或者Card View。
		//              cardView:true,//启用cardView模式
		showToggle : true,//设置为True可显示切换普通表格和名片（card）布局。
		//                 search: true,
		//                 searchAlign: 'right',
		selectItemName : 'btSelectItem',//单选框或者复选框的name，用于多个表格使用radio的情况。
		showHeader : true,//显示表头
		showColumns : true,//设置为True可显示表格显示/隐藏列表。
		//                 minimumCountColumns:1,//表格显示/隐藏列表时可设置最小隐藏的列数。
		//                 undefinedText:"---",//数据为空时显示 
		striped : true,//使表格带有条纹。
		showRefresh : true,//设置为True可显示刷新按钮。
		toolbar : "#toolbar",//定义toolbar是哪个
		toolbarAlign : "left",//对齐方式
		clickToSelect : true,//设置为True时点击行即可选中单选/复选框。
		//                 singleSelect:true,//只能选择一条记录
		//                 checkboxHeader:true,//设置为False时隐藏表头中的全选复选框。
		//                 maintainSelected:true,//设置为True当换页或者搜索时保持选中的行。
		//                 sortable:true,//设置为False时禁用所有列的排序。
		showExport : true,//设置为true，显示导出按钮。
		// exportTypes:['json', 'xml', 'csv', 'txt', 'sql', 'excel'],//出口类型，支持类型：“JSON”，“XML”，“PNG”，“CSV”，“TXT”，“SQL”，“文档”，“Excel的”，“简报”，“PDF”。
		//                 height:"auto",
		onLoadSuccess : function(data) {//加载成功后所做的事情
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
		},{
			field : "fullname",
			title : "姓名",
			align : 'center',
			sortable : true
		},{
			field : "intentionjob",
			title : "求职意向",
			align : 'center',
			sortable : true
		},{
			field : "keyword",
			title : "专业分类",
			align : 'center',
			sortable : true
		},{
			field : "isworking",
			title : "是否在职",
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
		},{
			field : "highestdegree",
			title : "学历",
			align : 'center',
			sortable : true
		},{
			field : "worktime",
			title : "工作年限",
			align : 'center',
			sortable : true
		},{
			field : "status",
			title : "审核状态",
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				if (value == 0) {
					return "正在审核中";
				}
				if (value == 1) {
					return "审核通过";
				}
				if(value == 2) {
					return "审核未通过";
				}
			}
		}]
	});
	
	
	
	$('#opptable').bootstrapTable( {
		method : "post",
		contentType : "application/x-www-form-urlencoded",//远程数据请求的“contentType”类型,默认请写此
		dataType : "json",//远程数据请求返回的数据类型
		url : 'queryOpportunitiesInformation.do',
		//idField : "id",
		sidePagination : "server",//定义表格分页的位置，只能是“client”（客户端）和“server”（服务器端）。
		pagination : true,//设置True在表格底部显示分页工具栏。
		pageNumber : 1,
		pageSize : 10,
		pageList : [ 10, 25, 50, 100 ],
		queryParamsType : "RESTFul",//设置为“limit”可以发送标准的RESTFul类型的参数请求。  默认请使用这个，如果项目上分页为limit方式时，写limit
		queryParams : queryOppParams,//远程数据请求时，可以通过queryParams来格式化所需要的数据信息，参数（对象）包含了：
		//pageSize, pageNumber, searchText, sortName, sortOrder。返回 false 可以禁用请求。
		smartDisplay : true,//设置为True智能显示分页或者Card View。
		//              cardView:true,//启用cardView模式
		showToggle : true,//设置为True可显示切换普通表格和名片（card）布局。
		//                 search: true,
		//                 searchAlign: 'right',
		selectItemName : 'btSelectItem',//单选框或者复选框的name，用于多个表格使用radio的情况。
		showHeader : true,//显示表头
		showColumns : true,//设置为True可显示表格显示/隐藏列表。
		//                 minimumCountColumns:1,//表格显示/隐藏列表时可设置最小隐藏的列数。
		//                 undefinedText:"---",//数据为空时显示 
		striped : true,//使表格带有条纹。
		showRefresh : true,//设置为True可显示刷新按钮。
		toolbar : "#toolbar",//定义toolbar是哪个
		toolbarAlign : "left",//对齐方式
		//clickToSelect : true,//设置为True时点击行即可选中单选/复选框。
		//                 singleSelect:true,//只能选择一条记录
		//                 checkboxHeader:true,//设置为False时隐藏表头中的全选复选框。
		//                 maintainSelected:true,//设置为True当换页或者搜索时保持选中的行。
		//                 sortable:true,//设置为False时禁用所有列的排序。
		showExport : true,//设置为true，显示导出按钮。
		// exportTypes:['json', 'xml', 'csv', 'txt', 'sql', 'excel'],//出口类型，支持类型：“JSON”，“XML”，“PNG”，“CSV”，“TXT”，“SQL”，“文档”，“Excel的”，“简报”，“PDF”。
		//                 height:"auto",
		onLoadSuccess : function(data) {//加载成功后所做的事情
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
			field : "id",
			visible:false
		},{
			field : "userid",
			visible:false
		},{
			field : "customercompname",
			title : "客户公司名称",
			align : 'center',
			sortable : true
		},{
			field : "customername",
			title : "客户姓名",
			align : 'center',
			sortable : true
		},{
			field : "customertel",
			title : "客户电话",
			align : 'center',
			sortable : true
		},{
			field : "requirementstype",
			title : "需求类型",
			align : 'center',
			sortable : true
		},{
			field : "requirementsdescribe",
			title : "需求描述",
			align : 'center',
			sortable : true
		},{
			field : "checkstatus",
			title : "确认状态",
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				if (value == 0) {
					return "确认无效商机";
				}
				if (value == 1) {
					return "未确认";
				}
				if (value == 2) {
					return "已确认";
				}
			}
		},{
			field : "paystatus",
			title : "支付状态",
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				if (value == 0) {
					return "未支付";
				}
				if (value == 1) {
					return "已支付";
				}
			}
		},{
			field : "personevaluate",
			title : "评价",
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				if (value == 1) {
					return '<a href="javascript:;" onclick="evaluatefun('+row.id+','+row.userid+','+row.personevaluate+')">待评价</a>';
				}
				if (value == 2) {
					return '<a href="javascript:;" onclick="evaluatefun('+row.id+','+row.userid+','+row.personevaluate+')">已经评价</a>';
				}
			}
		}]
	});
	
});

function queryResumeParams(params) {
	return {
		rows : params.pageSize,//每页多少条
		page : params.pageNumber,//当前页
		sort : params.sortName,//根据哪个字段排序
		order: params.sortOrder,//升降序 
		status:$("#resumestatus").val()
					//自定义查询属性
	};
}

function queryOppParams(params) {
	var oppstatus=$("#oppstatus").val();
	var checkstatus;
	var paystatus;
	switch(oppstatus){
	case "0":
		checkstatus=0;
		paystatus=0;
		break;
	case "1":
		checkstatus=1;
		paystatus=0;
		break;
	case "2":
		checkstatus=2;
		paystatus=0;
		break;
	case "3":
		checkstatus=2;
		paystatus=1;
		break;
	default:
			break;
	}
	
	return {
		rows : params.pageSize,//每页多少条
		page : params.pageNumber,//当前页
		sort : params.sortName,//根据哪个字段排序
		order: params.sortOrder,//升降序 
		checkstatus:checkstatus,
		paystatus:paystatus
					//自定义查询属性
	};
}


function searchResume(){
	
	$('#resumetable').bootstrapTable('refresh');
}

function searchOpp(){
	$('#opptable').bootstrapTable('refresh');
}


function saveevaluate(){
	if ($("#editForm").valid()) {
		var option = {
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					$("#editDiv").modal('hide');
					$('#opptable').bootstrapTable('refresh');
					layer.msg(data.msg, {
						icon : -1,
						time : 1000
					//2秒关闭（如果不配置，默认是3秒）
							}, function() {	
							
							
						});

				}else{
					layer.msg(data.msg, {
						icon : -1,
						time : 1000
					//2秒关闭（如果不配置，默认是3秒）
							});
				}
			}
		};
		$("#editForm").ajaxSubmit(option);
	}
}

function evaluatefun(id,userid,companyevaluate) {
	if(companyevaluate==1){
		$("#oppid").val(id);
		$("#compid").val(userid);
		//alert($("#oppid").val());
		$("#evaluateForm").submit();
	}
	else {
		$.post("evaluateShow.do",{oppid:id,userid:userid},function(result){
			$("#myevaluate").fill(result.rows[0]);
			//$("#confirmspeed").val(5);
			$("#confirmspeed").rating({'showClear':false});
			$("#paynum").rating({'showClear':false});
			$("#coopattitude").rating({'showClear':false});
			$("#evaluteModal").modal('show');
		},"json");
	}  
}
