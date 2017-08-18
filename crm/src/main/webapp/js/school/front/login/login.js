function login_check_mail(){
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
function login_check_password(){
	var user_password=$("#user_password").val();
	if (user_password!=null&&user_password!="") {
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
function user_login(url,type){
	if(type=='phone'){
		if(check_tel()&&login_check_password()){
			$(".zhucebu").css("background","#ccc");
			$(".zhucebu").html("登录中…");
			 $("#myform").ajaxSubmit({
					type : 'post',
					url : "/sc_login/login.html",
					success : function(data) {
						if (data == 1) {//注册成功跳转登录窗口
							if(url!=null&&url!=""){
								location.href=url;//登陆成功
							}else{
								window.location.reload();//登陆成功
							}
						} else if(data==0){//用户名错误
							$(".dui1").hide();
							$(".cuo1").show();
							$(".zhucebu").css("background","#06C1AE");
							$(".zhucebu").html("登录");
							$(".tishi1").css("visibility", "visible");
							$(".tishi1").html("您输入的手机号不存在")
							$(".mail").css("background-color", "#FAB0AF");
							$(".mail").css("border-color", "red");
						} else if(data==2){//密码错误
							$(".tishi4").css("visibility", "visible");
							$(".tishi4").html("您输入的密码错误")
							$(".zhucebu").css("background","#06C1AE");
							$(".zhucebu").html("登录");
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
		if(login_check_mail()&&login_check_password()){
			$(".zhucebu").css("background","#ccc");
			$(".zhucebu").html("登录中…");
			 $("#myform").ajaxSubmit({
					type : 'post',
					url : "/sc_login/login.html",
					success : function(data) {
						if (data == 1) {//注册成功跳转登录窗口
							if(url!=null&&url!=""){
								location.href=url;//登陆成功
							}else{
								window.location.reload();//登陆成功
							}
						} else if(data==0){//用户名错误
							$(".dui1").hide();
							$(".cuo1").show();
							$(".zhucebu").css("background","#06C1AE");
							$(".zhucebu").html("登录");
							$(".tishi1").css("visibility", "visible");
							$(".tishi1").html("您输入的邮箱不存在")
							$(".mail").css("background-color", "#FAB0AF");
							$(".mail").css("border-color", "red");
						} else if(data==2){//密码错误
							$(".tishi4").css("visibility", "visible");
							$(".tishi4").html("您输入的密码错误")
							$(".zhucebu").css("background","#06C1AE");
							$(".zhucebu").html("登录");
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
//注册页面
function to_register(url){
	location.href="/sc_register/register_page.html?jr_url="+url;
}
//找回密码页面
function find_pass_page(url,type){
	location.href="/sc_register/to_find_pass.html?jr_url="+url+"&type="+type;
}

//检查手机号
	function check_tel(){
		var user_phone=$("#user_phone").val();
		var reg6 =/^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))\d{8}$/;
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
//手机登录页面
function get_phone_page(){
	$.post("/sc_login/get_phone_login.html",
			function(data){
		$(".login_way1").css("color","#06C1AE");
		$(".login_way2").css("color","#444");
		$("#login_page").html(data);
	})
}
//获取邮箱登录页面
function get_mail_page(){
	$.post("/sc_login/get_mail_page.html",
			function(data){
		$(".login_way2").css("color","#06C1AE");
		$(".login_way1").css("color","#444");
		$("#login_page").html(data);
	})
}