<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:forEach items="${subemployeesays}" var="sub_say">
		<div class="clear"></div>
		<div class="comment_text">
			<div class="comment_img left">
				<c:if test="${empty sub_say.employee.employee_pic }">
					<img src="/images/employee/picture.jpg" class="left" />
				</c:if>
				<c:if test="${not empty sub_say.employee.employee_pic }">
					<img src="${sub_say.employee.employee_pic }" class="left" />
				</c:if>
			</div>
			<div class="comment_user left">
				<div class="comment_message">
					<span>${sub_say.employee.employee_name }</span>：${sub_say.say_content }
				</div>
				<div class="comment_time">${sub_say.time_change }<i
						class="fa fa-commenting-o" pid="${sub_say.say_id }" title="回复"></i>
				</div>
			</div>
			<c:forEach items="${sub_say.sub_employeeSays }" var="sanji">
				<div class="erji_pinglun">
					<div class="clear"></div>
					<div class="comment_img left">
						<c:if test="${empty sanji.employee.employee_pic }">
							<img src="/images/employee/picture.jpg" class="left" />
						</c:if>
						<c:if test="${not empty sanji.employee.employee_pic }">
							<img src="${sanji.employee.employee_pic }" class="left" />
						</c:if>
					</div>
					<div class="comment_user left">
						<div class="comment_message">
							<c:if test="${sanji.employee_id==sanji.parent_employee_id }">
								<span>${sanji.employee.employee_name }</span>
							</c:if>
							<c:if test="${sanji.employee_id!=sanji.parent_employee_id }">
								<span>${sanji.employee.employee_name }</span>回复<span>${sanji.parent_employee.employee_name }</span>：
									</c:if>
							${sanji.say_content }
						</div>
						<div class="comment_time">${sanji.time_change }<i
								class="fa fa-commenting-o" pid="${sanji.say_id }" title="回复"></i>
						</div>
					</div>
					<div class="clear"></div>
					<div class="comments none" id="comment_one${sanji.say_id }">
						<textarea id="one_content${sanji.say_id }"></textarea>
						<a href="javascript:void(0)"
							onclick="huifu_say_three(${sanji.say_id },${sanji.parent_id },${sanji.employee_id },${employeeSays.pageNumber })">发表</a>
					</div>
				</div>
			</c:forEach>
			<div class="comments none" id="comment_one${sub_say.say_id }">
				<textarea id="one_content${sub_say.say_id }"></textarea>
				<a href="javascript:void(0)"
					onclick="huifu_say_two(${sub_say.say_id },${sub_say.employee_id },${employeeSays.pageNumber })">发表</a>
			</div>
			<div class="clear"></div>
		</div>
	</c:forEach>