<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="/images/school/front/index/logo2.png"
	type="image/x-icon" />
<link href="/css/school/front/index/reset.css" rel="stylesheet"
	type="text/css" />
<link href="/css/school/front/common/header.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="/js/school/front/search/crypto-js/crypto-js.js"></script>
<script type="text/javascript"
	src="/js/school/front/search/crypto-js/mode-ecb.js"></script>
<script type="text/javascript"
	src="/js/school/front/search/crypto-js/tripledes.js"></script>
<script type="text/javascript"
	src="/js/common/head.js"></script>
<title>京人网校</title>
</head>
<body>
	<div class="top_header">
		<div class="top_nav">
			<div class="nav">
				<div class="nav_left">中国最具影响力的“高学历、高技能人才培训服务机构”</div>
				<div class="nav_right">
					<c:if test="${empty sessionScope.user_session}">
						<span class="nav_dl"> <a onclick="get_login()"
							style="cursor: pointer;">登录</a> <em>|</em> <a
							onclick="get_register()" style="cursor: pointer;">注册</a>
							<!--  <em>|</em>
							 <a	href="/login.jr" style="cursor: pointer;">BSM</a> -->
						</span>
					</c:if>
					<c:if test="${not empty sessionScope.user_session}">
						<div class="nav_dl1">
							<div class="nicheng">
								<span>${sessionScope.user_session.user_nickname}</span> <img
									src="/images/school/front/index/sanjiaoshang.png"
									class="jiantoushang">
							</div>
							<div class="nicheng_info">
								<ul>
									<li><a href="/person/get_head_mycourse.html">我的课程 </a></li>
									<li><a href="/person/get_head_myorder.html">我的订单 </a></li>
									<li><a href="/person/get_person_index.html">个人信息 </a></li>
									<li><a href="/person/get_person_safe.html">密码安全 </a></li>
									<li><a href="javascript:void(0);" onclick="logout()">退出
									</a></li>
								</ul>
							</div>
							
						</div>
						<!-- <div style="float:left;margin-right:50px;">
							<em>|</em>
								 <a	href="/login.jr" style="cursor: pointer;">BSM</a>
						</div> -->
					</c:if>
					<span class="nav_rx"> <i></i> <em>服务热线：0311-80958918</em>
					</span>
					<div style="clear: both;"></div>
				</div>
			</div>
		</div>
		<div class="nav_bottom_wrap">
		<div class="nav_bottom">
			<div class="logo">
				<a href="/index.jsp"><img src="/images/school/front/index/logo.png" /></a>
			</div>
			<div class="ss_div">
				<input type="text" id="search_id" placeholder="课程搜索"
					class="search_input" /> <i></i>
			</div>
			<a class="select" href="javascript:void(0)" onclick="get_search()">搜索</a>
			<a class="shop"> <i></i>
			<c:if test="${empty sessionScope.user_session}">
			<span onclick="get_login()" class="myshop">我的购物车</span>
			</c:if>
			<c:if test="${not empty sessionScope.user_session}">
			 <span onclick="to_go_shoping()" class="myshop">我的购物车</span> <!-- 购物车数量 -->
				 <span class="number" id="myshoping_number">0</span>
				 </c:if>
				  <i class="right_jt"></i>
			</a>
			<div class="clear"></div>
		</div>
		</div>
	</div>
<!-- 百度离线宝与百度商桥与百度统计-->
<script type="text/javascript"> var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://"); document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F14ade47071d670dceaa8bc6e6d73e5aa' type='text/javascript'%3E%3C/script%3E")) </script>
<!-- 百度离线宝与百度商桥与百度统计-->
</body>
</html>