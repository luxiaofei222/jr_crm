<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/school/front/index/reset.css" rel="stylesheet"	type="text/css" />
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" type="text/css"	href="/css/school/back/layui/css/layui.css">
<script src="/js/school/front/course_video/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/js/school/front/course_video/jquery.raty.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>任务反馈</title>
<script>
	//关闭弹窗
	function close_layer(){
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
	}
	//发送反馈内容
	function send_fankui(obj,task_id){
		var fankui=$("#fankui").val();
		if(fankui!=null&&fankui!=""){
			$(obj).attr("disabled", "disabled");
			$(obj).val("发送中");
			$.post("/back_task/fankui.jr",{
				'task_id':task_id,
				'task_jianyi':fankui
			},function(data){
				if(data==1){
					layer.msg("发送成功！");
					$(obj).removeAttr("disabled");
					$(obj).val("发送");
					$("#fankui").val("");
				}else{
					layer.msg("发送失败！");
					$(obj).removeAttr("disabled");
					$(obj).val("发送");
				}
			})
		}else{
			layer.msg("请输入您要反馈的内容！");
		}
	}
</script>
<style type="text/css">
.sixin .person {
	padding-top:20px;
	}
.sixin .person dl {
	width:460px;
	height:60px;
	margin-left:20px;
	}
.sixin .person dl dt,.sixin .person dl dd {
	float:left;
	}
.sixin .person dl dt img {
	width:60px;
	height:60px;
	border-radius:50%;
	}
.sixin .person dl dd {
	margin-left:20px;
	}
.sixin .person dl dd.name {
	font-size:15px;
	color:#3b3b3b;
	width:375px;
	margin-top:10px;
	margin-bottom:5px;
	}
.sixin .person dl dd.job {
	font-size:13px;
	color:#999;
	}
.sixin textarea {
	width:510px;
	height:95px;
	border:#ea592d solid 2px;
	padding:5px;
	line-height:25px;
	font-size:12px;
	color:#3b3b3b;
	margin-top:15px;
	margin-left:20px;
	}
.sixin a,.sixin input {
    border:none;
	float:right;
	width:80px;
	height:30px;
	line-height:30px;
	text-align:center;
	color:#fff;
	background-color:#ea592d;
	border-radius:5px;
	margin-top:20px;
	margin-right:20px;
	font-size:14px;
	font-weight:normal;
	}
.sixin a:hover,.sixin input:hover {
	background-color:#FF9966;
	color:#fff;
	}
</style>
</head>
<body>
  <div class="sixin">
    <div class="person">
      <dl>
      <c:if test="${not empty oaTask.fabuem.employee_pic }">
        <dt><img src="${oaTask.fabuem.employee_pic }" /></dt>
        </c:if>
         <c:if test="${empty oaTask.fabuem.employee_pic }">
        <dt><img src="/images/employee/picture.jpg" /></dt>
        </c:if>
        <dd class="name">${oaTask.fabuem.employee_name }</dd>
        <dd class="job">${Organization.organization_name }</dd>
        <div class="clear"></div>
      </dl>
    </div>
    <textarea placeholder="请输入您要发送的内容..." id="fankui"></textarea>
    <a href="javascript:void(0)" onclick="close_layer()">关闭</a><input type="button" onclick="send_fankui(this,${oaTask.task_id})" class="send" value="发送" />
    <div class="clear"></div>
</div>
</body>
</html>