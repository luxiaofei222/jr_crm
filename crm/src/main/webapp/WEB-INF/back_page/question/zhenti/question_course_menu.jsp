<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/common/right_content.css" />
<link rel="stylesheet" type="text/css" href="/css/jiaowu/index/jiaowu.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script>
//展开二级菜单
function zhankai_menu(course_id,id){
	var type=$("#tubiao"+course_id).attr("class");
	if(type=="fa fa-plus"){//切换符号
		$("#tubiao"+course_id).removeClass("fa-plus");
		$("#tubiao"+course_id).addClass("fa-minus");
		$.get("/back_zhenti/get_sub_menu_list.jr",{
			'course_id':course_id,
			'id':id
		},function(data){
			$("#menu"+course_id).after(data);
		})
	}else{
		$("#tubiao"+course_id).removeClass("fa-minus");
		$("#tubiao"+course_id).addClass("fa-plus");
		$("#menu"+course_id).nextAll("#success"+id).remove();
	}
}
//跳转章节列表页面
function to_add_chapter_question(question_course_id){
	jiazaidonghua();
	$.post("/back_zhenti/get_zhenti_years.jr",{
		'pageNumber' : 1,
		'limit' : 20,
		'question_course_id':question_course_id
			},function(data){
		$("#conten_list").html(data);
	})
}
</script>
<div class="back_right" style="min-width: 900px;">
	<div class="back_r_top">
		<div class="left">历年真题</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<div class="operation">
			<div class="opera_left left">
			</div>
			<div class="opera_right right"></div>
		</div>

		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>课程名称</th>
						<th>科目数量</th>
						<th>真题数量</th>
						<th>添加时间</th>
						<th>操作</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty courseMenus }">
						<tbody>
							<c:forEach items="${courseMenus}" var="coursemenu" varStatus="vs">
								<c:if test="${vs.count%2 == '0' }">
									<tr class="active" id="menu${coursemenu.course_id}">
								</c:if>
								<c:if test="${vs.count%2 != '0' }">
									<tr id="menu${coursemenu.course_id}">
								</c:if>
								<td><label class="label label-success btn-primary"
									for="minimal-checkbox-1">${vs.index+1 }</label></td>
								<td>${coursemenu.course_name }</td>
								<td>${coursemenu.question_number }</td>
								<td>--</td>
								<td>--</td>
								<td>--</td>
								<td><button onclick="zhankai_menu(${coursemenu.course_id},${vs.index+1})"
										type="button" class="btn btn-inverse btn-xs zhang_down">
										<i style="font-size:10px" id="tubiao${coursemenu.course_id}" class="fa fa-plus"></i>
									</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
			</table>
			<c:if test="${ empty courseMenus }">
				<p class="zanwu">暂无章节练习课程分类信息</p>
			</c:if>
		</div>
	</div>
</div>