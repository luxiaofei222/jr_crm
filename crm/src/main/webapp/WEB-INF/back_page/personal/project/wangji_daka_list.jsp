<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table class="layui-table" lay-even lay-skin="nob">
	<thead>
		<tr>
			<th>应打卡日期</th>
			<th>时间段</th>
			<th>原因</th>
			<th>人事状态</th>
			<th>人事意见</th>
			<th>提交时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${punchs.list }" var="punch">
			<tr>
				<td><fmt:formatDate value="${punch.daka_time }" /></td>
				<td>${punch.shijianduan }</td>
				<td>${punch.punch_info }</td>
				<td><c:if test="${punch.renshi_state==0 }">
					未查看
				</c:if> <c:if test="${punch.renshi_state==1 }">
					已记录
				</c:if> <c:if test="${punch.renshi_state==2 }">
					未批准
				</c:if></td>
				<td><c:if test="${punch.renshi_state!=0 }">
				${punch.renshi_info }
				</c:if> <c:if test="${punch.renshi_state==0 }">
				--
				</c:if></td>
				<td><fmt:formatDate type="both" value="${punch.punch_time }" /></td>
				<td><button class="layui-btn layui-btn-normal layui-btn-small">删除</button></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<ul class="pagination">
	<c:if test="${punchs.hasPreviousPage==true}">
		<li class="previous" onclick="punch_jump_page(${punchs.pageNumber-1})"><a
			href="#fakelink" class="fui-arrow-left"></a></li>
	</c:if>
	<c:forEach items="${punchs.navigatePageNumbers }" var="page">
		<c:choose>
			<c:when test="${punchs.pageNumber==page}">
				<li class="active" onclick="punch_jump_page(${page})"><a
					href="#fakelink">${page}</a></li>
			</c:when>
			<c:otherwise>
				<li onclick="punch_jump_page(${page})"><a href="#fakelink">${page}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${punchs.hasNextPage==true}">
		<li class="next" onclick="punch_jump_page(${punchs.pageNumber+1})"><a
			href="#fakelink" class="fa fa-angle-right"></a></li>
	</c:if>
</ul>