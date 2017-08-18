<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach items="${reviews }" var="course">
	<option value="${course.review_id }">${course.review_name }</option>
</c:forEach>