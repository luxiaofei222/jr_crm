<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/school/front/common/about_us.css">
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<title>如何注册</title>
</head>
<body>
<!--头部  -->
<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
 <div class="how_resign">
   <p class="resign_info">打开京人网校首页，在右上角找到“注册按钮”，点击进行注册。</p> 
   <p class="resign_info">注：所有京人网页顶部都有“注册”按钮，点击也可进行注册。</p>
   <div class="resign_away">
     <img src="/images/school/front/common/register_1.jpg" />
   </div>
   <div class="resign_away">
     <p class="resign_info">1.手机注册</p>
     <p class="resign_info">填写注册信息，点击“注册”按钮。</p>
     <img src="/images/school/front/common/register_2.jpg" />
   </div>
   <div class="resign_away">
     <p class="resign_info">2.邮箱注册</p>
     <p class="resign_info">(1)输入邮箱后，点击“发送”按钮。</p>
     <img src="/images/school/front/common/register_3.jpg" />
     <p class="resign_info">(2)进入到注册邮箱，打开来自京人网校的注册确认邮件，将获取到的验证码填写到注册框中。</p>
     <img src="/images/school/front/common/register_4.jpg" />
     <p class="resign_info">(3)填写完注册信息后，点击“注册”按钮，即可完成注册。</p>
     <img src="/images/school/front/common/register_5.jpg" />
   </div>
   <div class="resign_success">
     <p class="resign_info">注册成功后，返回登录界面，登录成功后就可以浏览课程啦</p>
   </div>
  </div>
<!--登录注册窗口  -->
	<jsp:include
		page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/footer.jsp"></jsp:include>
</body>
</html>