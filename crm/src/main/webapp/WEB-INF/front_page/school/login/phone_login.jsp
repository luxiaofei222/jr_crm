<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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