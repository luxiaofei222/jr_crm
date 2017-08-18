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

<title>选择报名点</title>
</head>
<script>
//返回第二步
function get_second(entryplanId){
	 location.href="/entry_info/get_entry_second.html?entryplanId="+entryplanId;
}
//获取城市地点
function get_entry_city(){
	var entry_province_id=$("#entry_province_id").val();
	$.post("/entry_place/get_sub_entryplace.html",{
		'plcae_id':entry_province_id
	},function(data){
		$("#entry_province_id").html(data);
	})
}
//获取学校地点
function get_entry_school(){
	var entry_city_id=$("#entry_city_id").val();
	$.post("/entry_place/get_sub_entryplace.html",{
		'plcae_id':entry_city_id
	},function(data){
		$("#entry_school_id").html(data);
	})
}
//第四步
function get_fourth(entryInfoId){
	var entry_province_id=$("#entry_province_id").val();
	var entry_city_id=$("#entry_city_id").val();
	var entry_school_id=$("#entry_school_id").val();
	$.post("/entry_info/save_entry_place.html",{
		'entry_city_id':entry_city_id,
		'entry_province_id':entry_province_id,
		'entry_school_id':entry_school_id,
		'entryInfoId':entryInfoId
	},function(data){
		if(data==1){
			location.href="/entry_info/get_entry_fourth.html?entryInfoId="+entryInfoId;
		}else if(data==3){
			location.href="/entry_plan/get_entry_plan.html"
		}else {
			layer.msg("系统发生错误！")
		}
	})
	
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
			<li><span></span>
				<div class="str">
					<h3>02</h3>
					<p>选择报名条件</p>
				</div></li>
			<li class="step_now"><span></span>
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
  <div class="content_info3">
    <div class="address_item">
      <label>省：</label>
      <select onchange="get_entry_city()" id="entry_province_id" name="entry_province_id">
      <c:forEach items="${entryPlaces }" var="place">
        <option value="${place.entryplace_id }">${place.entryplace_name }</option>
       </c:forEach>
      </select>
    </div>
    <div class="clear"></div>
    <div class="address_item">
      <label>市：</label>
      <select onchange="get_entry_school()" id="entry_city_id" name="entry_city_id">
          <c:forEach items="${entryPlaces_sub }" var="place">
        <option value="${place.entryplace_id }">${place.entryplace_name }</option>
       </c:forEach>
      </select>
    </div>
    <div class="clear"></div>
    <div class="address_item">
      <label>学校：</label>
      <select  id="entry_school_id" name="entry_school_id">
          <c:forEach items="${entryPlaces_sub_sub }" var="place">
        <option value="${place.entryplace_id }">${place.entryplace_name }</option>
       </c:forEach>
      </select>
    </div>
    <div class="clear"></div>
  </div>
  <div class="operations">
    <input type="button" onclick="get_second(${entryInfo.entryplanId})" value="上一步" /><input type="button" onclick="get_fourth(${entryInfo.entryInfoId})" value="下一步" />
  </div> 
</div>
</body>
</html>