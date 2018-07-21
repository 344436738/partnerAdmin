$(function() {
	var layerPop;// 弹窗
	
	$('#table').bootstrapTable( {
		method : "post",
		contentType : "application/x-www-form-urlencoded",//远程数据请求的“contentType”类型,默认请写此
		dataType : "json",//远程数据请求返回的数据类型
		url : 'weixinHuodong/dataList.do',
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
		}, 
							{
				field : "id",
				title : "ID",
				width : 50,
				align : 'center',
				sortable : true
			}
						,
								{
			field : "title",
			title : "活动名称",
			align : 'center',
			sortable : true
		}  
						,
							{
			field : "priceone",
			title : "一等奖奖品",
			align : 'center',
			sortable : true
		}  
						,
							{
			field : "pricetwo",
			title : "二等奖奖品",
			align : 'center',
			sortable : true
		}  
						,
								{
			field : "pricethree",
			title : "三等奖奖品",
			align : 'center',
			sortable : true
		}  
						,
		{
			field : "gl",
			title : "中奖概率",
			align : 'center',
			sortable : true
		}  ,
		{
			field : "starttime",
			title : "开始时间",
			align : 'center',
			sortable : true
		}  
						,
									
		{
			field : "endtime",
			title : "结束时间",
			align : 'center',
			sortable : true
		},
		{
			title : '操作',
			width : 220,
			align : 'center',
			formatter : function(value, row, index) {
				var html = "<a href='#' onclick='showPrize("+row.id+")'>查看中奖记录</a>&nbsp&nbsp&nbsp&nbsp<a href='#' onclick='showRecord("+row.id+")'>查看抽奖记录</a>";
				return html;
			}
		}]
	});
});
//查询方法，供其他位置点击触发Table的查询效果
function search() {
	$('#table').bootstrapTable('refresh');
}
// table的查询参数
function queryParams(params) {
	return {
		rows : params.pageSize,//每页多少条
		page : params.pageNumber,//当前页
		sort : params.sortName,//根据哪个字段排序
		order : params.sortOrder,//升降序 
				type:$("#type").val()
					//自定义查询属性
	};
}
//查看中奖记录
function showPrize(id){
	/*//var loading=layer.load(2);
	layerPop=layer.open({
	    type: 2, 
	    area: ['1300px', '600px'],
	    content:['weixinPrizerecord/list.do?hdid='+id]
	}); */
	loadPage('weixinPrizerecord/list.do?hdid='+id+'&type='+$('#type').val());
	
}
//抽奖记录
function showRecord(id){
	/*layerPop=layer.open({
	    type: 2, 
	    area: ['1300px', '600px'],
	    content:['weixinHdrecord/list.do?hdid='+id]
	}); */
	loadPage('weixinHdrecord/list.do?hdid='+id+'&type='+$('#type').val());
}
//删除
function del() {
	var array = $('#table').bootstrapTable('getSelections');
	if (array.length <= 0) {
		layer.msg('请选择项目', {
			tips : 2
		});
		return;
	}
	layer.confirm('您确定要删除吗？', {
		btn : [ '删除', '取消' ], //按钮
		shade : false
	//不显示遮罩
			}, function() {

				$('#table').bootstrapTable('showLoading');

				var arr = [], idKey = 'id'; //主键名称
			$.each(array, function(i, record) {
				arr.push(idKey + '=' + record[idKey]);
			});
			var data = arr.join("&");

			$.post("weixinHuodong/delete.do", data, function(result) {
				$('#table').bootstrapTable('hideLoading');
				if (result.success) {
					$('#table').bootstrapTable('refresh');
					layer.msg('已删除', {
						tips : 2
					});
				} else {
					layer.msg('网络错误，删除失败', {
						tips : 2
					});
				}
			},"json");
		}, function() {
			layer.msg('已取消', {
				tips : 2
			});
		});
}
//添加
function add(){ 
	$("#editForm").clearForm(true);
	$("#editForm").validate().resetForm();
	openBModal('#editDiv');
		
}	
//修改--根据id读取数据方法
function loadData(id) {
	var loading=layer.load(2);
	//loadPage("${msUrl}/weixinHuodong/getId.do");
	$.post("weixinHuodong/getId.do",{id:id},function(result){
		if(result.success){
			$("#editForm").fill(result.data); 
			 
			layer.close(loading);
			
		}else{
			layer.msg('无此记录', {tips:2});
		}
	},"json");
}
//修改
function update(){
	$( "#editForm" ).validate().resetForm();
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
	openBModal('#editDiv');
	loadData(array[0].id);
}
//编辑
function updateSave() {
	if ($("#editForm").valid()) {
		var option = {
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					layer.msg(data.msg, {
						icon : -1,
						time : 1000
					//2秒关闭（如果不配置，默认是3秒）
							}, function() {
								//关闭layer
							closeBModal('#editDiv');
							$('#table').bootstrapTable('refresh');
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

//关闭窗口
function closePop(){
	layer.close(layerPop);
}
