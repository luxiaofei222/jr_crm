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
<link rel="stylesheet" type="text/css" href="/css/employee/work_log.css" />
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
//提交日报
function send_em_log(obj,employee_id){
	var log_content=$("#log_content").val();
	if(log_content!=null&&log_content!=""){
		$(obj).attr("disabled", "disabled");
		$(obj).val("提交中");
		$.post("/em_log/send_em_log.jr",{
			'employee_id':employee_id,
			'log_content':log_content
		},function(data){
			if(data==1){
				layer.msg("提交成功！");
				$(obj).val("已提交");
				$("#log_content").val("");
			}else{
				layer.msg("提交失败！");
				$(obj).removeAttr("disabled");
				$(obj).val("提交");
			}
		})
	}else{
		layer.msg("请输入日报内容！");
	}
}
//翻页
function emlog_jump_page(page,limit,employee_id,now_nianyue ){
	$.post("/em_log/get_back_log_list.jr",{
		 'employee_id':employee_id,
		 'now_nianyue':now_nianyue,
		'pageNumber' : page,
		'limit' : limit
	},function(data){
		$("#log_list").html(data);
	})
}
//切换月份
function emlog_change_page(page,limit,employee_id,now_nianyue ){
	var now_nianyue=$("#time_yue").val();
	$.post("/em_log/get_back_log_list.jr",{
		 'employee_id':employee_id,
		 'now_nianyue':now_nianyue,
		'pageNumber' : page,
		'limit' : limit
	},function(data){
		$("#log_list").html(data);
	})
}
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
					<li style="color: #80c26a"
						onclick="location.href='/em_log/get_back_log.jr?employee_id=${employee.employee_id}'"><img
						src="/images/personal/li-1.png" />
						<p>日志</p></li>
					<li
						onclick="location.href='/back_task/get_back_task.jr?employee_id=${employee.employee_id}'"><img
						src="/images/personal/li-2.png" />
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
		<div class="work_log">
			<textarea placeholder="记录一下你今天的工作成果吧,每天只能输入一次，不可修改！" id="log_content"></textarea>
			<c:if test="${is_tijiao==1 }">
				<input type="button" disabled="disabled"
					onclick="send_em_log(this,${employee.employee_id})" value="已提交" />
			</c:if>
			<c:if test="${is_tijiao==0 }">
				<input type="button"
					onclick="send_em_log(this,${employee.employee_id})" value="提交" />
			</c:if>
		</div>
		<div class="filter">
			<select id="time_yue" onchange="emlog_change_page(1,10,${employee.employee_id })">
				<c:forEach items="${time_list }" var="tim">
					<c:if test="${now_nianyue==tim }">
						<option selected="selected" value="${tim }">${tim }</option>
					</c:if>
					<c:if test="${now_nianyue!=tim }">
						<option value="${tim }">${tim }</option>
					</c:if>
				</c:forEach>
			</select>
			<!-- <input type="button" /> -->
			<div class="clear"></div>
		</div>
		<div id="log_list">
			<c:if test="${empty em_logs.list }">
				<div
					style="margin: 0 auto; text-align: center; font-size: 18px; color: #ea592d; margin-top: 108px; padding-bottom: 108px; line-height: 30px;">
					您还没有写过日报，赶紧写吧，否则该扣工资了！<br />
				</div>
			</c:if>
			<c:if test="${not empty em_logs.list }">
			<div class="main-timeline">
				<c:forEach items="${em_logs.list }" var="em_log" varStatus="vs">
					<div class="timeline">
					<c:if test="${vs.index==0 }"><div class="timeline-icon" style="margin-top: 10px;"></div></c:if>
					<c:if test="${vs.index!=0 }"><div class="timeline-icon"></div></c:if>
					<c:if test="${vs.index==0 }"><div class="timeline-content" style="padding-top:40px;"></c:if>
					<c:if test="${vs.index!=0 }"><div class="timeline-content"></c:if>
					<c:if test="${vs.index==0 }"><h2 class="title" style="top:3px;">
								<fmt:formatDate pattern="yyyy年MM月dd日"
									value="${em_log.tijiao_time }" />
							</h2></c:if>
					<c:if test="${vs.index!=0 }"><h2 class="title">
								<fmt:formatDate pattern="yyyy年MM月dd日"
									value="${em_log.tijiao_time }" />
							</h2></c:if>
							
							<div class="daily_record">
								<div class="daily">
									<c:if test="${not empty em_log.employee.employee_pic }">
										<img src="${em_log.employee.employee_pic }" />
									</c:if>
									<c:if test="${empty em_log.employee.employee_pic }">
										<img src="/images/employee/picture.jpg" />
									</c:if>

									<div class="left">
										<p class="name">${em_log.employee.employee_name }</p>
										<p class="time">
											<fmt:formatDate pattern="yyyy年MM月dd日   HH:mm:ss"
												value="${em_log.tijiao_time }" />
										</p>
									</div>
									<div class="clear"></div>
									<p class="message">${em_log.log_content }</p>
								</div>
								<c:forEach items="${em_log.emLogs }" var="sub">
							 	<div class="appraise">
									<c:if test="${not empty sub.employee.employee_pic }">
										<img src="${sub.employee.employee_pic }" />
									</c:if>
									<c:if test="${empty sub.employee.employee_pic }">
										<img src="/images/employee/picture.jpg" />
									</c:if>
									<div class="left">
										<span class="appraise_time"><fmt:formatDate pattern="yyyy年MM月dd日   HH:mm:ss"
												value="${sub.tijiao_time }" /></span>
										<p class="name">${sub.employee.employee_name }</p>
										<p class="appraise_grade">
											<c:forEach begin="1" end="${sub.pingfen }">
											<img src="/images/school/front/course_video/star-on.png" />
											</c:forEach>
										</p>
									</div>
									<div class="clear"></div>
									<p class="message">${sub.log_content }</p>
								</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:forEach>
				<div id="pages" class="right page">
						<c:if test="${em_logs.hasPreviousPage==true}">
							<span class="prevpage noacitve"
								onclick="emlog_jump_page(${em_logs.pageNumber-1},10,${employee.employee_id },'${now_nianyue }')">上一页</span>
						</c:if>
						<c:forEach items="${em_logs.navigatePageNumbers }" var="page">
							<c:choose>
								<c:when test="${em_logs.pageNumber==page}">
									<span class="numberpage active">${page}</span>
								</c:when>
								<c:otherwise>
									<span class="numberpage" onclick="emlog_jump_page(${page},10,${employee.employee_id },'${now_nianyue }')">${page}</span>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${em_logs.hasNextPage==true}">
							<span class="nextpage"
								onclick="emlog_jump_page(${em_logs.pageNumber+1},10,${employee.employee_id },'${now_nianyue }')">下一页</span>
						</c:if>
					</div>
				</div>
			</c:if>
			</div>
	</div>
</body>
</html>