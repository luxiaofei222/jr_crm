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
<script type="text/javascript"
	src="/js/school/front/index/jquery-v1.10.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/question/question-common.js"></script>
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
				<li onclick="get_moni_kaoshi(${questionCourse.question_course_id})"><i
					class="icon iconfont icon-kaoshi"></i>模拟考试</li>
			</c:if>
			<c:if test="${zhentiYears.zhentitype=='模拟考试' }">
				<li onclick="get_zhenti_year(${questionCourse.question_course_id})"><i
					class="icon iconfont icon-shitiguanli"></i>历年真题</li>
				<li class="on"
					onclick="get_moni_kaoshi(${questionCourse.question_course_id})"><i
					class="icon iconfont icon-kaoshi"></i>模拟考试</li>
			</c:if>
			<li
				onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-zhihebi"></i>练习记录</li>
			<li onclick="get_nengli_main(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-baogaopinggu"></i>能力评估报告</li>
		</ul>
	</div>
	<div class="que_content" style="border: none;">
		<div class="page-content">
			<div class="exam-header">
				<c:if test="${zhentiYears.zhentitype=='历年真题' }">
					<h1 class="title-page">${zhentiYears.zhenti_year }年
						<c:if test="${not empty zhentiYears.zhenti_yue }">
	（${zhentiYears.zhenti_yue }月）
	</c:if>
						${courseMenu.course_name }《${questionCourse.question_course_name }》真题及答案解析
					</h1>
				</c:if>
				<c:if test="${zhentiYears.zhentitype=='模拟考试' }">
					<h1 class="title-page">${zhentiYears.zhenti_year }年
						<c:if test="${not empty zhentiYears.zhenti_yue }">
	（${zhentiYears.zhenti_yue }月）
	</c:if>
						${courseMenu.course_name }《${questionCourse.question_course_name }》${zhentiYears.zhenti_name }
					</h1>
				</c:if>
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
						<c:if test="${question.question_type=='技能选择题' }">
							<div class="collect_nei" pid="off" id="jineng${question.zhenti_question_id }" hid='${question.xuhao  }' 
								onclick="jineng_xuanze(${question.xuhao  },this,${zhentiYears.zhenti_zongtishu })">
						</c:if>
						<c:if test="${question.question_type!='技能选择题' }">
							<div class="collect_nei">
						</c:if>
						<div class="collect_title" pid="off" id="xuhao_tiaozhuan${question.xuhao  }">
							<span class="num">${question.xuhao  }</span> <span class='line'></span>
							<em class='type'>[${question.question_type }]</em>
							<p>${question.question_name }</p>
						</div>
						<c:if test="${question.question_type=='多选题' }">
							<ul class="multi_choices" pid="off" hid='${question.xuhao  }'
								qid="${question.zhenti_question_id }"
								id="biliduo${question.zhenti_question_id }">
								<c:forEach items="${question.zhentiQuestionOptions }"
									var="option">
									<li><span class="l">${option.option_number }</span> <input
										type="checkbox" name="${question.zhenti_question_id }"
										value='${option.option_number }'
										onclick="xuan_zhong_duoxuan(this,${question.zhenti_question_id },${zhentiYears.zhenti_zongtishu })"
										class="multiclass">
										<div class='r'>${option.option_content }</div></li>
								</c:forEach>
							</ul>
						</c:if>
						<c:if test="${question.question_type=='单选题' }">
							<ul class="single_choices" pid="off" hid='${question.xuhao  }'
								qid="${question.zhenti_question_id }"
								id="bilidan${question.zhenti_question_id }">
								<c:forEach items="${question.zhentiQuestionOptions }"
									var="option">
									<li><span class="l">${option.option_number }</span> <input
										type="radio"
										onclick="xuan_zhong(this,${question.zhenti_question_id },${zhentiYears.zhenti_zongtishu })"
										name="${question.zhenti_question_id }"
										value='${option.option_number }' class="singleclass">
										<div class='r'>${option.option_content }</div></li>
								</c:forEach>
							</ul>
						</c:if>
						<c:if test="${question.question_type=='职业道德' }">
							<ul class="single_choices" pid="off" hid='${question.xuhao  }'
								qid="${question.zhenti_question_id }"
								id="bilidan${question.zhenti_question_id }">
								<c:forEach items="${question.zhentiQuestionOptions }"
									var="option">
									<li><span class="l">${option.option_number }</span> <input
										type="radio"
										onclick="xuan_zhong(this,${question.zhenti_question_id },${zhentiYears.zhenti_zongtishu })"
										name="${question.zhenti_question_id }"
										value='${option.option_number }' class="singleclass">
										<div class='r'>${option.option_content }</div></li>
								</c:forEach>
							</ul>
						</c:if>
						<c:if test="${question.question_type=='技能选择题' }">
							<c:forEach items="${question.chapterQuestions }" var="chapter"
								varStatus="vs">
								<div class="collect_nei" >
									<div class="collect_title">
										<span class="num">(${vs.index+1 })</span> <span class='line'></span>
										<em class='type'>[${chapter.question_type }]</em>
										<p>${chapter.question_name }</p>
									</div>
									<c:if test="${chapter.question_type=='多选题' }">
										<ul class="multi_choices" pid="off" 
											jinengqid="${chapter.chapter_question_id }" qid="${question.zhenti_question_id }"
											id="biliduo${chapter.chapter_question_id }">
											<c:forEach items="${chapter.chapterQuerstionOptions }"
												var="option">
												<li><span class="l">${option.option_number }</span> <input
													onclick="jineng_duoxuan(this,${question.zhenti_question_id },${chapter.chapter_question_id })"
													type="checkbox" name="${chapter.chapter_question_id }"
													value='${option.option_number }' class="multiclass">
													<div class='r'>${option.option_content }</div></li>
											</c:forEach>
										</ul>
									</c:if>
									<c:if test="${chapter.question_type=='单选题' }">
										<ul class="single_choices" pid="off" 
											jinengqid="${chapter.chapter_question_id }" qid="${question.zhenti_question_id }"
											id="bilidan${chapter.chapter_question_id }">
											<c:forEach items="${chapter.chapterQuerstionOptions }"
												var="option">
												<li><span class="l">${option.option_number }</span> <input
													type="radio"
													onclick="jineng_danxuan(this,${question.zhenti_question_id },${chapter.chapter_question_id })"
													name="${chapter.chapter_question_id }" 
													value='${option.option_number }' class="singleclass">
													<div class='r'>${option.option_content }</div></li>
											</c:forEach>
										</ul>
									</c:if>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${question.question_type=='案例简答题' }">
							<c:forEach items="${question.chapterQuestions }" var="chapter"
								varStatus="vs">
								<div class="collect_nei">
									<div class="collect_title" >
										<span class="num">(${vs.index+1 })</span> <span class='line'></span>
										<em class='type'>[${chapter.question_type }]</em>
										<p>${chapter.question_name }</p>
									</div>
								<textarea placeholder="请输入您的答案"  anliid="off" oninput="shuru_anli_daan(${question.xuhao  },this,${zhentiYears.zhenti_zongtishu })"
								jinengqid="${chapter.chapter_question_id }" qid="${question.zhenti_question_id }" hid="${question.xuhao  }"
								class="anlikuang"></textarea>
							<p style="font-size: 14px; padding-bottom: 15px;">注：主观题请学员自行打分</p>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${question.question_type=='案例题' ||question.question_type=='简答题'}">
							<textarea placeholder="请输入您的答案" id="anliti_answer" pid="off"
								qid="${question.zhenti_question_id }" hid="${question.xuhao  }"
								oninput="shuru_daan(${question.xuhao  },this,${zhentiYears.zhenti_zongtishu })"
								class="anlikuang">${question.user_answer }</textarea>
							<p style="font-size: 14px; padding-bottom: 15px;">注：主观题请学员自行打分</p>
						</c:if>
			</div>
			</c:forEach>
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
						onclick="get_zhenti_year(${questionCourse.question_course_id })">返回目录</a>
					<a href="javascript:void(0);" onclick="pause(this)" id="jishiqi"
						state="on">暂停休息</a>
				</div>
				<div class="mod-ft">
					<label class="checkbox js-autoNext"> <input
						id="zidongxiayiti" type="checkbox" name="taskType" value="1"
						checked="checked"> <span class="checktitle">自动下一题（客观题有效）</span>
					</label> <label class="checkbox js-autoLocate"> <input
						id="zidongshangci" type="checkbox" onclick="to_first_null()"
						name="taskType" value="2"> <span class="checktitle">自动定位
							到上次做题位置</span>
					</label>
				</div>
			</div>
			<div class="mod mod--border modAnswerCard">
				<div class="mod-hd">
					<h3 class="mod-hd-tit">答题卡</h3>
					<em class="count js-count"><span class="js-count-finished">0</span>/<span
						class="js-count-total">${zhentiYears.zhenti_zongtishu }</span></em>
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
											name="cour_color"> <a
											id="hui${number_question.xuhao }" href="javascript:void(0)">${number_question.xuhao }</a>
										</span>
									</c:forEach>
								</div></li>
						</c:forEach>
					</ul>
					<div style="clear: both;"></div>
					<div class="btn-group">
						<span class="btn1"
							onclick="baocunjindu_zhenti(this,${zhentiYears.zhenti_zongtishu },${zhentiYears.zhenti_id },${questionCourse.question_course_id})">保存进度，下次继续</span>
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
				<i class="icon-attention"></i> 还有<b id="shengyu_number">${zhentiYears.zhenti_zongtishu }</b>道试题未作答，是否确定交卷？
				<!-- 全部做完的提示语 -->
				<!-- <i class="icon-ok"></i>
      您已完成全部试题，是否确定交卷？ -->
			</p>
			<div class='actions'>
				<button onclick="close_layer()" class='btn-close'>继续做题</button>
				<button
					onclick="save_user_question(this,${zhentiYears.zhenti_zongtishu },${zhentiYears.zhenti_id },${questionCourse.question_course_id})"
					class='btn-submit'>确定交卷</button>
			</div>
		</div>
		<div class="r">
			<img src="/images/question/chapter/ewm.jpg" class="qr-code">
			<p>关注京人 关注更多</p>
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
<script>
//历年真题-提交试卷
function save_user_question(obj,tishu,zhenti_id,question_course_id){
	var shengyu_number=$("#shengyu_number").text();
	if(tishu>shengyu_number){
		$(obj).attr('disabled',"true");
		$(obj).html("提交中");
		var str = "";
		var str_duo = "";
		var jineng_op="";
		var jineng_id="";
		//技能选择题
		$('.exam-main ul').each(function(i){
			var qid=$(this).attr("jinengqid");
			if(typeof(qid) != "undefined"){
			
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
				jineng_op=str_duo_op;
				jineng_id = qid;
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
					jineng_op+=","+str_duo_op
				}else{
					$('input:radio[name='+qid+']:checked').each(function(i) {
							if (0 == i) {
								str_duo_op =$(this).val();
							} 
					});
					if(str_duo_op==""||str_duo_op==null){
						str_duo_op="无";
					}
					jineng_op+=","+str_duo_op
				}
				jineng_id += ","+qid;
			}
		}
			
		})
		//案例简答题
	/* 	$('.anlijiandakuang').each(function(i){
			var qid=$(this).attr("jinengqid");
			var str_duo_op=$(this).val().replace(",", "，");
			if(str_duo_op==""||str_duo_op==null){
				str_duo_op="无";
			}
			jineng_op+=","+str_duo_op
			jineng_id += ","+qid;
		}) */
		//正常选项
		$('.exam-main ul').each(function(i){
			var qid=$(this).attr("qid");
			if(typeof(qid) != "undefined"){
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
		}
			})
		$('.anlikuang').each(function(i){
			var qid=$(this).attr("qid");
			var str_duo_op=$(this).val().replace(",", "，");
			if(str_duo_op==""||str_duo_op==null){
				str_duo_op="无";
			}
			str_duo+=","+str_duo_op
			str += ","+qid;
		})
		  $.post("/front_zhenti/save_zhenti_jindu.html",{
				'question_course_id':question_course_id,
				'zhenti_id':zhenti_id,
				'recourd_state':1,
				'answer_length':miao-1,
				'str':str,//题目ID
				'str_duo':str_duo,//选项
				'jineng_op':jineng_op,
				'jineng_id':jineng_id
			},function(data){
				if(data==1){
					get_zhenti_year(question_course_id);//跳转历年真题目录
				}else if(data==2){
					get_moni_kaoshi(question_course_id);//跳转模拟考试
				}else{
					$(obj).removeAttr('disabled');
					$(obj).html("确定交卷");
					layer.msg("提交失败！")
				}
			}) 
	}else{
		layer.msg("您还没有做题！");
	}
}
</script>
</html>