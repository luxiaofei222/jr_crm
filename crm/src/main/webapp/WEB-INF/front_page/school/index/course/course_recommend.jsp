<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/index/normalize.css" />
<script src="/js/school/front/index/modernizr.js"></script>
<script>
	$(document).ready(function() {
		if (Modernizr.touch) {
			// show the close overlay button
			$(".close-overlay").removeClass("hidden");
			// handle the adding of hover class when clicked
			$(".img").click(function(e) {
				if (!$(this).hasClass("hover")) {
					$(this).addClass("hover");
				}
			});
			// handle the closing of the overlay
			$(".close-overlay").click(function(e) {
				e.preventDefault();
				e.stopPropagation();
				if ($(this).closest(".img").hasClass("hover")) {
					$(this).closest(".img").removeClass("hover");
				}
			});
		} else {
			// handle the mouseenter functionality
			$(".img").mouseenter(function() {
				$(this).addClass("hover");
			})
			// handle the mouseleave functionality
			.mouseleave(function() {
				$(this).removeClass("hover");
			});
		}
	});
</script>

<c:if test="${yangshi==1 }">
<ul class="nr_kc_totle new_class" style="display: block">
</c:if>
<c:if test="${yangshi==2 }">
<ul class="nr_kc_totle free_class" style="display: block">
</c:if>
<c:if test="${yangshi==3 }">
<ul class="nr_kc_totle unfree_class" style="display: block">
</c:if>
	<c:forEach items="${courseVideos }" varStatus="vs" var="cour">

			<li class="nr_kc_info">

		<div class="img">
			<img src="${cour.video_pic }" class="kc_info_img">
			<div class="overlay">
				<a href="/sc_coursevideo/get_course_video_player.html?video_id=${cour.video_id }" class="expand">立即查看</a>
			</div>
		</div>
		<a href="/sc_coursevideo/get_course_video_player.html?video_id=${cour.video_id }" class="class_names"><span class="kc_info_title" title="${cour.video_name }">${cour.video_name }</span></a>
		<div class="kc_info1">
				<span class='kc_le'>现价:<span style="color: #f94c4c;">￥<!-- 0.00 --> ${cour.video_money_now }</span></span>
				<span class='kc_ri'>${cour.keshi_number }课时</span>
			</div>
			<div class="kc_info1">
				<span class='kc_le'> <img
					src="/images/school/front/index/head.png"
					style="float: left; margin-right: 5px;"> <span
					style="float: right;">${cour.comment_number}条评论</span>
				</span> <span class='kc_ri'> <img
					src="/images/school/front/index/start.png"
					style="float: left; margin-right: 5px;"> <span
					style="float: right;">${cour.play_times }次</span>
				</span>
		</li>
	</c:forEach>
</ul>