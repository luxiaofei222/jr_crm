<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<script src="/js/school/back/course/add_jie.js"></script>
<title>添加章</title>
</head>
<body>
	<div style="padding: 20px;">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group">
				<label class="col-xs-3 control-label">小节名称：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" onblur="check_jie_name()"
						name="video_name" id="video_name" placeholder="章节名称">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword1" class="col-xs-3 control-label">上传视频：</label>
				<div class="col-xs-9">
					<a href="javascript:void(0)" class="btn btn-info btn-xm"
						style="position: relative; float: left; display: block;"> <input
						type="file" id="file_up" name="file_up" onchange="video_add()"
						style="position: absolute; opacity: 0;">点击上传
					</a>
					<div id="file_name"></div>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword1" class="col-xs-3 control-label">&nbsp;</label>
				<div class="col-xs-9">
					<div class="progress hide" id="progress_id">
						<div class="progress-bar" id="progress_bar"></div>
						<div id="baifenbi"></div>
					</div>
					<div id="has_upload"></div>
					<div id="upload_speed"></div>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword1" class="col-xs-3 control-label">&nbsp;</label>
				<div class="col-xs-9">
					<button type="button" onclick="fSubmit(${video_id})"
						class="btn btn-success btn-xm">保存</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>