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
<title>查看评论详情</title>
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
	margin-top:10px;
	color:orange;
	float:left;
	margin-left:20px;
	width:350px;
}

label {
    float:left;
    width:80px;
    margin-top:10px;
}
.clear {
    clear:both;
}
</style>
</head>
<body>
	<label>课程名称：</label><p>${comment.courseVideo.video_name }</p><div class="clear"></div>
	<label>用户：</label><p>${comment.user.user_nickname }-${comment.user.user_phone }</p><div class="clear"></div>
	<label>评论内容：</label><p>${comment.v_comment_content }</p><div class="clear"></div>
	<label>评分：</label><p>${comment.pingfen }</p><div class="clear"></div>
	<label>用户ip：</label><p>${comment.v_comment_ip }</p><div class="clear"></div>
	<label>评论时间：</label><p><fmt:formatDate type="both" value="${comment.v_comment_time }" /></p>
</body>
</html>