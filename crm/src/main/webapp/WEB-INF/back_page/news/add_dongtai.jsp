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
<script type="text/javascript" src="/dist/summernote.js"></script>
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
        url : "/back_news/uploadpic.jr", //图片上传出来的url，返回的是图片上传后的路径，http格式  
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
//检查新闻动态标题
function check_news_title(){
	var news_title=$("#news_title").val();
	if(news_title!=null&&news_title!=""){
		$("#error_biaoti").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入新闻动态标题！');
		return false;
	}
}
//检查新闻编辑
function check_news_eidit(){
	var news_eidit=$("#news_eidit").val();
	if(news_eidit!=null&&news_eidit!=""){
		$("#error_bianji").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入新闻编辑！');
		return false;
	}
}

function check_news_abstrack(){
	var news_abstrack=$("#news_abstrack").val();
	if(news_abstrack!=null&&news_abstrack!=""){
		$("#error_zhaiyao").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入新闻摘要！');
		return false;
	}
}
//添加新闻动态信息
function add_news_dongtai(){
	 if (check_news_title()&&check_news_eidit()&&check_news_abstrack()) {
		 var markupStr= $('#summernote').summernote('code');//编辑器的内容
		 if(markupStr!=null&&markupStr!=""){
			 $("#myform").ajaxSubmit({
					type : 'POST',
					data:{'news_content':markupStr},
					url : "/back_news/save_news.jr",
					success : function(data) {
						if(data==1){
							tanchuang("提示：恭喜您，添加新闻动态成功！");
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
			 layer.msg("请添加新闻动态内容！");
		 }
		} 
}
</script>
<div style="padding: 20px;">
	<form enctype="multipart/form-data" id="myform" class="form-horizontal">
    <div class="form-group">
      <label class="col-xs-2 control-label">文章标题：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_news_title()" class="form-control" name="news_title" id="news_title" placeholder="文章标题">  
      </div>
      <div id="error_biaoti"></div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">文章来源：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" name="news_laiyuan" id="news_laiyuan" placeholder="文章来源">
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">责任编辑：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_news_eidit()" class="form-control" name="news_eidit" id="news_eidit" placeholder="责任编辑">   
      </div>
      <div id="error_bianji"></div>
    </div>    
    <div class="form-group">
      <label class="col-xs-2 control-label">文章摘要：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_news_abstrack()" class="form-control" name="news_abstrack" id="news_abstrack" placeholder="文章摘要"> 
      </div>
      <div id="error_zhaiyao"></div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">引用链接：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" id="" placeholder="http://"> 
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">页面标题：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" id="" placeholder="页面标题"> 
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">页面关键字：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" id="" placeholder="页面关键字"> 
      </div>
    </div>
     <div class="form-group">
      <label class="col-xs-2 control-label">页面描述：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" id="" placeholder="页面描述"> 
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
        <button type="button" onclick="add_news_dongtai()" class="btn btn-success btn-xm">提交</button>
        <button type="reset" class="btn btn-danger btn-xm">重置</button> 
      </div> 
    </div>
	</form>	
	</div>