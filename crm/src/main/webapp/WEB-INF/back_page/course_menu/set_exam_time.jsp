<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>设置考试时间</title>
<style type="text/css">
.form-group .right_wz {
	text-align: right;
	line-height: 35px;
}
</style>
</head>
<script>
$(function(){
	layui.use('laydate', function() {
		var laydate = layui.laydate;

		var start = {
			min : laydate.now(),
			max : '2099-06-16 23:59:59',
			istoday : false,
			choose : function(datas) {
				end.min = datas; //开始日选好后，重置结束日的最小日期
				end.start = datas //将结束日的初始值设定为开始日
			}
		};

		var end = {
			min : laydate.now(),
			max : '2099-06-16 23:59:59',
			istoday : false,
			choose : function(datas) {
				start.max = datas; //结束日选好后，重置开始日的最大日期
			}
		};
	});
})
//设置考试时间
function set_exam_time(course_id){
	$("#myform").ajaxSubmit({
		type : 'POST',
		url : "/back_course_menu/set_examtime.jr?course_id="+course_id,
		success : function(data) {
			if (data == 1) {
				tanchuang("提示：恭喜您，考试时间设置案成功！");
				$('#myform')[0].reset();
			}  else {
				layer.msg("提示：很遗憾，考试时间设置失败！");
			}
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			layer.msg("考试时间设置失败！");
		}
	});
}
//关闭弹窗
function close_layer() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}
</script>
<body>
	<form enctype="multipart/form-data" class="form-horizontal" id="myform" style="padding: 30px;">
		<div class="form-group">
			<label class="col-xs-4 right_wz">选择考试日期：</label>
			<div class="col-xs-8">
				<input class="layui-input" placeholder="选择考试日期" name="exam_time" id="exam_time"
					onclick="layui.laydate({elem: this, festival: true})">
			</div>
		</div>
		<div class="form-group" style="margin-top: 30px;">
			<div class="col-xs-6 right_wz">
				<button type="button" class="btn btn-warning btn-lg"
					style="width: 100px;" onclick="close_layer()">取消</button>
			</div>
			<div class="col-xs-6" style="text-align: left;">
				<button type="button" onclick="set_exam_time(${course_id})" class="btn btn-primary btn-lg"
					style="width: 100px;">保存</button>
			</div>
		</div>
	</form>
</body>
</html>