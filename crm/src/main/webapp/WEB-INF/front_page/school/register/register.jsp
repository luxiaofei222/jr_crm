<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="/images/school/front/index/logo2.png"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="/css/school/front/login/reset.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/login/register_page.css">
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/front/register/register.js"
	type="text/javascript"></script>
<title>京人网校注册页面</title>
</head>
<body>
	<div class="rpage_wrap">
		<div class='rpage_top'>
			<div class='logo'>
				<img src="/images/school/front/login/logo.png">
			</div>
		</div>
		<div class="rpage_info_w">
			<div class="rpage_info">
				<div class="zhucek">
					<div class="zhucek_w">
						<div class="zhucek_title">
							<div class="zhucek_title1">注册京人账号</div>
							<a class="zhucek_title2" onclick="to_login('${jr_url}')"> 登录
							</a>
						</div>
						<div class="login_way">
							<span class="login_way1" onclick="get_register_phone('${jr_url}')">手机</span>|<span
								onclick="get_register_mail('${jr_url}')" class="login_way2">邮箱</span>
						</div>
						<div class="inputz" id="register_page">
							<form id="myform" >
								<input type="text" onblur="check_tel()" name="user_phone"
									id="user_phone" placeholder="手机" class="mail"
									style="margin-top: 30px;"> <i class="mailicon1"></i> <b
									class="dui dui1"></b> <b class="cuo cuo1"></b>
								<div class='tishi tishi1'>请输入正确的手机号</div>
								<input type="text" onblur="check_phone_code()" id="phone_code"
									placeholder="验证码" class="code"> <a class="send1"
									onclick="send_phone_code(this)">发送</a> <i class="codeicon"></i> <b
									class="dui dui2"></b> <b class="cuo cuo2"></b>
								<div class='tishi tishi2'>请输入六位数字验证码</div>
								<input type="text" placeholder="昵称" onblur="check_nickname()"
									id="user_nickname" name="user_nickname" class="nicheng">
								<i class="ncicon"></i> <b class="dui dui3"></b> <b
									class="cuo cuo3"></b>
								<div class='tishi tishi3'>请输入中文、英文、数字、"-" "_"的组合，4-20个字符</div>
								<input type="password" onblur="check_password()"
									id="user_password" name="user_password" placeholder="密码"
									class="mima"> <i class="mimaicon"></i> <b
									class="dui dui4"></b> <b class="cuo cuo4"></b>
								<div class='tishi tishi4'>请使用字母、数字和符号的组合，6-20个字符</div>
								<input type="password" onblur="check_confirm()"
									id="confirm_pass" placeholder="再次输入密码" class="mima2"> <i
									class="mimaicon2"></i> <b class="dui dui5"></b> <b
									class="cuo cuo5"></b>
								<div class='tishi tishi5'>您输入的两次密码不一致！</div>
								<div class="iargee1">
									<input type="checkbox" id="check_xieyi" class="check1">
									<span class="iargee">我同意<span class="xieyi">《京人网校注册协议》</span></span>
								</div>
								<div class='tishi tishi6' style="color: red;">你没有同意本网站注册协议！</div>
								<a class="zhucebu" onclick="register_user('${jr_url}','phone')">注册</a>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class='coverfull1' id="coverfull1"></div>
	<div class="xieyi_chuang" id="xieyi_chuang">
		<jsp:include page="/WEB-INF/front_page/school/register/xieyi.jsp"></jsp:include>
	</div>
</body>
</html>