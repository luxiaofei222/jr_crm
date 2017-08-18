<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/dist/summernote.min.js"></script>
<script type="text/javascript" src="/dist/lang/summernote-zh-CN.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
input[type="text"] {
	border: none;
	width: 100%;
	height: 38px;
	line-height: 38px;
	border: 2px solid #bdc3c7;
	border-radius: 5px;
	padding-left: 5px;
}

.tianjia_xuexiao form .col-xs-10 select {
	width: 100%;
	border: 2px solid #bdc3c7;
	height: 42px;
	border-radius: 5px;
}

.tianjia_xuexiao input[type="button"], input[type="reset"] {
	width: 76px;
	height: 35px;
	display: block;
	margin: 25px auto;
	background-color: #8bc34a;
	color: #fff;
	border: none;
	border-radius: 3px;
	cursor: pointer;
	float: left;
	margin-right: 20px;
}

input[type="reset"] {
	background-color: orange;
}

.tianjia_xuexiao h4 {
	text-align: center;
	color: #313131;
	font-size: 18px;
	margin-top: 20px;
	font-weight: bold;
}
</style>
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
//添加学校介绍
function update_yuanxiao_changjian(obj,help_id){
	var markupStr= $('#summernote').summernote('code');
	if(markupStr!=null&&markupStr!="<p><br></p>"){
		$(obj).attr("disabled","disabled"); 
		$("#myform").ajaxSubmit({
			type : 'POST',
			data:{'help_id':help_id,'contente':markupStr},
			url : "/help_center/update_yuanxiao_changjian.jr",
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
	                 layer.msg("修改成功！");
	                 $(obj).removeAttr("disabled");
				}else{
					layer.msg('很遗憾，修改失败');
					$(obj).removeAttr("disabled");
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				layer.msg('很遗憾，修改失败');
				$(obj).removeAttr("disabled");
			}
		});
	}else{
		layer.msg("请输入内容！")
	}
}
</script>
<div class="tianjia_xuexiao">
	<h4>帮助中心-帮助信息编辑</h4>
	<form class="form-horizontal" enctype="multipart/form-data" id="myform"
		style="padding: 20px;">
		<div class="form-group">
			<label class="col-xs-2 control-label">标题：</label>
			<div class="col-xs-10">
				<input type="text" id="title" value="${helpCenter.title }" name="title" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">来源：</label>
			<div class="col-xs-10">
				<input type="text" id="help_laiyuan"  name="help_laiyuan"
					value="${helpCenter.help_laiyuan }" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">分类：</label>
			<div class="col-xs-10">
				<select id="help_type" name="help_type">
				<option value="${helpCenter.help_type }">${helpCenter.help_type }</option>
				<option value="关于成人高考">关于成人高考</option>
				<option value="关于远程教育">关于远程教育</option>
				<option value="如何选择院校">如何选择院校</option>
				<option value="学历学位">学历学位</option>
				<option value="常见问题">常见问题</option>
				<option value="入学说明">入学说明</option>
				<option value="入学测试">入学测试</option>
				<option value="新生选课">新生选课</option>
				<option value="学习平台操作">学习平台操作</option>
				<option value="毕业办理">毕业办理</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">编辑：</label>
			<div class="col-xs-10">
				<input type="text" value="${helpCenter.bianji }" id="bianji" name="bianji" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">MATE-title：</label>
			<div class="col-xs-10">
				<input type="text"  value="${helpCenter.mate_title }" id="mate_title" name="mate_title" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">MATE-key：</label>
			<div class="col-xs-10">
				<input type="text" value="${helpCenter.mate_key }" id="mate_key" name="mate_key" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">MATE-dis：</label>
			<div class="col-xs-10">
				<input type="text" value="${helpCenter.mate_dis }" id="mate_dis" name="mate_dis" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">内容：</label>
			<div class="col-xs-10">
				<div id="summernote">${helpCenter.contente }</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">&nbsp;</label>
			<div class="col-xs-10">
				<input type="button"
					onclick="update_yuanxiao_changjian(this,${helpCenter.help_id})" value="提交">
				<input type="reset" value="重置">
			</div>
		</div>
	</form>
</div>