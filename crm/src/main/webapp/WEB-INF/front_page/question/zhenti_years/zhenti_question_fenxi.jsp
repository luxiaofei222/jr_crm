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
<script type="text/javascript"
	src="/js/school/front/index/jquery-v1.10.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/question/front/frontchapter.js"></script>
<title>历年真题—做题中</title>
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
				<c:if test="${zhentiYears.zhentitype=='历年真题' }">
			<li class="on"
				onclick="get_zhenti_year(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-shitiguanli"></i>历年真题</li>
			<li onclick="get_moni_kaoshi(${questionCourse.question_course_id})"><i class="icon iconfont icon-kaoshi"></i>模拟考试</li>
			</c:if>
			<c:if test="${zhentiYears.zhentitype=='模拟考试' }">
			<li onclick="get_zhenti_year(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-shitiguanli"></i>历年真题</li>
			<li class="on" onclick="get_moni_kaoshi(${questionCourse.question_course_id})"><i class="icon iconfont icon-kaoshi"></i>模拟考试</li>
			</c:if>
			<li class="on"
				onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-zhihebi"></i>练习记录</li>
			<li onclick="get_nengli_main(${questionCourse.question_course_id})"><i class="icon iconfont icon-baogaopinggu"></i>能力评估报告</li>
		</ul>
	</div>
	<div class="que_content" style="border: none;">
	 <!-- 试卷分析 -->
  <div class="fenxi_wai">
  	<c:if test="${zhentiYears.zhentitype=='历年真题' }">
    <h1 class="fenxi_title">${zhentiYears.zhenti_year }年
    <c:if test="${not empty zhentiYears.zhenti_yue }">
	（${zhentiYears.zhenti_yue }月）
	</c:if>
    ${courseMenu.course_name }《${questionCourse.question_course_name }》真题及答案解析</h1>
    </c:if>
    	<c:if test="${zhentiYears.zhentitype=='模拟考试' }">
    <h1 class="fenxi_title">${zhentiYears.zhenti_year }年
    <c:if test="${not empty zhentiYears.zhenti_yue }">
	（${zhentiYears.zhenti_yue }月）
	</c:if>
    ${courseMenu.course_name }《${questionCourse.question_course_name }》${zhentiYears.zhenti_name }</h1>
    </c:if>
    <div class="line-dashed"></div>
    <div class="fenxi_title_sub">
      交卷时间：<fmt:formatDate value="${record.recourd_time }" />
    </div>
    <ul class="fenxi_ul">
      <li class="list-item">
        <h5 class="list-tit ">试卷得分</h5>
        <p class="card_epz hl-red">
          <strong>${fenzhi }</strong>分
        </p>
        <p class="card-epa">总分：${zhentiYears.zhenti_zongfen }分</p>
      </li>
      <li class="list-item">
        <h5 class="list-tit">作答时间</h5>
        <p class="card_epz hl-green">
          ${record.zhuanhuan_time }
        </p>
        <p class="card-epa">总时间： ${zhentiYears.zhenti_kaoshi_time }分钟</p>
      </li>
      <li class="list-item">
        <h5 class="list-tit">您答对了</h5>
        <p class="card_epz hl-green">
          <strong>${dadui }</strong>道
        </p>
        <p class="card-epa">正确率：<fmt:formatNumber type="number" value="${dadui /zongtishu*100 }" pattern="0.00" maxFractionDigits="2"/>%</p>
      </li>
    </ul>
    <h3 class="fenxi_title2">
      本试卷 ${zongtishu } 道题目
    </h3>
    <ul class="fenxi_number">
    <c:forEach items="${zhentiTypeIntroduces }" var="intro" varStatus="vs">
      <li class="list-item1">
        <strong class="list-tit">${intro.question_type }</strong>
        <div class='numbers'>
        <c:forEach items="${intro.zhentiRecords }" var="record">
        <c:if test="${record.is_right=='错误' }">
          <a href="javascript:void(0);" class='wrong'>${record.xuhao }</a>
         </c:if>
          <c:if test="${record.is_right=='正确' }">
          <a href="javascript:void(0);" class='correct'>${record.xuhao }</a>
         </c:if>
          </c:forEach>
        </div>
      </li>
      </c:forEach>
    </ul>
    <div class="line-dashed"></div>
    <div class="btn-group">
      <a href="/front_zhenti/get_zhenti_question_jiexi.html?question_course_id=${questionCourse.question_course_id}&zhenti_id=${zhentiYears.zhenti_id }&zhenti_record_id=${record.zhenti_record_id}" class='check_daan'>查看答案</a>
      <a href="/front_zhenti/get_zhenti_year_introduce.html?question_course_id=${questionCourse.question_course_id}&zhenti_id=${zhentiYears.zhenti_id }" class='check_reset'>重做试卷</a>
    </div>
  </div>
	</div>
	</div>
	<div class="coverfull">
		<div class="spinner">
			<div class="bounce1"></div>
			<div class="bounce2"></div>
			<div class="bounce3"></div>
		</div>
	</div>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/videofoot.jsp"></jsp:include>
</body>

</html>