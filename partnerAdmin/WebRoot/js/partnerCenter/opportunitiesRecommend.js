
function getmoney(){
	var data= $("#exchangescore").val();
	document.getElementById("getmoney").value = data;
}

function recommend() {
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

function next(){
	var industry=$("#industry").val();
	//$("#basic").hide();
	var type=2;//type=2说明是公司用户
	$.post("queryCompany.do", { industryid:industry,type:type}, function(result) {
		if (result.rows||result.success) {
			writeResult("#recommend",result.rows);
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
		}
		else{
		    layer.msg("系统错误，请稍后再试", {
			tips : 2
		});
	  }
	});
	
}

function search(){
	var searchComp=$("#searchComp").val();
	//$("#basic").hide();
	$.post("queryCompany.do", { nickname:searchComp ,type:2}, function(result) {
		if (result.rows||result.success) {
			writeResult("#seachresult",result.rows);
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
		}
		else{
		    layer.msg("系统错误，请稍后再试", {
			tips : 2
		});
	  }
	});
}

function writeResult(target,result){
	var html="<table id="+"comptable"+">";
	$.each(result,function(n,comp){
			html+="<tr>";
			html+="<td>"+comp.nickname+"</td>";
			html+="<td>"+comp.intro+"</td>";
			html+="<td>"+comp.insize+"</td>";
			if(comp.evaluatescore){
				html+="<td>"+comp.evaluatescore.toFixed(1)+"分</td>";
			}else{
				html+="<td>"+"0"+"分</td>";
			}
			html+="<td>有"+comp.evaluatenum+"人评价</td>";
			html+="<td>"+"<button onclick="+"watchevaluate("+comp.id+")"+">查看评价</button>"+"</td>";
			html+="<td>"+"<button onclick="+"setCompId("+comp.id+")"+">选择</button>"+"</td>";
			
			html+="</tr>"
		}
	);
	html+="</table>";
	$(target).html(html);
}

function setCompId(value){
	$('#Compid').val(value);
}

function watchevaluate(value){
	
	$.post("evaluateShow.do",{userid:value},function(result){
		if(result.success||result.rows){
			var html="<tr>";
			html+="<td>"+"评价人"+"</td>";
			html+="<td>"+"确认速度"+"</td>";
			html+="<td>"+"支付金额"+"</td>";
			html+="<td>"+"合作态度"+"</td>";
			html+="<td>"+"评价内容"+"</td>";
			html+="</tr>";
			$.each(result.rows,function(name,value){
				html+="<tr>";
				html+="<td>"+value.evaluatename+"</td>";
				html+="<td>"+value.confirmspeed+"</td>";
				html+="<td>"+value.paynum+"</td>";
				html+="<td>"+value.coopattitude+"</td>";
				html+="<td>"+value.evaluatecontent+"</td>";
				
				html+="</tr>";
			});
			$("#evaluatetable").html(html);
		}
		
	},"json");
	
	$("#editDiv").modal('show');
}

