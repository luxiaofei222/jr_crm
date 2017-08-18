<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务管理系统</title>
	<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
	<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="/css/edu/index/htindex.css">
    <link rel="stylesheet" type="text/css" href="/css/school/back/common/right_content.css">
	<script src="/js/common/jquery-1.11.1.min.js" type="text/javascript"></script>
	<!-- <script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script> -->
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/school/back/common/htindex.js"></script>
<%-- <script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script>
<input id="user_name" value="${sessionScope.employee_session.login_name }" type="hidden" />
 <script type="text/javascript">                        
      var goEasy = new GoEasy({
                            appkey: 'BC-5341e251aab445a192f79c538832335c'
                        });
      var user_name=$("#user_name").val();
      goEasy.subscribe({
          channel: user_name,
          onMessage: function(message){  //自动接收推送信息                           
                  alert('推送消息:'+message.content);
          }
 });

</script> --%>
</head>
<body>
	<div class='wrapper'>
		<!-- 左侧菜单列表 -->
		<jsp:include page="/WEB-INF/back_page/common/caiwumenu.jsp"></jsp:include>
		<div class="ht_right"  id="conten_list">
			<!-- 右侧内容 -->
			<jsp:include page="/WEB-INF/back_page/caiwu_welcome.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>