<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:forEach items="${subRecords }" var="sub">
<li>
	<div class='jindu'>
		<div class="jindu_title">${sub.parent_video.video_name }:${sub.courseVideo.video_name }</div>
		<span class="jindu_al">已学：</span>
		<div class="progress">
			<div
				class="progress-bar progress-bar-info progress-bar-striped active"
				style="width: <fmt:formatNumber type='number'
										value='${sub.record_time /sub.zong_shichang*100 }'
										pattern='0.00' maxFractionDigits='2' />%;"></div>
			<div class="progress-value"><fmt:formatNumber type='number'
									value='${sub.record_time /sub.zong_shichang*100 }'
									pattern='0.00' maxFractionDigits='2' />%</div>
		</div>
	</div>
	<div style="clear: both;"></div>
	<div class="study_time">学习时间：<fmt:formatDate value="${sub.paly_time }" /></div>
</li>
</c:forEach>