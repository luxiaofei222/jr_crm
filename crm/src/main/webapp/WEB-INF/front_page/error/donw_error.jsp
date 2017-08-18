<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>禁止下载视频</title>
<style type="text/css">
.no_down {
	width:600px;
	height:175px;
	margin:0 auto;
	margin-top:15%;
	position:relative;
	}
.no_down .pic {
	position:absolute;
	left:0px;
	top:0px;
	}
.no_down .wenzi {
	position:absolute;
	left:150px;
	top:60px;
	}
.no_down a {
	display:block;
	width: 90px;
    height: 35px;
    background-color: #f87678;
    border: none;
    border-radius: 5px;
    color: #fff;
    font-size: 16px;
    text-align: center;
    line-height: 35px;
    position: absolute;
    top: 120px;
    right: 20px;
    cursor: pointer;
    text-decoration: none;
	}
</style>
</head>

<body>
<div class="no_down">
  <img src="/images/error/wunai.png" class="pic" />
  <img src="/images/error/wenzi.png" class="wenzi" />
  <a href="/index.jsp" >返回首页</a>
</div>
</body>
</html>