<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<option value="0">请选择三级分类</option>
<c:forEach items="${review_list }" var="course">
	<option value="${course.review.review_id }">${course.review.review_name }</option>
</c:forEach>