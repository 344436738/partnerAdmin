$(function(){
	$('#resumetable').bootstrapTable( {
		method : "post",
		contentType : "application/x-www-form-urlencoded",//远程数据请求的“contentType”类型,默认请写此
		dataType : "json",//远程数据请求返回的数据类型
		url : 'resumeList.do',
		idField : "id",
		sidePagination : "server",//定义表格分页的位置，只能是“client”（客户端）和“server”（服务器端）。
		pagination : true,//设置True在表格底部显示分页工具栏。
		pageNumber : 1,
		pageSize : 10,
		pageList : [ 10, 25, 50, 100 ],
		queryParamsType : "RESTFul",//设置为“limit”可以发送标准的RESTFul类型的参数请求。  默认请使用这个，如果项目上分页为limit方式时，写limit
		queryParams : queryParams,//远程数据请求时，可以通过queryParams来格式化所需要的数据信息，参数（对象）包含了：
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
			    field : "id",
			    visible:false
		    },{
			    field : "fullname",
			    title : "姓名",
			    width : 50,
			    align : 'center',
			    sortable : true
		    },{
			    field : "sex",
			    title : "性别",
			    align : 'center',
			    sortable : true,
			    formatter : function(value, row, index){
			    	if(value==0){
						return "男";
					}
			    	if(value==1){
						return "女";
					}
					
				}
		    },{
			    field : "worktime",
			    title : "工作年限",
			    align : 'center',
			    sortable : true
		    },{
			    field : "currentlocation",
			    title : "所在城市",
			    align : 'center',
			    sortable : true
		    },{
			    field : "education",
			    title : "毕业学校",
			    align : 'center',
			    sortable : true
		    },{
			    field : "uploadtime",
			    title : "上传时间",
			    align : 'center',
			    sortable : true
		    },{
			    field : "uploader",
			    title : "上传人",
			    align : 'center',
			    sortable : true
		    },{
			    field : "vv",
			    title : "下载",
			    align : 'center',
			    sortable : true,
			    formatter : function(value, row, index){
			     		  	return "<a href='"+row.url+"' >下载</a>";
				}
		    },{
			    field : "status",
			    title : "审核状态",
			    align : 'center',
			    sortable : true,
			    formatter : function(value, row, index){
			    	if(value==0){
						return "未审核";
					}
			    	if(value==1){
						return "审核通过";
					}
					if(value==2){
						return "审核未通过";
					}
				}
		    }]
	  });
});

//table的查询参数
function queryParams(params) {
	var status=$("#status").val();
	
	return {
		rows : params.pageSize,//每页多少条
		page : params.pageNumber,//当前页
		sort : params.sortName,//根据哪个字段排序
		order : params.sortOrder,//升降序
		status : status,
		uploadby : $("#ssid").val()
	};
}

function changestatus(){
	$("#resumetable").bootstrapTable("refresh");
}


function checkpass(){
	var array=$("#resumetable").bootstrapTable('getSelections');
	
	if(array.length<1){
		layer.msg('请选择一条记录', {tips:2});
		return;
	}
	
	var allArray = [];
	$.each(array, function(i, record) {
		allArray.push({
			id:record['id'],
			status:record['status']
		});
	});	
	$.ajaxSetup({  
        contentType : 'application/json'  
    });

	$.post("resumeCheck.do", JSON.stringify(allArray), function(result) {
		if (result.success) {
			$('#resumetable').bootstrapTable('refresh');
			layer.msg(result.msg, {
				tips : 2
			});
		} else {
			layer.msg(result.msg, {
				tips : 2
			});
		}
	},"json");
}

function checknotpass(){
	var array=$("#resumetable").bootstrapTable('getSelections');
	
	if(array.length<1){
		layer.msg('请选择一条记录', {tips:2});
		return;
	}
	
	var allArray = [];
	$.each(array, function(i, record) {
		allArray.push({
			id:record['id'],
			status:record['status']
		});
	});	
	$.ajaxSetup({  
        contentType : 'application/json'  
    });

	$.post("resumeCheckNot.do", JSON.stringify(allArray), function(result) {
		if (result.success) {
			$('#resumetable').bootstrapTable('refresh');
			layer.msg(result.msg, {
				tips : 2
			});
		} else {
			layer.msg(result.msg, {
				tips : 2
			});
		}
	},"json");
}

