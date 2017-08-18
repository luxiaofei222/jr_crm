<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教务管理系统</title>
	<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
	<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="/css/jiaowu/index/htindex.css">
	<link rel="stylesheet" href="/css/school/back/common/right_content.css" />
	<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
	<!-- <script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script> -->
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/school/back/common/htindex.js"></script>
</head>
<body>
	<div class='wrapper'>
		<!-- 左侧菜单列表 -->
		<jsp:include page="/WEB-INF/back_page/common/educationalmenu.jsp"></jsp:include>
		<div class="ht_right"  id="conten_list">
			<!-- 右侧内容 -->
			<jsp:include page="/WEB-INF/back_page/edu_welcome.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>