<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<style>
body{
 background-color: #f5f5f5;
}
</style>
<div style="padding: 20px;">
	<c:if test="${empty videoRecords }">
		<span class="record_zanwu">暂无该用户的学习记录！</span>
	</c:if>
	<c:if test="${not empty videoRecords }">
		<!--学习记录  -->
		<ul class="record_wrapper">
			<c:forEach items="${videoRecords }" var="video" varStatus="vs" >
			<li class="study_record">
				<div class="record_caption" style="cursor: pointer;"
						onclick="study_record_open(${vs.index+1 },${video.video_parent },${video.user_id })">
					<div class="record_title_wrapper">
  						<span class="title_number">${vs.index+1 }</span>
						<h2 class="record_title">${video.courseVideo.video_name }</h2>
					</div>
					<span class="course_time">${video.courseVideo.keshi_number }课时</span> <i class="fa fa-chevron-down" id="icon${vs.index+1 }"></i>
				</div>
				<div class="line" id="line${vs.index+1 }"></div>
				<ul class="study_list" id="study_list${vs.index+1 }" style="display: none;">
					<!-- 学习记录 -->
					<li>
						<div class="list_left">
							<div class='jindu'>
								<div class="jindu_title">第二节:绪论2</div>
								<span class="jindu_al">已学：</span>
								<div class="progress">
									<div
										class="progress-bar progress-bar-info progress-bar-striped active"
										style="width: 20%;"></div>
									<div class="progress-value">20%</div>
								</div>
							</div>
							<div style="clear: both;"></div>
							<div class="study_time">学习时间：2017-7-6</div>
						</div>
					</li>
					<li>
						<div class="list_left">
							<div class='jindu'>
								<div class="jindu_title">第二节:绪论2</div>
								<span class="jindu_al">已学：</span>
								<div class="progress">
									<div
										class="progress-bar progress-bar-info progress-bar-striped active"
										style="width: 20%;"></div>
									<div class="progress-value">20%</div>
								</div>
							</div>
							<div style="clear: both;"></div>
							<div class="study_time">学习时间：2017-7-6</div>
						</div>
					</li>
				</ul>
			</li>
			</c:forEach>
		</ul>
	</c:if>
</div>
<script>
	//展开学习记录
	function study_record_open(id,video_parent,user_id) {
		if($("#icon"+id).is('.zhuan')){
			$("#study_list"+id).slideToggle(500);
			$("#line"+id).slideToggle(500);
			$("#icon"+id).toggleClass("zhuan");
		}else{
			$.post("/back_video_record/get_sub_record.jr",{
				'video_parent':video_parent,
				'user_id':user_id
			},function(data){
				$("#study_list"+id).html(data);
				$("#study_list"+id).slideToggle(500);
				$("#line"+id).slideToggle(500);
				$("#icon"+id).toggleClass("zhuan");
			})
		}
	}
</script>