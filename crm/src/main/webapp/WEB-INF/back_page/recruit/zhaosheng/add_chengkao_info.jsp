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
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/dist/summernote.min.js"></script>
<script type="text/javascript" src="/dist/lang/summernote-zh-CN.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
input[type="text"] {
    border:none;
	width:100%;
	height:38px;
	line-height:38px;
	border:2px solid #bdc3c7;
	border-radius:5px;
	padding-left:5px;
	}
 .tianjia_xuexiao form .col-xs-10 select {
    width: 100%;
    border: 2px solid #bdc3c7;
    height: 42px;
    border-radius: 5px;
}
.tianjia_xuexiao form .col-xs-10 ul.pics li .xueyuan_pic img {
	border: #cbcdcd solid 1px;
	}
.tianjia_xuexiao input[type="button"] {
    width: 76px;
    height: 35px;
    display: block;
    margin: 25px auto;
    background-color: #8bc34a;
    color: #fff;
    border: none;
    border-radius: 3px;
    cursor: pointer;
}
.tianjia_xuexiao form .col-xs-10 ul.pics li {
	float:left;
	margin-right:70px;
	margin-bottom: 15px;
	}
.tianjia_xuexiao form .col-xs-10 ul.pics li div.pic_btn {
    margin-top: 10px;
}
.tianjia_xuexiao form .col-xs-10 ul.pics li p {
    color: orange;
    font-size: 12px;
    float: left;
    margin-right: 10px;
}
.tianjia_xuexiao form .col-xs-10 ul.pics li a.btn {
    background-color: #8bc34a;
    float: left;
}
</style>
<script>
$(function() {
	$("#upfile1").change(function() {
	var $file = $(this);
	var fileObj = $file[0];
	var windowURL = window.URL || window.webkitURL;
	var dataURL;
	var $img = $("#preview1");
	 
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

	$("#upfile2").change(function() {
	var $file = $(this);
	var fileObj = $file[0];
	var windowURL = window.URL || window.webkitURL;
	var dataURL;
	var $img = $("#preview2");
	 
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

	$("#upfile3").change(function() {
	var $file = $(this);
	var fileObj = $file[0];
	var windowURL = window.URL || window.webkitURL;
	var dataURL;
	var $img = $("#preview3");
	 
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

	$("#upfile4").change(function() {
	var $file = $(this);
	var fileObj = $file[0];
	var windowURL = window.URL || window.webkitURL;
	var dataURL;
	var $img = $("#preview4");
	 
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

	$("#upfile5").change(function() {
	var $file = $(this);
	var fileObj = $file[0];
	var windowURL = window.URL || window.webkitURL;
	var dataURL;
	var $img = $("#preview5");
	 
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
</script>
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
function save_chengkao_xuexiao(obj){
	var markupStr= $('#summernote').summernote('code');
	if(markupStr!=null&&markupStr!="<p><br></p>"){
		 $(obj).attr("disabled","disabled"); 
		$("#myform").ajaxSubmit({
			type : 'POST',
			data:{'yuanxiao_jieshao':markupStr,'xuexiao_type':"成考"},
			url : "/ck_zhaosheng/save_chengkao_xuexiao.jr",
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
	                 location.reload();
				}else{
					tanchuang('很遗憾，添加失败');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，添加失败');
			}
		});
	}else{
		layer.msg("请输入学校介绍！");
	}
 
}
</script>
<div class="tianjia_xuexiao">
  <form class="form-horizontal" enctype="multipart/form-data" id="myform" style="padding:20px;">
    <div class="form-group">
      <label class="col-xs-2 control-label">学校名称：</label>
      <div class="col-xs-10">
        <select  id="chengkao_id" name="chengkao_id">
        <c:forEach items="${chengkaoScs }" var="chengkao">
          <option value="${chengkao.chengkao_id }">${chengkao.chengkao_name }</option>
        </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">招生状态：</label>
      <div class="col-xs-10">
        <select id="zhaosheng_state" name="zhaosheng_state">
          <option value="0">正在招生</option>
          <option value="1">停止招生</option>
          <option value="2">即将招生</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">热门专业：</label>
      <div class="col-xs-10">
        <input type="text" id="remen_zhuanye" name="remen_zhuanye" />
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">校训：</label>
      <div class="col-xs-10">
        <input type="text" id="xiaoxun" name="xiaoxun" />
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">上传图片：</label>
      <div class="col-xs-10">
        <ul class="pics">
          <li style=" margin-right:140px;">
            <div class="xueyuan_pic">
              <img id="preview1" width="150" height="150" />
            </div>
            <div class="pic_btn"><p>校徽</p><a href="javascript:void(0)" class="btn btn-info btn-xs" style="position: relative; ">浏览
          <input type="file" id="upfile1"  name="xiaohun_str" style="position: absolute;opacity:0; top:0px;left:0px; width: 100%; height:100%;" />
        </a></div>
          </li>
          <li>
            <div class="xueyuan_pic">
              <img id="preview4" width="185" height="150" />
            </div>
            <div class="pic_btn"><p>学校覆盖区域</p><a href="javascript:void(0)" class="btn btn-info btn-xs" style="position: relative; ">浏览
          <input type="file" id="upfile4"  name="fugai_str" style="position: absolute;opacity:0; top:0px;left:0px; width: 100%; height:100%;" />
        </a></div>   
          </li>
          <li>
            <div class="xueyuan_pic">
              <img id="preview2" width="220" height="150" />
            </div>
            <div class="pic_btn"><p>学历证书样本</p><a href="javascript:void(0)" class="btn btn-info btn-xs" style="position: relative; ">浏览
          <input type="file" id="upfile2"  name="xueli_str" style="position: absolute;opacity:0; top:0px;left:0px; width: 100%; height:100%;" />
        </a></div>
          </li>
          <li>
            <div class="xueyuan_pic">
              <img id="preview3" width="220" height="150" />
            </div>
            <div class="pic_btn"><p>学位证书样本</p><a href="javascript:void(0)" class="btn btn-info btn-xs" style="position: relative; ">浏览
          <input type="file" id="upfile3"  name="xuewei_str" style="position: absolute;opacity:0; top:0px;left:0px; width: 100%; height:100%;" />
        </a></div>  
          </li>
          <li>
            <div class="xueyuan_pic">
              <img id="preview5" width="705" height="150" />
            </div>
            <div class="pic_btn"><p>详情页背景图</p><a href="javascript:void(0)" class="btn btn-info btn-xs" style="position: relative; ">浏览
          <input type="file" id="upfile5"  name="xiangqing_str" style="position: absolute;opacity:0; top:0px;left:0px; width: 100%; height:100%;" />
        </a></div>
          </li>
        </ul>  
      </div>
    </div> 
    <div class="form-group">
      <label class="col-xs-2 control-label">院校介绍：</label>
      <div class="col-xs-10">
        <div id="summernote"></div>
      </div>
    </div>
    <input type="button" onclick="save_chengkao_xuexiao(this)" value="提交">
  </form>
</div>