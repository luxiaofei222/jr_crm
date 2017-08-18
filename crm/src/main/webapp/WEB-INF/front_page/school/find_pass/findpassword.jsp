<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/login/reset.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/login/findpass_page.css">
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/front/register/find_pass.js"
	type="text/javascript"></script>
<title>用户找回密码</title>
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
				<!-- 找回密码窗口 -->
		<div class="backpsd">
			<div class="zhucek_w">
				<!-- <span class="close" id="close">
					<img src="/images/school/front/login/close.png">
				</span> -->			
				<div class="zhucek_title">
					<!-- <div class="backbtn">
						<img src="/images/school/front/login/fanhui.png">
					</div> -->	
					<div class="back_wz">
						找回密码
					</div>				
				</div>
				<div class="inputz">
				<!-- 通过邮箱找回密码 -->
				<c:if test="${type=='mail' }">
					<form id="myform" enctype="multipart/form-data">
				<input type="text" onblur="find_check_mail()" name="user_mail"
					id="user_mail" placeholder="邮箱" class="mail"
					style="margin-top: 30px;"> <i class="mailicon"></i> <b
					class="dui dui1"></b> <b class="cuo cuo1"></b>
				<div class='tishi tishi1'>请输入正确的邮箱格式</div>
				<input type="text" onblur="find_check_code()" id="mail_code"
					placeholder="验证码" class="code"> <a
					onclick="find_send_code(this)" class="send1">发送</a> <i
					class="codeicon"></i> <b class="dui dui2"></b> <b class="cuo cuo2"></b>
				<div class='tishi tishi2'>请输入六位数字验证码</div>
				<input type="password" onblur="find_check_password()"
					id="user_password" name="user_password" placeholder="密码"
					class="mima"> <i class="mimaicon" style="top: 204px;"></i>
				<b class="dui dui3"></b> <b class="cuo cuo3"></b>
				<div class='tishi tishi4'>使用字母、数字和符号的组合，6-20个字符</div>
				<input type="password" onblur="find_check_confirm()"
					id="confirm_pass" placeholder="再次输入密码" class="mima2"> <i
					class="mimaicon2" style="top: 287px;"></i> <b class="dui dui4"></b>
				<b class="cuo cuo4"></b>
				<div class='tishi tishi5'>请再次输入密码</div>
				<a onclick="update_pass_user('${jr_url}','${type }')" class="zhucebu">修改</a>
			</form>
			</c:if>
			<!--通过手机号找回密码  -->
				<c:if test="${type=='phone' }">
					<form id="myform" enctype="multipart/form-data">
				<input type="text" onblur="find_check_phone()" name="user_phone"
					id="user_phone" placeholder="请输入您的手机号" class="mail"
					style="margin-top: 30px;"> <i class="mailicon1"></i> <b
					class="dui dui1"></b> <b class="cuo cuo1"></b>
				<div class='tishi tishi1'>请输入正确的手机号格式</div>
				<input type="text" onblur="find_check_phone_code()" id="phone_code"
				placeholder="验证码" class="code"> <a
					onclick="find_send_phone_code(this)" class="send1">发送</a> <i
					class="codeicon"></i> <b class="dui dui2"></b> <b class="cuo cuo2"></b>
				<div class='tishi tishi2'>请输入六位数字验证码</div>
				<input type="password" onblur="find_check_password()"
					id="user_password" name="user_password" placeholder="密码"
					class="mima"> <i class="mimaicon" style="top: 204px;"></i>
				<b class="dui dui3"></b> <b class="cuo cuo3"></b>
				<div class='tishi tishi4'>使用字母、数字和符号的组合，6-20个字符</div>
				<input type="password" onblur="find_check_confirm()"
					id="confirm_pass" placeholder="再次输入密码" class="mima2"> <i
					class="mimaicon2" style="top: 287px;"></i> <b class="dui dui4"></b>
				<b class="cuo cuo4"></b>
				<div class='tishi tishi5'>请再次输入密码</div>
				<a onclick="update_pass_user('${jr_url}','${type }')" class="zhucebu">修改</a>
			</form>
			</c:if>
				</div>
			</div>
		</div>
			</div>
		</div>
	</div>
</body>
</html>