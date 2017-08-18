<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<style>
.form-horizontal .form-group .right_wz {
	text-align: right;
}
</style>
<script>
	//检查题型介绍
	function check_introduce_content(){
		var introduce_content=$("#introduce_content").val();
		if(introduce_content!=null&&introduce_content!=""){
			return true;
		}else{
			layer.msg("请输入题型介绍！")
			return false;
		}
	}
	//关闭弹窗
	function colse_layer(){
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	    parent.layer.close(index);
	}
	//添加真题试卷信息
	function save_zhenti_year_type(zhenti_id){
		if(check_introduce_content()){
			 $("#myform").ajaxSubmit({
					type : 'POST',
					url : "/back_zhenti/save_zhenti_year_type.jr",
					data:{'zhenti_id':zhenti_id},
					success : function(data) {
						if(data==1){
							//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
			                tanchuang('恭喜您，添加成功');
			                $('#myform')[0].reset();
						}else if(data==2){
							tanchuang('该题型已经添加过了！');
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
</script>
<form class="form-horizontal add_qiye_dialog"
	enctype="multipart/form-data" id="myform" style="padding: 21px;">
	<div class="form-group" style="margin-top: 15px;">
	<c:if test="${zhentiYears.zhentitype=='历年真题' }">
		<label class="col-xs-12 control-label"
			style="text-align: center; font-size: 20px;">${zhentiYears.zhenti_year }年${courseMenu.course_name }《${questionCourse.question_course_name }》真题及答案解析</label>
	</c:if>
	<c:if test="${zhentiYears.zhentitype=='模拟考试' }">
		<label class="col-xs-12 control-label"
			style="text-align: center; font-size: 20px;">${zhentiYears.zhenti_year }年${courseMenu.course_name }《${questionCourse.question_course_name }》${zhentiYears.zhenti_name }</label>
	</c:if>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-3 control-label right_wz">选择题型：</label>
		<div class="col-xs-8">
		<select data-toggle="select"  id="question_type" name="question_type" class="form-control select select-primary">
          <option value="单选题">单选题</option>
          <option value="多选题">多选题</option>
          <option value="案例题">案例题</option>
          <option value="职业道德">职业道德</option>
          <option value="技能选择题">技能选择题</option>
          <option value="简答题">简答题</option>
          <option value="案例简答题">案例简答题</option>
        </select>
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-3 control-label right_wz">题型介绍：</label>
		<div class="col-xs-8">
			<input class="form-control" onblur="check_introduce_content()" id="introduce_content" name="introduce_content"  placeholder="输入题型介绍">
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<div class="col-xs-6 right_wz">
			<button type="button" class="btn btn-warning btn-lg"
				style="width: 100px;" onclick="colse_layer()" >取消</button>
		</div>
		<div class="col-xs-6" style="text-align: left;">
			<button type="button" class="btn btn-primary btn-lg"
				style="width: 100px;" onclick="save_zhenti_year_type(${zhentiYears.zhenti_id })" >保存</button>
		</div>
	</div>
</form>
