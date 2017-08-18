<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form id="myform" enctype="multipart/form-data">
	<input type="text" onblur="login_check_mail()" id="user_mail"
		name="user_mail" placeholder="邮箱" class="mail"
		style="margin-top: 18px;"> <i class="mailicon"></i> <b
		class="dui dui1"></b> <b class="cuo cuo1"></b>
	<div class='tishi tishi1'>请输入您的邮箱！</div>
	<input type="password" onblur="login_check_password()"
		id="user_password" name="user_password" placeholder="密码" class="mima">
	<i class="mimaicon" style="top: 91px;"></i>
	<div class='tishi tishi4'>请输入密码！</div>
	<span class='forget' onclick="find_pass('mail')">忘记密码？</span> <a
		class="zhucebu" onclick="user_login('mail')">登录</a>
</form>
<!-- <div clas="fangshi">
						<div class="fghx">
							<div class="fghx1"></div>
							<div class="fghx2">第三方登录方式</div>
							<div class="fghx3"></div>
						</div>
						<div class="fangshiz">
							<span class="fangshi1"><img src="/images/school/front/index/weibo.png"></span>
							<span class="fangshi2"><img src="/images/school/front/index/qq.png"></span>
							<span class="fangshi3"><img src="/images/school/front/index/weixin.png"></span>
						</div>
					</div> -->