<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/modules/laydate/laydate.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script type="text/javascript" src='/js/system/distpicker.js'></script>
<script type="text/javascript" src='/js/system/main.js'></script>
<style>
form .form-group label {
	font-size: 15px;
	color: #444;
}

form .form-group input:focus, form .form-group textarea:focus {
	border-color: #e74c3c;
}

form .form-group .btn {
	margin-right: 30px;
}
</style>
<script>
	//修改客户信息
	function update_conult_info(obj,phone,consult_id) {
		if (check_UserPhone(phone)) {
			$(obj).attr({
				"disabled" : "disabled"
			});
			$("#myform").ajaxSubmit({
				type : 'POST',
				url : "/net_conult/update_conult.jr",
				data:{'consult_id':consult_id},
				success : function(data) {
					if (data == 1) {
						//修改数据成功，关闭弹出窗之前，刷新列表页面的数据
						tanchuang('恭喜您，修改成功');
						$(obj).removeAttr("disabled");
					} else {
						tanchuang('很遗憾，修改失败');
						$(obj).removeAttr("disabled");
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					tanchuang('很遗憾，修改失败');
					alert(errorThrown);
					$(obj).removeAttr("disabled");
				}
			});
		}
	}
	//验证手机号
	function check_UserPhone(phone){
		var user_phone = $("#user_phone").val();
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
		if (user_phone != null && user_phone != "") {
			if(phone==user_phone){
				return true;
			}else{
				if (!myreg.test(user_phone)) {
					layer.msg('请输入有效的手机号码！');
					return false;
				} else {
					var bol = false;
					$.ajax({
						type : "POST",
						url : "/net_conult/check_UserPhone.jr?user_phone="
								+ user_phone,
						async : false,
						success : function(data) {
							if (data == 1) {
								bol = true;
							} else {
								layer.msg('你输入的手机号已存在！');
								bol = false;
							}
						}
					});
					return bol;
				}
			}
		} else {
			//layer.msg("请输入您的手机号！");
			return true;
		}
	}
	
	function get_sub_course() {
		var course_parent_id = $("#course_parent_id").val();
		$.post("/back_user/get_sub_course.jr", {
			'course_id' : course_parent_id
		}, function(data) {
			$("#course_id").html(data);
			if (course_parent_id == 9) {//显示职称评审3级
				var course_id = $("#course_id").val();
				$.post("/edu_entry_plan/get_review_list.jr", {
					'course_id' : course_id
				}, function(data) {
					$("#review_id").html(data);
				})
			}
		})
	}
//获取职称列表
	function get_review_list() {
		var course_id = $("#course_id").val();
		var course_parent_id = $("#course_parent_id").val();
		if (course_parent_id == 9) {//显示职称评审3级
			$.post("/edu_entry_plan/get_review_list.jr", {
				'course_id' : course_id
			}, function(data) {
				$("#review_id").html(data);
			})
		} else {
			$("#review_id").html("<option value=''>请选择</option>");
		}
		if(course_parent_id!=18){
			$.post("/back_video/get_dic_list.jr",{
				'course_id':course_id,
				'course_parent_id':course_parent_id,
				'type':2
			},function(data){
				$("#dictionary_id").html(data);
			})
		}else{
			$("#dictionary_id").html("<option value=''>请选择</option>");
		}
	}
	
</script>
<form class="form-horizontal add_qiye_dialog"
	enctype="multipart/form-data" id="myform" style="padding: 21px;">
		<div class="form-group">
		<label class="control-label col-xs-2">咨询时间：</label>
		<div class="col-xs-10">
			 <input class="layui-input" name="zixun_time_str" value='<fmt:formatDate value="${netConult.zixun_time }"/>' placeholder="自定义日期格式" onclick="layui.laydate({elem: this})">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-2">一级类别：</label>
		<div class=" col-xs-4">
			<select class="form-control" name="course_parent_id"
				id="course_parent_id" onchange="get_sub_course()">
					<c:if test="${not empty netConult.course_parent_id }">
					<option value="${netConult.course_parent_id}">${course_parent_name }</option>
					</c:if>
				<option value="">请选择</option>
				<c:forEach items="${courseMenus }" var="course">
					<option value="${course.course_id }">${course.course_name }</option>
				</c:forEach>
			</select>
		</div>
		<label class="control-label col-xs-2">二级类别：</label>
		<div class=" col-xs-4">
			<select name="course_id" id="course_id" onchange="get_review_list()"
				class="form-control">
					<c:if test="${not empty netConult.course_id }">
					<option value="${netConult.course_id}">${course_name }</option>
					</c:if>
				<option value="">请选择</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-2">职称：</label>
		<div class="col-xs-4">
			<select name="review_id" id="review_id" class="form-control">
			<c:if test="${not empty netConult.review_id }">
					<option value="${netConult.review_id}">${review_name }</option>
					</c:if>
				<option value="">请选择</option>
			</select>
		</div>
		<label class="control-label col-xs-2">级别：</label>
		<div class="col-xs-4">
			<select name="dictionary_id" id="dictionary_id" class="form-control col-xs-2">
					<c:if test="${not empty netConult.dictionary_id }">
					<option value="${netConult.dictionary_id}">${dictionary_name }</option>
					</c:if>
					<c:if test="${empty netConult.dictionary_id }">
					<option value="">请选择</option>
					</c:if>
			</select>
		</div>
	</div>
	<div class="form-group">
	<label class="control-label col-xs-2">顾问：</label>
		<div class="col-xs-4">
			<select name="employee_id" class="form-control col-xs-2">
					<c:if test="${not empty netConult.employee_id }">
					<option value="${netConult.employee_id}">${employee_name }</option>
					</c:if>
					<c:if test="${empty netConult.employee_id }">
					<option value="">请选择</option>
				<c:forEach items="${employees }" var="employee">
					<option value="${employee.employee_id }">${employee.employee_name }(${employee.call_number})</option>
				</c:forEach>
				</c:if>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-2">咨询渠道：</label>
		<div class='col-xs-4'>
			<select name="consult_type" class="form-control">
				<c:if test="${not empty netConult.consult_type }">
					<option value="${netConult.consult_type}">${netConult.consult_type }</option>
					</c:if>
				<option value="">请选择</option>
				<option value="53客服">53客服</option>
				<option value="百度商桥">百度商桥</option>
				<option value="离线宝">离线宝</option>
				<option value="QQ">QQ</option>
				<option value="来电咨询">来电咨询</option>
			</select>
		</div>
		<label class="control-label col-xs-2">搜索词：</label>
		<div class="col-xs-4">
			<input type="text" name="search_word" value="${netConult.search_word }" class="form-control"
				placeholder="请输入搜索词">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-2">目前学历：</label>
		<div class='col-xs-4'>
			<select class="form-control" name="now_edu" >
			<c:if test="${not empty netConult.now_edu }">
					<option value="${netConult.now_edu}">${netConult.now_edu }</option>
					</c:if>
				<option value="">请选择</option>
				<option value="初中">初中</option>
				<option value="中专">中专</option>
				<option value="高中">高中</option>
				<option value="专科">专科</option>
				<option value="本科">本科</option>
			</select>
		</div>
		<label class="control-label col-xs-2">意向学历：</label>
		<div class='col-xs-4'>
			<select class="form-control" name="hope_edu" >
			<c:if test="${not empty netConult.hope_edu }">
					<option value="${netConult.hope_edu}">${netConult.hope_edu }</option>
					</c:if>
				<option value="">请选择</option>
				<option value="专科">专科</option>
				<option value="本科">本科</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-2">意向学校：</label>
		<div class="col-xs-4">
			<input type="text" name="hope_sc"  value="${netConult.hope_sc}" class="form-control"
				placeholder="请输入意向学校">
		</div>
		<label class="control-label col-xs-2">专业：</label>
		<div class="col-xs-4">
			<input type="text" name="zhuanye" value="${netConult.zhuanye}"  class="form-control"
				placeholder="请输入专业">
		</div>
	</div>
	<div class="form-group">
		<div data-toggle="distpicker"> 
		<label class="control-label col-xs-2">地域：省</label>
		<div class='col-xs-3'>
			<select data-province='<c:if test="${empty netConult.province }">—— 省 ——</c:if><c:if test="${not empty netConult.province }">${netConult.province }</c:if>' class="form-control"   name="province" id="province">
			</select>
		</div>
		<label class="control-label col-xs-1">市</label>
		<div class='col-xs-3'>
			<select class="form-control" data-city='<c:if test="${empty netConult.city }">—— 市 ——</c:if><c:if test="${not empty netConult.city }">${netConult.city }</c:if>' name="city" id="city">
			</select>
		</div>
		<label class="control-label col-xs-1">县/区</label>
		<div class='col-xs-2'>
			<select class="form-control" data-district='<c:if test="${empty netConult.area }">—— 区 ——</c:if><c:if test="${not empty netConult.area }">${netConult.area }</c:if>' name="area" id="area">
			</select>
		</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-2">IP：</label>
		<div class="col-xs-4">
			<input type="text" value="${netConult.user_ip}" name="user_ip" class="form-control"
				placeholder="请输入ip">
		</div>
		<label class="control-label col-xs-2">姓名：</label>
		<div class="col-xs-4">
			<input type="text" value="${netConult.user_name}" name="user_name" class="form-control"
				placeholder="请输入姓名">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-2">性别：</label>
		<div class='col-xs-4'>
			<select class="form-control" name="user_sex">
			<c:if test="${not empty netConult.user_sex }">
					<option value="${netConult.user_sex}">${netConult.user_sex }</option>
					</c:if>
				<option value="未知">未知</option>
				<option value="男">男</option>
				<option value="女">女</option>
			</select>
		</div>
		<label class="control-label col-xs-2">电话：</label>
		<div class="col-xs-4">
			<input type="text" name="user_phone" value="${netConult.user_phone}" id="user_phone" onblur="check_UserPhone('${netConult.user_phone}')" class="form-control"
				placeholder="请输入客户电话">
		</div>
	</div>
		<div class="form-group">
		<label class="control-label col-xs-2">QQ：</label>
		<div class="col-xs-4">
			<input type="text" name="user_qq" value="${netConult.user_qq}" class="form-control"
				placeholder="请输入QQ">
		</div>
		<label class="control-label col-xs-2">微信：</label>
		<div class="col-xs-4">
			<input type="text" name="user_weixin"  value="${netConult.user_weixin}"  class="form-control"
				placeholder="请输入微信">
		</div>
	</div>
		<div class="form-group">
		<label class="control-label col-xs-2">问题描述：</label>
		<div class="col-xs-4">
			 <textarea class="form-control" name="question_info" rows="3">${netConult.question_info}</textarea>
		</div>
		<label class="control-label col-xs-2">备注：</label>
		<div class="col-xs-4">
			 <textarea class="form-control" name="note" rows="3">${netConult.note}</textarea>
		</div>
	</div>
	<div class="form-group">
	<div class="col-xs-6" style="text-align:right;">
		<button type="reset" class="btn btn-danger btn-lg">重置</button>
		</div>
		<div class="col-xs-6" style="text-align:left;">
		<button type="button" onclick="update_conult_info(this,'${netConult.user_phone}',${netConult.consult_id })" class="btn btn-success btn-lg">确定</button>
		</div>
	</div>
</form>
