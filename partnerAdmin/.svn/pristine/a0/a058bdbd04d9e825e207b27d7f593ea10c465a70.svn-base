<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 公共资源CSS,JS  -->
<!--Css -->

	<link rel="stylesheet" type="text/css" href="${msUrl}/css/bootstraptable/layer.css">
	<link rel="stylesheet" type="text/css" href="${msUrl}/css/bootstraptable/laycode.css">
	<link rel="stylesheet" href="${msUrl}/css/bootstraptable/bootstrap.min.css">
	<link rel="stylesheet" href="${msUrl}/css/bootstraptable/bootstrap-table.css">
	<link rel="stylesheet" type="text/css" href="${msUrl}/css/hebase.css">
  	<link rel="stylesheet" type="text/css" href="${msUrl}/css/liststyle.css">
  	<link rel="stylesheet" type="text/css" href="${msUrl}/css/valid.css">

<!-- ** Javascript ** -->
	<script src="${msUrl}/js/jquery-1.9.1.js"></script>
	<script src="${msUrl}/js/bootstraptable/layer.js"></script>
	<script src="${msUrl}/js/bootstraptable/laycode.js"></script>
    <script src="${msUrl}/js/bootstraptable/bootstrap.min.js"></script>
    <script src="${msUrl}/js/bootstraptable/bootstrap-table.js"></script>
    <script src="${msUrl}/js/bootstraptable/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript" src="${msUrl}/js/bootstraptable/bootstrap-table-export.js"></script>
	<script type="text/javascript" src="${msUrl}/js/bootstraptable/export-plugin/tableExport.js"></script>
	<script type="text/javascript" src="${msUrl}/js/bootstraptable/export-plugin/jquery.base64.js"></script>
	<script type="text/javascript">$.base64.utf8encode = true;</script>  
	<script type="text/javascript" src="${msUrl}/js/bootstraptable/export-plugin/html2canvas.js"></script>
	<script type="text/javascript" src="${msUrl}/js/laydate/laydate.dev.js"></script>
	
	
	<script src="${msUrl}/js/jquery-form.js"></script>
   	<script type="text/javascript" src="${msUrl}/js/jquery-ui.js"></script>
   	<script type="text/javascript" src="${msUrl}/js/jquery.formFill.js"></script>
   	
   	<script type="text/javascript" src="${msUrl}/js/validate/jquery.validate.js"></script>
   	<script type="text/javascript" src="${msUrl}/js/validate/valadate-message.js"></script>
   	
   	<script type="text/javascript">
	$.validator.setDefaults({
		ignoreTitle:'ignoreTitle',
		showErrors: function(map, list) {
			this.currentElements.removeAttr("title").removeClass("error");
			var tip_option={
					animation:true,
					placement:'right',
					trigger:'manual '
			}
			$.each(list, function(index, error) {
				$(error.element).removeAttr("title").removeClass("error");
				$(error.element).removeAttr("data-original-title");
				$(error.element).attr("title", error.message);
				$(error.element).attr("data-original-title", error.message);
				$(error.element).addClass("error");
				$(error.element).tooltip(tip_option).tooltip('show');
				 //layer.tips(error.message,error.element, {
				//    tips: [2, '#78BA32'],
				//	tipsMore: true
				//});
			}); 
		},
		focusCleanup:true,
		unhighlight: function(element,errorClass,validClass){
			$(element).tooltip('destroy');
		}
	});
</script>
<style>
.tooltip-inner{
	background-color: red;
}
.tooltip.right .tooltip-arrow{
	border-right-color:red;
}
</style>
