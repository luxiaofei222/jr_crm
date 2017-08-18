<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/question/index/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/question/select_question/question-common.css" />
<link href="/css/school/back/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/iconfont/iconfont.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>

<script type="text/javascript" src="/js/question/front/frontchapter.js"></script>
<title>京人网校题库-章节练习</title>
</head>
<script>
 $(function(){
	var question_course_id=$("#question_course_id").val();//课程分类ID
	$.post("/front_chapter/get_chapter_option_list.html",{
		'question_course_id':question_course_id
	},function(data){
		$(".que_content").html(data);
	})
}) 
</script>
<body>
<div class="question_title">
  <div class="que_top">
    <a href="/index.jsp"><img src="/images/school/front/index/logo.png" /></a>
    <div class="question_tab">
      <h1>${courseMenu.course_name }</h1>
      <h2>${questionCourse.question_course_name }</h2>
      <div class="question_others">
        <a href="javascript:void(0)">[切换]</a>
        <div class="qiehuan">
          <ul>
            <i class="fa fa-caret-up"></i>
            <div class="top_qiehuan">
              <h3>${courseMenu.course_name }</h3><a href="/questionindex/get_question_index.html">[切换考试]</a>
            </div>
            <c:forEach items="${questionCourses }" var="question">
            <li><a href="/front_chapter/get_chapter_list.html?question_course_id=${question.question_course_id }">${question.question_course_name }</a></li>
            </c:forEach>
          </ul>
        </div>
      </div>
    </div>
  </div> 
</div>
<div class="que_nav">
  <ul>
    <li onclick="get_question_index(${questionCourse.course_id})"><i class="icon iconfont icon-fangzi"></i>题库首页</li>
    <li class="on" onclick="get_chapter_list(${questionCourse.question_course_id})"><i class="icon iconfont icon-shu"></i>章节练习</li>
    <li onclick="get_zhenti_year(${questionCourse.question_course_id})"><i class="icon iconfont icon-shitiguanli"></i>历年真题</li>
    <li onclick="get_moni_kaoshi(${questionCourse.question_course_id})"><i class="icon iconfont icon-kaoshi"></i>模拟考试</li>
    <li onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i class="icon iconfont icon-zhihebi"></i>练习记录</li>
    <li onclick="get_nengli_main(${questionCourse.question_course_id})"><i class="icon iconfont icon-baogaopinggu"></i>能力评估报告</li>
  </ul>
</div>
<div class="que_content">
<input type="hidden" id="question_course_id" value="${questionCourse.question_course_id }" />
  <!-- 题目列表 -->
  <div class="main_jiazai">
	<div class="loadEffect">
		<span></span>
		<span></span>
		<span></span>
		<span></span>
		<span></span>
		<span></span>
		<span></span>
		<span></span>
	</div>
</div>
</div>
<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/videofoot.jsp"></jsp:include>
</body>
</html>