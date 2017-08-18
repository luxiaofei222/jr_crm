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

$(function() {
	layui.use('laydate',
					function() {
						var laydate = layui.laydate;
						var start = {
							festival : true,
							istoday : true
						};
						document.getElementById('lizhi_time').onclick = function() {
							start.elem = this;
							laydate(start);
						}
					});
})
//添加离职时间
function update_lizhi(oa_employee_id){
	var lizhi_time=$("#lizhi_time").val();
	$.post("/oa_employee/update_oa_employee_gangwei.jr",{
		'lizhi_time':lizhi_time,
		'oa_employee_id':oa_employee_id,
		'gangwei_state':"离职"
	},function(data){
		if(data==1){
			tanchuang('恭喜您，设置成功');
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
		<input class="layui-input" placeholder="点击选择离职日期"
						name="lizhi_time" id="lizhi_time">
		<label class="col-xs-3">&nbsp;</label>
		<a class="xiazai" onclick="update_lizhi(${oa_employee_id})" href="javascript:void(0)">确定</a>
	</div>
</body>
</html>