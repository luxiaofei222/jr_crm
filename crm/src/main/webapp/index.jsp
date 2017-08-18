<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="京人教育网校-海量优质网络课程在线学习平台|精品课堂
京人教育网校是专业互联网学习平台，百位名师在线互动教学！京人教育集学历提升（成人高考、远程教育等）培训、网络在线教育、云教育精品课……">
<meta name="keywords" content="京人,京人教育,京人教育集团,教育,培训,机构,学历,自考,本科,人力资源师,心理咨询师">
<meta name="baidu-site-verification" content="PwSkiVAHrY" />
<title>京人教育网校-海量优质网络课程在线学习平台|精品课堂</title>
<link rel="stylesheet" type="text/css"
	href="/css/school/front/index/reset.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/index/index.css">
<link rel="shortcut icon" href="/images/school/front/index/logo2.png"
	type="image/x-icon" />
<link href="/css/school/front/course_video/css/font-awesome.min.css"
	rel="stylesheet">
<!--Required libraries-->
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>

<script src="/js/school/front/index/jquery-v1.10.2.min.js"
	type="text/javascript"></script>
<script src="/js/school/front/index/modernizr-custom-v2.7.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="/js/school/front/index/index.js"></script>
<script type="text/javascript" src="/js/school/front/index/gundong.js"></script>
<script type="text/javascript"
	src="/js/school/front/index/index_data.js"></script>
</head>
<body>
	<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
	<!-- 内容 -->
	<div class="wrapper">
		<div class="dt" id="banner_list">
			<!--首页广告大图列表  -->

		</div>
		<div class="jianav_wai">
			<ul class="jianav">
			<c:if test="${not empty sessionScope.user_session}">
				<li><a href="/entry_plan/get_entry_plan.html" target="_blank">报名中心</a></li>
			</c:if>	
			<c:if test="${empty sessionScope.user_session}">
			<li><a href="javascript:void(0)" onclick="get_login()">报名中心</a></li>
			</c:if>	
			<c:if test="${empty sessionScope.user_session}">
				<li><a href="javascript:void(0);" onclick="get_login()">金牌题库</a></li>
			</c:if>
			<c:if test="${not empty sessionScope.user_session}">
				<li><a href="/questionindex/get_question_index.html" target="_blank">金牌题库</a></li>
			</c:if>
				<li style="position:relative;"><a href="javascript:void(0);" target="_blank" class="zixunshow">资讯信息</a>
					<ul class="zixun_listinfo">
					<!-- 资讯列表 -->	
					</ul>
				</li>
			</ul>
			<div style="clear:both;"></div>
		</div>
		<div class="banner_scroll">
			<ul class="fenlei" id="course_menu_list">
				<div class="page3">
					<div class="circle-loader">
						<div class="circle-line">
							<div class="circle circle-blue"></div>
							<div class="circle circle-blue"></div>
							<div class="circle circle-blue"></div>
						</div>
						<div class="circle-line">
							<div class="circle circle-yellow"></div>
							<div class="circle circle-yellow"></div>
							<div class="circle circle-yellow"></div>
						</div>
						<div class="circle-line">
							<div class="circle circle-red"></div>
							<div class="circle circle-red"></div>
							<div class="circle circle-red"></div>
						</div>
						<div class="circle-line">
							<div class="circle circle-green"></div>
							<div class="circle circle-green"></div>
							<div class="circle circle-green"></div>
						</div>
					</div>
				</div>
				<!-- 课程分类列表 -->
			</ul>
		</div>
		<div class="banner_scroll2">
			<div class="dl_div">
				<!--登录注册-->

				<ul class="tx_ul">
					<li class="tx_img"><c:if
							test="${empty sessionScope.user_session}">
							<img src="/images/school/front/index/touxiang.png" />
						</c:if> <c:if test="${not empty sessionScope.user_session}">
							<img src="${sessionScope.user_session.user_pic}" />
						</c:if></li>
					<c:if test="${empty sessionScope.user_session}">
						<li class="hy">Hi,欢迎加入京人网校！</li>
					</c:if>
					<c:if test="${not empty sessionScope.user_session}">
						<li class="hy">Hi,${sessionScope.user_session.user_nickname }</li>
					</c:if>
					<li><c:if test="${empty sessionScope.user_session}">
							<a class="dl_btn" onclick="get_login()" id="login">登录</a>
							<a class="zc_btn" id="zhuce" onclick="get_register()">注册</a>
						</c:if> <c:if test="${not empty sessionScope.user_session}">
							<a class="grzx_btn" href="/person/get_person_index.html">个人中心</a>
						</c:if></li>
					<hr class="hx" />
				</ul>
				<!--新闻-->
				<div class="right_news">
					<h3>新闻动态</h3>
					<div class="news_myscroll">
						<ul class="news" id="news_list">
							<!-- 新闻列表动态 -->
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--课程分类-->
		<div class="course">
			<div class="course_div">
				<!--课程分类名称-->
				<ul class="course_name">
					<li class="select_kc course_name_fl" onclick="tuijian_course(1)"><a href="javascript:void(0)"><i class="fa fa-magic"></i>最新课程</a></li>
					<li class="course_name_fl" onclick="tuijian_course(2,'免费')"><a href="javascript:void(0)"><i class="fa fa-paper-plane"></i>免费课程</a></li>
					<li class="course_name_fl" onclick="tuijian_course(3,'付费')"><a href="javascript:void(0)"><i class="fa fa-usd"></i>付费课程</a></li>
					<!-- <li class="course_name_fl" onclick="jinyong()">直播课程</li>
					<li class="course_name_fl" onclick="jinyong()">海量题库</li> -->
					<!-- <li
						style="float: right; width: 215px; height: 52px; line-height: 52px; background: none; color: #666; font-size: 14px;">一流的师资队伍,尽在京人教育</li>
					<div style="clear: both;"></div> -->
				</ul>
				<!--课程 分类内容-->
				<div class="nr_kc effects" id="course_list_index">
					<div class="page1">
						<div class="circle-loader">
							<div class="circle-line">
								<div class="circle circle-blue"></div>
								<div class="circle circle-blue"></div>
								<div class="circle circle-blue"></div>
							</div>
							<div class="circle-line">
								<div class="circle circle-yellow"></div>
								<div class="circle circle-yellow"></div>
								<div class="circle circle-yellow"></div>
							</div>
							<div class="circle-line">
								<div class="circle circle-red"></div>
								<div class="circle circle-red"></div>
								<div class="circle circle-red"></div>
							</div>
							<div class="circle-line">
								<div class="circle circle-green"></div>
								<div class="circle circle-green"></div>
								<div class="circle circle-green"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="content">
			<div class="page">
				<div class="circle-loader">
					<div class="circle-line">
						<div class="circle circle-blue"></div>
						<div class="circle circle-blue"></div>
						<div class="circle circle-blue"></div>
					</div>
					<div class="circle-line">
						<div class="circle circle-yellow"></div>
						<div class="circle circle-yellow"></div>
						<div class="circle circle-yellow"></div>
					</div>
					<div class="circle-line">
						<div class="circle circle-red"></div>
						<div class="circle circle-red"></div>
						<div class="circle circle-red"></div>
					</div>
					<div class="circle-line">
						<div class="circle circle-green"></div>
						<div class="circle circle-green"></div>
						<div class="circle circle-green"></div>
					</div>
				</div>
			</div>
			<!--中间课程内容  -->
		</div>
		<!-- 左侧menu -->
		<div id="menu" class="nav">
			<!-- 浮动菜单列表 -->
		</div>
		<div class="friendly_link">
			<div class="friendly_link_nei">
				<ul class="link_title_wai">
					<li class="link_title link_active">合作伙伴</li>
					<li class="link_title">全国院校</li>
					<li class="link_title">友情链接</li>
				</ul>
				<div class="cooper_list_wai">
					<div id="cooper_list" class="cooper_list" style="display:block;">
						<!--合作伙伴列表  -->
					</div>
					<div class="cooper_list" style="padding:30px;">
						<!--全国院校列表  -->
						<ul class="cooper_list_ul">
							<li><a href="#">石家庄校区</a></li>
							<li><a href="#">北京校区</a></li>
							<li><a href="#">青岛校区</a></li>
							<li><a href="#">邯郸校区</a></li>
							<li><a href="#">天津校区开发</a></li>
							<li><a href="#">长春校区开发</a></li>
							<li><a href="#">沈阳校区开发</a></li>
							<li><a href="#">西安校区开发</a></li>
							<li><a href="#">郑州校区开发</a></li>
							<li><a href="#">济南校区开发</a></li>
							<li><a href="#">太原校区开发</a></li>
							<li><a href="#">合肥校区开发</a></li>
							<li><a href="#">长沙校区开发</a></li>
							<li><a href="#">武汉校区开发</a></li>
							<li><a href="#">南京校区开发</a></li>
							<li><a href="#">杭州校区开发</a></li>
							<li><a href="#">南昌校区开发</a></li>
							<li><a href="#">广州校区开发</a></li>							
						</ul>
					</div>
					<div class="cooper_list" style="padding:30px;">
						<!--友情链接列表  -->
						<ul class="cooper_list_ul ul2">
							<li><a href="http://qy.58.com/32598195168780/?psid=111375641195725083677060218&entinfo=27005947304907_j&PGTID=0d302408-000f-165d-64f0-6fcdde90c84b&ClickID=2">58同城</a></li>
							<li><a href="http://kejun.sjz.ganji.com/">赶集网</a></li>
							<li><a href="http://blog.sina.com.cn/u/5580527836">新浪博客</a></li>
							<li><a href="http://weibo.com/jingrenedu">新浪微博</a></li>
							<li><a href="http://www.houxue.com/sjz/xuexiao-249501.html">厚学网</a></li>
							<li><a href="http://sjz.jiaoyubao.cn/edu/sjzjrjy/">教育宝</a></li>
							<li><a href="https://koubei.baidu.com/s/www.jingrenedu.cn?from=ps_pc3">百度口碑</a></li>
							<li><a href="http://www.baike.com/wiki/京人教育&prd=button_doc_entry">互动百科</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--登录注册窗口  -->
	<jsp:include
		page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/footer.jsp"></jsp:include>
	
</body>
</html>