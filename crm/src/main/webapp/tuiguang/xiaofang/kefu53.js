// 53客服代码
/*(function() {
	var _53code = document.createElement("script");
	_53code.src = "//tb.53kf.com/code/code/10147174/1";
	var s = document.getElementsByTagName("script")[0];
	s.parentNode.insertBefore(_53code, s);
})();*/
// 点击相关按钮弹出来53客服窗口
function kefu53() {
	window.open('http://tb.53kf.com/code/client/10147174/1]');
}
//点击电话出来百度离线宝
function lixianbao() {
	window.open('http://dwz.cn/4RExyN');
}
//点击跳转活动页面
function active() {
	window.open('http://www.jingrenedu.com/tuiguang/jingren_activity.html');
}
// 点击相关按钮弹出来网校页面
function wangxiao() {
	window.open('http://www.jingrenedu.com');
}
// 猜你喜欢关闭和打开
$(function(){

$(".close").click(function() {
	$(".like").stop(true, true).animate({
		"margin-left" : "-5000px"
	});
	$(".cai_link").css("display", "block");
	$(".like").css("background", "none");
})
$(".cai_link").click(function() {
	$(".like").stop(true, true).animate({
		"margin-left" : "0px"
	});
	$(".cai_link").css("display", "none");
	$(".like").css("background", "#fafafa");
})
})
$(function() {
	// 倒计时
	// 本周结束日期
	var now = new Date();
	var start = new Date();
	var end = new Date();
	var n = now.getDay();
	end.setDate(now.getDate() - n + 7);
	var fnTimeCountDown = function(d, o) {
		var f = {
			zero : function(n) {
				var n = parseInt(n, 10);
				if (n > 0) {
					if (n <= 9) {
						n = "0" + n;
					}
					return String(n);
				} else {
					return "00";
				}
			},
			dv : function() {
				d = d || Date.UTC(2050, 0, 1); // 如果未定义时间，则我们设定倒计时日期是2050年1月1日
				var future = new Date(d), now = new Date();
				// 现在将来秒差值
				var dur = Math.round((future.getTime() - now.getTime()) / 1000)
						+ future.getTimezoneOffset() * 60, dur_minisec = (Math
						.random()) * 1000, pms = {
					minisec : "000",
					sec : "00",
					mini : "00",
					hour : "00",
					day : "00",
					month : "00",
					year : "0"
				};
				if (dur > 0) {
					pms.minisec = f.zero(dur_minisec);
					pms.sec = f.zero(dur % 60);
					pms.mini = Math.floor((dur / 60)) > 0 ? f.zero(Math
							.floor((dur / 60)) % 60) : "00";
					pms.hour = Math.floor((dur / 3600)) > 0 ? f.zero(Math
							.floor((dur / 3600)) % 24) : "00";
					pms.day = Math.floor((dur / 86400)) > 0 ? f.zero(Math
							.floor((dur / 86400)) % 30) : "00";
					// 月份，以实际平均每月秒数计算
					pms.month = Math.floor((dur / 2629744)) > 0 ? f.zero(Math
							.floor((dur / 2629744)) % 12) : "00";
					// 年份，按按回归年365天5时48分46秒算
					pms.year = Math.floor((dur / 31556926)) > 0 ? Math
							.floor((dur / 31556926)) : "0";
				}
				return pms;
			},
			ui : function() {
				if (o.minisec) {
					o.minisec.innerHTML = f.dv().minisec;
				}
				if (o.sec) {
					o.sec.innerHTML = f.dv().sec;
				}
				if (o.mini) {
					o.mini.innerHTML = f.dv().mini;
				}
				if (o.hour) {
					o.hour.innerHTML = f.dv().hour;
				}
				if (o.day) {
					o.day.innerHTML = f.dv().day;
				}
				if (o.month) {
					o.month.innerHTML = f.dv().month;
				}
				if (o.year) {
					o.year.innerHTML = f.dv().year;
				}
				setTimeout(f.ui, 1);
			}
		};
		f.ui();
	};
	var zxx = {
		$ : function(id) {
			return document.getElementById(id);
		},
		futureDate : Date.UTC(end.getFullYear(), end.getMonth() + 1, end
				.getDate(), 12),
		obj : function() {
			return {
				minisec : zxx.$("minisec"),
				sec : zxx.$("sec"),
				mini : zxx.$("mini"),
				hour : zxx.$("hour"),
				day : zxx.$("day"),
				month : zxx.$("month"),
				year : zxx.$("year")
			}
		}
	};
	fnTimeCountDown(zxx.futureDate, zxx.obj());
	// 第十二版块的时间差
	var today = new Date();// 开始时间
	var date = new Date(2017, 10, 12);// 结束时间
	var datec = date.getTime() - today.getTime();// 时间差的毫秒数
	var days = 0;
	days = Math.floor(datec / (24 * 3600 * 1000));// 计算出相差天数
	$(".xiaofang12_time2").html(days);

	// 资讯部分被选中
	$(".xiaofang19_textzixunul li").click(function() {
		$(this).addClass("xuanzhong").siblings().removeClass("xuanzhong");
	})
})

// 保存资讯内容
function save_intent(obj) {
	var reg = /^(13[0-9]|15[0-9]|18[0-9]|17[0-9])[0-9]{8}$/g;
	var yixiang_province = $("#yixiang_province").val();
	var city = $("#city").val();
	var phone = $("#phone").val();
	if (yixiang_province != 0) {
		if (city != 0) {
			if (phone != null && phone != "") {
				if (reg.test(phone)) {
					 $(obj).attr('disabled',"true");
					 $(obj).html("提交中");
					$.post("/tuiguang/save_yixiang_xiaofang.html", {
						'province' : yixiang_province,
						'yixiang_city' : city,
						'yixiang_phone' : phone
					}, function(data) {
						alert("提交成功！");
						kefu53();
						$(obj).html("立即查询");
						$(obj).removeAttr("disabled");
					})
				} else {
					alert("请输入正确的手机号！");
				}
			} else {
				alert("请输入您的手机号！");
			}
		} else {
			alert("请选择您所在城市！");
		}
	} else {
		alert("请选择您所在省份！");
	}
}
// 获取城市名称
function get_city() {
	var id = $("#yixiang_province").val();
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
			$("#city").html(ruleListTemp);
		}
	});
}

function donw_load(){
	location.href="/template/消防工程师考试报名表.xls"
}
function denglu(){
	location.href="http://www.jingrenedu.com/person/get_person_index.html"
}