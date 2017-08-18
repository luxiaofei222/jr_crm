<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/login/reset.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/login/login_page.css">
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/front/login/login.js" type="text/javascript"></script>
<title>京人网校登录页面</title>
</head>
<body>
	<div class="rpage_wrap" id="change_page">
		<div class='rpage_top'>
			<div class='logo'>
				<a href="/index.jsp"><img src="/images/school/front/login/logo.png"></a>
			</div>
		</div>
		<div class="rpage_info_w">
			<div class="rpage_info">
				<div class="dengluk">
					<div class="zhucek_w">
						<div class="zhucek_title">
							<div class="zhucek_title1">登录京人账号</div>
							<a class="zhucek_title2"
								onclick="to_register('${sessionScope.url}')"> 注册 </a>
						</div>
						<div class="login_way">
							<span class="login_way1" onclick="get_phone_page()">手机</span>|<span
								class="login_way2" onclick="get_mail_page()">邮箱</span>
						</div>
						<div class="inputz" id="login_page">
							<form id="myform" enctype="multipart/form-data">
								<input type="text" onblur="check_tel()" id="user_phone"
									name="user_phone" placeholder="手机" class="mail"
									style="margin-top: 30px;"> <i class="mailicon1"></i> <b
									class="dui dui1"></b> <b class="cuo cuo1"></b>
								<div class='tishi tishi1'>请输入您的手机号！</div>
								<input type="password" onblur="login_check_password()"
									id="user_password" name="user_password" placeholder="密码"
									class="mima"> <i class="mimaicon" style="top: 124px;"></i>
								<div class='tishi tishi4'>请输入密码！</div>
								<span class='forget'
									onclick="find_pass_page('${sessionScope.url}','phone')">忘记密码？</span> <a
									class="zhucebu"
									onclick="user_login('${sessionScope.url}','phone')">登录</a>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>