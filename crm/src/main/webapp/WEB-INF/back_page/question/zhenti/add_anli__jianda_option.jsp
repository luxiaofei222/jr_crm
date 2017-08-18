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
.form-horizontal .form-group .right_wz{
    text-align:right;
    font-size:16px;
}
.checkbox-inline{
  overflow:hidden;
}
</style>
</head>
<script>
//关闭弹窗
function colse_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
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
//添加题目
function add_question_option(zhenti_question_id,question_type){
	var summernote_name= $('#summernote_name').summernote('code');
	var summernote_jiexi= $('#summernote_jiexi').summernote('code');
	if(summernote_name!=null&&summernote_name!=""){
		if(check_fenzhi()){
			$(".loading").show();
			var nandu=$("#nandu").val();
			 $("#myform").ajaxSubmit({
					type : 'POST',
					data:{'zhenti_question_id':zhenti_question_id,'analysis':summernote_jiexi,
						'question_name':summernote_name,
						'difficulty':nandu,'question_type':question_type},
					url : "/back_chapter/save_anli_jianda.jr",
					success : function(data) {
						if(data==1){
							$(".loading").hide();
							//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
			                tanchuang('恭喜您，添加成功');
			                $('#myform')[0].reset();
			                $('#summernote_name').summernote('code', "");
			                $('#summernote_jiexi').summernote('code', "");
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
		}
	}else{
		layer.msg('请输入题目内容！');
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
		<label class="col-xs-12 control-label"
			style="text-align: center; font-size: 20px;">案例简答题</label>
	</div>
     <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">题目：</label>
       <div class="col-xs-9">
        <div class="summernote" id="summernote_name"></div>
      </div>
    </div>
   <div class="form-group" style="margin-top:15px;">
    <label class="col-xs-2 control-label right_wz">输入分值：</label>
      <div class="col-xs-6">
        <input class="form-control" onblur="check_fenzhi()" id="fenzhi" name="fenzhi" placeholder="请输入分值" >         
      </div> 
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
        <div id="summernote_jiexi"></div>
      </div>
  </div>
	<div class="form-group" style="margin-top:15px;">
		<div class="col-xs-6 right_wz">
		<button type="button" class="btn btn-warning btn-lg" onclick="colse_layer()" style="width:100px;">取消</button>
		</div>
    <div class="col-xs-6" style="text-align:left;">
    <button type="button" onclick="add_question_option(${zhenti_question_id},'案例简答题')" class="btn btn-primary btn-lg" style="width:100px;">保存</button>
    </div>
	</div>
	</form>	
</body>
<script type="text/javascript">
$(function() {
	//题目
	$('#summernote_name').summernote({
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
	//题目解析
	$('#summernote_jiexi').summernote({
		height: 150,
		lang:"zh-CN",                 // 设置中文
		maxHeight: 150,             //  设置最大高度
		focus: false,                  // 自动获取焦点
		 callbacks: {  
	         onImageUpload: function(files, editor, $editable) {  
	             sendFile_jiexi(files);  
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
        url : "/back_chapter/uploadpic.jr", //图片上传出来的url，返回的是图片上传后的路径，http格式  
        cache : false,  
        contentType : false,  
        processData : false,  
        dataType : "json",  
        success: function(data) {//data是返回的hash,key之类的值，key是定义的文件名  
            $('#summernote_name').summernote('insertImage', data.file_path);  
        },  
        error:function(){  
            alert("上传失败");  
        }  
    });  
}
function sendFile_jiexi(files, editor, $editable) {  
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
            $('#summernote_jiexi').summernote('insertImage', data.file_path);  
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