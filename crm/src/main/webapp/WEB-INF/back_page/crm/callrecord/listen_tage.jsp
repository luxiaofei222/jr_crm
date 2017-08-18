<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="/js/audiojs/audioplayer.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/bootstrap.min.js"
	type="text/javascript"></script>
<!-- <script src="/js/audiojs/audioplayer.js" type="text/javascript"></script> -->
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>试听录音</title>
<style>
.xiazai{
	width:100px;
	height:35px;
	line-height:35px;
	background-color:#94CE6E;
	border:none;
	margin:0 auto;
	text-align:center;
	display:block;
	color:white;
	border-radius:5px;
	margin-top:25px;
}
.audio{
    text-align: center;
    width: 80%;
    margin-left: 10%;
    margin-top: 15px;
}
.xiazai:hover{
	color:white;
}
</style>
</head>
<script>
/*  $(function(){ 
	$( 'audio' ).audioPlayer();
})  */
</script>
<body>
	<div style="padding: 20px;padding-top:30px;">
	<audio preload="auto" controls style="width:100%;">
	<%-- <source src="http://192.168.1.251/outbound/index.php/Call/recording/uniqueid/audio:${businessCallRecord.sound_file }"> --%>
		 <source src="http://192.168.1.251/api.php?f=voiceCallFile&uniqueid=${uniqueid }&date=${date}">
	</audio>
	<a class="xiazai" href="http://192.168.1.251/api.php?f=voiceCallFile&uniqueid=${uniqueid }&date=${date}&down=1" download="${date}.mp3">下载</a>
	</div>
</body>
</html>