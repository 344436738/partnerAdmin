<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 加载编辑器的容器 -->	   
<script type="text/javascript" charset="utf-8" src="${msUrl}/ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${msUrl}/ueditor1_4_3/ueditor.all.min.js"> </script>

<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->

<script type="text/javascript" charset="utf-8" src="${msUrl}/ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
<style type="text/css">
	#preview{width:100px;height:80px;overflow:hidden;}
	#imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
</style>  

<script type="text/javascript">
var ue;

$(document).ready(function() {
 	//实例化编辑器 
	ue = UE.getEditor('contentidUE');
	 $.validator.setDefaults({
			ignore: []
		});//如果有display:none的情况，加上这个，会验证display none的必填项
	 $("#editForm").validate();
});

<c:if test="${id!=null}">
	$(document).ready(function() {
		 //load数据
		 var loading=layer.load(2);
		$.post("siteNews/getId.do",{id:'${id}'},function(result){
			if(result.success){
				$("#editForm").fill(result.data); 
				ue.setContent(result.data.newsData==null?"":result.data.newsData.content);
				//ue.addListener("ready", function () {
				//	ue.setContent(result.data.newsData==null?"":result.data.newsData.content);
				//});
				layer.close(loading);
			}else{
				layer.msg(result.msg, {tips:2});
				layer.close(loading);
			}
		},"json").error(function(xhr, status, text){
			if (xhr.status == "504") {
				layer.msg("登录超时，请重新登录", {
					tips : 2
				}, function() {
					location.reload();
				});
			}else{
				layer.msg("系统错误，请稍后再试", {
					tips : 2
				});
			}
		});
	})
</c:if>
//编辑
function updateSave(){
	if($("#editForm").valid()){
	var option = {
			dataType:'json',
			success:function(data){ 
				data=eval(data);
				if(data.success){
					//$("#right").show();
					//$("#descdiv").hide();
					//$('#dg').datagrid('load');
					//layer.msg('保存成功', {tips:2});
					layer.msg(data.msg, {
					    icon: -1,
					   //time: 1000 //2秒关闭（如果不配置，默认是3秒）
					}, function(){
						loadPage("${msUrl}/siteNews/childColumnList.shtml");
					});  				
				}else{
					layer.msg(data.msg, {tips:2});
				}
			},
			error: function(xhr, status, text){ 
				if (xhr.status == "504") {
					layer.msg("登录超时，请重新登录", {
						tips : 2
					}, function() {
						location.reload();
					});
				}else{
					layer.msg("系统错误，请稍后再试", {
						tips : 2
					});
				} 
			},
		};
		$("#editForm").ajaxSubmit(option);
	}
	}
	//关闭窗口
	function closePop(){
		loadPage("${msUrl}/siteNews/childColumnList.shtml");
	}
	//预览新闻内容
	function pvw(){
	    $("#editForm").attr("target", "_blank"); 
	    $("#editForm").attr("action", "${msUrl}/siteNews/preview.do"); 
	    $("#editForm").submit();
	}	

	//图片上传预览    IE是用了滤镜。
    function previewImage(file)
    {
      var MAXWIDTH  = 260; 
      var MAXHEIGHT = 180;
      var div = document.getElementById('preview');
      if (file.files && file.files[0])
      {
          div.innerHTML ='<img id=imghead>';
          var img = document.getElementById('imghead');
          img.onload = function(){
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width  =  rect.width;
            img.height =  rect.height;
//             img.style.marginLeft = rect.left+'px';
            img.style.marginTop = rect.top+'px';
          }
          var reader = new FileReader();
          reader.onload = function(evt){img.src = evt.target.result;}
          reader.readAsDataURL(file.files[0]);
      }
      else //兼容IE
      {
        var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
        div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
      }
    }

    function clacImgZoomParam( maxWidth, maxHeight, width, height ){
        var param = {top:0, left:0, width:width, height:height};
        if( width>maxWidth || height>maxHeight )
        {
            rateWidth = width / maxWidth;
            rateHeight = height / maxHeight;
            
            if( rateWidth > rateHeight )
            {
                param.width =  maxWidth;
                param.height = Math.round(height / rateWidth);
            }else
            {
                param.width = Math.round(width / rateHeight);
                param.height = maxHeight;
            }
        }       
        param.left = Math.round((maxWidth - param.width) / 2);
        param.top = Math.round((maxHeight - param.height) / 2);
        return param;
    }
	
</script>
    
<body>
  <h3 class="litit ft16 ftno">新闻信息编辑页</h3>
  <form action="${msUrl}/siteNews/save.do" id="editForm" method="post" enctype="multipart/form-data">
      <input type="hidden"   id="id" name="id" />
      <input type="hidden"  id="contentid" name="contentid" />
      
	<p class="sub_form_list clearfix">
      <span class="sub_list_name">栏目：</span>
		<select class="manage_input wd160" name="pid" id="pid">
		   <c:forEach items="${columnList}" var="column">
			 <option value="${column.id}" style="font-weight:bold;">&nbsp;&nbsp;${column.name}</option>
			 	  <c:forEach items="${column.childList}" var="child">
				 		<option value="${child.id}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${child.name}</option>
				  </c:forEach>
		   </c:forEach>
		</select>
    </p>
    
    <p class="sub_form_list clearfix">
      <span class="sub_list_name">标题：</span>
      <input type="text" class="sub_text" name="title" data-rule-required="true" data-rule-maxlength="20"/>
    </p>
   
	<p class="sub_form_list clearfix">
		<span id="preview">
			<img id="imghead" width=80 height=80 border=0 src='<%=request.getContextPath()%>/images'>
		</span>
		<span class="sub_list_name">封面图：</span>
		<input type="file" class="sub_text" name="file" onchange="previewImage(this)">
		<input type="hidden" name="pic" />
	</p>
    
	<p class="sub_form_list clearfix">
    	<span class="sub_list_name">摘要：</span>
     	<input type="text" class="sub_text" name="summary" data-rule-required="true" data-rule-maxlength="150">
	</p>
	
	<div class="sub_form_list clearfix">
		<span class="sub_list_name">内容：</span>
		<div style="float:left;width:600px;">
			<script id="contentidUE" name="newsData.content" type="text/plain">
	    	</script>
	    </div>
	</div>
    
    <p class="sub_form_list clearfix" id="xibie">
		<span class="sub_list_name">状态：</span>
		<input name="status" type="radio" checked="checked" value="0">
		<span>可用</span>
		<input name="status" type="radio" value="1">
		<span>禁用</span>
	</p>		
  </form>
  
	<p class="btinp" style="text-align:center;">
		<input type="button" value="确定" onclick="updateSave()" id="test1" class="orage_bg prl20 sub_detail_btn h32">
		<input type="reset" value="取消" onclick="closePop()" class="bglv prl20 sub_detail_btn h32">
		<input type="reset" value="预览" onclick="pvw()" class="bglv prl20 sub_detail_btn h32">
	</p>
	
	<div class="manage_bot">
		<p class="copyright ft12">All Rights Reserved. Copyright 2015 XX科技有限股份有限公司</p>
	</div>
	 
</body>