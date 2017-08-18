<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--历年真题练习列表  -->
<div class="records_right_bottom" style="display: block;">
	<ul class="records_right_ul">
		<c:forEach items="${zhentiRecords }" var="record">
			<li class="records_right_li">
				<ul class="records_lineul">
					<li class="records_line1">
						<div class="records_line1_one">
							<span class="records_line1_one1">[历年真题]</span> <span
								title="${record.zhentiYears.zhenti_year }年<c:if test="${not empty record.zhentiYears.zhenti_yue }">（${zhentiYears.zhenti_yue }月）</c:if>${courseMenu.course_name }《${questionCourse.question_course_name }》真题及答案解析"
								class="records_line1_one2">${record.zhentiYears.zhenti_year }年<c:if test="${not empty record.zhentiYears.zhenti_yue }">（${zhentiYears.zhenti_yue }月）</c:if>${courseMenu.course_name }《${questionCourse.question_course_name }》真题及答案解析
							</span>
						</div>
						<c:if test="${record.recourd_state==1 }">
						<div class="records_line1_two">
							<span class="records_line1_two1">用时：${record.zhuanhuan_time }</span>
							<span class="records_line1_two2">得分：${record.fenzhi }分</span>
							<span class="records_line1_two3">考试日期：<fmt:formatDate
														value="${record.recourd_time }" /></span>
						</div>
						</c:if>
						<c:if test="${record.recourd_state==2 }">
						<div class="records_line1_two">
							<span class="records_line1_two1">总题：${record.zhentiYears.zhenti_zongtishu }</span>
							<span class="records_line1_two2">考试时长：${record.zhentiYears.zhenti_kaoshi_time }分钟</span>
							<span class="records_line1_two3">总分：${record.zhentiYears.zhenti_zongfen }分</span>
						</div>
						</c:if>
					</li>
						<c:if test="${record.recourd_state==1 }">
							<li class="records_line2"><a
								href="/front_zhenti/get_zhenti_question_jiexi.html?question_course_id=${questionCourse.question_course_id}&zhenti_id=${record.zhenti_id }&zhenti_record_id=${record.zhenti_record_id}">查看解析</a>
							</li>
						</c:if>
						<c:if test="${record.recourd_state==2 }">
							<li class="records_line2">未完成</li>
						</c:if>
						<c:if test="${record.recourd_state==1 }">
						 <li class="records_line3">
							<a class="finish"
								href="/front_zhenti/get_zhenti_year_introduce.html?question_course_id=${questionCourse.question_course_id}&zhenti_id=${record.zhenti_id }">再做一次</a>
						</li>
						</c:if>
						<c:if test="${record.recourd_state==2 }">
							<li class="records_line3">
							<a class="nofinish"
								href="/front_zhenti/get_zhenti_question_jixu.html?question_course_id=${questionCourse.question_course_id}&zhenti_id=${zhenti.zhenti_id }&zhenti_record_id=${zhenti.zhenti_record_id}">继续答题</a>
							</li>
						</c:if>
				</ul>
			</li>
		</c:forEach>
	</ul>
</div>