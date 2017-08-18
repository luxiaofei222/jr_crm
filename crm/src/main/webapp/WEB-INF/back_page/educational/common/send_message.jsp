<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
		<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
    <link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
    <link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
    <script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script type="text/javascript" src="/dist/summernote.min.js"></script>
<script type="text/javascript" src="/dist/lang/summernote-zh-CN.min.js"></script>
<script>
$(function() {
   $('#summernote').summernote({
	height: 200,
	lang:"zh-CN",                 // 设置中文
	maxHeight: 200,             //  设置最大高度
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
        url : "/back_message/uploadpic.jr", //图片上传出来的url，返回的是图片上传后的路径，http格式  
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

//添加信息动态信息
function send_message(user_id){
		 var markupStr= $('#summernote').summernote('code');//编辑器的内容
		 if(check_message_title()){
		 if(markupStr!=null&&markupStr!=""){
			 $("#myform").ajaxSubmit({
					type : 'POST',
					data:{'message_content':markupStr,
						  'user_id':user_id},
					url : "/back_message/send_message.jr",
					success : function(data) {
						if(data==1){
							tanchuang("提示：恭喜您，发送通知成功！");
							$('#myform')[0].reset();
							$('#summernote').summernote('code',"");
						}else{
							 layer.msg("提示：很遗憾，发送通知失败！");
						}
					},
					error : function(XmlHttpRequest, textStatus, errorThrown) {
						 layer.msg("发送通知失败！");
					}
				});
		 }else{
			 layer.msg("请添加消息内容！");
		 }
		 }
}
//点击加入文件时
function annexes_add(){
	  var fileName="";
	  var name = $("#file_up").val();
	  fileName = name.split("\\").pop();  
    //截取文件后缀名 fileName=fileName.substring(0, fileName.lastIndexOf("."));
	  $("#file_name").html(fileName);
}
//关闭弹窗
function close_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
//检查是否输入消息标题
function check_message_title(){
	var message_title=$("#message_title").val();
	if(message_title!=null&&message_title!=""){
		$("#error_title").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入消息标题！');
		return false;
	}
}
</script>
<div style="padding: 20px;">
	<form enctype="multipart/form-data" id="myform" class="form-horizontal">
    <div class="form-group">
      <label class="col-xs-2 control-label">发送给：</label>
      <div class="col-xs-9">
        	<label class="control-label" style="font-weight: normal;">${user.user_nickname }</label>
      </div>
    </div>
      <div class="form-group">
      <label class="col-xs-2 control-label">信息标题：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_message_title()" class="form-control" name="message_title" id="message_title" placeholder="请输入标题">   
      </div>
      <div id="error_title"></div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">发送内容：</label>
      <div class="col-xs-9">
        <div id="summernote"></div>
      </div>
    </div>
  <div class="form-group">
				<label for="inputPassword1" class="col-xs-2 control-label">上传附件：</label>
				<div class="col-xs-9">
					<a href="javascript:void(0)" class="btn btn-info btn-xm"
						style="position: relative; float: left; display: block;"> <input
						type="file" id="file_up" name="file_up"  onchange="annexes_add()"
						style="position: absolute; opacity: 0;">点击上传
					</a>
					<div id="file_name"></div>
				</div>
			</div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-9">
        <button type="button" onclick="send_message(${user.user_id })" class="btn btn-success btn-xm">发送</button>
        <button type="button" onclick="close_layer()" class="btn btn-danger btn-xm">取消</button> 
      </div> 
    </div>
	</form>	
	</div>