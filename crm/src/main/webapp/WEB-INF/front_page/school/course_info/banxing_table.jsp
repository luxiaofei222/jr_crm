<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="table-box" style="display: block;">
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tbody>
		<tr class="title">
			<td>新版课程</td>
			<td>讲师</td>
			<td>课时</td>
			<td>价格</td>
			<td>免费体验</td>
			<td>选课</td>
		</tr>
		<c:forEach items="${courseVideos_banxing }" var="banxing_video">
			<tr>
				<td class="name"><a href="javascript:void(0);" target="_blank">${banxing_video.video_name }</a></td>
				<td>${banxing_video.teacher.teacher_name }</td>
				<td>${banxing_video.keshi_number }</td>
				<td>${banxing_video.video_money_now }<!-- 0.00 -->元</td>
				<td><a href="javascript:void(0);" target="_blank"><img
						width="36" height="50"
						src="/images/school/front/courseinfo/st2.gif"></a></td>
				<td><a class="a-btn" href="javascript:void(0);" target="_blank">报名</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>