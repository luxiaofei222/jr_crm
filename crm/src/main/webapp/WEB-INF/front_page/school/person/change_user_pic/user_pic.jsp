<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" type="text/css" href="/css/school/front/person/user_pic.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script src="/js/school/front/person/cropbox.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>更换用户头像</title>
</head>
<body>
	<div class="container">
  <div class="imageBox">
    <div class="thumbBox"></div>
    <div class="spinner" style="display: none">Loading...</div>
  </div>
   <form enctype="multipart/form-data" id="myform" class="form-horizontal">
  <div class="action"> 
    <!-- <input type="file" id="file" style=" width: 200px">-->
    <div class="new-contentarea tc"> <a href="javascript:void(0)" class="upload-img">
      <label for="upload-file">浏览图像</label>
      </a>
      <input type="file"  name="upload_file" id="upload-file" />
    </div>
    <input type="button" id="btnCrop"  class="Btnsty_peyton" value="裁切">
    <input type="button" id="btnZoomIn" class="Btnsty_peyton" value="+"  >
    <input type="button" id="btnZoomOut" class="Btnsty_peyton" value="-" >
  </div>
  <div class="cropped"></div>
  </form>
</div>
<div class="footer_btn">
<button class="upload" onclick="shangchuan_touxiang(this,${user_id})">上传头像</button>
<button class="cancel" onclick="close_layer()">取消</button>
</div>
<script type="text/javascript">
var img="";
//关闭弹窗
function close_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
$(window).load(function() {
	var options =
	{
		thumbBox: '.thumbBox',
		spinner: '.spinner',
		imgSrc: '/images/school/front/person/avatar.png'
	}
	var cropper = $('.imageBox').cropbox(options);
	$('#upload-file').on('change', function(){
		var reader = new FileReader();
		reader.onload = function(e) {
			options.imgSrc = e.target.result;
			cropper = $('.imageBox').cropbox(options);
		}
		reader.readAsDataURL(this.files[0]);
		this.files = [];
	})
	$('#btnCrop').on('click', function(){
		img = cropper.getDataURL();
		$('.cropped').html('');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
	})
	$('#btnZoomIn').on('click', function(){
		cropper.zoomIn();
	})
	$('#btnZoomOut').on('click', function(){
		cropper.zoomOut();
	})
});
//修改头像
function shangchuan_touxiang(obj,user_id){
	$(obj).attr({"disabled":"disabled"});
	$(obj).html("上传中");
	 $("#myform").ajaxSubmit({
			type : 'POST',
			data:{'user_id':user_id,'headpic':img},
			url : "/person/update_person_pic.html",
			success : function(data) {
				if(data==1){
					tanchuang("提示：恭喜您，修改头像成功！");
					$(obj).html("上传头像");
					$(obj).removeAttr("disabled");
				}else if(data==2){
					layer.msg("提示：请先点击剪切按钮，对图片进行剪切！");
					$(obj).html("上传头像");
					$(obj).removeAttr("disabled");
				}else{
					 layer.msg("提示：很遗憾，修改头像失败！");
					 $(obj).html("上传头像");
						$(obj).removeAttr("disabled");
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				 layer.msg("修改头像失败！");
				 $(obj).html("上传头像");
					$(obj).removeAttr("disabled");
			}
		});
}
</script>
</body>
</html>