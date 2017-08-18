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
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/iconfont/iconfont.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/question/question-common.js"></script>
<script type="text/javascript" src="/js/question/front/frontchapter.js"></script>
<title>题库课程首页</title>
</head>
<body>
<div class="question_title">
  <div class="que_top">
    <a href="/index.jsp"><img src="/images/school/front/index/logo.png" /></a>
    <div class="question_tab">
      <h1>${courseMenu.course_name }</h1>
      <h2>${question_course_name }</h2>
      <div class="question_others">
        <a href="javascript:void(0)">[切换]</a>
        <div class="qiehuan">
          <ul>
            <i class="fa fa-caret-up"></i>
            <div class="top_qiehuan">
              <h3>${courseMenu.course_name }</h3><a href="/questionindex/get_question_index.html">[切换考试]</a>
            </div>
            <c:forEach items="${questionCourses }" var="question">
            <li><a href="/questionindex/get_selest_question_qiehuan.html?question_course_id=${question.question_course_id }">${question.question_course_name }</a></li>
            </c:forEach>
          </ul>
        </div>
      </div>
    </div>
  </div> 
</div>
<div class="que_nav">
  <ul>
    <li class="on" onclick="get_question_index(${courseMenu.course_id})"><i class="icon iconfont icon-fangzi"></i>题库首页</li>
    <li onclick="get_chapter_list(${question_course_id})"><i class="icon iconfont icon-shu"></i>章节练习</li>
    <li onclick="get_zhenti_year(${question_course_id})"><i class="icon iconfont icon-shitiguanli"></i>历年真题</li>
    <li onclick="get_moni_kaoshi(${question_course_id})"><i class="icon iconfont icon-kaoshi"></i>模拟考试</li>
    <li onclick="get_chapter_record_main(${question_course_id})"><i class="icon iconfont icon-zhihebi"></i>练习记录</li>
    <li onclick="get_nengli_main(${question_course_id})"><i class="icon iconfont icon-baogaopinggu"></i>能力评估报告</li>
  </ul>
</div>

<div class="que_content" style="border:none;">
  <div class="tiku_index">
    <div class="tiku_index_title">
      <ul>
        <li>
          <a class="list-item" href="/front_chapter/get_user_collection_question.html?question_course_id=${question_course_id}">
            <p class="list--data-num"><strong>${collection_number }</strong>题</p>习题收藏
          </a>
        </li>
        <li>
          <a class="list-item" href="/front_chapter/get_chapter_record_main.html?question_course_id=${question_course_id}">
            <p class="list--data-num"><strong>${cuowu_number }</strong>题</p>章节错题
          </a>
        </li>
        <li>
          <a class="list-item" href="/front_chapter/get_chapter_record_main.html?question_course_id=${question_course_id}">
            <p class="list--data-num"><strong>${chapter_number }</strong>条</p>习题记录
          </a>
        </li>
       
        <c:if test="${empty zhentiYears }">
         <li class="bujixu">
          <a class="list-item-xh" href="javascript:void(0)">
            <span class="">
                <i class="icon iconfont icon-xunhuan"></i>
                继续考试
            </span>
            <p class="linkArea-txt">暂无上次考试信息</p>
        </a>
        </li>
        </c:if>
         <c:if test="${not empty zhentiYears }">
         <c:if test="${zhentiYears.zhentitype=='历年真题' }">
          <li>
           <a class="list-item-xh" href="javascript:void(0)">
            <span class="">
                <i class="icon iconfont icon-xunhuan"></i>
               	 继续考试
            </span>
            <p class="linkArea-txt">${zhentiYears.zhenti_year }年${courseMenu.course_name }《${question_course_name }》真题及答案解析</p>
        </a>
        </li>
         </c:if>
          <c:if test="${zhentiYears.zhentitype=='模拟考试' }">
           <li>
           <a class="list-item-xh" href="javascript:void(0)">
            <span class="">
                <i class="icon iconfont icon-xunhuan"></i>
               	 继续考试
            </span>
            <p class="linkArea-txt">${zhentiYears.zhenti_year }年${courseMenu.course_name }《${question_course_name }》${zhentiYears.zhenti_name }</p>
        </a>
        </li>
         </c:if>
         </c:if>
      </ul>
    </div>
    <div class="tiku_kinds">
      <ul>
        <li class="list-item">
            <h3>章节练习</h3>
            <p>紧跟考试大纲，海量试题综合演练，哪里不会练哪里</p>
            <a href="/front_chapter/get_chapter_list.html?question_course_id=${question_course_id }">点击进入</a>
        </li>
        <li class="list-item">
            <h3>历年真题</h3>
            <p>历年考试真题囊括其中，京人网校重磅推出真题视频解析全方位为您解读考点</p>
            <a href="/front_zhenti/get_zhenti_year_main.html?question_course_id=${question_course_id }">点击进入</a>
        </li>
        <li class="list-item">
            <h3>模拟考试</h3>
            <p>依托行业最先进智能算法，全真模拟实战现场，让你学习考试更简单</p>
            <a href="/front_zhenti/get_moni_kaoshi_main.html?question_course_id=${question_course_id }">点击进入</a>
        </li>
      </ul>
      <div class="clear"></div>
    </div>
  </div>   
</div>
<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/videofoot.jsp"></jsp:include>
</body>
</html>