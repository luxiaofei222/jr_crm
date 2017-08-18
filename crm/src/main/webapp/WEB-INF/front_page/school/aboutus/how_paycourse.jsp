<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/school/front/common/about_us.css">
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<title>如何学习</title>
</head>
<body>
<!--头部  -->
<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
<div class="how_buy">
   <p class="buy_info">1.登录京人网校账号，没有京人网校账号的小伙伴们戳这里进行注册<a href="/jrsystem/howsregister.html">如何注册>></a></p> 
   <p class="buy_info">2.在网站首页中选择感兴趣的课程，点击进入详情页，或者在首页上方的搜索框中搜索感兴趣的课程。</p>
   <p class="buy_info">3.在课程详情页中点击<span>立即购买</span>，进入支付页面，共有3种支付方式供您选择。</p>
   <div class="pay_away">
     <p class="buy_info">a.平台支付</p>
     <img src="/images/school/front/common/a_pay.jpg" width="864" />
   </div>
   <div class="pay_away">
     <p class="buy_info">b.银行汇款</p>
     <img src="/images/school/front/common/b_pay.jpg" />
   </div>
   <div class="pay_away">
     <p class="buy_info">c.报名处支付（如您已经在报名处支付，请联系客服处理）</p>
   </div>
   <div class="buy_success">
     <p class="buy_info">支付完成后即可观看课程视频啦</p>
     <p class="buy_info">或者在课程详情页中点击<span>加入购物车</span>，之后可在<span class="buy_car"><i class="fa fa-shopping-cart"></i>我的购物车<span>0</span>&gt;</span>里找到您的课程，对课程进行支付操作，完
成后即可观看啦</p>
   </div>
  </div>
<!--登录注册窗口  -->
	<jsp:include
		page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/footer.jsp"></jsp:include>
</body>
</html>