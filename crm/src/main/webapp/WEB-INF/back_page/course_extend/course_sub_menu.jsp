<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${not empty coursesubMenus }">
<c:forEach items="${coursesubMenus }" var="sub" varStatus="subvs">
<tr class="success" id="success${id}">
<td><label class="label label-success btn-primary"
	for="minimal-checkbox-1">${id}-${subvs.index+1 }</label></td>
<td>${sub.course_name }</td>
<td><fmt:formatDate type="both" value="${sub.course_time }" /></td>
<td>
<button type="button" onclick="to_add_phone_extend(${sub.course_id})"class="btn btn-info btn-xs">添加手机封面</button>
<button type="button" onclick="to_add_extend(${sub.course_id})"class="btn btn-info btn-xs">添加推广</button>
<button type="button" onclick="to_update_meta(${sub.course_id})"class="btn btn-success btn-xs">修改META</button>
</td>
<td></td>
</tr>
</c:forEach>
</c:if>