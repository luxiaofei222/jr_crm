//第一块课程分类
$(function() {
	$(".course_name_fl").click(
			function() {
				var index = $(this).index();
				$(".nr_kc_totle").eq(index).css("display", "block").siblings(
						".nr_kc_totle").css("display", "none");
				$(".course_name_fl").eq(index).addClass("select_kc").siblings()
						.removeClass("select_kc");
			})
})

// 滚动后导航固定
$(function() {
	$(window).scroll(function() {
		height = $(window).scrollTop();
		if (height > 630) {
			$('#menu').fadeIn();
		} else {
			$('#menu').fadeOut();
		}
		;
	});
})
$(function() {
	// 左侧菜单到相应位置出现
	var a = $(window).width();
	var b = (a - 1200) / 2;
	var c = b - 100;
	var d = c / 2;
	if (b < 0) {
		$("#menu").css('display', 'none');
	} else if (b >= 0 && b < 100) {
		$("#menu").css('left', '0px');
	} else if (b >= 100 && b < 500) {
		$("#menu").css('left', d);
	}
	// 左侧菜单到改变位置出现
	$(window).resize(function() {
		var a = $(window).width();
		var b = (a - 1200) / 2;
		var c = b - 100;
		var d = c / 2;
		if (b < 0) {
			$("#menu").css('display', 'none');
		} else if (b >= 0 && b < 100) {
			$("#menu").css('left', '0px');
		} else if (b >= 100 && b < 500) {
			$("#menu").css('left', d);
		}
	});
})
// 获取注册页面
function get_register() {
	$.post("/sc_register/get_register.html", function(data) {
		$("#zhuce_chuangkou").html(data);
		$(".dengluk").hide();
		$(".coverfull").show();
		$(".zhucek").show();
	})
}

//获取登录页面
function get_login() {
	$.post("/sc_login/get_login.html", function(data) {
		$("#login_chuangkou").html(data);
		$(".backpsd").hide();
		$(".zhucek").hide();
		$(".coverfull").show();
		$(".dengluk").show();
	})
}
//获取找回密码
function find_pass(type){
	$.post("/sc_login/get_update_pass.html",{
		'type':type
	},function(data) {
		$("#findpass_chuangkou").html(data);
		$(".dengluk").hide();
		$("#login_chuangkou").html("");
		$(".coverfull").show();
		$(".backpsd").show();
	})
}

//关闭注册框
function colse_zhuce(){
	$(".coverfull").hide();
	$(".zhucek").hide();
	$("#zhuce_chuangkou").html("");
}
//关闭登录框
function colse_denglu(){
	$(".coverfull").hide();
	$(".dengluk").hide();
	$("#login_chuangkou").html("");
}
//关闭找回密码
function colse_find(){
	$(".coverfull").hide();
	$(".backpsd").hide();
	$("#findpass_chuangkou").html("");
}
// 注册框
$(function() {
	$(":input").focus(function() {
		$(this).addClass("focus");
	})
	$(":input").blur(function() {
		$(this).removeClass("focus");
	});
});
// 点击登录时
$(function() {
	$(":input").focus(function() {
		$(this).addClass("focus");
	})
	$(":input").blur(function() {
		$(this).removeClass("focus");
	});
});
//刷新当前页
function reload(){
	window.location.reload();
}

//左侧菜单到改变位置出现
$(window).resize(function(){
	var a = $(window).width();
	if (a<1200){
		$(".toolbar").css('display', 'none');
	}
});
//返回顶部
$(function(){
	$("#top").click(function () {
        var speed=200;//滑动的速度
        $('body,html').animate({ scrollTop: 0 }, speed);
        return false;
 });
})
//li点击样式
$(function() {
	var parent, ink, d, x, y;
	$("ul.course_name li.course_name_fl a").click(function(e){
		parent = $(this).parent();
		//create .ink element if it doesn't exist
		if(parent.find(".ink").length == 0)
			parent.prepend("<span class='ink'></span>");
			
		ink = parent.find(".ink");
		//incase of quick double clicks stop the previous animation
		ink.removeClass("animate");
		
		//set size of .ink
		if(!ink.height() && !ink.width())
		{
			//use parent's width or height whichever is larger for the diameter to make a circle which can cover the entire element.
			d = Math.max(parent.outerWidth(), parent.outerHeight());
			ink.css({height: d, width: d});
		}
		
		//get click coordinates
		//logic = click coordinates relative to page - parent's position relative to page - half of self height/width to make it controllable from the center;
		x = e.pageX - parent.offset().left - ink.width()/2;
		y = e.pageY - parent.offset().top - ink.height()/2;
		
		//set the position and add class .animate
		ink.css({top: y+'px', left: x+'px'}).addClass("animate");
	})
})
/*顶部导航处的资讯，划过时出现列表*/
$(function(){
	$(".zixunshow").mouseover(function(){
		$(".zixun_listinfo").show();
	})
	$(".zixun_listinfo").mouseover(function(){
		$(".zixun_listinfo").show();
	})
	$(".zixun_listinfo").mouseout(function(){
		$(".zixun_listinfo").hide();
	})
})
//友情链接tab切换
$(function() {
	$(".link_title").click(
			function() {
				var index = $(this).index();
				$(".cooper_list").eq(index).css("display", "block").siblings(
						".cooper_list").css("display", "none");
				$(".link_title").eq(index).addClass("link_active").siblings()
						.removeClass("link_active");
			})
})