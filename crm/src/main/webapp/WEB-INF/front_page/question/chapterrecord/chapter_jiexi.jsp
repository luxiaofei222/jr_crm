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
<script type="text/javascript" src="/js/question/question-jiexi.js"></script>
<script type="text/javascript" src="/js/question/front/frontchapter.js"></script>
<title>京人网校题库-练习解析</title>
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
			<li><i class="icon iconfont icon-shitiguanli"></i>历年真题</li>
			<li><i class="icon iconfont icon-kaoshi"></i>模拟考试</li>
			<li class="on"
				onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-zhihebi"></i>练习记录</li>
			<li onclick="get_nengli_main(${questionCourse.question_course_id})"><i class="icon iconfont icon-baogaopinggu"></i>能力评估报告</li>
		</ul>
	</div>
	<div class="que_content" style="border: none;">
		<!-- 解析记录 -->
		<div class="page-content">
			<div class="exam-header">
				<h1 class="title-page">${questionCourse.question_course_name }
					<c:if test="${chapterExercises.chapter_level==3 }">
                  《${chapterExercises.chapter_name }》
                  </c:if>
					<c:if test="${chapterExercises.chapter_level==2 }">
                  《第${chapterExercises.chapter_number_str }节${chapterExercises.chapter_name }》
                  </c:if>
					<c:if test="${chapterExercises.chapter_level==1 }">
                  《第${chapterExercises.chapter_number_str }章${chapterExercises.chapter_name }》
                  </c:if>
				</h1>
				<div class="exam-header-block progress">
					<span class="progress-num">共${total_number }道题</span>
					<div class="progress-bar js-progress-bar">
						<span style="width: ${finish_number/total_number*100}%"></span><i>已完成
							${finish_number}/${total_number }</i>
					</div>
				</div>
			</div>
			<div class="exam-main">
				<c:forEach items="${chapterRecords }" var="recourd" varStatus="vs">
					<c:if test="${recourd.is_right=='错误'&&recourd.user_answer!='无' }">
						<div class="collect_nei collect_wrong" id="item${vs.index+1 }">
					</c:if>
					<c:if test="${recourd.is_right=='正确' }">
						<div class="collect_nei collect_correct" id="item${vs.index+1 }">
					</c:if>
					<c:if test="${recourd.user_answer=='无' }">
						<div class="collect_nei" id="item${vs.index+1 }">
					</c:if>
					<span class="collect_icon"></span>
					<div class="collect_title">
						<span class="num">${vs.index+1 }</span> <span class='line'></span>
						<em class='type'>[${recourd.chapterQuestion.question_type }]</em>
						<p>${recourd.chapterQuestion.question_name }</p>
					</div>
					<c:if test="${recourd.chapterQuestion.question_type=='多选题' }">
						<ul class="multi_choices">
							<c:forEach
								items="${recourd.chapterQuestion.chapterQuerstionOptions }"
								var="option">
								<li>
								<c:if test="${recourd.user_answer!='无' && recourd.is_right=='正确'}"><!-- 用户填写了答案 -->
								<c:if test="${option.duoxuan_type_jiexi==1 }">
								<span class="l collect_select">${option.option_number }</span>
								</c:if>
								<c:if test="${option.duoxuan_type_jiexi==0 }">
								<span class="l">${option.option_number }</span>
								</c:if>
								</c:if>
								<c:if test="${recourd.user_answer!='无' && recourd.is_right=='错误'}"><!-- 用户做错了 -->
								<c:if test="${option.duoxuan_type_jiexi==1 }">
								<span class="l collect_select">${option.option_number }</span>
								</c:if>
								<c:if test="${option.duoxuan_type_jiexi==2 }">
								<span class="l collect_select_half">${option.option_number }</span>
								</c:if>
								<c:if test="${option.duoxuan_type_jiexi==0 }">
								<span class="l collect_select_wrong">${option.option_number }</span>
								</c:if>
								<c:if test="${empty option.duoxuan_type_jiexi }">
								<span class="l">${option.option_number }</span>
								</c:if>
								</c:if>
									<c:if test="${recourd.user_answer=='无'}"><!-- 用户未作答了 -->
								<c:if test="${option.duoxuan_type_jiexi==1 }">
								<span class="l collect_select">${option.option_number }</span>
								</c:if>
								<c:if test="${option.duoxuan_type_jiexi==0 }">
								<span class="l">${option.option_number }</span>
								</c:if>
								</c:if>
									<input type="checkbox" class="multiclass">
									<div class='r'>${option.option_content }</div>
									
								</li>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${recourd.chapterQuestion.question_type=='单选题' }">
						<ul class="single_choices">
							<c:forEach
								items="${recourd.chapterQuestion.chapterQuerstionOptions }"
								var="option">
								<li><c:if test="${recourd.user_answer!='无' }">
										<c:if
											test="${recourd.user_answer==option.option_number && recourd.is_right=='错误'}">
											<span class="l collect_select_wrong">${option.option_number }</span>
											<input type="radio" name="single1" value=''
												class="singleclass">
											<div class='r'>${option.option_content }</div>
										</c:if>
										<c:if
											test="${recourd.user_answer==option.option_number && recourd.is_right!='错误'}">
											<span class="l collect_select">${option.option_number }</span>
											<input type="radio" name="single1" value=''
												class="singleclass">
											<div class='r'>${option.option_content }</div>
										</c:if>
										<c:if test="${recourd.user_answer!=option.option_number}">
											<li><span class="l">${option.option_number }</span> <input
												type="radio" name="single1" value='' class="singleclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
									</c:if> 
									<c:if test="${recourd.user_answer=='无' }">
									<c:if test="${recourd.chapterQuestion.answer==option.option_number && recourd.is_right=='错误'}">
											<span class="l collect_select">${option.option_number }</span>
											<input type="radio" name="single1" value=''
												class="singleclass">
											<div class='r'>${option.option_content }</div>
										</c:if>
										<c:if test="${recourd.chapterQuestion.answer!=option.option_number}">
											<li><span class="l">${option.option_number }</span> <input
												type="radio" name="single1" value='' class="singleclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
										</c:if>
										</li>
							</c:forEach>
						</ul>
					</c:if>
					<div class="jiucuo">
						<div class="jiucuo_answer">
							<c:if test="${recourd.is_collection==1 }">
								<span class="collect collect_active" onclick="quxiao_question_collection(this,${recourd.chapter_recourd_id})"> <i
									class="icon-star2 collect_active"></i><em>取消收藏</em>
								</span>
								</c:if>
								<c:if test="${recourd.is_collection==0}">
								<span class="collect collect_active" onclick="collection_question(this,${recourd.chapter_recourd_id})"> <i
									class="icon-star2"></i><em>收藏本题</em>
								</span>
								</c:if>
								<span class="btn-report-error" onclick="get_correction(${recourd.chapter_recourd_id})"> <i class="icon-star3"></i>
									<em>本题纠错</em>
								</span>
							<div class="answer_inline" style="display: block;">
								<span class="as-title">参考答案：</span>
								<div class="as-cont hl-green">
									${recourd.chapterQuestion.answer }</div>
							</div>
							<div class="answer_inline" style="display: block;">
								<span class="as-title">我的答案：</span>
								<div class="as-cont hl-red">
								<c:if test="${recourd.user_answer=='无' }">
								未作答
								</c:if>
								<c:if test="${recourd.user_answer!='无' }">
								${recourd.user_answer }
								</c:if>
								</div>
							</div>
						</div>
						<div class='fold-bd' style="display: block;">
							<ul class="list-analyze">
								<li><span class='l'>试题难度：</span>
									<div class="r">
										<i class="icon-pen pen-${recourd.chapterQuestion.difficulty }">
											<span></span>
										</i>
									</div></li>
								<li><span class="l">
										统&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计： </span>
									<div class='r'>
										该题您答过 <b class="hl-orange">${recourd.user_answer_number }</b>次，正确率为
										<b class="hl-green"><fmt:formatNumber type="number" value="${recourd.user_answer_right_number/recourd.user_answer_number*100 }" pattern="0.00" maxFractionDigits="2"/>%</b>
									</div></li>
								<li><span class="l"> 参考解析： </span>
									<div class="r">${recourd.chapterQuestion.analysis }</div></li>
							</ul>
						</div>
					</div>
			</div>
			</c:forEach>
		</div>
		<div class="exam-side">
			<div class="mod mod--border modOperate">
				<div class="mod-hd">
					<p class="time">
						<i class="icon icon-clock2"></i>已用时 <b class="hl-orange js-timer">${answer_time }</b>
					</p>
				</div>
				<div class="mod-bd">
					<a href="/front_chapter/get_chapter_record_main.html?question_course_id=${questionCourse.question_course_id}">返回目录</a> <a
						href="/front_chapter/to_reset_chapter_question.html?question_course_id=${questionCourse.question_course_id}&question_order_number=${question_order_number}&chapter_exercises_id=${chapterExercises.chapter_exercises_id }" state="on">再做一次</a>
				</div>
				<div class="mod-ft">
					<label class="checkbox js-autoNext"> <input type="checkbox"
						value="1" class="check_wrong"> <span class="checktitle">只看错题</span>
					</label>
				</div>
			</div>
			<div class="mod mod--border modAnswerCard">
				<div class="mod-hd">
					<h3 class="mod-hd-tit">答题卡</h3>
				</div>
				<div class="mod-bd">
					<ul class="list--number2 js-answer-card">
						<li class="list-item"><strong class="list-tit">题号</strong>
							<div class="numbers js-card-number">
							<c:forEach items="${chapterRecords }" var="recourd" varStatus="vs">
										<span>
										<c:if test="${recourd.is_right=='错误' &&recourd.user_answer!='无'}">
										<a class="wrong" href="#item${vs.index+1 }">${vs.index+1 }</a>
										</c:if>
										<c:if test="${recourd.is_right=='正确'&&recourd.user_answer!='无' }">
										<a class="correct" href="#item${vs.index+1 }">${vs.index+1 }</a>
										</c:if>
										<c:if test="${recourd.user_answer=='无' }">
										<a href="#item${vs.index+1 }">${vs.index+1 }</a>
										</c:if>
										</span>
									</c:forEach>
							</div></li>
					</ul>
					<div style="clear: both;"></div>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	</div>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/videofoot.jsp"></jsp:include>
</body>
<script type="text/javascript">
// 开始做题后右侧时间div固定，这个在真题开始做题和章节联系的开始做题的top值是不一样的，所以要写两个
$(window).scroll(function(){
    var height = $(window).scrollTop();
    var left1=($(window).width()-1200)/2+760;
    if (height >= 480) {
        $(".exam-side").css({"position":"fixed","z-index":"800","top":"0px","left":left1});
    } else {
        $(".exam-side").css({"position":"absolute","z-index":"800","top":"200px","left":"760px"});
    }       
});
$(window).resize(function(){
    var height = $(window).scrollTop();
    var left1=($(window).width()-1200)/2+760;
    if (height >= 480) {
        $(".exam-side").css({"position":"fixed","z-index":"800","top":"0px","left":left1});
    } else {
        $(".exam-side").css({"position":"absolute","z-index":"800","top":"200px","left":"760px"});
    }       
}); 
</script>
</html>