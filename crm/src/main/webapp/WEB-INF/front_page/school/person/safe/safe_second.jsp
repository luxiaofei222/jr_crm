<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <h1>密码安全</h1> 
    <div class="step">
      <ul>
        <li class="number_one on"><span class="li_numbers">1</span><p>密码找回路径</p></li>
        <li class="number_two on"><i class="fa fa-angle-double-right"></i><span class="li_numbers">2</span><p>输入验证码<p></li>
        <li class="number_three"><i class="fa fa-angle-double-right"></i><span class="li_numbers">3</span><p>填写新密码<p></li>
        <li  class="number_four"><i class="fa fa-angle-double-right"></i><span class="li_numbers">4</span><p>修改成功</p></li>
      </ul>
      <div class="clear"></div>
      <div class="pathway_one">
      <c:if test="${type=='mail' }">
        <div class="to_send">系统已向您的邮箱<span>${fn:substring(user.user_mail, '0', '8')}****</span>发送了验证码，请及时查看！</div>
       </c:if>
       <c:if test="${type=='phone' }">
        <div class="to_send">系统已向您的手机<span>${fn:substring(user.user_phone, "0", "5")}****</span>发送了验证码，请及时查看！</div>
       </c:if>
        <div class="input_number">
          <span>验证码：</span>
          <input type="text" id="code" onblur="check_safe_code()" />
          <i class="fa fa-check" id="tubiao" style="display: none;"></i>
          <div class="clear"></div>
         <div class="no_phone"></div>
        </div>
      </div>
      <div class="next"><input type="button" onclick="get_third_safe_page()" value="下一步" /></div>
    </div>