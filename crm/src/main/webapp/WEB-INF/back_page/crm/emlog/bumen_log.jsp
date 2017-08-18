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
function emlog_jump_page(page,limit,employee_id,start_str,end_str,bumen_id ){
	jiazaidonghua();
	$.post("/em_log/get_bumen_emlog.jr",{
		 'employee_id':employee_id,
		 'start_str':start_str,
		 'end_str':end_str,
		 'bumen_id':bumen_id,
		'pageNumber' : page,
		'limit' : limit
	},function(data){
		$("#conten_list").html(data);
	})
}
//跳转页面
function task_tiaozhuan_page(page,limit,employee_id,start_str,end_str,bumen_id){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/em_log/get_bumen_emlog.jr", {
				 'employee_id':employee_id,
				 'start_str':start_str,
				 'end_str':end_str,
				 'pageNumber' : page_num,
				 'bumen_id':bumen_id,
				 'limit' : limit
				}, function(data) {
					$("#conten_list").html(data);
				}) 
		}
	}else{
		layer.msg("请输入数字！")
	}
}
/*调用 laydate*/
$(function(){
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  var start = {
			festival: true
		    ,istoday: true 
		  };
		 document.getElementById('ruzhi_time_start').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  }
		 document.getElementById('ruzhi_time_end').onclick = function(){
			    start.elem = this;
			    laydate(start);
			  }
		});
})
//筛选条件
function shaixuan_tioajian(obj,limit){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("筛选中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/em_log/get_bumen_emlog.jr",
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
//查看日志详情
function to_check_log(log_id){
	 layer.open({
		  type: 2,
		  title: ['日志详情'],
		  area: ['850px', '500px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/em_log/to_check_log.jr?log_id="+log_id
		  });
}
//选择员工
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
		<div class="left">员工日志</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<c:if test="${not empty organizations }">
				<div class="col-xs-2">
					<select class="form-control" name="bumen_id" id="bumen_id" onchange="get_employees()"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${organizations }" var="organ">
							<option value="${organ.organization_id }">${organ.organization_name }</option>
						</c:forEach>
					</select>
				</div>
				</c:if>
				<label class="col-xs-1 right_wz">发布人：</label>
				<div class="col-xs-2">
					<select class="form-control" name="employee_id" id="employee_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${employees }" var="em">
						<option value="${em.employee_id }">${em.employee_name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">截止时间从：</label>
				<div class="col-xs-2">
					<input class="layui-input" name="start_str"
						id="ruzhi_time_start">
				</div>
				<label class="col-xs-1 right_wz">到：</label>
				<div class="col-xs-2">
					<input class="layui-input" name="end_str"
						id="ruzhi_time_end">
				</div>
				<button class="shaixuan" onclick="shaixuan_tioajian(this,${limit})">筛选</button>
				</div>
		</form>
		<div class="operation">
			<div class="opera_left left">
				<!--左侧按钮  -->
			</div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>发布人</th>
						<th>部门</th>
						<th>发布日期</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:if test="${not empty emLogs.list }">
					<tbody>
						<c:forEach items="${emLogs.list }" var="log" varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+emLogs.begin }</label></td>
							<td>${log.employee.employee_name }</td>
							<td>${log.bumen }</td>
							<td><fmt:formatDate  pattern="yyyy-MM-dd"
									value="${log.tijiao_time }" /></td>
							<td>
								<button type="button"
										onclick="to_check_log(${log.log_id })"
										class="btn btn-success btn-xs">查看</button>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty emLogs.list }">
				<p class="zanwu" style="text-align: center; color: #53C1FE;">你们部门还没有人发布日志，赶紧去催促吧！</p>
			</c:if>
		</div>
		<div class="pages">
			<div class="whole left">
				<div class="go_page">
					<span>第</span><input type="text" id="page_num" /> <span>页</span> <a
						href="javascript:void(0)"
						onclick="task_tiaozhuan_page(${emLogs.pages},${limit})"
						class="btn_go">GO</a>
				</div>
			</div>
			<ul class="pagination right">
				<li><a href="javascript:void(0)"
					onclick="emlog_jump_page(1,${limit },'${employee_id }','${start_str }','${end_str }','${bumen_id }')"
					class="fa fa-fast-backward"></a></li>
				<c:if test="${emLogs.hasPreviousPage==true}">
					<li class="previous"
						onclick="emlog_jump_page(${emLogs.pageNumber-1},${limit },'${employee_id }','${start_str }','${end_str }','${bumen_id }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${emLogs.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${emLogs.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="emlog_jump_page(${page},${limit },'${employee_id }','${start_str }','${end_str }','${bumen_id }')"><a
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
							onclick="emlog_jump_page(${emLogs.pageNumber},20,'${employee_id }','${start_str }','${end_str }','${bumen_id }')">20</a></li>
						<li><a href="#fakelink"
							onclick="emlog_jump_page(${emLogs.pageNumber},50,'${employee_id }','${start_str }','${end_str }','${bumen_id }')">50</a></li>
						<li><a href="#fakelink"
							onclick="emlog_jump_page(${emLogs.pageNumber},100,'${employee_id }','${start_str }','${end_str }','${bumen_id }')">100</a></li>
					</ul></li>
				<c:if test="${emLogs.hasNextPage==true}">
					<li class="next"
						onclick="emlog_jump_page(${emLogs.pageNumber+1},${limit },'${employee_id }','${start_str }','${end_str }','${bumen_id }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
				<li
					onclick="emlog_jump_page(${emLogs.pages},${limit },'${employee_id }','${start_str }','${end_str }','${bumen_id }')"><a
					href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
			</ul>
		</div>
	</div>