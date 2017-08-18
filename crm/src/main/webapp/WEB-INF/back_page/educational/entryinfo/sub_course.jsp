<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<option value="0">请选择二级分类</option>
<c:forEach items="${course_list }" var="course">
	<option value="${course.course_id }">${course.coursename }</option>
</c:forEach>