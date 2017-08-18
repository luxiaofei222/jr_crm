<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<option value="">请选择专业</option>
<c:if test="${empty subuniversities }">
<option value="0">该层次没有专业</option>
</c:if>
<c:if test="${not empty subuniversities }">
<c:forEach items="${subuniversities }" var="univer">
	<option value="${univer.university_id }">${univer.university_zhuanye }</option>
</c:forEach>
</c:if>