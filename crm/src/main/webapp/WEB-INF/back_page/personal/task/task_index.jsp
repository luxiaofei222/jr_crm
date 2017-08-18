<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/course_sign/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/employee/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="/font-awesome-4.6.3/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="/css/employee/task.css" />
<link rel="stylesheet" type="text/css" href="/css/employee/top.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/employeecenter/cropbox.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>申请</title>
<script>
//退出系统
function logout_jr(){
	layer.confirm("提示：您好，确定要退出本系统吗？",function(){
		layer.closeAll('dialog');
		$.post("/logout.jr",function(data){
			if(data==1){
				location.href=("/login.jr");
			}
		})
		})
}
//任务反馈
function fankui_task(task_id){
	layer.open({
		  type: 2,
		  title: ['任务反馈'],
		  area: ['550px', '350px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_task/to_fankui_task.jr?task_id="+task_id
		  });
}
//完成
function finish_task(task_id){
	$.post("/back_task/fankui.jr",{
		'task_id':task_id,
		'task_type':"已完成"
	},function(data){
		if(data==1){
			layer.msg("发送成功！");
		}else{
			layer.msg("发送失败！");
		}
	})
}
//跳转分页
function task_jump_page(page,limit,employee_id,task_type){
	$.post("/back_task/get_back_task_list.jr",{
		'pageNumber':page,
		'limit':limit,
		'employee_id':employee_id,
		'task_type':task_type
	},function(data){
		$("#task_list").html(data);
	})
}
$(function(){
	$(".side_nav ul li").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
	})
})
</script>
</head>
<body>
	<div class="top">
		<div class="header">
			<span class="left"> <c:if
					test="${not empty employee.employee_pic }">
					<img src="${employee.employee_pic }" class="left" />
				</c:if> <c:if test="${empty employee.employee_pic }">
					<img src="/images/employee/picture.jpg" class="left" />
				</c:if>
				<div class="left">
					<p>${employee.employee_name }</p>
					<c:if test="${empty employee.employee_trades }">
						<span>这个人很懒，还没有设置...</span>
					</c:if>
					<c:if test="${not empty employee.employee_trades }">
						<span title="${employee.employee_trades}">${employee.employee_trades}</span>
					</c:if>

				</div>
			</span> <span class="right">
				<ul class="left">
					<li onclick="location.href='/em_log/get_back_log.jr?employee_id=${employee.employee_id}'"><img src="/images/personal/li-1.png" />
						<p>日志</p></li>
					<li
						onclick="location.href='/back_task/get_back_task.jr?employee_id=${employee.employee_id}'"
						style="color: #fbce44"><img src="/images/personal/li-2.png" />
						<p>任务</p></li>
					<li
						onclick="location.href='/back_leave/get_back_leave.jr?employee_id=${employee.employee_id}'"><img
						src="/images/personal/li-3.png" />
						<p>申请</p></li>
					<li
						onclick="location.href='/center/to_get_employee.jr?employee_id=${employee.employee_id}'"><img
						src="/images/personal/li-4.png" />
						<p>修改资料</p></li>
					<li onclick="location.href='/admin.jr'"><img
						src="/images/personal/li-5.png" />
						<p>进入系统</p></li>
				</ul>
				<div class="left">
					<span class="left">|</span><i
						class="left icon iconfont icon-shouyeshouye"
						onclick="location.href='/center/get_employee_center.jr'"></i><i
						class="left icon iconfont icon-guanbi" onclick="logout_jr()"></i>
				</div>
			</span>
		</div>
		<div class="clear"></div>
	</div>

	<div class="content">
		<div class="side_nav left">
			<ul>
				<li class="active"><a href="/back_task/get_back_task.jr?employee_id=${employee.employee_id}"></a></li>
				<li onclick="task_jump_page(1,3,${employee.employee_id },'未完成')"><a href="javascript:void(0)"></a></li>
				<li onclick="task_jump_page(1,3,${employee.employee_id },'已完成')"><a href="javascript:void(0)"></a></li>
			</ul>
		</div>
		<div class="side_cont left" id="task_list">
			<c:if test="${empty oaTasks.list }">
  	<div style="margin:0 auto;text-align:center;font-size:18px;color:#ea592d;margin-top:178px;line-height:30px;">您没有任务可以做！快去找你的领导要去吧！</div>
  </c:if>
			<c:if test="${not empty oaTasks.list }">
				<div class="cont" >
					<c:forEach items="${oaTasks.list }" var="task">
						<div class="task">
							<div class="tasks">
								<ul>
									<li><span>发布者：</span><img
										src="${task.fabuem.employee_pic }" /><span class="name">${task.fabuem.employee_name }</span></li>
									<li>发布日期：<fmt:formatDate value="${task.task_time }" /></li>
									<c:if test="${task.zhongyao_state==0 }">
										<li>紧急程度：<em class="grade green"></em>正常
										</li>
									</c:if>
									<c:if test="${task.zhongyao_state==1 }">
										<li>紧急程度：<em class="grade yellow"></em>紧急
										</li>
									</c:if>
									<c:if test="${task.zhongyao_state==2 }">
										<li>紧急程度：<em class="grade red"></em>特急
										</li>
									</c:if>
									<li>预计完成日期：<fmt:formatDate
											value="${task.task_finish_time }" /></li>
								</ul>
								<div class="clear"></div>
								<div class="task_con">
									<p class="text">${task.task_content }</p>
									<c:if test="${not empty task.fujian }">
										<p class="fujian">
											<em>附件1：</em><a href="${task.fujian }">任务附件</a>
										</p>
									</c:if>
									<c:if test="${not empty task.task_jianyi }">
										<div class="feedback">
											<h5>反馈内容</h5>
											<p class="text">${task.task_jianyi }</p>
										</div>
										<!--反馈内容-->
									</c:if>
									<c:if test="${not empty task.pingjia_content }">
										<div class="assessment">
											<h5>评价内容</h5>
											<p class="text">${task.pingjia_content  }</p>
										</div>
										<!--评价内容-->
									</c:if>
								</div>
								<!--任务内容-->
							</div>
							<!--任务-->
							<div class="caozuo">
								<div class="left">
									任务状态：<em>${task.task_type }</em>
								</div>
								<c:if test="${task.task_type=='已完成' }">
									<div class="left riqi">
										完成日期：
										<fmt:formatDate value="${task.finish_time }" />
									</div>
								</c:if>
								<c:if test="${task.task_type=='进行中' }">
									<div class="right">
										<a onclick="fankui_task(${task.task_id})" href="javascript:void(0)">反馈</a> <a
										 onclick="finish_task(${task.task_id})"	href="javascript:void(0)">完成</a>
									</div>
								</c:if>
								<c:if test="${not empty task.pingfen }">
									<div class="right assessment">
										<span>领导评价：</span>
										<c:forEach begin="1" end="${task.pingfen }">
											<img src="/images/school/front/course_video/star-on.png" />
										</c:forEach>
									</div>
								</c:if>
							</div>
							<div class="clear"></div>
						</div>
					</c:forEach>
					<div id="pages" class="right page">
						<c:if test="${oaTasks.hasPreviousPage==true}">
							<span class="prevpage noacitve"
								onclick="task_jump_page(${oaTasks.pageNumber-1},3,${employee.employee_id },'${task_type }')">上一页</span>
						</c:if>
						<c:forEach items="${oaTasks.navigatePageNumbers }" var="page">
							<c:choose>
								<c:when test="${oaTasks.pageNumber==page}">
									<span class="numberpage active">${page}</span>
								</c:when>
								<c:otherwise>
									<span class="numberpage" onclick="task_jump_page(${page},3,${employee.employee_id },'${task_type }')">${page}</span>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${oaTasks.hasNextPage==true}">
							<span class="nextpage"
								onclick="task_jump_page(${oaTasks.pageNumber+1},3,${employee.employee_id },'${task_type }')">下一页</span>
						</c:if>
					</div>
				</div>
			</c:if>
		</div>
		<div class="clear"></div>
	</div>
</body>
<script type="text/javascript">

</script>
</html>