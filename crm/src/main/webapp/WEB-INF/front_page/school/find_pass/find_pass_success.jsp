<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/login/reset.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/login/success_findpass_page.css">
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<title>恭喜找回密码成功</title>
</head>
<body>
<div class="rpage_wrap">
		<div class='rpage_top'>
			<div class='logo'>
				<img src="/images/school/front/login/logo.png">
			</div>
		</div>
		<input type="hidden" value="${ jr_url}" id="url">
		<div class="rpage_info_w">
			<div class="rpage_info">
				<div class="zhuce_success">
					<div class="success_title">
						<div class="success_title2">
							<img src="/images/school/front/login/happy.png">
						</div>
						<div class="success_title1">恭喜您，密码找回成功</div>
					</div>
					<p class="success_title4">您可以尽情的学习啦…</p>
					<p class="success_title3">
						将在 <span id="mes1" style="color: #06C1AE; font-size: 25px;"></span>
						秒钟后返回，或者<a href="${jr_url }"
							class="success_a">点击这里</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var i = 5;
	var timer;
	timer = setInterval("fun()", 1000);
	function fun() {
		if (i == 0) {
			location.href=$("#url").val();
			clearInterval(timer);
		}
		document.getElementById("mes1").innerHTML = i;
		i--;
	}
</script>
</html>