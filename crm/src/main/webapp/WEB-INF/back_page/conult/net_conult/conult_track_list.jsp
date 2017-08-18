<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/modules/laydate/laydate.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script type="text/javascript" src='/js/system/distpicker.js'></script>
<script type="text/javascript" src='/js/system/main.js'></script>
<style>
form .form-group label {
	font-size: 15px;
	color: #444;
}

form .form-group input:focus, form .form-group textarea:focus {
	border-color: #e74c3c;
}

form .form-group .btn {
	margin-right: 30px;
}

table tr th, table tr td {
	text-align: center;
}
</style>

<div style="padding:20px;">
	<table class="table table-hover">
		<thead>
			<tr class="tr_bgcolor warning">
				<th>序号</th>
				<th>顾问</th>
				<th>状态</th>
				<th>内容</th>
				<th>时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${conTracks }" varStatus="vs" var="track">
				<tr>
					<td><label class="label label-success btn-primary"
						for="minimal-checkbox-1">${vs.index+1 }</label></td>
					<td>${track.employee.employee_name }</td>
					<td><c:if test="${track.conult_state==1 }">
	继续追踪
	</c:if> <c:if test="${track.conult_state==2 }">
	拒绝
	</c:if> <c:if test="${track.conult_state==3 }">
	成交
	</c:if></td>
					<td>${track.content}</td>
					<td><fmt:formatDate type="both" value="${track.track_time }" />
					</td>
				</tr>
			</c:forEach>
	</table>
	<c:if test="${empty conTracks}">
		<p
			style="margin-top: 50px; text-align: center; font-size: 16px; color: orange;">暂无追踪记录</p>
	</c:if>
</div>
