<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/font-awesome-4.6.3/css/font-awesome.min.css" />
<link rel="stylesheet" href="/css/crm/index/qiyeku.css" />
<link rel="stylesheet" href="/css/page/pages.css" />
<link rel="stylesheet" type="text/css" href="/css/crm/index/crm.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script src="/js/crm/company/check_business.js"></script>
<script src="/js/crm/call/record_bumen_tongji.js"></script>
<style>
.select2-chosen{
	color:white!important;
}
.form-group .right_wz {
	text-align: right;
	line-height: 35px;
}
.form-control{
	height:35px;
}
.form-group{
	margin-bottom:10xp!important;
}
.form-control:focus{
	border:2px solid #94CE6E;
}
</style>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">通话统计</div>
		<div class="right" id="timer"></div>
	</div>
	
		<div class="right_content">
		<form enctype="multipart/form-data" id="myform" class="form-horizontal">
			<div class="form-group clearfix"  style="padding-top: 10px;">
				<label class="col-xs-1 right_wz">通话时间：</label>
				<div class="col-xs-2">
					 <input class="layui-input" placeholder="选择开始日期" name="start_time" id="start_time">
				</div>
				<label class="col-xs-1 right_wz">到：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择结束日期" name="end_time" id="end_time">
				</div>
			</div>
			
			<div class="form-group clearfix"  style="margin-top: 0px;">
				<label class="col-xs-1 right_wz">通话类型：</label>
				<select class="col-xs-2" name="record_type" style="line-height:35px;height:35px;">
					<option value="">请选择</option>
					<option value="呼入">呼入</option>
					<option value="呼出">呼出</option>
				</select>
				<label class="col-xs-1 right_wz">坐席：</label>
				<select class="col-xs-2" name="employee_id" style="line-height:35px;height:35px;">
					<option value="">请选择</option>
					<c:forEach items="${zuoxis }" var="zuoxi">
					<option value="${zuoxi.employee_id }">${zuoxi.employee_name }(${zuoxi.zuoxi })</option>
					</c:forEach>
				</select>
				<div class="col-xs-2">
			      <button type="button" onclick="chaxun_call_record(this,${limit})" class="btn btn-primary btn-lg" style="width:100px;height:35px;background-color:#94CE6E;line-height:15px;">查询</button>
			    </div>
			</div>
			</form>
			<div class="operation">
				<div class="opera_left left">
					<button type="button" onclick="refresh_business_record(${employees.pageNumber},${limit },'${start_time }','${end_time }','${employee_id }','${record_type }')" class="btn left">
						<i class="fa fa-refresh"></i>刷新
					</button>
					<button type="button" onclick="to_check_echart(${employees.pageNumber},${limit },1)" class="btn left">
						<i class="fa fa-area-chart"></i>报表排序
					</button>
				</div>
			</div>
			<div class="content_message">
				<table class="table table-hover">
					<thead>
						<tr class="tr_bgcolor warning">
							<th>编号</th>
							<th>坐席</th>
							<th>姓名</th>
							<th>通话条数</th>
							<th>今日累计时长</th>
							<th>本周累计时长</th>
						</tr>
					</thead>
							<c:if test="${not empty employees.list }">
				<tbody>
				<c:forEach items="${employees.list }" var="employee" varStatus="vs">
				
                    <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+employees.begin}</label></td>
							<td>${employee.zuoxi }</td>
							<td>${employee.employee_name }</td>
							<td>${employee.call_number }</td>
							<td>${employee.call_time_length }</td>
							<td>${employee.zhou_call_time_length }</td>
						</tr>
					</c:forEach>
				</tbody>
				</c:if>
				</table>
				<c:if test="${ empty employees.list }">
				<p class="zanwu" style="text-align:center;color:#94CE6E;">没有查询到通话记录</p>
				</c:if>
			</div>
			<div class="pages">
				<div class="whole left">
					<div class="go_page">
					  <span>跳转到第</span><input type="text" id="page_num" />
					  <span>页</span>
					  <a href="javascript:void(0)" onclick="record_tiaozhuan_page(${employees.pages},${limit},'${start_time }','${end_time }','${employee_id }','${record_type }')" class="btn_go">GO</a>
					</div>
				</div>
				<ul class="pagination right">
				    <li><a href="javascript:void(0)" onclick="record_jump_page(1,${limit },'${start_time }','${end_time }','${employee_id }','${record_type }')" class="fa fa-fast-backward"></a></li>
					<c:if test="${employees.hasPreviousPage==true}">
						<li class="previous"
							onclick="record_jump_page(${employees.pageNumber-1},${limit },'${start_time }','${end_time }','${employee_id }','${record_type }')"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${employees.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${employees.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="record_jump_page(${page},${limit },'${start_time }','${end_time }','${employee_id }','${record_type }')"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					 <li class="pagination-dropdown dropup">
                <a href="#fakelink" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fui-triangle-up"></i>
                </a>
                <ul class="dropdown-menu">
                  <li><a href="#fakelink" onclick="record_jump_page(${employees.pageNumber},20,'${start_time }','${end_time }','${employee_id }','${record_type }')">20</a></li>
                  <li><a href="#fakelink" onclick="record_jump_page(${employees.pageNumber},50,'${start_time }','${end_time }','${employee_id }','${record_type }')">50</a></li>
                  <li><a href="#fakelink" onclick="record_jump_page(${employees.pageNumber},100,'${start_time }','${end_time }','${employee_id }','${record_type }')">100</a></li>
                </ul>
              </li>
					<c:if test="${employees.hasNextPage==true}">
						<li class="next" onclick="record_jump_page(${employees.pageNumber+1},${limit },'${start_time }','${end_time }','${employee_id }','${record_type }')"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
					<li onclick="record_jump_page(${employees.pages},${limit },'${start_time }','${end_time }','${employee_id }','${record_type }')"><a href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
				</ul>
			</div>
		    <div class="clear"></div>
		</div>
