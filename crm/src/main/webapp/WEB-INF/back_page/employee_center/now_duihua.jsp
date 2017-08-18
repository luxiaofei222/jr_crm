<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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