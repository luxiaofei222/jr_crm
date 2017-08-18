<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="/images/school/front/index/logo2.png"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="/css/school/front/index/index.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/shoping/shopping_cart.css">
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/school/front/index/index.js"></script>
<script type="text/javascript" src="/js/school/front/myshoping/myshoping.js"></script>
<title>我的购物车</title>
</head>
<body>
	<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
	<div class="shop_wrap">
		<div class='shop_title'>
			<span>我的购物车</span>
		</div>
		<div class='shop_title2'>
			<span class='shop_t1'>课程</span> <span class='shop_t2'>课时</span> <span
				class='shop_t3'>价格</span> <span class='shop_t4'>操作</span>
		</div>
		<div class="shop_course">
			 <c:if test="${empty myShopings }">
			<div class="null_car">
			  <img src="/images/school/front/common/car.png" />
			  <div class="right">
			    <span>您的购物车还是空的哦~</span>
			    <span>赶紧去找找您需要的课程吧！</span>
			  </div>
			  <div style="clear: both;"></div>
			</div>
			</c:if>
			<ul>
				<c:forEach items="${myShopings }" var="shop">
					<li><%-- <input onclick="xuanze(this)" type="checkbox" pid="${shop.courseVideo.video_id }"
						name="check_shoping" value="${shop.courseVideo.video_money_now }"
						class="shop_input"> --%> <img
						src="${shop.courseVideo.video_pic }"> <a target="_blank"
						href="/sc_coursevideo/get_course_video_player.html?video_id=${shop.courseVideo.video_id }"
						class="shop_info1">${shop.courseVideo.video_name }</a> <span
						class="shop_info2">${shop.courseVideo.keshi_number }课时</span> <span
						class="shop_info3">￥${shop.courseVideo.video_money_now }</span>
						<span class="shop_info4">
							<a style="margin-right:20px;" onclick="pay_course(${shop.courseVideo.video_id})" href="javascript:void(0);">付款</a>
						 	<a  onclick="delete_myshoping(${shop.my_shoping_id})" href="javascript:void(0);">删除</a></li>
						</span>
				</c:forEach>
			</ul>
		</div>
	<!-- 	<div class="shop_count">
			<input type="checkbox" id="is_check" onclick="quanxuan()"  class="select_all">
			<span class='count_info1' onclick="quanxuan()">全选</span> <span
				class='count_info1' onclick="fanxuan()">取消</span>
			<span class='count_info2'>删除</span>
			<div class='count_info3'>
				共选中<span id="zongshu" class="red">0</span>个课程
			</div>
			<div class='count_info4'>
				总价：￥<span id="zongjia" class="red">0</span>
			</div>
			<button class='count_info5' onclick="to_pay()">去结算</button>
		</div> -->
	</div>
	<!--登录注册窗口  -->
	<jsp:include
		page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/footer.jsp"></jsp:include>
</body>
</html>