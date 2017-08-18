<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/employee/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>名片</title>
<script>
function close_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
</script>
</head>
<style type="text/css">
.business_card {
	background-color:#fff;
	width:100%;
	height:250px;
	}
.business_card .message {
    min-height: 240px;
    }
.business_card .message .pic {
	width:150px;
	height:150px;
	border-radius:50%;
	margin-top:35px;
	margin-left:25px;
	}
.business_card .message ul {
	margin-left:40px;
	}
.business_card .message ul .name {
	font-size:22px;
	color:#444;
	margin-top: 15px;
	}
.business_card .message ul .job {
	font-size:18px;
	color:#999;
	margin-top: 10px;
	}
.business_card .message ul .number,.business_card .message ul .phone,.business_card .message ul .qq {
	margin-top:15px;
	}
.business_card .message ul li img {
	width:25px;
	height:25px;
	}
.business_card .message .grade img {
    width: 16px;
    height: 16px;
    }
.business_card .message ul li span {
	font-size:16px;
	color:#595757;
	margin-left:18px;
	line-height:25px;
	}
.business_card .message .autograph {
    margin-left: 25px;
    margin-top: 20px;
    }
.business_card .message .autograph span {
    display: inline-block;
    font-size: 16px;
    color: #79ccbf;
}
.business_card .message .autograph p {
    display: inline-block;
    font-size: 14px;
    color: #444;
}
.business_card .close {
	width:100%;
	height:40px;
	line-height:40px;
	background-color:#f5f5f5;
	font-size:16px;
	color:#444;
	font-weight:bold;
	margin-top:29px;
	}
.business_card .close span {
	display:block;
	margin-right:20px;
	text-align:right;
	cursor:pointer;
	}
</style>
<body>
<div class="business_card">
  <div class="message">
		<c:if test="${empty employee.employee_pic }">
			<img src="/images/employee/picture.jpg" class="left pic" />
		</c:if>
		<c:if test="${not empty employee.employee_pic }">
			<img src="${employee.employee_pic }" class="left pic" />
		</c:if>
		<ul class="left">
			<li class="name">${employee.employee_name }
			<div class="grade" title="您的积分:${employee.jifen }">
		<c:if test="${not empty apple &&apple!=0 }">
		<c:forEach begin="1" end="${apple }">
		<img src="/images/employee/apple.png" />
		</c:forEach>
		</c:if>
		<c:if test="${flower!=0 }">
		<c:forEach begin="1" end="${flower }">
		<img src="/images/employee/flowers.png" />
		</c:forEach>
		</c:if>
		<c:forEach begin="1" end="${ye }">
		<img src="/images/employee/selvef.png" />
		</c:forEach>
		</div>
			</li>
			<li class="job">${bumen.organization_name }&nbsp;——&nbsp;${gangwei.organization_name }</li>
			<li class="number"><img src="/images/employee/number.png"
				class="left" /><span class="left">${employee.job_number }</span></li>
			<div class="clear"></div>
			<li class="phone"><img src="/images/employee/phone.png"
				class="left" /><span class="left">${employee.employee_phone }<c:if test="${empty employee.employee_phone }">暂无</c:if></span></li>
			<div class="clear"></div>
			<li class="qq"><img src="/images/employee/qq.png" class="left" /><span
				class="left">${employee.employee_qq }
				<c:if test="${empty employee.employee_qq }">暂无</c:if>
				</span></li>
			<div class="clear"></div>
		</ul>
		<div class="clear"></div>
		<div class="autograph"><p><span>个性签名：</span>${employee.employee_trades }</p></div>
	</div>
	<div class="close">
		<span onclick="close_layer()">关闭</span>
	</div>
</div>
</body>
</html>