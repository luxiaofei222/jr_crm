<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/question/question-common.js"></script>
<script>
	$(function() {
		//教材版本划过
		$(".width_diff").hover(function() {
			$(".width_diff_info").show();
		}, function() {
			$(".width_diff_info").hide();
		})
		// 错题记录中的tab切换
		$(".records_cuoti_headul li").click(
				function() {
					$(this).addClass("records_cuoti_active").siblings()
							.removeClass("records_cuoti_active");
					var index3 = $(this).index();
					$(".records_cuoti_info").eq(index3).show().siblings(
							".records_cuoti_info").hide();
				})
	})
</script>
<!-- 错题记录 -->
<div class="records_right">
	<div class="records_cuoti">
		<div class='records_cuoti_head'>
			<ul class='records_cuoti_headul'>
				<li class="records_cuoti_active">题型</li>
				<li class="width_diff">${version }教材<i
					class="icon iconfont icon-xiajiantou"></i>
					<div class="width_diff_info">${questionCourse.question_course_name }(${version })</div>
				</li>
			</ul>

			<div class="cuotibiaoshi">错题/已做</div>
		</div>
		<div class="records_cuoti_info" style="display: block;">
			<ul>
				<li><span class="records_cuoti_info1">单选题</span> <span
					class="records_cuoti_info2">${danxuan_cuowu }/${danxuan_yizuo }</span>
					<a class="chakan"
					href="/front_chapter/get_chapter_record_danxuan_detail.html?question_course_id=${questionCourse.question_course_id }">点击查看</a></li>
				<li><span class="records_cuoti_info1">多选题</span> <span
					class="records_cuoti_info2">${duoxuan_cuowu }/${duoxuan_yizuo }</span>
					<a class="chakan"
					href="/front_chapter/get_chapter_record_duoxuan_detail.html?question_course_id=${questionCourse.question_course_id }">点击查看</a></li>
			</ul>
		</div>
		<div class="records_cuoti_info">
			<div class="zhangjie_lianxi">
				<div class="zhangjie_lianxi_title">
					<ul class="zhangjie_content">
						<c:forEach items="${exercises }" var="exer">
							<li class="list-item">
								<div class="list-items1">
									<div class="nth-5 col-9">
										<strong class="is-sub" style="padding-left: 0px;"> <i
											class="icon iconfont icon-yuanquanjiahao"></i>
											第${exer.chapter_number_str }章${exer.chapter_name }
										</strong>
									</div>
									<div class="nth-1 col-1">
										<span class="wrong-amount">${exer.cuowu_chapter }/${exer.yizuo_chapter }</span>
									</div>
									<div class="nth-4 col-2">
										<c:if test="${exer.yizuo_chapter!=0 }">
											<a href="/front_chapter/get_zhangjie_cuoti.html?question_course_id=${questionCourse.question_course_id }&zhang_id=${exer.chapter_exercises_id }">点击查看</a>
										</c:if>
										<c:if test="${exer.yizuo_chapter==0 }">
											<a href="javascript:void(0)" class='no_followbtn'>点击查看</a>
										</c:if>
									</div>
								</div> <!--  list-item-zhang  end  -->
								<ul class="zhangjie_content_second">
									<c:forEach items="${exer.chapterExercises_jie }" var="jie">
										<li class="list-item">
											<div class="list-items1">
												<div class="nth-5 col-9">
													<strong class="is-sub"> <i
														class="icon iconfont icon-yuanquanjiahao"></i>第${jie.chapter_number_str }节${jie.chapter_name }
													</strong>
												</div>
												<div class="nth-1 col-1">
													<span class="wrong-amount">${jie.cuowu_chapter }/${jie.yizuo_chapter }</span>
												</div>
												<div class="nth-4 col-2">
													<c:if test="${jie.yizuo_chapter!=0 }">
														<a href="/front_chapter/get_zhangjie_cuoti.html?question_course_id=${questionCourse.question_course_id }&jie_id=${jie.chapter_exercises_id }">点击查看</a>
													</c:if>
													<c:if test="${jie.yizuo_chapter==0 }">
														<a href="javascript:void(0)" class='no_followbtn'>点击查看</a>
													</c:if>
												</div>
											</div> <!--  list-items  end  -->
											<ul class="zhangjie_content_third">
												<c:forEach items="${jie.chapterExercises_question }"
													var="question">
													<li class="list-item">
														<div class="list-items1">
															<div class="nth-5 col-9">
																<strong class="is-sub1"> <i
																	class="icon iconfont icon-wu"></i>${question.chapter_name }
																</strong>
															</div>
															<div class="nth-1 col-1">
																<span class="wrong-amount">${question.cuowu_chapter }/${question.yizuo_chapter }</span>
															</div>
															<div class="nth-4 col-2">
																<c:if test="${question.yizuo_chapter!=0 }">
																	<a href="/front_chapter/get_zhangjie_cuoti.html?question_course_id=${questionCourse.question_course_id }&question_id=${question.chapter_exercises_id }">点击查看</a>
																</c:if>
																<c:if test="${question.yizuo_chapter==0 }">
																	<a href="javascript:void(0)" class="no_fllowbtn">点击查看</a>
																</c:if>
															</div>
														</div> <!--  list-items  end  -->
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
	</div>
</div>