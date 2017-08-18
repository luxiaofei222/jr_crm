<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>查看低价体系</title>
<style type="text/css">
*{
	margin:0px;
	padding:0px;
	font-family:"微软雅黑";
	color:#444;
	}
body{
	padding:20px;
}
h4{
	margin:0 auto;
	text-align:center;
	margin-top:20px;
}
p{
	margin:0 auto;
	text-align:left;
	text-indent:2em;
	margin-top:10px;
	color:orange;
}
div{
	text-align:right;
}
</style>
</head>
<body>
	<h4>${priceSys.price_sys_title }</h4>
	<p>${priceSys.price_sys_content }</p>
	<div><fmt:formatDate type="both" value="${priceSys.price_sys_time }" /></div>
</body>
</html>