<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心首页</title>

<link href="/css/school/front/person/css.css" rel="stylesheet" type="text/css" />
<link href="/css/school/front/person/person_left.css" rel="stylesheet" type="text/css" />
<link href="/css/school/front/person/person.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/css/school/front/person/flatpickr.min.css">
<link href="/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/common/flatpickr.min.js"></script>
<script type="text/javascript" src="/js/school/front/person/person.js"></script>
<script>
$(function(){
	$.post("/person/get_safe_first.html",
			function(data){
		$("#person_menu_list").html(data);
	})
})
</script>
</head>
<body>
	<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
	<div class="person_top_img"><img src="/images/school/front/person/person_img1.jpg" /></div>
<div class="content">
  <div class="contents">
    <div class="person_nav left">
    <div class="person_head" onclick="change_user_pic(${sessionScope.user_session.user_id })">
      <img src="${sessionScope.user_session.user_pic }" />
      <p>${sessionScope.user_session.user_nickname }</p>
    </div>
    <ul>
      <li class="person_nav_li" onclick="get_person()"><i class="fa fa-user"></i><span class="span_nav">个人信息</span></li>
      <li class="person_nav_li" onclick="get_my_course()"><i class="fa fa-book"></i><span class="span_nav">我的课程</span></li>
      <li class="person_nav_li" onclick="get_my_order()"><i class="fa fa-list-alt"></i><span class="span_nav">我的订单</span></li>
      <li class="person_nav_li" onclick="get_mycourseware()"><i class="fa fa-folder-open-o"></i><span class="span_nav">我的课件</span></li>
    <!--   <li class="person_nav_li"><i class="fa fa-tasks"></i><span class="span_nav">我的题库<i class="fa fa-caret-square-o-down"></i></span></li>
      <div class="person_nav_child hide">
        <ul>
          <li class="nav_child_li">我的题库1</li>
          <li class="nav_child_li">我的题库2</li>
        </ul>
      </div> -->
      <li class="person_nav_li"><i class="fa fa-credit-card"></i><span class="span_nav">报考信息<i class="fa fa-caret-square-o-down"></i></span></li>
      <div class="person_nav_child hide">
        <ul>
          <li class="nav_child_li">报名信息</li>
          <li class="nav_child_li">成绩查询</li>
        </ul>
      </div>
      <li class="person_nav_li" onclick="send_message()"><i class="fa fa-commenting-o"></i><span class="span_nav">请求与留言</span></li>
      <li class="person_nav_li" onclick="get_inbox()"><i class="fa fa-envelope-o"></i><span class="span_nav inbox">收件箱<p id="weidu_number">0</p></span></li>
      <li class="person_nav_li on"><i class="fa fa-shirtsinbulk"></i><span class="span_nav">密码安全</span></li>
    </ul>
  </div>
  <div class="person_content left" id="person_menu_list">
  <!-- 我的课程 -->
  </div>
  <div class="clear"></div>
  </div>
</div>
<div id="bg"></div>
<div class="box" id="phone" style="display:none">
    <h2>绑定手机号<a href="#" class="close"><i class="fa fa-close"></i></a></h2>
    <div class="binding" id="bangding_shouji">
        <div class="error" style="color: red;"></div>
        <ul>
            <li>
              <label>手机号：</label>
              <input type="text" onblur="check_user_phone()" id="user_phone" placeholder="请输入手机号"  />
            </li>
            <div class="clear"></div>
            <li>
              <label>验证码：</label>
              <input type="text" id="phone_code" onblur="check_phone_code()" placeholder="请输入验证码" class="phone_yzm" />
              <a href="javascript:void(0)" onclick="send_phone_code(this)" class="phone_yzm_btn">点击获取</a>
            </li>
            <div class="clear"></div>
            <li>
              <label>&nbsp;</label>
              <input type="button" onclick="update_phone(${user.user_id})" value="提交" />
            </li>
            <div class="clear"></div>
        </ul>
    </div>
</div>
<!-- 修改绑定邮箱 -->
<div class="box" id="update_bangding_youxiang" style="display:none">
    <h2>绑定邮箱<a href="#" class="close"><i class="fa fa-close"></i></a></h2>
    <div class="binding">
        <div class="error"></div>
        <ul>
            <li>
              <label>邮箱：</label>
              <input type="text" id="up_user_mail" onblur="up_check_mail()" placeholder="请输入电子邮箱"  />
            </li>
            <div class="clear"></div>
            <li>
              <label>验证码：</label>
              <input type="text" onblur="check_code()" placeholder="请输入验证码" id="mail_code" class="phone_yzm" />
              <a href="javascript:void(0)" onclick="send_code(this)" class="phone_yzm_btn">点击获取</a>
            </li>
            <div class="clear"></div>
            <li>
              <label>&nbsp;</label>
              <input type="button" onclick="update_mail(${user.user_id})" value="提交" />
            </li>
            <div class="clear"></div>
        </ul>
    </div>
</div>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/footer.jsp"></jsp:include>
</body>
</html>