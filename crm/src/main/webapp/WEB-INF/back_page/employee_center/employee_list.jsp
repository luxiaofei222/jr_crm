<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<span class="title"><i class="fa fa-users"></i>系统成员</span>
<c:forEach items="${employees.list }" var="employ">
	<dl style="cursor: pointer;" onclick="get_employee_card(${employ.employee_id})">
		<dt>
			<c:if test="${empty employ.employee_pic }">
				<img src="/images/employee/picture.jpg" />
			</c:if>
			<c:if test="${not empty employ.employee_pic }">
				<img src="${employ.employee_pic }" />
			</c:if>
		</dt>
		<dd class="name">${employ.employee_name }</dd>
		<dd class="job">${employ.organization.organization_name }</dd>
	</dl>
</c:forEach>
<div class="pages right">
	<c:if test="${employees.hasPreviousPage==true}">
						<a href="javascript:void(0)"
							onclick="get_employee_page(${employees.pageNumber-1})"
							title="上一页" class="left"><i class="fa fa-fast-backward"></i></a>
					</c:if>
					<c:if test="${employees.hasPreviousPage!=true}">
						<a href="javascript:void(0)" class="left huise"
							title="上一页" class="left"><i class="fa fa-fast-backward"></i></a>
					</c:if>
					<a class="left"><span>${employees.pageNumber }</span>&nbsp;/&nbsp;${employees.pages }</a>
					<c:if test="${employees.hasNextPage==true}">
						<a href="javascript:void(0)"
							onclick="get_employee_page(${employees.pageNumber+1})"
							title="下一页" class="left"><i class="fa fa-fast-forward"></i></a>
					</c:if>
					<c:if test="${employees.hasNextPage!=true}">
						<a href="javascript:void(0)" class="left huise"
							title="下一页" class="left"><i class="fa fa-fast-forward"></i></a>
					</c:if>
					<div class="clear"></div>
</div>
<div class="clear"></div>