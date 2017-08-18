<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<option value="">请选择</option>
<c:if test="${empty employees }">
<option value="">该部门没有用户</option>
</c:if>
<c:forEach items="${employees }" var="em">
	<option value="${em.employee_id }">${em.employee_name }</option>
</c:forEach>