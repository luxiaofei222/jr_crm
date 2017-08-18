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
//检查试卷年份
	function check_zhenti_year(){
		var zhenti_year=$("#zhenti_year").val();
		if(zhenti_year!=null&&zhenti_year!=""){
			if(isNaN(zhenti_year)){
				layer.msg("您输入的类型不正确，请输入数字类型，如2017");
				return false;
			}else{
				return true;
			}
		}else{
			layer.msg("请输入试卷年份")
			return false;
		}
	}
	//检查月份
	function check_zhenti_yue(){
		var zhenti_yue=$("#zhenti_yue").val();
		if(zhenti_yue!=null&&zhenti_yue!=""){
			if(isNaN(zhenti_yue)){
				layer.msg("您输入的类型不正确，请输入数字类型，如1/2/3");
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	//检查考试时长
	function check_kaoshi_time(){
		var zhenti_kaoshi_time=$("#zhenti_kaoshi_time").val();
		if(zhenti_kaoshi_time!=null&&zhenti_kaoshi_time!=""){
			if(isNaN(zhenti_kaoshi_time)){
				layer.msg("您输入的类型不正确，请输入数字类型");
				return false;
			}else{
				return true;
			}
		}else{
			layer.msg("请输入考试时长！")
			return false;
		}
	}
	//检查价格
	function check_zhenti_new_price(){
		var zhenti_new_price=$("#zhenti_new_price").val();
		if(isNaN(zhenti_new_price)){
			layer.msg("您输入的类型不正确，请输入数字类型");
			return false;
		}else{
			return true;
		}
	}
	//关闭弹窗
	function colse_layer(){
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	    parent.layer.close(index);
	}
	//添加真题试卷信息
	function save_zhenti_year(question_course_id){
		if(check_zhenti_year()&&check_zhenti_yue()&&check_kaoshi_time()&&check_zhenti_new_price()){
			 $("#myform").ajaxSubmit({
					type : 'POST',
					url : "/back_zhenti/save_zhenti_year.jr",
					data:{'question_course_id':question_course_id},
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
	//切换考试类型
	function change_kaoshi_type(obj){
		var val=$(obj).val();
		if(val=="模拟考试"){
			$("#kaoshimingcheng").show();
		}else{
			$("#kaoshimingcheng").hide();
		}
	}
</script>
<form class="form-horizontal add_qiye_dialog"
	enctype="multipart/form-data" id="myform" style="padding: 21px;">
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-12 control-label"
			style="text-align: center; font-size: 20px;">${courseMenu.course_name }《${questionCourse.question_course_name }》试卷</label>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-3 control-label right_wz">试卷年份：</label>
		<div class="col-xs-8">
			<input class="form-control" onblur="check_zhenti_year()" id="zhenti_year" name="zhenti_year" placeholder="输入真题年份(如:2017)">
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-3 control-label right_wz">试卷月份：</label>
		<div class="col-xs-8">
			<input class="form-control" onblur="check_zhenti_yue()" id="zhenti_yue" name="zhenti_yue" placeholder="输入真题月份份(如:11)">
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-3 control-label right_wz">考试时长：</label>
		<div class="col-xs-8">
			<input class="form-control" onblur="check_kaoshi_time()" id="zhenti_kaoshi_time" name="zhenti_kaoshi_time"  placeholder="输入考试时长(单位:分钟)">
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-3 control-label right_wz">试卷价格：</label>
		<div class="col-xs-8">
			<input class="form-control" onblur="check_zhenti_new_price()" id="zhenti_new_price" value="0.00" name="zhenti_new_price" placeholder="输入试卷价格">
		</div>
	</div>
	<div class="form-group" id="kaoshimingcheng" style="margin-top: 15px;display: none;">
		<label class="col-xs-3 control-label right_wz">模拟考试名称：</label>
		<div class="col-xs-8">
			<input class="form-control"  id="zhenti_name"  name="zhenti_name" placeholder="输入试卷名称，如：入学评测、仿真模拟试卷一">
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-3 control-label right_wz">试卷类型：</label>
		<div class="col-xs-8">
		<select data-toggle="select" onchange="change_kaoshi_type(this)" id="zhentitype" name="zhentitype" class="form-control select select-primary">
			<option value="历年真题">历年真题</option>
          <option value="模拟考试">模拟考试</option>
        </select>
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<div class="col-xs-6 right_wz">
			<button type="button" class="btn btn-warning btn-lg"
				style="width: 100px;" onclick="colse_layer()" >取消</button>
		</div>
		<div class="col-xs-6" style="text-align: left;">
			<button type="button" class="btn btn-primary btn-lg"
				style="width: 100px;" onclick="save_zhenti_year(${questionCourse.question_course_id })" >保存</button>
		</div>
	</div>
</form>
