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
<title>添加章</title>
</head>
<script>
var oTimer = null;
  //检查章的名称是否为null
  function check_name(){
	  var zhang=$("#file_name").val();
	  if(zhang!=null&&zhang!=""){
			return true;
		}else{
			layer.msg('请输入课件名称！');
			return false;
		}
  }
  //监听上传进度
  function getProgress() {
		var now = new Date();
	    $.ajax({
	        type: "post",
	        dataType: "json",
	        url: "/back_video/progress.jr",
	        data: now.getTime(),
	        success: function(data) {
	            $("#progress_bar").css("width",data.percent);//上传的百分比
	            $("#has_upload").html("已上传："+data.hasread); 
	          	$("#baifenbi").html(data.percent);
	            $("#upload_speed").html("速度："+data.speed+"/s");//上传速度
	            if(data.percent=="100%"){
	            	clearInterval(oTimer);
	            	 layer.msg('上传成功！');
	            	$("#sbtn").removeAttr("disabled");
	            }
	        },
	        error: function(err) {
	        	$("#progress_percent").html("Error");
	        }
	    });
	}
  /**
   * 提交上传文件
   */
  function fSubmit(obj) {
	  $("#progress_id").removeClass("hide");
	  var file_up=$("#file_up").val();
  if(video_add()){
	  $(obj).attr("disabled","disabled"); 
	 	 if(file_up!=null&&file_up!=""){
	   			  if (check_name()) {
	   			    oTimer = setInterval("getProgress()", 1000);
	   			   	save_ware();
	   				  }else{
	   					$(obj).removeAttr("disabled");
	   				  }
	   	  }else{
	   		  layer.msg('请选择您要上传的视频');
	   		 $(obj).removeAttr("disabled"); 
	   	  }
  }
  }
  //点击加入文件时
  function video_add(){
	  var fileName="";
	  var name = $("#file_up").val();
	  fileName = name.split("\\").pop();  
      //截取文件后缀名 filezhouzhui = name.substring( name.lastIndexOf("."), name.length).toUpperCase();
	  var byteSize  = $("#file_up")[0].files[0].size;
	     if(byteSize<500*1024*1024){
	    	 $("#file_name_file").html(fileName);
	    	 return true;
	     }else{
	    	 layer.msg('文件大小不能超过500MB'); 
	    	 return false;
	     }
  }
  //保存章节名称
  function save_ware(){
	  var bol=false;
			$("#myform").ajaxSubmit({
				type : 'POST',
				url : "/notice/save_oa_file.jr",
				success : function(data) {
					if(data==1){
						//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
						console.log("成功"); 
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					console.log("失败");
				}
			});
  }
</script>
<body>
	<div style="padding: 20px;">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group">
				<label class="col-xs-2 control-label">文件名称：</label>
				<div class="col-xs-10">
					<input type="text" class="form-control" onblur="check_name()"
						name="file_name" id="file_name" placeholder="文件名称" style="margin-left: 0px;">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword1" class="col-xs-2 control-label">上传课件：</label>
				<div class="col-xs-10">
					<a href="javascript:void(0)" class="btn btn-info btn-xm"
						style="position: relative; float: left; display: block;"> <input
						type="file" id="file_up" name="file_up" onchange="video_add()"
						style="position: absolute; opacity: 0;">点击上传
					</a>
					<div id="file_name_file" style="line-height: 3;margin-left: 105px;"></div>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
				<div class="col-xs-10">
					<div class="progress hide" id="progress_id">
						<div class="progress-bar" id="progress_bar"></div>
						<div id="baifenbi"></div>
					</div>
					<div id="has_upload"></div>
					<div id="upload_speed"></div>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
				<div class="col-xs-10">
					<button type="button" id="sbtn" onclick="fSubmit(this)"
						class="btn btn-success btn-xm">保存</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>