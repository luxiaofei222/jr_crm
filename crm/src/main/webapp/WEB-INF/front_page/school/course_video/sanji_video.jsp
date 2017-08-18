<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<ul>
<c:if test="${not empty courseVideos }">
 <c:forEach items="${courseVideos }" var="cour_video" varStatus="vs">
	<li class="kj_mc play"><span>${vs.index+1}、${cour_video.video_name }</span></li>
	</c:forEach>
</c:if>
<c:if test="${empty courseVideos }">
<li class="kj_mc play"><span>该章节暂无课程内容！</span></li>
</c:if>
</ul>