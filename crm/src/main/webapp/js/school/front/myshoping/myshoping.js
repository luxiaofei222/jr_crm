function quanxuan() {
	$("input[name='check_shoping']").prop("checked", true);
	var spCodesTemp = 0;
	var len = $("input:checkbox[name=check_shoping]:checked").length;// 被选中的数量
	$('input:checkbox[name=check_shoping]:checked').each(function(i) {
		if (0 == i) {
			spCodesTemp = parseInt($(this).val());
		} else {
			spCodesTemp += parseInt($(this).val());
		}
	});
	$("#zongshu").html(len);
	$("#zongjia").html(spCodesTemp);
}
// 反选
function fanxuan() {
	$("input[name='check_shoping']").prop("checked", false);
	var spCodesTemp = 0;
	var len = $("input:checkbox[name=check_shoping]:checked").length;// 被选中的数量
	$('input:checkbox[name=check_shoping]:checked').each(function(i) {
		if (0 == i) {

			spCodesTemp = parseInt($(this).val());
		} else {
			spCodesTemp += parseInt($(this).val());
		}
	});
	$("#zongshu").html(len);
	$("#zongjia").html(spCodesTemp);
}
// 单选
function xuanze(obj) {
	var len = $("input:checkbox[name=check_shoping]:checked").length;// 被选中的数量
	var spCodesTemp = 0.00;
	$('input:checkbox[name=check_shoping]:checked').each(function(i) {
		if (0 == i) {
			spCodesTemp = parseFloat($(this).val());
		} else {
			spCodesTemp += parseFloat($(this).val());
		}
	});
	$("#zongshu").html(len);
	$("#zongjia").html(spCodesTemp.toFixed(2));
}
// 删除购物车
function delete_myshoping(shoping_id) {
	if (confirm("确认要删除购物车信息吗？")) {
		// 选择确定时进入
		$.post("/sc_myshoping/delete_shoping.html", {
			'my_shoping_id' : shoping_id
		}, function(data) {
			if (data == 1) {
				alert("删除购物车成功！")
				window.location.reload();
			}
		})
	}
}
//进入支付页面
function pay_course(video_id){
	location.href="/sc_pay/get_pay_main.html?video_id="+video_id
}

//跳转结算页面
function to_pay(){
	alert("暂时不支持线上购买！")
//	var video_id="";
//	$('input:checkbox[name=check_shoping]:checked').each(function(i) {
//		if (0 == i) {
//			video_id = $(this).attr("pid");
//		} else {
//			video_id +=","+$(this).attr("pid");
//		}
//	});
//	if(video_id==""){
//		alert("请您选择您要支付的课程！");
//	}else{
//		location.href="/sc_pay/get_pay_main.html?video_id="+video_id;
//	}
}