<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	var i = 5;
	var timer;
	timer = setInterval("fun()", 1000);
	function fun() {
		if (i == 0) {
			window.location.reload();
			clearInterval(timer);
		}
		document.getElementById("mes1").innerHTML = i;
		i--;
	}
</script>
<div class="zhuce_success">
	<div class="success_title">
		<div class="success_title2">
			<img src="/images/school/front/index/happy.png">
		</div>
		<div class="success_title1">恭喜您，注册成功</div>

	</div>
	<p class="success_title4">您可以尽情的学习啦…</p>
	<p class="success_title3">
		将在 <span id="mes1" style="color: #06C1AE; font-size: 25px;"></span>
		秒钟后返回，或者<a href="javascript:void(0)" onclick="reload()"
			class="success_a">点击这里</a>
	</p>
</div>