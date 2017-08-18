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
	background-color:#94CE6E;
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
function add_zuoxi(extension){
	var employee_id=$("#employee_id").val();
	$.post("/back_employee/update_employee.jr",{
		'employee_id':employee_id,
		'zuoxi':extension
	},function(data){
		if(data==1){
            location.reload();
		}else{
			tanchuang('很遗憾，设置失败');
		}
	})
}
</script>
</head>
<body>
	<div style="padding: 20px;">
		<label class="col-xs-3">&nbsp;</label>
		<select class="col-xs-6" name="employee_id" id="employee_id" style="line-height:35px;height:35px;">
					<c:forEach items="${employees }" var="employee">
					<option value="${employee.employee_id }">${employee.employee_name }</option>
					</c:forEach>
				</select>
		<label class="col-xs-3">&nbsp;</label>
		<a class="xiazai" onclick="add_zuoxi('${zuoxi}')" href="javascript:void(0)">绑定</a>
	</div>
</body>
</html>