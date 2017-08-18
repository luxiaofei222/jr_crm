<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
//翻页上一页下一页
function leave_jump_page(page,limit ){
	$.post("/back_leave/get_employee_leave.jr",{
		'pageNumber' : page,
		'limit' : limit
	},function(data){
		$("#jindu").html(data);
	})
}
//打开销假页面
function get_xiaojia_page(leave_id){
	layer.open({
		  type: 2,
		  title: ['销假'],
		  area: ['550px', '350px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_leave/get_xiaojia_page.jr?leave_id="+leave_id
		  });
}
</script>
<c:if test="${empty backLeaves.list }">
	<div
		style="margin: 0 auto; text-align: center; font-size: 18px; color: #ea592d; margin-top: 178px; line-height: 30px;">
		您还没有请过假哦！可喜可贺！<br />
	</div>
</c:if>
<c:if test="${not empty backLeaves.list }">
	<div class="leave_plan">
		<select id="change_project" onchange="get_progect_page()">
			<option value="0">请假申请</option>
			<option value="1">离职申请</option>
			<option value="2">转正申请</option>
		</select>
		<div id="leave_list">
			<c:forEach items="${backLeaves.list }" var="leave">
				<ul>
					<li><span class="lp_title">请假时间：</span> <span><fmt:formatDate
								type="both" value="${leave.leave_start_time }" />至<fmt:formatDate
								type="both" value="${leave.leave_end_time }" /></span></li>
					<li><span class="lp_title">请假类别：</span> <span><c:if
								test="${leave.leave_type==1 }">
							事假
							</c:if> <c:if test="${leave.leave_type==2 }">
							病假
							</c:if> <c:if test="${leave.leave_type==3 }">
							婚假
							</c:if> <c:if test="${leave.leave_type==4 }">
							产假
							</c:if> <c:if test="${leave.leave_type==5 }">
							丧假
							</c:if> <c:if test="${leave.leave_type==6 }">
							其他
							</c:if> <c:if test="${leave.leave_type==7 }">
							倒休
							</c:if> <c:if test="${leave.leave_type==8 }">
							年假
							</c:if></span></li>
					<li><span class="lp_title">提交时间：</span> <span><fmt:formatDate
								type="both" value="${leave.leave_time }" /></span></li>
					<li><span class="lp_title">请假事由：</span> <span>${leave.leave_content}</span></li>
					<li><span class="lp_title">审批状态：</span> <span class="orange"><c:if
								test="${leave.leave_state==0}">
								经理审批中
							</c:if> <c:if test="${leave.leave_state==1}">
								总监审批中
							</c:if> <c:if test="${leave.leave_state==2}">
								总经理审批中
							</c:if> <c:if test="${leave.leave_state==3}">
								<c:if test="${leave.qingjiazhong==3 }">
								等待销假
							</c:if>
								<c:if test="${leave.qingjiazhong==2 }">
								休假中
							</c:if>
								<c:if test="${leave.qingjiazhong==1 }">
								等待休假
							</c:if>
							</c:if> <c:if test="${leave.leave_state==4}">
								已销假
							</c:if> <c:if test="${leave.leave_state==5}">
								未批准
							</c:if></span></li>
							<c:if test="${leave.leave_state==3 }">
							<c:if test="${leave.qingjiazhong==3 }">
							<li><span><input type="button" onclick="get_xiaojia_page(${leave.leave_id})" value="销假" class="xiaojia" /></span></li>
							</c:if>
							</c:if>
							<c:if test="${not empty leave.beizhu }">
							<li><span class="lp_title">说明：</span> <span style="color: orange;">${leave.beizhu}</span></li>
							</c:if>
				</ul>
			</c:forEach>
			<div class="page">
			<c:if test="${backLeaves.hasPreviousPage==true}">
				<span class="prevpage noacitve" onclick="leave_jump_page(${backLeaves.pageNumber-1},3)">上一页</span>
			</c:if>
			<c:forEach items="${backLeaves.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${backLeaves.pageNumber==page}">
							 <span class="numberpage active">${page}</span>
						</c:when>
						<c:otherwise>
								<span class="numberpage" onclick="leave_jump_page(${page},3)">${page}</span>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${backLeaves.hasNextPage==true}">
				<span class="nextpage" onclick="leave_jump_page(${backLeaves.pageNumber+1},3)">下一页</span>
				</c:if>
			</div>
		</div>
	</div>
</c:if>