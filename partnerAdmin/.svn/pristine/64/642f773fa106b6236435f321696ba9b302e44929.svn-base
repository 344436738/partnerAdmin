var jiathis_config={
		url: "http://www.yourdomain.com", 
		title: "马上加入乐享合伙人。邀请码：", 
		summary:"分享的文本摘要" 
};

$(function(){

	table_show();
	
	 /*全选与取消*/
	  $('body').delegate('.checkboxAll input', 'click', function () {
	    if ($(this).is(':checked')) {
	      $('.checkbox input').prop('checked', true);
	      $('.checkbox').addClass('active');
	      $('.checkboxAll').addClass('active');
	    } 
	    else {
	      $('.checkbox input').prop('checked', false);
	      $('.checkbox').removeClass('active');
	      $('.checkboxAll').removeClass('active');
	    }
	  });
	  $('body').delegate('.checkbox input', 'change', function () {
	    if ($(this).is(":checked")) {
	      $(this).parent().addClass('active');
	    } 
	    else {
	      $(this).parent().removeClass('active');
	    }
	    if ($('.checkbox input:checked').length == $('.checkbox input').length) {
	      $('.checkboxAll input').prop('checked', true);
	      $('.checkboxAll').addClass('active');
	    } 
	    else {
	      $('.checkboxAll input').prop('checked', false);
	      $('.checkboxAll').removeClass('active');
	    }
	  });
	  /*全选与取消*/
	  /*一键分享弹窗*/
	  $('.share').click(function () {
		 
	    layer.open({
	      type: 1,
	      title: '招募新合伙人获取金币！',
	      scrollbar: false,
	      move: false,
	      area: '600px',
	      shadeClose: true,
	      content: '<div class="layerPop sharePop">'
	    	  +'<div class="content">'
	    	  +'<div class="title">新用户通过链接进入注册，推荐人获得一个金币</div>'
	    	  +'<div class="jiathis_style_32x32">'
	    	  +	'<a class="jiathis_button_qzone"></a>'
	    	  +	'<a class="jiathis_button_tsina"></a>'
	    	  +	'<a class="jiathis_button_tqq"></a>'
	    	  +	'<a class="jiathis_button_weixin"></a>'
	    	  +	'<a class="jiathis_button_renren"></a>'
	    	  +	'<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>'
	    	  +'</div>'
	    	  +'<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>'
	    	  +'</div>'
	    	  +'</div>',
	    });
	  });
	  /*一键分享弹窗*/
	  $('body').delegate('.closePop', 'click', function () {
	    layer.closeAll('page');
	  });
});

//展示消息
function table_show(){
	var countn=1;
	var html = '<li class="title">';
    html += '<div class="fl num"><label class="checkboxAll"><input type="checkbox"><i></i><span>序号</span></label></div>';
    html += '<div class="fl content">';
    html += '<h4 class="total">消息</h4>';
    html += '</div>';
    html += '</li>';
	$.post( "smDataList.do",{},function(result){
		if(result.success){
			$.each(result.rows,function(name,value){
				html +=	'<li>';
		        html +=		'<div class="fl num"><label class="checkbox"><input type="checkbox" value="'+$.id+'"><i></i><span>'+countn+'</span></label></div>';
		        html += 	'<div class="fl content">';
		        html += 		'<h4 class="total">'+value.title+'</h4>';
		        html += 		'<div class="text">'+value.content+'</div>';
		        html += 		'<a href="/partner/stBusinessCenter/confirm.shtml" class="btn-ab">去看看</a>';
		        html += 	'</div>';
		        html +=	'</li>';
		        countn++;	//增加计数
			});
			$('#message').html(html);
		}
		else{
			layer.msg(result.msg, {tips:2});
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

//读取选中消息id
function readSelected(){
	var array=[];
	var dd = document.getElementsByTagName("input");
	for(var i=0;i < dd.length;i++) { 
		if(dd[i].type=='checkbox' && dd[i].checked){ 
			array.push(dd[i].val());
		}
	}
	return array;
}

//标记为已读
function marked(){
	var array=readSelected();
	if(array.length<=0){
		layer.msg('请选择项目', {tips:2});
		return ;
	}
	var arr = [],idKey = 'id'; //主键名称
	$.each(array,function(i,record){
		arr.push(idKey+'='+record[idKey]);
	});
	var data = arr.join("&");
	
	$.post("smMarked.do",data,function(result){
		if(result.success){
			layer.msg(result.msg, {tips:2});
			table_show();
		}else{
			layer.msg(result.msg, {tips:2});
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

//删除消息
function del(){
	var array=readSelected();
	if(array.length<=0){
		layer.msg('请选择项目', {tips:2});
		return ;
	}
	var arr = [],idKey = 'id'; //主键名称
	$.each(array,function(i,record){
		arr.push(idKey+'='+record[idKey]);
	});
	var data = arr.join("&");
	
	$.post("smDelete.do",data,function(result){
		if(result.success){
			layer.msg(result.msg, {tips:2});
		}else{
			layer.msg(result.msg, {tips:2});
		}
		$('#table').bootstrapTable('refresh');
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