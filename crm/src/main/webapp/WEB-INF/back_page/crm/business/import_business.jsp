<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
	<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
    <link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
    <link rel="stylesheet" href="/css/crm/index/qiyeku.css" />
    <script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>
//开始导入
function satrt_daoru(){
	$("#daoru").attr({"disabled":"disabled"});
	var name = $("#upfile").val();
	if(name!=""&&name!=null){
		$(".loading").css("display","block");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/up_excle/upload.jr",
			success : function(data) {
				$("#tishi").html(data);
				$(".loading").css("display","none");
				 $("#daoru").removeAttr("disabled");//将按钮可用
				tanchuang('恭喜您，导入成功');
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				$(".loading").css("display","none");
				 $("#daoru").removeAttr("disabled");//将按钮可用
				tanchuang('很遗憾，导入失败，系统发生错误');
			}
		});
	}else{
		layer.msg("请选择您要导入的文件！")
	}
}
//获取文件名
function get_file_name(){
	 var fileName="";
	  var name = $("#upfile").val();
	  fileName = name.split("\\").pop(); 
	  $("#file_name").val(fileName);
}
//关闭弹窗
function close_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
</script>
<div class="loading" style="display: none;">
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
<form class="form-horizontal import_qiyeshuju_dialog" enctype="multipart/form-data" id="myform" style="padding:20px;">
     <div class="form-group">
      <label class="col-xs-3 control-label" style="text-align: right;">选择数据：</label>
      <div class="col-xs-4">
        <input type="text" class="form-control" id="file_name">
      </div>
      <div class="col-xs-5">
        <a href="javascript:void(0)" class="btn btn-info btn-sm" style="position: relative; margin-right: 35px;">浏览数据
          <input type="file" id="upfile" onchange="get_file_name()" name="upfile" style="position: absolute;opacity:0; top:0px;left:0px; width: 60px;" />
        </a>
        <a href="/template/template.xlsx" download="template.xlsx" class="btn btn-warning btn-sm">下载模板</a>
      </div>
    </div>
    <div class="form-group">
      <label for="inputPassword1" class="col-xs-3 control-label"></label>
      <div class="col-xs-9" id="tishi">
       <!-- 提示信息 --> 
       <p>说明：请先下载模板文件，按照要求的格式填写信息，否则导入失败或者出现混乱！</p>
      </div>
    </div>
    <div class="form-group" style="margin-top: 60px;">
       <label for="inputPassword1" class="col-xs-3 control-label">&nbsp;</label>
      <div class="col-xs-9">
        <button type="button" onclick="satrt_daoru()" class="btn btn-info btn-sm" style="margin-right:35px;">开始导入</button> 
        <button type="button" onclick="close_layer()" class="btn btn-success btn-sm">关闭</button> 
      </div> 
    </div>
	</form>	