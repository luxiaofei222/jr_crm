<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/person/pay_wait.css">
<h1>我的订单</h1>
<div class="wait_wrap">
	<div class="wait_info">
		<c:if test="${courseOrder.order_state=='待付款' }">
			<script type="text/javascript"
				src="/js/school/front/person/daojishi.js"></script>
			<div class="wait_info1">当前交易状态：待支付</div>
			<div class="wait_info2">
				点击<a class="wait_info2_green" href="javascript:void(0);">付款</a> <input
					type="hidden" id="back_time" value="${shengyutime }">
				进行支付，请在48小时内完成支付，超过时间需要重新下单 <span style="color:#f94c4c;float:right;margin-right:5px;margin-top:20px;">剩余时间：<a id="time_d"></a>天<a id="time_h"></a>小时<a id="time_m"></a>分<a id="time_s"></a>秒</span>
			</div>
		</c:if>
		<c:if test="${courseOrder.order_state!='待付款' }">
		<div class="wait_info1">当前交易状态：已完成</div>
		</c:if>
	</div>
	<div class="wait_list">
		<div class="wait_list_title">订单详情</div>
		<div class='wait_list_info'>
		<c:if test="${courseOrder.order_state=='待付款' }">
			<span class='wait_list_info1'>订单号：${courseOrder.order_number }</span>
			<span class='wait_list_info3'>创建时间：<fmt:formatDate type="BOTH" value="${courseOrder.order_time }" /></span>
		</c:if>
		<!-- 交易完成的未写 -->
		</div>
		<div class="wait_list_course">
			<div class="wait_course1">
				<span class="wait_course1_one">课程名称</span> <span
					class="wait_course1_two">价格</span> <span class="wait_course1_three">实付款</span>
				<span class="wait_course1_four">交易状态</span>
			</div>
			<ul class="wait_course2">
				<li>
					<div class="wait_course2_one">
						<img src="${courseOrder.courseVideo.video_pic }">
						<div style="float:right;width:60%;margin-top:40px;">
						 <a>${courseOrder.courseVideo.video_name }</a>
						 <span style="cursor:pointer;">课时：4课时</span>
						 </div>
					</div>
					<div class="wait_course2_two">￥ ${courseOrder.courseVideo.video_money_now }<!-- 0.00 --></div>

					<c:if test="${not empty courseOrder.pay_money }">
						<div class='wait_course2_three'>￥${courseOrder.pay_money }</div>
					</c:if> 
					<c:if test="${empty courseOrder.pay_money }">
						<div class='wait_course2_three'>未支付</div>
					</c:if>
					<div class='wait_course2_four'>${courseOrder.order_state }</div>
				</li>
			</ul>
		</div>
		<div class='wait_list_course2'>
			订单总金额：<span>￥${courseOrder.courseVideo.video_money_now } <!-- 0.00 --></span>
		</div>
	</div>
</div>