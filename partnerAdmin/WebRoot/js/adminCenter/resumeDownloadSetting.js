$(function(){
	var dkeyperson=$("#dkeyperson").val();
	var dkeycomp=$("#dkeycomp").val();
	
	var array=[];
	array.push({dkey:dkeyperson},{dkey:dkeycomp});
	$.ajaxSetup({  
        contentType : 'application/json'  
    }); 
	
	$.post("downloadSettingList.do",JSON.stringify(array),function(result){
		if(result.success){
			if(result.rows[0]){
				$("#idperson").val(result.rows[0].id);
				if(result.rows[0].dvalue==1){
					document.getElementById("dvalueperson1").checked = "checked";
				}else if(result.rows[0].dvalue==2){
					document.getElementById("dvalueperson2").checked = "checked";
				}				
			}
			if(result.rows[1]){
				$("#idcomp").val(result.rows[1].id);
				if(result.rows[1].dvalue==1){
					document.getElementById("dvaluecomp1").checked = "checked";
				}else if(result.rows[1].dvalue==2){
					document.getElementById("dvaluecomp2").checked = "checked";
				}
			}
		}else{
			layer.msg("还未设置上传获取积分规则", {
				tips : 2
			});
		}
		
		
		
	},"json");
	
});

function saveDownloadSetting(){
	
	$.ajaxSetup({  
        contentType : 'application/x-www-form-urlencoded; charset=UTF-8'
    });
	
	if($("#downloadSettingForm").valid()){
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
		$("#downloadSettingForm").ajaxSubmit(option);
	}
}


function cancel(){
	history.back();
}