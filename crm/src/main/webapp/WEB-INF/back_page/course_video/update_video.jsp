<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
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
	focus: true,                  // 自动获取焦点
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
        url : "/back_video/uploadpic.jr", //图片上传出来的url，返回的是图片上传后的路径，http格式  
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
//检查课程名称
function check_course_name(){
	var video_name=$("#video_name").val();
	if(video_name!=null&&video_name!=""){
		$("#panduan2").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入课程名称！');
		return false;
	}
}
//检查课程描述
function check_video_dis(){
	var video_dis=$("#video_dis").val();
	if(video_dis!=null&&video_dis!=""){
		$("#panduan3").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入课程描述！');
		return false;
	}
}
//添加课程信息
function update_course_video(video_id){
	var file_upload=$("#file_upload").val();
	 if (check_course_name()&&check_video_dis()) {
		 var markupStr= $('#summernote').summernote('code');//编辑器的内容
		 $("#myform").ajaxSubmit({
				type : 'POST',
				data:{'course_jieshao':markupStr},
				url : "/back_video/update_course_video.jr?video_id="+video_id,
				success : function(data) {
					if(data==1){
						tanchuang("提示：恭喜您，修改课程成功！");
					}else{
						 layer.msg("提示：很遗憾，修改失败！");
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					 layer.msg("修改失败！");
				}
			});
		} 
}
//获取二级菜单
function get_sub_course(){
	$("#zi_menu").remove();
	var course_parent_id=$("#course_parent_id").val();
	$.post("/back_user/get_sub_course.jr",{
		'course_id':course_parent_id
	},function(data){
		$("#course_id").html(data);
	})
	//显示等级
	$.post("/back_video/get_dic_list.jr",{
		'course_id':course_parent_id,
		'type':1
	},function(data){
		$("#dictionary_id").html(data);
	})
}
//显示等级
function get_dic_list(){
	var course_id=$("#course_id").val();
	//显示等级
	$.post("/back_video/get_dic_list.jr",{
		'course_id':course_id,
		'type':2
	},function(data){
		$("#dictionary_id").html(data);
	})
}
</script>
<div style="padding: 20px;">
	<form enctype="multipart/form-data" id="myform" class="form-horizontal add_class_dialog">
    <div class="form-group">
      <label class="col-xs-2 control-label">封面：</label>
      <div class="col-xs-9">
        <div class="image_container">
          <img id="preview" src="${courseVideo.video_pic }" width="105" height="60">
        </div>
        <div class="input_container">
          <input id="file_upload" name="file_pic" type="file" />
          <input type="button" value="上传封面图" /> 
          <span>建议：（225px*130px）</span>
        </div>  
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">课程名称：</label>
      <div class="col-xs-9">
        <input type="text" value="${courseVideo.video_name }" onblur="check_course_name()" class="form-control" name="video_name" id="video_name" placeholder="课程名称">
      </div>
      <div id="panduan2"></div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">课程分类：</label>
      <div class="col-xs-3">
          <select  onchange="get_sub_course()" id="course_parent_id" name="course_parent_id" class="form-control select select-primary">
          <option id="zi_menu" value="${courseMenumain.course_id }">${courseMenumain.course_name }</option>
          <c:forEach items="${courseMenus }" var="cou_menu">
          <option value="${cou_menu.course_id }">${cou_menu.course_name }</option>
          </c:forEach>
        </select>  
      </div>
      <label class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-3">
          <select  onchange="get_dic_list()" id="course_id" name="course_id" class="form-control select select-primary">
          <option value="${courseMenu.course_id }">${courseMenu.course_name }</option>
          <c:forEach items="${coursesubMenus }" var="cou_menu">
          <option value="${cou_menu.course_id }">${cou_menu.course_name }</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">级别：</label>
      <div class="col-xs-3">
	    <select  id="dictionary_id" name="dictionary_id" class="form-control select select-primary">
          <option value="${dictionary.dictionary_id }">${dictionary.dictionary_name }</option>
          <c:forEach items="${dictionaries }" var="dic">
          <option value="${dic.dictionary_id }">${dic.dictionary_name }</option>
          </c:forEach>
        </select>     
  	  </div>
	  <label class="col-xs-2 control-label">班型：</label>    
      <div class="col-xs-3">
	    <select  name="banxing" id="banxing" class="form-control select select-primary">
          <option value="${courseVideo.banxing }">${courseVideo.banxing }</option>
          <option value="上岗实操班">上岗实操班</option>
          <option value="模考押题班">模考押题班</option>
          <option value="真题解析班">真题解析班</option>
          <option value="考点预测班">考点预测班</option>
          <option value="精讲班">精讲班</option>
        </select>    
  	  </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">主讲教师：</label>
      <div class="col-xs-3">
        <select  id="teacher_id" name="teacher_id" class="form-control select select-primary">
           <option value="${teacher.teacher_id }">${teacher.teacher_name }</option>
          <c:forEach items="${teachers }" var="teacher">
          <option value="${teacher.teacher_id }">${teacher.teacher_name }</option>
          </c:forEach>
        </select>
      </div> 
      <label class="col-xs-2 control-label">价格：</label>
      <div class="col-xs-3">
        <input type="text" class="form-control" value="${courseVideo.video_money_now }" name="video_money_now" id="video_money_now" placeholder="价格"> 
      </div> 
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">到期时间：</label>
      <div class="col-xs-3">
        <input type="text" class="form-control" value='<fmt:formatDate value="${courseVideo.daoqi_time }"/>'  name="daoqi_time_str" id="daoqi_time" placeholder="请选择到期时间"> 
      </div>
      <script>
      layui.use('laydate', function(){
    	  var laydate = layui.laydate;
    	  var start = {
    	    max: '2099-06-16'
    		,format: 'YYYY-MM-DD'
    	  };
    	  
    	  document.getElementById('daoqi_time').onclick = function(){
    	    start.elem = this;
    	    laydate(start);
    	  }
    	  
    	});
      </script>
      <div id="panduan3"></div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">课程描述：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" value="${courseVideo.video_dis }" onblur="check_video_dis()" name="video_dis" id="video_dis" placeholder="课程描述"> 
      </div>
      <div id="panduan3"></div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">课程介绍：</label>
      <div class="col-xs-9">
        <div id="summernote">${courseVideo.course_jieshao }</div>
      </div>
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-9">
        <button type="button" onclick="update_course_video(${courseVideo.video_id})" class="btn btn-success btn-xm">提交</button>
        <button type="reset" class="btn btn-danger btn-xm">重置</button> 
      </div> 
    </div>
	</form>	
	</div>