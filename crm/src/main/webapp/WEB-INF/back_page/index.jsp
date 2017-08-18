<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
<link rel="stylesheet" type="text/css" href="/css/index/dlindex.css">
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/index/system.js"></script>
<title>首页</title>
</head>
<body>
<div class="dl_wrapper">
		<div class="dl_title">
			<div class="dlt1">京人教育集团</div>
			<div class="dlt2">&nbsp;</div>
			<div class="dlt3">
				<div class="yhm" onclick="logout_jr()">
					退出
				</div>
				<div onclick="location.href='/center/get_employee_center.jr'" class="tuichu">${sessionScope.employee_session.employee_name }
				</div>
			</div>
			<div class="dlt4">&nbsp;</div>
		</div>
		<div class="dl_img">
			<img src="/images/index/indexhand.png">
		</div>
		<div class="dl_select">
			<ul>
			<c:forEach items="${menus }" var="menu">
				<c:if test="${menu.menu_name=='人员' &&  menu.menu_id==2 }">
				<li onclick="get_system_main(${menu.menu_id},'${menu.is_permission}')">
					<img src="/images/index/renyuan.png">
				</li>
				</c:if>
				<c:if test="${menu.menu_name=='网校' &&  menu.menu_id==1 }">
				<li onclick="get_system_main(${menu.menu_id},'${menu.is_permission}')">
					<img src="/images/index/wangxiao.png">
				</li>
				</c:if>
				<c:if test="${menu.menu_name=='CRM' &&  menu.menu_id==29 }">
				<li onclick="get_system_main(${menu.menu_id},'${menu.is_permission}')">
					<img src="/images/index/crm.png">
				</li>
				</c:if>
				<c:if test="${menu.menu_name=='财务' &&  menu.menu_id==30 }">
				<li onclick="get_system_main(${menu.menu_id},'${menu.is_permission}')">
					<img src="/images/index/caiwu.png">
				</li>
				</c:if>
				<c:if test="${menu.menu_name=='教务' &&  menu.menu_id==36}">
				<li onclick="get_system_main(${menu.menu_id},'${menu.is_permission}')">
					<img src="/images/index/jiaowu.png">
				</li>
				</c:if>
			<c:if test="${menu.menu_name=='OA' &&  menu.menu_id==31}">
				<li onclick="get_system_main(${menu.menu_id},'${menu.is_permission}')">
					<img src="/images/index/OA.png">
				</li>
				</c:if>
			</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>