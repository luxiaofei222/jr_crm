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
<!--     <script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script> -->
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
//添加新闻动态信息
function add_course_info(obj){
	 if (check_info_title()&&check_info_user()&&check_info_zhaiyao()) {
		 var markupStr= $('#summernote').summernote('code');//编辑器的内容
		 var info_type=$("#info_type").val();
		 if(info_type=="考试动态"||info_type=="热点专题"){
				var is_dic="否";
			}else{
				var is_dic="是";
			}
		 if(markupStr!=null&&markupStr!=""){
			 $(obj).attr({"disabled":"disabled"});
			 $("#myform").ajaxSubmit({
					type : 'POST',
					data:{'info_content':markupStr,
						'is_dic':is_dic},
					url : "/back_info/save_corse_info.jr",
					success : function(data) {
						if(data==1){
							tanchuang("提示：恭喜您，添加课程资讯成功！");
							//location.reload();
							//$('#myform')[0].reset();
							//$('#summernote').summernote('code',"");
							//$(obj).removeAttr("disabled");
							$.post("/back_info/to_add_course_info.jr",function(data){
								$("#conten_list").html(data);
							})
						}else{
							 layer.msg("提示：很遗憾，添加失败！");
						}
					},
					error : function(XmlHttpRequest, textStatus, errorThrown) {
						 layer.msg("添加失败！");
					}
				});
		 }else{
			 layer.msg("请添加课程资讯内容！");
		 }
		} 
}
//切换课程等级
function getshow_dic(){
	var info_type=$("#info_type").val();
	if(info_type=="考试动态"||info_type=="热点专题"){
		$("#dengji_dic").css("display","none");
	}else{
		$("#dengji_dic").show();
	}
}
//切换二级课程以及等级
function get_sub_course(){
	var parent_course_id=$("#parent_course_id").val();
	$.post("/back_user/get_sub_course.jr",{
		'course_id':parent_course_id
	},function(data){
		$("#course_id").html(data);
	})
	//显示等级
	$.post("/back_video/get_dic_list.jr",{
		'course_id':parent_course_id,
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
	<form enctype="multipart/form-data" id="myform" class="form-horizontal">
	 <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label">新闻类型：</label>
      <div class="col-xs-9">
        <select data-toggle="select" onchange="getshow_dic()" id="info_type" name="info_type" class="form-control select select-primary">
          <option value="考试动态">考试动态</option>
          <option value="热点专题">热点专题</option>
          <option value="课程相关">课程相关</option>
          <option value="报考指南">报考指南</option>
           <option value="考试试题">试题辅导（考试试题）</option>
        </select>
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label">课程类别：</label>
      <div class="col-xs-9">
        <select data-toggle="select" onchange="get_sub_course()" id="parent_course_id" name="parent_course_id" class="form-control select select-primary">
          <c:forEach items="${courseMenus }" var="course_menu">
          <option value="${course_menu.course_id }">${course_menu.course_name }</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label">选择课程：</label>
      <div class="col-xs-9">
        <select data-toggle="select" onchange="get_dic_list()" id="course_id" name="course_id" class="form-control select select-primary">
          <c:forEach items="${coursesubMenus }" var="course_sub">
          <option value="${course_sub.course_id }">${course_sub.course_name }</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group" id="dengji_dic" style="margin-top:15px;display: none" >
      <label class="col-xs-2 control-label">课程等级(科目)：</label>
      <div class="col-xs-9">
        <select data-toggle="select" id="dictionary_id" name="dictionary_id" class="form-control select select-primary">
         <c:forEach items="${dictionaries }" var="dic">
          <option value="${dic.dictionary_id }">${dic.dictionary_name }</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">资讯标题：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_info_title()" class="form-control" name="info_title" id="info_title" placeholder="文章标题">  
      </div>
      <div id="error_biaoti"></div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">资讯来源：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" name="info_laiyuan" id="info_laiyuan" placeholder="文章来源">
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">责任编辑：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_info_user()" class="form-control" name="info_user" id="info_user" placeholder="责任编辑">   
      </div>
      <div id="error_bianji"></div>
    </div>    
    <div class="form-group">
      <label class="col-xs-2 control-label">资讯摘要：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_info_zhaiyao()" class="form-control" name="info_zhaiyao" id="info_zhaiyao" placeholder="文章摘要"> 
      </div>
      <div id="error_zhaiyao"></div>
    </div>
<!--     <div class="form-group">
      <label class="col-xs-2 control-label">引用链接：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" id="" placeholder="http://"> 
      </div>
    </div> -->
    <div class="form-group">
      <label class="col-xs-2 control-label">页面标题：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" name="meta_title" id="meta_title" placeholder="页面标题"> 
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">页面关键字：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" name="meta_key" id="meta_key" placeholder="页面关键字"> 
      </div>
    </div>
     <div class="form-group">
      <label class="col-xs-2 control-label">页面描述：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" name="meta_dis" id="meta_dis" placeholder="页面描述"> 
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">资讯内容：</label>
      <div class="col-xs-9">
        <div id="summernote"></div>
      </div>
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-9">
        <button type="button" onclick="add_course_info(this)" class="btn btn-success btn-xm">提交</button>
        <button type="reset" class="btn btn-danger btn-xm">重置</button> 
      </div> 
    </div>
	</form>	
	</div>