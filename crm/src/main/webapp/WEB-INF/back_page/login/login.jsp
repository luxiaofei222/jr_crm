<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="/css/school/back/login/login.css">
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/school/back/common/flat-ui.min.css">
	<link rel="stylesheet" href="/css/school/back/common/font-awesome.min.css">
<script src='/js/common/jquery-1.11.1.min.js'></script>
<script src='/js/common/prefixfree.min.js'></script>
<script src='/js/common/stopExecutionOnTimeout.js'></script>
<script type="text/javascript" src="/js/common/login.js"></script>
<title>BSM登录</title>
</head>
<body>
<!-- 星空的位置开始-->
	<div class="stars"></div>
	<!-- 星空的位置结束 -->
	<!-- 登录框开始 -->
    <div class="back_color"></div>
	<div class="back_login">
		<h2 style="color:#e9e9e9;">京人教育科技BSM系统</h2>
        <div class="error" style="color: orange;"><%
					String error = request.getParameter("error");
					if (error != null) {
						out.print(error);
					}
				%></div>
		<div class="login-form">
		<form name="backloginForm" action="/login_employee.jr"
				method="post">
            <div class="form-group">
              <input type="text" class="form-control login-field"
              id="login_name" name="login_name"  placeholder="请输入登录账号" id="login-name" />
              <label class="login-field-icon fui-user" for="login-name"></label>
            </div>

            <div class="form-group">
              <input type="password" id="login_password" name="login_password"
               class="form-control login-field"  placeholder="请输入登录密码" id="login-pass" />
              <label class="login-field-icon fui-lock" for="login-pass"></label>
            </div>

            <input type="submit" class="btn btn-primary btn-lg btn-block" value="登  录">
          </form>
          </div>
          <div class="copyright">
		    <a>北京京人教育科技有限公司版权所有@2016.Company name All rights reserved</a>
	      </div>
	</div>
	<!-- 登录框结束 -->
</body>
</html>