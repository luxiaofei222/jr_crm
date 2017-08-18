<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="${news.meta_dis }">
<meta name="keywords" content="${news.meta_key }">
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/school/front/news/news.css">
<link rel="stylesheet" href="/css/school/front/index/reset.css">
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<script>
$(function(){
	var win_height = $(window).height(); 
	$(".zixun_content").css("min-height",win_height-560);	
})
</script>
<c:if test="${not empty news.meta_title }">
	<title>${news.meta_title }</title>
</c:if>
<c:if test="${empty news.meta_title }">
	<title>${news.news_title }</title>
</c:if>
</head>
<body>
	<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
	<div class="breadcrumb">
		<div class="home">
			<a href="javascript:void(0)"><i class="fa fa-home"></i></a> <a
				href="/index.jsp">首页&gt;</a> <a href="javascript:void(0)">${news.news_title }</a>
		</div>
	</div>
	<div class="clear"></div>
	<div class="zixun_content">
		<div class="zixun_title">
			<h3>${news.news_title }</h3>
		</div>
		<div class="zixun_info">
			<ul>
			<c:if test="${empty news.news_laiyuan }">
				<li>来源：京人教育网校</li>
			</c:if>
			<c:if test="${not empty news.news_laiyuan }">
				<li>来源：${news.news_laiyuan }</li>
			</c:if>
				<li>责任编辑：${news.news_eidit }</li>
				<li>时间：<fmt:formatDate type="both" value="${news.news_time }" /></li>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="daodu">
			<p>
				<b>导读：</b>${news.news_abstrack }</p>
		</div>
		<div class="zixun_contents">
			<c:if test="${not empty news.news_content }">
      	${news.news_content }
      	</c:if>
			<c:if test="${empty news.news_content }">
				<p class="zanwu">暂无内容</p>
			</c:if>
		</div>
	</div>
	<!--登录注册窗口  -->
	<jsp:include
		page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/footer.jsp"></jsp:include>
</body>
</html>