<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<option value="0">请选择</option>
<c:if test="${empty chengkaoScs }">
	<option value="0">暂无专业信息</option>
</c:if>
<c:forEach items="${chengkaoScs }" var="chengkao">
	<option value="${chengkao.chengkao_id }">${chengkao.chengkao_name }</option>
</c:forEach>