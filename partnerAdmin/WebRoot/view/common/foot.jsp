<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--公用尾部开始-->
<div class="mainFooter">
  <div class="wd1200">
    <div class="container-layout">
      <div class="line">
        <div class="x7">
          <div class="linkTo"> 
            <a href="#">售后服务</a>
            <a href="#">ZOL见见</a>
            <a href="#">ZOL历程</a>
            <a href="#">广告服务</a>
            <a href="#">招聘</a>
            <a href="#">互动营销</a>  
            <a href="#">品牌大全</a>
            <a href="#">站点地图</a>
            <a href="#">联系方式</a>
            <a href="#">RSS订阅</a>
            <a href="#">反馈/纠错</a>
          </div>
          <div class="copyRight">
            &copy;2016中关村在线版权所有
          </div>
        </div>
        <div class="x5">
          <div>联系我们：400-678-0068（个人业务）hh@zol.com（企业业务）</div>
          合伙人QQ群：205955823
        </div>
      </div>
    </div>
  </div>
</div>
<!--公用尾部结束-->

<%--<!------------网页内容--->
<!---页面JS开始---------->
<script src="${pageContext.request.contextPath}/frame/jQuery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/frame/pintuer/pintuer.js"></script>
<script src="${pageContext.request.contextPath}/frame/layer/layer.js"></script>
<script>
  /*全选与取消*/
  $('.tableBox').delegate('.checkboxAll input', 'click', function () {
    if ($(this).is(':checked')) {
      $('.checkbox input').prop('checked', true);
      $('.checkbox').addClass('active');
      $('.checkboxAll').addClass('active');
    } else {
      $('.checkbox input').prop('checked', false);
      $('.checkbox').removeClass('active');
      $('.checkboxAll').removeClass('active');
    }
  });
  $('.tableBox').delegate('.checkbox input', 'change', function () {
    var length = $('.checkbox input:checked').length;
    if ($(this).is(":checked")) {
      $(this).parent().addClass('active');
    } else {
      $(this).parent().removeClass('active');
    }
    if ($('.checkbox input:checked').length == $('.checkbox input').length) {
      $('.checkboxAll input').prop('checked', true);
      $('.checkboxAll').addClass('active');
    } else {
      $('.checkboxAll input').prop('checked', false);
      $('.checkboxAll').removeClass('active');
    }
  });
  /*全选与取消*/

  /****弹窗口*****/
  $('.sureBO').on('click', function () {
    layer.open({
      type: 1,
      title: '确认商机',
      scrollbar: false,
      move: false,
      area: '600px',
      shadeClose: true,
      content: '<div class="layerPop surePop"><div class="container-layout"><div class="line"><div class="x12 text-center">              此处为系统根据勾选自动带出评级可以手动修改</div></div><div class="line"><div class="x8"><div class="x12"><div class="x4 text-right">商机名称</div><div class="x8"><select><option value="0">A</option><option value="1">B</option><option value="2">C</option><option value="3">D</option></select></div></div><div class="x12"><div class="x4 text-right">确认的商机奖励</div><div class="x8"><select><option value="0">A</option><option value="1">B</option><option value="2">C</option><option value="3">D</option></select></div></div><div class="x12"><div class="x4 text-right">对应奖励</div><div class="x8"><input type="text" class="input"></div></div></div><div class="x4"><div class="x1"></div><div class="x10"><div class="total">推荐公司规模</div><div>5000+ A级</div><div>1000-5000 B级</div><div>100-1000 C级</div><div>50-100 D级</div><div><50 其他级</div></div></div></div></div><div class="btnGroup text-center"><div class="button bg closePop">取消</div><div class="button bg-sub">确定</div></div></div>',
    });
  });
  $('body').delegate('.closePop', 'click', function () {
    layer.closeAll('page');
  });
  /*一键分享弹窗*/
  $('.share').click(function () {
    layer.open({
      type: 1,
      title: '招募新合伙人获取金币！',
      scrollbar: false,
      move: false,
      area: '600px',
      shadeClose: true,
      content: '<div class="layerPop sharePop"><div class="content"><div class="title">新用户通过链接进入注册，推荐人获得一个金币</div><div class="clearfix shareBtn"><div class="fl qqIcon"><div class="circleBox"></div></div><div class="fl weixinIcon"><div class="circleBox"></div></div><div class="fl weiboIcon"><div class="circleBox"></div></div></div></div><div class="btnGroup text-center"><div class="button bg">一键分享</div></div></div>',
    });
  });
  /*一键分享弹窗*/
  /********弹窗口******/
</script>
<!----------页面JS结束--->
--%>