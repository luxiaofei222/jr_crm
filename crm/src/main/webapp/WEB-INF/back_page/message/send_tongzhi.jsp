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

//添加新闻动态信息
function send_message(obg){
		 var markupStr= $('#summernote').summernote('code');//编辑器的内容
		 if(check_message_title()&&check_user_name()){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("发送中");
		 if(markupStr!=null&&markupStr!=""){
			 $("#myform").ajaxSubmit({
					type : 'POST',
					data:{'message_content':markupStr},
					url : "/back_message/send_message.jr",
					success : function(data) {
						if(data==1){
							tanchuang("提示：恭喜您，发送通知成功！");
							$('#myform')[0].reset();
							$('#summernote').summernote('code',"");
							$(obj).removeAttr("disabled");
							$(obj).html("发送");
						}else if(data==2){
							layer.msg("提示：您输入的手机号不存在！");
						}else if(data==3){
							layer.msg("提示：您输入的邮箱不存在！");
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
//检查是否输入用户名
function check_user_name(){
	var anniu=$("#anniu").html();
	if(anniu!="所有用户"){
		var user_name=$("#user_name").val();
		if(user_name!=null&&user_name!=""){
			$("#user_name_title").html("<i class='fa fa-check'></i>");
			return true;
		}else{
			layer.msg('请输入用户名！');
			return false;
		}
	}else{
		return true;
	}
}
//切换所有用户还是单个用户
function change_single(obj){
	var btn=$(obj).html();
	if(btn=="单个用户"){
		$("#user_message").show();
		$("#all_user").hide();
	}
}
function change_all(obj){
	var btn=$(obj).html();
	if(btn=="所有用户"){
		$("#all_user").show();
		$("#user_message").hide();
		$("#user_name_title").empty();
	}
	}
</script>
<style>
.send_user {
    display:inline-block;
    background-color: #f1c40f; 
    width: 70px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    color: #fff;
    border-radius:3px;
    text-decoration: none;
    padding: 0px 3px;
}
.send_user:hover {
    color: #fff;
    text-decoration: none;
    cursor: pointer;
}
.one_user {
    
}
.all_user {
    margin-bottom: 10px;
}
.form-group.send i.fa-check {
    margin-top: 35px;
}

</style>
<div style="padding: 20px;">
	<form enctype="multipart/form-data" id="myform" class="form-horizontal">
    <div class="form-group send">
      <label class="col-xs-2 control-label">发送给：</label>
      <div class="col-xs-9" id="all_user">
        	<label class="control-label" style="font-weight: normal;">所有用户</label>
        	<a onclick="change_single(this)"" class="send_user one_user">单个用户</a>
      </div>
      <div class="col-xs-9" id="user_message" style="display: none;">
        <a onclick="change_all(this)" id="anniu" class="send_user all_user">所有用户</a>
        <input type="text" onblur="check_user_name()" class="form-control" name="user_name" id="user_name" placeholder="请输入用户的联系电话或者邮箱">   
      </div>
      <div id="user_name_title"></div>
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
        <button type="button" onclick="send_message(this)" class="btn btn-success btn-xm">发送</button>
        <button type="button" onclick="close_layer()" class="btn btn-danger btn-xm">取消</button> 
      </div> 
    </div>
	</form>	
	</div>
    