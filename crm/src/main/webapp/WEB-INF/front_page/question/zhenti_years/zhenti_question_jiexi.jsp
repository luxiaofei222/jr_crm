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
<script type="text/javascript"
	src="/js/question/question-common-jiexi.js"></script>
<script type="text/javascript" src="/js/question/front/frontchapter.js"></script>
<title>历年真题—真题解析</title>
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
			<li onclick="get_zhenti_year(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-shitiguanli"></i>历年真题</li>
			<li onclick="get_moni_kaoshi(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-kaoshi"></i>模拟考试</li>
			<li class="on"
				onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-zhihebi"></i>练习记录</li>
			<li onclick="get_nengli_main(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-baogaopinggu"></i>能力评估报告</li>
		</ul>
	</div>
	<div class="que_content" style="border: none; position: relative;">
		<div class="page-content">
			<div class="exam-header">
				<c:if test="${zhentiYears.zhentitype=='历年真题' }">
					<h1 class="title-page">${zhentiYears.zhenti_year }年<c:if
							test="${not empty zhentiYears.zhenti_yue }">（${zhentiYears.zhenti_yue }月）</c:if>
						${courseMenu.course_name }《${questionCourse.question_course_name }》真题及答案解析
					</h1>
				</c:if>
				<c:if test="${zhentiYears.zhentitype=='历模拟考试' }">
					<h1 class="title-page">${zhentiYears.zhenti_year }年<c:if
							test="${not empty zhentiYears.zhenti_yue }">（${zhentiYears.zhenti_yue }月）</c:if>
						${courseMenu.course_name }《${questionCourse.question_course_name }》${zhentiYears.zhenti_name }
					</h1>
				</c:if>
				<div class="exam-score">
					<span>${fenzhi }</span>分
				</div>
				<div class="line line-dashed2"></div>
				<ul class="exam-info">
					<c:if test="${zhentiYears.zhentitype=='历年真题' }">
						<li>◇ ${zhentiYears.zhenti_year }年${courseMenu.course_name }${questionCourse.question_course_name }真题</li>
					</c:if>
					<c:if test="${zhentiYears.zhentitype=='历模拟考试' }">
						<li>◇ ${zhentiYears.zhenti_year }年${courseMenu.course_name }${questionCourse.question_course_name }${zhentiYears.zhenti_name }</li>
					</c:if>
				</ul>
				<p class="exam-hint">若本套试卷含有主观题，针对主观题请自行参考解析评分</p>
				<div class="exam-header-block">
					<ul class="list-nav js-exam-nav">
						<c:forEach items="${zhentiTypeIntroduces }" var="intro"
							varStatus="vs">
							<c:if test="${vs.index==0 }">
								<li class="list-item active"
									onclick="change_question_type(${vs.index+1 },this)">
							</c:if>
							<c:if test="${vs.index!=0 }">
								<li class="list-item"
									onclick="change_question_type(${vs.index+1 },this)">
							</c:if>
							<span>${intro.question_type }</span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="exam-main">
				<c:forEach items="${zhentiTypeIntroduces }" var="intro_question"
					varStatus="vs">
					<div class="exam-well well${vs.index+1 }">
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
						<c:if test="${vs.index==4 }">
      				五
      				 </c:if>
						、${intro_question.question_type }(${intro_question.introduce_content })
					</div>
					<c:forEach items="${intro_question.zhentiQuestions }"
						var="question" varStatus="vs_ques">
						<c:if test="${question.question_type!='技能选择题' && question.question_type!='简答题'&& question.question_type!='案例简答题' }">
							<c:if test="${question.is_right=='正确' }">
								<div class="collect_nei collect_correct">
									<span class="collect_icon"></span>
							</c:if>
							<c:if test="${question.is_right=='错误' }">
								<div class="collect_nei collect_wrong">
									<span class="collect_icon"></span>
							</c:if>
						</c:if>
						<c:if test="${question.question_type=='技能选择题' || question.question_type=='简答题' || question.question_type=='案例简答题' }">
							<div class="collect_nei">
						</c:if>
						<div class="collect_title" id="xuhao_tiaozhuan${question.xuhao  }">
							<span class="num">${question.xuhao  }</span> <span class='line'></span>
							<em class='type'>[${question.question_type }]</em>
							<p>${question.question_name }</p>
						</div>
						<c:if test="${question.question_type=='多选题' }">
							<c:if test="${empty question.user_answer }">
								<ul class="multi_choices" pid="off" hid='${question.xuhao  }'
									qid="${question.zhenti_question_id }"
									id="biliduo${question.zhenti_question_id }">
									<c:forEach items="${question.zhentiQuestionOptions }"
										var="option">
										<c:if test="${option.is_dati==0 }">
											<li><span class="l">${option.option_number }</span> <input
												type="checkbox" name="${question.zhenti_question_id }"
												value='${option.option_number }' class="multiclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
										<c:if test="${option.is_dati==1 }">
											<li><span class="l collect_select">${option.option_number }</span>
												<input type="checkbox" checked="checked"
												name="${question.zhenti_question_id }"
												value='${option.option_number }' class="multiclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
									</c:forEach>
								</ul>
							</c:if>
							<c:if test="${not empty question.user_answer }">
								<ul class="multi_choices" pid="on" hid='${question.xuhao  }'
									qid="${question.zhenti_question_id }"
									id="biliduo${question.zhenti_question_id }">
									<c:forEach items="${question.zhentiQuestionOptions }"
										var="option">
										<c:if test="${option.is_dati==0 }">
											<li><span class="l">${option.option_number }</span> <input
												type="checkbox" name="${question.zhenti_question_id }"
												value='${option.option_number }' class="multiclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
										<c:if test="${option.is_dati==1 }">
											<li><span class="l collect_select_wrong">${option.option_number }</span>
												<input type="checkbox" checked="checked"
												name="${question.zhenti_question_id }"
												value='${option.option_number }' class="multiclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
									</c:forEach>
								</ul>
							</c:if>
						</c:if>
						<c:if test="${question.question_type=='单选题' }">
							<c:if test="${not empty question.user_answer }">
								<ul class="single_choices" pid="on" hid='${question.xuhao  }'
									qid="${question.zhenti_question_id }"
									id="bilidan${question.zhenti_question_id }">
									<c:forEach items="${question.zhentiQuestionOptions }"
										var="option">
										<c:if test="${question.user_answer==option.option_number }">
											<li><span class="l collect_select_wrong">${option.option_number }</span>
												<input type="radio" checked="checked"
												name="${question.zhenti_question_id }"
												value='${option.option_number }' class="singleclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
										<c:if test="${question.user_answer!=option.option_number }">
											<li><span class="l">${option.option_number }</span> <input
												type="radio" name="${question.zhenti_question_id }"
												value='${option.option_number }' class="singleclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
									</c:forEach>
								</ul>
							</c:if>
							<c:if test="${empty question.user_answer }">
								<ul class="single_choices" pid="off" hid='${question.xuhao  }'
									qid="${question.zhenti_question_id }"
									id="bilidan${question.zhenti_question_id }">
									<c:forEach items="${question.zhentiQuestionOptions }"
										var="option">
										<c:if test="${question.user_answer==option.option_number }">
											<li><span class="l collect_select">${option.option_number }</span>
												<input type="radio" checked="checked"
												name="${question.zhenti_question_id }"
												value='${option.option_number }' class="singleclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
										<c:if test="${question.user_answer!=option.option_number }">
											<li><span class="l">${option.option_number }</span> <input
												type="radio" name="${question.zhenti_question_id }"
												value='${option.option_number }' class="singleclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
									</c:forEach>
								</ul>
							</c:if>
						</c:if>
						<c:if test="${question.question_type=='职业道德'}">
							<c:if test="${not empty question.user_answer }">
								<ul class="single_choices" pid="on" hid='${question.xuhao  }'
									qid="${question.zhenti_question_id }"
									id="bilidan${question.zhenti_question_id }">
									<c:forEach items="${question.zhentiQuestionOptions }"
										var="option">
										<c:if test="${question.user_answer==option.option_number }">
											<li><span class="l collect_select_wrong">${option.option_number }</span>
												<input type="radio" checked="checked"
												name="${question.zhenti_question_id }"
												value='${option.option_number }' class="singleclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
										<c:if test="${question.user_answer!=option.option_number }">
											<li><span class="l">${option.option_number }</span> <input
												type="radio" name="${question.zhenti_question_id }"
												value='${option.option_number }' class="singleclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
									</c:forEach>
								</ul>
							</c:if>
							<c:if test="${empty question.user_answer }">
								<ul class="single_choices" pid="off" hid='${question.xuhao  }'
									qid="${question.zhenti_question_id }"
									id="bilidan${question.zhenti_question_id }">
									<c:forEach items="${question.zhentiQuestionOptions }"
										var="option">
										<c:if test="${question.user_answer==option.option_number }">
											<li><span class="l collect_select">${option.option_number }</span>
												<input type="radio" checked="checked"
												name="${question.zhenti_question_id }"
												value='${option.option_number }' class="singleclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
										<c:if test="${question.user_answer!=option.option_number }">
											<li><span class="l">${option.option_number }</span> <input
												type="radio" name="${question.zhenti_question_id }"
												value='${option.option_number }' class="singleclass">
												<div class='r'>${option.option_content }</div></li>
										</c:if>
									</c:forEach>
								</ul>
							</c:if>
						</c:if>
						<%-- <c:if test="${question.question_type=='案例题' || question.question_type=='简答题'}">
							<textarea id="anliti_answer" pid="off"
								qid="${question.zhenti_question_id }" hid="${question.xuhao  }"
								class="anlikuang">${question.user_answer }</textarea>
							<p style="font-size: 14px; padding-bottom: 15px;">注：主观题请学员自行打分</p>
						</c:if> --%>

						<c:if test="${question.question_type=='技能选择题'}">
							<c:forEach items="${question.chapterQuestions }" var="chapter"
								varStatus="vs">
								<c:if test="${chapter.chapterRecord.is_right=='正确' }">
									<div class="collect_nei collect_correct">
										<span class="collect_icon"></span>
								</c:if>
								<c:if test="${chapter.chapterRecord.is_right!='正确' }">
									<div class="collect_nei collect_wrong">
										<span class="collect_icon"></span>
								</c:if>
								<div class="collect_title">
									<span class="num">(${vs.index+1 })</span> <span class='line'></span>
									<em class='type'>[${chapter.question_type }]</em>
									<p>${chapter.question_name }</p>
								</div>
								<c:if test="${chapter.question_type =='单选题'}">
									<ul class="single_choices">
										<c:forEach items="${chapter.chapterQuerstionOptions }"
											var="option">
											<c:if
												test="${chapter.chapterRecord.user_answer==option.option_number }">
												<li><span class="l collect_select">${option.option_number }</span>
													<input type="radio" checked="checked"
													value='${option.option_number }' class="singleclass">
													<div class='r'>${option.option_content }</div></li>
											</c:if>
											<c:if
												test="${chapter.chapterRecord.user_answer!=option.option_number }">
												<li><span class="l">${option.option_number }</span> <input
													type="radio" value='${option.option_number }'
													class="singleclass">
													<div class='r'>${option.option_content }</div></li>
											</c:if>
										</c:forEach>
									</ul>
								</c:if>
								<c:if test="${chapter.question_type =='多选题'}">
									<ul class="multi_choices">
										<c:forEach items="${chapter.chapterQuerstionOptions}"
											var="option">
											<c:if test="${option.duoxuan_type_jiexi==0 }">
												<li><span class="l">${option.option_number }</span> <input
													type="checkbox" value='${option.option_number }'
													class="multiclass">
													<div class='r'>${option.option_content }</div></li>
											</c:if>
											<c:if test="${option.duoxuan_type_jiexi==1 }">
												<li><span class="l collect_select">${option.option_number }</span>
													<input type="checkbox" checked="checked"
													value='${option.option_number }' class="multiclass">
													<div class='r'>${option.option_content }</div></li>
											</c:if>
										</c:forEach>
									</ul>
								</c:if>
								<div class="jiucuo_answer">
									<!-- <span class="btn-report-error"> <i class="icon-star3"></i>
										<em>本题纠错</em>
									</span> -->
									<div class="answer_inline" style="display: block;">
										<span class="as-title">参考答案：</span>
										<c:if test="${not empty chapter.answer }">
											<div class="as-cont hl-green">${chapter.answer }</div>
										</c:if>
										<c:if test="${empty chapter.answer }">
											<div class="as-cont hl-green">暂无</div>
										</c:if>
									</div>
									<div class="answer_inline" style="display: block;">
										<span class="as-title">我的答案：</span>
										<c:if test="${not empty chapter.chapterRecord.user_answer }">
											<div class="as-cont hl-red">${chapter.chapterRecord.user_answer }</div>
										</c:if>
										<c:if test="${empty chapter.chapterRecord.user_answer }">
											<div class="as-cont hl-red">未作答</div>
										</c:if>
									</div>
								</div>
								<div class='fold-bd' style="display: block;">
									<ul class="list-analyze">
										<li><span class='l'>试题难度：</span>
											<div class="r">
												<i class="icon-pen pen-${chapter.difficulty }"> <span></span>
												</i>
											</div></li>
										<li><span class="l"> 参考解析： </span>
											<div class="r">${chapter.analysis }</div></li>
									</ul>
								</div>
			</div>
			</c:forEach>
			</c:if>
			<c:if test="${question.question_type=='案例简答题' }">
				<c:forEach items="${question.chapterQuestions }" var="chapter"
					varStatus="vs">
					<c:if test="${chapter.chapterRecord.is_right=='正确' }">
						<div class="collect_nei collect_correct">
							<span class="collect_icon"></span>
					</c:if>
					<c:if test="${chapter.chapterRecord.is_right!='正确' }">
						<div class="collect_nei collect_wrong">
							<span class="collect_icon"></span>
					</c:if>
					<div class="collect_title">
						<span class="num">(${vs.index+1 })</span> <span class='line'></span>
						<em class='type'>[${chapter.question_type }]</em>
						<p>${chapter.question_name }</p>
					</div>
					<!-- 答案 -->
					<div class="jiucuo_answer">
						<!-- <span class="btn-report-error"> <i class="icon-star3"></i>
										<em>本题纠错</em>
									</span> -->
						<div class="answer_inline" style="display: block;">
							<span class="as-title">参考答案：</span>
							<c:if test="${not empty chapter.answer }">
								<div class="as-cont hl-green">${chapter.answer }</div>
							</c:if>
							<c:if test="${empty chapter.answer }">
								<div class="as-cont hl-green">暂无</div>
							</c:if>
						</div>
						<div class="answer_inline" style="display: block;">
							<span class="as-title">我的答案：</span>
							<c:if test="${not empty chapter.chapterRecord.user_answer }">
								<div class="as-cont hl-red">${chapter.chapterRecord.user_answer }</div>
							</c:if>
							<c:if test="${empty chapter.chapterRecord.user_answer }">
								<div class="as-cont hl-red">未作答</div>
							</c:if>
						</div>
					</div>
					<div class='fold-bd' style="display: block;">
						<ul class="list-analyze">
							<li><span class='l'>试题难度：</span>
								<div class="r">
									<i class="icon-pen pen-${chapter.difficulty }"> <span></span>
									</i>
								</div></li>
							<li><span class="l"> 参考解析： </span>
								<div class="r">${chapter.analysis }</div></li>
						</ul>
					</div>
		</div>
		</c:forEach>
		</c:if>
		<c:if
			test="${question.question_type!='技能选择题'&& question.question_type!='案例简答题'}">
			<div class="jiucuo_answer">
				<!-- <span class="btn-report-error"> <i class="icon-star3"></i>
										<em>本题纠错</em>
									</span> -->
				<div class="answer_inline" style="display: block;">
					<span class="as-title">参考答案：</span>
					<c:if test="${not empty question.answer }">
						<div class="as-cont hl-green">${question.answer }</div>
					</c:if>
					<c:if test="${empty question.answer }">
						<div class="as-cont hl-green">暂无</div>
					</c:if>
				</div>
				<div class="answer_inline" style="display: block;">
					<span class="as-title">我的答案：</span>
					<c:if test="${not empty question.user_answer }">
						<div class="as-cont hl-red">${question.user_answer }</div>
					</c:if>
					<c:if test="${empty question.user_answer }">
						<div class="as-cont hl-red">未作答</div>
					</c:if>
				</div>
			</div>
			<div class='fold-bd' style="display: block;">
				<ul class="list-analyze">
					<li><span class='l'>试题难度：</span>
						<div class="r">
							<i class="icon-pen pen-${question.difficulty }"> <span></span>
							</i>
						</div></li>
					<li><span class="l">
							统&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计： </span>
						<div class='r'>
							该题您答过 <b class="hl-orange">${question.zuoguo_number }</b>次，正确率为 <b
								class="hl-green"> <c:if test="${question.zuoguo_number!=0 }">
									<fmt:formatNumber type="number"
										value="${question.zuodui_number /question.zuoguo_number*100 }"
										pattern="0.00" maxFractionDigits="2" />%
								</c:if> <c:if test="${question.zuoguo_number==0 }">
								0.00%
								</c:if>
							</b>
						</div></li>
					<li><span class="l"> 参考解析： </span>
						<div class="r">${question.analysis }</div></li>
				</ul>
			</div>
		</c:if>
	</div>
	</c:forEach>
	</c:forEach>
	</div>
	<div class="exam-side">
		<div class="mod mod--border modOperate">
			<div class="mod-hd">
				<p class="time">
					<i class="icon icon-clock2"></i>已用时 <b class="hl-orange js-timer">${record.zhuanhuan_time }</b>
				</p>
			</div>
			<div class="mod-bd">
				<a
					href="/front_zhenti/get_zhenti_fenxi.html?question_course_id=${questionCourse.question_course_id}&zhenti_id=${zhentiYears.zhenti_id }&zhenti_record_id=${record.zhenti_record_id }">试卷分析</a>
				<a href="javascript:void(0);"
					onclick="get_zhenti_year(${questionCourse.question_course_id})">返回目录</a>
				<a
					href="/front_zhenti/get_zhenti_question_now.html?question_course_id=${questionCourse.question_course_id}&zhenti_id=${zhentiYears.zhenti_id }">再做一次</a>
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
					<c:forEach items="${zhentiTypeIntroduces }" var="intro">
						<li class="list-item_num"><strong class="list-tit">${intro.question_type }
								<span>(${intro.introduce_content })</span>
						</strong>
							<div class="numbers js-card-number">
								<c:forEach items="${intro.zhentiQuestions }"
									var="number_question">
									<span
										onclick="jump_question_number(${number_question.xuhao },this)"
										name="cour_color"> <a class="wrong"
										id="hui${number_question.xuhao }" href="javascript:void(0)">${number_question.xuhao }</a>
									</span>
								</c:forEach>
							</div></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div style="clear: both;"></div>
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