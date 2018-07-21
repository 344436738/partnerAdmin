<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<script type="text/javascript">
	function showValue(){
		
		$.post("redisDemo/getRedisInfo.do",{key:$('#key').val()},function(data){
			alert("value："+data.value);
		},"json");
	}
	
	</script>

  
  <div class="panel">
  		<div class="panel-body">
    <form action="redisDemo/insertRedis.do" method="post" class="form-inline" style="margin-top: 30px;">
    	<span class="manage_unit clearfix">
 			<span class="manage_span_name">key：</span> <input type="text" name="key" class="form-control"/>
 		</span>
 		<span class="manage_unit clearfix">
 			<span class="manage_span_name">value：</span> <input type="text" name="value" class="form-control"/>
 		</span>
 		<input type="submit" value="存值" class="btn btn-primary"/>
 		
    </form>
    
    <form action="redisDemo/getRedisInfo.do" method="post"  class="form-inline" style="margin-top: 30px;margin-bottom: 30px;">
 		<span class="manage_unit clearfix">
 			<span class="manage_span_name">key：</span>
 				<input type="text" name="key" id="key" class="form-control"/>
 		</span>
 		<input type="button" onclick="showValue()" value="取值" class="btn btn-primary"/>
    </form>
  	<h4>当前redis配置地址：</h4><br />
  	<table align="center" class="table">
  		<tr>
  			<td>IP地址：</td><td>${host}</td>
  		</tr>
  		<tr>
  			<td>端口号：</td><td>${port}</td>
  		</tr>
  		<tr>
  			<td>最大能保持idle的状态的对象数：</td><td>${maxIdle}</td>
  		</tr>
  		<tr>
  			<td>最大分配的对象数：</td><td>${maxTotal}</td>
  		</tr>
  		<tr>
  			<td>多长时间检查连接池中空闲的连接：</td><td>${timeBetweenEvictionRunsMillis }</td>
  		</tr>
  		<tr>
  			<td>空闲连接多长时间被收回：</td><td>${minEvictableIdleTimeMillis}</td>
  		</tr>
  		<tr>
  			<td>当调用borrow Object方法时，是否进行有效性的检查：</td><td>${testOnBorrow }</td>
  		</tr>
  		<tr>
  			<td>redis编码格式：</td><td>${encode}</td>
  		</tr>
  		<tr>
  			<td>缓存过期时间  秒：</td><td>${expire }</td>
  		</tr>
  		<tr>
  			<td>是否开始reids应用服务：</td><td>${unlock }</td>
  		</tr>
  	</table>
 
 </div></div>
