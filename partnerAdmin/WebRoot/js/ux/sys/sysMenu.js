$(function() {
	
	$('#table').bootstrapTable(
			{
				method : "post",
				contentType : "application/x-www-form-urlencoded",//远程数据请求的“contentType”类型,默认请写此
				dataType : "json",//远程数据请求返回的数据类型
				url : 'sysMenu/dataList.do',
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
					else{
						layer.msg("系统错误，请稍后再试", {
							tips : 2
						});
					}
				},
				columns : [
						{
							checkbox : true
						},
						{
							field : 'id',
							title : 'ID',
							//width : 50,
							align : 'center',
							sortable : true
						},
						{
							field : 'name',
							title : '名称',
							//width : 120,
							align : 'center',
							sortable : true
						},
						{
							field : 'rank',
							title : '排序',
							align : 'center',
							//width : 80,
							sortable : true
						},
						{
							field : 'url',
							title : '路径',
							//width : 100,
							align : 'center',
							sortable : true
						},
						/*{
							field : 'createTime',
							title : '创建时间',
							//width : 150,
							align : 'center',
							sortable : true
						},
						{
							field : 'updateTime',
							title : '更新时间',
							//width : 150,
							align : 'center',
							sortable : true
						},*/
						{
							field : 'childMenus',
							title : '子菜单',
							//width : 120,
							align : 'center',
							formatter : function(value, row, index) {
								var html = "<a href='javascript:void(0)' onclick='toList("
										+ row.id + ")'>子菜单管理(" + row.subCount
										+ ")</a>";
								return html;
							}
						} ]
			});
});
//查询方法，供其他位置点击触发Table的查询效果
function search(){
	$('#table').bootstrapTable('refresh');
}
//table的查询参数
function queryParams(params) {
	return {
		rows : params.pageSize,//每页多少条
		page : params.pageNumber,//当前页
		sort : params.sortName,//根据哪个字段排序
		order : params.sortOrder,//升降序 
		name : $("#name").val(),//自定义查询属性
		parentId : $("#_querypid").val()
	};
}
//删除记录
function del(){ 
var array=$('#table').bootstrapTable('getSelections');
if(array.length<=0){
	layer.msg('请选择项目', {tips:2});
	return ;
}

layer.confirm('您确定要删除吗？', {
    btn: ['删除','取消'], //按钮
}, function(){
	$('#table').bootstrapTable('showLoading');

	var arr = [],idKey = 'id'; //主键名称
	$.each(array,function(i,record){
		arr.push(idKey+'='+record[idKey]);
	});
	var data = arr.join("&");

	$.post("sysMenu/delete.do",data,function(result){
		$('#table').bootstrapTable('hideLoading');
		if(result.success){
			$('#table').bootstrapTable('refresh');
			layer.msg('已删除~请您刷新页面查看新菜单', {tips:2,time: 2000});
		}else{
			layer.msg('网络错误，删除失败', {tips:2});
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
}, function(){
	 //layer.msg('已取消', {icon: 1});
});
}
//添加纪录
function add(){ 
	$("#editForm").clearForm(true);
	 openBModal('#editDiv');
	/*layerPop=layer.open({
	    type: 1,
	    area: ['500px', 'auto'], //宽高
	    content: $("#editDiv"),
	    success: function(layero, index){
			delAllLine(true);//清空已有的数据
			var parentId =$('#_querypid').val();
			if(parentId){
				$("#edit_parentId").val(parentId);
			}
    }
	});*/
}
// 修改--根据id读取数据方法
function loadData(id) {
	 var loading=layer.load(2);
	$.post("sysMenu/getId.do",{id:id},function(result){
		layer.close(loading);
		if(result.success){
			$("#editForm").fill(result.data); 
			delAllLine(true);
			$.each(result.data.btns,function(i,btn){
				addLine(btn);
			});
			
		}else{
			layer.msg('无此记录', {tips:2});
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


// 修改--根据记录打开弹窗
function update(){
	$( "#editForm" ).validate();
	$( "#editForm" ).clearForm(true);
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
/*	layerPop=layer.open({
	    type: 1,
	    area: ['500px', '500px'], //宽高
	    content: $("#editDiv"),
	    success: function(layero, index){
		 loadData(array[0].id);
    }
	
	});*/    
}

//编辑
function updateSave(){
	
	if($("#editForm").valid()){
   var loading=layer.load(2);
	var option = {
			dataType:'json',
			success:function(data){ 
				layer.close(loading);
				if(data.success){
					layer.msg(data.msg, {
					    icon: -1,
					    time: 2000 //2秒关闭（如果不配置，默认是3秒）
					}, function(){
						//关闭layer
						closeBModal('#editDiv');
						$('#table').bootstrapTable('refresh');
					});  
				}
			},
			error: function(xhr, status, text){ 
				layer.close(loading);
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
			},
		};
		$("#editForm").ajaxSubmit(option);
	}
}
//增加隐藏弹出框里面的一行增加菜单的表单
function addLine(data){
	var table = $("#tyshuju");
	var	html = "<tr class='tb-line'>";
	html+=	   "	<td><input name=\"btnName\" type=\"text\" class=\"form-control\" ></td>";
	html+=	   "	<td><input name=\"btnType\" type=\"text\" class=\"form-control\"  ></td>";
	html+=	   "	<td><input name=\"actionUrls\" type=\"text\" class=\"form-control\"  ></td>";
	html+=	   "	<td align='center'><input type=\"button\" class=\"form-control remove-btn\" value=\"-\" ></input>";
	html+=	   "		<input class=\"hidden\" name=\"btnId\">";
	html+=	   "		<input class=\"hidden\" name=\"deleteFlag\">";
	html+=	   "	</td>";
	html+=	   "</tr>";
	var line = $(html);//将这段html封装为jQuery对象
	//绑定删除按钮事件
	$(".remove-btn",line).click(function(){
			
		var index=layer.confirm('删除确认','你确定删除菜单么?',function(r){
			if(r){
				delLine(line);
				layer.close(index);
			}
		})
	});
	if(data){
		if(data.id){
			$(".newFlag",line).html(''); //清空新增标记
		}
		$("input[name='btnId']",line).val(data.id);
		$("input[name='btnName']",line).val(data.btnName);
		$("input[name='btnType']",line).val(data.btnType);
		$("input[name='actionUrls']",line).val(data.actionUrls);
	}
 
	table.append(line);
	
}
//删除全部
function delAllLine(b){
	if(b){
		$(".tb-line").remove();
	}else{
		$(".tb-line").each(function(i,line){
			delLine($(line));
		});
	}
}	
//删除单行
function delLine(line){
	if(line){
		var btnId = $("input[name='btnId']",line).val();
		if(btnId){
			$("input[name='deleteFlag']",line).val(1); //设置删除状态
			line.fadeOut();
		}else{
			line.fadeOut("fast",function(){
				 $(this).remove();
			});
		}
	}
}
//设置默认按钮数据
function addDefBtns(){
	var defaultBtns= [
		{"btnName":"添加","menuid":2,"actionUrls":"save.do","btnType":"add"},
		{"btnName":"修改","menuid":2,"actionUrls":"getId.do|save.do","btnType":"edit"},
		{"btnName":"删除","menuid":2,"actionUrls":"delete.do","btnType":"remove"}
	];
	var tbline = $(".tb-line:visible");
	var btnType = $("input[name='btnType']",tbline);
	$.each(defaultBtns,function(i,btn){
		var isExists = false;
		//判断按钮类型是否存在
		if(btnType.length > 0){
			for(var i =0; i < btnType.length; i++){
				if(btnType.eq(i).val() == btn.btnType){
					isExists = true;
					break;
				}
			}
		}
		if(!isExists){
			addLine(btn);
		}
	});
}
//查询子菜单
function toList(pid){
	$("#_querypid").val(pid);
	$('#table').bootstrapTable("hideColumn","childMenus");
	$('#table').bootstrapTable("refresh");
	$("#btnfh").css('display','inline-block'); 
	$('#btnfh').removeClass('hidden');
	//$('#btnfh').show();
	
	
}
//返回父菜单
function returnFid(){
	$("#_querypid").val('');
	$('#table').bootstrapTable("showColumn","childMenus");
	$('#table').bootstrapTable("refresh");
	$('#btnfh').addClass('hidden');
	//$('#btnfh').hide();
	//$("#btnfh").css('display','none'); 
}
