<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加院校简章</title>
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/dist/summernote.min.js"></script>
<script type="text/javascript" src="/dist/lang/summernote-zh-CN.min.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
h3 {
    text-align: center;
}
select {
	width: 100%;
	border: 2px solid #bdc3c7;
	height: 35px;
	border-radius: 5px;
	float: left;
}
label {
    line-height: 2.5;
}

.file {
    position: relative;
	display: block;
	width: 150px;
	height: 35px;
	padding-left:15px;
	line-height: 34px;
	background-color: #06C1AE;
	color: #fff;
	font-size: 16px;
	float: left;
	border-radius: 3px;
}

.file:hover {
	text-decoration: none;
	cursor: pointer;
	color: #fff;
}

.file i {
    float:left;
	margin-left: 10px;
    position: absolute;
    top: 9px;
}
.file input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
	width:150px;
}
.file_text {
    float: left;
    line-height: 35px;
    margin-left: 15px;
}
.form-horizontal .form-group {
    width: 500px;
    margin: 0 auto;
    margin-bottom: 15px;
}
.content_message table {
    width: 500px;
    margin: 0 auto;
    border: #06c1ae solid 2px;
}
.content_message span {
    width: 500px;
    display: block;
    margin: 15px auto;
    color: orange;
}
</style>
<script>
	//添加简章
	function save_jianzhang(zhaosheng_id){
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/zs_jianzhang/upload_jianzhang.jr",
			data:{
				'zhaosheng_id':zhaosheng_id
			},
			success : function(data) {
				if(data==1){
					 location.reload();
					//tanchuang('恭喜，设置成功');
				}else{
					tanchuang('很遗憾，设置失败');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，设置失败');
			}
		});
	}
	//获取文件名称
	function get_file_name(){
		var file_upload=$("#file_upload").val();
		$("#file_name").html(file_upload.split("\\").pop());
	}
	//删除简章
	function delete_jianzhang(jianzhang_id){
		layer.confirm("提示：您好，确定要删除吗？",function(){
			layer.closeAll('dialog');
			$.post("/zs_jianzhang/delete_jianzhang.jr",{
				'jianzhang_id':jianzhang_id
			},function(data){
				if(data==1){
					location.reload();
				}else{
					tanchuang("删除失败！");
				}
			});
			})
	}
</script>
</head>
<body>
<h3>${ckZhaoSheng.xuexiao_name }招生简章</h3>
<div class="content_message">
<c:if test="${empty jianzhangs }">
	<span>您还没上传过简章文件</span>
</c:if>
<c:if test="${not empty jianzhangs }">
      <table class="table table-hover">
        <thead>
          <tr class="tr_bgcolor warning">
          <th>年份</th>
          <th>文件名称</th>
          <th>操作</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${jianzhangs }" var="jianzhang">
          <tr>
            <td>${jianzhang.nianfen }</td>
            <td>${ckZhaoSheng.xuexiao_name }招生简章</td>        
            <td>
              <a href="${jianzhang.jianzhang_file }" class="btn btn-info btn-xs">下载</a>
              <button type="button" onclick="delete_jianzhang(${jianzhang.jianzhang_id})" class="btn btn-danger btn-xs">删除</button>
            </td>
          </tr>
          </c:forEach>
        </tbody>
	  </table>
	  <span>注：您已上传，如再重新上传添加，则会覆盖上一条。</span>
	  </c:if>
    </div>
<form  class="form-horizontal import_xueyuanshuju_dialog"
		enctype="multipart/form-data" id="myform" style="padding: 20px;">
  <div class="form-group">
      <label class="col-xs-2 control-label">年份：</label>
      <div class="col-xs-10">
        <select id="nianfen" name="nianfen">
          <option value="2017年">2017年</option>
          <option value="2018年">2018年</option>
          <option value="2019年">2019年</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">上传：</label>
      <div class="col-xs-10">
        <a href="javascript:void(0);" class="file">
          <input onchange="get_file_name()" type="file" name="file_upload" id="file_upload">上传招生简章<i class="fa fa-upload"></i>
        </a>
        <span class="file_text" id="file_name"></span>
      </div>
    </div>
    <div class="form-group">
      <label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-10">
        <button type="button" onclick="save_jianzhang(${ckZhaoSheng.zhaosheng_id })" class="btn btn-success btn-xm">提交</button>
        <button type="reset" class="btn btn-danger btn-xm">重置</button> 
      </div> 
    </div>
	</form>
</body>
</html>