<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="/images/school/front/index/logo2.png" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="/css/school/front/index/reset.css">
<link href="/css/school/front/course_video/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/school/front/index/pay_code.css">
<link rel="stylesheet" type="text/css" href="/css/school/front/index/index.css">
<link rel="stylesheet" type="text/css" href="/css/school/front/pay/pay.css">
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/school/front/pay/pay.js"></script>
<title>京人网校课程结算中心</title>
</head>
<body>
<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
<div class="pay_wrap">
		<div class='pay_title'>
			订单信息
		</div>
		<div class="pay_course_info">
			<ul>
			<c:forEach items="${list }" var="cours">
			<input style="display: none;" type="checkbox" checked="checked" name="video_id" value="${cours.video_id }" />
				<li>
					<a href="javascript:void(0);" class='pay_course_info1'>
						<img src="${cours.video_pic }">
					</a>
					<a href="/sc_coursevideo/get_course_video_player.html?video_id=${cours.video_id }" target="_blank" class='pay_course_info2'>
						<span>${cours.video_name }</span>
					</a>
					<span class="pay_course_info3">金额:<span class="pay_course_info4">￥${cours.video_money_now }</span></span>
				</li>
			</c:forEach>
			</ul>
			<div class="pay_info_r">
				<span class="pay_info_r1">
	 			总金额：<span class="pay_info_red">${zong_price}</span>元
	 			</span>
	 			<div class="pay_info_r2">
		 			<span class="time1">注：根据相关规定，在线视频等商品支付订单，支付后将不退款，谢谢合作。</span>
		 			<!-- <span id="t_h" class="pay_info_red">00时</span><span id="t_m" class="pay_info_red">00分</span><span id="t_s" class="pay_info_red">00秒</span> -->
		 		</div>
			</div>
		</div>
		<div class='pay_title2'>
			支付方式
		</div>
		<div class="pay_way">
			<div class="pay_way_list" id="pay_way_list">
				<span class="norightb select_pay">平台支付</span>
				<span class="norightb">银行汇款</span>
				<span>报名处支付</span>
			</div>
			<div class="pay_way_list2" id="pay_way_list2">
				<div class="pay_way1">
					<span class='pay_way_c1'>
						<input type="radio" name="paylist" value='支付宝' class="radioclass">
					</span>
					<img src="/images/school/front/pay/zhifubaopay.png" >			
					<span class='pay_way_c1'>
						<input type="radio"  name="paylist" value='微信' class="radioclass">
					</span>
					<img src="/images/school/front/pay/weixinpay.png">
					<!-- <span class='pay_way_c1'>
						<input type="radio"  name="paylist" value='3' class="radioclass">
					</span>
					<img src="/images/school/front/pay/baomingpay.png"> -->
				</div>
				<div class="pay_way2">
					<table>
						<tr>
							<th>银行名</th>
							<th>收款方</th>
							<th>账号</th>
							<th>开户行</th>
						</tr>
						<tr>
							<td>工商银行</td>
							<td>穆瑞清</td>
							<td>6222080402004805193</td>
							<td>石家庄光明花城支行</td>
						</tr>
						<tr>
							<td>农业银行</td>
							<td>吕晓峰</td>
							<td>6228480638928508974</td>
							<td>石家庄和平支行</td>
						</tr>
						<tr>
							<td>建设银行</td>
							<td>吕晓峰</td>
							<td>6217000130024501586</td>
							<td>石家庄红旗大街支行</td>
						</tr>
						<tr>
							<td>邮政储蓄银行</td>
							<td>穆瑞清</td>
							<td>6217991210009782715</td>
							<td></td>
						</tr>
					</table>
				</div>
				<div class="pay_way3">
					如您已在报名处付款，请主动联系客服，帮您开通课程！
				</div>
			</div>
			<div style="clear:both;"></div>
			<div class='payoff'>
			<c:if test="${empty sessionScope.user_session}">
				<button id="zhifu_but" onclick="get_login()">立即支付</button>
			</c:if>
			<c:if test="${not empty sessionScope.user_session}">
				<button onclick="pay_course()" id="zhifu_btu">立即支付</button>
			</c:if>
				<div style="clear:both;"></div>
			</div>
			<div id="erweima" class="erweima" style="display:none;">
				<div class="modal-left">
					<p>
						<span>请使用 </span><span class="orange">微信 </span><i class="iconfont icon-saoyisao"></i><span class="orange"> 扫一扫</span><br>
						扫描二维码支付
					</p>
					<div class="modal-qr">
						<img class="modal-qrcode"  src="" alt="您的浏览器版本太低, 请升级您的浏览器">
						<div class="modal-info">
							<img class="icon-clock" src="/images/school/front/pay/tixing.png"><span>二维码有效时长为2小时, 请尽快支付</span>
						</div>
					</div>
				</div>
				<div class="modal-right">
					<i class="iconfont icon-error" onclick="close_pay_page()"></i><img src="/images/school/front/pay/weixin_saoyisao.jpg" alt="微信扫码">
				</div>
			</div>
			<div class="pay_finish" style="display: none;">
				<div class="pf_info">
					<i class="iconfont icon-zhengque pf_l"></i>
					<div class="pf_r">
						<p class="pf_wz1">支付成功</p>
						<p class="pf_wz2">
							<span class="pf_wz3">温馨提示:</span>
							请在我的课程中查看已购买课程，如没有该课程，请及时联系客服！
						</p>
					</div>
				</div>
				<button class="pf_btn" onclick="location.href='/person/get_head_myorder.html'">进入我的课程</button>
			</div>
		</div>
		<!-- 遮罩层 -->
		<div class="zhezhao" style="display: none"></div>
			<!--登录注册窗口  -->
	<jsp:include
		page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/footer.jsp"></jsp:include>
</body>
</html>