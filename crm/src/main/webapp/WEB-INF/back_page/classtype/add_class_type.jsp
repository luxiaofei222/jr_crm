<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>添加班型介绍</title>
</head>
<style type="text/css">
.form-group .right_wz{
		text-align:right;
}
</style>
<script>
	//关闭弹窗
	function close_layer() {
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
	}
	//获取二级分类
	function get_sub_course() {
		var course_parent_id = $("#course_parent_id").val();
		$.post("/back_user/get_sub_course.jr", {
			'course_id' : course_parent_id
		}, function(data) {
			$("#course_id").html(data);
		})
	}
	//添加班型
	function add_class_type() {
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_class/save_class_type.jr",
			success : function(data) {
				if (data == 1) {
					tanchuang("提示：恭喜您，添加班型成功！");
					$('#myform')[0].reset();
					$('#summernote').summernote('code', "");
				} else if (data == 2) {
					layer.msg("提示：该课程的班型已经添加过，不可重复添加！");
				} else {
					layer.msg("提示：很遗憾，添加失败！");
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				layer.msg("添加失败！");
			}
		});
	}
</script>
<body>
	<form enctype="multipart/form-data" id="myform" style="padding: 20px;">
		<div class="form-group" style="overflow:hidden;margin-top:20px;">
			<label class="col-xs-3 control-label right_wz">课程分类：</label>
			<div class="col-xs-8">
				<select onchange="get_sub_course()" id="course_parent_id"
					name="course_parent_id" class="form-control select select-primary">
					<c:forEach items="${courseMenus }" var="cou_menu">
						<option value="${cou_menu.course_id }">${cou_menu.course_name }</option>
					</c:forEach>
				</select>
			</div>
			</div>
			<div class="form-group" style="overflow:hidden;margin-top:20px;">
			<label class="col-xs-3 control-label right_wz">选择课程：</label>
			<div class="col-xs-8">
				<select id="course_id" name="course_id"
					class="form-control select select-primary">
					<c:forEach items="${coursesubMenus }" var="cou_menu">
						<option value="${cou_menu.course_id }">${cou_menu.course_name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group" style="overflow:hidden;margin-top:20px;">
			<label class="col-xs-3 control-label right_wz">班型名称：</label>
			<div class="col-xs-8">
				<select id="dictionary_id" name="dictionary_id"
					class="form-control select select-primary">
					<c:forEach items="${dictionaries }" var="dic">
						<option value="${dic.dictionary_id }">${dic.dictionary_name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group" style="overflow:hidden;margin-top:20px;">
			<label class="col-xs-3 control-label right_wz">名称备注：</label>
			<div class="col-xs-8">
				<input class="form-control" id="class_beizhu" name="class_beizhu"
					placeholder="输入备注">
			</div>
		</div>
		<div class="form-group" style="overflow:hidden;margin-top:20px;">
			<label class="col-xs-3 control-label right_wz">班级特色：</label>
			<div class="col-xs-8">
				<input class="form-control" id="class_feature" name="class_feature"
					placeholder="输入班级特色">
			</div>
		</div>
		<div class="form-group" style="overflow:hidden;margin-top:20px;">
			<label class="col-xs-3 control-label right_wz">内容特色：</label>
			<div class="col-xs-8">
				<input class="form-control" id="neirong_feature"
					name="neirong_feature" placeholder="输入内容特色">
			</div>
		</div>
		<div class="form-group" style="overflow:hidden;margin-top:20px;">
			<div class="col-xs-6 right_wz">
				<button type="button" onclick="close_layer()"
					class="btn btn-warning btn-lg" style="width: 100px;">取消</button>
			</div>
			<div class="col-xs-6" style="text-align: left;">
				<button type="button" onclick="add_class_type()"
					class="btn btn-primary btn-lg" style="width: 100px;">保存</button>
			</div>
		</div>
	</form>
</body>
</html>