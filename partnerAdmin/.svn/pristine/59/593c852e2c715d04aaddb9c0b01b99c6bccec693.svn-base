
function Login(){	
	if($("#loginForm").valid()){
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
		$("#loginForm").ajaxSubmit(option);
	}
}

function Forget(){
	
}

function Register(){
	
}

function Confirm(){
	
}