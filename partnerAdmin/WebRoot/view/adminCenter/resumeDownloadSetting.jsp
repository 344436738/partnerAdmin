<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/headinclude.jsp" %>

<script type="text/javascript" src="${msUrl}/js/adminCenter/resumeDownloadSetting.js"></script>
<jsp:include page="../common/header.jsp" />
 <body>

	<div>
	
						<jsp:include page="../common/partnerLeft.jsp"/>
						<div style="font-size:16px;color:#333;float: left;margin-left: 20px;border-bottom: 1px solid #ccc;margin-bottom: 20px;">简历下载开关</div>
							<div style=" width: 70%; float: left;">
								<form action="${msUrl}/admin/saveDownloadSetting.do" method="post"
									class="form-horizontal form-bordered" id="downloadSettingForm">
									<input id="idperson" name="bean[0].id" type="hidden" class="gldint texwd160">
									<input id="dkeyperson" name="bean[0].dkey" type="hidden" value="个人用户下载开关" class="gldint texwd160">
									  <p class="manage_unit clearfix mtb15" style="margin:30px 0 10px 100px">
										<label class="control-label" style="font-size:16px;">个人用户下载开关</label></br>
										 <input type="radio" name="bean[0].dvalue" id="dvalueperson1" value="1" style="margin:5px;float:left;"><span style="float:left;margin:4px 0 0 0;">可下载</span>
										 <input type="radio" name="bean[0].dvalue" id="dvalueperson2" value="2" style="margin:5px;float:left;"><span style="float:left;margin:4px 0 0 0;">不能下载</span>
									  </p>	
									  
									  <input id="idcomp" name="bean[1].id" type="hidden" class="gldint texwd160">
									<input id="dkeycomp" name="bean[1].dkey" type="hidden" value="企业用户下载开关" class="gldint texwd160">
									  <p class="manage_unit clearfix mtb15" style="margin:30px 0 10px 100px">
										<label class="control-label" style="font-size:16px;">企业用户下载开关</label></br>
										 <input type="radio" name="bean[1].dvalue" id="dvaluecomp1" value="1" style="margin:5px;float:left;"><span style="float:left;margin:4px 0 0 0;">可下载</span>
										 <input type="radio" name="bean[1].dvalue" id="dvaluecomp2" value="2" style="margin:5px;float:left;"><span style="float:left;margin:4px 0 0 0;">不能下载</span>
									  </p>	
								</form>
								
								<button type="button" class="btn btn-default"  onclick="cancel()" style="margin:20px 0 0 95px;" >
										取消 
								</button>
									            
								<button type="button" onclick="saveDownloadSetting()" 
									        class="btn btn-primary" style="margin: 20px 0 0 20px;">
									   	确认
								</button>
				
							</div>
				</div>
</body>	
	