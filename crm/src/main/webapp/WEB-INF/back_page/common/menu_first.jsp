<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/tipso.min.css">
<script type="text/javascript" src="/js/school/back/common/tipso.min.js"></script>
<c:forEach items="${menus }" var="menu">
<li class="sidebarli">
	<div class="bar_details hvr-sweep-to-right">
		<div class='sidetubiao'>
			<i class="${menu.menu_icon }"></i>
		</div>
		<span class="bar_wz">${menu.menu_name }</span> <span class="sidetubiao2"> <i
			class="fa fa-angle-down"></i>
		</span>
	</div>
	<ul class="bar_details_two">
	<c:forEach items="${menu.menus_sub }" var="sub">
		<li onclick="get_menu_content('${sub.menu_link }',${sub.menu_limit })" class="hvr-radial-out" data-tipso="${sub.menu_name }"><i
			class="fa fa-list-ul"></i> <span class="erji">${sub.menu_name }</span></li>
		</c:forEach>
	</ul>
</li>
</c:forEach>