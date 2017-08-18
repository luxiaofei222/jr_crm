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
        <li class="number_three on"><i class="fa fa-angle-double-right"></i><span class="li_numbers">3</span><p>填写新密码<p></li>
        <li  class="number_four"><i class="fa fa-angle-double-right"></i><span class="li_numbers">4</span><p>修改成功</p></li>
      </ul>
      <div class="clear"></div>
      <div class="pathway_two">
        <ul>
          <li>
            <label>新密码：</label>
            <input type="password" style="display:none" />
            <input type="password" onblur="check_safe_pass()" id="new_password" onfocus="this.type='password'" autocomplete="off"/><i class="fa fa-check" id="tubiao1" style="display: none;"></i>
            <input type="password" style="display:none" />
          </li>
          <li>
            <label>确认新密码：</label>
            <input type="password" onblur="check_safe_confirm()" id="confirm_new_password" autocomplete="off"/><i class="fa fa-check" id="tubiao2" style="display:none;"></i>
          </li>
        </ul>
        <div class="clear"></div>
        <div class="no_phone"></div>
      </div>
      <div class="next"><input type="button" onclick="update_safe_pass(${user.user_id })" value="完成" /></div>
    </div>