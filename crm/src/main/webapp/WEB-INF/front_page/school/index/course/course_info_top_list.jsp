<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:forEach items="${course_sub }" var="cour_menu">
	<li><a target="_blank" href="/front_info/get_course_info.html?courseid=${cour_menu.sub_course_id }">${cour_menu.course_name }</a></li>
</c:forEach>