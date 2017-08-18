<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="/css/question/back/jiazaidonghua.css">
<link rel="stylesheet" type="text/css"
	href="/css/question/back/chapteroption.css">
<link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/question/jquery.raty.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script type="text/javascript" src="/dist/summernote.js"></script>
<script type="text/javascript" src="/dist/lang/summernote-zh-CN.min.js"></script>
<style type="text/css">
.image_container {
	position: absolute;
	left: 0px;
	top: 0px;
	z-index: 1;
}

.file {
	display: inline-block;
	background: #00AAEF;
	border: none;
	border-radius: 4px;
	overflow: hidden;
	color: white;
	text-decoration: none;
	text-indent: 0;
	margin-top: 54px;
	width: 92px;
	height: 33px;
	line-height: 33px;
	text-align: center;
	position: absolute;
	left: 0px;
	top: 4px;
	z-index: 2;
}

#preview {
	width: 92px;
	height: 92px;
	background: url('/images/question/back/add_img.png') no-repeat;
	border: 1px solid #ccc;
}

.file input {
	position: absolute;
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
	font-size: 16px;
}

.form-horizontal .form-group .left_wz {
	text-align: left;
	font-size: 16px;
}

.checkbox-inline {
	overflow: hidden;
}

.options {
	width: 20px;
	height: 20px;
	float: left;
}

.checkbox-inline span {
	font-size: 20px;
	display: inline-block;
	line-height: 30px;
	margin-left: 10px;
	float: left;
}
</style>
<script>
	//关闭弹窗
	function colse_layer() {
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
	}
	//修改题目
	function update_zhenti_question(zhenti_question_id){
		var summernote_name= $('#summernote_name').summernote('code');
		var markupStr= $('#summernote').summernote('code');//编辑器的内容
		 $("#myform").ajaxSubmit({
				type : 'POST',
				data:{'question_name':summernote_name,
					'zhenti_question_id':zhenti_question_id,
					'analysis':markupStr},
				url : "/back_zhenti/update_zhenti_question.jr",
				success : function(data) {
					if(data==1){
		                tanchuang('恭喜您，修改成功');
					}else{
						tanchuang('很遗憾，修改失败');
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					tanchuang('很遗憾，修改失败');
				}
			}); 
	}
</script>
<form class="form-horizontal add_qiye_dialog"
	enctype="multipart/form-data" id="myform" style="padding: 21px;">
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">题目：</label>
		 <div class="col-xs-9">
        <div id="summernote_name">${zhentiQuestion.question_name }</div>
      	</div>
	</div>
	<div class="form-group">
		<label class="col-xs-2 control-label">题目解析：</label>
		<div class="col-xs-9">
			<div id="summernote">${zhentiQuestion.analysis }</div>
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<div class="col-xs-6 right_wz">
			<button type="button" class="btn btn-warning btn-lg"
				onclick="colse_layer()" style="width: 100px;">关闭</button>
		</div>
		 <div class="col-xs-6" style="text-align: left;">
			<button type="button" class="btn btn-primary btn-lg"
				style="width: 100px;" onclick="update_zhenti_question(${zhentiQuestion.zhenti_question_id })">修改</button>
		</div> 
	</div>
</form>
<script type="text/javascript">
	$(function() {
	//题目
	$('#summernote_name').summernote({
		height: 150,
		lang:"zh-CN",                 // 设置中文
		maxHeight: 150,             //  设置最大高度
		focus: false,                  // 自动获取焦点
		 callbacks: {  
	         onImageUpload: function(files, editor, $editable) {  
	             sendFile_timu(files);  
	         }
	     } 
	   });
	$('#summernote').summernote({
		height : 150,
		lang : "zh-CN", // 设置中文
		maxHeight : 150, //  设置最大高度
		focus : false, // 自动获取焦点
		callbacks : {
			onImageUpload : function(files, editor, $editable) {
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
	        url : "/back_chapter/uploadpic.jr", //图片上传出来的url，返回的是图片上传后的路径，http格式  
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
	function sendFile_timu(files, editor, $editable) {  
	    var data = new FormData();  
	    data.append("ajaxTaskFile", files[0]);  
	    $.ajax({  
	        data : data,  
	        type : "POST",  
	        url : "/back_chapter/uploadpic.jr", //图片上传出来的url，返回的是图片上传后的路径，http格式  
	        cache : false,  
	        contentType : false,  
	        processData : false,  
	        dataType : "json",  
	        success: function(data) {//data是返回的hash,key之类的值，key是定义的文件名  
	            $('#summernote_name').summernote('insertImage', data.file_path);  
	        },  
	        error:function(){  
	            alert("上传失败");  
	        }  
	    });  
	}
</script>