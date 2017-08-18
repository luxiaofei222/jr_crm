<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/question/index/reset.css">
<link rel="stylesheet" type="text/css"
	href="/css/question/back/question-common.css" />
<link rel="stylesheet" href="/css/iconfont/iconfont.css">
<script type="text/javascript"
	src="/js/question/question-common-back.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script type="text/javascript"
	src="/js/question/chapter/chapter.js"></script>
<button type="button" class="btn btn-primary btn-lg tianjiaz"
	style="margin-bottom: 10px; background: #53C1FE; border-color: #53C1FE;"
	onclick="to_add_zhang(${questionCourses.question_course_id})">
	<i class="fa fa-plus"></i>添加章标题
</button>

<button type="button" class="btn btn-primary btn-lg tianjiaz"
	style="margin-bottom: 10px; background: #53C1FE; border-color: #53C1FE;"
	onclick="to_insert_zhang(${questionCourses.question_course_id})">
	<i class="fa fa-arrow-circle-right"></i>插入章标题
</button>

<button type="button" class="btn btn-primary btn-lg tianjiaz"
	style="margin-bottom: 10px; background: #53C1FE; border-color: #53C1FE;"
	onclick="to_update_version(${questionCourses.question_course_id})">
	<i class="fa fa-edit"></i>设置教材版本
</button>

<button type="button" class="btn btn-primary btn-lg tianjiaz"
	style="margin-bottom: 10px; background: #53C1FE; border-color: #53C1FE;"
	onclick="to_check_option(${questionCourses.question_course_id})">
	<i class="fa fa-eye"></i>查看所有题目
</button>
<div class="que_content">
	<div class="zhangjie_lianxi">
		<div class="zhangjie_lianxi_title">
			<div class="panel-hd zhangjie_head">
				<div class="row">
					<div class="col-6">
						<div class="chapterMenu">
							<span class="book_name"><span>${questionCourses.question_course_name }(${version })</span></span>
						</div>
					</div>
					<div class="col-2">题总数</div>
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
									class="icon iconfont icon-yuanquanjiahao"></i>第${exer.chapter_number_str }章
									${exer.chapter_name }
								</strong>
							</div>
							<div class="nth-3 col-2">
								<span class="wrong-amount">${exer.question_number }</span>
							</div>
							<div class="nth-4 col-4">
								<a href="javascript:void(0)" onclick="to_add_jie(${exer.chapter_exercises_id},${exer.question_course_id })" >添加节</a>
								<a href="javascript:void(0)" onclick="to_update_name(${exer.chapter_exercises_id},${exer.question_course_id })" >修改名称</a>
								<a href="javascript:void(0)" onclick="delete_zhang(${exer.chapter_exercises_id},${exer.question_course_id })" >删除本章</a>
								<a href="javascript:void(0)" onclick="to_check_zhang_option(${exer.chapter_exercises_id})">查看</a>
							</div>
						</div>
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
										<div class="nth-3 col-2">
											<span class="wrong-amount">${jie.question_number }</span>
										</div>
										<div class="nth-4 col-4">
											<a href="javascript:void(0)"onclick="to_add_question(${jie.chapter_exercises_id},${jie.question_course_id })" >添加课目</a>
											<a href="javascript:void(0)" onclick="to_update_name(${jie.chapter_exercises_id},${jie.question_course_id })" >修改名称</a>
											<a href="javascript:void(0)" onclick="delete_jie(${jie.chapter_exercises_id},${jie.question_course_id })" >删除本节</a>
											<a href="javascript:void(0)" onclick="to_check_jie_option(${jie.chapter_exercises_id})">查看</a>
										</div>
									</div>
									<ul class="zhangjie_content_third">
										<c:if test="${empty jie.chapterExercises_question }">
											<span class="zanwu">暂无课目练习内容</span>
										</c:if>
										<c:forEach items="${jie.chapterExercises_question }"
											var="question">
											<li class="list-item">
												<div class="list-items">
													<div class="nth-5 col-5">
														<strong class="is-sub"> <i
															class="icon iconfont icon-wu"></i>
															${question.chapter_name }
														</strong>
													</div>
													<div class="nth-3 col-2">
														<span class="wrong-amount">${question.question_number }</span>
													</div>
													<div class="nth-4 col-4">
														<a href="javascript:void(0)" onclick="to_add_question_option(${question.chapter_exercises_id},${question.question_course_id })">添加题</a>
														<a href="javascript:void(0)" onclick="to_update_name(${question.chapter_exercises_id},${question.question_course_id })" >修改名称</a>
														<a href="javascript:void(0)"onclick="delete_question(${question.chapter_exercises_id},${question.question_course_id })" >删除本课</a>
														<a href="javascript:void(0)" onclick="to_check_question_option(${question.chapter_exercises_id})" >查看</a>
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
</div>