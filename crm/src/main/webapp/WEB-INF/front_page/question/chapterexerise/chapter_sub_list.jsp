<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/question/question-common.js"></script>
<script>
//加载动画
function jiazai(){
	$(".que_content").html('<div class="main_jiazai"><div class="loadEffect"><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span></div></div>');
}
//选题
function get_xuanti_list(chapter_exercises_id,type,name){
	jiazai()
	$.post("/front_chapter/get_xuanti_list.html",{
		'chapter_exercises_id':chapter_exercises_id,
		'type':type,
		'name':name
	},function(data){
		$(".que_content").html(data);
	})
}
function get_xuanti_not(){
	layer.msg("当前章节没有题目可做！")
}
</script>
<div class="zhangjie_lianxi">
	<div class="zhangjie_lianxi_title">
		<div class="panel-hd zhangjie_head">
			<div class="row">
				<div class="col-6">
					<div class="chapterMenu">
						<span class="book_name"><span>${version }教材</span><i
							class="fa fa-chevron-up"></i></span>
						<ul class="book_menu">
							<li class="active"><a
								href="/questionindex/get_selest_question_qiehuan.html?question_course_id=${questionCourse.question_course_id }">${questionCourse.question_course_name }(<span
									class="version">${version }</span>)
							</a></li>
						</ul>
					</div>
				</div>
				<div class="col-3">已做/总数</div>
				<div class="col-1">错题</div>
				<div class="col-2"></div>
			</div>
		</div>
		<ul class="zhangjie_content">
			<c:if test="${empty chapterExercises }">
				<span class="zanwu">暂无章节练习内容</span>
			</c:if>
			<c:forEach items="${chapterExercises }" var="exer">
				<li class="list-item">
					<div class="list-items">
						<div class="nth-1 col-6">
							<strong class="is-sub"> <i
								class="icon iconfont icon-yuanquanjiahao"></i>
								第${exer.chapter_number_str }章${exer.chapter_name }
							</strong>
						</div>
						<div class="nth-2 col-3">
							<div class="progress">
								<div class="progress-bar">
									<span style="width: ${exer.yizuo_chapter/exer.question_number*100}%"></span>
								</div>
								<p class="progress-num">${exer.yizuo_chapter } / ${exer.question_number}</p>
							</div>
						</div>
						<div class="nth-3 col-1">
							<span class="wrong-amount" style="color: red;">${exer.cuowu_chapter }</span>
						</div>
						<div class="nth-4 col-2">
							<c:if test="${exer.question_number==0 }">
								<a href="javascript:void(0)" onclick="get_xuanti_not()">开始做题</a>
							</c:if>
							<c:if test="${exer.question_number!=0 }">
								<a href="javascript:void(0)"
									onclick="get_xuanti_list(${exer.chapter_exercises_id },'zhang','${questionCourse.question_course_name }')">开始做题</a>
							</c:if>
						</div>
					</div>
					<!--  list-item-zhang  end  -->
					<ul class="zhangjie_content_second">
						<c:if test="${empty exer.chapterExercises_jie }">
							<span class="zanwu">暂无小节练习内容</span>
						</c:if>
						<c:forEach items="${exer.chapterExercises_jie }" var="jie">
							<li class="list-item">
								<div class="list-items">
									<div class="nth-1 col-6">
										<strong class="is-sub"> <i
											class="icon iconfont icon-yuanquanjiahao"></i>第${jie.chapter_number_str }节${jie.chapter_name }
										</strong>
									</div>
									<div class="nth-2 col-3">
										<div class="progress">
											<div class="progress-bar">
												<span style="width: ${jie.yizuo_chapter/jie.question_number*100}%"></span>
											</div>
											<p class="progress-num">${jie.yizuo_chapter } / ${jie.question_number}</p>
										</div>
									</div>
									<div class="nth-3 col-1">
										<span class="wrong-amount" style="color: red;">${jie.cuowu_chapter }</span>
									</div>
									<div class="nth-4 col-2">
										<c:if test="${jie.question_number==0 }">
											<a href="javascript:void(0)" onclick="get_xuanti_not()">开始做题</a>
										</c:if>
										<c:if test="${jie.question_number!=0 }">
											<a href="javascript:void(0)"
												onclick="get_xuanti_list(${jie.chapter_exercises_id },'jie','${questionCourse.question_course_name }')">开始做题</a>
										</c:if>
									</div>
								</div>
								<!--  list-items  end  -->
								<ul class="zhangjie_content_third">
									<c:if test="${empty jie.chapterExercises_question }">
										<span class="zanwu">暂无课目练习内容</span>
									</c:if>
									<c:forEach items="${jie.chapterExercises_question }"
										var="question">
										<li class="list-item">
											<div class="list-items">
												<div class="nth-5 col-6">
													<strong class="is-sub"> <i
														class="icon iconfont icon-wu"></i>${question.chapter_name }</strong>
												</div>
												<div class="nth-2 col-3">
													<div class="progress">
														<div class="progress-bar">
															<span style="width: ${question.yizuo_chapter/question.question_number*100}%"></span>
														</div>
														<p class="progress-num">${question.yizuo_chapter } / ${question.question_number}</p>
													</div>
												</div>
												<div class="nth-3 col-1">
													<span class="wrong-amount" style="color: red;">${question.cuowu_chapter }</span>
												</div>
												<div class="nth-4 col-2">
													<c:if test="${question.question_number!=0 }">
														<a href="javascript:void(0)"
															onclick="get_xuanti_list(${question.chapter_exercises_id },'question','${questionCourse.question_course_name }')">开始做题</a>
													</c:if>
													<c:if test="${question.question_number==0 }">
														<a href="javascript:void(0)" onclick="get_xuanti_not()">开始做题</a>
													</c:if>
												</div>
											</div>
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>