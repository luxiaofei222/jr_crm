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
			<li><i class="icon iconfont icon-kaoshi"></i>模拟考试</li>
			<li class="on"
				onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-zhihebi"></i>练习记录</li>
			<li onclick="get_nengli_main(${questionCourse.question_course_id})"><i class="icon iconfont icon-baogaopinggu"></i>能力评估报告</li>
		</ul>
	</div>
	<div class="que_content" style="border: none;">
		<!-- 练习记录 -->
		<div class='records_zong'>
			<ul class="records_left">
				<li class="records_active" onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i
					class="icon iconfont icon-shunxulianxi-copy"></i><span>练习记录</span>
				</li>
				<li onclick="get_cuoti_record(${questionCourse.question_course_id})"><i class="icon iconfont icon-cuowu"></i><span>错题记录</span></li>
				<li style="border: none;" onclick="get_user_collection(${questionCourse.question_course_id})"><i
					class="icon iconfont icon-shoucang" style="margin-top: -2px;"></i><span>我的收藏</span>
				</li>
			</ul>
			<!-- 练习记录开始 -->
			<div id="cuoti_shoucang_list">
			<div class="records_right" style="display: block;">
				<div class="records_right_top">
					<span>类型：</span>
					<ul class="records_topul">
						<li class="records_active" onclick="chapter_jump_page(1)" style="cursor: pointer;">章节练习</li>
						<li style="cursor: pointer;" onclick="zhenti_jump_page(${questionCourse.question_course_id})" >历年真题</li>
						<li style="cursor: pointer;" onclick="monikaoshi_jump_page(${questionCourse.question_course_id})">模拟考试</li>
					</ul>
				</div>
				<div id="chapter_list">
					<!--章节练习列表  -->
					<div class="records_right_bottom" style="display: block;">
						<ul class="records_right_ul"> 
							<c:forEach items="${chapterRecords.list }" var="record">
								<li class="records_right_li">
									<ul class="records_lineul">
										<li class="records_line1">
											<div class="records_line1_one">
												<span class="records_line1_one1">[章节练习]</span> <span
													class="records_line1_one2">${questionCourse.question_course_name }&nbsp;&nbsp;
													<c:if test="${record.chapterExercises.chapter_level==3 }">
                  《${record.chapterExercises.chapter_name }》
                  </c:if> <c:if
														test="${record.chapterExercises.chapter_level==2 }">
                  《第${record.chapterExercises.chapter_number_str }节${record.chapterExercises.chapter_name }》
                  </c:if> <c:if
														test="${record.chapterExercises.chapter_level==1 }">
                  《第${record.chapterExercises.chapter_number_str }章${record.chapterExercises.chapter_name }》
                  </c:if>
												</span>
											</div>
											<div class="records_line1_two">
												<span class="records_line1_two1"><fmt:formatDate
														value="${record.recourd_time }" /></span> <span
													class="records_line1_two2">总共：${record.lianxi_number }题</span>
												<span class="records_line1_two3">做对：${record.lianxi_right_number }题</span>
											</div>
										</li>
										<li class="records_line2"
											onclick="get_chapter_jiexi_jilu(${questionCourse.question_course_id},${record.chapter_exercises_id },'${record.question_order_number }')">
											查看解析</li>
										<li class="records_line3"
											onclick="get_reset_chapter_question(${questionCourse.question_course_id},${record.chapter_exercises_id },'${record.question_order_number }')">
											再做一次</li>
									</ul>
								</li>
							</c:forEach>
						</ul>
					</div>
					
					<!--练习记录-章节练习的分页   -->
					<div class="pagination">
						<c:if test="${chapterRecords.hasPreviousPage==true}">
							<a
								onclick="chapter_jump_page(${chapterRecords.pageNumber-1})">上一页</a>
						</c:if>
						<c:forEach items="${chapterRecords.navigatePageNumbers }"
							var="page">
							<c:choose>
								<c:when test="${chapterRecords.pageNumber==page}">
									<a class="active">${page}</a>
								</c:when>
								<c:otherwise>
									<a
										onclick="chapter_jump_page(${page})">${page}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${chapterRecords.hasNextPage==true}">
							<a onclick="chapter_jump_page(${chapterRecords.pageNumber+1})">下一页</a>
						</c:if>
						<span class="page-num">${chapterRecords.pageNumber}/${chapterRecords.pages }页</span>
						<span class="input"> <input type="text" id="page_number">
						</span> <a onclick="to_jump_shuru_page(${chapterRecords.pages })">跳转</a>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/videofoot.jsp"></jsp:include>
</body>
</html>