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
        url : "/back_info/uploadpic.jr", //图片上传出来的url，返回的是图片上传后的路径，http格式  
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
function check_info_title(){
	var info_title=$("#info_title").val();
	if(info_title!=null&&info_title!=""){
		$("#error_biaoti").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入资讯标题！');
		return false;
	}
}
//检查新闻编辑
function check_info_user(){
	var info_user=$("#info_user").val();
	if(info_user!=null&&info_user!=""){
		$("#error_bianji").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入责任编辑！');
		return false;
	}
}
//检查新闻摘要
function check_info_zhaiyao(){
	var info_zhaiyao=$("#info_zhaiyao").val();
	if(info_zhaiyao!=null&&info_zhaiyao!=""){
		$("#error_zhaiyao").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入资讯摘要！');
		return false;
	}
}
//修改新闻动态信息
function update_course_info(info_id){
	 if (check_info_title()&&check_info_user()&&check_info_zhaiyao()) {
		 var markupStr= $('#summernote').summernote('code');//编辑器的内容
		 if(markupStr!=null&&markupStr!=""){
			 $("#myform").ajaxSubmit({
					type : 'POST',
					data:{'info_content':markupStr},
					url : "/back_info/update_corse_info.jr?info_id="+info_id,
					success : function(data) {
						if(data==1){
							tanchuang("提示：恭喜您，修改课程资讯成功！");
							//$('#myform')[0].reset();
							//$('#summernote').summernote('code',"");
						}else{
							 layer.msg("提示：很遗憾，修改失败！");
						}
					},
					error : function(XmlHttpRequest, textStatus, errorThrown) {
						 layer.msg("修改失败！");
					}
				});
		 }else{
			 layer.msg("请输入课程资讯内容！");
		 }
		} 
}

//关闭弹窗
function colse_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
</script>
<div style="padding: 20px;">
	<form enctype="multipart/form-data" id="myform" class="form-horizontal">
	 <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label">新闻类型：</label>
      <div class="col-xs-9">
        <select data-toggle="select" id="info_type" name="info_type" class="form-control select select-primary">
          <c:if test="${courseInfo.info_type!='考试试题' }">
          <option value="${courseInfo.info_type }">${courseInfo.info_type }</option>
          </c:if>
          <c:if test="${courseInfo.info_type=='考试试题' }">
          <option value="${courseInfo.info_type }">试题辅导（考试试题）</option>
          </c:if>
          <option value="考试动态">考试动态</option>
          <option value="热点专题">热点专题</option>
          <option value="课程相关">课程相关</option>
          <option value="报考指南">报考指南</option>
           <option value="考试试题">试题辅导（考试试题）</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">资讯标题：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_info_title()" value="${courseInfo.info_title }" class="form-control" name="info_title" id="info_title" placeholder="文章标题">  
      </div>
      <div id="error_biaoti"></div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">资讯来源：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" value="${courseInfo.info_laiyuan }" name="info_laiyuan" id="info_laiyuan" placeholder="文章来源">
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">责任编辑：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_info_user()" value="${courseInfo.info_user }"  class="form-control" name="info_user" id="info_user" placeholder="责任编辑">   
      </div>
      <div id="error_bianji"></div>
    </div>    
    <div class="form-group">
      <label class="col-xs-2 control-label">资讯摘要：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_info_zhaiyao()" value="${courseInfo.info_zhaiyao }" class="form-control" name="info_zhaiyao" id="info_zhaiyao" placeholder="文章摘要"> 
      </div>
      <div id="error_zhaiyao"></div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">资讯内容：</label>
      <div class="col-xs-9">
        <div id="summernote">${courseInfo.info_content }</div>
      </div>
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-9">
        <button type="button" onclick="update_course_info(${courseInfo.info_id })" class="btn btn-success btn-xm">提交</button>
        <button type="button" onclick="colse_layer()" class="btn btn-danger btn-xm">关闭</button> 
      </div> 
    </div>
	</form>	
	</div>