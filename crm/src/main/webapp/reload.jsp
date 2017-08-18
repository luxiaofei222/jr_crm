<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录页面</title>
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(function(){
	location.href=("/login.jr");
})
</script>
<style type="text/css">
.loading {
	  width:484px;
	  height:525px;
	  top:5%;
	  left:50%;
	  position:absolute;
	  margin-left:-242px;
	  }
</style>
</head>
<body>
<div class="loading">
  <img src="/images/school/front/login/loading.jpg" />
</div>
</body>
</html>
