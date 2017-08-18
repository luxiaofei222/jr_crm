<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="recommendation">精品课程推荐</div>
<div class="recommend">
	<c:forEach items="${courseVideos }" var="cour_tuijian">
		<dl>
			<dt>
				<img src="${cour_tuijian.video_pic }" />
			</dt>
			<dd class="title">
				<h3>${cour_tuijian.video_name }</h3>
				<p>
					<span>现价:</span> <span class="green">${cour_tuijian.video_money_now }<!-- 0.00 --></span> <span>${cour_tuijian.keshi_number }课时</span>
				</p>
				<p>
					<span>${cour_tuijian.comment_number }条评论</span> <span>${cour_tuijian.play_times }次</span>
				</p>
			</dd>
		</dl>
	</c:forEach>
</div>