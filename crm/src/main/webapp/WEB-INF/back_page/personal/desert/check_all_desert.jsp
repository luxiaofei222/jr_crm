<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/school/front/index/reset.css" rel="stylesheet"	type="text/css" />
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" type="text/css"	href="/css/school/back/layui/css/layui.css">
<script src="/js/school/front/course_video/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/js/school/front/course_video/jquery.raty.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>查看请假详情</title>
<style type="text/css">
    .jujue {
		display:none;
		}
	.jujue button {
		margin-top:20px;
		}
	.form-group select {
	    width: 100%;
	    height: 40px;
	    border: 2px solid #bdc3c7;
	}
	.form-control[disabled]{
	    color: #313131;
	}
    </style>
</head>
<body>
  <div style="padding:20px;">
  <form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
    <div class="form-group">
      <label class="col-xs-2 control-label">离职人员：</label>
      <div class="col-xs-10">
        <input class="form-control" id="" type="text" value="${desertJob.bumen }" disabled style="width: 47%;float: left;margin-right: 38px;"> 
        <input class="form-control" id="" type="text" value="${desertJob.employee.employee_name }" disabled style="width: 47%;float: left;">   
      </div>
    </div> 
      <div class="form-group">
      <label class="col-xs-2 control-label">离职时间：</label>
      <div class="col-xs-10">
        <input class="form-control" type="text" value="<fmt:formatDate pattern="yyyy年MM月dd日"
									value="${desertJob.desert_time }" />" disabled>   
      </div>
    </div> 
    <div class="form-group">
      <label class="col-xs-2 control-label">距离离职时长：</label>
      <div class="col-xs-10">
        <input class="form-control" type="text" value="${shicha}天" disabled>   
      </div>
    </div> 
    <div class="form-group">
      <label class="col-xs-2 control-label">离职原因：</label>
      <div class="col-xs-10">
        <textarea class="form-control" rows="3" disabled>${desertJob.desert_content }</textarea>
      </div>
    </div>
    <c:if test="${not empty desertJob.jingli_message }">
    <div class="form-group">
      <label class="col-xs-2 control-label">部门经理意见：</label>
      <div class="col-xs-10">
        <textarea class="form-control" rows="3" disabled>${desertJob.jingli_message }</textarea>
      </div>
    </div>
    </c:if>
     <c:if test="${not empty desertJob.zongjian_message }">
    <div class="form-group">
      <label class="col-xs-2 control-label">市场总监意见：</label>
      <div class="col-xs-10">
        <textarea class="form-control" rows="3" disabled>${desertJob.zongjian_message }</textarea> 
      </div>
    </div>
    </c:if>
    <c:if test="${not empty desertJob.boss_message }">
    <div class="form-group">
      <label class="col-xs-2 control-label">总经理意见：</label>
      <div class="col-xs-10">
        <textarea class="form-control" rows="3" disabled>${desertJob.boss_message }</textarea>  
      </div>
    </div>
    </c:if>
    </form>
  </div>
</body>
</html>