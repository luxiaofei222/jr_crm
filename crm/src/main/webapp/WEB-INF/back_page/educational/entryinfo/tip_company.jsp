<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach items="${companies }" begin="1" end="10" var="company" varStatus="vs">
	<div id="${vs.index+1}"  ondblclick="hide_change()" onmouseover="change_hint(${vs.index+1})" onmouseout="change_hint1(${vs.index+1})" >${company.company_name }</div>
</c:forEach>