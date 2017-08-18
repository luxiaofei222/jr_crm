<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<title>提示升级</title>
<link rel="stylesheet" type="text/css"
	href="/css/school/front/index/reset.css">
<script>
 $(function(){
	if(!isIE()){
		location.href="/index.jsp";
	}
}) 
	function isIE() { //ie?
		if (!!window.ActiveXObject || "ActiveXObject" in window) {
			return true;
		} else {
			return false;
		}
	}
</script>
<style type="text/css">
body {
	background: url("/images/index/updatebg.png")
}

div {
	margin: 0 auto;
}

.up_title {
	overflow: hidden;
	width: 337px;
	margin-top: 100px;
}

.up_title img {
	width: 80px;
	height: 69px;
	float: left;
}

.up_title span {
	float: right;
	font-size: 50px;
	color: #595c63;
	letter-spacing: 10px;
}

.up_notice {
	overflow: hidden;
	width: 400px;
	width: 620px;
	margin-top: 50px;
}

.up_notice img {
	width: 40px;
	height: 40px;
	float: left;
}

.up_notice span {
	float: right;
	font-size: 18px;
	line-height: 40px;
	color: #595c63;
}

.up_borwser {
	border-left: 100px solid #444;
	border-right: 100px solid #444;
	font-size: 18px;
	margin: 0 auto;
	line-height: 1px;
	text-align: center;
	width: 200px;
	color: #595c63;
	margin-top: 250px;
}

.logo360 {
	width: 150px;
	margin: 0 auto;
	display: block;
	margin-top: 60px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="up_title">
		<img alt="logo" src="/images/index/logo2.png"> <span>京人网校</span>
	</div>
	<div class="up_notice">
		<img alt="提示" src="/images/index/warn.png"> <span>浏览器版本过低，建议您对浏览器进行升级，以便获得更好的浏览体验</br>
		如果您使用的是非IE浏览器请选择极速模式，即可享受完美体验！
		</span>
	</div>
	<div class="up_borwser">推荐以下浏览器</div>
	<img alt="360logo" class="logo360" src="/images/index/logo360.png"
		onclick="logo360()">
</body>
<script type="text/javascript">
	function logo360() {
		window.open("http://www.jingrenedu.com/template/360se8.1.1.404.exe");
	}
</script>
</html>