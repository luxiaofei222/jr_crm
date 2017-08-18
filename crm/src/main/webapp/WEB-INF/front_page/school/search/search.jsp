<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程搜索</title>
<link rel="shortcut icon" href="/images/school/front/index/logo2.png"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="/css/school/front/index/index.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/index/reset.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/search/search.css">
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/school/front/search/search.js"></script>
<script type="text/javascript" src="/js/school/front/index/index.js"></script>
</head>
<body>
	<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
	<div class="search">
		<div class="search_wrapper">
			<div class="search_wrapper_nei">
				<div class="search_head">
					<div class="head_home">
						<a href="/index.jsp"><img src="/images/school/front/search/weizhi.png"></a>
					</div>
					<div class="head_test">
						<a href="javascript:void(0);" onclick="get_all_course_video()">全部课程</a>
						<c:if test="${not empty search_name }">
						 > <a href="javascript:void(0);">${search_name }</a>
						 </c:if>
					</div>
				</div>
			</div>
		</div>
		<div class="search_course">
			<div class='search_course_head'>
				<div class='course_head1 search_green' onclick="zonghe_search('${search_name}')">综合排序</div>
				<div class="course_head2" onclick="zonghe_search('${search_name}','zuire')">最热</div>
				<div class="course_head3" id="jiangxu" onclick="zonghe_search('${search_name}','DESC')">
					<span>价格</span> <img src="/images/school/front/search/price.png"
						class='price1'> <img
						src="/images/school/front/search/price2.png" class="price2">
				</div>
				<div class="course_head3" style="display: none;" onclick="zonghe_search('${search_name}','ASC')" id="shengxu">
					<span>价格</span> <img src="/images/school/front/search/price.png"
						class='price1'> <img
						src="/images/school/front/search/price2.png" class="price2">
				</div>
			</div>
			<div class="search_course_info" id="sc_search_list">
			<c:if test="${empty courseVideos.list }">
			<p style="line-height:50px;text-align:center;font-size:20px;margin-top:50px;">没有搜索到您想要课程！</p>
			</c:if>
			<c:if test="${not empty courseVideos.list }">
				<ul class='course_infoul'>
					<c:forEach items="${courseVideos.list }" var="course_video">
						<li class="course_infoli"><a href="/sc_coursevideo/get_course_video_player.html?video_id=${course_video.video_id }"><img
							src="${course_video.video_pic }" class="infoli1"></a>
							<div class='infoli2'>
								<span class="infoli2_wz1"> <span>
										<a href="/sc_coursevideo/get_course_video_player.html?video_id=${course_video.video_id }">${course_video.video_name }</a> </span> <c:if
										test="${course_video.video_money_now>0 }">
										<img src="/images/school/front/search/shiting.png">
									</c:if>
								</span>
								<div style="clear: both;"></div>
								<p class="infoli2_wz2">
									<c:if test="${not empty course_video.video_dis }">
							${course_video.video_dis }
							</c:if>
									<c:if test="${empty course_video.video_dis }">
							暂无描述
							</c:if>
								</p>
								<div class="infoli2_wz3">
									<span>现价：</span> <span class='wz3_free1'>￥${course_video.video_money_now }</span>
								</div>
								<div class="infoli2_wz4">
									<span class='wz4_one'>${course_video.keshi_number }课时</span>
									<div class='wz4_two'>
										<img src="/images/school/front/search/head.png"> <span>${course_video.comment_number }条评论</span>
									</div>
									<div class='wz4_three'>
										<img src="/images/school/front/search/start.png"> <span>${course_video.play_times }次</span>
									</div>
								</div>
							</div>
							<div class="infoli3">
								<a href="/sc_coursevideo/get_course_video_player.html?video_id=${course_video.video_id }"> 查看详情 </a>
							</div></li>
					</c:forEach>
				</ul>
				</c:if>
				<div class="search_fenye">
				<ul>
					<c:if test="${courseVideos.hasPreviousPage==true}">
						<li class="fenyeda" onclick="course_jump_page(${messages.pageNumber-1},'${search_name}')"><a href="javascript:void(0)"> 上一页 </a></li>
					</c:if>
					<c:forEach items="${courseVideos.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${courseVideos.pageNumber==page}">
								<li class="fenye_green" ><a href="javascript:void(0)">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="javascript:void(0)" onclick="course_jump_page(${page},'${search_name}')">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${courseVideos.hasNextPage==true}">
						<li class="fenyeda" onclick="course_jump_page(${courseVideos.pageNumber+1},'${search_name}')"><a href="javascript:void(0)"> 下一页 </a></li>
					</c:if>
				</ul>
			</div>
			</div>
		</div>
	</div>
	 <!--登录注册窗口  -->
	<jsp:include page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/footer.jsp"></jsp:include>
</body>
</html>