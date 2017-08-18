<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>excel解析</title>
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
</head>
<script>
//开始导入
function satrt_daoru(){
	var name = $("#upfile").val();
	if(name!=""&&name!=null){
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/entry_info_excle/jiexi_excel.html",
			success : function(data) {
				alert("成功");
				 $("#list").html(data);
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				 alert('很遗憾，导入失败，系统发生错误');
			}
		});
	}else{
		alert("请选择您要导入的文件！")
	}
}
</script>
<body>
<form enctype="multipart/form-data" id="myform" class="form-horizontal">
	 <input type="file" id="upfile" name="upfile"  />
	  <button type="button" onclick="satrt_daoru()" class="btn btn-info btn-sm" style="margin-right:35px;">提交</button> 		
</form>
<div id="list">
</div>
</body>
</html>