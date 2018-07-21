$(function(){
	$("#successMsg").hide();
});


//保存评价表
function saveevaluate(){
	if ($("#evaluateForm").valid()) {
		var option = {
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					$("#evaluateDiv").hide();
					$('#num').html(3);
					$("#successMsg").show();					
					jump(3);
				}else{
					layer.msg(data.msg, {
						icon : -1,
						time : 1000
					//2秒关闭（如果不配置，默认是3秒）
							});
				}
			}
		};
		$("#evaluateForm").ajaxSubmit(option);
	}
}


function cancel(){
	window.location.href="incomeCountDetail.shtml"; 
}

function jump(count) {
    window.setTimeout(function () {
       count--;
       if (count > 0) {
          $('#num').html(count);
          jump(count);
       } else {
          location.href = "incomeCountDetail.shtml";
       }
    }, 1000);
 }