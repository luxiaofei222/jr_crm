<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${not empty questionCourses }">
<c:forEach items="${questionCourses }" var="sub" varStatus="subvs">
<tr class="info" id="success${id}">
<td><label class="label label-success btn-primary"
	for="minimal-checkbox-1">${id}-${subvs.index+1 }</label></td>
<td>${sub.question_course_name }</td>
<td>--</td>
<td>${sub.question_chapter_number }</td>
<td><fmt:formatDate type="both" value="${sub.question_course_time }" /></td>
<td>
	<button type="button"
			onclick="to_add_chapter_question(${sub.question_course_id})"
			class="btn btn-success btn-xs">编辑章节练习</button>
	</td>
<td></td>
</tr>
</c:forEach>
</c:if>