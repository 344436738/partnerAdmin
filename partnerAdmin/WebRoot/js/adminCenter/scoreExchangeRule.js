$(function(){
	var dkey=$("#dkey").val();
	$.post("scoreExchangeRuleList.do",{dkey:dkey},function(result){
		if(result.success){
			$("#id").val(result.rows.id);
			$("#dvalue").val(result.rows.dvalue);
		}
		else{
			layer.msg("还未设置积分兑换规则", {
				tips : 2
			});
		}
	},"json");
	
});




function saveExchangeRule(){
	if($("#exchangeruleForm").valid()){
		var option = {
			dataType:'json',
			success:function(data){ 
				if(data.success){
					layer.msg(data.msg, {
					    icon: -1,
					    time: 2000 //2秒关闭（如果不配置，默认是3秒）
					}, function(){
						//关闭layer
						
					});  
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
			},
		};
		$("#exchangeruleForm").ajaxSubmit(option);
	}
}

function cancel(){
	history.back();
}