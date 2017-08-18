<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑院历</title>
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
	<script src="/js/common/jquery.form.js" type="text/javascript"></script>
	<script type="text/javascript"
		src="/js/school/back/common/bootstrap.min.js"></script>
	<script type="text/javascript" src="/dist/summernote.min.js"></script>
	<script type="text/javascript" src="/dist/lang/summernote-zh-CN.min.js"></script>
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
	<script src="/js/school/back/common/tanchuang.js"
		type="text/javascript"></script>
	<style>
input[type="text"] {
	width: 100%;
	height: 38px;
	line-height: 38px;
	border: 2px solid #bdc3c7;
	border-radius: 5px;
	padding-left: 5px;
}

.tianjia_xuexiao form .col-xs-10 select {
	width: 100%;
	border: 2px solid #bdc3c7;
	height: 42px;
	border-radius: 5px;
}

.tianjia_xuexiao input[type="button"], input[type="reset"] {
	width: 76px;
	height: 35px;
	display: block;
	margin: 25px auto;
	background-color: #8bc34a;
	color: #fff;
	border: none;
	border-radius: 3px;
	cursor: pointer;
	float: left;
	margin-right: 20px;
}

input[type="reset"] {
	background-color: orange;
}

.file {
	position: relative;
	display: inline-block;
	background: #00AAEF;
	border: none;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	color: white;
	text-decoration: none;
	text-indent: 0;
	line-height: 20px;
	margin-top: 49px;
	margin-left: 28px;
}

.filetwo {
	background-color: #5CB85C;
	color: white;
}

.file input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
}

.file:hover {
	background: #25BDFB;
	color: white;
	text-decoration: none;
}

.form-horizontal .form-group .right_wz {
	text-align: right;
	line-height: 35px;
}

table {
	text-align: center;
}

table tr th {
	text-align: center !important;
}

h4 {
	text-align: center;
	margin-top: 20px;
	font-weight: bold;
}
</style>
</head>
<script>
$(function() {
	   $('#summernote').summernote({
		height: 300,
		lang:"zh-CN",                 // 设置中文
		maxHeight: 300,             //  设置最大高度
		focus: false,                  // 自动获取焦点
		 callbacks: {  
	         onImageUpload: function(files, editor, $editable) {  
	             sendFile(files);  
	         }
	     } 
	   });
	});
	//上传图片到服务器
	function sendFile(files, editor, $editable) {  
	    var data = new FormData();  
	    data.append("ajaxTaskFile", files[0]);  
	    $.ajax({  
	        data : data,  
	        type : "POST",  
	        url : "/back_info/uploadpic.jr", //图片上传出来的url，返回的是图片上传后的路径，http格式  
	        cache : false,  
	        contentType : false,  
	        processData : false,  
	        dataType : "json",  
	        success: function(data) {//data是返回的hash,key之类的值，key是定义的文件名  
	            $('#summernote').summernote('insertImage', data.file_path);  
	        },  
	        error:function(){  
	            alert("上传失败");  
	        }  
	    });  
	}
function save_yuanli(obj,zhaosheng_id){
	 $(obj).attr("disabled","disabled"); 
	 var markupStr= $('#summernote').summernote('code');
		$("#myform").ajaxSubmit({
			type : 'POST',
			data:{'shoufei_shuoming':markupStr,'zhaosheng_id':zhaosheng_id},
			url : "/ck_zhaosheng/save_yuanli.jr",
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
					tanchuang('恭喜您，添加成功');
					$(obj).removeAttr("disabled");
				}else{
					tanchuang('很遗憾，添加失败');
					$(obj).removeAttr("disabled");
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，添加失败');
			}
		});
}
</script>
<body>
	<div class="tianjia_xuexiao">
		<h4>${ckZhaoSheng.xuexiao_name }文件编辑</h4>
		<form class="form-horizontal" enctype="multipart/form-data"
			id="myform" style="padding: 0 20px; padding-top: 20px;">
			<div class="wrapper">
				<table class="table table-hover">
					<thead>
						<tr class="tr_bgcolor info">
							<th>名称</th>
							<th>院历下载</th>
							<th>学生手册下载</th>
							<th>模拟题下载</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${ckZhaoSheng.yuanli_name }</td>
							<td><c:if test="${not empty ckZhaoSheng.yuanli_file }">
									<a href="${ckZhaoSheng.yuanli_file }"
										class="btn btn-sm btn-success">下载</a>
								</c:if> <c:if test="${empty ckZhaoSheng.yuanli_file }">
							还未上传
							</c:if></td>
							<td><c:if test="${not empty ckZhaoSheng.xuesheng_shouce }">
									<a href="${ckZhaoSheng.xuesheng_shouce }"
										class="btn btn-sm btn-success">下载</a>
								</c:if> <c:if test="${empty ckZhaoSheng.xuesheng_shouce }">
							还未上传
							</c:if></td>
							<td><c:if test="${not empty ckZhaoSheng.moniti }">
									<a href="${ckZhaoSheng.moniti }" class="btn btn-sm btn-success">下载</a>
								</c:if> <c:if test="${empty ckZhaoSheng.moniti }">
							还未上传
							</c:if></td>
						</tr>
					</tbody>
				</table>
				<div class="form-group" style="margin-top: 15px;">
					<label class="col-xs-3 control-label right_wz">院历名称：</label>
					<div class="col-xs-8">
						<input type="text" id="yuanli_name" name="yuanli_name"
							class="form-control" placeholder="请输入名称">
					</div>
				</div>
				<div class="form-group" style="margin-top: 15px;">
					<label class="col-xs-3 control-label right_wz">院历图片上传：</label>
					<div class="col-xs-8 clearfix">
						<div class="image_container pull-left">
							<img id="preview" width="120" height="120"
								src="${ckZhaoSheng.yuanli_pic }"
								style="background: url('/images/school/back/course/upload/img_upload.jpg') no-repeat">
						</div>
						<a href="javascript:void(0);" class="file pull-left"> <input
							type="file" name="file_upload" id="file_upload">点击上传院历图片
						</a>
					</div>
				</div>
				<div class="form-group" style="margin-top: 15px;">
					<label class="col-xs-3 control-label right_wz">院历上传：</label>
					<div class="col-xs-8 clearfix">
						<input type="file" name="yuanlifile" value="点击上传院历文件"
							class="filetwo">
					</div>
				</div>
				<div class="form-group" style="margin-top: 15px;">
					<label class="col-xs-3 control-label right_wz">学生手册上传：</label>
					<div class="col-xs-8 clearfix">
						<input type="file" name="shoucefile" value="点击上传学生手册文件"
							class="filetwo">
					</div>
				</div>
				<div class="form-group" style="margin-top: 15px;">
					<label class="col-xs-3 control-label right_wz">模拟题上传：</label>
					<div class="col-xs-8 clearfix">
						<input type="file" name="monitifile" value="点击上传模拟题文件"
							class="filetwo">
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label">收费说明：</label>
					<div class="col-xs-10">
						<div id="summernote">${ckZhaoSheng.shoufei_shuoming }</div>
					</div>
				</div>
				<button class="btn btn-md btn-info"
					onclick="save_yuanli(this,${ckZhaoSheng.zhaosheng_id})"
					style="display: table; margin: 0 auto;">确定</button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	/* 图片上传并预览 */
	$(function() {
		$("#file_upload").change(function() {
			var $file = $(this);
			var fileObj = $file[0];
			var windowURL = window.URL || window.webkitURL;
			var dataURL;
			var $img = $("#preview");

			if (fileObj && fileObj.files && fileObj.files[0]) {
				dataURL = windowURL.createObjectURL(fileObj.files[0]);
				$img.attr('src', dataURL);
			} else {
				dataURL = $file.val();
				var imgObj = document.getElementById("preview");
			}
		});
	});
</script>
</html>
