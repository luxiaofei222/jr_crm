<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" type="text/css"
	href="/js/common/bootstrap-select.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap-select.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
.form-horizontal .form-group .right_wz {
	text-align: right;
}
</style>
<script>
//添加联系人信息信息
function save_user_grade(){
	var str = "";
	var len =$('input:text[name=content]').length;
	$('input:text[name=content]').each(function(i) {
		if (0 == i) {
			str = $(this).val();
		} else {
			if(str!=null&&str!=""){
				str += ","+$(this).val();
			}
		}
	});
	var str2 = "";
	$('input:text[name=xiangguanginfo]').each(function(i) {
		if (0 == i) {
			if($(this).val()!=null&&$(this).val()!=""){
				str2 = $(this).val();
			}else{
				str2="暂无"
			}
		} else {
			if($(this).val()!=null&&$(this).val()!=""){
				str2 += ","+$(this).val();
			}else{
				str2+= ","+"暂无"
			}
		}
	});
	if(check_user_name()&&check_user_phone()&&check_user_card()&&check_user_zhunkao()&&str!=""&&str!=null){
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/user_grade/save_user_grade.jr",
			data:{'str':str,'str2':str2},
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
	                tanchuang('恭喜您，添加成功');
					location.reload();
	                //$('#myform')[0].reset();
				}else{
					tanchuang('很遗憾，添加失败');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，添加失败');
			}
		});
	}else{
		layer.msg("请添加一条成绩信息！")
	}
}
//获取二级菜单
function get_sub_course(){
	var course_parent_id = $("#course_parent_id").val();
	if(course_parent_id==9){
		$("#course_id").html("<option value=''>请选择</option>");
		$("#dictionary_id").html("<option value=''>请选择级别</option>");
	}else{
		$("#review_id").html("<option value=''>请选择级别</option>");
		$("#dictionary_id").html("<option value=''>请选择级别</option>");
	}
	$.post("/edu_entry_plan/get_sub_course.jr", {
		'course_id' : course_parent_id
	}, function(data) {
		$("#course_id").html(data);
	})
}
//获取职称
function get_review_list() {
	var course_id = $("#course_id").val();
	var course_parent_id = $("#course_parent_id").val();
	if (course_parent_id == 9) {//显示职称评审3级
		$.post("/user_grade/get_sub_review.jr", {
			'course_id' : course_id
		}, function(data) {
			$("#review_id").html(data);
		})
	} else {
		if(course_id==19){
			$.post("/edu_condition/get_dic_list.jr",{
				'course_id':course_id,
				'type':2
			},function(data){
				$("#dictionary_id").html("<option value=''>请选择级别</option>");
			}) 
		}else{
			$.post("/back_video/get_dic_list.jr",{
				'course_id':course_id,
				'type':2
			},function(data){
				$("#dictionary_id").html(data);
			}) 
		}
		$("#review_id").html("<option value=''>请选择</option>");
	}
}
//关闭弹窗
function colse_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
</script>
<form class="form-horizontal add_qiye_dialog" id="myform" style="padding: 21px;">
		<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">一级分类：</label>
		<div class="col-xs-4">
			<select data-toggle="select" onchange="get_sub_course()"
				id="course_parent_id" name="course_parent_id"
				class="form-control select select-primary">
				<option value="">请选择</option>
						<c:forEach items="${courseMenus }" var="course">
							<option value="${course.course_id }">${course.course_name }</option>
				</c:forEach>
			</select>
		</div>
		<label class="col-xs-2 control-label right_wz">二级分类：</label>
		<div class="col-xs-4">
			<select data-toggle="select" onchange="get_review_list()" id="course_id"
				name="course_id" class="form-control select select-primary">
				<option value="">请选择</option>
			</select>
		</div>
	</div>
	<script type="text/javascript">
	function get_user_review(){
		var review_id = $("#review_id").val();
		if (review_id != null&&review_id!="") {
			$.post("/user_grade/get_review_diclist.jr"
			, function(data) {
				$("#dictionary_id").html(data);
			})
		} else {
			$("#dictionary_id").html("<option value=''>请选择级别</option>");
		}
	}
	</script>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">三级分类：</label>
		<div class="col-xs-4">
			<select data-toggle="select" id="review_id"
				name="review_id" onchange="get_user_review()" class="form-control select select-primary">
				<option value="">请选择</option>
			</select>
		</div>
		<label class="col-xs-2 control-label right_wz">等级：</label>
			<div class="col-xs-4">
				<select id="dictionary_id" data-toggle="select" class="form-control select select-primary" name="dictionary_id">
					<option value="">请选择级别</option>
				</select>
			</div>
	</div> 
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">报考学校：</label>
		<div class="col-xs-4">
			<input class="form-control" id="xuexiao" name="xuexiao"
				placeholder="请输入报考的学校">
		</div>
		<label class="col-xs-2 control-label right_wz">报考专业：</label>
		<div class="col-xs-4">
			<input class="form-control" id="zhuanye" name="zhuanye"
				placeholder="请输入报考的专业">
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
	<script type="text/javascript">
	function check_user_name(){
		var user_name=$.trim($("#user_name").val());
		if(user_name!=null&&user_name!=""){
			return true;
		}else{
			layer.msg("请输入用户姓名！");
			return false;
		}
	}
	</script>
		<label class="col-xs-2 control-label right_wz">姓名：</label>
		<div class="col-xs-4">
			<input class="form-control" onblur="check_user_name()" id="user_name" name="user_name"
				placeholder="请输入姓名">
		</div>
		<label class="col-xs-2 control-label right_wz">性别：</label>
		<div class="col-xs-4">
			<select id="user_sex" data-toggle="select" class="form-control select select-primary" name="user_sex">
					<option value="">请选择</option>
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">手机号：</label>
		<script type="text/javascript">
		function check_user_phone(){
			var user_phone = $("#user_phone").val();
			var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
			if (user_phone != null && user_phone != "") {
				if (!myreg.test(user_phone)) {
					layer.msg('请输入有效的手机号码！');
					return false;
				} else {
					return true;
				}
			} else {
				layer.msg("请输入您的手机号！");
				return false;
			}
		}
		</script>
		<div class="col-xs-4">
			<input class="form-control" onblur="check_user_phone()" id="user_phone" name="user_phone"
				placeholder="请输入手机号">
		</div>
		<label class="col-xs-2 control-label right_wz">身份证号：</label>
		<script type="text/javascript">
		function check_user_card(){
			var user_card=$("#user_card").val();
			if(user_card!=null&&user_card!=""){
				isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
				if(isIDCard2.test(user_card)){
					return true;
				}else{
					layer.msg("请输入正确的身份证号码！");
					return false;
				}
			}else{
				layer.msg("请输入证件号码！");
				return false;
			}
		}
		</script>
		<div class="col-xs-4">
			<input class="form-control" onblur="check_user_card()" id="user_card" name="user_card"
				placeholder="请输入身份证号">
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">准考证号：</label>
		<script type="text/javascript">
		function check_user_zhunkao(){
			var user_zhunkao=$.trim($("#user_zhunkao").val());
			if(user_zhunkao!=null&&user_zhunkao!=""){
				return true;
			}else{
				layer.msg("请输入准考证号！");
				return false;
			}
		}
		</script>
		<div class="col-xs-4">
			<input class="form-control" onblur="check_user_zhunkao()" id="user_zhunkao" name="user_zhunkao"
				placeholder="请输入准考证号">
		</div>
		<label class="col-xs-2 control-label right_wz">考生号：</label>
		<div class="col-xs-4">
			<input class="form-control" id="kaoshenghao" name="kaoshenghao"
				placeholder="请输入考生号，没有则不填">
		</div>
	</div>
	<script type="text/javascript">
	//添加一条成绩
	function tj(obj){
		  var val= $(obj).attr("class");
		   if(val=="fa fa-plus-circle"){
		    $(obj).parent().parent().parent().after('<div><div class="form-group plus" style="margin-top:15px;"><label class="col-xs-2 control-label right_wz">科目：</label><div class="col-xs-8"><input class="form-control" id="content" name="content"  placeholder="输入课目，如果没有课目请填写其他相关输入，比如录取结果/成绩"></div></div><div class="form-group" style="margin-top:15px;"><label class="col-xs-2 control-label right_wz">成绩（分数）：</label><div class="col-xs-8"><input class="form-control" id="xiangguanginfo" name="xiangguanginfo" placeholder="输入相关信息：比如合格、录取、60分"></div><div class="col-xs-2"><i class="fa fa-plus-circle" style="font-size:30px;color:#1ABC9C;line-height:34px;" onclick="tj(this)"></i></div></div></div>');
		    $(obj).removeClass("fa fa-plus-circle");
		    $(obj).addClass("fa fa-minus-circle"); 
		    $(obj).css("color","#999");
		    } else{
		    $(obj).parent().parent().parent().remove();
		    }
		 }
	</script>
	<div>
	<div class="form-group plus" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">科目：</label>
		<div class="col-xs-8">
			<input class="form-control" id="content" name="content"
				placeholder="输入科目，如果没有课目请填写其他相关输入，比如录取结果/成绩">
		</div>
		<div class="col-xs-2">
			<i class="fa fa-plus-circle"
				style="font-size: 30px; color: #1ABC9C; line-height: 34px;"
				onclick="tj(this)"></i>
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">成绩（分数）：</label>
		<div class="col-xs-8">
			<input class="form-control" id="xiangguanginfo" name="xiangguanginfo"
				placeholder="输入相关信息：比如合格、录取、60分">
		</div>
	</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<div class="col-xs-6 right_wz">
			<button type="button" onclick="colse_layer()"
				class="btn btn-warning btn-lg" style="width: 100px;">取消</button>
		</div>
		<div class="col-xs-6" style="text-align: left;">
			<button type="button" onclick="save_user_grade()"
				class="btn btn-primary btn-lg" style="width: 100px;">保存</button>
		</div>
	</div>
</form>
