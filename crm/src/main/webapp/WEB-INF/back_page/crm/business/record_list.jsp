<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table class="table table-hover">
	<thead>
		<tr class="tr_bgcolor">
			<th>时间</th>
			<th>姓名</th>
			<th>联系电话</th>
			<th>跟进内容</th>
		</tr>
	</thead>
	<c:if test="${not empty records.list }">
		<tbody>
			<c:forEach items="${records.list }" var="record" varStatus="vs">
				<c:if test="${vs.count%2 == '0' }">
					<tr class="active">
				</c:if>
				<c:if test="${vs.count%2 != '0' }">
					<tr>
				</c:if>
				<td><fmt:formatDate type="both" value="${record.call_time }" /></td>
				<td>
				<c:if test="${empty record.customer.customer_name }">
				该联系人已被删除
				</c:if>
				${record.customer.customer_name }</td>
				<td>${record.called_phone }</td>
				<td>${record.record_note }</td>
				</tr>
			</c:forEach>
		</tbody>
	</c:if>
</table>
<c:if test="${ empty records.list }">
	<p class="zanwu" style="text-align: center; color: #94CE6E;">暂无该联系人通话记录</p>
</c:if>