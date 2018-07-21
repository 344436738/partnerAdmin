<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
  <head>
	 <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/ueditor1_4_3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/ueditor1_4_3/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
	var ue;

	$(document).ready(function() {
	 	//实例化编辑器 
		ue = UE.getEditor('contentidUE');
		 $.validator.setDefaults({
				ignore: []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
	});
	</script>

  </head>
  
  <body>
  	<div class="panel">
  		<div class="panel-body">
		  		<div class="sub_form_list clearfix">
		      <div style="float:left;width:600px;">
			      <script id="contentidUE"  type="text/plain">
	   				 </script>
				    </div>
		    </div>
  		</div>
  	</div>
    
  </body>
</html>
