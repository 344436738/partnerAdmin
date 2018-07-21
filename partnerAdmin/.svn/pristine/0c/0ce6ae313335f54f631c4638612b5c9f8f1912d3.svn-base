<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/headinclude.jsp" %>

<script type="text/javascript" src="${msUrl}/js/adminCenter/scoreExchangeRule.js"></script>
<jsp:include page="../common/header.jsp" />
 <body>

	<div>
	
			<jsp:include page="../common/partnerLeft.jsp"/>
			
				
				<div style=" width: 70%; float: left;">
					<form action="${msUrl}/admin/saveScoreRule.do" method="post"
						class="form-horizontal form-bordered" id="exchangeruleForm">
						<input id="id" name="id" type="hidden" class="gldint texwd160">
						<input id="dkey" name="dkey" type="hidden" value="积分兑换规则" class="gldint texwd160">
						  <p class="manage_unit clearfix mtb15">
							<label class="control-label">1积分=</label>
							 <input id="dvalue" name="dvalue" >元
						  </p>		
					</form>
					
					<button type="button" class="btn btn-default"  onclick="cancel()" style="margin-left: 20px;" >
							取消 
					</button>
						            
					<button type="button" onclick="saveExchangeRule()" 
						        class="btn btn-primary" style="margin-left: 20px;">
						   	确认
					</button>
		
			</div>
		</div>	
	</body>	