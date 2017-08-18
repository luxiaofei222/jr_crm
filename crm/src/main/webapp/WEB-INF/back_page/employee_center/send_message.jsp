<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>添加章</title>
<style type="text/css">
.sixin {
	background-color:#fff;
	width:100%;
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
	margin-top:5px;
	margin-bottom:5px;
	}
.sixin .person dl dd.job {
	font-size:13px;
	color:#999;
	}
.sixin textarea {
	width:520px;
	height:90px;
	border:#6cb7ab solid 2px;
	padding:5px;
	line-height:25px;
	font-size:12px;
	color:#3b3b3b;
	margin-top:15px;
	margin-left:20px;
	resize: none;
	}
.sixin button {
	float:right;
	width:80px;
	height:30px;
	line-height:30px;
	text-align:center;
	color:#fff;
	border:none;
	background-color:#6cb7ab;
	border-radius:5px;
	margin-top:20px;
	margin-right:20px;
	}
.sixin button:hover {
	background-color:#79ccbf;
	}
</style>
</head>
<script>
  
  //检查私信的内容是否为null
  function check_content(){
	  var private_message_content=$("#private_message_content").val();
	  if(private_message_content!=null&&private_message_content!=""){
			return true;
		}else{
			layer.msg('请输入私信内容！');
			return false;
		}
  }
  //发送私信
  function send_message(employee_id,employee_name){
	  if (check_content()) {
			$("#myform").ajaxSubmit({
				type : 'POST',
				data:{'get_employee_name':employee_name},
				url : "/private_message/send_private_message.jr?get_employee_id="+employee_id,
				success : function(data) {
					if(data==1){
						//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
	                    tanchuang('恭喜您，发送成功');
	                	$('#myform')[0].reset();
					}else{
						tanchuang('很遗憾，发送失败');
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					tanchuang('很遗憾，发送失败');
				}
			});
		} 
  }
</script>

<body>
<div style="padding: 20px;">
  <form enctype="multipart/form-data" id="myform" class="form-horizontal">
    <div class="sixin">
    <div class="person">
      <dl>
        <dt>
          <c:if test="${empty employee.employee_pic }">
			<img src="/images/employee/picture.jpg" class="left pic" />
		  </c:if>
		  <c:if test="${not empty employee.employee_pic }">
			<img src="${employee.employee_pic }" class="left pic" />
		  </c:if>
        </dt>
        <dd class="name">${employee.employee_name }</dd>
        <dd class="job">${bumen.organization_name }&nbsp;——&nbsp;${gangwei.organization_name }</dd>
        <div class="clear"></div>
      </dl>
    </div>
    <textarea placeholder="请输入您要发送的内容..." onblur="check_content()" id="private_message_content" name="private_message_content"></textarea>
    <button type="reset">重置</button> 
    <button type="button" onclick="send_message(${employee.employee_id},'${employee.employee_name }')">发送</button>
    <div class="clear"></div>
</div>
	</form>	
	</div>
</body>
</html>