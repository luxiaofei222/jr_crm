<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${empty courseInfos.list }">
	<p class="zanwu">暂无相关资讯</p>
</c:if>
<c:if test="${not empty courseInfos.list }">
	<ul class="ul_zixun">
		<c:forEach items="${courseInfos.list }" var="info">
			<li><a target="_blank" href="javascript:void(0)">${info.info_title }</a><span
				class="zixun_time"><fmt:formatDate pattern="MM-dd"
						value="${info.info_time }" /></span></li>
		</c:forEach>
	</ul>
</c:if>
<div class="clear"></div>
<div class="page">
	<ul>
		<c:if test="${courseInfos.hasPreviousPage==true}">
			<li class="fenyeda"
				onclick="course_info_jump_page(${courseInfos.pageNumber-1},'${info_type }','${course_id }')"><a
				href="javascript:void(0)"> 上一页 </a></li>
		</c:if>
		<c:forEach items="${courseInfos.navigatePageNumbers }" var="page">
			<c:choose>
				<c:when test="${courseInfos.pageNumber==page}">
					<li class="fenye_green"><a href="javascript:void(0)">${page}</a></li>
				</c:when>
				<c:otherwise>
					<li
						onclick="course_info_jump_page(${page},'${info_type }','${course_id }')"><a
						href="javascript:void(0)">${page}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${courseInfos.hasNextPage==true}">
			<li class="fenyeda"
				onclick="course_info_jump_page(${courseInfos.pageNumber+1},'${info_type }','${course_id }')"><a
				href="javascript:void(0)"> 下一页 </a></li>
		</c:if>
	</ul>
</div>