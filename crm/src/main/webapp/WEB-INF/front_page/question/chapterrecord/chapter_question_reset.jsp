<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript" src="/js/school/front/index/jquery-v1.10.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/question/question-common.js"></script>
<script type="text/javascript" src="/js/question/front/frontchapter.js"></script>
<title>章节练习做题中</title>
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
			<li class="on"
				onclick="get_chapter_list(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-shu"></i>章节练习</li>
			<li onclick="get_zhenti_year(${questionCourse.question_course_id})"><i class="icon iconfont icon-shitiguanli"></i>历年真题</li>
			<li><i class="icon iconfont icon-kaoshi"></i>模拟考试</li>
			<li onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i class="icon iconfont icon-zhihebi"></i>练习记录</li>
			<li onclick="get_nengli_main(${questionCourse.question_course_id})"><i class="icon iconfont icon-baogaopinggu"></i>能力评估报告</li>
		</ul>
	</div>
	<div class="que_content" style="border: none;">
		<div class="page-content">
			<div class="exam-header">
				<h1 class="title-page">${questionCourse.question_course_name }《
					<c:if test="${chapterExercises.chapter_level==1 }">     
        第${chapterExercises.chapter_number_str}章
   </c:if>
					<c:if test="${chapterExercises.chapter_level==2 }">     
        第${chapterExercises.chapter_number_str}节
   </c:if>
					${chapterExercises.chapter_name}》
				</h1>
				<div class="exam-header-block progress">
					<span class="progress-num">共${question_number }道题</span>
					<div class="progress-bar js-progress-bar">
						<span id="jindu"></span><i>已完成<b id="jindu_number" style="font-weight:normal;">0</b>/${question_number }</i>
					</div>
				</div>
			</div>
			<div class="exam-main">
				<c:forEach items="${chapterRecords }" var="chapter" varStatus="vs">
					<input type="hidden" name="zhang_id_str" value="${chapter.chapterQuestion.zhang_id }" />
					<input type="hidden" name="jie_id_str" value="${chapter.chapterQuestion.jie_id }" />
					<input type="hidden" name="question_id_str" value="${chapter.chapterQuestion.question_id }" />
					<div class="collect_nei" id="item${vs.index+1 }">
						<div class="collect_title">
							<span class="num">${vs.index+1 }</span> <span class='line'></span>
							<em class='type'>[${chapter.chapterQuestion.question_type }]</em>
							<p>${chapter.chapterQuestion.question_name }</p>
						</div>
						<c:if test="${chapter.chapterQuestion.question_type=='多选题' }">
							<ul class="multi_choices" pid="off" hid='${vs.index+1 }' qid="${chapter.chapterQuestion.chapter_question_id }"  id="biliduo${chapter.chapterQuestion.chapter_question_id }">
								<c:forEach items="${chapter.chapterQuestion.chapterQuerstionOptions }"
									var="option">
									<li><span class="l">${option.option_number }</span>
									 <input
										type="checkbox" name="${chapter.chapterQuestion.chapter_question_id }"
										value='${option.option_number }'  onclick="xuan_zhong_duoxuan(this,${chapter.chapterQuestion.chapter_question_id },${question_number })"
										class="multiclass">
										<div class='r'>${option.option_content }</div></li>
								</c:forEach>
							</ul>
						</c:if>
						<c:if test="${chapter.chapterQuestion.question_type=='单选题' }">
							<ul class="single_choices" pid="off" hid='${vs.index+1 }' qid="${chapter.chapterQuestion.chapter_question_id }" id="bilidan${chapter.chapterQuestion.chapter_question_id }">
								<c:forEach items="${chapter.chapterQuestion.chapterQuerstionOptions }"
									var="option">
									<li><span class="l">${option.option_number }</span> 
									<input 
										type="radio" onclick="xuan_zhong(this,${chapter.chapterQuestion.chapter_question_id },${question_number })" name="${chapter.chapterQuestion.chapter_question_id }"
										value='${option.option_number }'
										class="singleclass">
										<div class='r'>${option.option_content }</div></li>
								</c:forEach>
							</ul>
						</c:if>
					</div>
				</c:forEach>
			</div>
			<div class="exam-side">
				<div class="mod mod--border modOperate">
					<div class="mod-hd">
						<p class="time">
							<i class="icon icon-clock2"></i>已用时 <b class="hl-orange js-timer">00:00:00</b>
						</p>
					</div>
					<div class="mod-bd">
						<a href="javascript:void(0);"
							onclick="get_chapter_list(${questionCourse.question_course_id})">返回目录</a>
						<a href="javascript:void(0);" onclick="pause(this)" id="jishiqi" state="on">暂停休息</a>
					</div>
					<div class="mod-ft">
						<label class="checkbox js-autoNext"> <input id="zidongxiayiti"
							type="checkbox" name="taskType" value="1" checked="checked">
							<span class="checktitle">自动下一题（客观题有效）</span>
						</label> <label class="checkbox js-autoLocate"> <input id="zidongshangci"
							type="checkbox" onclick="to_first_null()" name="taskType" value="2"> <span
							class="checktitle">自动定位 到上次做题位置</span>
						</label>
					</div>
				</div>
				<div class="mod mod--border modAnswerCard">
					<div class="mod-hd">
						<h3 class="mod-hd-tit">答题卡</h3>
						<em class="count js-count"><span class="js-count-finished">0</span>/<span
							class="js-count-total">${question_number }</span></em>
					</div>
					<div class="mod-bd">
						<ul class="list--number2 js-answer-card">
							<li class="list-item"><strong class="list-tit">题号</strong>
								<div class="numbers js-card-number">
									<c:forEach begin="1" end="${question_number }" var="num">
										<span> <a id="hui${num }" href="#item1">${num }</a>
										</span>
									</c:forEach>
								</div></li>
						</ul>
						<div style="clear: both;"></div>
						<div class="btn-group">
							<span class="btn">提交答案</span>
						</div>
					</div>
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>
	</div>
	<div class="tjanswer" style="display: none;">
		<div class="l">
			<p class="tips">
				<!-- 未做完的提示语 -->
				<i class="icon-attention"></i> 还有<b id="shengyu_number">${question_number }</b>道试题未作答，是否确定交卷？
				<!-- 全部做完的提示语 -->
				<!-- <i class="icon-ok"></i>
      您已完成全部试题，是否确定交卷？ -->
			</p>
			<div class='actions'>
				<a href="javascript:void(0);" onclick="close_layer()" class='btn-close'>继续做题</a> <button onclick="save_user_question(this,${questionCourse.question_course_id},${chapterExercises.chapter_exercises_id })"  class='btn-submit'>确定交卷</button>
			</div>
		</div>
		<div class="r">
			<img src="/images/question/chapter/ewm.jpg" class="qr-code">
			<p>关注京人 关注更多</p>
		</div>
	</div>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/videofoot.jsp"></jsp:include>
</body>
<script>
//保存用户章节练习
function save_user_question(obj,question_course_id,chapter_exercises_id){
	$(obj).attr('disabled',"true");
	var str = "";
	var str_duo = "";
	var str_zhang = "";
	var str_jie = "";
	var str_question = "";
	$('input[name="zhang_id_str"]').each(function(i) {//章
		if (0 == i) {
			str_zhang = $(this).val();
		} else {
			str_zhang += ","+$(this).val();
		}
	});
	$('input[name="jie_id_str"]').each(function(i) {//节
		if (0 == i) {
			str_jie = $(this).val();
		} else {
			str_jie += ","+$(this).val();
		}
	});
	$('input[name="question_id_str"]').each(function(i) {//题目
		if (0 == i) {
			str_question = $(this).val();
		} else {
			str_question += ","+$(this).val();
		}
	});
	$('.exam-main ul').each(function(i){
		var qid=$(this).attr("qid");
		if (0 == i) {
			var str_duo_op = "";
			if($(this).attr("class")=="multi_choices"){//多选
				$('input:checkbox[name='+qid+']:checked').each(function(i) {
						if (0 == i) {
							str_duo_op = $(this).val();
						} else {
							str_duo_op += "."+$(this).val();
						}
				});
			}else{
				$('input:radio[name='+qid+']:checked').each(function(i) {
						if (0 == i) {
							str_duo_op = $(this).val();
						}
				});
			}
			if(str_duo_op==""||str_duo_op==null){
				str_duo_op="无";
			}
			str_duo=str_duo_op;
			str = qid;
		} else {
			var str_duo_op = "";
			if($(this).attr("class")=="multi_choices"){//多选
				$('input:checkbox[name='+qid+']:checked').each(function(i) {
						if (0 == i) {
							str_duo_op = $(this).val();
						} else {
							str_duo_op += "."+$(this).val();
						}
				});
				if(str_duo_op==""||str_duo_op==null){
					str_duo_op="无";
				}
				str_duo+=","+str_duo_op
			}else{
				$('input:radio[name='+qid+']:checked').each(function(i) {
						if (0 == i) {
							str_duo_op =$(this).val();
						} 
				});
				if(str_duo_op==""||str_duo_op==null){
					str_duo_op="无";
				}
				str_duo+=","+str_duo_op
			}
			str += ","+qid;
		}
		})
	  $.post("/front_chapter/save_chapter_user.html",{
		'question_course_id':question_course_id,
		'chapter_exercises_id':chapter_exercises_id,
		'answer_length':miao-1,
		'str':str,//题目ID
		'str_duo':str_duo,//选项
		'str_zhang':str_zhang,
		'str_jie':str_jie,
		'str_question':str_question
	},function(data){
		if(data!=null){
			location.href="/front_chapter/get_chapter_jiexi_jilu.html?question_course_id="+question_course_id+"&chapter_exercises_id="+chapter_exercises_id+"&question_order_number="+data;//跳转至练习记录页面
		}else{
			layer.msg("提交失败！")
		}
	}) 
}
</script>
</html>