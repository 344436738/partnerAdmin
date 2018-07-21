<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
  <head>
	<script type="text/javascript">
	function aa() {
		layer.open({
		    type: 1,
		    skin: 'layui-layer-demo', //样式类名
		    closeBtn: false, //不显示关闭按钮
		    shift: 2,
		    shadeClose: true, //开启遮罩关闭
		    content: '斯蒂芬斯蒂芬斯蒂芬岁的斯蒂芬斯蒂芬斯蒂芬斯蒂芬斯蒂芬斯蒂芬斯蒂芬斯蒂芬蒂芬森的'
		});
	}
	
	function bb() {
		layer.msg('玩命提示中');
	}

	function cc() {
		layer.confirm('您是如何看待前端开发？', {
		    btn: ['重要','奇葩'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.msg('的确很重要', {shift: 6});
		}, function(){
		    layer.msg('奇葩么么哒', {shift: 6});
		});
	}

	function dd() {
		var index = layer.load(1, {
		    shade: [0.1,'#fff'] //0.1透明度的白色背景
		});
	}


	
	</script>
  </head>
  
  <body>
  <p style="margin-top: 100px;" align="center">
	  <input type="button" value="自定页" onclick="aa()" class="orage_bg prl20 mr15 h32">
	  <input type="button" value="提示层" onclick="bb()" class="orage_bg prl20 mr15 h32">
	  <input type="button" value="询问框" onclick="cc()" class="orage_bg prl20 mr15 h32">
	  <input type="button" value="loading层" onclick="dd()" class="orage_bg prl20 mr15 h32">
  </p>
  </body>
</html>
