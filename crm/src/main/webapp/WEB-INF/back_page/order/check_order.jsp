<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/school/back/order/reset.css">
<link rel="stylesheet" type="text/css" href="/css/school/back/order/order_manage_check.css">
<title>订单详情</title>
</head>
<body>
<div class="wait_wrap">
		<div class="user_info">
			<div class="user_top">
				<img src="${courseOrder.user.user_pic }">
				<span class="nicheng">${courseOrder.user.user_nickname }</span>
			</div>
			<div class="user_center">
				<div class="user_left">用户信息：</div>
				<ul class="user_right">
					<li class="user_noleft">
						<div>注册时间：<fmt:formatDate type="both" value="${courseOrder.user.user_time }" /></div>
					</li>
					<li>
						<div>电话：
						<c:if test="${not empty courseOrder.user.user_phone }">
						${courseOrder.user.user_phone }
						</c:if>
						<c:if test="${empty courseOrder.user.user_phone }">
						暂无手机号
						</c:if>
						</div>
					</li>
					<li>
						<div>qq：
						<c:if test="${not empty courseOrder.user.user_qq }">
						${courseOrder.user.user_qq }
						</c:if>
						<c:if test="${empty courseOrder.user.user_qq }">
						暂无QQ号
						</c:if>
						</div>
					</li>
					<li class="user_noleft">
						<div>邮箱：
						<c:if test="${not empty courseOrder.user.user_mail }">
							${courseOrder.user.user_mail }
						</c:if>
						<c:if test="${empty courseOrder.user.user_mail }">
							暂无邮箱地址
						</c:if>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="wait_info">
			<div class="wait_info1">当前交易状态：${courseOrder.order_state }
			</div>
		</div>
		<div class="wait_list">
				<div class="wait_list_title">
					订单详情
				</div>
				<ul class='wait_list_info'>
					<li class='wait_list_info1'>订单号：${courseOrder.order_number }</li>
					<li class='wait_list_info1'>支付宝交易号：暂无</li>
					<li class='wait_list_info1'>创建时间：<fmt:formatDate type="both" value="${courseOrder.order_time }" /></li>
					<c:if test="${not empty courseOrder.pay_time }">
					<li class='wait_list_info1'>成交时间：<fmt:formatDate type="both" value="${courseOrder.pay_time }" /></li>
					</c:if>
				</ul>
				<div class="wait_list_course">
					<div class="wait_course1">
						<span class="wait_course1_one">课程名称</span>
						<span class="wait_course1_two">价格</span>
						<span class="wait_course1_three">实付款</span>
						<span class="wait_course1_four">交易状态</span>
					</div>
					<ul class="wait_course2">
						<li>
							<div class="wait_course2_one">
								<img src="${courseOrder.courseVideo.video_pic }">
								<div style="float:right;width:60%;margin-top:40px;">
								<a>${courseOrder.courseVideo.video_name }</a>
								<span style="cursor:pointer;">课时：${courseOrder.courseVideo.keshi_number }课时</span>
								</div>
							</div>
							<div class="wait_course2_two">
								￥${courseOrder.courseVideo.video_money_now }
							</div>
							<div class='wait_course2_three'>
							<c:if test="${not empty courseOrder.pay_money }">
								￥${courseOrder.pay_money }
								</c:if>
								<c:if test="${empty courseOrder.pay_money }">
									未支付
								</c:if>
							</div>
							<div class='wait_course2_four'>
							<c:if test="${courseOrder.order_state=='已付款' }">
								交易已完成
							</c:if>
							<c:if test="${courseOrder.order_state=='待付款' }">
								用户未付款
							</c:if>
							<c:if test="${courseOrder.order_state=='关闭' }">
								订单已关闭
							</c:if>
							</div>
						</li>
					</ul>
				</div>
				<div class='wait_list_course2'>
					订单金额：<span>￥${courseOrder.courseVideo.video_money_now }</span>
				</div>
			</div>
	</div>
</body>
</html>