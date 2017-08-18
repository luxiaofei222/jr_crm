<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="content2">
	<!--列表式的课程  -->
	<c:if test="${ empty courseVideos.list }">
		<p>暂无课程内容，敬请期待！</p>
	</c:if>
	<c:if test="${not empty courseVideos.list }">
		<c:forEach items="${courseVideos.list }" var="course_video">
			<div class="kc_con">
				<dl>
					<dt>
						<a target="_blank"
							href="/sc_coursevideo/get_course_video_player.html?video_id=${course_video.video_id }"><img
							src="${course_video.video_pic }" /></a>
					</dt>
					<dd>
						<h3>
							<a target="_blank"
								href="/sc_coursevideo/get_course_video_player.html?video_id=${course_video.video_id }">${course_video.video_name }</a>
						</h3>
						<p class="introduce">
							<c:if test="${not empty course_video.video_dis }">
							${course_video.video_dis }
							</c:if>
							<c:if test="${empty course_video.video_dis }">
							暂无描述
							</c:if>
						</p>
						<p>
							<span>现价:</span> <span class="green">￥${course_video.video_money_now }<!-- 0.00 --></span>
						</p>
						<p class="keshi">
							<span>${course_video.keshi_number }课时</span> <span>${course_video.comment_number }条评论</span>
							<span>${course_video.play_times }次</span>
						</p>
					</dd>
				</dl>
				<a target="_blank"
					href="/sc_coursevideo/get_course_video_player.html?video_id=${course_video.video_id }"
					class="check_xq">查看详情</a>
			</div>
		</c:forEach>
	</c:if>
</div>
<div class="paging">
	<ul>
							<c:if test="${courseVideos.hasPreviousPage==true}">
							<c:if test="${empty show }">
							<li onclick="moren_jump_page(${courseVideos.pageNumber-1},'${courseMenu.course_id }')"><a href="javascript:void(0)">上一页</a></li>
							</c:if>
								<c:if test="${show=='yiji' }">
									<li
										onclick="retrieval_yiji_jump_page(${courseVideos.pageNumber-1},'${course_parent_id }','${type }')"><a href="javascript:void(0)">上一页</a></li>
								</c:if>
								<c:if test="${show=='other' }">
									<li
										onclick="retrieval_other_jump_page(${courseVideos.pageNumber-1},'${zuire }','${jiage_paixu }','${type }','${banxing }','${dictionary_id }','${course_id }')"><a href="javascript:void(0)">上一页</a></li>
								</c:if>
							</c:if>
							<c:forEach items="${courseVideos.navigatePageNumbers }" var="page">
								<c:choose>
									<c:when test="${courseVideos.pageNumber==page}">
									<li class="active"><a href="javascript:void(0)">${page}</a></li>
									</c:when>
									<c:otherwise>
									<c:if test="${empty show }">
										<li onclick="moren_jump_page(${page},'${courseMenu.course_id }')"><a href="javascript:void(0)">${page}</a></li>
									</c:if>	
										<c:if test="${show=='yiji' }">
											<li
												onclick="retrieval_yiji_jump_page(${page},'${course_parent_id }','${type }')"><a href="javascript:void(0)">${page}</a></li>
										</c:if>
										<c:if test="${show=='other' }">
											<li
												onclick="retrieval_other_jump_page(${page},'${zuire }','${jiage_paixu }','${type }','${banxing }','${dictionary_id }','${course_id }')"><a href="javascript:void(0)">${page}</a></li>
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${courseVideos.hasNextPage==true}">
								<c:if test="${empty show }">
									<li
										onclick="moren_jump_page(${courseVideos.pageNumber+1},${courseMenu.course_id })"><a href="javascript:void(0)">下一页</a></li>
								</c:if>
								<c:if test="${show=='yiji' }">
									<li
										onclick="retrieval_yiji_jump_page(${courseVideos.pageNumber+1},${course_parent_id },'${type }')"><a href="javascript:void(0)">下一页</a></li>
								</c:if>
								<c:if test="${show=='other' }">
									<li
										onclick="retrieval_other_jump_page(${messages.pageNumber+1},'${zuire }','${jiage_paixu }','${type }','${banxing }',${dictionary_id },${course_id })"><a href="javascript:void(0)">下一页</a></li>
								</c:if>
							</c:if>
						</ul>
</div>