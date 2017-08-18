<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/js/school/front/index/scroll.js"></script>
<h1>我的课程</h1>
<ul class="person_class">
	<c:if test="${empty myCourses.list }">
		<p class="without">
			<img src="/images/school/front/index/user_moren.png"><span>我的课程还没有内容，快去添加吧！</span>
		</p>
		<div class="clear"></div>
	</c:if>
	<c:if test="${not empty myCourses.list }">
		<c:forEach items="${myCourses.list }" var="mycourse">
			<li>
				<dl>
					<dt>
						<img src="${mycourse.courseVideo.video_pic }" width="195"
							height="110" />
							<c:if test="${not empty mycourse.tianshu }">
						<p style="text-align: center;margin: 0 auto;margin-top: 10px;">课程剩余时间：<b style="color:#06c1ae;font-weight:normal;">${mycourse.tianshu }</b>天</p>
						</c:if>
					</dt>
					<dd class="title">
						<span class='title_wz'>${mycourse.courseVideo.video_name }</span>
						<span class="jindu">${mycourse.courseVideo.keshi_number }课时</span>
						<c:if test="${empty mycourse.videoRecord }">
						<a href="/sc_coursevideo/get_course_video_player.html?video_id=${mycourse.courseVideo.video_id }"
							target="_blank" class="continue" style="background-color:#06c1ae;color:white;">点击学习</a>
						</c:if>
						<c:if test="${not empty mycourse.videoRecord }">
						<a href="javascript:void(0);" onclick="get_user_study_record(${mycourse.courseVideo.video_id })" target="_blank" class="continue">学习记录</a>
						</c:if>
					</dd>
					<dd class="jieshao">${mycourse.courseVideo.video_dis }</dd>
					<c:if test="${not empty mycourse.videoRecord }">
					<dd class="xuexi">
						<div class="recently_watch">
							<span class="watch_course">您最近观看的课程：<span
								style="color: #06c1ae;">${mycourse.videoRecord.parent_video.video_name }:${mycourse.videoRecord.courseVideo.video_name }</span></span>
						</div>
					</dd>
					<dd>
						<span class="watch_course" style="color:#999;">进度：</span>
						<div class="progress">
							<div class="progress-bar" style="width:<fmt:formatNumber type='number'
										value='${mycourse.videoRecord.record_time /mycourse.videoRecord.zong_shichang*100 }'
										pattern='0.00' maxFractionDigits='2' />%" role="progressbar"></div>
							<p class="progress-number"><fmt:formatNumber type="number"
										value="${mycourse.videoRecord.record_time /mycourse.videoRecord.zong_shichang*100 }"
										pattern="0.00" maxFractionDigits="2" />%</p>
						</div>
						<a
							href="/sc_coursevideo/get_course_video_player.html?video_id=${mycourse.courseVideo.video_id }&bofang_id=${mycourse.videoRecord.video_id}"
							target="_blank" class="continue" style="background-color:#06c1ae;color:white;">继续学习</a>
					</dd>
					</c:if>
				</dl>
			</li>
		</c:forEach>
	</c:if>
</ul>
<div class="zixun_wrapper"
	style="height: 290px; width: 200px; overflow: hidden; position: absolute; top: 75px; right: 30px;">
	<ul class="course_zixun">
		<c:forEach items="${courseInfos }" var="info">
			<li class="course_zixunli"><a target="_blank"
				title="${info.info_title }"
				href="/front_info/get_course_info_detail.html?info_id=${ info.info_id}">【${info.info_type }】${info.info_title }</a></li>
		</c:forEach>
	</ul>
</div>
<div class="clear"></div>
<div class="search_fenye">
	<ul>
		<c:if test="${myCourses.hasPreviousPage==true}">
			<li class="fenyeda"
				onclick="mycourse_jump_page(${myCourses.pageNumber-1})"><a
				href="javascript:void(0)"> 上一页 </a></li>
		</c:if>
		<c:forEach items="${myCourses.navigatePageNumbers }" var="page">
			<c:choose>
				<c:when test="${myCourses.pageNumber==page}">
					<li class="pageactive"><a href="javascript:void(0)">${page}</a></li>
				</c:when>
				<c:otherwise>
					<li onclick="mycourse_jump_page(${page})"><a
						href="javascript:void(0)">${page}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${myCourses.hasNextPage==true}">
			<li onclick="mycourse_jump_page(${myCourses.pageNumber+1})"
				class="fenyeda"><a href="javascript:void(0)"> 下一页 </a></li>
		</c:if>
	</ul>
</div>
<div class="clear"></div>
<script type="text/javascript">
//新闻动态滚动
$(document).ready(function(){ 
	$('.zixun_wrapper').myScroll({
		speed: 60,
		rowHeight: 26 
	});
});
</script>