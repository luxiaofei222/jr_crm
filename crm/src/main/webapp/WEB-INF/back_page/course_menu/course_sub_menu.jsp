<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${empty coursesubMenus }">
<tr class="zanwu">
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <td class="zanwu">暂无二级课程目录内容</td>
  <!-- <td></td> -->
  <td></td>
  <td></td>
  <td></td>
  <td></td>
</tr>
<!-- <span class="zanwu">还没有添加章节内容</span> -->
</c:if>
<c:if test="${not empty coursesubMenus }">
<c:forEach items="${coursesubMenus }" var="sub" varStatus="subvs">
<tr class="success" id="success${id}">
<td><label class="label label-success btn-primary"
	for="minimal-checkbox-1">${id}-${subvs.index+1 }</label></td>
<td>${sub.course_name }</td>
<td><fmt:formatDate type="both" value="${sub.course_time }" /></td>
<%-- <td><button type="button"
		onclick="upload_ware(${sub.course_id})"
		class="btn btn-info btn-xs">添加</button></td>
<td><button type="button"
		onclick="check_ware(${sub.course_id})"
		class="btn btn-warning btn-xs">添加</button></td> --%>
<td><c:if test="${sub.is_show=='否'  }">
		<button type="button"
			onclick="coursemenu_show('是',${sub.course_id})"
			class="btn btn-info btn-xs">显示</button>
	</c:if> <c:if test="${sub.is_show=='是'  }">
		<button type="button"
			onclick="coursemenu_show('否',${sub.course_id})"
			class="btn btn-default btn-xs">隐藏</button>
	</c:if>
	<button type="button"
			onclick="coursemenu_exam(${sub.course_id})"
			class="btn btn-success btn-xs">考试时间</button>
	</td>
<td></td>
</tr>
</c:forEach>
</c:if>