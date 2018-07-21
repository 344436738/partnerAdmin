$(function() {
	var layerPop;// 弹窗
	
	$('#table').bootstrapTable( {
		method : "post",
		contentType : "application/x-www-form-urlencoded",//远程数据请求的“contentType”类型,默认请写此
		dataType : "json",//远程数据请求返回的数据类型
		url : 'stProject/dataList.do',
		idField : "id",
		sidePagination : "server",//定义表格分页的位置，只能是“client”（客户端）和“server”（服务器端）。
		pagination : true,//设置True在表格底部显示分页工具栏。
		pageNumber : 1,
		pageSize : 50,
		pageList: "[10, 25, 50, 100]",
		queryParamsType : "RESTFul",//设置为“limit”可以发送标准的RESTFul类型的参数请求。  默认请使用这个，如果项目上分页为limit方式时，写limit
		queryParams : queryParams,//远程数据请求时，可以通过queryParams来格式化所需要的数据信息，参数（对象）包含了：
		//pageSize, pageNumber, searchText, sortName, sortOrder。返回 false 可以禁用请求。
		smartDisplay : true,//设置为True智能显示分页或者Card View。
		//              cardView:true,//启用cardView模式
		//showToggle : true,//设置为True可显示切换普通表格和名片（card）布局。
		//                 search: true,
		//                 searchAlign: 'right',
		selectItemName : 'btSelectItem',//单选框或者复选框的name，用于多个表格使用radio的情况。
		showHeader : true,//显示表头
		//showColumns : true,//设置为True可显示表格显示/隐藏列表。
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
		//showExport : true,//设置为true，显示导出按钮。
		// exportTypes:['json', 'xml', 'csv', 'txt', 'sql', 'excel'],//出口类型，支持类型：“JSON”，“XML”，“PNG”，“CSV”，“TXT”，“SQL”，“文档”，“Excel的”，“简报”，“PDF”。
		//                 height:"auto",
		onLoadSuccess : function(data) {//加载成功后所做的事情
			if (data.success == false) {
				layer.msg(data.msg, {
					tips : 2
				});
			}else{
				doLoadSuccess();
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
		rowStyle:function(row,index){
			 //var classes = ['active', 'success', 'info', 'warning', 'danger'];
			var percent=row.finishPercent*100;
			//var colorclass='success';
			var colorclass='success';
			var cssTemp={};
			if(percent>50)colorclass="info";
			if(percent>90)colorclass="warning";
			if(percent>100)colorclass="danger";
		//	if(percent>100&&(row.contractAmountDecode<row.predictedCostDecode))cssTemp={"background-color":"red","color":"white"}
			
			//if(colorclass!=''){
				return {
					classes:colorclass,
					css:cssTemp
				}
			//}else{
			//	return {};
			//}
			
		},

		columns : [ {
			checkbox : true
		}, 
							{
				field : "id",
				title : "ID",
				visible:false,
				width : 50,
				align : 'center',
				sortable : true
			}
						,
								{
			field : "name",
			title : "项目名称",
			align : 'center',
			sortable : true
		}  
					  ,
		  {
				field : "managerName",
				title : "项目经理",
				align : 'center',
				sortable : true
			}  
						  ,
								
							 
						 
								{
			field : "predictedCostDecode",
			title : "预计成本",
			align : 'center',
			sortable : false
		}  
						,
						
		{
			field : "costAmount",
			title : "项目成本",
			align : 'center',
			sortable : false
		}  
						,
						
		{
			field : "bonus",
			title : "目前预计奖金",
			align : 'center',
			sortable : false
		}  
						,
						
		{
			field : "finishPercent",
			title : "项目成本比例",
			align : 'center',
			sortable : false,
			formatter : function(value, row, index) {
				var html=getCostProcessHtml((value*100).toFixed(1));
				return html;
			}
						
		}  
		,
						
		{
			field : "contractAmountDecode",
			title : "合同额",
			align : 'center',
			sortable : false
		}  
						,
		/*						{
			field : "balanceDate",
			title : "结算日期",
			align : 'center',
			sortable : true
		}  
						,*/
								{
			field : "status",
			title : "项目状态",
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
					switch(value){
						case 0:
							return "未启动";
						case 1:
							return "实施中";
						case 2:
							return "已结束";
						default:
							return "-";
					}
				}
		}  
								]
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
				name:$("#name").val()
					//自定义查询属性
	};
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

			$.post("stProject/delete.do", data, function(result) {
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
function add(id){ 
	loadPage("stProject/addProject.do");
}	
//修改--根据id读取数据方法
function loadData(id) {
	var loading=layer.load(2);
	//loadPage("${msUrl}/stProject/getId.do");
	$.post("stProject/getId.do",{id:id},function(result){
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
	var array=$('#table').bootstrapTable('getSelections');
	if(array.length<1){
		layer.msg('请至少选择一条记录', {tips:2});
		return;
	}	

	if(array.length>1){
		layer.msg('请选择一条记录', {tips:2});
		return;
	}
	loadPage("stProject/editProject.do?id="+array[0].id);
	 
}

function projectCostImport(){
	var array=$('#table').bootstrapTable('getSelections');
	if(array.length<1){
		layer.msg('请至少选择一条记录', {tips:2});
		return;
	}	

	if(array.length>1){
		layer.msg('请选择一条记录', {tips:2});
		return;
	}
	loadPage("stProject/importProjectCost.do?id="+array[0].id);
}
 
function checkContinuously(){
	$("#checkContinuouslyForm").resetForm(true);
	$("#checkContinuouslyDiv").removeClass("hidden");
	layerPop = layer.open({
		type : 1,
		area : [ '400px', 'auto' ], // 宽高
		shade : [0.5 , '#000' , true],
		content : $("#checkContinuouslyDiv"),
		success : function(layero, index) {
			 
		}
	});
}

function checkContinuouslySave(){
	if ($("#checkContinuouslyForm").valid()) {
	if($('#startDate').val()==''||$('#endDate').val()==''){
		layer.msg('请选择时间');
		return;
	}	
	var _loading = layer.load(2, {
		offset : [ '290px' ]
	});
	var option = {
			dataType : 'json',
			success : function(data) {
				layer.close(_loading );
				if (data.success) {
					if(data.iscontinue){
						if(!data.isoverflow){
							layer.msg(data.msg);
							layer.close(layerPop);
							$('#table').bootstrapTable('refresh');
						}else{
							var _msg="核检通过，但是存在超出记录：<br />";
							$.each(data.overflowList,function(index,content){
								_msg += content.userName+":";
								_msg += ""+content.recordDate+"计入:";
								_msg += ""+content.recordNum+"天工作记录";
								_msg += "<br />";
							});
							layer.alert(_msg, {icon: 6});
						}
					}else{
						var _msg="核检未通过,是否查看不连续人员？<br />";
						$.each(data.unContinuouslyUserList,function(index,content){
							_msg += content.nickName+":";
							_msg += "工作"+content.workcount+"天,";
							_msg += "请假"+content.leavecount+"天,";
							_msg += "怠工"+content.sabotagecount+"天,";
							if(content.leaveOrEntrycount) _msg += "入职/离职调整"+content.leaveOrEntrycount+"天,";
							_msg += "未达到"+data.worklimit+"天<br />";
						});
						var _index=layer.msg(_msg, {
							icon : -1,
							time : 0,
						//不自动关闭
							btn: ['查看', '不用了'],
							 yes: function(index){
								 layer.close(_index);
								 layer.close(layerPop);
								loadPage('stProject/checkContinuouslyDetail.shtml?startDate='+data.startDate+'&endDate='+data.endDate); 
							 }
						});
						
					}
				}else{
					// 关闭layer
					layer.msg(data.msg);
				}
			},
			error: function(xhr, status, text){
				layer.close(_loading );
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
		$("#checkContinuouslyForm").ajaxSubmit(option);
	}
}



function accounting(){
	$("#accountingForm").resetForm(true);
	$("#accountingDiv").removeClass("hidden");
	layerPop = layer.open({
		type : 1,
		area : [ '400px', 'auto' ], // 宽高
		shade : [0.5 , '#000' , true],
		content : $("#accountingDiv"),
		success : function(layero, index) {
			 
		}
	});
}

function beforeAccountingSave(){
	if ($("#accountingForm").valid()) {
	if($('#startDate1').val()==''||$('#endDate1').val()==''){
		layer.msg('请选择时间');
		return;
	}	
	var _index1=layer.msg("核检中",{time : 0});
	var option = {
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					if(data.iscontinue){
						// 关闭layer
						
						layer.close(_index1);
						accountingSave();
					}else{
						layer.close(_index1);
						layer.msg("核检未通过,存在不连续人员,请先进行连续性核检");
					}
				}else{
					layer.close(_index1);
					layer.msg(data.msg);
				}
			},
			error: function(xhr, status, text){
				if (xhr.status == "504") {
					layer.close(_index1);
					layer.msg("登录超时，请重新登录", {
						tips : 2
					}, function() {
						location.reload();
					});
				}else{
					layer.close(_index1);
					layer.msg("系统错误，请稍后再试", {
						tips : 2
					});
				} 
			}
		};
		var _action ="stProject/checkContinuously.do";
		$("#accountingForm").attr("action",_action);
		$("#accountingForm").ajaxSubmit(option);
	}
}


function accountingSave(){
	
	if ($("#accountingForm").valid()) {
	if($('#startDate1').val()==''||$('#endDate1').val()==''){
		layer.msg('请选择时间');
		return;
	}
	var _loading = layer.load(2, {
		offset : [ '290px' ]
	});
	var option = {
			dataType : 'json',
			success : function(data) {
				layer.close(_loading );
				if (data.success) {
					layer.msg(data.msg);
					layer.close(layerPop);
					$('#table').bootstrapTable('refresh');
				}else{
					// 关闭layer
					layer.msg(data.msg);
				}
			},
			error: function(xhr, status, text){
				layer.close(_loading );
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
		var _action ="stProject/accounting.do";
		$("#accountingForm").attr("action",_action);
		$("#accountingForm").ajaxSubmit(option);
	}
}

function getCostProcessHtml(percent){
	var html='';
	var colorclass="success";
	var percentdisplay=percent;
	if(percent>100)percentdisplay=100;
	//if(percent<10)percentdisplay=20;
	
	if(percent>50)colorclass="info";
	if(percent>90)colorclass="warning";
	if(percent>100)colorclass="danger";
	html +='<div class="progress progress-striped" style="margin:0px;">';
	html +='<div class="progress-bar progress-bar-'+colorclass+'" role="progressbar"';
	html +='aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" ';
	html +='style="width: '+percentdisplay+'%;">';
	//html +='<span class="">'+(percent>20?percent+"%":"")+' </span>';
	html +='<span class="">'+percent +'% </span>';
	html +'</div>';
	html +'</div>';
	return html;
}


//关闭窗口
function closePop(){
	layer.close(layerPop);
}







