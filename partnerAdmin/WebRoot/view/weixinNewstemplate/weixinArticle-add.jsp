<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
 <head>
  <title>微信单图消息</title>
  <link type="text/css" rel="stylesheet" href="${msUrl}/css/weixin/appmsg_edit.css" />
  <link type="text/css" rel="stylesheet" href="${msUrl}/css/weixin/jquery.fileupload.css" />
  <link rel="stylesheet" href="${msUrl}/css/bootstraptable/bootstrap.min.css">
  
  	<link rel="stylesheet" type="text/css" href="${msUrl}/css/hebase.css">
  	<link rel="stylesheet" type="text/css" href="${msUrl}/css/liststyle.css">
  <link type="text/css" rel="stylesheet" href="${msUrl}/css/weixin/easyui.css" />
  <link type="text/css" rel="stylesheet" href="${msUrl}/css/weixin/icon.css" />
   <link type="text/css" rel="stylesheet" href="${msUrl}/css/weixin/common.css" />
   <link type="text/css" rel="stylesheet" href="${msUrl}/css/weixin/accordion.css" />
   
  <script type="text/javascript" src="${msUrl}/js/weixin/ckfinder.js"></script>
  <script src="${msUrl}/js/jquery-1.9.1.js"></script>
  <!--jquery-form-->
  <script src="${msUrl}/js/jquery-form.js"></script>
   	<script type="text/javascript" src="${msUrl}/js/jquery-ui.js"></script>
   	<script type="text/javascript" src="${msUrl}/js/jquery.formFill.js"></script>
   	 <!--validate-->
    <script type="text/javascript" src="${msUrl}/js/validate/jquery.validate.js"></script>
   	<script type="text/javascript" src="${msUrl}/js/validate/valadate-message.js"></script> 
   	 <!--layer-->
   	 <link rel="stylesheet" type="text/css" href="${msUrl}/css/bootstraptable/layer.css">
	<link rel="stylesheet" type="text/css" href="${msUrl}/css/bootstraptable/laycode.css">
   	<script src="${msUrl}/js/bootstraptable/layer.js"></script>
	<script src="${msUrl}/js/bootstraptable/laycode.js"></script>
   	  <!--fileupload-->
  <script type="text/javascript" src="${msUrl}/js/weixin/jquery.ui.widget.js"></script>
  <script type="text/javascript" src="${msUrl}/js/weixin/load-image.min.js"></script>
  <script type="text/javascript" src="${msUrl}/js/weixin/jquery.fileupload.js"></script>
  <script type="text/javascript" src="${msUrl}/js/weixin/jquery.fileupload-process.js"></script>
  <script type="text/javascript" src="${msUrl}/js/weixin/jquery.fileupload-image.js"></script>
  <script type="text/javascript" src="${msUrl}/js/weixin/jquery.iframe-transport.js"></script>
  <!--UEditor-->
 	 <script type="text/javascript" charset="utf-8" src="${msUrl}/ueditor1_4_3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${msUrl}/ueditor1_4_3/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${msUrl}/ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
  
  <script type="text/javascript">
  //编写自定义JS代码	
/*jslint unparam: true, regexp: true */
/*global window, $ */
$(function () {
    'use strict';
    // Change this to the location of your server-side upload handler:
    var url = '${msUrl}/weixinNewsitem/upload.do',
        uploadButton = $('<button/>')
            .addClass('btn btn-primary')
            .prop('disabled', true)
            .text('上传中...')
            .on('click', function () {
                var $this = $(this), data = $this.data();
                $this.off('click').text('正在上传...').on('click', function () {
                        $this.remove();
                        data.abort();
                });
                data.submit().always(function () {
                    $this.remove();
                });
            });
    $('#fileupload').fileupload({
        url: url,
        dataType: 'json',
        autoUpload: false,
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 2000000, // 2 MB
        disableImageResize: /Android(?!.*Chrome)|Opera/
            .test(window.navigator.userAgent),
        previewMaxWidth: 290,
        previewMaxHeight: 160,
        previewCrop: true
    }).on('fileuploadadd', function (e, data) {
        $("#files").text("");
        data.context = $('<div/>').appendTo('#files');
        $.each(data.files, function (index, file) {
            //var node = $('<p/>').append($('<span/>').text(file.name));
            //fileupload
            var node = $('<p/>');
            if (!index) {
                node.append('<br>').append(uploadButton.clone(true).data(data));
            }
            node.appendTo(data.context);
        });
    }).on('fileuploadprocessalways', function (e, data) {
        var index = data.index,
            file = data.files[index],
            node = $(data.context.children()[index]);
        if (file.preview) {
            node.prepend('<br>').prepend(file.preview);
        }
        if (file.error) {
            node
                .append('<br>')
                .append($('<span class="text-danger"/>').text(file.error));
        }
        if (index + 1 === data.files.length) {
            data.context.find('button')
                .text('上传')
                .prop('disabled', !!data.files.error);
        }
    }).on('fileuploadprogressall', function (e, data) {
        var progress = parseInt(data.loaded / data.total * 100, 10);
        $('#progress .progress-bar').css(
            'width',
            progress + '%'
        );
    }).on('fileuploaddone', function (e, data) {
        console.info(data);
        var  file = data.files[0];
        //var delUrl = "<a class=\"js_removeCover\" onclick=\"return false;\" href=\"javascript:void(0);\">删除</a>";
        $("#imgName").text("").append(file.name);
        $("#progress").hide();
		var d =data.result;
		if (d.success) {
			var link = $('<a>').attr('target', '_blank').prop('href', d.attributes.viewhref);
        	$(data.context.children()[0]).wrap(link);
        	console.info(d.attributes.viewhref);
        	$("#imagePath").val(d.attributes.url);
		}else{
			var error = $('<span class="text-danger"/>').text(d.msg);
            $(data.context.children()[0]).append('<br>').append(error);
		}
    }).on('fileuploadfail', function (e, data) {
        $.each(data.files, function (index, file) {
            var error = $('<span class="text-danger"/>').text('File upload failed.');
            $(data.context.children()[index])
                .append('<br>')
                .append(error);
        });
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
});
</script>
  
 </head>
 <body>
 	<div class="main_bd">
 		<div class="media_preview_area">
				<div class="appmsg editing">
					<div class="appmsg_content" id="js_appmsg_preview">
							<h4 class="appmsg_title">
								<a target="_blank" href="javascript:void(0);"
									onclick="return false;">标题</a>
							</h4>
							<div class="appmsg_info">
								<em class="appmsg_date"></em>
							</div>
							<div id="files" class="files">
								<i class="appmsg_thumb default">封面图片</i>
							</div>
							 <div id="progress" class="progress">
						        <div class="progress-bar progress-bar-success"></div>
						    </div>
							<p class="appmsg_desc"></p>
					</div>
				</div>
		</div>
 
 		<div class="media_edit_area" id="js_appmsg_editor">	
 			<div class="appmsg_editor" style="margin-top: 0px;">
	 			<div class="inner">
		  		<form id="editForm"  action="${msUrl}/weixinNewsitem/save.do" method="post">
							<input id="id" name="id" type="hidden" value="${weixinArticlePage.id }">
							<input id="templateId" name="templateId" type="hidden" value="${templateId}">
							<input id="createDate" name="createDate" type="hidden" value="${weixinArticlePage.createDate }">
						
						 <p class="manage_unit clearfix mtb15">
							<span class="manage_span_name inline_inblo pl15 wd120 txtri">标题:</span>
							<input id="title" name="title" type="text"
								class="manage_input wd250" data-rule-required="true">
						</p>
						
						 <p class="manage_unit clearfix mtb15">
							<span class="manage_span_name inline_inblo pl15 wd120 txtri">作者:</span>
							<input id="author" name="author" type="text"
								class="manage_input wd250" data-rule-required="true">
						</p>
						
						 <p class="sub_form_list clearfix">
						      <span class="sub_list_name">图片链接:</span>
						      
						      <span class="btn btn-success fileinput-button">
							        <i class="glyphicon glyphicon-plus"></i>
							        <span>浏览</span>
							        <!-- The file input field used as target for the file upload widget -->
							        <input id="fileupload" type="file" name="file" >
							        <input id="imagePath" name=imagePath type="hidden"  datatype="*" nullmsg="请添加图片">
							    </span>
							    <span id="imgName"></span> 
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">图片链接</label>
  						 </p>
  						 
  						  <p class="manage_unit clearfix mtb15">
							<span class="manage_span_name inline_inblo pl15 wd120 txtri">摘要:</span>
							<input id="description" name="description" type="text"
								class="manage_input wd250" data-rule-required="true">
						</p>
						
						 <p class="manage_unit clearfix mtb15">
							<span class="manage_span_name inline_inblo pl15 wd120 txtri">外部链接:</span>
							<input id="url" name="url" type="text"
								class="manage_input wd250" data-rule-required="true">
						</p>
						<div class="sub_form_list clearfix">
					      <span class="sub_list_name">正文:</span>
					     	 <div style="float:left;width:330px;">
					     	 <textarea name="content" id="content"></textarea>
					     	  <script type="text/javascript">
							      var editor = UE.getEditor('content',{
							    	  toolbars:[["bold","italic","underline","|","insertorderedlist","insertunorderedlist","|","insertimage","|",'removeformat','forecolor','backcolor',"insertvideo","fullscreen"]],
							      allHtmlEnabled :false//提交到后台的数据是否包含整个html字符串
							      });
							    </script>
	    					</div>
    					</div>

 						<p class="manage_unit clearfix mtb15">
							<span class="manage_span_name inline_inblo pl15 wd120 txtri">顺序:</span>
							<input id="sorting" name="sorting" type="text"
								class="manage_input wd250" data-rule-required="true">
						</p>
						
						<p class="btinp" style="text-align:center;">
						  <input type="button" value="确定" onclick="updateSave()" id="test1" class="orage_bg prl20 sub_detail_btn h32">
						 <input type="reset" value="取消" onclick="closePop()" class="bglv prl20 sub_detail_btn h32">
						</p>
				</form>
				</div>
		</div>
	</div>
   </div>
 </body>
  <script src = "${msUrl}/js/weixinNewstemplate/weixinArticle.js"></script>		
