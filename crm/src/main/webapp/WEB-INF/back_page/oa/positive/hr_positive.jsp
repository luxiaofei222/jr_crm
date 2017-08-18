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
function positive_jump_page(page,limit,employee_id ,bumen_id ){
	jiazaidonghua();
	$.post("/positive/get_renshi_positive.jr",{
		 'employee_id':employee_id,
		 'bumen_id':bumen_id,
		'pageNumber' : page,
		'limit' : limit
	},function(data){
		$("#conten_list").html(data);
	})
}
//跳转页面
function positive_tiaozhuan_page(page,limit,employee_id ,bumen_id){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/positive/get_renshi_positive.jr", {
				 'employee_id':employee_id,
				 'bumen_id':bumen_id,
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
			url : "/positive/get_renshi_positive.jr",
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
//查看转正申请详情
function to_check_positive(positive_id){
	 layer.open({
		  type: 2,
		  title: ['转正审批'],
		  area: ['850px', '600px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/positive/to_check_renshi_positive.jr?positive_id="+positive_id
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
		<div class="left">转正申请</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">所在部门：</label>
				<div class="col-xs-2">
					<select class="form-control" name="bumen_id" id="bumen_id" onchange="get_employees()"
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
				<button class="shaixuan" onclick="shaixuan_tioajian(this,${limit})">筛选</button>
			</div>
		</form>
		<div class="operation">
			<div class="opera_left left">
				
			</div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>申请人</th>
						<th>转正时间</th>
						<th>所属部门</th>
						<th>岗位</th>
						<th>提交时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:if test="${not empty positives.list }">
					<tbody>
						<c:forEach items="${positives.list }" var="positive" varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+positives.begin }</label></td>
							<td>${positive.employee.employee_name }</td>
							<td><fmt:formatDate 
									value="${positive.positive_time }" /></td>
							<td>${positive.bumen }</td>
							<td>${positive.gangwei }
							</td>
							<td><fmt:formatDate  type="both"
									value="${positive.shenqing_time}" /></td>
							<td>
							<c:if test="${positive.positive_state==0}">
								经理审批中
							</c:if>
							<c:if test="${positive.positive_state==1}">
								总监审批中
							</c:if>
							<c:if test="${positive.positive_state==2}">
								人力总监审批中
							</c:if>
							<c:if test="${positive.positive_state==3}">
								总经理审批中
							</c:if>
							<c:if test="${positive.positive_state==4}">
								未批准
							</c:if>
							<c:if test="${positive.positive_state==5}">
								已批准
							</c:if>
							</td>
							<td>
								<button type="button"
										onclick="to_check_positive(${positive.positive_id })"
										class="btn btn-success btn-xs">查看</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty positives.list }">
				<p class="zanwu" style="text-align: center; color: #53C1FE;">还没有人申请转正哦！</p>
			</c:if>
		</div>
		<div class="pages">
			<div class="whole left">
				<div class="go_page">
					<span>第</span><input type="text" id="page_num" /> <span>页</span> <a
						href="javascript:void(0)"
						onclick="positive_tiaozhuan_page(${positives.pages},${limit},'${employee_id }','${bumen_id }')"
						class="btn_go">GO</a>
				</div>
			</div>
			<ul class="pagination right">
				<li><a href="javascript:void(0)"
					onclick="positive_jump_page(1,${limit },'${employee_id }','${bumen_id }')"
					class="fa fa-fast-backward"></a></li>
				<c:if test="${positives.hasPreviousPage==true}">
					<li class="previous"
						onclick="positive_jump_page(${positives.pageNumber-1},${limit },'${employee_id }','${bumen_id }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${positives.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${positives.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="positive_jump_page(${page},${limit },'${employee_id }','${bumen_id }')"><a
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
							onclick="positive_jump_page(${positives.pageNumber},20,'${employee_id }','${bumen_id }')">20</a></li>
						<li><a href="#fakelink"
							onclick="positive_jump_page(${positives.pageNumber},50,'${employee_id }','${bumen_id }')">50</a></li>
						<li><a href="#fakelink"
							onclick="positive_jump_page(${positives.pageNumber},100,'${employee_id }','${bumen_id }')">100</a></li>
					</ul></li>
				<c:if test="${positives.hasNextPage==true}">
					<li class="next"
						onclick="positive_jump_page(${positives.pageNumber+1},${limit },'${employee_id }','${bumen_id }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
				<li
					onclick="positive_jump_page(${positives.pages},${limit },'${employee_id }','${bumen_id }')"><a
					href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
			</ul>
		</div>
	</div>