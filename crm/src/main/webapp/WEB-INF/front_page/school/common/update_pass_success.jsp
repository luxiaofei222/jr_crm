<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	var t = 5;
	var timer1;
	timer1 = setInterval("fun()", 1000);
	function fun() {
		if (t == 0) {
			window.location.reload();
			clearInterval(timer);
		}
		document.getElementById("mes").innerHTML = t;
		t--;
	}
</script>
<div class="findpass_success">
	<div class="success_title" style="width: 350px;">
		<div class="success_title2">
			<img src="/images/school/front/index/happy.png">
		</div>
		<div class="success_title1">恭喜您，找回密码成功</div>

	</div>
	<p class="success_title4">您可以尽情的学习啦…</p>
	<p class="success_title3">
		将在 <span id="mes" style="color: #06C1AE; font-size: 25px;"></span>
		秒钟后返回，或者<a href="javascript:void(0)" onclick="reload()"
			class="success_a">点击这里</a>
	</p>
</div>