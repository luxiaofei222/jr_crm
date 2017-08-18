<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
.form-horizontal .form-group .wz_right {
	text-align: right;
}

body {
	color: #333;
}

.table>tbody>tr>td {
	vertical-align: middle;
}

.dangqian p {
	color: orange;
	display: inline-block;
}

.label {
	padding: 0.3rem 0.6rem;
}

td.status {
	position: relative;
}

td.status span {
	width: 15px;
	height: 15px;
	display: inline-block;
	border-radius: 50%;
	position: absolute;
	top: 15px;
	left: -10px;
}

td.status span.leisure {
	background-color: #0C6;
}

td.status span.no_online {
	background-color: #999;
}
td.status span.online {
	background-color: #FF9900;
}

td.status span.busying {
	background-color: #FF0033;
}
.colse i {
    float: right;
    font-size: 35px;
    color: #999;
    margin-top: -20px;
    margin-right: -20px;
}
</style>
<script>
//设置坐席
function add_zuoxi(extension,employee_id){
	$.post("/back_employee/update_employee.jr",{
		'employee_id':employee_id,
		'zuoxi':extension
	},function(data){
		if(data==1){
            tanchuang('恭喜您，设置成功');
		}else{
			tanchuang('很遗憾，设置失败');
		}
	})
}
</script>
<div class="zxshz">
<div class="zuoxi_shezhi" style="padding: 35px;">
    <div class="colse"><i class="fa fa-times-circle" style="cursor: pointer;" onclick="colse()"></i></div>
	<div class="dangqian">
		当前坐席名称为：
		<p>${employee.employee_name }</p>
	</div>
	<br />
	<table class="table table-hover">
		<thead>
			<tr class="tr_bgcolor warning">
				<th>编号</th>
				<th>分机号</th>
				<th>状态</th>
				<th>绑定账号</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${zuoxi_list }" var="zuoxi" varStatus="vs">
				<tr>
					<td><label class="label label-success btn-primary"
						for="minimal-checkbox-1">${vs.index+1 }</label></td>
					<td>${zuoxi.extension }</td>
					<c:if test="${zuoxi.status==0 }">
					<td class="status"><span class="leisure"></span>空闲</td>
					</c:if>
					<c:if test="${zuoxi.status==8 }">
					<td class="status"><span class="ringing"></span>振铃</td>
					</c:if>
					<c:if test="${zuoxi.status==4 }">
					<td class="status"><span class="no_online"></span>不在线</td>
					</c:if>
					<c:if test="${zuoxi.status==1 }">
					<td class="status"><span class="online"></span>通话中</td>
					</c:if>
					<c:if test="${zuoxi.status==11 }">
					<td class="status"><span class="busying"></span>示忙</td>
					</c:if>
					<c:if test="${not empty zuoxi.employee }">
						<td style="color: green;">${zuoxi.employee.employee_name }</td>
					</c:if>
					<c:if test="${ empty zuoxi.employee }">
						<td>未绑定</td>
					</c:if>
					<td><c:if test="${ empty zuoxi.employee }">
							<button type="button" onclick="add_zuoxi('${zuoxi.extension }',${employee.employee_id })" class="btn btn-success btn-xs">点击绑定</button>
						</c:if> <c:if test="${not empty zuoxi.employee }">
							<button type="button" onclick="add_zuoxi('0',${employee.employee_id })" class="btn btn-danger btn-xs">解除绑定</button>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>