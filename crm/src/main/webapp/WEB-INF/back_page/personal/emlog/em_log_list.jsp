<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${empty em_logs.list }">
				<div
					style="margin: 0 auto; text-align: center; font-size: 18px; color: #ea592d; margin-top: 178px; line-height: 30px;">
					您还没有写过日报，赶紧写吧，否则该扣工资了！<br />
				</div>
			</c:if>
			<c:if test="${not empty em_logs.list }">
			<div class="main-timeline">
				<c:forEach items="${em_logs.list }" var="em_log">
					<div class="timeline">
						<div class="timeline-icon" style="margin-top: 10px;"></div>
						<div class="timeline-content">
							<h2 class="title">
								<fmt:formatDate pattern="yyyy年MM月dd日"
									value="${em_log.tijiao_time }" />
							</h2>
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
								onclick="emlog_jump_page(${em_logs.pageNumber-1},10,${employee_id },'${now_nianyue }')">上一页</span>
						</c:if>
						<c:forEach items="${em_logs.navigatePageNumbers }" var="page">
							<c:choose>
								<c:when test="${em_logs.pageNumber==page}">
									<span class="numberpage active">${page}</span>
								</c:when>
								<c:otherwise>
									<span class="numberpage" onclick="emlog_jump_page(${page},10,${employee_id },'${now_nianyue }')">${page}</span>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${em_logs.hasNextPage==true}">
							<span class="nextpage"
								onclick="emlog_jump_page(${em_logs.pageNumber+1},10,${employee_id },'${now_nianyue }')">下一页</span>
						</c:if>
					</div>
				</div>
			</c:if>