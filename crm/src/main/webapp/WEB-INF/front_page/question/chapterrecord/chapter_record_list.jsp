<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
								class="records_line1_two2">总共：${record.lianxi_number }题</span> <span
								class="records_line1_two3">做对：${record.lianxi_right_number }题</span>
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
			onclick="chapter_jump_page(${chapterRecords.pageNumber-1},${questionCourse.question_course_id})">上一页</a>
	</c:if>
	<c:forEach items="${chapterRecords.navigatePageNumbers }" var="page">
		<c:choose>
			<c:when test="${chapterRecords.pageNumber==page}">
				<a class="active">${page}</a>
			</c:when>
			<c:otherwise>
				<a
					onclick="chapter_jump_page(${page},${questionCourse.question_course_id})">${page}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<a>下一页</a>
	<c:if test="${chapterRecords.hasNextPage==true}">
		<a
			onclick="chapter_jump_page(${chapterRecords.pageNumber+1},${questionCourse.question_course_id})">下一页</a>
	</c:if>
	<span class="page-num">${chapterRecords.pageNumber}/${chapterRecords.pages }页</span>
	<span class="input"> <input type="text" id="page_number">
	</span> <a onclick="to_jump_shuru_page(${chapterRecords.pages })">跳转</a>
</div>