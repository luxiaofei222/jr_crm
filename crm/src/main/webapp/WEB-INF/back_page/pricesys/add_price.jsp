<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>添加低价体系</title>
</head>
<style type="text/css">
.form-group .right_wz{
		text-align:right;
}
</style>
<script>
	//关闭弹窗
	function close_layer() {
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
	}
	//添加低价体系
	function add_price() {
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_price/save_price.jr",
			success : function(data) {
				if (data == 1) {
					tanchuang("提示：恭喜您，添加低价体系成功！");
					$('#myform')[0].reset();
				}else {
					layer.msg("提示：很遗憾，添加失败！");
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				layer.msg("添加失败！");
			}
		});
	}
</script>
<body>
	<form enctype="multipart/form-data" id="myform" style="padding: 20px;" class="form-horizontal">
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-3 control-label right_wz">标题：</label>
      <div class="col-xs-8">
        <input class="form-control" id="price_sys_title" name="price_sys_title" placeholder="输入标题">         
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-3 control-label right_wz">内容：</label>
      <div class="col-xs-8">
        <textarea class="form-control"  id="price_sys_content" name="price_sys_content" placeholder="低价体系内容" rows="3" style="color:#444;"></textarea>         
      </div>
    </div>
		<div class="form-group" style="overflow:hidden;margin-top:20px;">
			<div class="col-xs-6 right_wz">
				<button type="button" onclick="close_layer()"
					class="btn btn-warning btn-lg" style="width: 100px;">取消</button>
			</div>
			<div class="col-xs-6" style="text-align: left;">
				<button type="button" onclick="add_price()"
					class="btn btn-primary btn-lg" style="width: 100px;">保存</button>
			</div>
		</div>
	</form>
</body>
</html>