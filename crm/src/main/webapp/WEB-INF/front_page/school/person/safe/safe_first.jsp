<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
$(function() {
	$(".person_content .pathway ul li").click(function() {
        $(this).addClass("xuanzhong").siblings().removeClass("xuanzhong");
	});
});
</script>
<h1>密码安全</h1> 
    <div class="step">
      <ul>
        <li class="number_one on"><span class="li_numbers">1</span><p>选择验证方式</p></li>
        <li class="number_two"><i class="fa fa-angle-double-right"></i><span class="li_numbers">2</span><p>输入验证码<p></li>
        <li class="number_three"><i class="fa fa-angle-double-right"></i><span class="li_numbers">3</span><p>填写新密码<p></li>
        <li  class="number_four"><i class="fa fa-angle-double-right"></i><span class="li_numbers">4</span><p>修改成功</p></li>
      </ul>
      <div class="clear"></div>
      <div class="pathway">
        <ul>
        <c:if test="${not empty user.user_phone }">
          <li class="step_phone xuanzhong">
          	<input type="hidden" value="phone">
          	<input type="hidden" id="user_phone" value="${user.user_phone }">
            <p>通过手机号码找回</p>
            <p class="size">通过您注册的手机号${fn:substring(user.user_phone, "0", "5")}****验证找回登录密码</p>
          </li>
          </c:if>
          <c:if test="${not empty user.user_mail }">
          <li class="step_mail">
          <input type="hidden" value="mail">
          <input type="hidden" id="user_mail" value="${user.user_mail }">
            <p>通过邮箱找回</p>
            <p class="size">通过您注册填写的邮箱${fn:substring(user.user_mail, '0', '8')}****找回登录密码</p>
          </li>
          </c:if>
        </ul>
        <div class="clear"></div>
        <div class="no_phone"></div>
      </div>
      <div class="next"><input type="button" onclick="xiayibu_safe()" value="下一步" /></div>
    </div>