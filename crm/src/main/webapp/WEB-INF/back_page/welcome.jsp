<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登陆</title>
	<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
	<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="/css/school/back/welcome/welcome.css" />
	<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="/js/school/back/common/bootstrap.min.js" type="text/javascript"></script>
	<script src="/js/system/highcharts.js" type="text/javascript"></script>
</head>
<body>
	<div class="welcome">
            <div class="welcome_left_img pull-left col-lg-6">
              <img src="/images/school/back/welcome/ball.png" class="img-responsive" />
            </div>
            <div class="welcome_right_center pull-right col-lg-5">
              <h3>欢迎进入</h3>
              <%-- <h2 style="color: orange;">${sessionScope.employee_session.employee_name }</h2> --%>
              <p>京人教育网校后台管理系统</p>
              <div class="operate">
                <a href="javascript:void(0)" style="text-decoration:none;"><i class="fa fa-user"></i>${sessionScope.employee_session.employee_name }</a>
                <a href="/admin.jr"><i class="fa fa-home"></i>返回首页</a>
              </div>
            </div>
            <!-- <a href="/admin.jr">返回首页</a> -->
            <div class="welcome_bottom col-lg-12 clearfix">
              <p class="welcome_info_bottom">Welcome to Jingren education background management system</p>
            </div>
          </div>
</body>
</html>