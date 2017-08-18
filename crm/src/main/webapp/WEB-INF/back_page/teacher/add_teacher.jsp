<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    margin-top:54px;
    margin-left:10px;
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
//添加教师
function save_teacher(){
	if(check_teacher_name()&&check_teacher_course()&&add_img()&&check_teacher_dis()){
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_teacher/save_teacher.jr",
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
	}
}

//点击加入文件时
function add_img(){
	  var name = $("#file_upload").val();
	    var extStart=name.lastIndexOf(".");
        var ext=name.substring(extStart,name.length).toUpperCase();
        if(name!=null&&name!=""){
        	  if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
              	layer.msg("图片限于bmp,png,gif,jpeg,jpg格式");
               	return false;
              }else{
              	 return true;
              }
        }else{
        	layer.msg("请上传教师头像！");
           	return false;
        }
}
//检查教师姓名
function check_teacher_name(){
	  var teacher_name=$("#teacher_name").val();
	  if(teacher_name!=null&&teacher_name!=""){
			return true;
		}else{
			layer.msg('请填写教师姓名！');
			return false;
		}
}
function check_teacher_course(){
	  var teacher_course=$("#teacher_course").val();
	  if(teacher_course!=null&&teacher_course!=""){
			return true;
		}else{
			layer.msg('请填写教师称号！');
			return false;
		}
}
//检查教师简介
function check_teacher_dis(){
	  var teacher_dis=$("#teacher_dis").val();
	  if(teacher_dis!=null&&teacher_dis!=""){
			return true;
		}else{
			layer.msg('请输入教师简介！');
			return false;
		}
}
//关闭弹窗
function colse_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
//获取二级课程
function get_sub_couerse(){
	var parent_id=$("#parent_id").val();
	$.post("/back_user/get_sub_course.jr",{
		'course_id':parent_id
	},function(data){
		$("#course_id").html(data);
	})
}

//提示信息
function tishi_cotent(){
	var teacher_dis=$("#teacher_dis").val();
	if(teacher_dis!=""&&teacher_dis!=null){
		if(teacher_dis.length>40){
			$("#tishi").html("您输入字数<span style='color:red;'>超过</span>"+"<span style='color:red;'>" + (teacher_dis.length-40)+ "</span>"+"个字");
		}else{
			$("#tishi").html("您已经输入" + "<span style='color:red;'>" + teacher_dis.length + "</span>" + "个字");
		}
	}else{
		$("#tishi").html("请输入您要评论的内容！")
	}
}
</script>
<form class="form-horizontal" enctype="multipart/form-data" id="myform" style="padding:20px;">
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">姓名：</label>
      <div class="col-xs-8">
        <input class="form-control" onblur="check_teacher_name()" id="teacher_name" name="teacher_name" placeholder="输入教师姓名">         
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">教师称号：</label>
      <div class="col-xs-8">
        <input class="form-control" onblur="check_teacher_course()" id="teacher_course" name="teacher_course" placeholder="输入教师姓名">         
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">性别：</label>
      <div class="col-xs-8">
            <select data-toggle="select" id="teacher_sex" name="teacher_sex" class="form-control select select-primary">
              <option value="男">男</option>
              <option value="女">女</option>
          </select>
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">电话：</label>
      <div class="col-xs-8">
        <input class="form-control" id="teacher_phone" name="teacher_phone" placeholder="输入教师电话">         
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">教授科目类别：</label>
      <div class="col-xs-8">
            <select data-toggle="select" onchange="get_sub_couerse()"name="course_parent_id"  id="parent_id" class="form-control select select-primary">
              <c:forEach items="${courseMenus }" var="course_menu">
              <option value="${course_menu.course_id }">${course_menu.course_name }</option>
              </c:forEach>
          </select>
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">教授科目：</label>
      <div class="col-xs-8">
            <select data-toggle="select" id="course_id" name="course_id" class="form-control select select-primary">
              <c:forEach items="${coursesubMenus }" var="course_sub">
              <option value="${course_sub.course_id }">${course_sub.course_name }</option>
              </c:forEach>
          </select>
      </div>
    </div>
	   <div class="form-group" style="margin-top:15px;">
  		<label class="col-xs-2 control-label right_wz">头像：</label>
  		<div class="col-xs-8 clearfix">
				<div class="pull-left">
  				<img id="preview"  width="84" height="84" style="background:url('/images/school/back/teacher/touxbg.png') no-repeat">
  			</div>
  				<a href="javascript:void(0);" class="file pull-left">
  				<input type="file" onchange="add_img()" name="file_upload" id="file_upload">点击上传头像
  				</a>
  		</div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">简介：</label>
      <div class="col-xs-8">
        <textarea class="form-control" id="teacher_dis"  oninput="tishi_cotent()" name="teacher_dis" rows="3" placeholder="请输入教师简介，不超过40字" style="color:#444;"></textarea>         
      </div>
    </div>
    <div class="form-group" style="margin-top:5px;">
    	<div class="col-xs-2"></div>
       <p id="tishi" class="col-xs-8">请输入教师简介！</p>
    </div>
      	<div class="form-group" style="margin-top:15px;">
      		<div class="col-xs-6 right_wz">
      		<button type="button" onclick="colse_layer()" class="btn btn-warning btn-lg" style="width:100px;">取消</button>
      		</div>
          <div class="col-xs-6" style="text-align:left;">
          <button type="button" onclick="save_teacher()" class="btn btn-primary btn-lg" style="width:100px;">保存</button>
          </div>
      	</div>
	</form>	