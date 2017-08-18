<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script type="text/javascript" src="/dist/summernote.min.js"></script>
<script type="text/javascript" src="/dist/lang/summernote-zh-CN.min.js"></script>
<script>
$(function() {
  $("#file_upload").change(function() {
	var $file = $(this);
	var fileObj = $file[0];
	var windowURL = window.URL || window.webkitURL;
	var dataURL;
	var $img = $("#preview");
	if(fileObj && fileObj.files && fileObj.files[0]){
	dataURL = windowURL.createObjectURL(fileObj.files[0]);
	$img.attr('src',dataURL);
	}else{
	dataURL = $file.val();
	var imgObj = document.getElementById("preview");
	// 两个坑:
	// 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
	// 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
	imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
	imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
	}
  });
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
        url : "/extend/uploadpic.jr", //图片上传出来的url，返回的是图片上传后的路径，http格式  
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

//添加推广动态信息
function add_course_extend(course_id){
		 var markupStr= $('#summernote').summernote('code');//编辑器的内容
		 var file_load=$("#file_upload").val();
		 if(file_load!=null&&file_load!=""){
		 if(markupStr!=null&&markupStr!=""){
			 $("#myform").ajaxSubmit({
					type : 'POST',
					data:{'course_dis':markupStr,'course_id':course_id},
					url : "/extend/update_extend.jr",
					success : function(data) {
						if(data==1){
							tanchuang("提示：恭喜您，添加推广信息成功！");
							$('#myform')[0].reset();
							$('#summernote').summernote('code',"");
						}else{
							 layer.msg("提示：很遗憾，添加失败！");
						}
					},
					error : function(XmlHttpRequest, textStatus, errorThrown) {
						 layer.msg("添加失败！");
					}
				});
		 }else{
			 layer.msg("请添加推广信息内容！");
		 }
		 }else{
			 layer.msg("请上传封面图片！");
		 }
}
</script>
<div style="padding: 20px;">
	<form enctype="multipart/form-data" id="myform" class="form-horizontal">

		<div class="form-group">
			<label class="col-xs-2 control-label">推广课程：</label>
			<div class="col-xs-9">
				<label class="control-label" style="font-weight: normal;">${courseMenu.course_name }</label>
			</div>
			<div id="error_biaoti"></div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">推广图片：</label>
			<div class="input_container" class="col-xs-9" style="overflow:hidden;">
				<div style="float:left;">
				<input id="file_upload" name="file_pic" type="file" />
				<input type="button" value="上传封面图" /> <span>建议：（234px*465px）</span>
				</div>
				<div class="image_container" style="float:left;">
					<img id="preview" width="105" height="60">
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">页面标题：</label>
			<div class="col-xs-9">
				<input type="text" class="form-control" name="meta_title"
					id="meta_title" placeholder="页面标题">
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">页面关键字：</label>
			<div class="col-xs-9">
				<input type="text" class="form-control" name="meta_key"
					id="meta_key" placeholder="页面关键字">
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">页面描述：</label>
			<div class="col-xs-9">
				<input type="text" class="form-control" name="meta_dis"
					id="meta_dis" placeholder="页面描述">
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">文章内容：</label>
			<div class="col-xs-9">
				<div id="summernote"></div>
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
			<div class="col-xs-9">
				<button type="button"
					onclick="add_course_extend(${courseMenu.course_id})"
					class="btn btn-success btn-xm">提交</button>
				<button type="reset" class="btn btn-danger btn-xm">重置</button>
			</div>
		</div>
	</form>
</div>