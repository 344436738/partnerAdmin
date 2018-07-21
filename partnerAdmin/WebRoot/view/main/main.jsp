<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="shortcut icon" href="${msUrl}/images/bimages/favicon.png" type="image/png">

  <title>互联网项目基础开发平台</title>
  
<%@include file="/view/include.jsp"%>

<script type="text/javascript">
		
		
		var mainPop;//主页弹窗
		//加载页面，右侧div将服务器的page加载过来
		function loadPage(url) {
			/*var index = layer.load(2, {
				offset : [ '290px' ]
			});*/
			var path = window.location.hash.replace('#', '');
			if(path.indexOf("?")!=-1
			&&url.indexOf("?")!=-1
			&&path.indexOf(url.substring(0,url.indexOf("?")))!=-1
			&&url!=path){
				return;
			}
			
			// Page Preloader
			$(".contentpanel").load("${msUrl}"+url, function(response,status) {
				if(status=="error"){
					layer.msg("系统错误，请稍后再试");
					//layer.close(index);
					return;
				}
				chooseNav(url);
				//layer.close(index);
				showButton(url);	
			});
		}
		
		function chooseNav(url){
			 $('#left-menu').find(".active").each(function() {
			 	$(this).removeClass("active");
			 });
			 
			  $('#left-menu').find("a[href*='#']").each(function() {
			  	$(this).css("color","#94989d");
			  });
			 
		 	 $('#left-menu').find("a[href='#"+url+"']").each(function() {
		 	 	var navParent= $(this).parents(".nav-parent");
		 		navParent.addClass("active nav-active");
		 		 $(this).parents(".children").css("display","block");
		 		 $(this).css("color","#1caf9a");
		 		 var toptitle=navParent.children("a").html().replace("<span>","").replace("</span>","");
		 		 var subtitle="<span>"+$(this).text()+"</span>";
		 		 $('#pageHeaderRight').html(toptitle+subtitle);
		 	});
			
		}
		
		function openBModal(obj){
			$(obj).modal('show');
			$("body").css('padding-right','0px');
		}
		
		function closeBModal(obj){
			$(obj).modal('hide');
		}
		//根据权限显示菜单的toolbar
		function showButton(url){
			var urlPost = {'url':url};
			$.post(
					"${msUrl}/getActionBtn.do", 
					urlPost,
					function(data) {
					if(data.success){//操作成功
						if(data.allType){//如果是超级管理员
							$('#toolbar span').each(function(){
								 $(this).show();
							   // $(this).attr("fun");
							  });
						}else{//不是超级管理员
							$('#toolbar span').each(function(){
								if($(this).attr("fun")!=null){
									$(this).hide();
								}
								if($.inArray($(this).attr("fun"), data.types)>-1){
									 $(this).show();
								}
							  });
						}
					}else{
						layer.msg(data.msg, {
							tips : 2
						});
					}
			},"json");
		}
		
		// 修改我的密码--打开弹窗
		function updateMyPwd() {
			$( "#updateMyPwdForm" ).validate().resetForm();
			mainPop=layer.open( {
				type : 1,
				area : [ '420px', '240px' ], //宽高
				content : $("#update"),
				success: function(layero, index){
				 $("#update").removeClass("hidden");
		    }
			});
	
		}
		
		// 修改我的密码--保存按钮
		function updateMyPwdSave() {
			if(!$("#updateMyPwdForm").valid()){
				return;
			}
			var oldPwd=$("#myOldPwd").val();
			var newPwd=$("#myNewPwd").val();
			$("#updateMyPwdForm").clearForm(true);
			$.post(
					"${msUrl}/modifyPwd.do", 
					{"oldPwd":oldPwd,"newPwd":newPwd},
					function(result) {
					if (result.success) {
						layer.msg(result.msg, {
							tips : 2
						});
						//关闭layer
						layer.close(mainPop);
					
					} else {
						layer.msg(result.msg, {
							tips : 2
						});
					}
			},"json");
		}	

		$(document).ready(function() {
			//左侧菜单点开效果
		/*	$("#list_ul a").click(function() {
				if($(this).attr("openwindow")!=null){
					window.open($(this).attr("page"));
				}else{
					loadPage($(this).attr("page"));
				}
			});
			*/
			//初始化验证
			 $.validator.setDefaults({
					ignore: []
				});//如果有display:none的情况，加上这个，会验证display none的必填项
			 $("#updateMyPwdForm").validate();
		});
 			$(document).ajaxError(function(event,xhr,settings,errorType){
 				if(xhr.status=="504"){
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
   	
  	window.addRouter = function(path, func) {
		var pos = path.indexOf('?');
		var truePath = path;
		
		if (pos != -1)
			truePath = path.substring(0,pos);
		func = func || function() {
			loadPage(path);
		}
		var tmparray = truePath.split("/");
		if(tmparray[1] in router.routes && tmparray[2] in router.routes[tmparray[1]] && tmparray[3] in router.routes[tmparray[1]][tmparray[2]]){
			return;
		}else{
			router.on(truePath, func);
		}
	}

	
	 window.router = Router();
	 
	 
	 $(document).ready(function(){
		 $('#left-menu').find("a[href*='#']").each(function() {
				var path = this.hash.replace('#', '');
				//console.log(path+"$$$$")
				addRouter(path);
			});
			window.router.init();
			
			
			
		$.validator.setDefaults({
			ignoreTitle:'ignoreTitle',
			showErrors: function(map, list) {
				this.currentElements.removeAttr("title").removeClass("error");
				var tip_option={
						animation:true,
						placement:'right',
						trigger:'manual '
				}
				$.each(list, function(index, error) {
					$(error.element).removeAttr("title").removeClass("error");
					$(error.element).removeAttr("data-original-title");
					$(error.element).attr("title", error.message);
					$(error.element).attr("data-original-title", error.message);
					$(error.element).addClass("error");
					$(error.element).tooltip(tip_option).tooltip('show');
					 //layer.tips(error.message,error.element, {
					//    tips: [2, '#78BA32'],
					//	tipsMore: true
					//});
				}); 
			},
			focusCleanup:true,
			unhighlight: function(element,errorClass,validClass){
				$(element).tooltip('destroy');
			}
		});
	});
		

</script>
  
</head>

<body>



<!-- Preloader -->
<div id="preloader">
    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
</div>

<section>

  <div class="leftpanel">

    <div class="logopanel">
        <h1><span>[</span> 开发平台v1.0 <span>]</span></h1>
    </div><!-- logopanel -->

    <div class="leftpanelinner">

        <!-- This is only visible to small devices -->
        <div class="visible-xs hidden-sm hidden-md hidden-lg">
            <div class="media userlogged">
                <img alt="" src="${msUrl}/images/bimages/photos/loggeduser.png" class="media-object">
                <div class="media-body">
                    <h4>${user.nickName}</h4>
                    <span></span>
                </div>
            </div>

            <h5 class="sidebartitle actitle">Account</h5>
            <ul class="nav nav-pills nav-stacked nav-bracket mb30">
              <li><a href="profile.html"><i class="fa fa-user"></i> <span>修改密码</span></a></li>
             <li><a href="logout.shtml"><i class="fa fa-sign-out"></i> <span>登出</span></a></li>
            </ul>
        </div>

      <h5 class="sidebartitle">Navigation</h5>
      <ul class="nav nav-pills nav-stacked nav-bracket" id="left-menu">
        <li class="active"><a href="main.shtml"><i class="fa fa-home"></i> <span>主页</span></a></li>
        
        
        <c:forEach var="item" items="${menuList}" varStatus="var">
         <c:if test="${item.isopen==1}">
         	<li><a href="${item.url}">
         	<i class="fa fa-${item.icon}"></i> 
	         <span>${item.text}</span></a></li>
         </c:if>
          <c:if test="${item.isopen!=1}">
	          <li class="nav-parent"><a href="">
	         <i class="fa fa-${item.icon}"></i> 
	         <span>${item.text}</span></a>
	          <ul class="children">
	          <c:forEach var="node" items="${item.children}">
	          	 <c:if test="${node.isopen==1}">
	          		 <li><a href="${msUrl}${node.url}" target="_blank">
	          		 <i class="fa fa-caret-right"></i>${node.text}</a></li>
	          	 </c:if>
	          	 <c:if test="${node.isopen!=1}">
	          		 <li><a href="#${node.url}"><i class="fa fa-caret-right"></i>${node.text}</a></li>
	          	 </c:if>
	            </c:forEach>
	          </ul>
	        </li>
          </c:if>
         
		</c:forEach>
		
      </ul>


    </div><!-- leftpanelinner -->
  </div><!-- leftpanel -->

  <div class="mainpanel">

    <div class="headerbar">

      <a class="menutoggle"><i class="fa fa-bars"></i></a>

      <form class="searchform" action="index.html" method="post">
        <input type="text" class="form-control" name="keyword" placeholder="Search here..." />
      </form>

      <div class="header-right">
        <ul class="headermenu">
         
          <li>
            <div class="btn-group">
              <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                <img src="${msUrl}/images/bimages/photos/loggeduser.png" alt="" />
                ${user.nickName}
                <span class="caret"></span>
              </button>
              <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                <li><a href="profile.html"><i class="glyphicon glyphicon-user"></i>修改密码</a></li>
            <li><a href="logout.shtml"><i class="glyphicon glyphicon-log-out"></i> 登出</a></li>
              </ul>
            </div>
          </li>
         
        </ul>
      </div><!-- header-right -->

    </div><!-- headerbar -->

    <div class="pageheader">
      <h2 id="pageHeaderRight"><i class="fa fa-home"></i> 首页 <span><!-- Subtitle goes here... --></span></h2>
  
    </div>

    <div class="contentpanel">
  <div class="row">

        <div class="col-sm-6 col-md-3">
         

        <div class="col-sm-6 col-md-3">
        

      
      </div><!-- row -->

      <div class="row">
        <div class="col-sm-8 col-md-9">
          <div class="panel panel-default">
            <div class="panel-body">
              <div class="row">
                <div class="col-sm-8">
              
                  <div id="basicflot" style="width: 100%; height: 300px; margin-bottom: 20px"></div>
                </div><!-- col-sm-8 -->
               
              </div><!-- row -->
            </div><!-- panel-body -->
          </div><!-- panel -->
        </div><!-- col-sm-9 -->

        <div class="col-sm-4 col-md-3">

          <div class="panel panel-default">
            <div class="panel-body">
           
            <div id="donut-chart2" class="ex-donut-chart"></div>
            </div><!-- panel-body -->
          </div><!-- panel -->

        </div><!-- col-sm-3 -->

      </div><!-- row -->

      <div class="row">

        <div class="col-sm-7">

          <div class="table-responsive">
            <table class="table table-bordered mb30">
           
          </table>
          </div><!-- table-responsive -->

        </div><!-- col-sm-7 -->

        <div class="col-sm-5">

          <div class="panel panel-success">
            <div class="panel-heading padding5">
              <div id="line-chart" class="ex-line-chart"></div>
            </div>
            <div class="panel-body">
              <div class="tinystat pull-right">
                <div id="sparkline2" class="chart mt5"></div>
               
              </div><!-- tinystat -->
            </div>
          </div><!-- panel -->

        </div><!-- col-sm-6 -->
      </div><!-- row -->

      <div class="row">
      <!-- col-sm-6 -->

      
    
  </div>
         
    </div><!-- contentpanel -->

  </div><!-- mainpanel -->



</section>


</body>
</html>
