<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="item_nei_bt3">
<a href="${cour_menu.course_link }" target="_blank"><img src="${cour_menu.course_index_pic }"></a>
</div>
<div class='item_nei_bt4'>
		      				<ul class="qianmianul">
		      				<c:if test="${empty cour_menu.courseVideos }"><p style="text-align:center;font-size:20px;color:#06C1AE;line-height:465px;">暂无课程，请耐心等待！</p></c:if>
		      				<c:if test="${not empty cour_menu.courseVideos }">
		      				<c:forEach items="${cour_menu.courseVideos }" var="vide" varStatus="vs">
		      					<c:if test="${vs.index==0 }">
		      					<li class="no-left">
		      					</c:if>
		      					<c:if test="${vs.index==3 }">
		      					<li class="no-left">
		      					</c:if>
		      					<c:if test="${vs.index!=0 &&vs.index!=3}">
		      					<li>
		      					</c:if> 
			      					<a target="_blank" href="/sc_coursevideo/get_course_video_player.html?video_id=${vide.video_id }">
			      						<img src="${vide.video_pic }" class="item_nei_img">
			      					</a>
			      					<a target="_blank" href="/sc_coursevideo/get_course_video_player.html?video_id=${vide.video_id }"><span class="item_nei_title" title="${vide.video_name }">${vide.video_name }</span></a>
			      					<div class="nei_info1">
			      						<span class='info1_le'>现价:<span style="color:#f94c4c;">￥<!-- 0.00 -->${vide.video_money_now }</span></span>
			      						<span class='info1_ri'>${vide.keshi_number }课时</span>
			      					</div>
			      					<div class="nei_info1">
			      						<span class='info1_le'>
			      							<img src="/images/school/front/index/head.png" style="float:left;margin-right:5px;">
			      							<span style="float:right;">${vide.comment_number}条评论</span>
			      						</span>
			      						<span class='info1_ri'>
			      							<img src="/images/school/front/index/start.png" style="float:left;margin-right:5px;">
			      							<span style="float:right;">${vide.play_times }次</span>
			      						</span>
			      					</div>
		      					</li>
		      				</c:forEach>
		      				</c:if>
		      				</ul>
		      				<div class="nei_bt4_wz">
		      					<div class="bt4_wz1"><a target="_blank" href="/front_info/get_course_info.html?courseid=${cour_menu.sub_course_id }">${cour_menu.course_name }</a></div>
		      					<ul class='bt4_wz2'>
		      					<c:if test="${empty cour_menu.courseInfos }">
		      					<p style="text-align:center;font-size:15px;color:#06C1AE;line-height:300px;">暂无资讯信息！</p>
		      					</c:if>
		      					<c:if test="${not empty cour_menu.courseInfos }">
		      					<c:forEach items="${cour_menu.courseInfos }" var="info">
		      						<li class='bt4_wz3'>
		      							<img src="/images/school/front/index/zixun.png">
		      							<span><a target="_blank" href="/front_info/get_course_info_detail.html?info_id=${ info.info_id}" title="${info.info_title }">${info.info_title }</a></span>
		      						</li>
		      						</c:forEach>	
		      					</c:if>
		      					</ul>
		      				</div>	
	    				</div>