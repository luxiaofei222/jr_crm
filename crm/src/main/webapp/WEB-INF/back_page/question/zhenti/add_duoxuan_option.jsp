<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="/css/question/back/jiazaidonghua.css">
<link rel="stylesheet" type="text/css" href="/css/question/back/chapteroption.css">
<link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/question/jquery.raty.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script type="text/javascript" src="/dist/summernote.js"></script>
<script type="text/javascript" src="/dist/lang/summernote-zh-CN.min.js"></script>

<title>添加题目</title>
<style>
.image_container{
  position:absolute;
  left:0px;
  top:0px;
  z-index:1;
}
.file {
    display: inline-block;
    background:#00AAEF;
    border:none;
    border-radius: 4px;
    overflow: hidden;
    color:white;
    text-decoration: none;
    text-indent: 0;
    margin-top:54px;
    width:92px;
    height:33px;
    line-height:33px;
    text-align:center;
    position:absolute;
    left:0px;
    top:4px;
    z-index:2;
}
#preview{
  width:92px;
  height:92px;
  background:url('/images/question/back/add_img.png') no-repeat;
  border:1px solid #ccc;
}
.file input{
    position: absolute;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover{
    background:#25BDFB;
    color:white;
    text-decoration: none;
}
.form-horizontal .form-group .right_wz{
    text-align:right;
    font-size:16px;
}
.checkbox-inline{
  overflow:hidden;
}
.options{
  width:20px;
  height:20px;
  float:left;
}
.checkbox-inline span{
  font-size:20px;
  display:inline-block;
  line-height:30px;
  margin-left:10px;
  float:left;
}
</style>
</head>
<script>
//关闭弹窗
function colse_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
//检题目名称
function check_question_name(){
	var question_name=$("#question_name").val();
	if(question_name!=null&&question_name!=""){
		return true;
	}else{
		layer.msg('请输入题目内容！');
		return false;
	}
}
//检查分值
function check_fenzhi(){
	var fenzhi=$("#fenzhi").val();
	if(fenzhi!=null&&fenzhi!=""){
		if(isNaN(fenzhi)){
			layer.msg('请输入数字！');
			return false;
		}else{
			return true;
		}
	}else{
		layer.msg('请输入分值！');
		return false;
	}
}
function add_question_option(zhenti_id){
	if(check_question_name()&&check_fenzhi()){
		$(".loading").show();
		var str = "";
		var str_xuanxiang = "";
		$('input:text[name=option_name]').each(function(i) {
			if (0 == i) {
				str = $(this).val();
			} else {
				str += ","+$(this).val();
			}
		});
		$('input:text[name=option_name]').each(function(i) {
			if (0 == i) {
				str_xuanxiang = $(this).attr("pid");
			} else {
				str_xuanxiang += ","+$(this).attr("pid");
			}
		});
		if(str!=""&&str!=null){
			var markupStr= $('#summernote').summernote('code');//编辑器的内容
			var len = $("input:checkbox[name=answer_str]:checked").length;
			if(len>0){
				var str_answer = "";
				$('input:checkbox[name=answer_str]:checked').each(function(i) {
					if (0 == i) {
						str_answer = $(this).val();
					} else {
						str_answer += "."+$(this).val();
					}
				});
				var nandu=$("#nandu").val();
				  $("#myform").ajaxSubmit({
						type : 'POST',
						data:{'zhenti_id':zhenti_id,
							'str':str,'str_xuanxiang':str_xuanxiang,
							'analysis':markupStr,'answer':str_answer,
							'difficulty':nandu,'question_type':'多选题'},
						url : "/back_zhenti/save_zhenti_question_option.jr",
						success : function(data) {
							if(data==1){
								$(".loading").hide();
								//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
				                tanchuang('恭喜您，添加成功');
				                $('#myform')[0].reset();
				                $('#summernote').summernote('code', "");
							}else{
								$(".loading").hide();
								tanchuang('很遗憾，添加失败');
							}
						},
						error : function(XmlHttpRequest, textStatus, errorThrown) {
							$(".loading").hide();
							tanchuang('很遗憾，添加失败');
						}
					}); 
			}else{
				layer.msg("请选择答案！")
			}
		}else{
			layer.msg("请至少输入一个选项！")
		}
	}
}
</script>
<body>
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
    <p style="color: #fff; position: absolute; top: 45px; left: -35px;">正在添加数据...</p>
  </div>
  </div>
<form class="form-horizontal add_qiye_dialog" enctype="multipart/form-data" id="myform" style="padding:21px;">
  <div class="form-group" style="margin-top: 15px;">
		<c:if test="${zhentiYears.zhentitype=='历年真题' }">
		<label class="col-xs-12 control-label"
			style="text-align: center; font-size: 20px;">${zhentiYears.zhenti_year }年${courseMenu.course_name }《${questionCourse.question_course_name }》真题及答案解析-多选题</label>
	</c:if>
	 <c:if test="${zhentiYears.zhentitype=='模拟考试' }">
		<label class="col-xs-12 control-label"
			style="text-align: center; font-size: 20px;">${zhentiYears.zhenti_year }年${courseMenu.course_name }《${questionCourse.question_course_name }》${zhentiYears.zhenti_name }-多选题</label>
	</c:if>
	</div>
     <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">题目：</label>
      <div class="col-xs-6">
        <textarea class="form-control" onblur="check_question_name()" placeholder="请输入题目" id="question_name" name="question_name" rows="4" style="color:#444;"></textarea>         
      </div>
      <div class="col-xs-3"  style="position:relative;">
        <div class="image_container">
          <img id="preview">
        </div>
          <a href="javascript:void(0);" class="file">
          <input type="file" name="question_pic" id="file_upload" >添加题目图片
          </a>
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">选项A：</label>
      <div class="col-xs-6">
        <input class="form-control" name="option_name"  placeholder="选项A" pid="A">         
      </div>
      <div class="col-xs-3">
          <input type="file" name="option_a" style="margin-top:5px;">
      </div>
    </div>
    <div class="form-group plus" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">选项B：</label>
      <div class="col-xs-6">
        <input class="form-control" name="option_name" placeholder="选项B" pid="B">         
      </div>
      <div class="col-xs-3">
          <input type="file" name="option_b" style="margin-top:5px;">
      </div>
      <div class="col-xs-1">
        <i class="fa fa-plus-circle" style="font-size:30px;color:#1ABC9C;line-height:34px;" onclick="tj(this)"></i>      
      </div>
  </div>
   <div class="form-group" style="margin-top:15px;">
    <label class="col-xs-2 control-label right_wz">输入分值：</label>
      <div class="col-xs-6">
        <input class="form-control" onblur="check_fenzhi()" id="fenzhi" name="fenzhi" placeholder="请输入分值" >         
      </div> 
  </div>
   <div class="form-group" style="margin-top:15px;">
    <label class="col-xs-2 control-label right_wz" style="margin-right:15px;">选择答案：</label>
     <label class="checkbox-inline">
      <input type="checkbox" name="answer_str" value="A" class="options"><span>A</span> 
    </label>
    <label class="checkbox-inline">
      <input type="checkbox" name="answer_str" value="B" class="options"><span>B</span>
    </label>
    <label class="checkbox-inline">
      <input type="checkbox" name="answer_str" value="C" class="options"><span>C</span> 
    </label>
    <label class="checkbox-inline">
      <input type="checkbox" name="answer_str" value="D" class="options"><span>D</span>
    </label>
    <label class="checkbox-inline">
      <input type="checkbox" name="answer_str" value="E" class="options"><span>E</span>
    </label>
    <label class="checkbox-inline">
      <input type="checkbox" name="answer_str" value="F" class="options"><span>F</span> 
    </label>
    <label class="checkbox-inline">
      <input type="checkbox" name="answer_str" value="G" class="options"><span>G</span>
    </label>
    <label class="checkbox-inline">
      <input type="checkbox" name="answer_str" value="H" class="options"><span>H</span>
    </label>   
  </div>
    <div class="form-group" style="margin-top:15px;">
    <label class="col-xs-2 control-label right_wz">题目难度：</label>
    <div class="col-xs-9">
    	<input type="hidden" id="nandu" value="1"/>
        <div id="star" style="margin-top:10px;"></div>
      </div>
  </div>
   <div class="form-group" style="margin-top:15px;">
    <label class="col-xs-2 control-label right_wz">输入解析：</label>
    <div class="col-xs-9">
        <div id="summernote"></div>
      </div>
  </div>
	<div class="form-group" style="margin-top:15px;">
		<div class="col-xs-6 right_wz">
		<button type="button" class="btn btn-warning btn-lg" onclick="colse_layer()" style="width:100px;">取消</button>
		</div>
    <div class="col-xs-6" style="text-align:left;">
    <button type="button" onclick="add_question_option(${zhentiYears.zhenti_id })" class="btn btn-primary btn-lg" style="width:100px;">保存</button>
    </div>
	</div>
	</form>	
</body>
<script type="text/javascript">
function tj(obj){
  var val= $(obj).attr("class");
  var intput_number=$("input:text[name=option_name]").length;
  switch(intput_number)
  {
    case 2:
        $(obj).parent().parent().after('<div class="form-group" style="margin-top:15px;"><label class="col-xs-2 control-label right_wz">选项C：</label><div class="col-xs-6"><input class="form-control" name="option_name" placeholder="选项C" pid="C"></div><div class="col-xs-3"><input type="file" name="option_c" style="margin-top:5px;"></div><div class="col-xs-1"><i class="fa fa-plus-circle" style="font-size:30px;color:#1ABC9C;line-height:34px;" onclick="tj(this)"></i></div></div>');
        break;
    case 3:
        $(obj).parent().parent().after('<div class="form-group" style="margin-top:15px;"><label class="col-xs-2 control-label right_wz">选项D：</label><div class="col-xs-6"><input class="form-control" name="option_name" placeholder="选项D" pid="D"></div><div class="col-xs-3"><input type="file" name="option_d" style="margin-top:5px;"></div><div class="col-xs-1"><i class="fa fa-plus-circle" style="font-size:30px;color:#1ABC9C;line-height:34px;" onclick="tj(this)"></i></div></div>');
        break;
    case 4:
         $(obj).parent().parent().after('<div class="form-group" style="margin-top:15px;"><label class="col-xs-2 control-label right_wz">选项E：</label><div class="col-xs-6"><input class="form-control" name="option_name" placeholder="选项E" pid="E"></div><div class="col-xs-3"><input type="file" name="option_e" style="margin-top:5px;"></div><div class="col-xs-1"><i class="fa fa-plus-circle" style="font-size:30px;color:#1ABC9C;line-height:34px;" onclick="tj(this)"></i></div></div>');
        break;
    case 5:
       $(obj).parent().parent().after('<div class="form-group" style="margin-top:15px;"><label class="col-xs-2 control-label right_wz">选项F：</label><div class="col-xs-6"><input class="form-control" name="option_name" placeholder="选项F" pid="F"></div><div class="col-xs-3"><input type="file" name="option_f" style="margin-top:5px;"></div><div class="col-xs-1"><i class="fa fa-plus-circle" style="font-size:30px;color:#1ABC9C;line-height:34px;" onclick="tj(this)"></i></div></div>');
        break;
    case 6:
        $(obj).parent().parent().after('<div class="form-group" style="margin-top:15px;"><label class="col-xs-2 control-label right_wz">选项G：</label><div class="col-xs-6"><input class="form-control" name="option_name" placeholder="选项G" pid="G"></div><div class="col-xs-3"><input type="file" name="option_g" style="margin-top:5px;"></div><div class="col-xs-1"><i class="fa fa-plus-circle" style="font-size:30px;color:#1ABC9C;line-height:34px;" onclick="tj(this)"></i></div></div>');
        break;
    case 7:
        $(obj).parent().parent().after('<div class="form-group" style="margin-top:15px;"><label class="col-xs-2 control-label right_wz">选项H：</label><div class="col-xs-6"><input class="form-control" name="option_name" placeholder="选项H" pid="H"></div><div class="col-xs-3"><input type="file" name="option_h" style="margin-top:5px;"></div><div class="col-xs-1"><i class="fa fa-plus-circle" style="font-size:30px;color:#1ABC9C;line-height:34px;" onclick="tj(this)"></i></div></div>');
        break;
    default:
        layer.msg("没有选项了，如需添加，请联系技术人员!");
     break;
  }
   if(val=="fa fa-plus-circle"){
    $(obj).removeClass("fa fa-plus-circle");
    }
   else{
    $(obj).parent().parent().remove();
    }
 }
</script>
<script type="text/javascript">
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
}
});
});
$('#summernote').summernote({
	height: 150,
	lang:"zh-CN",                 // 设置中文
	maxHeight: 150,             //  设置最大高度
	focus: false,                  // 自动获取焦点
	 callbacks: {  
         onImageUpload: function(files, editor, $editable) {  
             sendFile(files);  
         }
     } 
   });
   
//上传图片到服务器
function sendFile(files, editor, $editable) {  
    var data = new FormData();  
    data.append("ajaxTaskFile", files[0]);  
    $.ajax({  
        data : data,  
        type : "POST",  
        url : "/back_chapter/uploadpic.jr", //图片上传出来的url，返回的是图片上传后的路径，http格式  
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
$(function() {
    $.fn.raty.defaults.path = '/images/question/back';
    $('#star').raty({ 
    	width:210,
    	scoreName: 'entity[score]',
    	click: function(score, evt){
            $("#nandu").val(score);
        }
	// 星级评分
	});
});
</script>
</html>