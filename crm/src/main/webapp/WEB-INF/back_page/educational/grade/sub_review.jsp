<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<option value="">请选择三级分类</option>
<c:forEach items="${reviews }" var="review">
	<option value="${review.review_id }">${review.review_name }</option>
</c:forEach>