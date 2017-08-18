<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="/css/school/front/person/css.css" rel="stylesheet"
	type="text/css" />
<link href="/css/school/front/person/study_record.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<script src="/js/school/front/index/scroll.js"></script>
<h1>
	<a href="javascript:void(0);" onclick="get_my_course()">我的课程</a>&nbsp;＞&nbsp;学习记录
</h1>
<div class="record_text">
	<ul class="study_list">
		<c:forEach items="${videoRecords }" var="record">
			<li>
				<div class="list_left">
					<h2>${record.parent_video.video_name }:${record.courseVideo.video_name }</h2>
					<div class='jindu'>
						<span class="jindu_title">已学：</span>
						<div class="progress">
							<div
								class="progress-bar progress-bar-info progress-bar-striped active"
								style="width: <fmt:formatNumber type='number'
										value='${record.record_time /record.zong_shichang*100 }'
										pattern='0.00' maxFractionDigits='2' />%;">
							</div>
							<div class="progress-value">
								<fmt:formatNumber type='number'
									value='${record.record_time /record.zong_shichang*100 }'
									pattern='0.00' maxFractionDigits='2' />
								%
							</div>
						</div>
						<div class="study_time">
							学习时间：
							<fmt:formatDate value="${record.paly_time }" />
						</div>
					</div>
				</div> <c:choose>
					<c:when test="${record.record_time /record.zong_shichang<1 }">
						<a href="/sc_coursevideo/get_course_video_player.html?video_id=${record.video_parent }&bofang_id=${record.video_id}" class="list_right">继续学习</a>
					</c:when>
					<c:otherwise>
						<img src="/images/school/front/person/ok.png"
							class="list_right_img">
					</c:otherwise>
				</c:choose>
			</li>
		</c:forEach>
	</ul>
</div>