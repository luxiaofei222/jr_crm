<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
$(function(){
	$(".side_nav ul li").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
	})
})
</script>
<c:if test="${empty oaTasks.list }">
  	<div style="margin:0 auto;text-align:center;font-size:18px;color:#ea592d;margin-top:178px;line-height:30px;">您没有任务可以做！快去找你的领导要去吧！</div>
  </c:if>
<c:if test="${not empty oaTasks.list }">
	<div class="cont">
		<c:forEach items="${oaTasks.list }" var="task">
			<div class="task">
				<div class="tasks">
					<ul>
						<li><span>发布者：</span><img src="${task.fabuem.employee_pic }" /><span
							class="name">${task.fabuem.employee_name }</span></li>
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
						<li>预计完成日期：<fmt:formatDate value="${task.task_finish_time }" /></li>
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
							<a onclick="fankui_task(${task.task_id})"
								href="javascript:void(0)">反馈</a> <a
								onclick="finish_task(${task.task_id})" href="javascript:void(0)">完成</a>
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
					onclick="leave_jump_page(${oaTasks.pageNumber-1},3,${employee_id },'${task_type }')">上一页</span>
			</c:if>
			<c:forEach items="${oaTasks.navigatePageNumbers }" var="page">
				<c:choose>
					<c:when test="${oaTasks.pageNumber==page}">
						<span class="numberpage active">${page}</span>
					</c:when>
					<c:otherwise>
						<span class="numberpage"
							onclick="leave_jump_page(${page},3,${employee_id },'${task_type }')">${page}</span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${oaTasks.hasNextPage==true}">
				<span class="nextpage"
					onclick="leave_jump_page(${oaTasks.pageNumber+1},3,${employee_id },'${task_type }')">下一页</span>
			</c:if>
		</div>
	</div>
</c:if>