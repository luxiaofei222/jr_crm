<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<c:if test="${empty courseVideos.list }">
			<p>没有搜索到您想要课程！</p>
			</c:if>
			<c:if test="${not empty courseVideos.list }">
				<ul class='course_infoul'>
					<c:forEach items="${courseVideos.list }" var="course_video">
						<li class="course_infoli"><a target="_blank" href="/sc_coursevideo/get_course_video_player.html?video_id=${course_video.video_id }"><img
							src="${course_video.video_pic }" class="infoli1"></a>
							<div class='infoli2'>
								<span class="infoli2_wz1"> <span><a target="_blank" href="/sc_coursevideo/get_course_video_player.html?video_id=${course_video.video_id }">
										${course_video.video_name }</a> </span> <c:if
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
						<li class="fenyeda" onclick="course_jump_page(${courseVideos.pageNumber-1},'${search_name}','${paixu }')"><a href="javascript:void(0)"> 上一页 </a></li>
					</c:if>
					<c:forEach items="${courseVideos.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${courseVideos.pageNumber==page}">
								<li class="fenye_green" ><a href="javascript:void(0)">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="course_jump_page(${page},'${search_name}','${paixu }')"><a href="javascript:void(0)" >${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${courseVideos.hasNextPage==true}">
						<li onclick="course_jump_page(${courseVideos.pageNumber+1},'${search_name}','${paixu }')" class="fenyeda"><a href="javascript:void(0)"> 下一页 </a></li>
					</c:if>
				</ul>
			</div>