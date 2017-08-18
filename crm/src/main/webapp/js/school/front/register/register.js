//验证邮箱
	function check_mail() {
		var reg1 = /^\w{6,24}@[a-z0-9]{1,12}(\.[a-zA-Z]{2,4}){1,4}$/g;
		var user_mail = $("#user_mail").val();
		var bol = false;
		if (reg1.test(user_mail)) {
			$.ajax({
				type : "POST",
				url : "/sc_register/check_mail.html?user_mail=" + user_mail,
				async : false,
				success : function(data) {
					if (data == 1) {
						//邮箱可用
						$(".tishi1").css("visibility", "hidden");
						$(".cuo1").hide();
						$(".dui1").show();
						$(".mail").css("background-color", "white");
						$(".mail").css("border-color", "#ccc");
						$(".tishi1").css("visibility", "hidden");
						bol = true;
					} else if (data == 0) {
						//邮箱是空的
						$(".dui1").hide();
						$(".cuo1").show();
						$(".tishi1").css("visibility", "visible");
						$(".tishi1").html("请输入邮箱")
						$(".mail").css("background-color", "#FAB0AF");
						$(".mail").css("border-color", "red");
						bol = false;
					} else {//邮箱已注册
						$(".dui1").hide();
						$(".cuo1").show();
						$(".tishi1").css("visibility", "visible");
						$(".tishi1").html("您的邮箱已经被注册！")
						$(".mail").css("background-color", "#FAB0AF");
						$(".mail").css("border-color", "red");
						bol = false;
					}
				}
			});
		} else {
			$(".dui1").hide();
			$(".cuo1").show();
			$(".tishi1").css("visibility", "visible");
			$(".mail").css("background-color", "#FAB0AF");
			$(".mail").css("border-color", "red");
			bol = false;
		}
		return bol;
	}
	//发送邮箱验证码
	var validCode = true;
	function send_code(obj) {
		var user_mail = $("#user_mail").val();
		var time = 120;
		var code = $(obj);
		if (check_mail()) {
			//发送验证码
			$.post("/sc_register/send_mail.html", {
				'user_mail' : user_mail
			}, function(data) {
				if (data == 0) {
					$(".tishi2").css("visibility", "visible");
					$(".tishi2").html("系统发生错误，请重新发送邮箱验证码！");
				}
			})
			if (validCode) {
				validCode = false;
				var t = setInterval(function() {
					time--;
					code.html(time + "秒");
					if (time == 0) {
						clearInterval(t);
						code.html("重新获取");
						validCode = true;
					}
				}, 1000)
			}
		}
	}
	//验证验证码
	function check_code() {
		var bol = false;
		var mail_code = $("#mail_code").val();
		$.ajax({
			type : "POST",
			url : "/sc_register/check_code.html?mail_code=" + mail_code,
			async : false,
			success : function(data) {
				if (data == 1) {
					//验证码正确
//					$(".send1").hide();
					$(".tishi2").css("visibility", "hidden");
					$(".cuo2").hide();
					$(".dui2").show();
					$(".code").css("background-color", "white");
					$(".code").css("border-color", "#ccc");
					bol = true;
				} else if (data == 0) {
					//验证码是空的
					
					$(".dui2").hide();
					$(".cuo2").show();
					$(".tishi2").css("visibility", "visible");
					$(".tishi2").html("请输验证码");
					$(".code").css("background-color", "#FAB0AF");
					$(".code").css("border-color", "red");
					bol = false;
				} else {//验证码失效或者不正确
//					$(".send1").hide();
					$(".dui2").hide();
					$(".cuo2").show();
					$(".tishi2").css("visibility", "visible");
					$(".tishi2").html("您输入的验证码已失效，或者不正确！");
					$(".code").css("background-color", "#FAB0AF");
					$(".code").css("border-color", "red");
					bol = false;
				}
			}
		});
		return bol;
	}
	//验证昵称---后期再用
	function check_nickname(){
		var user_nickname=$("#user_nickname").val();
		if(user_nickname!=null&&user_nickname!=""){
			$(".tishi3").css("visibility", "hidden");
			$(".cuo3").hide();
			$(".dui3").show();
			$(".nicheng").css("background-color", "white");
			$(".nicheng").css("border-color", "#ccc");
		}
//		var reg3 = /^(\w|[\u4E00-\u9FA5]|\-){4,20}$/g;
//		if (reg3.test(user_nickname)) {
//			$(".tishi3").css("visibility", "hidden");
//			$(".cuo3").hide();
//			$(".dui3").show();
//			$(".nicheng").css("background-color", "white");
//			$(".nicheng").css("border-color", "#ccc");
//			return true;
//		} else {
//			$(".dui3").hide();
//			$(".cuo3").show();
//			$(".tishi3").css("visibility", "visible");
//			$(".nicheng").css("background-color", "#FAB0AF");
//			$(".nicheng").css("border-color", "red");
//			return false;
//		}
	}
	//检查密码
	function check_password(){
		var user_password=$("#user_password").val();
		var reg4 = /^[a-zA-Z0-9\x21-\x7e]{6,20}$/g;
		if (reg4.test(user_password)) {
			$(".tishi4").css("visibility", "hidden");
			$(".cuo4").hide();
			$(".dui4").show();
			$(".mima").css("background-color", "white");
			$(".mima").css("border-color", "#ccc");
			return true;
		} else {
			$(".dui4").hide();
			$(".cuo4").show();
			$(".tishi4").css("visibility", "visible");
			$(".mima").css("background-color", "#FAB0AF");
			$(".mima").css("border-color", "red");
			return false;
		}
	}
	//检查两次密码输入是否一致
	function check_confirm(){
		var user_password=$("#user_password").val();
		var confirm_pass = $("#confirm_pass").val();
		if(user_password==confirm_pass){
			$(".tishi5").css("visibility", "hidden");
			$(".cuo5").hide();
			$(".dui5").show();
			$(".mima2").css("background-color", "white");
			$(".mima2").css("border-color", "#ccc");
			return true;
		}else{
			$(".dui5").hide();
			$(".cuo5").show();
			$(".tishi5").css("visibility", "visible");
			$(".mima2").css("background-color", "#FAB0AF");
			$(".mima2").css("border-color", "red");
			return false;
		}
	}
	//获取注册成功页面
	function get_register_success() {
		$.post("/sc_register/get_register_success.html", function(data) {
			$(".zhucek").hide();
			$("#time_success").html(data);
			$("#zhuce_chuangkou").html("");
			$(".dengluk").hide();
			$(".coverfull").show();
			$(".zhuce_success").show();
		})
	}
	//注册用户
	function register_user(url,type){
		if ($("input[type='checkbox']").is(':checked')) {
			if(type=='phone'){
				 if(check_tel()&&check_phone_code()&&check_password()&&check_confirm()){
					 $("#myform").ajaxSubmit({
							type : 'post',
							url : "/sc_register/register.html",
							success : function(data) {
								if (data == 1) {//注册成功跳转登录窗口
									if(url!=null&&url!=""){
										location.href="/sc_register/to_register_success.html?jr_url="+url;
									}else{
										get_register_success();
									}
								}else if(data==3){
									alert("您注册的手机号已经被注册");
								} else {//注册失败
									alert("注册失败！");
								}
							},
							error : function(XmlHttpRequest, textStatus, errorThrown) {
								alert("注册失败！");
							}
						});
			     }
			}else{
				 if(check_mail()&&check_code()&&check_password()&&check_confirm()){
			    	 $("#myform").ajaxSubmit({
							type : 'post',
							url : "/sc_register/register.html",
							success : function(data) {
								if (data == 1) {//注册成功跳转登录窗口
									if(url!=null&&url!=""){
										location.href="/sc_register/to_register_success.html?jr_url="+url;
									}else{
										get_register_success();
									}
								}else if(data==3){
									alert("您注册的邮箱已经被注册");
								} else {//注册失败
									alert("注册失败！");
								}
							},
							error : function(XmlHttpRequest, textStatus, errorThrown) {
								alert("注册失败！");
							}
						});
			     }
			}
		 }else{
			 $(".tishi6").css("visibility", "visible");
		 }
	}
//返回登录页面
	function to_login(url){
		location.href="/sc_login/login_page.html?jr_url="+url;
	}
	//弹出协议窗口
	$(function(){
		$(".xieyi").click(function(){
			$(".coverfull1").show();
			$(".xieyi_chuang").show();
		})
		$("#btn").click(function(){
		$(".coverfull1").hide();
		$(".xieyi_chuang").hide();
		$("#check_xieyi").prop("checked",true);
	})
	})
	
	$(function(){
		$(".xieyi2").click(function(){
			$(".coverfull2").show();
			$(".xieyi_chuang2").show();
		})
		$("#btn").click(function(){
		$(".coverfull2").hide();
		$(".xieyi_chuang2").hide();
		$("#check_xieyi").prop("checked",true);
	})
	})
//获取手机注册页面
function get_register_phone(url){
		$.post("/sc_register/to_phone_register.html",{
			'jr_url':url
		},function(data){
			$(".login_way1").css("color","#06C1AE");
			$(".login_way2").css("color","#444");
			$("#register_page").html(data);
		})
	}
//获取邮箱注册页面	
function get_register_mail(url){
	$.post("/sc_register/to_mail_register.html",{
		'jr_url':url
	},function(data){
		$(".login_way2").css("color","#06C1AE");
		$(".login_way1").css("color","#444");
		$("#register_page").html(data);
	})
}
//窗口手机注册页面
function get_register_phone_chuangkou(){
	$.post("/sc_register/get_register_phone_chuangkou.html"
			,function(data){
		$(".login_way1").css("color","#06C1AE");
		$(".login_way2").css("color","#444");
		$("#register_chuangkou_page").html(data);
	})
}

//窗口邮箱注册页面
function get_register_mail_chuangkou(){
	$.post("/sc_register/get_register_mail_chuangkou.html"
			,function(data){
		$(".login_way2").css("color","#06C1AE");
		$(".login_way1").css("color","#444");
		$("#register_chuangkou_page").html(data);
	})
}
//检查手机号
function check_tel(){
	var reg1 = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
	var user_phone = $("#user_phone").val();
	var bol = false;
	if (reg1.test(user_phone)) {
		$.ajax({
			type : "POST",
			url : "/person/check_phone.html?user_phone=" + user_phone,
			async : false,
			success : function(data) {
				if (data == 1) {
					//手机号可用
					$(".tishi1").css("visibility", "hidden");
					$(".cuo1").hide();
					$(".dui1").show();
					$(".mail").css("background-color", "white");
					$(".mail").css("border-color", "#ccc");
					$(".tishi1").css("visibility", "hidden");
					bol = true;
				} else if (data == 0) {
					//手机号是空的
					$(".dui1").hide();
					$(".cuo1").show();
					$(".tishi1").css("visibility", "visible");
					$(".tishi1").html("请输入你要注册的手机号")
					$(".mail").css("background-color", "#FAB0AF");
					$(".mail").css("border-color", "red");
					bol = false;
				} else {//手机号已注册
					$(".dui1").hide();
					$(".cuo1").show();
					$(".tishi1").css("visibility", "visible");
					$(".tishi1").html("您的手机号已经被注册！")
					$(".mail").css("background-color", "#FAB0AF");
					$(".mail").css("border-color", "red");
					bol = false;
				}
			}
		});
	} else {
		$(".dui1").hide();
		$(".cuo1").show();
		$(".tishi1").css("visibility", "visible");
		$(".tishi1").html("请输入正确格式的手机号");
		$(".mail").css("background-color", "#FAB0AF");
		$(".mail").css("border-color", "red");
		bol = false;
	}
	return bol;
}

//发送手机验证码
function send_phone_code(obj) {
	var user_phone=$("#user_phone").val();
	var time = 120;
	var code = $(obj);
	if (check_tel()) {
		//发送验证码
		$.post("/person/send_phone.html", {
			'user_phone' : user_phone
		}, function(data) {
			if (data == 0) {
				$(".tishi2").css("visibility", "visible");
				$(".tishi2").html("系统发生错误，请重新发送手机验证码！");
			}
		})
		if (validCode) {
			validCode = false;
			var t = setInterval(function() {
				time--;
				code.html(time + "秒");
				if (time == 0) {
					clearInterval(t);
					code.html("重新获取");
					validCode = true;
				}
			}, 1000)
		}
	}
}
//验证手机验证码
function check_phone_code(){
	var bol = false;
	var phone_code = $("#phone_code").val();
	$.ajax({
		type : "POST",
		url : "/person/check_phone_code.html?phone_code=" + phone_code,
		async : false,
		success : function(data) {
			if (data == 1) {
				//验证码正确
//				$(".send1").hide();
				$(".tishi2").css("visibility", "hidden");
				$(".cuo2").hide();
				$(".dui2").show();
				$(".code").css("background-color", "white");
				$(".code").css("border-color", "#ccc");
				bol = true;
			} else if (data == 0) {
				//验证码是空的
				
				$(".dui2").hide();
				$(".cuo2").show();
				$(".tishi2").css("visibility", "visible");
				$(".tishi2").html("请输验证码");
				$(".code").css("background-color", "#FAB0AF");
				$(".code").css("border-color", "red");
				bol = false;
			} else {//验证码失效或者不正确
//				$(".send1").hide();
				$(".dui2").hide();
				$(".cuo2").show();
				$(".tishi2").css("visibility", "visible");
				$(".tishi2").html("您输入的验证码已失效，或者不正确！");
				$(".code").css("background-color", "#FAB0AF");
				$(".code").css("border-color", "red");
				bol = false;
			}
		}
	});
	return bol;
}