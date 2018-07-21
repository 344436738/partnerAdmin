<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/include.jsp"%>
<script type="text/javascript" src="${msUrl}/js/weixinHdrecord/weixinHdrecord.js"></script>

<script type="text/javascript">
	/* $(document).ready(function() {
			//初始化校验
			$.validator.setDefaults( {
				ignore : []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
			$("#editForm").validate();
			
			
																																		laydate({
				    elem: '#addtime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
				    event: 'focus', //响应事件。如果没有传入event，则按照默认的click
				    format: 'YYYY/MM/DD hh:mm:ss',
				    istime: true,
				    istoday: true,
				    choose: function(datas){ //选择日期完毕的回调
				    	$("#editForm").validate().element($('#addtime'))
				    }
				});
																		
		}); */
		function returnFid(){
			loadPage('weixinHuodong/list.do?type=${type}');
			
		}
</script>
				
<!--查询条件  -->
<form action="" class="list_con_form clearfix" id="searchForm">
		<span class="manage_unit clearfix"> <span class="manage_span_name">类型：</span> <input
			type="text" name="roleName" id="new_type" class="manage_input wd160" />
	</span>
		<span class="manage_unit clearfix"> <input type="button" value="查询"
			onclick="search()" class="h32 inline_blo flole prl20 orage_bg"> </span>
</form>
<div class="dashed-line"></div>
<!--toolbar-->
<div class="tacz" id="toolbar">
	<!-- <span class="inline_inblo mr15 pl15 bg_btn btnimg20" onclick="add()" fun="add"><span
		class="add_btn inline_inblo pl15">增加</span>
	</span>
	<span class="inline_inblo mr15 pl15 bg_btn btnimg20" onclick="update()" fun="edit"><span
		class="editor_btn inline_inblo pl15">修改</span>
	</span> -->
	<span class="inline_inblo mr15 pl15 bg_btn btnimg20" onclick="del()" fun="remove"><span
		class="del_btn inline_inblo pl15">删除</span>
	</span>
	<span class="inline_inblo mr15 pl15 bg_btn  btnimg20" id="btnfh"
		onclick="returnFid()" ><span class="return_btn inline_inblo pl15">返回</span>
	</span>
</div>
<!-- 编辑窗口   ----->
<div id="editDiv" class="hidden">
	<form action="${msUrl}/weixinHdrecord/save.do" method="post"
		class="list_con_form clearfix" id="editForm">
		
		
		<input id="type" name="type" type="hidden" class="gldint texwd160">
							<input id="id" name="id" type="hidden" class="gldint texwd160">
				
						       <p class="manage_unit clearfix mtb15">
					<span class="manage_span_name inline_inblo pl15 wd120 txtri">主键ID:</span>
					<input id="hdid" name="hdid" type="text"
						class="manage_input wd160" data-rule-required="true">
				</p>
				
						       <p class="manage_unit clearfix mtb15">
					<span class="manage_span_name inline_inblo pl15 wd120 txtri">用户唯一标识:</span>
					<input id="openid" name="openid" type="text"
						class="manage_input wd160" data-rule-required="true">
				</p>
				
						       <p class="manage_unit clearfix mtb15">
					<span class="manage_span_name inline_inblo pl15 wd120 txtri">总数:</span>
					<input id="total" name="total" type="text"
						class="manage_input wd160" data-rule-required="true">
				</p>
				
				
						       <p class="manage_unit clearfix mtb15">
					<span class="manage_span_name inline_inblo pl15 wd120 txtri">昵称:</span>
					<input id="nickname" name="nickname" type="text"
						class="manage_input wd160" data-rule-required="true">
				</p>
				
				   
		<p class="" style="margin-bottom: 30px;">
			<span class="manage_span_name inline_inblo pl15 wd120 txtri"></span>
			<input type="button" value="确定" onclick="updateSave()" id="test1"
				class="orage_bg prl20 mr15 h32">
			<input type="reset" value="取消" onclick="closePop()"
				class="bglv prl20 mr15 h32">
		</p>
	</form>
</div>

<table id="table" class="table_data"></table>


