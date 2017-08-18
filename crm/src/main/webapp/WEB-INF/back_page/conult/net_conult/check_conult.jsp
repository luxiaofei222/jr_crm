<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<title>查看网询客户详情</title>
<style>
 table tr td{
 	text-align:center;
 }
 table tr td:nth-child(2n+1){
 	font-weight:bold;
 	color:#fff;
 	background-color:#06C1AE;
 }
</style>
</head>
<body style="padding:20px;">
	<table class="table table-bordered table-striped table-hover">
		<tbody>
			<tr>
				<td>所属分类</td>
				<td>${netConult.course_name }</td>
				<td>等级</td>
				<td>${netConult.dictionary_name }</td>
				<td>客户姓名</td>
				<td>${netConult.user_name }</td>
			</tr>
			<tr>
				<td>客户性别</td>
				<td>${netConult.user_sex }</td>
				<td>客户电话</td>
				<td>${netConult.user_phone }</td>
				<td>客户QQ</td>
				<td>${netConult.user_qq }</td>
			</tr>
			<tr>
				<td>客户微信</td>
				<td>${netConult.user_weixin }</td>
				<td>意向学校</td>
				<td>${netConult.hope_sc }</td>
				<td>意向专业</td>
				<td>${netConult.zhuanye }</td>
			</tr>
			<tr>
				<td>目前学历</td>
				<td>${netConult.now_edu }</td>
				<td>意向学历</td>
				<td>${netConult.hope_edu }</td>
				<td>资讯方式</td>
				<td>${netConult.consult_type }</td>
			</tr>
			<tr>
				<td>搜索关键词</td>
				<td>${netConult.search_word }</td>
				<td>所在地区</td>
				<td>${netConult.province }-${netConult.city }-${netConult.area }</td>
				<td>客户IP</td>
				<td>${netConult.user_ip }</td>
			</tr>
			<tr>
				<td>资讯日期</td>
				<td><fmt:formatDate value="${netConult.zixun_time }" /></td>
				<td>录入日期</td>
				<td><fmt:formatDate value="${netConult.consult_time }" /></td>
				<td>目前业务状态：</td>
				<td><c:if test="${netConult.conult_state==0 }">
              	未回访
              </c:if> <c:if test="${netConult.conult_state==1 }">
              	正在联系
              </c:if> <c:if test="${netConult.conult_state==2 }">
              	已拒绝
              </c:if> <c:if test="${netConult.conult_state==3 }">
              	成交
              </c:if></td>
			</tr>
			<tr>
				<td>备注</td>
				<td colspan='5'>${netConult.note }</td>
			</tr>
			<tr>
				<td>问题描述</td>
				<td colspan='5'>${netConult.question_info }</td>
			</tr>
		</tbody>
	</table>
</body>
</html>