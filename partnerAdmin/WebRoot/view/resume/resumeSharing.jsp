<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${msUrl}/js/stBusinessCenter/cooperation.js"></script>

<div class="panel">
    <label class="control-label">简历共享活动第</label> 
	<input id="id" name="id" type="hidden" class="gldint texwd160">
    <label class="control-label">期</label><!--id-->（
	<input id="startdate" name="startdate" type="hidden" class="gldint texwd160"> <!--活动起始日期-->-	
	<input id="enddate" name="enddate" type="hidden" class="gldint texwd160"> <!--活动结束日期-->）
	<input id="homeimg" name="homeimg" type="hidden" class="gldint texwd160"> <!--活动首页图-->
<div class="panel">
	<div class="panel-heading">
		<input id="title" name="title" type="hidden" class="gldint texwd160"><!--活动标题-->（
		<input id="startdate" name="startdate" type="hidden" class="gldint texwd160"> <!--活动起始日期-->-	
		<input id="enddate" name="enddate" type="hidden" class="gldint texwd160"> <!--活动结束日期-->）
		<p class="clearfix mtb8">
			<label class="control-label">活动规则：</label>
			<input id="intro" name="intro" type="hidden" class="gldint texwd160"> <!--活动简述-->
			<input id="uploadleast" name="uploadleast" type="hidden" class="gldint texwd160"> <!--简历上传数-->
		    <input id="introContent" name="introContent" type="hidden" class="gldint texwd160"> <!--活动介绍-->
		    <input id="uploadnum" name="uploadnum" type="hidden" class="gldint texwd160"> <!--简历上传数目-->
		    <input id="industryid" name="industryid" type="hidden" class="gldint texwd160"> <!--适用行业-->
		</p>
		
	</div>
	<div class="panel-heading">
		<label class="control-label">企业确认同意：</label>
		<input type="text" id="confirm" name="confirm" />
		<label class="control-label">请手动敲入“我同意以上规则”</label>
	</div>
	<div class="modal-footer">
        <button type="button" onclick="resumeSave()" 
            class="btn btn-primary">
            	同意</button>
        <button type="button" class="btn btn-default" 
            data-dismiss="modal" >
                                                        取消 </button>
    </div>	
</div></div>