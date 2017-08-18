<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/crm/index/qiyeku.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="/css/jiaowu/index/jiaowu.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
.select2-chosen {
	color: white !important;
}
</style>
<script type="text/javascript">
//翻页上一页下一页
function plan_jump_page(page,entryplan_content,entryplan_explain,parent_id,course_id){
	jiazaidonghua();
	$.post("/edu_entry_plan/get_entry_plan_main.jr",{
		'entryplan_explain':entryplan_explain,
		'parent_id':parent_id,
		'course_id':course_id,
		'pageNumber' : page,
		'limit' : 20,
		'entryplan_content':entryplan_content
	},function(data){
		$("#conten_list").html(data);
	})
}

//筛选条件
function shaixuan_tioajian(obj){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("筛选中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/edu_entry_plan/get_entry_plan_main.jr",
			data:{
				'pageNumber':1,
				'limit':20
			},
			success : function(data) {
				$("#conten_list").html(data);
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('查询失败');
			}
		});
}
//添加报考计划
function to_add_entry_plan(page){
	layer.open({
		type : 2,
		title : [ '添加报考计划' ],
		area : [ '700px', '550px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/edu_entry_plan/to_entry_plan.jr"
	});
}
//添加申报条件
function to_add_condition(entryplan_id){
	layer.open({
		type : 2,
		title : [ '添加申报条件' ],
		area : [ '700px', '600px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/edu_condition/to_add_condition.jr?entryplan_id="+entryplan_id
	});
}
//修改计划是否显示
function update_ishow(entryplan_id,type,page,entryplan_content,entryplan_explain,parent_id,course_id){
	$.post("/edu_entry_plan/update_ishow.jr",{
		'entryplan_id':entryplan_id,
		'is_show':type
	},function(data){
		if(data==1){
			tanchuang("设置成功！");
			plan_jump_page(page,entryplan_content,entryplan_explain,parent_id,course_id);
		}else{
			tanchuang("设置失败！");
		}
	})
}
//修改计划内容弹窗
function to_update_plan(entryplan_id,page){
	layer.open({
		type : 2,
		title : [ '修改报考计划' ],
		area : [ '700px', '550px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/edu_entry_plan/to_update_plan.jr?entryplan_id="+entryplan_id
	});
}
//查看申报条件
function to_check_condition(entryplan_id){
	layer.open({
		type : 2,
		title : [ '查看申报条件' ],
		area : [ '1100px', '600px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/edu_condition/to_check_condition.jr?entryplan_id="+entryplan_id
	});
}
//获取二级分类
function get_sub_course(){
	var course_parent_id=$("#parent_id").val();
	$.post("/edu_entry_plan/get_sub_course.jr",{
		'course_id':course_parent_id
	},function(data){
		$("#course_id").html(data);
	})
	
}
</script>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">报考计划</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<div class="operation" style="height:95px;">
			<form class="form-horizontal" style="padding-left:35px;" id="myform">
				<div class="form-group clearfix" style="margin-bottom: 5px;">
					<label class="left">类别：</label>
					<div class="col-xs-2">
						<select class="form-control" id="parent_id" name="parent_id"
							onchange="get_sub_course()"
							style="line-height: 35px; height: 35px; padding: 0px;">
							<option value="">请选择</option>
							<c:forEach items="${courseMenus }" var="course">
								<option value="${course.course_id }">${course.course_name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-xs-2">
						<select class="form-control" id="course_id" name="course_id"
							style="line-height: 35px; height: 35px; padding: 0px;">
							<option value="">请选择</option>
						</select>
					</div>
					<label class="left">计划内容：</label>
					<div class="col-xs-2">
						<input class="form-control" id="entryplan_content"
							name="entryplan_content" placeholder="输入计划内容">
					</div>
					<label class="left">计划说明：</label>
					<div class="col-xs-2">
						<input class="form-control" id="entryplan_explain"
							name="entryplan_explain" placeholder="输入计划说明">
					</div>
					<div class="col-xs-2">
						<button type="button" class="btn"
							onclick="shaixuan_tioajian(this)"
							style="background-color: #53C1FE; color: white;">筛选</button>
					</div>
				</div>
			</form>
			<div class="opera_left left">
				<button type="button"
					onclick="to_add_entry_plan(${entryPlans.pageNumber})"
					class="btn left" id="add_company"
					style="background-color: #53C1FE; color: white;">
					<i class="fa fa-plus"></i>添加计划
				</button>
			</div>
			<div class="opera_right right">
				<!-- 右侧按钮 -->
			</div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>计划内容</th>
						<th>说明</th>
						<th>截至日期</th>
						<th>报名人数</th>
						<th>计划状态</th>
						<th>添加时间</th>
						<th>计划</th>
						<th>申报条件</th>
					</tr>
				</thead>
				<c:if test="${not empty entryPlans.list }">
					<tbody>
						<c:forEach items="${entryPlans.list }" var="plan" varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+entryPlans.begin }</label></td>
							<td>${plan.entryplan_content }</td>
							<td><c:if test="${not empty plan.entryplan_explain }">
							${plan.entryplan_explain }
							</c:if> <c:if test="${empty plan.entryplan_explain }">
							--
							</c:if></td>
							<td><fmt:formatDate value="${plan.validity_time }" /></td>
							<td>${plan.baomingnumber }</td>
							<td>${plan.time_type }</td>
							<td><fmt:formatDate type="both"
									value="${plan.entryplan_time }" /></td>
							<td><c:if test="${plan.is_show=='隐藏' }">
									<button type="button"
										onclick="update_ishow(${plan.entryplan_id },'显示',${entryPlans.pageNumber},'${entryplan_content }','${entryplan_explain }','${parent_id }','${course_id }')"
										class="btn btn-info btn-xs">显示</button>
								</c:if> <c:if test="${plan.is_show=='显示' }">
									<button type="button"
										onclick="update_ishow(${plan.entryplan_id },'隐藏',${entryPlans.pageNumber},'${entryplan_content }','${entryplan_explain }','${parent_id }','${course_id }')"
										class="btn btn-default btn-xs">隐藏</button>
								</c:if>
								<button type="button"
									onclick="to_update_plan(${plan.entryplan_id },${entryPlans.pageNumber})"
									class="btn btn-danger btn-xs">修改计划</button></td>
							<td>
								<button type="button"
									onclick="to_add_condition(${plan.entryplan_id })"
									class="btn btn-success btn-xs">添加申报条件</button>
								<button type="button"
									onclick="to_check_condition(${plan.entryplan_id })"
									class="btn btn-warning btn-xs">查看申报条件</button>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty entryPlans.list }">
				<p class="zanwu" style="text-align: center; color: #53C1FE;">暂无报名计划内容</p>
			</c:if>
		</div>
		<div class="pages">
			<ul class="pagination right">
				<c:if test="${entryPlans.hasPreviousPage==true}">
					<li class="previous"
						onclick="plan_jump_page(${entryPlans.pageNumber-1},'${entryplan_content }','${entryplan_explain }','${parent_id }','${course_id }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${entryPlans.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${entryPlans.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li onclick="plan_jump_page(${page},'${entryplan_content }','${entryplan_explain }','${parent_id }','${course_id }')"><a href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${entryPlans.hasNextPage==true}">
					<li class="next"
						onclick="plan_jump_page(${entryPlans.pageNumber+1},'${entryplan_content }','${entryplan_explain }','${parent_id }','${course_id }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
			</ul>
		</div>
	</div>