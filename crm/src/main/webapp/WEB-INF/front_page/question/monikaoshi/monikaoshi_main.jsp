<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/question/index/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/question/select_question/question-common.css" />
<link href="/css/school/back/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/iconfont/iconfont.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/question/question-common.js"></script>
<script type="text/javascript" src="/js/question/front/frontchapter.js"></script>
<title>京人网校题库-练习记录</title>
</head>
<body>
	<div class="question_title">
		<div class="que_top">
			<a href="/index.jsp"><img
				src="/images/school/front/index/logo.png" /></a>
			<div class="question_tab">
				<h1>${courseMenu.course_name }</h1>
				<h2>${questionCourse.question_course_name }</h2>
				<div class="question_others">
					<a href="javascript:void(0)">[切换]</a>
					<div class="qiehuan">
						<ul>
							<i class="fa fa-caret-up"></i>
							<div class="top_qiehuan">
								<h3>${courseMenu.course_name }</h3>
								<a href="/questionindex/get_question_index.html">[切换考试]</a>
							</div>
							<c:forEach items="${questionCourses }" var="question">
								<li><a
									href="/front_chapter/get_chapter_list.html?question_course_id=${question.question_course_id }">${question.question_course_name }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="que_nav">
		<ul>
			<li onclick="get_question_index(${questionCourse.course_id})"><i
				class="icon iconfont icon-fangzi"></i>题库首页</li>
			<li onclick="get_chapter_list(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-shu"></i>章节练习</li>
			<li onclick="get_zhenti_year(${questionCourse.question_course_id})"><i class="icon iconfont icon-shitiguanli"></i>历年真题</li>
			<li class="on" onclick="get_moni_kaoshi(${questionCourse.question_course_id})"><i class="icon iconfont icon-kaoshi"></i>模拟考试</li>
			<li onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-zhihebi"></i>练习记录</li>
			<li onclick="get_nengli_main(${questionCourse.question_course_id})"><i class="icon iconfont icon-baogaopinggu"></i>能力评估报告</li>
		</ul>
	</div>
	<div class="que_content" style="border: none;min-height:363px;">
		<!-- 历年真题 -->
		<div class="linian_zhenti">
			<div class="linian_zhenti_title">
				<div class="panel-hd">
					<div class="row">
						<div class="col-7">试卷名称</div>
						<div class="col-2">日期</div>
						<div class="col-1">状态</div>
						<div class="col-2"></div>
					</div>
				</div>
				<ul class="panel-bd">
				
				<c:forEach items="${zhentiYears }" var="zhenti">
					<li class="list-item">
						<div class="nth-1 col-7">
							<strong title="${zhenti.zhenti_year }年<c:if test="${not empty zhenti.zhenti_yue }">（${zhenti.zhenti_yue }月）</c:if>${courseMenu.course_name }《${questionCourse.question_course_name }》${zhenti.zhenti_name }"> <em
								class="hl-orange2">[模拟考试]</em> <span>${zhenti.zhenti_year }年<c:if test="${not empty zhenti.zhenti_yue }">（${zhenti.zhenti_yue }月）</c:if>${courseMenu.course_name }《${questionCourse.question_course_name }》${zhenti.zhenti_name }
							</span>
							<c:if test="${zhenti.zhneti_shoufei_type=='免费' }">
							 <img src="/images/question/chapter/mianfei.png">
							 </c:if>
							 <c:if test="${zhenti.zhneti_shoufei_type=='收费' }">
							 <img src="/images/question/chapter/shoufei.png">
							 </c:if>
							</strong>
							<p>
								<span>总题：${zhenti.zhenti_zongtishu }道</span> <span>考试时长：${zhenti.zhenti_kaoshi_time } 分钟</span> <span>总分：${zhenti.zhenti_zongfen }
									分</span>
							</p>
						</div>
						<div class="nth-2 col-2">
							<span><fmt:formatDate
										pattern="yyyy-MM-dd" value="${zhenti.zhenti_time }" /></span>
						</div>
						<div class="nth-3 col-1">
						<c:if test="${zhenti.is_jixu==0 }">
							<span>未开始</span>
						</c:if>
						<c:if test="${zhenti.is_jixu==1 }">
							<span><a href="/front_zhenti/get_zhenti_question_jiexi.html?question_course_id=${questionCourse.question_course_id}&zhenti_id=${zhenti.zhenti_id }&zhenti_record_id=${zhenti.zhenti_record_id}">查看解析</a></span>
						</c:if>
						<c:if test="${zhenti.is_jixu==2 }">
							<span>未完成</span>
						</c:if>
						</div>
						<div class="nth-4 col-2">
						<c:if test="${zhenti.is_jixu==0 }">
							<a  href="/front_zhenti/get_zhenti_year_introduce.html?question_course_id=${questionCourse.question_course_id}&zhenti_id=${zhenti.zhenti_id }">开始做题</a>
						</c:if>
							<c:if test="${zhenti.is_jixu==1 }">
							<a class="finish" href="/front_zhenti/get_zhenti_year_introduce.html?question_course_id=${questionCourse.question_course_id}&zhenti_id=${zhenti.zhenti_id }">再做一次</a>
						</c:if>
							<c:if test="${zhenti.is_jixu==2 }">
							<a class="nofinish" href="/front_zhenti/get_zhenti_question_jixu.html?question_course_id=${questionCourse.question_course_id}&zhenti_id=${zhenti.zhenti_id }&zhenti_record_id=${zhenti.zhenti_record_id}">继续答题</a>
						</c:if>
						</div>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/videofoot.jsp"></jsp:include>
</body>
</html>