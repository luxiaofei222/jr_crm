<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/edu/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="/css/edu/entryinfo/e_tankuang.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script src="/js/edu/jquery-ui.min.js" type="text/javascript"></script>
<script>
$(function(){
	$(".loading").hide();
})
//关闭弹窗
function close_layer() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}
//获取文件名
function get_file_name(){
	 var fileName="";
	  var name = $("#upfile").val();
	  var extStart=name.lastIndexOf(".");
      var ext=name.substring(extStart,name.length).toUpperCase();
      if(ext!=".ZIP"){
    	  layer.msg('您上传的视频文件类型错误，目前只支持zip格式');
    	  return false;
      }else{
    	  fileName = name.split("\\").pop(); 
    	  $("#file_name").val(fileName);
    	  return true;
      }
}
//导入信息
function satrt_daoru(){
	if(get_file_name()){
		$("#daoru").attr({"disabled":"disabled"});
		$(".loading").show();
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/uload_zip/upload_zip_pic.jr",
			success : function(data) {
				$("#tishi").html(data);
				$(".loading").hide();
				$("#daoru").removeAttr("disabled");//将按钮可用
				tanchuang('报名学员图片导入成功！');
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				$(".loading").hide();
				 $("#daoru").removeAttr("disabled");//将按钮可用
				tanchuang('很遗憾，系统发生错误，报名学员图片导入失败！');
			}
		});
	}
}
</script>
 <div class="loading">
   <div class="loader-inner ball-spin-fade-loader">
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <p style="color: #fff; position: absolute; top: 45px; left: -35px;">正在导入数据...</p>
  </div>    
</div>
<div class="daoru_shuju_dialog">
<form class="form-horizontal import_xueyuanshuju_dialog" enctype="multipart/form-data" id="myform" style="padding:20px;">
     <div class="form-group">
      <label class="col-xs-2 control-label" style="text-align: right;">选择压缩包：</label>
      <div class="col-xs-5">
        <input type="text" class="form-control" id="file_name">
      </div>
      <div class="col-xs-5">
        <a href="javascript:void(0)" class="btn btn-info btn-sm" style="position: relative; margin-right: 35px;">浏览
          <input type="file" id="upfile" onchange="get_file_name()" name="upfile" style="position: absolute;opacity:0; top:0px;left:0px; width: 100%; height:100%;" />
        </a>
      </div>
    </div>
    <div class="form-group">
      <label for="inputPassword1" class="col-xs-2 control-label"></label>
      <div class="col-xs-10" id="tishi">
       <!-- 提示信息 --> 
       <p>说明：导入的文件压缩包必须是zip格式的，其他会发生系统错误！</p>
       <p style="color: orange;">照片命名：学历证书：XLZ+身份证号.jpg；身份证正面：SFZ+身份证号.jpg；身份证背面：SFB+身份证号.jpg
       ；学员照片：XYP+身份证号.jpg；其他证件照：QTZ+身份证号.jpg</p>
      </div>
    </div>
    <div class="form-group" style="margin-top: 60px;">
       <label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-10">
        <button type="button" onclick="satrt_daoru()" class="btn btn-info btn-sm" style="margin-right:35px;">开始导入</button> 
        <button type="button" onclick="close_layer()" class="btn btn-success btn-sm">关闭</button> 
      </div> 
    </div>
	</form>	
</div>