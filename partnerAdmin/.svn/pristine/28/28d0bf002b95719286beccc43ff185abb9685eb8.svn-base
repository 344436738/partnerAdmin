<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>简历上传</title>
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/upload/uploadFile.css"
			type="text/css"></link>
		  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/upload/hestyle.css">
  		<script type="text/javascript" src="${pageContext.request.contextPath}/js/hecoyote.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery.uploadify.v2.1.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/swfobject.js"></script>
		
	</head>
	<script type="text/javascript">
 var fileCount=0;
 $(document).ready( function() {
	var failCount=0;
	var failistrue=0;
	var fileSuccess=0;
     $("#uploadify").uploadify( {//初始化函数
	    'uploader' :"${pageContext.request.contextPath}/default/uploadify.swf",//flash文件位置，注意路径
	    'script' :"${pageContext.request.contextPath}/partnerCenter/addUpload.do",//后台处理的请求
	    'cancelImg' :'${pageContext.request.contextPath}/default/cancel.png',//取消按钮图片
	    'queueID' :'fileQueue',//与下面的上传文件列表id对应
	    'queueSizeLimit' :20,//上传文件的数量
	    'fileDesc' :'txt文件或doc文件或docx文件',//上传文件类型说明
	    'fileExt' :'*.txt;*.doc;*.docx;', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
	    'formData':{'mgid':'${mgid}'},   //发送给后台的其他参数
	    'method':'get',//如果向后台传输数据，必须是get
	    'sizeLimit':0,//文件上传的大小限制，单位是字节
	    'auto' :false,//是否自动上传
	    'multi' :true,//设置为true时可以上传多个文件。
	    'onInit':function(){}, 
	    'simUploadLimit' :20,//同时上传文件的数量
	    'buttonText' :'selectFile',//浏览按钮图片
	    //'scriptAccess':'always',
	     'buttonImg':'${pageContext.request.contextPath}/default/selectbt.jpg',   
	    'overrideEvents': ['onSelectError', 'onDialogClose'],
	    'onError':function(event, queueID, fileObj,serverData,data){
	    		alert(data);
	    },
	    'onComplete': function(event, queueID, fileObj,response,data) {//当上传完成后的回调函数
	     var json = eval('('+response+')');
	     if(json.success==false){
	       if(json.msg!="存在此简历"){
		      failCount=failCount+1;
	      }else{
	      	  failistrue=failistrue+1;
	      }
	      }else{
	       fileSuccess=fileSuccess+1;
	      }
	    },
	    'onSelectOnce': function (a, b) {
         fileCount = b.fileCount;
      },
      'onCancel': function (a, b, c, d) {
         fileCount = d.fileCount;
      },
      'onClearQueue': function (a, b) {
         fileCount = b.fileCount;
      },
      'onAllComplete':function(event, data){
       		   fileCount = 0;
       		   var fileSuccessMg="";
       		   if(failistrue!=0){
       		  	 fileSuccessMg="\n有"+failistrue+"个文件已经存在!";
       		   }
       		   var msg="本次共上传"+(fileSuccess+failCount+failistrue)+"份简历!\n共有"+fileSuccess+"份上传成功!\n有"+failCount+"个文件解析失败!"+fileSuccessMg;
       		   alert(msg);
     		   failCount=0;
			   failistrue=0;
	 		   fileSuccess=0;
	   		   fileCount==0;
	    }
   });
});


function upload(){
	 if(fileCount==0){
	       alert("请先点击选择上传按钮进行简历上传!");
	       return false;
	 }
	jQuery('#uploadify').uploadifySettings("scriptData", {'batchid':'1','mgid':'${mgid}'});
	jQuery('#uploadify').uploadifyUpload();	
}


</script>
	<body>
    <div class="fl w978">
    	<div class="panel">		
			<div class="clearfix mb20 pb15"  id="first">
			
			<div class="panel-top">
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link addload" href="javascript:void(0);"
								onclick="return upload()">开始上传简历</a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link delload"
								href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消上传简历</a>
						</div>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<div style="border: solid #EEE 0px; width: 630px;">
					<span style="color: green; line-height: 20px;">
						提示：1、可同时批量上传(20份)txt,doc,docx格式的文件,若上传过程中信息解析失败,可在列表中手动进行内容修改<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						2、先点击“选择文件”再点击“开始上传简历”,若无法使用上传功能请使用“<span style="color: red">IE7.0版本以上的浏览器</span>”重试<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						3、如果未安装Adobe Flash Player,请先安装，安装之后再打开本页面重试。 </span>
				</div>
				<div align="left">
					<div id="fileQueue"
						style="width: 900px; height: 400px; border-color: #8dc2e3 1px solid">
						<input type="file" name="uploadify" id="uploadify"  multiple="true"/>
					</div>
					<input id="save" type="button" style="display: none;" value="保存"  />
				</div>
			</div>
		</div>
    </div>
<script type="text/javascript">
  var aLisA=getByInClass(document,'chanliscol');
  for(var i=0;i<aLisA.length;i++)
  {
    aLisA[i].onclick=function()
    {
      for(var j=0;j<aLisA.length;j++)
      {
        aLisA[j].className=removeNameClass(aLisA[j],'acthelisa');
      }
      this.className=addClass(this,'acthelisa');
    }
  }
</script>
<!-- 内容结束 -->
	</body>
</html>


