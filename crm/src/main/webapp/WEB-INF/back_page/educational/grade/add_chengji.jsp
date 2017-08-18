<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/bootstrap.min.js"
	type="text/javascript"></script>
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>试听录音</title>
<style>
.xiazai{
	width:100px;
	height:35px;
	line-height:35px;
	background-color:#f15151;
	border:none;
	margin:0 auto;
	text-align:center;
	display:block;
	color:white;
	border-radius:5px;
	margin-top:100px;
}
.audio{
    text-align: center;
    width: 80%;
    margin-left: 10%;
    margin-top: 15px;
}
.xiazai:hover{
	color:white;
}
</style>
<script>
function add_chengji(grade_id){
	var grade=$("#grade").val();
	var kemu=$("#kemu").val();
	if(grade!=""&&kemu!=""){
		$.post("/user_grade/add_chengji.jr",{
			'parent_id':grade_id,
			'kemu':kemu,
			'grade':grade
		},function(data){
			if(data==1){
	            layer.msg('恭喜您，添加成功');
	            location.reload();
			}else{
				tanchuang('很遗憾，添加失败');
			}
		})
	}else{
		layer.msg("请输入内容");
	}
}
</script>
</head>
<body>
	<div style="padding: 20px;">
	<div class="form-group" style="margin-top: 15px;overflow:hidden;">
		<label class="col-xs-3 control-label right_wz">科目:</label>
		<div class="col-xs-8">
		<input class="form-control" id="kemu" name="kemu"
				placeholder="输入科目，如果没有课目请填写其他相关输入，比如录取结果/成绩">
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
	<label class="col-xs-3 control-label right_wz">成绩:</label>
		<div class="col-xs-8">
		<input class="form-control" id="grade" name="grade"
				placeholder="输入相关信息：比如合格、录取、60分">
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
	<label class="col-xs-3">&nbsp;</label>
		<a class="xiazai" onclick="add_chengji(${grade_id})" href="javascript:void(0)">提交</a>
	</div>
	</div>
</body>
</html>