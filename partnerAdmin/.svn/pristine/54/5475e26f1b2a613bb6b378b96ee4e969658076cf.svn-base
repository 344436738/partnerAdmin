<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
  </head>
  <body class="easyui-layout">
 	 <!-- Search panel start -->
 	 <div class="ui-search-panel" region="north" style="height: 80px;" title="Search box" data-options="striped: true,collapsible:false,iconCls:'icon-search',border:false" >  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
           <label class="ui-label">Name:</label> 
           <input name="name" class="easyui-box ui-text" style="width:100px;">
        </p>
        <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">Search</a>
      </form>  
     </div> 
     <!--  Search panel end -->

     <!-- Data List -->
     <div region="center" border="false" >
     <table id="data-list"></table>
	 </div>
	 
     <!-- Edit Win&Form -->
     <div id="edit-win" class="easyui-dialog" title="Basic window" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:380px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		 <div class="ui-edit">
		     	   <div class="ftitle">Site Information</div>    
		           <div class="fitem">  
		               <label>Name:</label>  
		               <input class="easyui-validatebox" type="text" name="name" data-options="required:true">
		           </div>  
		            <div class="fitem">  
		               <label>Domain:</label>  
		               <input class="easyui-validatebox" type="text" name="domain" data-options="required:true">
		           </div> 
		            <div class="fitem">  
		               <label>Link:</label>  
		               <input class="easyui-validatebox" type="text" value="http://" name="link" data-options="required:true,validType:'url'">
		           </div> 
		           <div class="fitem">  
		               <label>Rank:</label>  
		               <input class="easyui-numberbox" type="text" value="0" name="rank" data-options="required:true,min:0,max:999">
		           </div> 
		           <div class="fitem">  
		               <label>State:</label>  
		               <select class="easyui-combobox" name="state" data-options="required:true">
	                    	<option value="0" selected="selected">可用</option>
	                    	<option value="1">禁用</option>
                    	</select>
		           </div> 
		           <div class="fitem">  
		               <label>Description:</label>  
		               <textarea class="easyui-validatebox" data-options="required:false,length:[0,100]" name="descr"></textarea>
		           </div> 
		           <div class="fitem">  
		               <label>Types:</label>  
		               <input id='typeIds_combobox' class="easyui-combobox" 
							name="typeIds"
							data-options="
								url:'${msUrl}/siteType/typeListJson.do',
								valueField:'id',
								textField:'name',
								multiple:true,
								panelHeight:'auto'">
		           </div> 
		           <div class="fitem">  
		               <label>Pic:</label>  
		               <input class="easyui-validatebox" type="text" name="pic" data-options="required:false,validType:'url'">
		           </div>
	         </div>
     	</form>
  	 </div>
<script type="text/javascript" src="${msUrl}/js/ux/siteMain/siteMain.js"></script>
  </body>
</html>
