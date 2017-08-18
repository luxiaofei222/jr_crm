<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script>
	function login_check_mail() {
		var user_mail = $("#user_mail").val();
		var bol = false;
		$.ajax({
			type : "POST",
			url : "/sc_register/check_mail.html?user_mail=" + user_mail,
			async : false,
			success : function(data) {
				if (data == 1) {
					//邮箱不存在
					$(".dui1").hide();
					$(".cuo1").show();
					$(".tishi1").css("visibility", "visible");
					$(".tishi1").html("您输入的邮箱不存在")
					$(".mail").css("background-color", "#FAB0AF");
					$(".mail").css("border-color", "red");
					bol = false;
				} else if (data == 0) {
					//邮箱是空的
					$(".dui1").hide();
					$(".cuo1").show();
					$(".tishi1").css("visibility", "visible");
					$(".tishi1").html("请输入登录邮箱！")
					$(".mail").css("background-color", "#FAB0AF");
					$(".mail").css("border-color", "red");
					bol = false;
				} else {//邮箱已注册
					$(".tishi1").css("visibility", "hidden");
					$(".cuo1").hide();
					$(".dui1").show();
					$(".mail").css("background-color", "white");
					$(".mail").css("border-color", "#ccc");
					$(".tishi1").css("visibility", "hidden");
					bol = true;
				}
			}
		});
		return bol;
	}
	//检查密码
	function login_check_password() {
		var user_password = $("#user_password").val();
		if (user_password != null && user_password != "") {
			$(".tishi4").css("visibility", "hidden");
			$(".mima").css("background-color", "white");
			$(".mima").css("border-color", "#ccc");
			return true;
		} else {
			$(".tishi4").css("visibility", "visible");
			$(".mima").css("background-color", "#FAB0AF");
			$(".mima").css("border-color", "red");
			return false;
		}
	}
	//登录页面
	function user_login(type) {
		if(type=='phone'){
			if (check_tel() && login_check_password()) {
				$(".zhucebu").css("background", "#ccc");
				$(".zhucebu").html("登录中…");
				$("#myform").ajaxSubmit({
					type : 'post',
					url : "/sc_login/login.html",
					success : function(data) {
						if (data == 1) {//注册成功跳转登录窗口
							window.location.reload();//登陆成功
						} else if (data == 0) {//用户名错误
							$(".dui1").hide();
							$(".cuo1").show();
							$(".zhucebu").css("background", "#06C1AE");
							$(".zhucebu").html("登录");
							$(".tishi1").css("visibility", "visible");
							$(".tishi1").html("您输入的手机号不存在")
							$(".mail").css("background-color", "#FAB0AF");
							$(".mail").css("border-color", "red");
						} else if (data == 2) {//密码错误
							$(".zhucebu").css("background", "#06C1AE");
							$(".zhucebu").html("登录");
							$(".tishi4").css("visibility", "visible");
							$(".tishi4").html("您输入的密码错误")
							$(".mima").css("background-color", "#FAB0AF");
							$(".mima").css("border-color", "red");
						}else if(data==3){
							$(".dui1").hide();
							$(".cuo1").show();
							$(".zhucebu").css("background","#06C1AE");
							$(".zhucebu").html("登录");
							$(".tishi1").css("visibility", "visible");
							$(".tishi1").html("您的账号已被限制登录！")
							$(".mail").css("background-color", "#FAB0AF");
							$(".mail").css("border-color", "red");
						}
					},
					error : function(XmlHttpRequest, textStatus, errorThrown) {
						alert("登录失败！");
					}
				});
			}
		}else{
			if (login_check_mail() && login_check_password()) {
				$(".zhucebu").css("background", "#ccc");
				$(".zhucebu").html("登录中…");
				$("#myform").ajaxSubmit({
					type : 'post',
					url : "/sc_login/login.html",
					success : function(data) {
						if (data == 1) {//注册成功跳转登录窗口
							window.location.reload();//登陆成功
						} else if (data == 0) {//用户名错误
							$(".dui1").hide();
							$(".cuo1").show();
							$(".zhucebu").css("background", "#06C1AE");
							$(".zhucebu").html("登录");
							$(".tishi1").css("visibility", "visible");
							$(".tishi1").html("您输入的邮箱不存在")
							$(".mail").css("background-color", "#FAB0AF");
							$(".mail").css("border-color", "red");
						} else if (data == 2) {//密码错误
							$(".zhucebu").css("background", "#06C1AE");
							$(".zhucebu").html("登录");
							$(".tishi4").css("visibility", "visible");
							$(".tishi4").html("您输入的密码错误")
							$(".mima").css("background-color", "#FAB0AF");
							$(".mima").css("border-color", "red");
						}else if(data==3){
							$(".dui1").hide();
							$(".cuo1").show();
							$(".zhucebu").css("background","#06C1AE");
							$(".zhucebu").html("登录");
							$(".tishi1").css("visibility", "visible");
							$(".tishi1").html("您的账号已被限制登录！")
							$(".mail").css("background-color", "#FAB0AF");
							$(".mail").css("border-color", "red");
						}
					},
					error : function(XmlHttpRequest, textStatus, errorThrown) {
						alert("登录失败！");
					}
				});
			}
		}
		
	}
	//检查手机号
	function check_tel(){
		var user_phone=$("#user_phone").val();
		var reg6 =/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
		if (reg6.test(user_phone)) {
			$(".tishi1").css("visibility", "hidden");
			$(".cuo1").hide();
			$(".dui1").show();
			$(".mail").css("background-color", "white");
			$(".mail").css("border-color", "#ccc");
			return true;
		} else {
			$(".dui1").hide();
			$(".cuo1").show();
			$(".tishi1").css("visibility", "visible");
			$(".mail").css("background-color", "#FAB0AF");
			$(".mail").css("border-color", "red");
			return false;
		}
	}
	//手机登陆页面
	function get_tanchuang_phone(){
		$.post("/sc_login/get_phone_tanchuang_login.html",
				function(data){
			$(".login_way1").css("color","#06C1AE");
			$(".login_way2").css("color","#444");
			$("#type_login").html(data);
		})
	}
	//邮箱登录页面
	function get_tanchuang_mail(){
		$.post("/sc_login/get_mail_tanchuang_login.html",
				function(data){
			$(".login_way2").css("color","#06C1AE");
			$(".login_way1").css("color","#444");
			$("#type_login").html(data);
		})
	}
</script>
<div class="dengluk">
	<span class="close" onclick="colse_denglu()" id="close"> <img
		src="/images/school/front/index/close.png">
	</span>
	<div class="zhucek_w">
		<div class="zhucek_title">
			<div class="zhucek_title1">登录京人账号</div>
			<a onclick="get_register()" class="zhucek_title2"> 注册 </a>
		</div>
		<div class="login_way">
			<span class="login_way1" onclick="get_tanchuang_phone()">手机</span>|<span onclick="get_tanchuang_mail()"class="login_way2">邮箱</span>
		</div>
		<div class="inputz" id="type_login">
			<form id="myform" enctype="multipart/form-data">
				<input type="text" onblur="check_tel()" id="user_phone"
					name="user_phone" placeholder="手机" class="mail"
					style="margin-top: 18px;"> <i class="mailicon1"></i> <b
					class="dui dui1"></b> <b class="cuo cuo1"></b>
				<div class='tishi tishi1'>请输入您的手机！</div>
				<input type="password" onblur="login_check_password()"
					id="user_password" name="user_password" placeholder="密码"
					class="mima"> <i class="mimaicon" style="top: 91px;"></i>
				<div class='tishi tishi4'>请输入密码！</div>
				<span class='forget' onclick="find_pass('phone')">忘记密码？</span> <a
					class="zhucebu" onclick="user_login('phone')">登录</a>
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
		</div>
	</div>
</div>