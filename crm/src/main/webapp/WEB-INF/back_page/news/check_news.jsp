<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看新闻动态</title>
	<style type="text/css">
	h2{
		text-align:center;
	}
	.ul_wai{
		margin:0 auto;
		text-align:center;
		margin-top:20px;
	}
	ul{
		overflow:hidden;
		margin:0 auto;
		display:inline-block;
		text-align:center;
	}
	ul li{
		list-style:none;
		float:left;
		margin-right:70px;
		font-size:14px;
		color:#999;
	}
	.daodu{
		width:100%;
		height:50px;
		border:1px dashed #06C1AE;
		background-color:#fafafa;
		margin-top:20px;
		padding:20px;
	}
	p{
		text-indent:2em;
		text-align:center;
	}
	</style>
</head>
<body style="max-width: 1000px">
<h2>${news.news_title }</h2>
<hr style='height:1px;border:none;border-top:1px dotted #06C1AE'>
<div class="ul_wai">
	<ul>
		<li>来源：${news.news_laiyuan }</li>
		<li>责任编辑：${news.news_eidit }</li>
		<li>时间：<fmt:formatDate type="both" value="${news.news_time }" /></li>
	</ul>
</div>
<div class="daodu">
<span>导读:</span>${news.news_abstrack }
</div>
<p>${news.news_content }</p>
</body>
</html>