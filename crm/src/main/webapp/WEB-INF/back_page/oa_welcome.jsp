<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登陆</title>
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/oa/index/oa_welcome.css" />
<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="/js/school/back/common/bootstrap.min.js"
	type="text/javascript"></script>
</head>
<script type="text/javascript">
	function to_admin() {
		location.href = "/admin.jr"
	}
</script>
<body>
	<div class="welcome_wrapper">
		<div class="welcome_item">
			<img src="/images/oa/oa_welcome.png">
			<div class="welcome_button">
				<button type="button" class="btn btn-lg b_left" >
					${sessionScope.employee_session.employee_name }</button>
				<button type="button" onclick="to_admin()" class="btn btn-lg b_right">返回首页</button>
			</div>
		</div>
	</div>
</body>
</html>