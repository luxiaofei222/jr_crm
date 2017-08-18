<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/course_sign/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/course_sign/registration-common.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<title>选择计划</title>
</head>
<script>
//进入第二步
function to_second_entry(entryplan_id){
	location.href="/entry_info/get_entry_second.html?entryplanId="+entryplan_id;
}
</script>
<script type="text/javascript">
  $(function(){
	  $(".content_info1 .exam_kind").click(function(){
		  $(this).nextAll(".exam_kinds").slideToggle(500);
		  var val= $(this).children("i").attr("class").replace(/\s+/g, "");
		  if(val=="fafa-plus-square"){
			  $(this).children("i").removeClass("fa-plus-square");
			  $(this).children("i").addClass("fa-minus-square");  
		  }else{
			  $(this).children("i").removeClass("fa-minus-square");
			  $(this).children("i").addClass("fa-plus-square");
		  }
		  })
	  })
</script>
<body>
<div class="registration_title">
  <div class="reg_top">
   <a href="/index.jsp"><img src="/images/course_sign/logo.png" /></a><span>京人教育网上报名系统</span>
  </div> 
</div>
<div class="reg_nav">
  <ul class="ul_nav">
    <li class="step_now">
      <span></span>
      <div class="str">
        <h3>01</h3>
        <p>选择计划</p>
      </div>
    </li>
    <li>
      <span></span>
      <div class="str">
        <h3>02</h3>
        <p>选择报名条件</p>
      </div>
    </li>
    <li>
      <span></span>
      <div class="str">
        <h3>03</h3>
        <p>选择报名点</p>
      </div>
    </li>
    <li>
      <span></span>
      <div class="str">
        <h3>04</h3>
        <p>填写报名信息</p>
      </div>
    </li>
    <li>
      <span></span>
      <div class="str">
        <h3>05</h3>
        <p>确认报考信息</p>
      </div>
    </li>
    <li>
      <span></span>
      <div class="str">
        <h3>06</h3>
        <p>报名费缴付</p>
      </div>
    </li>
    <li>
      <span></span>
      <div class="str">
        <h3>07</h3>
        <p>报名成功</p>
      </div>
    </li>
  </ul>
</div><!--  reg_nav   end   -->
<div class="reg_content">
  <div class="zanwu">
    <c:if test="${empty entryPlans }">
      <span>暂无报名计划</span>
    </c:if>
    <c:if test="${not empty error }">
      <span>${error }</span>
    </c:if>
  </div>
  <c:forEach items="${entryPlans }" var="course">
  <div class="content_info1">
    <span class="exam_kind"><i class="fa fa-plus-square"></i>${course.coursename }</span>
    <c:forEach items="${course.entryPlans }" var="plan" varStatus="vs">
    <div class="exam_kinds">
      <div class="test_name">
        <span class="title_left left"> ${vs.index+1 }.${plan.entryplan_content }</span>
        <a href="javascript:void(0)" onclick="to_second_entry(${plan.entryplan_id })" class="right"><i class="fa fa-arrow-circle-right"></i>进入报名</a>
        <span class="title_right right">有效期至：<fmt:formatDate pattern="yyyy-MM-dd" value="${plan.validity_time }" /></span>  
      </div> 
      <div class="test_name_info">
        <span>【相关说明：${plan.entryplan_explain }】</span>
      </div> 
    </div>
    </c:forEach>
  </div>
  </c:forEach>
 <%--  <c:forEach items="${entryPlans }" var="plan" varStatus="vs">
  <div class="content_info1">
    <div class="test_name">
      <span class="title_left left">${vs.index+1 }.${plan.entryplan_content }</span>
      <a href="javascript:void(0)" onclick="to_second_entry(${plan.entryplan_id })" class="right"><i class="fa fa-arrow-circle-right"></i>进入报名</a>
      <span class="title_right right">有效期至：<fmt:formatDate pattern="yyyy-MM-dd" value="${plan.validity_time }" /></span>  
    </div>
    <c:if test="${not empty plan.entryplan_explain }">
    <div class="test_name_info">
      <span>【相关说明：${plan.entryplan_explain }】</span>
    </div>
    </c:if>
  </div> 
  </c:forEach> --%>
</div>
</body>
</html>