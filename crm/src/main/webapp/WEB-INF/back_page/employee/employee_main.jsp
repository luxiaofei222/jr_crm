<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/school/back/employee/employee.js"></script>
<script src="/js/common/time.js"></script>
<style type="text/css">
  #zuoxi_list {
    position: absolute;
    width: 100%;
    height:100%;
    z-index: 999;
    top: 0px;
    left: 0px;
    background-color: rgba(0,0,0,0.4);
    display: none;
	}
	#zuoxi_list .zxshz {
	position: relative;
	width: 100%;
	height: 100%;
	}
	#zuoxi_list .zuoxi_shezhi {
	position: absolute;
	width: 950px;
	height: 644px;
	overflow-y:scroll;
	top: 50%;
	left: 50%;
	margin-top: -322px;
	margin-left: -475px;
	background-color: #fff;
	border-radius:15px;
	} 
	/* 加载动画样式 */
.loader-inner {
	top:35%;
	left:50%;
	}

@-webkit-keyframes ball-spin-fade-loader {
  50% {
    opacity: 0.3;
    -webkit-transform: scale(0.4);
            transform: scale(0.4); }

  100% {
    opacity: 1;
    -webkit-transform: scale(1);
            transform: scale(1); } }

@keyframes ball-spin-fade-loader {
  50% {
    opacity: 0.3;
    -webkit-transform: scale(0.4);
            transform: scale(0.4); }

  100% {
    opacity: 1;
    -webkit-transform: scale(1);
            transform: scale(1); } }

.ball-spin-fade-loader {
  position: relative; }
  .ball-spin-fade-loader > div:nth-child(1) {
    top: 25px;
    left: 0;
    -webkit-animation: ball-spin-fade-loader 1s 0s infinite linear;
            animation: ball-spin-fade-loader 1s 0s infinite linear; }
  .ball-spin-fade-loader > div:nth-child(2) {
    top: 17.04545px;
    left: 17.04545px;
    -webkit-animation: ball-spin-fade-loader 1s 0.12s infinite linear;
            animation: ball-spin-fade-loader 1s 0.12s infinite linear; }
  .ball-spin-fade-loader > div:nth-child(3) {
    top: 0;
    left: 25px;
    -webkit-animation: ball-spin-fade-loader 1s 0.24s infinite linear;
            animation: ball-spin-fade-loader 1s 0.24s infinite linear; }
  .ball-spin-fade-loader > div:nth-child(4) {
    top: -17.04545px;
    left: 17.04545px;
    -webkit-animation: ball-spin-fade-loader 1s 0.36s infinite linear;
            animation: ball-spin-fade-loader 1s 0.36s infinite linear; }
  .ball-spin-fade-loader > div:nth-child(5) {
    top: -25px;
    left: 0;
    -webkit-animation: ball-spin-fade-loader 1s 0.48s infinite linear;
            animation: ball-spin-fade-loader 1s 0.48s infinite linear; }
  .ball-spin-fade-loader > div:nth-child(6) {
    top: -17.04545px;
    left: -17.04545px;
    -webkit-animation: ball-spin-fade-loader 1s 0.6s infinite linear;
            animation: ball-spin-fade-loader 1s 0.6s infinite linear; }
  .ball-spin-fade-loader > div:nth-child(7) {
    top: 0;
    left: -25px;
    -webkit-animation: ball-spin-fade-loader 1s 0.72s infinite linear;
            animation: ball-spin-fade-loader 1s 0.72s infinite linear; }
  .ball-spin-fade-loader > div:nth-child(8) {
    top: 17.04545px;
    left: -17.04545px;
    -webkit-animation: ball-spin-fade-loader 1s 0.84s infinite linear;
            animation: ball-spin-fade-loader 1s 0.84s infinite linear; }
  .ball-spin-fade-loader > div {
    background-color: #fff;
    width: 15px;
    height: 15px;
    border-radius: 100%;
    margin: 2px;
    -webkit-animation-fill-mode: both;
            animation-fill-mode: both;
    position: absolute; 
    }
    .select2-chosen {
	color: white !important;
}

.form-group .right_wz {
	text-align: right;
	line-height: 35px;
}

.form-control {
	height: 35px;
}

.form-group {
	margin-bottom: 10xp !important;
}

.form-control:focus {
	border: 2px solid #28A4F4;
}

.shaixuan {
	width: 100px;
	height: 35px;
	line-height: 35px;
	background: #18d1bb;
	border: none;
	color: white;
	float: left;
	border-radius: 5px;
	margin-left: 40px;
}
</style>
<div class="back_right" style="min-width:900px;">
	<div class="back_r_top">
		<div class="left">账号管理</div>
		<div class="right" id="timer">
		</div>
	</div>
	<div class="right_content">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">姓名：</label>
				<div class="col-xs-2">
					<input class="form-control" id=employee_name name="employee_name">
				</div>
				<label class="col-xs-1 right_wz">所属角色：</label>
				<div class="col-xs-2">
					<select class="form-control" name="role_id" id="role_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${roles }" var="role">
							<option value="${role.role_id }">${role.role_name }</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-xs-1 right_wz">所在部门：</label>
				<div class="col-xs-2">
					<select class="form-control" name="bumen_id" id="bumen_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${organizations }" var="organ">
							<option value="${organ.organization_id }">${organ.organization_name }</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-xs-1 right_wz">性别：</label>
				<div class="col-xs-2">
					<select class="form-control" name="employee_sex"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</div>
			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">职位状态：</label>
				<div class="col-xs-2">
					<select class="form-control" name="employee_state"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="在职">在职</option>
						<option value="离职">离职</option>
					</select>
				</div>
				<button class="shaixuan" onclick="shaixuan_tioajian(this,${limit})">筛选</button>
			</div>
		</form>
	
		<div class="operation">
			<div class="opera_left left">
				<button type="button" onclick="to_add_employee()" class="btn left btn-primary" id="add_employee">
					<i class="fa fa-plus"></i>添加账号
				</button>
				<span><b style="margin-left: 15px;line-height: 31px;">员工数量</b>：<span style="color:#06C1AE;">${em_number }</span></span>
				<span><b style='margin-left:15px;line-height: 31px;'>在职员工数量</b>：<span style="color:orange;">${zaizhi_number }</span></span>
			</div>
			<div class="opera_right right">
			
			</div>
		</div>
			
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>姓名</th>
						<th>账号</th>
						<th>所属角色</th>
						<th>岗位</th>
						<th>坐席</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
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
							for="minimal-checkbox-1">${vs.index+1+employees.begin }</label></td>
						<td>${employee.employee_name }</td>
						<td>${employee.login_name }</td>
						<td>${employee.role.role_name }</td>
						<td>${employee.organization.organization_name }</td>
						<td>${employee.zuoxi }</td>
						<td><fmt:formatDate type="both" value="${employee.employee_time }" /></td>
						<td>
						<button type="button" onclick="to_set_zuoxi(${employee.employee_id},${is_ip })" class="btn btn-info btn-xs">设置坐席</button>
							<button type="button" onclick="to_update_employee(${employee.employee_id})" class="btn btn-success btn-xs">修改</button>
							<button type="button" onclick="delete_employee(${employee.employee_id},${employees.pageNumber},${limit })" class="btn btn-danger btn-xs">删除</button>
							<c:if test="${employee.employee_state=='在职' }">
							<button type="button" onclick="jinyong_employee(${employee.employee_id},'离职')" class="qidong btn btn-default btn-xs">禁用</button>
							</c:if>
							<c:if test="${employee.employee_state=='离职' }">
							<button type="button" onclick="jinyong_employee(${employee.employee_id},'在职')" class="qidong btn btn-warning btn-xs">启用</button>
							</c:if>
						</td>
						</td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
			</table>
			<c:if test="${ empty employees.list }">
				<p class="zanwu">暂无员工信息</p>
				</c:if>
		</div>
		<div class="pages">
			<!-- <div class="whole left">
				<button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
				<button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
			</div> -->
			<ul class="pagination right">
				 <c:if test="${employees.hasPreviousPage==true}">
						<li class="previous"
							onclick="employee_jump_page(${employees.pageNumber-1},${limit },'${employee_name }','${employee_sex }','${employee_state }','${role_id }','${bumen_id }')"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${employees.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${employees.pageNumber==page}">
								<li class="active" onclick="employee_jump_page(${page},${limit },'${employee_name }','${employee_sex }','${employee_state }','${role_id }','${bumen_id }')"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="employee_jump_page(${page},${limit },'${employee_name }','${employee_sex }','${employee_state }','${role_id }','${bumen_id }')"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${employees.hasNextPage==true}">
						<li class="next" onclick="employee_jump_page(${employees.pageNumber+1},${limit },'${employee_name }','${employee_sex }','${employee_state }','${role_id }','${bumen_id }')"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
			</ul>
		</div>
	</div>
</div>
	<div id="zuoxi_list">
	<!-- 坐席设置的 -->
	</div>
<script type="text/javascript">
function colse(){
	 $("#zuoxi_list").css("display","none");
	 $("#zuoxi_list").empty();
}
</script>