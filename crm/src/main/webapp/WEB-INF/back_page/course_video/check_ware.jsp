<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>查看课件</title>
</head>
<script>
//删除课件
function delete_ware(courseware_id){
	$.post("/back_video/delete_course_ware.jr",{
		'courseware_id':courseware_id
	},function(data){
		if(data==1){
			location.reload();
		}else{
			tanchuang("删除失败！");
		}
	})
}
//关闭按钮
function guanbi_tanchuang(){
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	parent.layer.close(index);
}
//改变排序
function warexiangshang(courseware_id){
	$.post("/back_video/warexiangshang.jr",{
		'courseware_id':courseware_id
	},function(data){
		if(data!=0){
			tanchuang("设置成功!");
		}else{
			tanchuang("设置失败!");
		}
	})
}
</script>
<body>
<div style="padding: 20px;">
  <form class="form-horizontal check_kejian_dialog" role="form">
  <c:if test="${not empty courseWares }">
    <div class="check_info">
      <c:forEach items="${courseWares }" var="ware" varStatus="vs">
    <div class="form-group">
      <label for="inputPassword1" class="col-xs-2 control-label">课件${vs.index+1 }：</label>
      <div class="col-xs-9">
        <a href="${ware.courseware_file }" class="check_kejian" download="${ware.courseware_name }">${ware.courseware_name }</a>
        <c:if test="${vs.index>0 }">
          &nbsp;&nbsp;<a href="javascript:void(0)" onclick="warexiangshang(${ware.courseware_id})" ><i class="fa fa-arrow-up"></i></a>
        </c:if> 
      </div> 
      <div class="col-xs-1">
        <button type="button" onclick="delete_ware(${ware.courseware_id })" class="btn btn-danger btn-xs">删除</button>
      </div>
    </div>
    </c:forEach>
    </div>
    </c:if>
    <c:if test="${ empty courseWares }">
    <div class="check_info">
      <span class="zanwu">暂无课件内容</span>
    </div>
    </c:if>
      <button type="button" onclick="guanbi_tanchuang()" class="btn btn-success btn-xm">关闭</button> 
    </div>
	</form>	
</div>
</body>
</html>