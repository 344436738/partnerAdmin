<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <<script type="text/javascript">
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
								layer.close(layerPop);
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
</script>
    
<body>
  <h3 class="litit ft16 ftno">消息群发页</h3>
  <form action="${msUrl}/WeixinSendGroupMessage/sendMessage.do" id="editForm" method="post"   enctype="multipart/form-data">
    <!--   <input type="hidden"   id="param" name="param" />
      <input type="hidden"  id="templateid" name="templateid" /> -->
      
    <p class="sub_form_list clearfix">
      <span class="sub_list_name">群发分组：</span>
     		  <select class="manage_input wd160" name="istoAll" id="istoAll">
					   <option value="true">全部用户</option>
						<option value="false">按分组选择</option>
					</select>
    </p>
   <!--  <p class="sub_form_list clearfix">
      <span class="sub_list_name">用户分组：</span>
       <select class="manage_input wd160" name="groupid" id="groupid">
					   <option value="true">------</option>
					</select>
    </p> -->
    
    <p class="sub_form_list clearfix">
      <span class="sub_list_name">群发类型：</span>
       <select class="manage_input wd160" name="msgtype" id="msgtype">
					   <option value="text">文本消息</option>
					    <option value="news">图文消息</option>
					</select>
    </p>
   
   <p class="sub_form_list clearfix">
      <span class="sub_list_name">文本内容：</span>
       <textarea class="wz" placeholder="请输入内容" name="param" id="param" style="width: 500px;height: 400px;"></textarea>
       
    </p>
    
    
  </form>
	<p class="btinp" style="text-align:center;">
	  <input type="button" value="确定" onclick="updateSave()" id="test1" class="orage_bg prl20 sub_detail_btn h32">
	 <input type="reset" value="取消" onclick="closePop()" class="bglv prl20 sub_detail_btn h32">
	</p>
  <div class="manage_bot">
    <p class="copyright ft12">All Rights Reserved. Copyright 2015 XX科技有限股份有限公司</p>
  </div>
 
</body>
