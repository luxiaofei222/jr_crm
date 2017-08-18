<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style type="text/css">
.file {
    position: relative;
    display: inline-block;
    background:#00AAEF;
    border:none;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color:white;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
    margin-top:10px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background:#25BDFB;
    color:white;
    text-decoration: none;
}
.form-horizontal .form-group .right_wz{
		text-align:right;
}
</style>
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
	}); 
//添加广告
function save_adver(){
	var adver_name=$("#adver_name").val();
	if(adver_name!=null&&adver_name!=""){
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_adver/save_adver.jr",
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                    tanchuang('恭喜您，添加成功');
                    $('#myform')[0].reset();
				}else{
					tanchuang('很遗憾，添加失败');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，添加失败');
			}
		});
	}else{
		layer.msg("请输入广告名称！")
	}
		
}
//关闭弹窗
function colse_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
</script>
<form class="form-horizontal" enctype="multipart/form-data" id="myform" style="padding:20px;">
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">广告名称：</label>
      <div class="col-xs-8">
        <input class="form-control" id="adver_name" name="adver_name" placeholder="广告名称">         
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">广告链接：</label>
      <div class="col-xs-8">
        <input class="form-control" id="adver_link" name="adver_link" placeholder="http://">         
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">广告位置：</label>
      <div class="col-xs-8">
         <select class="form-control select select-primary left top_select" name="adver_type" id="adver_type" data-toggle="select">
            <option value="首页">首页</option>
            <option value="手机首页">手机首页</option>
          </select>
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">大图：</label>
      <div class="col-xs-8">
           <a href="javascript:void(0);" class="file">
          <input type="file" name="file_pic" id="file_upload">点击上传banner图
          </a>
           <span style="color:#F12727">注：建议banner图尺寸：1587px*400px</span>
      </div>
    </div>
	   <div class="form-group" style="margin-top:15px;height:100px;">
  		<label class="col-xs-2 control-label right_wz">预览图片：</label>
  		<div class="col-xs-8">
				<div class="image_container">
  				<img id="preview" width="400" height="100" src="/images/school/back/adver/adver_yulan.jpg">
  			</div>  				
  		</div>
    </div>
    <div class="form-group" style="margin-top:36px;">
          <div class="col-xs-6 right_wz">
          <button type="button" class="btn btn-warning btn-lg" onclick="colse_layer()" style="width:100px;">取消</button>
          </div>
          <div class="col-xs-6" style="text-align:left;">
          <button type="button" onclick="save_adver()" class="btn btn-primary btn-lg" style="width:100px;">保存</button>
          </div>
        </div>
	</form>	