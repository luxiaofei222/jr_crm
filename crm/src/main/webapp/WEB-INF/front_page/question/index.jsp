<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/question/index/reset.css">
    <link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="/css/question/index/index.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<script src="/js/school/front/index/jquery-v1.10.2.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>京人题库首页</title>
<script>
function to_select_question(course_id,question_number){
	if(question_number>0){
		location.href="/questionindex/get_selest_question_index.html?course_id="+course_id;
	}else{
		tanchuang("该课程暂时没有题目作答，请耐心等候！");
	}
}
</script>
</head>
<body>
	<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
	<!-- 内容开始 -->
	<div class="tk_banner">
		<img src="/images/question/index/banner.png">
	</div>
	<div class="tk_wrapper">
		<ul class="tk_ul">
		<c:forEach items="${courseMenus}" var="course" varStatus="vs">
			<li class="tk_li">
				<div class="tk_bt color${vs.index+1 }">
					<i class="${course.course_icon }"></i>${course.course_name }
				</div>
				<hr style="height: 1px; border: none; border-top: 1px solid #ccc;">
				<ul class="tk_neiul hover${vs.index+1 }">
					<c:forEach items="${course.sub_list }" var="sub">
					<li class="tk_neili">
						<div class='neili_title color${vs.index+1 }'>${sub.course_name }</div>
						<div class='neili_tu' style="background-image: url('${sub.question_index_pic}');">
							<div class="zhangjie">
								<img src="/images/question/index/book.png"> <span
									class="tishu1"> 章节题数： <span class="tishu">${sub.chapter_option_number}</span>
									道
								</span>
							</div>
							<div class="shijuan">
								<img src="/images/question/index/book.png"> <span
									class="tishu1"> 试卷数量： <span class="tishu"> ${sub.shijuan_number} </span> 套
								</span>
							</div>
						</div>
						<div class="neili_jinru" onclick="to_select_question(${sub.course_id},${sub.question_number })">进入做题</div>
					</li>
					</c:forEach>
				</ul>
			</li>
			</c:forEach>
		</ul>
	</div>
	<!-- 内容结束 -->
	<!--登录注册窗口  -->
	<jsp:include
		page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/videofoot.jsp"></jsp:include>
</body>
</html>