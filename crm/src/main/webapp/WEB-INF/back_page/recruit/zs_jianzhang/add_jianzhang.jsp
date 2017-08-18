<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加院校简章</title>
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/dist/summernote.min.js"></script>
<script type="text/javascript" src="/dist/lang/summernote-zh-CN.min.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
h1 {
    text-align: center;
}
select {
	width: 30%;
	border: 2px solid #bdc3c7;
	height: 35px;
	border-radius: 5px;
	float: left;
}
input[type="text"] {
    border:none;
	width:100%;
	height:38px;
	line-height:38px;
	border:2px solid #bdc3c7;
	border-radius:5px;
	padding-left:5px;
	}

.file {
    position: relative;
	display: block;
	width: 150px;
	height: 35px;
	padding-left:15px;
	line-height: 34px;
	background-color: #06C1AE;
	color: #fff;
	font-size: 16px;
	float: left;
	border-radius: 3px;
	margin-left: 25px;
}

.file:hover {
	text-decoration: none;
	cursor: pointer;
	color: #fff;
}

.file i {
    float:left;
	margin-left: 10px;
    position: absolute;
    top: 9px;
}
.file input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
	width:150px;
}
.file_text {
    float: left;
    line-height: 35px;
    margin-left: 15px;
}
</style>
<script>
$(function() {
	$('#summernote').summernote({
		height: 300,
		lang:"zh-CN",                 // set editor height
		minHeight: null,             // set minimum height of editor
		maxHeight: null,             // set maximum height of editor
		focus: true                  // set focus to editable area after initializing summernote
	  });
})
	//上传图片到服务器
	function sendFile(files, editor, $editable) {  
	    var data = new FormData();  
	    data.append("ajaxTaskFile", files[0]);  
	    $.ajax({  
	        data : data,  
	        type : "POST",  
	        url : "/zs_jianzhang/uploadpic.jr", //图片上传出来的url，返回的是图片上传后的路径，http格式  
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
	//添加简章
	function save_jianzhang(zhaosheng_id){
		var content= $('#summernote').summernote('code');
		var title=$("#title").val();
		if(title!=null&&title!=""){
			$("#myform").ajaxSubmit({
				type : 'POST',
				url : "/zs_jianzhang/save_jianzhang.jr",
				data:{
					'zhaosheng_id':zhaosheng_id,'content':content
				},
				success : function(data) {
					if(data==1){
						 location.reload();
						//tanchuang('恭喜，设置成功');
					}else{
						tanchuang('很遗憾，设置失败');
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					tanchuang('很遗憾，设置失败');
				}
			});
		}else{
			layer.msg("请输入项目名称！");
		}
	}
</script>
</head>
<body>
<h1>${ckZhaoSheng.xuexiao_name }招生简章</h1>
<form  class="form-horizontal import_xueyuanshuju_dialog"
		enctype="multipart/form-data" id="myform" style="padding: 20px;">
  <div class="form-group">
      <label class="col-md-2 control-label">年份：</label>
      <div class="col-md-10">
        <select id="nianfen" name="nianfen">
          <option value="2017年">2017年</option>
          <option value="2018年">2018年</option>
          <option value="2019年">2019年</option>
        </select>
        <!-- <a href="javascript:void(0);" class="file">
          <input type="file" name="file_upload" id="file_upload">上传招生简章<i class="fa fa-upload"></i>
        </a>
        <span class="file_text" id="file_name"></span> -->
      </div>
    </div>
    <div class="form-group">
      <label class="col-md-2 control-label">项目名称：</label>
      <div class="col-md-10">
        <input type="text" id="title" name="title" />
      </div>
    </div>
    <div class="form-group">
      <label class="col-md-2 control-label">项目内容：</label>
      <div class="col-md-10">
        <div id="summernote"></div>
      </div>
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-md-2 control-label">&nbsp;</label>
      <div class="col-md-10">
        <button type="button" onclick="save_jianzhang(${ckZhaoSheng.zhaosheng_id })" class="btn btn-success btn-xm">提交</button>
        <button type="reset" class="btn btn-danger btn-xm">重置</button> 
      </div> 
    </div>
	</form>
</body>
</html>