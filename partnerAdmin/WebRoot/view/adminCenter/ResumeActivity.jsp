<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/headinclude.jsp" %>
<link type="text/css" rel="stylesheet" href="${msUrl}/css/admin.css" />
<link rel="stylesheet" type="text/css" href="${msUrl}/css/jquery-ui.css" />
<script type="text/javascript" src="${msUrl}/js/jquery-ui-1.10.4.custom.min.js"></script>
<script type="text/javascript" src="${msUrl}/js/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${msUrl}/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="${msUrl}/js/jquery-ui-timepicker-zh-CN.js"></script>
<style type="text/css">
.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
.ui-timepicker-div dl { text-align: left; }
.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; }
.ui-timepicker-div dl dd { margin: 0 10px 10px 45%; }
.ui-timepicker-div td { font-size: 90%; }
.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }

.ui-timepicker-rtl{ direction: rtl; }
.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
.ui-timepicker-rtl dl dt{ float: right; clear: right; }
.ui-timepicker-rtl dl dd { margin: 0 45% 10px 10px; }
.ui-state-default{width:32px;heught:32px;}
.ui-datepicker-month{
	float: none !important;
    color: #fff !important;
    font-size:none !important;
    line-height: normal !important;
    font-size:none !important;
}
.ui-datepicker-year{
float: none !important;
    color: #fff !important;
    line-height: normal !important;
}
.ui-datepicker-calendar thead tr span{
float: none !important;
 
    line-height: normal !important;
}
.ui-datepicker{
	width:18em !important;
}

</style>


<script type="text/javascript" src="${msUrl}/js/adminCenter/ResumeActivity.js"></script>
<jsp:include page="../common/header.jsp" />
 <body>
<style>
span{float:left;font-size:14px;color:#333;line-height: 34px;width:100px;}
.box{float:left;clear:both;margin:10px 0 10px 200px;}
input{float:left;margin-left:10px;height:34px;}
</style>
	<div>
	
			<jsp:include page="../common/partnerLeft.jsp"/>
			<div style="font-size:16px;color:#333;float: left;margin-left: 20px;border-bottom: 1px solid #ccc;margin-bottom: 20px;">简历活动管理</div>
				
				<div style=" width: 70%; float: left;margin:15px;float:left;overflow:hidden;margin-bottom: 20px;">
					<form action="${msUrl}/admin/saveUploadResumeActivity.do" class="form-horizontal form-bordered form" id="uploadScoreForm" method="POST" enctype="multipart/form-data" >
						<div class="box"><span>活动标题:</span><input id="title" name="title" type="text" class="gldint texwd160 cs"></div>
						<div class="box"><span>活动首页图:</span><input id="homeimgUrl" name="homeimgUrl" type="file" class="gldint texwd160"></div>
						<div class="box"><span>活动开始时间:</span>    <input  type="text" class="text-box gldint texwd160 cs" value="" placeholder="开始时间≥当前时间" title="开始时间≥当前时间"  style="cursor:pointer;" name="t5" /></div>
						<div class="box"> <span>活动结束时间:</span>  <input type="text" class="text-box gldint texwd160 cs" value="" placeholder="结束时间>开始时间" title="结束时间>开始时间"  style="cursor:pointer;" name="t6" /></div>
			
						
						
						<div class="box"><span>简历上传数:</span><input id="uploadleast" name="uploadleast" type="text" class="gldint texwd160 cs"></div>
						<div class="box"><span>活动简述:</span><textarea id="intro" name="intro" type="text" class="gldint texwd160 cs" style="width:340px;height:200px;margin-left:10px;"></textarea></div>
						<div class="box"><span>活动介绍:</span><textarea id="introContent" name="introContent" type="text" class="gldint texwd160 cs" style="width:340px;height:200px;margin-left:10px;"></textarea></div>
						<div class="box"><span>适用行业:</span><input id="industryid" name="industryid" type="text" class="gldint texwd160 cs"></div>
						
						<div class="box"><span>报名截止时间:</span><input  name="t1" type="text" class="gldint texwd160 cs"  placeholder="开始时间≥当前时间" title="开始时间≥当前时间"  style="cursor:pointer;" ></div>
						<div class="box"><span>上传开始时间:</span><input  name="t2" type="text"  class="text-box gldint texwd160 cs" value="" placeholder="开始时间≥当前时间" title="开始时间≥当前时间"  style="cursor:pointer;"></div>
						<div class="box"><span>上传截止时间:</span><input  name="t3" type="text"  class="text-box gldint texwd160 cs" value="" placeholder="开始时间≥当前时间" title="开始时间≥当前时间"  style="cursor:pointer;"></div>
						<div class="box"><span>下载开始时间:</span><input  name="t4" type="text"  class="text-box gldint texwd160 cs" value="" placeholder="开始时间≥当前时间" title="开始时间≥当前时间"  style="cursor:pointer;"></div>
					</form>
					
					<button type="button" class="btn btn-default"  onclick="cancel()"  style="margin-left: 240px;clear:both;float:left;margin-top:20px;">
							取消 
					</button>
						            
					<button type="button" onclick="saveDownloadSetting()" 
									        class="btn btn-primary" style="margin-left: 20px;float:left;margin-top:20px;">
									   	确认
					</button>
			</div>
	</div>	
	<script type="text/javascript">
	$( "input[name='t5'],input[name='t6'],input[name='t4'],input[name='t3'],input[name='t2'],input[name='t1']" ).datetimepicker();
</script>
		
</body>