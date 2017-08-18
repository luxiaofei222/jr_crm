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
			<c:if test="${zhentiYears.zhentitype=='历年真题' }">
			<li class="on" onclick="get_zhenti_year(${questionCourse.question_course_id})"><i class="icon iconfont icon-shitiguanli"></i>历年真题</li>
			<li onclick="get_moni_kaoshi(${questionCourse.question_course_id})"><i class="icon iconfont icon-kaoshi"></i>模拟考试</li>
			</c:if>
			<c:if test="${zhentiYears.zhentitype=='模拟考试' }">
			<li onclick="get_zhenti_year(${questionCourse.question_course_id})"><i class="icon iconfont icon-shitiguanli"></i>历年真题</li>
			<li class="on" onclick="get_moni_kaoshi(${questionCourse.question_course_id})"><i class="icon iconfont icon-kaoshi"></i>模拟考试</li>
			</c:if>
			<li onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-zhihebi"></i>练习记录</li>
			<li onclick="get_nengli_main(${questionCourse.question_course_id})"><i class="icon iconfont icon-baogaopinggu"></i>能力评估报告</li>
		</ul>
	</div>
	<div class="que_content" style="border: none;min-height:363px;">
		<!-- 历年真题题型介绍-->
	 <div class="moni_wai">
    <div class="moni_bt">
    <c:if test="${zhentiYears.zhentitype=='历年真题' }">
     ${zhentiYears.zhenti_year }年
     	<c:if test="${not empty zhentiYears.zhenti_yue }">
	（${zhentiYears.zhenti_yue }月）
	</c:if>
     ${courseMenu.course_name }《${questionCourse.question_course_name }》真题及答案解析
    </c:if>
   	 <c:if test="${zhentiYears.zhentitype=='模拟考试' }">
      ${zhentiYears.zhenti_year }年
      	<c:if test="${not empty zhentiYears.zhenti_yue }">
	（${zhentiYears.zhenti_yue }月）
	</c:if>
      ${courseMenu.course_name }《${questionCourse.question_course_name }》${zhentiYears.zhenti_name }
     </c:if>
    </div>
    <hr>
    <div class="moni_nian">
        <div class="moni_line1">
          <div class="moni_line11">
            <i class="icon iconfont icon-rili"></i>
            <span>年份：${zhentiYears.zhenti_year }</span>
          </div>
          <div class="moni_line12">
            <i class="icon iconfont icon-datiyingxiao"></i>
            <span>总题数：${zhentiYears.zhenti_zongtishu }道</span>
          </div>
        </div>
        <div class="moni_line2">
          <div class="moni_line21">
            <i class="icon iconfont icon-shitiguanli"></i>
            <span>试卷类型:${zhentiYears.zhentitype}</span>
          </div>
           <div class="moni_line22">
            <i class="icon iconfont icon-shiliangzhinengduixiang1"></i>
            <span>考试时间:${zhentiYears.zhenti_kaoshi_time }分钟</span>
          </div>
        </div>
    </div>
    <a href="/front_zhenti/get_zhenti_question_now.html?question_course_id=${questionCourse.question_course_id}&zhenti_id=${zhentiYears.zhenti_id }" class="start_que">开始做题</a>
    <hr>
    <div class="txjs">
      <div class="txjs_title">
        <i class="icon iconfont icon-dingzi"></i>
        <span>题型介绍</span>
      </div>
      <div class="txjs_info">
      <c:forEach items="${zhentiTypeIntroduces }" var="intro" varStatus="vs">
        <div>${intro.question_type }：</div>
        <div>
<c:if test="${vs.index==0 }">
一
</c:if>
<c:if test="${vs.index==1 }">
二
</c:if>
<c:if test="${vs.index==2 }">
三
</c:if>
<c:if test="${vs.index==3 }">
四
</c:if>
、${intro.question_type }(${intro.introduce_content })</div>
       </c:forEach>
      </div>  
    </div>
  </div>
	</div>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/videofoot.jsp"></jsp:include>
</body>
</html>