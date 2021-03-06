<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
	<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
    <script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
.form-horizontal .form-group .right_wz{
		text-align:right;
}
</style>
<script>
//添加联系人信息信息
function save_question_course(course_id,course_parent_id){
	if(check_question_course_name(course_id)){
		 $("#myform").ajaxSubmit({
				type : 'POST',
				data:{'course_id':course_id,
					'course_parent_id':course_parent_id},
				url : "/back_question_course/save_question_course.jr",
				success : function(data) {
					if(data==1){
						//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
		                tanchuang('恭喜您，添加成功');
		                $('#myform')[0].reset();
					}else{
						tanchuang('很遗憾，添加失败');
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					tanchuang('很遗憾，添加失败');
				}
			}); 
	}
}
//检查题库课程分类信息
function check_question_course_name(course_id){
	var bol = false;
	var question_course_name=$("#question_course_name").val();
		if(question_course_name!=null&&question_course_name!=""){
			$.ajax({
				type : "POST",
				url : "/back_question_course/check_question_course_name.jr?question_course_name="
						+ question_course_name+"&course_id="+course_id,
				async : false,
				success : function(data) {
					if (data == 2) {
						bol = true;
					} else {
						layer.msg('你输入题库分类信息已存在！');
						bol = false;
					}
				}
			});
		}else{
			layer.msg('请输入题库分类信息！');
			bol = false;
		}
	return bol;
}
//关闭弹窗
function colse_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
</script>
<form class="form-horizontal add_qiye_dialog" enctype="multipart/form-data" id="myform" style="padding:21px;">
        <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">题库类别：</label>
      <div class="col-xs-8">
        <input class="form-control" onblur="check_question_course_name(${course_id})" name="question_course_name" id="question_course_name" placeholder="输入题库类别">         
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">描述：</label>
      <div class="col-xs-8">
        <textarea class="form-control" rows="3" name="question_course_dis" id="question_course_dis" placeholder="请输入课程分类描述" style="color:#444;"></textarea>         
      </div>
    </div>
      	<div class="form-group" style="margin-top:15px;">
      		<div class="col-xs-6 right_wz">
      		<button type="button" onclick="colse_layer()" class="btn btn-warning btn-lg" style="width:100px;">取消</button>
      		</div>
          <div class="col-xs-6" style="text-align:left;">
          <button type="button" onclick="save_question_course(${course_id},${course_parent_id })" class="btn btn-primary btn-lg" style="width:100px;">提交</button>
          </div>
      	</div>
	</form>	