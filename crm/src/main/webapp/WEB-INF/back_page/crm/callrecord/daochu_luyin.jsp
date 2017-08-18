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
.xiazai {
	width: 100px;
	height: 35px;
	line-height: 35px;
	background-color: #94CE6E;
	border: none;
	margin: 0 auto;
	text-align: center;
	display: block;
	color: white;
	border-radius: 5px;
	margin-top: 10px;
}

.audio {
	text-align: center;
	width: 80%;
	margin-left: 10%;
	margin-top: 15px;
}

.xiazai:hover {
	color: white;
}

.right_wz {
	text-align: right;
}
</style>
<script>
	$(function() {
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			var start = {
				festival : true,
				istoday : true
			};
			document.getElementById('end_time').onclick = function() {
				start.elem = this;
				laydate(start);
			}
			document.getElementById('start_time').onclick = function() {
				start.elem = this;
				laydate(start);
			}
		});
	})
	//导出录音
	function donw_load(){
		var start_time=$("#start_time").val();
		var end_time=$("#end_time").val();
		var zuoxi=$("#zuoxi").val();
		if(start_time!=null&&start_time!=""&&end_time!=null&&end_time!=""){
			if(zuoxi!=null&&zuoxi!=""){
				location.href="http://192.168.1.251/api.php?f=getAllVoice&fromdate="+start_time+"&todate="+end_time+"&exten="+zuoxi;
			}else{
				location.href="http://192.168.1.251/api.php?f=getAllVoice&fromdate="+start_time+"&todate="+end_time;
			}
		}else{
		layer.msg("请选择时间区间！")
		}
	}
</script>
</head>
<body>
	<div style="padding: 20px;">
		<div class="form-group clearfix">
			<label class="col-xs-4 right_wz">通话时间：</label>
			<div class="col-xs-7">
				<input class="layui-input" placeholder="选择开始日期" name="start_time"
					id="start_time">
			</div>
		</div>
		<div class="form-group clearfix">
			<label class="col-xs-4 right_wz">到：</label>
			<div class="col-xs-7">
				<input class="layui-input" placeholder="选择结束日期" name="end_time"
					id="end_time">
			</div>
		</div>
		<div class="form-group clearfix">
			<label class="col-xs-4 right_wz">坐席：</label> <select class="col-xs-7"
				name="zuoxi" id="zuoxi" style="line-height: 35px; height: 35px;">
				<option value="">选择坐席</option>
				<c:forEach items="${employees }" var="employee">
					<option value="${employee.zuoxi }">${employee.employee_name }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group clearfix">
		<label class="col-xs-3">&nbsp;</label> 
		<a class="xiazai"
			onclick="donw_load()" href="javascript:void(0)" class="col-xs-6">导出</a>
		<label class="col-xs-3">&nbsp;</label> 
		</div>
	</div>
</body>
</html>