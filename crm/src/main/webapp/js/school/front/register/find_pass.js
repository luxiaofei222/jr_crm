function find_check_mail() {
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
					$(".dui1").hide();
					$(".cuo1").show();
					$(".tishi1").css("visibility", "visible");
					$(".tishi1").html("您输入的邮箱不存在！")
					$(".mail").css("background-color", "#FAB0AF");
					$(".mail").css("border-color", "red");
					bol = false;
				} else if (data == 0) {
					//邮箱是空的
					$(".dui1").hide();
					$(".cuo1").show();
					$(".tishi1").css("visibility", "visible");
					$(".tishi1").html("请输入邮箱")
					$(".mail").css("background-color", "#FAB0AF");
					$(".mail").css("border-color", "red");
					bol = false;
				}  else if (data == 2) {//邮箱已注册
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
function find_send_code(obj) {
	var user_mail = $("#user_mail").val();
	var time = 120;
	var code = $(obj);
	if (find_check_mail()) {
		//发送验证码
		$.post("/sc_register/send_findpass_mail.html", {
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
function find_check_code() {
	var bol = false;
	var mail_code = $("#mail_code").val();
	$.ajax({
		type : "POST",
		url : "/sc_register/check_findpass_code.html?mail_code=" + mail_code,
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
//检查密码
function find_check_password(){
	var user_password=$("#user_password").val();
	var reg4 = /^[a-zA-Z0-9\x21-\x7e]{6,20}$/g;
	if (reg4.test(user_password)) {
		$(".tishi4").css("visibility", "hidden");
		$(".cuo3").hide();
		$(".dui3").show();
		$(".mima").css("background-color", "white");
		$(".mima").css("border-color", "#ccc");
		return true;
	} else {
		$(".dui3").hide();
		$(".cuo3").show();
		$(".tishi4").css("visibility", "visible");
		$(".mima").css("background-color", "#FAB0AF");
		$(".mima").css("border-color", "red");
		return false;
	}
}
//检查两次密码输入是否一致
function find_check_confirm(){
	var user_password=$("#user_password").val();
	var confirm_pass = $("#confirm_pass").val();
	if(user_password==confirm_pass){
		$(".tishi5").css("visibility", "hidden");
		$(".cuo4").hide();
		$(".dui4").show();
		$(".mima2").css("background-color", "white");
		$(".mima2").css("border-color", "#ccc");
		return true;
	}else{
		$(".dui4").hide();
		$(".cuo4").show();
		$(".tishi5").css("visibility", "visible");
		$(".mima2").css("background-color", "#FAB0AF");
		$(".mima2").css("border-color", "red");
		return false;
	}
}
//找回密码成功页面
function get_update_pass_success() {
	$.post("/sc_register/get_updatepass_success.html", function(data) {
		$(".backpsd").hide();
		$("#time_success").html(data);
		$("#findpass_chuangkou").html("");
		$(".dengluk").hide();
		$(".coverfull").show();
		$(".findpass_success").show();
	})
}
//修改密码
function update_pass_user(url,type){
	if(type=='phone'){
		 if(find_check_phone()&&find_check_phone_code()&&find_check_password()&&find_check_confirm()){
	    	 $("#myform").ajaxSubmit({
					type : 'post',
					url : "/sc_register/update_pass.html",
					success : function(data) {
						if (data == 1) {//注册成功跳转登录窗口
							if(url!=null&&url!=""){
								location.href="/sc_register/to_find_pass_success.html?jr_url="+url;
							}else{
								get_update_pass_success();
							}
						} else {//注册失败
							alert("修改失败！");
						}
					},
					error : function(XmlHttpRequest, textStatus, errorThrown) {
						alert("修改失败！");
					}
				});
	     }
	}else{
		 if(find_check_mail()&&find_check_code()&&find_check_password()&&find_check_confirm()){
	    	 $("#myform").ajaxSubmit({
					type : 'post',
					url : "/sc_register/update_pass.html",
					success : function(data) {
						if (data == 1) {//注册成功跳转登录窗口
							if(url!=null&&url!=""){
								location.href="/sc_register/to_find_pass_success.html?jr_url="+url;
							}else{
								get_update_pass_success();
							}
						} else {//注册失败
							alert("修改失败！");
						}
					},
					error : function(XmlHttpRequest, textStatus, errorThrown) {
						alert("修改失败！");
					}
				});
	     }
	}
}

//检测手机号
function find_check_phone() {
	var reg1 = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))\d{8}$/;
	var user_phone = $("#user_phone").val();
	var bol = false;
	if (reg1.test(user_phone)) {
		$.ajax({
			type : "POST",
			url : "/person/check_phone.html?user_phone=" + user_phone,
			async : false,
			success : function(data) {
				if (data == 1) {
					//邮手机号不存在
					$(".dui1").hide();
					$(".cuo1").show();
					$(".tishi1").css("visibility", "visible");
					$(".tishi1").html("您输入的手机号没有注册！")
					$(".mail").css("background-color", "#FAB0AF");
					$(".mail").css("border-color", "red");
					bol = false;
				} else if (data == 0) {
					//手机号是空的
					$(".dui1").hide();
					$(".cuo1").show();
					$(".tishi1").css("visibility", "visible");
					$(".tishi1").html("请输入您的手机号")
					$(".mail").css("background-color", "#FAB0AF");
					$(".mail").css("border-color", "red");
					bol = false;
				}  else if (data == 2) {//手机号存在可以用
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
//手机号发送验证码
function find_send_phone_code(obj){
	var user_phone=$("#user_phone").val();
	var time = 120;
	var code = $(obj);
	if (find_check_phone()) {
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
function find_check_phone_code(){
	var bol = false;
	var phone_code = $("#phone_code").val();
	$.ajax({
		type : "POST",
		url : "/person/check_phone_code.html?phone_code=" + phone_code,
		async : false,
		success : function(data) {
			if (data == 1) {
				//验证码正确
				$(".error").html("");
//				$(".send1").hide();
				bol = true;
			} else if (data == 0) {
				//验证码是空的
				$(".error").html("请输验证码");
				bol = false;
			} else {//验证码失效或者不正确
				$(".error").html("您输入的验证码已失效，或者不正确！");
				bol = false;
			}
		}
	});
	return bol;
}
