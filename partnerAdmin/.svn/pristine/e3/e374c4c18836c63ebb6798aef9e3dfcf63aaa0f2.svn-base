<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
  <title>新闻内容页</title>
  <link rel="stylesheet" type="text/css" href="${msUrl}/css/hebase.css">
  <link rel="stylesheet" type="text/css" href="${msUrl}/css/liststyle.css">
  <script src="${msUrl}/js/coyote.js"></script>
 
<body class="headbody">
<!-- 头部开始 -->
<div class="header">
  <div class="wid1000 clearfix headerdiv">
    <img src="${msUrl}/images/he_logo.png" class="header_logo" height="48" width="144">
    <ul class="header_ul clearfix">
      <li><a href="首页.htm" class="ft18">首页</a></li>
      <li><a href="帮助中心.htm" class="ft18">帮助中心</a></li>
      <li><a href="关于我们.htm" class="ft18">关于我们</a></li>
      <li><a href="企业中心.htm" class="ft18">企业中心</a></li>
    </ul>
  </div>
</div>
<!-- 头部结束 -->
<!-- 内容开始 -->
<div class="cont_news clearfix wid1000">
  <div class="news_left">
    <h3 class="newsdetail_tit ft26 ftno">${news.title }</h3>
    <p class="newsdetail_heading clearfix ft12">
      <span class="newsdetail_titspan clearfix">
        <a href="#" class="flole greentxt">时政新闻</a>
        <span class="newsdetail_divide flole">|</span>
        <span class="newsdetail_sour flole">${news.creatUserName}</span>
        <span class="newsdetail_sour flole"><fmt:formatDate value="${news.createTime}" type="date"/></span>
        <span class="newsdetail_divide flole">|</span>
        <span class="newsdetail_sour news_share flole bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more">分享到</a></span>
      </span>
      <span class="newsdet_heading_rig">
        <a href="#"><img src="${msUrl}/images/shoucang.jpg" height="23" width="16" alt="" style="margin:0px 5px;"></a>
        <a href="#" class="clearfix">
          <img src="${msUrl}/images/pinlun.jpg" height="23" width="24" alt="">
          <span class="reviews inline_inblo pl15 ftbol"></span>
        </a>
      </span>
    </p>
    <div class="newsdetailcon">
      <div class="newsdetail_p ft16">${news.newsData.content}</div>
    </div>
    <div class="review_div">
      <h3 class="review_tit_h3"><span>发表评论</span></h3>
      <p class="review_sum">共<span class="greentxt">22</span>条评论&nbsp;&nbsp;&nbsp;&nbsp;已有<span class="greentxt">122</span>人参加</p>
      <form action="" class="review_form">
        <textarea>发表您想说的话</textarea>
        <input type="button" value="发表" class="review_btn green_bg ft18">
      </form>
    </div>
    <div class="review_list">
      <h3 class="review_tit_h3"><span>全部评论</span></h3>
      <ul>
        <li class="review_con_li clearfix">
          <span class="review_con_lef">
            <img src="${msUrl}/images/dstx.png" class="review_avatr">
          </span>
          <span class="review_con_rig">
            <span class="review_name">GOO</span>
            <span>3小时前</span>
            <span class="review_user_con">卡联孵化园卡联孵化园卡联孵化园卡联孵化园</span>
            <a href=""><span>顶（111）</span></a>
            <a href=""><span>回复</span></a>
          </span>
        </li>
        <li class="review_con_li clearfix">
          <span class="review_con_lef">
            <img src="${msUrl}/images/dstx.png">
          </span>
          <span class="review_con_rig">
            <span class="review_name">GOO</span>
            <span>3小时前</span>
            <span class="review_user_con">卡联孵化园卡联孵化园卡联孵化园卡联孵化园</span>
            <a href=""><span>顶（111）</span></a>
            <a href=""><span>回复</span></a>
          </span>
        </li>
      </ul>
    </div>
  </div>
  <div class="news_rig">
    <h4 class="news_rig_tit ft18">新闻分类</h4>
				<ul id="newsul">
					<c:forEach items="${columnList}" var="column">
						<li>
							<span class="inline_blo news_rig_a ft16 pointer"> 
							<span class="inline_inblo pl15 news_tit_rig_lef">${column.name}</span> 
							<span class="inline_inblo pl15 news_tit_rig_rig"></span>
							</span>
							<c:forEach items="${column.childList}" var="child">
								<a href="#" class="inline_blo news_son_tit chacol h32">${child.name}</a>
							</c:forEach>
						</li>
					</c:forEach>
				</ul>
			</div>
</div>
<!-- 内容结束 -->
<!-- 底部开始 -->
<div class="footer">
  <p class="footp ft12">All Rights Reserved. Copyright 2015 XX科技有限股份有限公司</p>
</div>
<!-- 底部结束 -->
<script>
	window._bd_share_config = {
		"common" : {
			"bdSnsKey" : {},
			"bdText" : "",
			"bdMini" : "2",
			"bdMiniList" : false,
			"bdPic" : "",
			"bdStyle" : "0",
			"bdSize" : "16"
		},
		"share" : {
			"bdSize" : 16
		},
		"image" : {
			"viewList" : [ "qzone", "tsina", "tqq", "renren", "weixin" ],
			"viewText" : "分享到：",
			"viewSize" : "16"
		},
		"selectShare" : {
			"bdContainerClass" : null,
			"bdSelectMiniList" : [ "qzone", "tsina", "tqq", "renren", "weixin" ]
		}
	};
	with (document)
		0[(getElementsByTagName('head')[0] || body)
				.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
</script>
<script type="text/javascript">
var oLisUl=document.getElementById('newsul');
var aLisA=oLisUl.getElementsByTagName('a');
//alert(aLisA.length);
for(var i=0;i<aLisA.length;i++)
{
  aLisA[i].index=i;
  aLisA[i].style.display='none';
  aLisA[i].onclick=function()
  {
    for(var j=0;j<aLisA.length;j++)
    {
      aLisA[j].style.color='#99a8ac';
    }
    this.style.color="#4dbfe5";
  }
}
//列表点击收缩展开
var oLisLi=oLisUl.getElementsByTagName('li');
var aLisSpa=getByInClass(oLisUl,'news_rig_a');
for(var k=0;k<aLisA.length;k++)
{
  aLisA[k].style.display='none';
}
for(var i=0;i<oLisLi.length;i++)
{
  aLisSpa[i].index=i;
  oLisLi[i].index=i;
  aLisSpa[i].onclick=function()
  {//alert(this.index);
	  var curSp=this.index;
    var aLiSpA=oLisLi[this.index].getElementsByTagName('a');
    if(aLiSpA.length>0)
    {
      if(aLiSpA[0].style.display=='none')
      {
        for(var k=0;k<aLisA.length;k++)
        {
          aLisA[k].style.display='none';
        }
        for(var j=0;j<aLiSpA.length;j++)
        {
          aLiSpA[j].style.display='block';
        }
      }
      else
      {
        for(var k=0;k<aLisA.length;k++)
        {
          aLisA[k].style.display='none';
        }
      }
    }
  }
}
</script>
</body>
