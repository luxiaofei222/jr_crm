<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<option value="0">请选择</option>
<c:if test="${not empty universities }">
<c:forEach items="${universities }" var="wangjiao">
	<option value="${wangjiao.university_id }">${wangjiao.university_zhuanye }</option>
</c:forEach>
</c:if>
<c:if test="${not empty chengkaoScs }">
<c:forEach items="${chengkaoScs }" var="chengkao">
	<option value="${chengkao.chengkao_id }">${chengkao.chengkao_name }</option>
</c:forEach>
</c:if>