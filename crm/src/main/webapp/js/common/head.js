
$(function() {
	// 我的购物车数量
	$.post("/sc_myshoping/get_my_shoping_number.html", function(data) {
		$("#myshoping_number").html(data);
	})
})

$(function() {
	$(".nicheng").click(function(e) {
		$(".nicheng_info").show();
		$(".nav_dl1").css("background", "white");
		$(".nav_dl1").css("color", "#666");
		$(".nicheng img").addClass("hover");
	})
	$(".nav_dl1").mouseleave(function(e) {
		$(".nicheng_info").hide();
		$(".nav_dl1").css("background", "#313131");
		$(".nav_dl1").css("color", "white");
		$(".nicheng img").removeClass("hover");
	})
})
// 跳转搜索页
function get_search() {
	var Num = "";
	for (var i = 0; i < 10; i++) {
		Num += Math.floor(Math.random() * 10);
	}
	var search = $("#search_id").val();
	if (search != null && search != "") {
		var keyHex = CryptoJS.enc.Utf8.parse(Num);
		var encrypted = CryptoJS.DES.encrypt(search, keyHex, {
			mode : CryptoJS.mode.ECB,
			padding : CryptoJS.pad.Pkcs7
		});
		var s = encrypted.toString() + "";
		var message = s.replace(/\+/g, "%2B");
		location.href = "/sc_search/get_search_main.html?s_id=" + Num
				+ "&message=" + message;
	} else {
		alert("请输入您要搜索的内容！")
	}
}
// 跳转至购物车
function to_go_shoping() {
	location.href = "/sc_myshoping/get_my_shoping_main.html";
}
//退出网校
function logout(){
	$.post("/sc_login/logout.html")
	location.reload();
	window.location.reload();//退出
}
//滚动后右侧侧边栏出现
$(function() {
	$(window).scroll(function() {
		height = $(window).scrollTop();
		if (height > 500) {
			$('.toolbar').fadeIn();
		} else {
			$('.toolbar').fadeOut();
		};
	});
})