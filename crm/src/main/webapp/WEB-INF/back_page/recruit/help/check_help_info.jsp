<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>帮助信息查看</title>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/reset.css" />
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<style>
  .help {
  width: 100%;
  height: 100%;
  padding:20px;
  }
  h3 {
  text-align: center;
  margin: 20px auto;
  }
  .laiyuan {
  width: 400px;
  margin: 10px auto;
  }
  .content {
  width: 985px;
  margin: 0 auto;
  line-height: 30px;
  }
  strong {
  font-weight: bold;
  }
</style>
</head>
<body>
<div class="help">
  <h3>${helpCenter.title }</h3>
  <div class="laiyuan">来源：${helpCenter.help_laiyuan } &nbsp;&nbsp;&nbsp; 编辑：${helpCenter.bianji }</div>
  <div class="content">${helpCenter.contente }</div>
</div>
</body>
</html>
