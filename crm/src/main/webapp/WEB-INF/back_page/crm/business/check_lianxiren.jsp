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
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="/css/crm/index/lianxiren.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>
//通话记录翻页
function lianxiren_jump_page(page,customer_id){
	jiazaidonghua();
	 $.post("/crm_business/get_record_list.jr", {
		'pageNumber' : page,
		'limit' : 20,
		'customer_id':customer_id
	}, function(data) {
		$("#record_list").html(data);
	}) 
}
</script>
<div class="check_person">
	<div class="person_left pull-left">
		<div class="person_name">
			<c:if test="${customer.customer_sex=='男' }">
				<img width="75px" height="71px"
					src="/images/crm/business/header_man.png" />
			</c:if>
			<c:if test="${customer.customer_sex=='女' }">
				<img src="/images/crm/business/person_header.png" />
			</c:if>
			<p>
				<strong>${customer.customer_name }</strong>
			</p>
			<p style="min-width: 90px; margin: 0 auto; height: 25px;">
				<span class="pull-left">${customer.customer_depart }</span><span
					class="pull-right">${customer.customer_position }</span>
			</p>
		</div>
		<div class="person_message">
			<ul>
				<li class="clearfix"><i class="fa fa-phone-square"></i><span>${customer.customer_officephone }</span></li>
				<li class="clearfix"><i class="fa fa-phone"></i><span>${customer.customer_phone }</span></li>
				<li class="clearfix"><i class="fa fa-qq"></i><span>${customer.customer_qq }</span></li>
				<li class="clearfix"><i class="fa fa-wechat"></i><span>${customer.customer_weixin }</span></li>
				<li class="clearfix"><i class="fa fa-envelope"></i><span>${customer.customer_mail }</span></li>
				<li class="clearfix"><i class="fa fa-tags"></i><span>${courseMenu.course_name }</span></li>
			</ul>
			<div class="person_ps">
				<p>备注：${customer.customer_note }</p>
			</div>
		</div>
	</div>
	<div class="person_right pull-right" id="record_list">
		<table class="table table-hover">
			<thead>
				<tr class="tr_bgcolor">
					<th>记录时间</th>
					<th>主叫号码</th>
					<th>被叫号码</th>
					<th>通话时长</th>
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
						<c:if test="${record.call_type=='呼出' }">
							<td>${record.employee.employee_name }/${record.zuoxi }</td>
							<td>${record.called_phone }</td>
						</c:if>
						<c:if test="${record.call_type=='呼入' }">
							<td>${record.zuoxi }</td>
							<td>${record.employee.employee_name }/${record.called_phone }</td>
						</c:if>
						<td>${record.record_time }</td>
						<td>${record.record_note }</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>
		</table>
		<c:if test="${ empty records.list }">
			<p class="zanwu" style="text-align: center; color: #94CE6E;">暂无该联系人通话记录</p>
		</c:if>
		<div class="pages">
			<ul class="pagination pull-right">
				<c:if test="${companies.hasPreviousPage==true}">
					<li class="previous"
						onclick="lianxiren_jump_page(${companies.pageNumber-1},${customer.customer_id })"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${companies.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${companies.pageNumber==page}">
							<li class="active"
								onclick="lianxiren_jump_page(${page},${customer.customer_id })"><a
								href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="lianxiren_jump_page(${page},${customer.customer_id })"><a
								href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${companies.hasNextPage==true}">
					<li class="next"
						onclick="lianxiren_jump_page(${companies.pageNumber+1},${customer.customer_id })">><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>