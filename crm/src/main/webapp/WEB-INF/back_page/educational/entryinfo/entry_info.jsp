<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/crm/index/qiyeku.css" />
<link rel="stylesheet" href="/css/page/pages.css" />
<link rel="stylesheet" type="text/css" href="/css/jiaowu/index/jiaowu.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$(".pagination-dropdown").click(function(){
		$(this).children(".dropdown-menu").toggle();
	  })
})
//翻页上一页下一页
function info_jump_page(page,limit,entryUserName , documentNumber,bumen_id ,start_time ,end_time , entryplanId,course_id ,review_id ,parent_id ,employee_id){
	jiazaidonghua();
	$.post("/edu_entry/get_entry_info_main.jr",{
		'entryUserName':entryUserName,
		 'documentNumber':documentNumber,
		 'bumen_id':bumen_id,
		 'start_time':start_time,
		 'end_time':end_time,
		 'entryplanId':entryplanId,
		 'course_id':course_id,
		 'review_id':review_id,
		 'parent_id':parent_id,
		 'employee_id':employee_id,
		'pageNumber' : page,
		'limit' : limit
	},function(data){
		$("#conten_list").html(data);
	})
}
$(function(){
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  var start = {
			festival: true
		    ,istoday: true 
		  };
		 document.getElementById('end_time').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  }
		  document.getElementById('start_time').onclick = function(){
			    start.elem = this;
			    laydate(start);
			  }
		});
})
//学员审核
function to_examine_page(entryinfo_id){
	layer.open({
		type : 2,
		title : [ '报考信息审核' ],
		area : [ '1100px', '740px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/edu_entry/to_examine_page.jr?entryinfo_id="+entryinfo_id
	});
}
//查看学员信息
function to_check_page(entryinfo_id){
	layer.open({
		type : 2,
		title : [ '报考信息查看' ],
		area : [ '1100px', '740px' ],
		shadeClose : true, //点击遮罩关闭
		content : "/edu_entry/to_check_page.jr?entryinfo_id="+entryinfo_id
	});
}
//跳转页面
function info_tiaozhuan_page(page,limit,documentNumber,bumen_id ,start_time ,end_time , entryplanId,course_id ,review_id ,parent_id ,employee_id){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/edu_entry/get_entry_info_main.jr", {
				 'entryUserName':entryUserName,
				 'documentNumber':documentNumber,
				 'bumen_id':bumen_id,
				 'start_time':start_time,
				 'end_time':end_time,
				 'entryplanId':entryplanId,
				 'course_id':course_id,
				 'review_id':review_id,
				 'parent_id':parent_id,
				 'employee_id':employee_id,
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
			url : "/edu_entry/get_entry_info_main.jr",
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
</script>
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
	background: #f15151;
	border: none;
	color: white;
	border-radius: 5px;
	margin: 0 auto;
	margin-top: 10px;
}
</style>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">学员审核</div>
		<div class="right" id="timer"></div>
	</div>
		<div class="right_content">
			<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">学员姓名：</label>
				<div class="col-xs-2">
					<input class="form-control" id="entryUserName" name="entryUserName"
						placeholder="输入学员姓名">
				</div>
				<label class="col-xs-1 right_wz">身份证号：</label>
				<div class="col-xs-2">
					<input class="form-control" id="documentNumber"
						name="documentNumber" placeholder="输入身份证号">
				</div>
				<label class="col-xs-1 right_wz">报名时间从：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择报名开始时间" name="start_time"
						id="start_time">
				</div>
				<label class="col-xs-1 right_wz">到：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择报名结束时间" name="end_time"
						id="end_time">
				</div>
			</div>
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">一级分类：</label>
				<script>
				function get_entry_plan_parent() {
					var parent_id = $("#parent_id").val();
					if (parent_id != 0) {
						$.post("/edu_entry/get_sub_couse.jr", {
							'parent_id' : parent_id
						}, function(data) {
							$("#course_id").html(data);
							if (parent_id == 9) {
								var course_id = $("#course_id").val();
								$.post("/edu_entry/get_sub_review.jr", {
									'course_id' : course_id
								}, function(data) {
									$("#review_id").html(data);
								})
							}
						})
					} 
				}
				</script>
				<div class="col-xs-2">
					<select class="form-control" name="parent_id" id="parent_id"
					onchange="get_entry_plan_parent()"	style="line-height: 35px; height: 35px; padding: 0px;">
					<option value="0">请选择</option>
					<c:forEach items="${course_list }" var="course">
						<option value="${course.parent_id }">${course.coursename }</option>
					</c:forEach>
					</select>
				</div>
				<label class="col-xs-1 right_wz">二级分类：</label>
				<script>
				//获取计划
				function get_entry_plan(obj) {
					var course_id_plan = $(obj).val();
					var parent_id = $("#parent_id").val();
					if (course_id_plan != 0) {
						if (parent_id != 9) {
							$.post("/edu_entry/get_sub_plan.jr", {
								'course_id_plan' : course_id_plan
							}, function(data) {
								$("#entryplanId").html(data);
							})
						} else {
							var course_id = $("#course_id").val();
							$.post("/edu_entry/get_sub_review.jr", {
								'course_id' : course_id
							}, function(data) {
								$("#review_id").html(data);
							})
						}
					} 
				}
				</script>
				<div class="col-xs-2">
					<select class="form-control" name="course_id" id="course_id" onchange="get_entry_plan(this)"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="0">请选择</option>
						<c:forEach items="${entryPlans }" var="plan">
							<option value="${plan.entryplan_id }">${plan.entryplan_content }</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-xs-1 right_wz">三级分类：</label>
				<script>
				//获取职称评审计划
				function get_entry_review_plan() {
					var course_id_review = $("#review_id").val();
					if (course_id_review != 0) {
						$.post("/edu_entry/get_sub_review_plan.jr", {
							'review_id' : course_id_review
						}, function(data) {
							$("#entryplanId").html(data);
						})
					}
				}
				</script>
				<div class="col-xs-2">
					<select class="form-control" name="review_id" id="review_id" onchange="get_entry_review_plan()"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="0">请选择</option>
					</select>
				</div>
				<label class="col-xs-1 right_wz">报名计划：</label>
				<div class="col-xs-2">
					<select class="form-control" name="entryplanId" id="entryplanId"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="0">请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">所在部门：</label>
				<script>
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
				<div class="col-xs-2">
					<select class="form-control" name="bumen_id" id="bumen_id" onchange="get_employees()"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
					<option value="6">市场一部</option>
					<option value="7">市场二部</option>
					<option value="8">市场三部</option>
					<option value="9">市场四部</option>
					<option value="10">市场五部</option>
					<option value="11">市场六部</option>
					<option value="12">市场七部</option>
					</select>
				</div>
				<label class="col-xs-1 right_wz">业务员：</label>
				<div class="col-xs-2">
					<select class="form-control" name="employee_id" id="employee_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
					</select>
				</div>
			</div>
			<div style="text-align: center;">
				<button class="shaixuan" onclick="shaixuan_tioajian(this,${limit})">筛选</button>
			</div>
		</form>
			<div class="operation">
				<div class="opera_left left">
				<!--左侧按钮  -->
				</div>
				<div class="opera_right right">
				<%-- <select class="form-control select left top_select"
						data-toggle="select" id="select_val">
						<option value="0">请选择查询条件</option>
						<option value="order_number">订单号</option>
					</select> <input type="text" id="check_val" placeholder="请输入查询内容"
						class="form-control left top_search" />
					<button type="button" onclick="seacher_info(${limit})"
						class="btn left btn-primary">查询</button> --%>
				</div>
			</div>
			<div class="content_message">
				<table class="table table-hover">
					<thead>
						<tr class="tr_bgcolor warning">
							<th>编号</th>
							<!-- <th>订单号</th> -->
							<th>业务员</th>
							<th>学员姓名</th>
							<th>学员手机号</th>
							<th>报考类别</th>
							<th>报考专业</th>
							<th>缴费状态</th>
							<th>审核状态</th>
							<th>退费状态</th>
							<th>报名时间</th>
							<th>操作</th>
						</tr>
					</thead>
				<c:if test="${not empty entryInfos.list }">
				<tbody>
				<c:forEach items="${entryInfos.list }" var="info" varStatus="vs">
                    <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+entryInfos.begin }</label></td>
							<%-- <td>${info.orderNumber }</td> --%>
							<td>
							<c:if test="${not empty info.employee.employee_name }">
							${info.employee.employee_name }
							</c:if>
							<c:if test="${empty info.employee.employee_name }">
							暂无业务员
							</c:if>
							</td>
							<td>
							${info.entryUserName }
							</td>
							<td>${info.entryUserPhone }</td>
							<td>${info.courseMenu.course_name }</td>
							<td>
							<c:if test="${info.courseMenu.course_id==20 }">
							${info.xuexiao }-${info.zhuanye }
							</c:if>
									<c:if test="${info.courseMenu.course_id==19 }">
							${info.baokaocengci }-${info.baokaokemu }
							</c:if>
							<c:if test="${info.courseMenu.course_id!=20&&info.courseMenu.course_id!=19 }">
							${info.dictionary.dictionary_name }
							</c:if>							
							</td>
							<td>
							<c:if test="${info.isPay==1 }">
							已付款
							</c:if>
							<c:if test="${info.isPay!=1 }">
							未缴费
							</c:if>
							</td>
							<td style="color: orange;">
							<c:if test="${info.entryInfoState==3 }">
							审核通过
							</c:if>
							<c:if test="${info.entryInfoState==2 }">
							待审核
							</c:if>
							<c:if test="${info.entryInfoState==5 }">
							审核失败
							</c:if>
							</td>
							<td>
							<c:if test="${info.tuifei_state==0 }">
								未退费
							</c:if>
							<c:if test="${info.tuifei_state==1 }">
								经理审核中
							</c:if>
							<c:if test="${info.tuifei_state==2 }">
								总监审核中
							</c:if>
							<c:if test="${info.tuifei_state==3 }">
								总经理审核中
							</c:if>
							<c:if test="${info.tuifei_state==4 }">
								财务审核中
							</c:if>
							<c:if test="${info.tuifei_state==5 }">
								退费成功
							</c:if>
							<c:if test="${info.tuifei_state==6 }">
								拒绝退费
							</c:if>
							</td>
							<td><fmt:formatDate type="both" value="${info.entryInfoTime }" /></td>
							<td>
							<c:if test="${info.entryInfoState ==2}">
							<button type="button" onclick="to_examine_page(${info.entryInfoId })" class="btn btn-success btn-xs">审核</button>
							</c:if>
							<c:if test="${info.entryInfoState ==3}">
							<button type="button" onclick="to_check_page(${info.entryInfoId })" class="btn btn-success btn-xs">查看</button>
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</c:if>
				</table>
				<c:if test="${ empty entryInfos.list }">
				<p class="zanwu" style="text-align:center;color:#53C1FE;">暂无报考学员信息</p>
				</c:if>
			</div>
			<div class="pages">
			    <div class="whole left">
					<div class="go_page">
					  <span>跳转到第</span><input type="text" id="page_num" />
					  <span>页</span>
					  <a href="javascript:void(0)" onclick="info_tiaozhuan_page(${entryInfos.pages},${limit})" class="btn_go">GO</a>
					</div>
				</div>
				<ul class="pagination right">
				 <li><a href="javascript:void(0)" onclick="info_jump_page(1,${limit })" class="fa fa-fast-backward"></a></li>
					<c:if test="${entryInfos.hasPreviousPage==true}">
						<li class="previous"
							onclick="info_jump_page(${entryInfos.pageNumber-1},${limit })"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${entryInfos.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${entryInfos.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="info_jump_page(${page},${limit })"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					 <li class="pagination-dropdown dropup">
                <a href="#fakelink" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fui-triangle-up"></i>
                </a>
                <ul class="dropdown-menu">
                  <li><a href="#fakelink" onclick="info_jump_page(${entryInfos.pageNumber},20)">20</a></li>
                  <li><a href="#fakelink" onclick="info_jump_page(${entryInfos.pageNumber},50)">50</a></li>
                  <li><a href="#fakelink" onclick="info_jump_page(${entryInfos.pageNumber},100)">100</a></li>
                </ul>
              </li>
					<c:if test="${entryInfos.hasNextPage==true}">
						<li class="next" onclick="info_jump_page(${entryInfos.pageNumber+1},${limit })"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
					<li onclick="info_jump_page(${entryInfos.pages},${limit })"><a href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
				</ul>
			</div>
		</div>
	