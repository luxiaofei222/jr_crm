<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script>
	//检测姓名是否为空
	function check_api_info() {
		var api_info = $("#api_info").val();
		if (api_info != null && api_info != "") {
			return true;
		} else {
			layer.msg('请输入员工姓名！');
			return false;
		}
	}
	//添加员工信息
	function add_web_api() {
		if (check_api_info()) {
			$("#myform").ajaxSubmit({
				type : 'POST',
				url : "/web_api/save_web_api.html",
				success : function(data) {
					if (data == 1) {
						//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
						tanchuang('恭喜您，添加成功');
						$('#myform')[0].reset();
					} else {
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
<style>
.form-horizontal .form-group .wz_right {
	text-align: right;
}
</style>
<div style="padding: 20px;">
	<form enctype="multipart/form-data" id="myform" class="form-horizontal">
		<div class="form-group">
			<label for="inputEmail1" class="col-xs-2 control-label wz_right">api内容：</label>
			<div class="col-xs-9">
				<input type="text" class="form-control" name="api_info"
					id="api_info" placeholder="请输入内容">
			</div>
			<div id="error_name"></div>
		</div>
		<div class="form-group">
			<label for="inputPassword1" class="col-xs-2 control-label wz_right">&nbsp;</label>
			<div class="col-xs-9">
				<button type="button" onclick="add_web_api()"
					class="btn btn-success btn-xm">提交</button>
				<button type="reset" class="btn btn-danger btn-xm">重置</button>
			</div>
		</div>
	</form>
</div>
		<div style="text-align: center;font-size:25px;color:#5cb85c;">历史记录</div>
		<ul style="display:table;margin:0 auto;font-size:20px;">
			<c:forEach items="${apis }" var="api">
				<li style="line-height:40px;">${api.api_info }<span style="margin-left:30px;"><fmt:formatDate value="${api.api_time }" /></span></li>
			</c:forEach>
		</ul>