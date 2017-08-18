// 头部事件
$(function(){
  $(".jiantoushang").mouseenter(
    function(e){
      $(".nicheng_info").show();
      $(".nav_dl1").css("background","white");
      $(".nav_dl1").css("color","#666");
      $(".nicheng img").addClass("hover");
    })
  $(".nicheng_info").mouseleave(
    function(e){
      $(".nicheng_info").hide();
      $(".nav_dl1").css("background","#313131");
      $(".nav_dl1").css("color","white");
      $(".nicheng img").removeClass("hover");
    })
})
// input按钮事件
$(function(){
  $(".pay_way1 .pay_way_c1").click(function(){
    var indexp=$(this).index();
    $(".pay_way1 .pay_way_c1").eq(indexp/2).addClass("on").siblings().removeClass("on");
  })
  
  //支付方式tab切换
$(".pay_way_list span").click(function(){
  var indexw=$(this).index();
 if($(this).text()!="平台支付"){
	 $("#zhifu_btu").hide();
 }else{
	 $("#zhifu_btu").show();
 }
  $(".pay_way_list2").children().eq(indexw).css("display","block").siblings("div").css("display","none");
  $(".pay_way_list span").eq(indexw).addClass("select_pay").siblings().removeClass("select_pay");
})
})
var oTimer = null;
//支付
function pay_course(){
	var pay_type=$("input:radio[name=paylist]:checked").val();
	if(typeof(pay_type) == "undefined"){
		alert("请选择支付方式！")
	}else{
		var str="";
		$("input:checkbox[name=video_id]:checked").each(function(i) {
			if (0 == i) {
				str = $(this).val();
			} else {
				str +=","+$(this).val();
			}
		});
		$.post("/sc_pay/save_course_order.html",{
			'str':str,
			'pay_type':pay_type
		},function(data){
			if(data==0){
				alert("失败");
			}else if(data==3){
				alert("登录超时")
			}else if(data==2){
				alert("您已经购买过该课程，请勿重复购买！")
			}else{
				if(pay_type=="支付宝"){
					alert("支付宝支付")
				}else if(pay_type=="微信"){
					oTimer = setInterval("check_course_order()",
							2000);
					$(".zhezhao").show();
					$("#erweima").show();
					$(".modal-qrcode").attr("src",data);
				}
			}
		})
	}
}
//检查是否支付成功
function check_course_order(){
	$.post("/fron_order/check_course_order.html",function(data){
		if(data==1){//支付成功
			clearInterval(oTimer);// 停止
			$("#erweima").hide();
			$(".pay_finish").show();
		}
	})
}

function close_pay_page(){
	clearInterval(oTimer);
	$(".zhezhao").hide();
	$("#erweima").hide();
}

function claerance_pay_course(clearance_id){
	var pay_type=$("input:radio[name=paylist]:checked").val();
	if(typeof(pay_type) == "undefined"){
		alert("请选择支付方式！")
	}else{
		$.post("/sc_pay/save_clearance_order.html",{
			'clearance_id':clearance_id,
			'pay_type':pay_type
		},function(data){
			if(data==0){
				alert("失败");
			}else if(data==3){
				alert("登录超时");
			}else if(data==2){
				alert("您已经购买过该课程，请勿重复购买！")
			}else{
				if(pay_type=="支付宝"){
					alert("支付宝支付")
				}else if(pay_type=="微信"){
					oTimer = setInterval("check_course_order()",
							2000);
					$(".zhezhao").show();
					$("#erweima").show();
					$(".modal-qrcode").attr("src",data);
				}
			}
		})
	}
}
