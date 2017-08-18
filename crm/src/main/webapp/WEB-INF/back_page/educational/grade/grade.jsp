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
<script type="text/javascript">
//翻页上一页下一页
function plan_jump_page(page,course_parent_id ,course_id ,user_name ,user_phone ,user_card ,user_zhunkao ){
	jiazaidonghua();
	$.post("/user_grade/get_user_grade.jr",{
		'course_parent_id':course_parent_id,
		'course_id':course_id,
		'user_name':user_name,
		'user_phone':user_phone,
		'user_card':user_card,
		'user_zhunkao':user_zhunkao,
		'pageNumber' : page,
		'limit' : 20
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
			url : "/user_grade/get_user_grade.jr",
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
//添加学员成绩
function to_add_user_grade(){
	layer.open({
		type : 2,
		title : [ '添加学员成绩' ],
		area : [ '900px', '650px' ],
		shadeClose : true, //点击遮罩关闭
		content : "/user_grade/to_add_user_grade.jr"
	});
}
//查看学员成绩
function to_check_grade(grade_id){
	layer.open({
		type : 2,
		title : [ '查看学员成绩' ],
		area : [ '1000px', '600px' ],
		shadeClose : true, //点击遮罩关闭
		content : "/user_grade/to_check_user_grade.jr?grade_id="+grade_id
	});
}
//删除成绩
function to_delete_grade(page,grade_id){
	layer.confirm("提示：您好，确定要删除吗？",function(){
		layer.closeAll('dialog');
		$.post("/user_grade/delete_user_grade.jr",{
			'grade_id':grade_id
		},function(data){
			if(data==1){
				tanchuang("删除成功！");
				plan_jump_page(page);
			}else{
				tanchuang("删除失败！");
			}
		});
		})
}
//获取二级分类
function get_sub_course(){
	var course_parent_id=$("#course_parent_id").val();
	$.post("/edu_entry_plan/get_sub_course.jr",{
		'course_id':course_parent_id
	},function(data){
		$("#course_id").html(data);
	})
	
}
</script>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">学员成绩</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
			<form class="form-horizontal" style="padding-left:35px;" id="myform">
				<div class="form-group clearfix" style="padding-top: 10px; margin-bottom: 5px;">
					<label class="col-xs-1 wz_right">类别：</label>
					<div class="col-xs-2">
						<select class="form-control" id="course_parent_id" name="course_parent_id"
							onchange="get_sub_course()"
							style="line-height: 35px; height: 35px; padding: 0px;">
							<option value="">请选择</option>
							<c:forEach items="${courseMenus }" var="course">
								<option value="${course.course_id }">${course.course_name }</option>
							</c:forEach>
						</select>
					</div>
					<label class="col-xs-1 wz_right">二级类别：</label>
					<div class="col-xs-2">
						<select class="form-control" id="course_id" name="course_id"
							style="line-height: 35px; height: 35px; padding: 0px;">
							<option value="">请选择</option>
						</select>
					</div>
					<label class="col-xs-1 wz_right">姓名：</label>
					<div class="col-xs-2">
						<input class="form-control" id="user_name"
							name="user_name" placeholder="输入学员姓名">
					</div>
					<label class="col-xs-1 wz_right">手机号：</label>
					<div class="col-xs-2">
						<input class="form-control" id="user_phone"
							name="user_phone" placeholder="输入学员手机号">
					</div>
				</div>
				<div class="form-group clearfix" style="margin-bottom: 5px;">
				<label class=" col-xs-1 wz_right">身份证号：</label>
					<div class="col-xs-2">
						<input class="form-control" id="user_card"
							name="user_card" placeholder="输入学员身份证号">
					</div>
				<label class="col-xs-1 wz_right">准考证号：</label>
					<div class="col-xs-2">
						<input class="form-control" id="user_zhunkao"
							name="user_zhunkao" placeholder="输入学员身份证号">
					</div>
				<div class="col-xs-2">
						<button type="button" class="btn"
							onclick="shaixuan_tioajian(this)"
							style="background-color: #53C1FE; color: white;">筛选</button>
					</div>
				</div>
			</form>
		<div class="operation">
			<div class="opera_left left">
				<button type="button"
					onclick="to_add_user_grade()"
					class="btn left" id="add_company"
					style="background-color: #53C1FE; color: white;">
					<i class="fa fa-plus"></i>添加
				</button>
			</div>
			<div class="opera_right right">
				<!-- 右侧按钮 -->
			</div>
			<div style="clear:both;"></div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>所属分类</th>
						<th>等级/学校</th>
						<th>专业</th>
						<th>姓名</th>
						<th>性别</th>
						<th>手机号</th>
						<th>身份证号</th>
						<th>准考证号</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:if test="${not empty userGrades.list }">
					<tbody>
						<c:forEach items="${userGrades.list }" var="grade" varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+userGrades.begin }</label></td>
							<td>${grade.courseMenu.course_name }</td>
							<td><c:if test="${not empty grade.dictionary }">
							${grade.dictionary.dictionary_name }
							</c:if> <c:if test="${not empty grade.xuexiao }">
							${grade.xuexiao }
							</c:if></td>
							<td><c:if test="${empty grade.zhuanye }">
							--
							</c:if>${grade.zhuanye }</td>
							<td>${grade.user_name }</td>
							<td>${grade.user_sex }</td>
							<td>${grade.user_phone }</td>
							<td>${grade.user_card }</td>
							<td>${grade.user_zhunkao }</td>
							<td>
								<button type="button"
									onclick="to_check_grade(${grade.grade_id })"
									class="btn btn-success btn-xs">查看</button>
								<button type="button"
									onclick="to_delete_grade(${userGrades.pageNumber},${grade.grade_id })"
									class="btn btn-warning btn-xs">删除</button>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty userGrades.list }">
				<p class="zanwu" style="text-align: center; color: #53C1FE;">暂无学员成绩信息</p>
			</c:if>
		</div>
		<div class="pages">
			<ul class="pagination right">
				<c:if test="${userGrades.hasPreviousPage==true}">
					<li class="previous"
						onclick="plan_jump_page(${userGrades.pageNumber-1},'${course_parent_id }','${course_id }','${user_name }','${user_phone }','${user_card }','${user_zhunkao }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${userGrades.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${userGrades.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li onclick="plan_jump_page(${page},'${course_parent_id }','${course_id }','${user_name }','${user_phone }','${user_card }','${user_zhunkao }')"><a href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${userGrades.hasNextPage==true}">
					<li class="next"
						onclick="plan_jump_page(${userGrades.pageNumber+1},'${course_parent_id }','${course_id }','${user_name }','${user_phone }','${user_card }','${user_zhunkao }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
			</ul>
		</div>
	</div>