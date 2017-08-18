<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<link rel="stylesheet" type="text/css" href="/css/school/back/common/tipso.min.css">
<script type="text/javascript" src="/js/school/back/common/tipso.min.js"></script>
<script type="text/javascript" src="/js/school/back/menu/menu.js"></script>
<div class="ht_left">
	<div class="sidebarone">
		<div class="left_logo">
			<div class="logoimg" >
				<img src="/images/school/back/left/logo111.png">
			</div>
			<div class="logowz" >京人教育</div>
			<div class="left_back">
				<img src="/images/school/back/left/side_left.png" onclick="shouqi()" id="left_backside"> 
			</div>
		</div>
		<div class="sidebar_info">
			<ul class="sidebarul" id="menu_first">
				<!-- 网校菜单一级列表 -->
			</ul>
			<div style="clear: both;"></div>
		</div>
	</div>
	<div class="bar_bottom_wai">
		<div class="bar_bottom">
			<div class="bar_bottom1 hvr-sweep-to-right" onclick="location.href='/center/get_employee_center.jr'">
			<c:if test="${not empty sessionScope.employee_session.employee_pic }">
				<img src="${sessionScope.employee_session.employee_pic }" class="emp_pic" />
			</c:if>
			<c:if test="${empty sessionScope.employee_session.employee_pic }">
				<img src="/images/employee/1.jpg" class="emp_pic" />
			</c:if>
			<span class='bxyc'>${sessionScope.employee_session.employee_name }</span>
			</div>
			<div onclick="logout_jr()" class="bar_bottom2 hvr-sweep-to-right">
				<i class="fa fa-power-off"></i> <span  class='bxyc'>退出</span>
			</div>
		</div>
		<div class="bxyc_info" id="organization">
			<!-- 所属部门 -->
		</div>
	</div>

</div>