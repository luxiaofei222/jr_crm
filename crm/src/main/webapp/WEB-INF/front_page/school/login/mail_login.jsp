<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form id="myform" enctype="multipart/form-data">
	<input type="text" onblur="login_check_mail()" id="user_mail"
		name="user_mail" placeholder="邮箱" class="mail"
		style="margin-top: 30px;"> <i class="mailicon"></i> <b
		class="dui dui1"></b> <b class="cuo cuo1"></b>
	<div class='tishi tishi1'>请输入正确的邮箱格式</div>
	<input type="password" onblur="login_check_password()"
		id="user_password" name="user_password" type="password"
		placeholder="密码" class="mima"> <i class="mimaicon"
		style="top: 124px;"></i>
	<div class='tishi tishi4'>建议使用字母、数字和符号的组合，6-20个字符</div>
	<span class='forget' onclick="find_pass_page('${sessionScope.url}','mail')">忘记密码？</span>
	<a class="zhucebu" onclick="user_login('${sessionScope.url}','mail')">登录</a>
</form>