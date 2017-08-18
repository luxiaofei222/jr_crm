<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
.tab-RconBox .ptfw-box td {
	background: none !important;
	border: 1px solid #ffedd2;
}

ul.page_number {
	overflow: hidden;
	display: table;
	margin-top: 20px;
	margin-left: 384px;
}

ul.page_number li {
	float: left;
	color: #06c1ae;
	width: 25px;
	height: 25px;
	text-align: center;
	line-height: 25px;
}

ul.page_number li:hover {
	background-color: #06c1ae;
	color: white;
	cursor: pointer;
}

ul.page_number li.noborder {
	border: none;
}

.buy_liji {
	border: solid 1px #ff0000 !important;
	color: #ff0000 !important;
}

.buy_liji:hover {
	background-color: #ff0000 !important;
	color: white !important;
}

.tg_wrapper .sixbg {
	height: 270px;
	line-height: 45px;
}

.grade_class {
	overflow: hidden;
}

.grade_class li {
	float: left;
	width: 210px;
	height: 45px;
	text-align: center;
	font-size: 16px;
	background-color: #ffedd2;
}
</style>
<c:if test="${empty clearances}">
	<span
		style="display: block; margin: 0 auto; text-align: center; font-size: 25px; color: #06C1AE; margin-top: 200px;">暂无通关方案</span>
</c:if>
<c:if test="${not empty clearances}">
<c:if test="${not empty dengji }">
	<h6 class="tab-title clearfix" >
		<ul class="clearfix">
		<c:forEach items="${clearances_group }" var="group" varStatus="vs">
		<c:if test="${vs.index==0 }">
			<li><a class="hover" onclick="get_clearance_table(this,${course_id },'${group.dengji }')" href="javascript:void(0);"
				style="width: 146px;">${group.dengji }</a></li>
		</c:if>
		<c:if test="${vs.index!=0 }">
			<li><a onclick="get_clearance_table(this,${course_id },'${group.dengji }')" href="javascript:void(0);"
				style="width: 146px;">${group.dengji }</a></li>
		</c:if>
		</c:forEach>
		</ul>
	</h6>
</c:if>
	<div id="clearance_table">
	<ul class="tg_wrapper">
		<li class="oneul">
			<ul>
				<li class="oneg">套餐
					</li>
				<li class="twog">原价<br /></li>
				<li class="twog">现价<br /></li>
				<li class="sixbg" style="line-height: 270px;">包含<br /></li>
				<li class="fourg" style="line-height: 58px;"><br /> 特色</li>
				<li class="fiveg">保障</li>
				<li class="fiveg">截止日期</li>
				<li class="sixg"></li>
			</ul>
		</li>
		<c:forEach items="${clearances.list }" var="clearance">
			<li class="twoul">
				<ul>
					<li class="oneg" style="font-size: 22px;">${clearance.clearance_name }</li>
					<li class="twog yuanjia">
						<p>
							<font>￥${clearance.clearance_price }</font>
						</p>
					</li>
					<li class="twog">
						<p>
							<font style="color: #FF0000">￥${clearance.new_price }</font>
						</p>
					</li>
					<li class="sixbg"
						style="display: table-cell; vertical-align: middle; text-align: center; font-size: 15px;">
						<c:if test="${empty clearance.clearanceVideos }">
							<div style='line-height: 270px; text-align: center;'>暂无课程</div>
						</c:if>
						<div style="display: inline-block;">
							<c:forEach items="${clearance.clearanceVideos }" var="video">
			${video.video_name }<span style="color: #FF0000">(￥${video.video_price })</span>
								<br />
							</c:forEach>
						</div>
					</li>
					<li class="fourg"><c:forEach items="${ clearance.tese}"
							var="tese">
							<p>${tese }</p>
						</c:forEach></li>
					<li class="fiveg">${clearance.clearrance_baozhang }</li>
					<li class="fiveg"><fmt:formatDate
							value="${clearance.daoqi_time }" /></li>
					<li class="a-btn sixg"><a
						href="http://tb.53kf.com/code/client/10147174/1]">在线咨询</a> <c:if
							test="${empty sessionScope.user_session}">
							<a href="javascript:void(0);" onclick="get_login()"
								class="buy_liji">立即购买</a>
						</c:if> <c:if test="${not empty sessionScope.user_session}">
							<a href="javascript:void(0);"
								onclick="pay_clearance_video(${clearance.clearance_id})"
								class="buy_liji">立即购买</a>
						</c:if></li>
				</ul>
			</li>
		</c:forEach>
	</ul>
	<ul class="page_number">
		<c:if test="${clearances.hasPreviousPage==true}">
			<li class="noborder"
				onclick="claerance_jump_page(${clearances.pageNumber-1},${course_id },'${dengji }')"><span class='fa fa-arrow-left'></span></li>
		</c:if>
		<li style="background-color: #06c1ae; color: white;">${clearances.pageNumber }</li>
		<c:if test="${clearances.hasNextPage==true}">
			<li class="noborder"
				onclick="claerance_jump_page(${clearances.pageNumber+1},${course_id },'${dengji }')"><span class='fa fa-arrow-right'></span></li>
		</c:if>
	</ul>
	</div>
</c:if>
<script>
function claerance_jump_page(page,course_id,dengji){
	$.post("/front_info/get_clearance_table.html",{
		'course_id':course_id,
		'dengji':dengji,
		'pageNumber' : page
	},function(data){
		$("#clearance_table").html(data);
	})
}
//点击获取通关方案
function get_clearance_table(obj,course_id,dengji){
	$(".hover").removeClass("hover");
	$(obj).addClass("hover");
	$.post("/front_info/get_clearance_table.html",{
		'course_id':course_id,
		'dengji':dengji
	},function(data){
		$("#clearance_table").html(data);
	})
}

//立即购买
function pay_clearance_video(clearance_id){
	location.href="/sc_pay/get_pay_clearance_main.html?clearance_id="+clearance_id
	//layer.msg("暂时不支持线上购买！")
}
</script>