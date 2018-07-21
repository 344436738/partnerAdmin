<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

	<script type="text/javascript">
	function haha() {
		
		$.ajax({
	        url:"exceptionDemo/exceptionJson.do",
	        data:$("#formId").serialize(),
	        type:"post",
	        success:function(data){
				if(data.success){
					//因为是异常示范页，所以不会处罚这里的代码
				}else{
					layer.msg("系统错误，请稍后再试");
				}
	        }
	   })
	   
	}
	</script>

  <div class="panel"><div class="panel-body">
  	<div style="margin-top: 30px;" >
	    <form action="exceptionDemo/exceptionPage.do" method="post">	    
	    	<input type="submit" value="页面异常" class="btn btn-primary"/>
	    </form>
    </div>
    
    <div style="margin-top: 30px;" >
	    <form action="" method="post" id="formId">  
	    	<input type="button" value="异步请求异常" onclick="haha()" class="btn btn-primary"/>
	    </form>
    </div>
</div></div>