<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/page/pages.css" />
<link rel="stylesheet" type="text/css" href="/css/crm/index/crm.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
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
<script type="text/javascript">
$(function(){
	$(".pagination-dropdown").click(function(){
		$(this).children(".dropdown-menu").toggle();
	  })
})
//翻页上一页下一页
function leave_jump_page(page,limit,employee_id ,leave_type ){
	jiazaidonghua();
	$.post("/back_leave/get_crm_bumen_leave.jr",{
		 'employee_id':employee_id,
		 'leave_type':leave_type,
		'pageNumber' : page,
		'limit' : limit
	},function(data){
		$("#conten_list").html(data);
	})
}
//跳转页面
function leave_tiaozhuan_page(page,limit,employee_id ,leave_type){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/back_leave/get_crm_bumen_leave.jr", {
				 'employee_id':employee_id,
				 'leave_type':leave_type,
				 'pageNumber' : page_num,
				 'limit' : limit
				}, function(data) {
					$("#conten_list").html(data);
				}) 
		}
	}else{
		layer.msg("请输入数字！")
	}
}
//筛选条件
function shaixuan_tioajian(obj,limit){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("筛选中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_leave/get_crm_bumen_leave.jr",
			data:{
				'pageNumber':1,
				'limit':limit
			},
			success : function(data) {
				$("#conten_list").html(data);
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('查询失败');
			}
		});
}
//查看任务详情
function to_check_leave(leave_id){
	 layer.open({
		  type: 2,
		  title: ['请假审批'],
		  area: ['850px', '600px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_leave/to_check_leave.jr?leave_id="+leave_id
		  });
}

//选择请假的人
function get_employees(){
	  var bumen_id=$("#bumen_id").val();
	  if(bumen_id!=null&&bumen_id!=""){
		  $.post("/back_leave/get_bumen_employee_list.jr", {
				'bumen_id' : bumen_id
			}, function(data) {
				$("#employee_id").html(data);
			}) 
	  }else{
		  $("#employee_id").html("<option value=''>请选择</option>");
	  }
		
}
</script>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">请假审批</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">所在部门：</label>
				<div class="col-xs-2">
					<select class="form-control" name="bumen_id" id="bumen_id"
						onchange="get_employees()"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${organizations }" var="organ">
							<option value="${organ.organization_id }">${organ.organization_name }</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-xs-1 right_wz">申请人：</label>
				<div class="col-xs-2">
					<select class="form-control" name="employee_id" id="employee_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
					</select>
				</div>
				<label class="col-xs-1 right_wz">类型：</label>
				<div class="col-xs-2">
					<select class="form-control" name="leave_type"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="1">事假</option>
						<option value="2">病假</option>
						<option value="3">婚假</option>
						<option value="4">产假</option>
						<option value="5">丧假</option>
						<option value="6">其他</option>
						<option value="7">倒休</option>
						<option value="8">年假</option>
					</select>
				</div>
				<button class="shaixuan" onclick="shaixuan_tioajian(this,${limit})">筛选</button>
			</div>
		</form>
		<div class="operation">
			<div class="opera_left left"></div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>申请人</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>类型</th>
						<th>所属部门</th>
						<th>提交时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:if test="${not empty backLeaves.list }">
					<tbody>
						<c:forEach items="${backLeaves.list }" var="leave" varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+backLeaves.begin }</label></td>
							<td>${leave.employee.employee_name }</td>
							<td><fmt:formatDate type="both"
									value="${leave.leave_start_time }" /></td>
							<td><fmt:formatDate type="both"
									value="${leave.leave_end_time }" /></td>
							<td><c:if test="${leave.leave_type==1 }">
							事假
							</c:if> <c:if test="${leave.leave_type==2 }">
							病假
							</c:if> <c:if test="${leave.leave_type==3 }">
							婚假
							</c:if> <c:if test="${leave.leave_type==4 }">
							产假
							</c:if> <c:if test="${leave.leave_type==5 }">
							丧假
							</c:if> <c:if test="${leave.leave_type==6 }">
							其他
							</c:if> <c:if test="${leave.leave_type==7 }">
							倒休
							</c:if> <c:if test="${leave.leave_type==8 }">
							年假
							</c:if></td>
							<td>${leave.bumen }</td>
							<td><fmt:formatDate type="both" value="${leave.leave_time}" /></td>
							<td><c:if test="${leave.leave_state==0}">
								经理审批中
							</c:if> <c:if test="${leave.leave_state==1}">
								总监审批中
							</c:if> <c:if test="${leave.leave_state==2}">
								总经理审批中
							</c:if> <c:if test="${leave.leave_state==3}">
									<c:if test="${leave.qingjiazhong==3 }">
								等待销假
							</c:if>
									<c:if test="${leave.qingjiazhong==2 }">
								休假中
							</c:if>
									<c:if test="${leave.qingjiazhong==1 }">
								等待休假
							</c:if>
								</c:if> <c:if test="${leave.leave_state==4}">
								已销假
							</c:if> <c:if test="${leave.leave_state==5}">
								未批准
							</c:if></td>
							<td>
								<button type="button"
									onclick="to_check_leave(${leave.leave_id })"
									class="btn btn-success btn-xs">查看</button>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty backLeaves.list }">
				<p class="zanwu" style="text-align: center; color: #53C1FE;">还没有人请假哦！</p>
			</c:if>
		</div>
		<div class="pages">
			<div class="whole left">
				<div class="go_page">
					<span>第</span><input type="text" id="page_num" /> <span>页</span> <a
						href="javascript:void(0)"
						onclick="leave_tiaozhuan_page(${backLeaves.pages},${limit},'${employee_id }','${leave_type }')"
						class="btn_go">GO</a>
				</div>
			</div>
			<ul class="pagination right">
				<li><a href="javascript:void(0)"
					onclick="leave_jump_page(1,${limit },'${employee_id }','${leave_type }')"
					class="fa fa-fast-backward"></a></li>
				<c:if test="${backLeaves.hasPreviousPage==true}">
					<li class="previous"
						onclick="leave_jump_page(${backLeaves.pageNumber-1},${limit },'${employee_id }','${leave_type }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${backLeaves.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${backLeaves.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="leave_jump_page(${page},${limit },'${employee_id }','${leave_type }')"><a
								href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li class="pagination-dropdown dropup"><a href="#fakelink"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="fui-triangle-up"></i>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#fakelink"
							onclick="leave_jump_page(${backLeaves.pageNumber},20,'${employee_id }','${leave_type }')">20</a></li>
						<li><a href="#fakelink"
							onclick="leave_jump_page(${backLeaves.pageNumber},50,'${employee_id }','${leave_type }')">50</a></li>
						<li><a href="#fakelink"
							onclick="leave_jump_page(${backLeaves.pageNumber},100,'${employee_id }','${leave_type }')">100</a></li>
					</ul></li>
				<c:if test="${backLeaves.hasNextPage==true}">
					<li class="next"
						onclick="leave_jump_page(${backLeaves.pageNumber+1},${limit },'${employee_id }','${leave_type }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
				<li
					onclick="leave_jump_page(${backLeaves.pages},${limit },'${employee_id }','${leave_type }')"><a
					href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
			</ul>
		</div>
	</div>