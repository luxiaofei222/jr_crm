<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="pl_content">
	<c:if test="${empty comments.list }">
		<p class="zanwu">暂无评论信息</p>
	</c:if>
	<c:if test="${not empty comments.list }">
	<ul>
	<c:forEach items="${comments.list }" var="comment">
		<li class="pl_contents">
			<div class="student">
				<div class="st_l left">
					<dl>
						<dt>
							<img src="${comment.user.user_pic }">
						</dt>
						<dd>
							<div class="st_name">${comment.user.user_nickname }</div>
							<ul>
								<c:forEach begin="1" end="${comment.pingfen}" >
										<li class="star-five"><img
											src="/images/school/front/course_video/y-star.png" width="17"
											height="16"></li>
									</c:forEach>
							</ul>
						</dd>
					</dl>
				</div>
				<div class="st_r right">
					<span><fmt:formatDate type="both"
									value="${comment.v_comment_time }" /></span>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="pl_text">
				<p>${comment.v_comment_content }</p>
			</div>
		</li>
		</c:forEach>
	</ul>
</c:if>
	<!-- ****************************yema str************************** -->
	<ul class="pagination pull-right">
		<c:if test="${comments.hasPreviousPage==true}">
		<li class="previous" onclick="comment_jump_page(${comments.pageNumber-1},${video_id })"><a href="#fakelink" class="fui-arrow-left"></a></li>
		</c:if>
		<c:forEach items="${comments.navigatePageNumbers }" var="page">
				<c:choose>
					<c:when test="${comments.pageNumber==page}">
						<li class="active"><a>${page}</a></li>
					</c:when>
					<c:otherwise>
						<li onclick="comment_jump_page(${page},${video_id })"><a href="#fakelink">${page}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${courseVideos.hasNextPage==true}">
		<li class="next" onclick="comment_jump_page(${comments.pageNumber+1},${video_id })"><a href="#fakelink" class="fui-arrow-right"></a></li>
		</c:if>
	</ul>
</div>