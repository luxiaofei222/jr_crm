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
<!-- <link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css"> -->
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<!-- <script src="/js/crm/company/check_business.js"></script> -->
<script src="/js/crm/call/bumen_record.js"></script>
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
		<div class="left">通话记录</div>
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
				<label class="col-xs-1 right_wz">主叫号码：</label>
				<div class="col-xs-2">
					<input class="form-control" name="zhujiao" id="zhujiao" placeholder="主叫号码">
				</div>
				<label class="col-xs-1 right_wz">被叫号码：</label>
				<div class="col-xs-2">
					<input class="form-control" name="beijiao" id="beijiao" placeholder="被叫号码">
				</div>
				<label class="col-xs-1 right_wz">通话状态：</label>
				<select class="col-xs-2" name="record_state" style="line-height:35px;height:35px;">
					<option value="">请选择</option>
					<option value="ANSWERED">已接</option>
					<option value="NO ANSWER">未接</option>
				</select>
			</div>
			<div class="form-group clearfix"  style="margin-top: 0px;">
				<label class="col-xs-1 right_wz">通话时长：</label>
				<div class="col-xs-2">
					<input class="form-control" name="start_shichang" id="start_shichang" placeholder="通话时长以秒为单位">
				</div>
				<label class="col-xs-1 right_wz">到：</label>
				<div class="col-xs-2">
					<input class="form-control" name="end_shichang" id="end_shichang" placeholder="通话时长以秒为单位">
				</div>
				<label class="col-xs-1 right_wz">通话类型：</label>
				<select class="col-xs-2" name="record_type" style="line-height:35px;height:35px;">
					<option value="">请选择</option>
					<option value="呼入">呼入</option>
					<option value="呼出">呼出</option>
				</select>
				<div class="col-xs-2">
			      <button type="button" onclick="chaxun_call_record(this,${limit})" class="btn btn-primary btn-lg" style="width:100px;height:35px;background-color:#94CE6E;line-height:15px;">查询</button>
			    </div>
			</div>
			</form>
			<div class="operation">
				<div class="opera_left left">
					<button type="button" onclick="refresh_business_record()" class="btn left">
						<i class="fa fa-refresh"></i>刷新
					</button>
				</div>
			</div>
			<div class="content_message">
				<table class="table table-hover">
					<thead>
						<tr class="tr_bgcolor warning">
							<th>编号</th>
							<th>主叫号码</th>
							<th>被叫号码</th>
							<th>联系企业</th>
							<th>通话时长</th>
							<th>通话状态</th>
							<th>通话类型</th>
							<th>通话时间</th>
							<th width="25%">操作</th>
						</tr>
					</thead>
							<c:if test="${not empty businessCallRecords.list }">
				<tbody>
				<c:forEach items="${businessCallRecords.list }" var="record" varStatus="vs">
                    <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+businessCallRecords.begin}</label></td>
							<c:if test="${record.call_type=='呼出' }">
							<td>${record.zuoxi }(${record.employee.employee_name })</td>
							<td>
						<%-- 	<i class="fa fa-phone-square"
										onclick="panduan_now_zuoxi_status(this,'${record.customer.customer_id }','${record.called_phone }','${sessionScope.employee_session.zuoxi }','${is_ip }')"
										style="font-size: 20px; color: #94CE6E;"></i> --%>
							${record.called_phone }
							<c:if test="${not empty record.customer }">
							(${record.customer.customer_name })
							</c:if>
							</td>
							</c:if>
							<c:if test="${record.call_type=='呼入' }">
							<td>
							<%-- <i class="fa fa-phone-square"
										onclick="panduan_now_zuoxi_status(this,${record.customer.customer_id },'${record.called_phone }','${sessionScope.employee_session.zuoxi }','${is_ip }')"
										style="font-size: 20px; color: #94CE6E;"></i> --%>
							${record.zuoxi }
							<c:if test="${not empty record.customer }">
							(${record.customer.customer_name })
							</c:if>
							</td>
							<td>${record.called_phone }(${record.employee.employee_name })</td>
							</c:if>
							<td>${record.qiye }</td>
							<td>${record.sec_time_call }</td>
							<c:if test="${record.call_state=='ANSWERED' }">
							<td>已接听</td>
							</c:if>
							<c:if test="${record.call_state=='NO ANSWER' }">
							<td>未接通</td>
							</c:if>
							<c:if test="${record.call_state=='FAILED' }">
							<td>失败</td>
							</c:if>
							<td>${record.call_type }</td>
							<td><fmt:formatDate type="both" value="${record.call_time }" /></td>
							<td>
							<i class="fa fa-eye three" onclick="check_record_detail(${record.record_id })"  title="查看通话详情"></i> 
							<c:if test="${record.record_time!=0 }">
							<i class="fa fa-play-circle two" onclick="listen_the_tape(${record.record_id },'${record.crm_recourd_uid}','${record.shijianchuo }',${is_ip })" title="试听录音"></i>
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</c:if>
				</table>
				<c:if test="${ empty businessCallRecords.list }">
				<p class="zanwu" style="text-align:center;color:#94CE6E;">没有查询到通话记录</p>
				</c:if>
			</div>
			<div class="pages">
				<div class="whole left">
					<div class="go_page">
					  <span>跳转到第</span><input type="text" id="page_num" />
					  <span>页</span>
					  <a href="javascript:void(0)" onclick="record_tiaozhuan_page(${businessCallRecords.pages},${limit},'${start_time }','${end_time }','${beijiao }','${zhujiao }','${record_state }','${record_type }','${start_shichang }','${end_shichang }')" class="btn_go">GO</a>
					</div>
				</div>
				<ul class="pagination right">
				    <li><a href="javascript:void(0)" onclick="record_jump_page(1,${limit },'${start_time }','${end_time }','${beijiao }','${zhujiao }','${record_state }','${record_type }','${start_shichang }','${end_shichang }')" class="fa fa-fast-backward"></a></li>
					<c:if test="${businessCallRecords.hasPreviousPage==true}">
						<li class="previous"
							onclick="record_jump_page(${businessCallRecords.pageNumber-1},${limit },'${start_time }','${end_time }','${beijiao }','${zhujiao }','${record_state }','${record_type }','${start_shichang }','${end_shichang }')"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${businessCallRecords.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${businessCallRecords.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="record_jump_page(${page},${limit },'${start_time }','${end_time }','${beijiao }','${zhujiao }','${record_state }','${record_type }','${start_shichang }','${end_shichang }')"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					 <li class="pagination-dropdown dropup">
                <a href="#fakelink" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fui-triangle-up"></i>
                </a>
                <ul class="dropdown-menu">
                  <li><a href="#fakelink" onclick="record_jump_page(${businessCallRecords.pageNumber},20,'${start_time }','${end_time }','${beijiao }','${zhujiao }','${record_state }','${record_type }','${start_shichang }','${end_shichang }')">20</a></li>
                  <li><a href="#fakelink" onclick="record_jump_page(${businessCallRecords.pageNumber},50,'${start_time }','${end_time }','${beijiao }','${zhujiao }','${record_state }','${record_type }','${start_shichang }','${end_shichang }')">50</a></li>
                  <li><a href="#fakelink" onclick="record_jump_page(${businessCallRecords.pageNumber},100,'${start_time }','${end_time }','${beijiao }','${zhujiao }','${record_state }','${record_type }','${start_shichang }','${end_shichang }')">100</a></li>
                </ul>
              </li>
					<c:if test="${businessCallRecords.hasNextPage==true}">
						<li class="next" onclick="record_jump_page(${businessCallRecords.pageNumber+1},${limit },'${start_time }','${end_time }','${beijiao }','${zhujiao }','${record_state }','${record_type }','${start_shichang }','${end_shichang }')"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
					<li onclick="record_jump_page(${businessCallRecords.pages},${limit },'${start_time }','${end_time }','${beijiao }','${zhujiao }','${record_state }','${record_type }','${start_shichang }','${end_shichang }')"><a href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
				</ul>
			</div>
		    <div class="clear"></div>
		</div>
