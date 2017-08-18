<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<option value="0">请选择计划</option>
<c:forEach items="${entryPlans }" var="entryplan">
	<option value="${entryplan.entryplan_id }">${entryplan.entryplan_content }</option>
</c:forEach>