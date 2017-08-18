<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<dl>
	<dt>方向:</dt>
	<dd>
		<a href="javascript:void(0)" onclick="yiji_quanbu(${course_parent_id},this)" class="green1">全部</a>
		<c:forEach items="${courseMenus_sub }" var="course_menu">
				<a href="javascript:void(0)" onclick="get_erji_neironglist(${course_menu.course_id },this)">${course_menu.course_name }</a>
		</c:forEach>
	</dd>
</dl>
<div id="erji_shaixuan">
<!-- 二级筛选条件 -->
</div>