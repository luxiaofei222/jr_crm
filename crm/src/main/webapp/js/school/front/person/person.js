$(function() {
	$(".person_nav ul li span").click(function() {
		$(this).parent().next(".person_nav_child").slideToggle();
		$(this).toggleClass("fangxiang");
	});
});

$(function() {
	$(".person_nav_li").click(function() {
		$(this).addClass("on");
		$(".person_nav_li").not(this).removeClass("on");
		$(".person_nav_child ul li").not(this).removeClass("on");
	});
	$(".person_nav_child ul li").click(function() {
		$(this).addClass("on");
		$("li.nav_child_li").not(this).removeClass("on");
	});

});
//加载动画
function jiazai() {
	$("#person_menu_list")
			.html("<div id='preloader6'>" +
					"<span></span><span></span>" +
					"<span></span><span></span>" +
					"</div>");
}
/**/
// 个人信息
function get_person() {
	location.href = "/person/get_person_index.html";
}
// 我的课程
function get_my_course() {
	jiazai();
	var pageNumber = 1;
	var limit = 5;
	$.post("/person/get_mycourse.html", {
		'pageNumber' : pageNumber,
		'limit' : limit
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}
// 我的课程翻页：上一页，下一页
function mycourse_jump_page(page) {
	jiazai();
	var limit = 5;
	$.post("/person/get_mycourse.html", {
		'pageNumber' : page,
		'limit' : limit
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}
// 获取我的订单
function get_my_order() {
	jiazai();
	var pageNumber = 1;
	var limit = 5;
	$.post("/person/get_mycourse_order.html", {
		'pageNumber' : pageNumber,
		'limit' : limit
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}
//查询用户成绩
function get_user_grade(){
	jiazai();
	$.post("/f_grade/get_user_grade.html",
		function(data) {
		$("#person_menu_list").html(data);
	});
}

// 我的订单翻页。上一页，下一页
function mycourse_order_jump_page(page) {
	jiazai();
	var limit = 5;
	$.post("/person/get_mycourse_order.html", {
		'pageNumber' : page,
		'limit' : limit
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}
// 绑定手机
function bangding_phone() {
	$("#bg").css({
		display : "block",
		height : $(document).height()
	});
	var $box = $('#phone');
	$box.css({
		// 设置弹出层距离左边的位置
		left : ($("body").width() - $box.width()) / 2 - 20 + "px",
		// 设置弹出层距离上面的位置
		top : ($(window).height() - $box.height()) / 2 + $(window).scrollTop()
				+ "px",
		display : "block"
	});
}
$(function() {
	// 点击关闭按钮的时候，遮罩层关闭
	$(".close").click(function() {
		$("#bg,.box").css("display", "none");
	});
});
// 修改绑定邮箱
function update_bangding_youxiang() {
	$("#bg").css({
		display : "block",
		height : $(document).height()
	});
	var $box = $('#update_bangding_youxiang');
	$box.css({
		// 设置弹出层距离左边的位置
		left : ($("body").width() - $box.width()) / 2 - 20 + "px",
		// 设置弹出层距离上面的位置
		top : ($(window).height() - $box.height()) / 2 + $(window).scrollTop()
				+ "px",
		display : "block"
	});
}
// 检测邮箱
function up_check_mail() {
	var reg1 = /^\w{6,24}@[a-z0-9]{1,12}(\.[a-zA-Z]{2,4}){1,4}$/g;
	var user_mail = $("#up_user_mail").val();
	var bol = false;
	if (reg1.test(user_mail)) {
		$.ajax({
			type : "POST",
			url : "/sc_register/check_mail.html?user_mail=" + user_mail,
			async : false,
			success : function(data) {
				if (data == 1) {
					// 邮箱可用
					$(".error").html("");
					bol = true;
				} else if (data == 0) {
					// 邮箱是空的
					$(".error").html("请输入邮箱");
					bol = false;
				} else {// 邮箱已注册
					$(".error").html("您的邮箱已经被注册！");
					bol = false;
				}
			}
		});
	} else {
		$(".error").html("请输入正确的邮箱");
		bol = false;
	}
	return bol;
}
// 验证验证码
function check_code() {
	var bol = false;
	var mail_code = $("#mail_code").val();
	$.ajax({
		type : "POST",
		url : "/sc_register/check_code.html?mail_code=" + mail_code,
		async : false,
		success : function(data) {
			if (data == 1) {
				// 验证码正确
				$(".error").html("");
				// $(".send1").hide();
				bol = true;
			} else if (data == 0) {
				// 验证码是空的
				$(".error").html("请输验证码");
				bol = false;
			} else {// 验证码失效或者不正确
				$(".error").html("您输入的验证码已失效，或者不正确！");
				bol = false;
			}
		}
	});
	return bol;
}
// 修改绑定邮箱
function update_mail(user_id) {
	var user_mail = $("#up_user_mail").val();
	if (up_check_mail() && check_code()) {
		$.post("/person/update_bangding.html", {
			'user_id' : user_id,
			'user_mail' : user_mail
		}, function(data) {
			if (data == 1) {
				location.href = "/person/get_person_index.html";
			}
		})
	}
}
// 发送邮箱验证码
var validCode = true;
function send_code(obj) {
	var user_mail = $("#up_user_mail").val();
	var time = 120;
	var code = $(obj);
	if (up_check_mail()) {
		// 发送验证码
		$.post("/sc_register/send_mail.html", {
			'user_mail' : user_mail
		}, function(data) {
			if (data == 0) {
				$(".error").html("系统发生错误，请重新发送邮箱验证码！");
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
// 修改用户信息
function person_update(user_id) {
	jiazai();
	$.post("/person/get_update_person.html", {
		'user_id' : user_id
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}
// 获取城市名称
function get_city() {
	var id = $("#province").val();
	$.ajax({
		type : "POST",// 请求方式
		url : "/person/get_city_erji.html?id=" + id,// 地址，就是action请求路径
		data : {
			"id" : id
		},
		dataType : "json",// 数据类型text xml json script jsonp
		success : function(msg) {// 返回的参数就是 action里面所有的有get和set方法的参数
			var ruleListTemp = "";
			$.each(msg, function(i, item) {
				ruleListTemp += ("<option value='" + item.name + "'>"
						+ item.name + "</option>");
			});
			$("#user_city").html(ruleListTemp);
		}
	});
}
// 获取我的课件
function get_mycourseware() {
	jiazai();
	var pageNumber = 1;
	var limit = 8;
	$.post("/person/get_my_courseware.html", {
		'pageNumber' : pageNumber,
		'limit' : limit
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}
// 获取发送消息页面
function send_message() {
	jiazai();
	$.post("/person/get_message_main.html", function(data) {
		$("#person_menu_list").html(data);
	})
}
// 我的课件翻页
function ware_jump_page(page) {
	jiazai();
	var limit = 8;
	$.post("/person/get_my_courseware.html", {
		'pageNumber' : page,
		'limit' : limit
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}
// 获取收件箱
function get_inbox() {
	jiazai();
	var pageNumber = 1;
	var limit = 8;
	$.post("/sc_message/get_message_list.html", {
		'pageNumber' : pageNumber,
		'limit' : limit
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}
// 收件箱翻页
function message_jump_page(page) {
	jiazai();
	var limit = 8;
	$.post("/sc_message/get_message_list.html", {
		'pageNumber' : page,
		'limit' : limit
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}
// 查看信件详情
function get_message_xiangqing(message_id) {
	jiazai();
	$.get("/sc_message/get_message_details.html", {
		'message_id' : message_id
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}
// 检测手机号
function check_user_phone() {
	var user_phone = $("#user_phone").val();
	var reg1 = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))\d{8}$/;
	var bol = false;
	if (reg1.test(user_phone)) {
		$.ajax({
			type : "POST",
			url : "/person/check_phone.html?user_phone=" + user_phone,
			async : false,
			success : function(data) {
				if (data == 1) {
					// 手机号可以用
					$(".error").html("");
					bol = true;
				} else if (data == 0) {
					// 手机号是空的
					$(".error").html("请输入手机号");
					bol = false;
				} else {// 手机号已注册
					$(".error").html("您的手机号已经被注册！");
					bol = false;
				}
			}
		});
	} else {
		$(".error").html("请输入正确的手机号");
		bol = false;
	}
	return bol;
}
// 发送手机验证码
function send_phone_code(obj) {
	var user_phone = $("#user_phone").val();
	var time = 120;
	var code = $(obj);
	if (check_user_phone()) {
		// 发送验证码
		$.post("/person/send_phone.html", {
			'user_phone' : user_phone
		}, function(data) {
			if (data == 0) {
				$(".error").html("系统发生错误，请重新发送手机验证码！");
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
// 检查手机验证码
function check_phone_code() {
	var bol = false;
	var phone_code = $("#phone_code").val();
	$.ajax({
		type : "POST",
		url : "/person/check_phone_code.html?phone_code=" + phone_code,
		async : false,
		success : function(data) {
			if (data == 1) {
				// 验证码正确
				$(".error").html("");
				// $(".send1").hide();
				bol = true;
			} else if (data == 0) {
				// 验证码是空的
				$(".error").html("请输验证码");
				bol = false;
			} else {// 验证码失效或者不正确
				$(".error").html("您输入的验证码已失效，或者不正确！");
				bol = false;
			}
		}
	});
	return bol;
}
// 绑定手机号
function update_phone(user_id) {
	var user_phone = $("#user_phone").val();
	if (check_user_phone() && check_phone_code()) {
		$.post("/person/update_bangding.html", {
			'user_id' : user_id,
			'user_phone' : user_phone
		}, function(data) {
			if (data == 1) {
				location.href = "/person/get_person_index.html";
			}
		})
	}
}
// 获取密码与安全页面
function get_safe() {
	jiazai();
	$.post("/person/get_safe_first.html", function(data) {
		$("#person_menu_list").html(data);
	})
}
// 第一步的下一步
function xiayibu_safe() {
	var val = $(".xuanzhong").children("input").val();
	if (val != "" && val != null) {
		if (val == 'phone') {
			// 手机验证
			var user_phone = $("#user_phone").val();
			$.post("/person/send_phone.html", {
				'user_phone' : user_phone
			}, function(data) {
				if (data == 0) {
					$(".no_phone").html("系统发生错误，请重新发送手机验证码！");
				} else {
					get_safe_second("phone");
				}
			})
		} else {
			// 邮箱验证
			var user_mail = $("#user_mail").val();
			$.post("/sc_register/send_mail.html", {
				'user_mail' : user_mail
			}, function(data) {
				if (data == 0) {
					$(".no_phone").html("系统发生错误，请重新发送邮箱验证码！");
				} else {
					get_safe_second("mail");
				}
			})
		}
	} else {
		$(".no_phone").html("请选择您要验证修改密码的方式！")
	}
}
// 安全与密码第二步
function get_safe_second(type) {
	jiazai();
	$.post("/person/get_safe_second.html", {
		'type' : type
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}
// 安全与密码第二步验证验证码
function check_safe_code() {
	var bol = false;
	var code = $("#code").val();
	$.ajax({
		type : "POST",
		url : "/person/check_safe_code.html?code=" + code,
		async : false,
		success : function(data) {
			if (data == 0) {
				$("#tubiao").hide();
				$(".no_phone").html("请输入验证码");
				bol = false;
			} else if (data == 2) {
				$("#tubiao").hide();
				$(".no_phone").html("验证码输入超时或者不正确！");
				bol = false;
			} else {
				$("#tubiao").show();
				$(".no_phone").html("");
				bol = true;
			}
		}
	});
	return bol;
}
// 第三步，修改密码页面
function get_third_safe_page() {
	if (check_safe_code()) {
		jiazai();
		$.post("/person/get_safe_third.html", function(data) {
			$("#person_menu_list").html(data);
		})
	}
}
function check_safe_pass() {
	var new_password = $("#new_password").val();
	if (new_password != null && new_password != "") {
		$(".no_phone").html("");
		$("#tubiao1").show();
		return true;
	} else {
		$(".no_phone").html("请输入您的新密码");
		$("#tubiao1").hide();
		return false;
	}
}
function check_safe_confirm() {
	var confirm_new_password = $("#confirm_new_password").val();
	var new_password = $("#new_password").val();
	if (new_password != null && new_password != "") {
		if (confirm_new_password == new_password) {
			$(".no_phone").html("");
			$("#tubiao2").show();
			return true;
		} else {
			$("#tubiao2").hide();
			$(".no_phone").html("您两次密码输入的不一致！");
			return false;
		}
	} else {
		$("#tubiao2").hide();
		$(".no_phone").html("请输入您的新密码");
		return false;
	}
}
// 修改密码
function update_safe_pass(user_id) {
	var new_password = $("#new_password").val();
	if (check_safe_pass() && check_safe_confirm()) {
		$.post("/person/update_safe_pass.html", {
			'user_id' : user_id,
			'new_password' : new_password
		}, function(data) {
			if (data == 1) {
				get_safe_success();
			} else {
				alert("修改失败，系统发生问题！")
			}
		})
	}
}
// 跳转修改成功页面
function get_safe_success() {
	jiazai();
	$.post("/person/get_safe_success.html", function(data) {
		$("#person_menu_list").html(data);
	})
}

$(function() {
	$.post("/sc_message/get_weidu_message_num.html", function(data) {
		$("#weidu_number").html(data);
	})
})
// 查看订单详情页面
function get_xiangqing(order_id) {
	jiazai();
	$.post("/sc_pay/get_order_details.html", {
		'order_id' : order_id
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}
//获取我的报名信息
function get_entry_info(){
	jiazai();
	$.post("/person/get_entry_info.html", function(data) {
		$("#person_menu_list").html(data);
	})
}

//更换头像
function change_user_pic(user_id){
	 layer.open({
		  type: 2,
		  title: ['更换用户头像'],
		  area: ['900px', '700px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/person/to_change_user_pic.html?user_id="+user_id,
		  end: function () {
			  location.reload()
         }
		  });
}
//学习记录
function get_user_study_record(video_id) {
	jiazai();
	$.post("/video_record/get_user_study_record.html", {
		'video_id':video_id
	}, function(data) {
		$("#person_menu_list").html(data);
	})
}