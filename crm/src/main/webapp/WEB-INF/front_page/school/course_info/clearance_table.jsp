<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<ul class="tg_wrapper">
		<li class="oneul">
			<ul>
				<li class="oneg">套餐
					</div>
				<li class="twog">原价<br /></li>
				<li class="twog">现价<br /></li>
				<li class="sixbg" style="line-height: 270px;">包含<br /></li>
				<li class="fourg" style="line-height: 58px;"><br /> 特色</li>
				<li class="fiveg">保障</li>
				<li class="fiveg">截止日期</li>
				<li class="sixg"></li>
			</ul>
		</li>
		<c:forEach items="${clearances.list }" var="clearance">
			<li class="twoul">
				<ul>
					<li class="oneg" style="font-size: 22px;">${clearance.clearance_name }</li>
					<li class="twog yuanjia">
						<p>
							<font>￥${clearance.clearance_price }</font>
						</p>
					</li>
					<li class="twog">
						<p>
							<font style="color: #FF0000">￥${clearance.new_price }</font>
						</p>
					</li>
					<li class="sixbg"
						style="display: table-cell; vertical-align: middle; text-align: center; font-size: 15px;">
						<c:if test="${empty clearance.clearanceVideos }">
							<div style='line-height: 270px; text-align: center;'>暂无课程</div>
						</c:if>
						<div style="display: inline-block;">
							<c:forEach items="${clearance.clearanceVideos }" var="video">
			${video.video_name }<span style="color: #FF0000">(￥${video.video_price })</span>
								<br />
							</c:forEach>
						</div>
					</li>
					<li class="fourg"><c:forEach items="${ clearance.tese}"
							var="tese">
							<p>${tese }</p>
						</c:forEach></li>
					<li class="fiveg">${clearance.clearrance_baozhang }</li>
					<li class="fiveg"><fmt:formatDate
							value="${clearance.daoqi_time }" /></li>
					<li class="a-btn sixg"><a
						href="http://tb.53kf.com/code/client/10147174/1]">在线咨询</a> <c:if
							test="${empty sessionScope.user_session}">
							<a href="javascript:void(0);" onclick="get_login()"
								class="buy_liji">立即购买</a>
						</c:if> <c:if test="${not empty sessionScope.user_session}">
							<a href="javascript:void(0);"
								onclick="pay_clearance_video(${clearance.clearance_id})"
								class="buy_liji">立即购买</a>
						</c:if></li>
				</ul>
			</li>
		</c:forEach>
	</ul>
	<ul class="page_number">
		<c:if test="${clearances.hasPreviousPage==true}">
			<li class="noborder"
				onclick="claerance_jump_page(${clearances.pageNumber-1},${course_id },'${dengji }')"><span class='fa fa-arrow-left'></span></li>
		</c:if>
		<li style="background-color: #06c1ae; color: white;">${clearances.pageNumber }</li>
		<c:if test="${clearances.hasNextPage==true}">
			<li class="noborder"
				onclick="claerance_jump_page(${clearances.pageNumber+1},${course_id },'${dengji }')"><span class='fa fa-arrow-right'></span></li>
		</c:if>
	</ul>