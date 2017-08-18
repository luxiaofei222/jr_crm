<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${course_id==20 }">
<option value="0">请选择申报学校</option>
<c:forEach items="${quchongconditions }" var="con">
<option value="${con.entrycondition_id }">${con.xuexiao }</option>
</c:forEach>
</c:if>
<c:if test="${course_id==19 }">
	<option value="0">请选择报考层次</option>
<c:forEach items="${quchongconditions }" var="con">
<option value="${con.entrycondition_id }">${con.baokao_cengci }</option>
</c:forEach>
</c:if>
<c:if test="${course_id!=20&&course_id!=19 }">
<option value="0">请选择申报级别</option>
<c:forEach items="${quchongconditions }" var="con">
<option value="${con.entrycondition_id }">${con.dictionary.dictionary_name }</option>
</c:forEach>
</c:if>