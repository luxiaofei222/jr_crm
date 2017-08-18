<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/course_sign/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="/css/course_sign/registration-common.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>选择报名条件</title>
</head>
<script>
//返回第一步  
function get_first(){
	location.href="/entry_plan/get_entry_plan.html"
}
//获取等级条件列表
function get_sub_condition(entrycondition_id,course_id,obj){
	 $(obj).nextAll(".level").slideToggle();
	  var val= $(obj).children("i").attr("class").replace(/\s+/g, "");
	  if(val=="fafa-plus-square"){
		  $(obj).children("i").removeClass("fa-plus-square");
		  $(obj).children("i").addClass("fa-minus-square");  
		  $.post("/condition/get_sub_dition.html",{
				'entrycondition_id':entrycondition_id,
				'course_id':course_id
			},function(data){
				$("#dengji"+course_id).html(data);
			})
	  }else{
		  $(obj).children("i").removeClass("fa-minus-square");
		  $(obj).children("i").addClass("fa-plus-square");
	  }
	
}
//第三步
function get_third(entry_infoid){
	var dition=$("input[type='radio']:checked").val();
	if (typeof(dition) == "undefined"){ 
		layer.msg("请选择您报名的条件！") 
		}else{
			$.get("/condition/check_entry_dition.html",{
				'entrycondition_id':dition
			},function(data){
				if(data==1){
					location.href="/entry_info/get_entry_third.html?entrycondition_id="+dition+"&entryInfoId="+entry_infoid;
				}else{
					layer.msg("该条件，您已经报名过，请去我的报名系统查看！") 
				}
			})
		}
}
</script>
<body>
	<div class="registration_title">
		<div class="reg_top">
			<a href="/index.jsp"><img src="/images/course_sign/logo.png" /></a><span>京人教育网上报名系统</span>
		</div>
	</div>
	<div class="reg_nav">
		<ul class="ul_nav">
			<li><span></span>
				<div class="str">
					<h3>01</h3>
					<p>选择计划</p>
				</div></li>
			<li class="step_now"><span></span>
				<div class="str">
					<h3>02</h3>
					<p>选择报名条件</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>03</h3>
					<p>选择报名点</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>04</h3>
					<p>填写报名信息</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>05</h3>
					<p>确认报考信息</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>06</h3>
					<p>报名费缴付</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>07</h3>
					<p>报名成功</p>
				</div></li>
		</ul>
	</div>
	<!--  reg_nav   end   fa-plus-square  fa-minus-square-->
	<div class="reg_content">
		<div class="content_info2">
			<c:forEach items="${conditions }" var="cond">
				<div class="professional">
					<span class="professional_title"
						onclick="get_sub_condition(${cond.entryplan_id},${cond.course_id},this)"><i
						class="fa fa-plus-square"></i>
						${cond.courseMenu.course_name }</span>
					<div class="level" id="dengji${cond.course_id}">
						<!-- 等级条件列表 -->

                          <div id="preloader6">
					        <span></span>
					        <span></span>
					        <span></span>
					        <span></span>
				          </div>    

					</div>
				</div>
			</c:forEach>

		</div>
		<div class="operations">
			<input type="button" onclick="get_first()" value="上一步" /><input
				type="button" onclick="get_third(${entry_infoid})" value="下一步" />
		</div>
	</div>
</body>
</html>