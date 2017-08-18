<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${empty positives.list }">
	<div
		style="margin: 0 auto; text-align: center; font-size: 18px; color: #ea592d; margin-top: 178px; line-height: 30px;">
		您没有正在审批的转正申请，如果想离职请慎重考虑哦！<br />
	</div>
</c:if>

<c:if test="${not empty positives.list }">
	<div class="leave_plan1">
		<select id="change_project" onchange="get_progect_page()">
			<option value="2">转正申请</option>
			<option value="1">离职申请</option>
			<option value="0">请假申请</option>
		</select>
		<c:forEach items="${positives.list }" var="positive">
			<div style="clear: both"></div>
			<ul class="lizhi_condi">
				<li class="lizhi_depar">
					<h4>部门：</h4> <input type="text" disabled="disabled"
					placeholder="${positive.bumen }">
				</li>
				<li class="lizhi_post">
					<h4>岗位：</h4> <input type="text" disabled="disabled"
					placeholder="${positive.gangwei }">
				</li>
				<c:if test="${not empty positive.oaEmployee }">
					<li class="lizhi_leave">
						<h4>入职日期：</h4> <input type="text" disabled="disabled"
						value="<fmt:formatDate value="${positive.oaEmployee.ruzhi_time }" />">
        			</li>
        		</c:if>
         <li class="lizhi_leave">
        	<h4>
            	申请转正日期：
            </h4>
            <input type="text" disabled="disabled" value="<fmt:formatDate value="${positive.positive_time }" />">
        </li>
      </ul>
       <h4>
          试用期个人工作总结：
      </h4>
      <textarea disabled="disabled" style="height: 150px">${positive.positive_content }</textarea>
       <c:if test="${not empty positive.jingli_message }">
        <h4>
          部门经理意见：
      </h4>
      <textarea disabled="disabled" style="height: 80px">${positive.jingli_message }</textarea>
      </c:if>
      <c:if test="${not empty positive.zongjian_message }">
      <h4>
          总监意见：
      </h4>
      <textarea disabled="disabled" style="height: 80px">${positive.zongjian_message }</textarea>
      </c:if>
     <c:if test="${not empty positive.hr_message }">
      <h4>
          人事意见：
      </h4>
      <textarea disabled="disabled" style="height: 80px">${positive.hr_message }</textarea>
      </c:if>
      <c:if test="${not empty positive.boss_message }">
      <h4>
         总经理意见：
      </h4>
      <textarea disabled="disabled" style="height: 80px">${positive.boss_message }</textarea>
      </c:if>
      <h4 style="margin-top:14px;margin-bottom:17px;">
          审批状态：<span class="orange"><c:if test="${positive.positive_state==0}">
								经理审批中
							</c:if>
							<c:if test="${positive.positive_state==1}">
								总监审批中
							</c:if>
							<c:if test="${positive.positive_state==2}">
								人力总监审批中
							</c:if>
							<c:if test="${positive.positive_state==3}">
								总经理审批中
							</c:if>
							<c:if test="${positive.positive_state==4}">
								未批准
							</c:if>
							<c:if test="${positive.positive_state==5}">
								已批准
							</c:if></span>
      </h4>
	</c:forEach>
	</div>
</c:if>