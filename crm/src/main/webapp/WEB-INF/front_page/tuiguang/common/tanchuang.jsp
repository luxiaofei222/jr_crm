<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="/css/school/front/common/animate.min.css" rel="stylesheet" type="text/css">
<style>
.dianhua{
	overflow: hidden;
    width: 90px;
    height: 30px;
    line-height: 30px;
    background-color: #adc619;
    color: white;
    border: none;
    border-bottom-right-radius: 5px;
    border-top-right-radius: 5px;
    cursor: pointer;
    float:left;
    font-family:"微软雅黑";
}
.zixun{
	overflow: hidden;
    width: 90px;
    height: 30px;
    line-height: 30px;
    background-color: #2c8c67;
    color: white;
    border: none;
    border-bottom-left-radius: 5px;
    border-top-left-radius: 5px;
    cursor: pointer;
    float:left;
    font-family:"微软雅黑";
	}
.zixun:hover{
	background-color:#23b767;
}
.dianhua:hover{
	background-color:#b0e719;
}
.zixunon_btn{
	visibility: visible;
	animation-duration:0.7s;
	-webkit-animation-duration:0.7s;
	animation-delay:0.6s;
	-webkit-animation-delay:0.6s;
	display:block;
}
.ersheng{
	visibility: visible;
	animation-duration:0.7s;
	-webkit-animation-duration:0.7s;
	animation-delay:0.9s;
	-webkit-animation-delay:0.9s;
	display:block;
}
.tanicon1_wz{
	color:#479c7b;
	font-size:13px;
	float:left;
	line-height:25px;
	margin-left:3px;
}
.tanicon1,.tanicon2{
	cursor:pointer;
}
.TcShow {
    -webkit-animation: TcShow1 1.3s both;
    animation: TcShow1 1.3s both;
}
@keyframes TcShow1 {
    0% {
        -webkit-transform: translate3d(0, -300%, 0);
        transform: translate3d(0, -300%, 0);
    }
    50% {
        -webkit-transform: rotate(3deg);
        transform: rotate(3deg);
    }
    to {
        -webkit-transform: none;
        transform: none;
    }
}
@-webkit-keyframes TcShow1 {
    0% {
        -webkit-transform: translate3d(0, -300%, 0);
        transform: translate3d(0, -300%, 0);
    }
    50% {
        -webkit-transform: rotate(3deg);
        transform: rotate(3deg);
    }
    to {
        -webkit-transform: none;
        transform: none;
    }
}
.TcOut {
    -webkit-animation: TcOut1 1.3s both;
    animation: TcOut1 1.3s both;
}
@keyframes TcOut1 {
    50% {
        -webkit-transform: rotate(-4deg);
        transform: rotate(-4deg);
    }
    to {
        -webkit-transform: translate3d(0, 300%, 0);
        transform: translate3d(0, 300%, 0);
    }
}
@-webkit-keyframes TcOut1 {
    50% {
        -webkit-transform: rotate(-4deg);
        transform: rotate(-4deg);
    }
    to {
        -webkit-transform: translate3d(0, 300%, 0);
        transform: translate3d(0, 300%, 0);
    }
}
.toolbar_btn_wra a{
	margin-top:7px!important;
}
/*通用弹框样式*/
.tybtn{
	width:265px;
	height:47px;
	margin:0 auto;
	overflow:hidden;
	position:absolute;
	bottom:40px;
	right:80px;
}
.tybtn li{
	width:47px;
	height:47px;
	float:left;
	margin-left:25px;
}
.tybtn li a{
    width:47px;
	height:47px;
	background-color:#fe5778;
	font-size:16px;
    color:white;
    display:inline-block;
    text-align:center;
    line-height:23px;
}
.tybtn li a:hover{
    background-color:#ea4778;
}
/*通用弹框样式*/
.tybtn{
	width:265px;
	height:47px;
	margin:0 auto;
	overflow:hidden;
	position:absolute;
	bottom:40px;
	right:80px;
}
.tybtn li{
	width:47px;
	height:47px;
	float:left;
	margin-left:25px;
}
.tybtn li a{
    width:47px;
	height:47px;
	background-color:#fe5778;
	font-size:16px;
    color:white;
    display:inline-block;
    text-align:center;
    line-height:23px;
}
.tybtn li a:hover{
    background-color:#ea4778;
}
/* 报名输入信息样式 */
.tank_time{
	overflow:hidden;
	margin-top:16px;
}
.tank_time li{
	float:left;
	width:114px;
	height:34px;
	background-color:#f6f6f6;
	border:1px solid #e6e6e6;
	text-align:center;
	line-height:34px;
	font-size:14px;
	color:#808080;
	margin-left:10px;
	cursor:pointer;
}
.tank_time li.active{
	border:1px solid #06c1ae;
	color:#06c1ae;
}
.pro_list{
	overflow:hidden;
	margin-top:16px;
}
.pro_list li{
	float:left;
	width:150px;
	height:40px;
	line-height:40px;
	overflow:hidden;
}
.pro_list li input{
	width:15px;
	height:15px;
	float:left;
	margin-top:14px;
}
.pro_list li span{
	float:left;
	font-weight:bold;
	margin-left:4px;
	letter-spacing:1px;
	font-size:15px;
}
.tank_info{
	overflow:hidden;
	margin-top:18px;
}
.tank_info li{
	float:left;
	width:140px;
	height:37px;
	line-height:37px;
	margin-left:4px;
}
.tank_info li input{
	width:138px;
	height:34px;
	line-height:34px;
	background-color:#f6f6f6;
	outline:none;
	border:1px solid #e6e6e6;
	text-indent:1em;
}
.tank_btn{
	margin-top:20px;
	overflow:hidden;
}
.tank_btn a{
	width:180px;
	height:38px;
	line-height:38px;
	text-align:center;
	float:left;
	font-size:16px;	
}
.tank_btn .youhui{
	background-color:#fff100;
	color:#232323;	
}
.tank_btn .youhui:hover{
	background-color:#daf800;
}
.tank_btn .kfzi{
	background-color:#232323;
	color:white;
	margin-left:12px;
}
.tank_btn .kfzi:hover{
    background-color:#071d23;
}
</style>
<div class="toolbar">
		<div class="toolbar_nei" style="position: absolute; top: 112px;">
			<div class="toolbar_top">在线客服</div>
			<div class="toolbar_time">（每天：8:30-21:30）</div>
			<div class="toolbar_btn_wra">
				<a href="http://tb.53kf.com/code/client/10147174/1]" target="_blank">
					<img src="/images/school/front/index/kaowu.png"> <span>报考咨询</span>
				</a> <a href="http://tb.53kf.com/code/client/10147174/1]"
					target="_blank"> <img
					src="/images/school/front/index/yuyue.png"> <span>课程咨询</span>
				</a> <a href="tencent://message/?uin=70791858" target="_blank"> <img
					src="/images/school/front/index/qq_zixun.png"> <span>QQ咨询</span>
				</a> <a href="http://dwz.cn/4RExyN" target="_blank"> <img
					src="/images/school/front/index/dianhua.png"> <span>免费通话</span>
				</a> <a href="http://dwz.cn/4RExyN" target="_blank"> <img
					src="/images/school/front/index/hezuo.png"> <span>培训合作</span>
				</a> <span class="toolbar_rx">全国咨询热线</span> <span class="toolbar_phone">0311-88818178</span>
			</div>
			<div class='toolbar_bottom'>
				由于近期学员较多<br />如遇电话忙线<br />请在线报名
			</div>
		</div>
	</div>
	  <c:if test="${empty sessionScope.user_session }">
 	<!--<div id="cover"
		style="display:none;position: fixed; z-index: 298; top: 0px; left: 0px; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.5);"></div>
	 <div class="animate_ad">
		<div id="center_ad"
			style="display:none;width:784px; height:484px;  position: fixed; z-index: 299; top: 50%; left: 50%; margin-left:-392px; margin-top:-242px;background:url('/images/school/front/index/xueli_tan.png')"">
			<img src="/images/school/front/index/on_close1.png" id="on_close" style="width:27px;height:27px;position:absolute;right:4px;top:4px;z-index:10;cursor:pointer;" class="ani  lightSpeedIn animated ersheng" swiper-animate-effect="lightSpeedIn" swiper-animate-duration="0.7s" swiper-animate-delay="0.9s"> -->
			<!-- <img src="/images/school/front/index/xueli_tan.png" id="on_img">
			<div class="zixunon_btn ani  rotateInDownRight animated jiyu" style="overflow:hidden;width:180px;height:30px;position:absolute;bottom:4px;right:30px;" swiper-animate-effect="rotateInDownRight" swiper-animate-duration="0.7s" swiper-animate-delay="0.6s">

				<button class="zixun" id="zixun_on">
					<img src="/images/school/front/index/qipao.png" style="float:left;margin-top:3px;">
					<span style="float:right;color:white;font-size:12px;">立即咨询</span>
				</button>
				<button class="dianhua" id="zixun_phone">
					<img src="/images/school/front/index/dianhua.png" style="float:left;margin-top:3px;">
					<span style="float:right;color:white;font-size:12px;">免费电话</span>
				</button>
			</div>
			<div class="tan_icon  ani  rotateInDownLeft animated" style="position:absolute;bottom:4px;left:30px;overflow:hidden;" swiper-animate-effect="rotateInDownLeft" swiper-animate-duration="0.7s" swiper-animate-delay="0.6s">
				<a  href="http://www.jingrenedu.com" class="tanicon1" style="float:left;overflow:hidden;">
					<img src="/images/school/front/index/home.png" style="float:left;">
					<span class='tanicon1_wz'>首页</span>
				</a>
				<a	href="tencent://message/?uin=70791858" class="tanicon2"  style="float:left;overflow:hidden;margin-left:20px;">
					<img src="/images/school/front/index/tan_qq.png" style="float:left;">
					<span class='tanicon1_wz'>QQ咨询</span>
				</a>
			</div> -->
			<!-- 通用弹框样式 -->
			<!-- <ul class="tybtn ani  rotateInDownRight animated jiyu" swiper-animate-effect="rotateInDownRight" swiper-animate-duration="0.7s" swiper-animate-delay="0.6s">
				<li style="margin-left:0px;">
					<a href="http://tb.53kf.com/code/client/10147174/1]">报名时间</a>
				</li>
				<li>
					<a href="http://tb.53kf.com/code/client/10147174/1]">报名条件</a>
				</li>
				<li>
					<a href="http://tb.53kf.com/code/client/10147174/1]">考试科目</a>
				</li>
				<li>
					<a href="http://tb.53kf.com/code/client/10147174/1]">费用明细</a>
				</li>
			</ul> -->
			<!-- 报名输入信息样式 -->
			<!-- <div style="width:461px;height:430px;padding-top:54px;padding-left:28px;">
				<h2 style="font-size:21px;color:#333;letter-spacing:3px;">9年专注考试培训服务</h2>
				<span style="font-size:16px;color:#4c4c4c;display:block;margin-top:27px;">学习时间</span>
				<ul class="tank_time" id="nianxian">
					<li style='margin-left:0px;' pid='2年内' class="active">2年内</li>
					<li pid='1年内'>1年内</li>
					<li pid='3个月内'>3个月内</li>
				</ul>
				<span style="font-size:16px;color:#4c4c4c;display:block;margin-top:16px;">感兴趣的专业</span>
				<ul class="pro_list">
					<li>
						<input type="checkbox" name="course_name_bm" value="成人高考"><span>成人高考</span>
					</li>
					<li>
						<input type="checkbox" name="course_name_bm" value="理财规划师"><span>理财规划师</span>
					</li>
					<li>
						<input type="checkbox" name="course_name_bm" value="消防工程师"><span>消防工程师</span>
					</li>
					<li>
						<input type="checkbox" name="course_name_bm" value="远程教育"><span>远程教育</span>
					</li>
					<li>
						<input type="checkbox" name="course_name_bm" value="一级建造师"><span>一级建造师</span>
					</li>
					<li>
						<input type="checkbox" name="course_name_bm" value="二级建造师"><span>二级建造师</span>
					</li>
					<li>
						<input type="checkbox" name="course_name_bm" value="心理咨询师"><span>心理咨询师</span>
					</li>
					<li>
						<input type="checkbox" name="course_name_bm" value="人力资源管理师"><span>人力资源管理师</span>
					</li>
				</ul>
				<ul class="tank_info">
					<li>
						<input type="text" id="user_name_bm" placeholder="姓名">
					</li>
					<li>
						<input type="text" onblur="check_bmUserPhone()" id="user_phone_bm" placeholder="手机号">
					</li>
					<li>
						<input type="text" id=qq_bm placeholder="微信/QQ">
					</li>
				</ul>
				<div class="tank_btn">
					<a href="javascript:void(0);" class="youhui">获取优惠</a>
					<a href="http://tb.53kf.com/code/client/10147174/1]" class="kfzi">在线咨询</a>
				</div>
			</div>
		</div>
	</div> -->
	<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
	<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script>
$(function(){
	layui.use('layer', function(){
	  	var layer = layui.layer;
		  //点击添加弹出层事件
	});  
	 $('input[type=checkbox]').click(function() {
         $("input[name='course_name_bm']").attr('disabled', true);
         if ($("input[name='course_name_bm']:checked").length >=2) {
             $("input[name='course_name_bm']:checked").attr('disabled', false);
         } else {
             $("input[name='course_name_bm']").attr('disabled', false);
         }
     })
});
//检查用户手机号
function check_bmUserPhone() {
	var entryUserPhone = $("#user_phone_bm").val();
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
	if (entryUserPhone != null && entryUserPhone != "") {
		if (!myreg.test(entryUserPhone)) {
			layer.msg('请输入有效的手机号码！');
			return false;
		} else {
			return true;
		}
	} else {
		layer.msg("请输入您的手机号！");
		return false;
	}
}
//报名
function baoming_tt(){
	var check_number=$("input[name='course_name_bm']:checked").length;
	var nianxian=$("#nianxian").children(".active").attr("pid");
	var str="";
	$('input[name=course_name_bm]:checked').each(function(i) {
		if (0 == i) {
			str = $(this).val();
		} else {
			str += ","+$(this).val();
		}
	});
	var user_name_bm=$("#user_name_bm").val();
	var user_phone_bm=$("#user_phone_bm").val();
	var qq_bm=$("#qq_bm").val();
	if(0<check_number&&check_number<3){
		if(user_name_bm!=null&&user_name_bm!=""){
			if(check_bmUserPhone()){
				$.post("/baoming/save_baoming.html",{
					'course_dic':nianxian,
					'course_leibie':str,
					'user_name':user_name_bm,
					'user_phone':user_phone_bm,
					'user_qq':qq_bm,
					'course_content':"京人网校推广弹出窗口报名渠道"
				},function(data){
					layer.msg(data);
					(':input','#myDataDicForm')  
		            .not(':button, :submit, :reset, :hidden') 
		            .val('') 
		            .removeAttr('checked') 
		            .removeAttr('selected');
				})
			}
		}else{
			layer.msg("请输入您的姓名！");
		}
	}else{
		layer.msg("请选择1-2个您感兴趣的专业！");
	}
}
	window.setTimeout("go()", 35000); 
	function go(){
		$("#cover").show();
		 $("#center_ad").css({display:'block',opacity:'1'}).addClass("TcShow")
		clearTimeout();
	}
	function kuang(){
		$("#center_ad").show();
	}
	$(function(){
		var oTimer = null;
		 $("#zixun_on").click(function(){
			$("#center_ad").css({display:'none',opacity:'0'}); 
			$("#cover").hide();
			 window.open('http://tb.53kf.com/code/client/10147174/1]');
		}) 
		$("#on_close").click(function(){
			$("#center_ad").removeClass('TcShow').addClass('TcOut');
		/* 	$("#center_ad").css({display:'none',opacity:'0'}) */
			$("#cover").hide();
			clearInterval(oTimer);
			 oTimer = setInterval("kuang()", 10000);
			
		})
		 $("#zixun_phone").click(function(){
			$("#center_ad").css({display:'none',opacity:'0'});
			$("#cover").hide();
			 window.open('http://dwz.cn/4RExyN');
		})
		$("#on_img").click(function(){
			window.open('http://tb.53kf.com/code/client/10147174/1]');
		})
		$(".tank_time li").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
	})
	})
	</script>
	</c:if>