<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心首页</title>
<link rel="shortcut icon" href="/images/school/front/index/logo2.png"
	type="image/x-icon" />
<link href="/css/school/front/person/css.css" rel="stylesheet" type="text/css" />
<link href="/css/school/front/person/person_left.css" rel="stylesheet" type="text/css" />
<link href="/css/school/front/person/person.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/css/school/front/person/flatpickr.min.css">
<link href="/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">
 <link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
 <script type="text/javascript" src="/css/school/back/layui/layui.js"></script> 
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/common/flatpickr.min.js"></script>
<script type="text/javascript" src="/js/school/front/person/person.js"></script>
</head>
<body>
	<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
	<div class="person_top_img"><img src="/images/school/front/person/person_img1.jpg" /></div>
<div class="content">
  <div class="contents">
    <div class="person_nav left">
    <div class="person_head" onclick="change_user_pic(${user.user_id })">
      <img src="${user.user_pic }" />
      <p>${user.user_nickname }</p>
    </div>
    <ul>
      <li class="person_nav_li on" onclick="get_person()"><i class="fa fa-user"></i><span class="span_nav">个人信息</span></li>
      <li class="person_nav_li" id="penson_my_course" onclick="get_my_course()"><i class="fa fa-book"></i><span class="span_nav">我的课程</span></li>
      <li class="person_nav_li" onclick="get_my_order()"><i class="fa fa-list-alt"></i><span class="span_nav">我的订单</span></li>
      <li class="person_nav_li" onclick="get_mycourseware()"><i class="fa fa-folder-open-o"></i><span class="span_nav">我的课件</span></li>
     <!--  <li class="person_nav_li"><i class="fa fa-tasks"></i><span class="span_nav">我的题库<i class="fa fa-caret-square-o-down"></i></span></li> -->
     <!--  <div class="person_nav_child hide">
        <ul>
          <li class="nav_child_li">我的题库1</li>
          <li class="nav_child_li">我的题库2</li>
        </ul>
      </div> -->
      <li class="person_nav_li"><i class="fa fa-credit-card"></i><span class="span_nav">报考信息<i class="fa fa-caret-square-o-down"></i></span></li>
      <div class="person_nav_child hide">
        <ul>
          <li class="nav_child_li" onclick="get_entry_info()">报名信息</li>
          <li class="nav_child_li" onclick="get_user_grade()" >成绩查询</li>
        </ul>
      </div>
      <li class="person_nav_li" onclick="send_message()"><i class="fa fa-commenting-o"></i><span class="span_nav">请求与留言</span></li>
      <li class="person_nav_li" onclick="get_inbox()"><i class="fa fa-envelope-o"></i><span class="span_nav inbox">收件箱<p id="weidu_number">0</p></span></li>
      <li class="person_nav_li" onclick="get_safe()"><i class="fa fa-shirtsinbulk"></i><span class="span_nav">密码安全</span></li>
    </ul>
  </div>
  <div class="person_content left" id="person_menu_list">
  <!--加载动画  -->
  <!-- 加载动画 -->
    <div class="person_content_title">
      <h1 class="left">个人信息</h1> 
      <a class="right" onclick="person_update(${user.user_id })" href="javascript:void(0)">修改信息</a> 
      <div class="clear"></div>
    </div>
      <div class="person_content_text">
        <ul class="information">
          <li>
            <span>昵称：</span>
            <div class="infos">${user.user_nickname }</div>
          </li>
          <div class="clear"></div>
          <li>
            <span>性别：</span>
            <div class="infos">${user.user_sex }</div>
          </li>
          <div class="clear"></div>
          <li>
            <span>手机号：</span>
            <c:if test="${not empty user.user_phone }">
            <div class="infos">${user.user_phone }</div>
            <a href="javascript:void(0)" onclick="bangding_phone()" class="bangding">修改绑定手机</a>
             </c:if>
             <c:if test="${ empty user.user_phone }">
            <div class="infos" style="color: red;">您还没有绑定手机号</div>
            <a href="javascript:void(0)"  onclick="bangding_phone()" class="bangding">添加绑定手机</a>
             </c:if>
          </li>
          <div class="clear"></div>
          <li>
            <span>QQ号：</span>
            <div class="infos">${user.user_qq }</div>
          </li>
          <div class="clear"></div>
          <li>
            <span>邮箱：</span>
            <c:if test="${not empty user.user_mail }">
            <div class="infos">${user.user_mail }</div>
            <a href="javascript:void(0)" onclick="update_bangding_youxiang()" class="bangding">修改绑定邮箱</a>
            </c:if>
             <c:if test="${empty user.user_mail }">
            <div class="infos">您还没有绑定电子邮箱</div>
            <a href="javascript:void(0)" onclick="update_bangding_youxiang()" class="bangding">添加绑定邮箱</a>
            </c:if>
          </li>
          <div class="clear"></div>
          <li>
            <span>出生年月：</span>
            <div class="infos"><fmt:formatDate  value="${user.user_birthday }" /></div>
          </li>
          <div class="clear"></div>
          <li>
            <span>所在地：</span>
            <div class="infos">${user.user_province }&nbsp;${user.user_city }</div>
          </li>
          <div class="clear"></div>
          <li>
            <span>职业：</span>
            <div class="infos">${user.user_zhiye }</div>
          </li>
          <div class="clear"></div>
          <li>
            <span>公司名称：</span>
            <div class="infos">${user.user_bus }</div>
          </li>
          <div class="clear"></div>
        </ul>
      </div>
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