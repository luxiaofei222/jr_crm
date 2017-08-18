<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/course_sign/reset.css" />
<link rel="stylesheet" type="text/css" href="/font-awesome-4.6.3/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="/css/employee/index_center.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>私信</title>
<style type="text/css">
.sixin {
	width:1001px;
	margin:50px auto;
	height:670px;
	background: #f8f8f8;
    border-top: 1px solid #cfcfcf;
    border-right: 1px solid #cfcfcf;
    border-bottom: 1px solid #cfcfcf;
	}
.sixin_left {
	width:200px;
	height:670px;
	float:left;
	background-color:#6cb7ab;
	}
.sixin_left .title {
	width:200px;
	height:60px;
	background-color:#79ccbf;
	color:#fff;
	line-height: 60px;
    text-align: center;
	}
.sixin_left .title i {
	margin-right:5px;
	}
.sixin_left .linkman ul li {
	width:200px;
	height:45px;
	line-height:45px;
	color:#fff;
	}
.sixin_left .linkman ul li img {
	width:28px;
	height:28px;
	border-radius:50%;
	border:none;
	float:left;
	margin:8.5px 10px 0px 20px;
	}
.sixin_left .linkman ul li p {
	float:left;
	max-width: 120px;
	height: 45px;
	overflow: hidden;
	text-overflow:ellipsis;
    white-space: nowrap;
	}
.sixin_left .linkman ul li span {
    width:8px;
	height:8px;
	border-radius:50%;
	background-color:#f44336;
	visibility: hidden;
	float: right;
    margin-top: 18px;
    margin-right: 10px;
    }
.sixin_left .linkman ul li:hover {
	cursor:pointer;
	background-color:#42ad9c;
	}
.sixin_left .linkman ul li.li_hover {
	background-color:#42ad9c;
	}

.sixin_right {
	width:800px;
	height:670px;
	float:left;
	}
.sixin_right .title {
	width:800px;
	height:60px;
	line-height:60px;
	border-bottom: 1px solid #cfcfcf;
	color:#79ccbf;
	}
.sixin_right .title i {
	margin-left:20px;
	margin-right:10px;
	}

.sixin_content {
	width:750px;
	margin:22px auto;
	background: #fff;
    border: 1px solid #ccc;
	height: 565px;
	}
.sixin_content h4 {
	width:725px;
	height:40px;
	color:#fff;
	padding-left:25px;
	background-color:#79ccbf;
	font-weight:normal;
	line-height:40px;
	font-size:14px;
	}
.sixin_content h4 em {
	margin:0px 5px;
	font-weight:bold;
	font-style:inherit;
	}

.sixin_content .private_letter {
	height:380px;
	overflow-y:auto;
	overflow-x:hidden;
	border-bottom: 1px solid #cfcfcf;
	}
.sixin_content .sixin_con {
	margin-top:10px;
	margin-left:15px;
	}
.sixin_content .sixin_con.sixin_con_right {
	margin-left:0px;
	margin-right:15px;
	}
.sixin_content .sixin_con img {
	width:28px;
	height:28px;
	}
.sixin_content .sixin_con p {
	padding: 5px 11px;
    background: #f8f8f8;
    border: 1px solid #e6e6e6;
    border-radius: 1px;
    min-height: 21px;
    line-height: 20px;
    font-size: 12px;
    position: relative;
    word-wrap: break-word;
    max-width: 420px;
	}
.sixin_content .sixin_con_left img,.sixin_content .sixin_con_left .sixin_sixin {
	float:left;
	}
.sixin_content .sixin_con_right img,.sixin_content .sixin_con_right .sixin_sixin {
	float:right;
	}
.sixin_content .sixin_con_left .sixin_sixin {
	margin-left:15px;
	}
.sixin_content .sixin_con_right .sixin_sixin {
	margin-right:15px;
	}
.sixin_content .sixin_con .time{
	display: block;
    color: #ccc;
    height: 25px;
    line-height: 25px;
    clear: both;
	font-size:14px;
	margin-top:5px;
	}
.sixin_content .sixin_con_right .time {
	float:right;
	}

.reply p {
	line-height:35px;
	color:#353232;
	padding-left:15px;
	color:#eb7350;
	}
.reply p i {
	margin-right:5px;
	}
.reply textarea {
	width:710px;
	display:block;
	margin:0 auto;
	height:40px;
	border: 1px solid #cfcfcf;
	padding:5px;
	line-height:20px;
	margin-top:5px;
	}
.reply a {
	display:block;
	width:80px;
	height:30px;
	line-height:30px;
	text-align:center;
	color:#fff;
	background-color:#eb7350;
	border-radius:3px;
	float:right;
	font-size:12px;
	margin-right:15px;
	margin-top:12px;
	}
.reply a:hover {
	background-color:#FF9966;
	}
.linkman{
	height: 610px;
	overflow-y:auto;
	overflow-x:hidden;
}
</style>
</head>
<c:if test="${empty employee.beijing }">
<body style="background-image:url(/images/employee/bg.jpg);">
</c:if>
<c:if test="${not empty employee.beijing }">
<body style="background-image:url(${employee.beijing});">
</c:if>
	<!-- 换肤按钮start -->
	<!-- <div class="huanfu"></div> -->
	<!-- 换肤按钮end -->
	<div class="top">
		<div class="top_nav">
			<div class="left">京人教育集团欢迎您~</div>
			<div class="right">
				<ul>
					<li onclick="location.href='/center/get_employee_center.jr'"><i class="fa fa-home"></i>返回首页</li>
					<li onclick="location.href='/admin.jr'"><i class="fa fa-sign-out"></i>进入系统</li>
					<li onclick="location.href='/center/to_get_employee.jr?employee_id=${employee.employee_id}'"><i class="fa fa-cog"></i>个人中心</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="sixin">
  <div class="sixin_left">
    <div class="title"><i class="fa fa-envelope"></i>私信消息</div>
    <div class="linkman">
      <ul>
      <c:forEach items="${messages_employee }" var="emplo" varStatus="vs">
      <c:if test="${vs.index==0 }">
        <li class="li_hover" onclick="refush_now_duihua(${emplo.employee.employee_id})"><img src="${emplo.employee.employee_pic }" /><p>${emplo.employee.employee_name }</p></li>
      </c:if>
       <c:if test="${vs.index!=0 }">
        <li onclick="refush_now_duihua(${emplo.employee.employee_id})"><img src="${emplo.employee.employee_pic }" /><p>${emplo.employee.employee_name }</p>
        <c:if test="${emplo.weidu>0 }">
        <span class="diaodian" id="xiaodian${emplo.employee.employee_id}" style="visibility: visible;"></span>
        </c:if>
        </li>
        </c:if>
        </c:forEach>
      </ul>
    </div>
  </div> 
  <div class="sixin_right">
    <div class="title"><span style="cursor: pointer;" onclick="set_all_yidu()"><i class="fa fa-coffee"></i>全部设为已读</span></div>
    <div class="sixin_content">
      <h4>正在与<em>${employee_duihua.employee_name }</em>对话</h4>
      <div id="duihua">
      <div class="private_letter">
      <c:forEach items="${messages_all }" var="message">
      <c:if test="${message.get_employee_id!=employee_duihua.employee_id  }">
        <div class="sixin_con sixin_con_left">
          <img src="${message.employee.employee_pic }" />
          <div class="sixin_sixin">
            <p>${message.private_message_content }</p>
            <span class="time"><fmt:formatDate  value="${message.private_message_time }" /></span>
          </div>
        <div class="clear"></div>
        </div>
        </c:if>
        <c:if test="${message.get_employee_id==employee_duihua.employee_id }">
        <div class="sixin_con sixin_con_right">
          <img src="${message.employee.employee_pic }" />
          <div class="sixin_sixin">
            <p>${message.private_message_content }</p>
            <span class="time"><fmt:formatDate  value="${message.private_message_time }" /></span>
          </div>
          <div class="clear"></div>
        </div>
        </c:if>
        </c:forEach>
       </div>
      <div class="reply">
        <p><i class="fa fa-comments"></i>发表回复</p>
        <textarea onblur="check_content()" id="private_message_content"></textarea>
        <a href="javascript:void(0)" onclick="send_message(${employee_duihua.employee_id},'${employee_duihua.employee_name }')">提交</a>
        <div class="clear" ></div>
      </div>
      </div>
    </div>
  </div> 
</div>
<script type="text/javascript">
//刷新当前对话
function refush_now_duihua(employee_id){
	  $.post("/private_message/get_now_dangqian_duihua.jr",{
		  "now_employee_id":employee_id
	  },function(data){
		  	$("#xiaodian"+employee_id).remove();
			$("#duihua").html(data);
	  })
}
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
	var private_message_content=$("#private_message_content").val();
	  if (check_content()) {
		  $.post("/private_message/send_private_message.jr",{
			  "get_employee_id":employee_id,
			  "get_employee_name":employee_name,
			  'private_message_content':private_message_content
		  },function(data){
			  if(data==1){
				//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                layer.msg('恭喜您，发送成功');
                $("#private_message_content").val("");
              	refush_now_duihua(employee_id);
				}else{
					tanchuang('很遗憾，发送失败');
				}
		  })
		} 
}
//设置已读
function set_all_yidu(){
	$.post("/private_message/set_all_yidu.jr",
			function(data){
		if(data==1){
			$(".diaodian").remove();
			layer.msg("设置成功！");
		}
	})
}
$(".linkman ul li").click(function(){
		$(this).addClass("li_hover").siblings().removeClass("li_hover");
		})
</script>
</body>
</html>