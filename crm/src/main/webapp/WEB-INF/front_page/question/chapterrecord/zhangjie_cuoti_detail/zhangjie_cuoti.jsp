<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/question/index/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/question/select_question/question-common.css" />
<link href="/css/school/back/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/iconfont/iconfont.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/question/question-common.js"></script>
<script type="text/javascript" src="/js/question/front/frontchapter.js"></script>
<title>京人网校题库-练习记录</title>
<script>
//错题加载动画
function cuotijiazai(){
	$("#cuoti_shoucang_list").html("<div id='preloader_4'><span></span><span></span><span></span><span></span><span></span></div>");
}
//加载分页
function zhangjie_cuoti_jump_page(page,question_course_id,zhang_id,jie_id,question_id){
	cuotijiazai();
	$.post("/front_chapter/get_zhangjie_cuoti.html",{
		'type':1,
		'question_course_id':question_course_id,
		'pageNumber':page,
		'limit':10,
		'zhang_id':zhang_id,
		'jie_id':jie_id,
		'question_id':question_id
	},function(data){
		$("#cuoti_shoucang_list").html(data);
	})
}
//跳转输入的页面
function to_zhangjie_cuoti_jump_page(page,question_course_id,zhang_id,jie_id,question_id){
	var page_number=$("#page_number").val();
	if(page_number!=null&&page_number!=""){
		if(isNaN(page_number)){
			layer.msg("请输入数字！")
		}else{
			if(page_number<=page&&page_number!=0){
				cuotijiazai();
				$.post("/front_chapter/get_zhangjie_cuoti.html",{
					'type':1,
					'question_course_id':question_course_id,
					'pageNumber':page_number,
					'limit':10,
					'zhang_id':zhang_id,
					'jie_id':jie_id,
					'question_id':question_id
				},function(data){
					$("#cuoti_shoucang_list").html(data);
				})
			}else{
				layer.msg("您输入的页码 不存在！")
			}
		}
	}else{
		layer.msg("请输入您要跳转的页数！")
	}
}
</script>
</head>
<body>
	<div class="question_title">
		<div class="que_top">
			<a href="/index.jsp"><img
				src="/images/school/front/index/logo.png" /></a>
			<div class="question_tab">
				<h1>${courseMenu.course_name }</h1>
				<h2>${questionCourse.question_course_name }</h2>
				<div class="question_others">
					<a href="javascript:void(0)">[切换]</a>
					<div class="qiehuan">
						<ul>
							<i class="fa fa-caret-up"></i>
							<div class="top_qiehuan">
								<h3>${courseMenu.course_name }</h3>
								<a href="/questionindex/get_question_index.html">[切换考试]</a>
							</div>
							<c:forEach items="${questionCourses }" var="question">
								<li><a
									href="/front_chapter/get_chapter_list.html?question_course_id=${question.question_course_id }">${question.question_course_name }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="que_nav">
		<ul>
			<li onclick="get_question_index(${questionCourse.course_id})"><i
				class="icon iconfont icon-fangzi"></i>题库首页</li>
			<li onclick="get_chapter_list(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-shu"></i>章节练习</li>
			<li onclick="get_zhenti_year(${questionCourse.question_course_id})"><i class="icon iconfont icon-shitiguanli"></i>历年真题</li>
    <li onclick="get_moni_kaoshi(${questionCourse.question_course_id})"><i class="icon iconfont icon-kaoshi"></i>模拟考试</li>
    <li class="on" onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i class="icon iconfont icon-zhihebi"></i>练习记录</li>
    <li onclick="get_nengli_main(${questionCourse.question_course_id})"><i class="icon iconfont icon-baogaopinggu"></i>能力评估报告</li>
		</ul>
	</div>
	<div class="que_content" style="border: none;">
		<!-- 错题记录详情 -->
		<div class='records_zong'>
			<ul class="records_left">
				<li onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i
					class="icon iconfont icon-shunxulianxi-copy"></i><span>练习记录</span>
				</li>
				<li onclick="get_cuoti_record(${questionCourse.question_course_id})"  class="records_active"><i class="icon iconfont icon-cuowu"></i><span>错题记录</span></li>
				<li style="border: none;" onclick="get_user_collection(${questionCourse.question_course_id})"><i
					class="icon iconfont icon-shoucang" style="margin-top: -2px;"></i><span>我的收藏</span>
				</li>
			</ul>
			<!-- 练习记录开始 -->
			<div id="cuoti_shoucang_list">
			<div class="records_right">
			<c:if test="${empty chapterRecords.list }">
			<div style="height:250px;">
				<img src="/images/question/chapter/noinfo.png" style="margin:0 auto;">
			</div>
			</c:if>
			<c:forEach items="${chapterRecords.list }" var="records" varStatus="vs">
				<div class="collect_wai">
					<div class="collect_nei">
						<div class="collect_title">
							<span class="num">${vs.index+1+chapterRecords.begin }</span> <span class='line'></span> <em
								class='type'>[${records.question_type }]</em>
							<p>${records.chapterQuestion.question_name }</p>
						</div>
						<c:if test="${records.chapterQuestion.question_type=='单选题' }">
						<ul class="single_choices">
							<c:forEach items="${records.chapterQuestion.chapterQuerstionOptions }" var="option">
							<c:if test="${records.chapterQuestion.answer==option.option_number }">
							<li><span class="l collect_select">${option.option_number }</span>
								<div class='r'>${option.option_content }</div></li>
							</c:if>
							<c:if test="${records.chapterQuestion.answer!=option.option_number }">
							<li><span class="l">${option.option_number }</span>
								<div class='r'>${option.option_content }</div></li>
							</c:if>
							</c:forEach>
						</ul>
						</c:if>
						<c:if test="${records.chapterQuestion.question_type=='多选题' }">
							<ul class="multi_choices">
							<c:forEach items="${records.chapterQuestion.chapterQuerstionOptions }" var="option">
							<c:if test="${option.duoxuan_type_jiexi==1 }">
							<li><span class="l collect_select">${option.option_number }</span>
								<div class='r'>${option.option_content }</div></li>
							</c:if>
							<c:if test="${option.duoxuan_type_jiexi==0 }">
							<li><span class="l">${option.option_number }</span>
								<div class='r'>${option.option_content }</div></li>
							</c:if>
							</c:forEach>
						</ul>
						</c:if>
						<div class="jiucuo">
							<div class="jiucuo_answer">
								<span class="fold-toggle" onclick="chakan_jiexi(this)" >查看解析<i
									class="fa fa-angle-double-down"></i>
								</span><c:if test="${records.is_collection==1 }">
								<span class="collect collect_active" onclick="quxiao_question_collection(this,${records.chapter_recourd_id})"> <i
									class="icon-star2 collect_active"></i><em>取消收藏</em>
								</span>
								</c:if>
								<c:if test="${records.is_collection==0}">
								<span class="collect collect_active" onclick="collection_question(this,${records.chapter_recourd_id})"> <i
									class="icon-star2"></i><em>收藏本题</em>
								</span>
								</c:if> <span class="btn-report-error"> <i class="icon-star3"></i>
									<em>本题纠错</em>
								</span>
								<div class="answer_inline">
									<span class="as-title">参考答案：</span>
									<div class="as-cont hl-green">${records.chapterQuestion.answer }</div>
								</div>
								<div class="answer_inline">
									<span class="as-title">我的答案：</span>
									<c:if test="${records.user_answer=='无' }">
									<div class="as-cont hl-red">未作答</div>
									</c:if>
									<c:if test="${records.user_answer!='无' }">
									<div class="as-cont hl-red">${records.user_answer }</div>
									</c:if>
								</div>
							</div>
							<div class='fold-bd'>
								<ul class="list-analyze">
									<li><span class='l'>试题难度：</span>
										<div class="r">
											<i class="icon-pen pen-${records.chapterQuestion.difficulty }"> <span></span>
											</i>
										</div></li>
									<li><span class="l">
											统&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计： </span>
										<div class='r'>
											该题您答过 <b class="hl-orange">${records.user_answer_number }</b>次，正确率为 <b class="hl-green"><fmt:formatNumber type="number" value="${records.user_answer_right_number/records.user_answer_number*100 }" pattern="0.00" maxFractionDigits="2"/>%</b>
										</div></li>
									<li><span class="l"> 参考解析： </span>
										<div class="r">
											${records.chapterQuestion.analysis }
										</div></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				</c:forEach>
					<!--练习记录-章节练习的分页   -->
					<div class="pagination">
						<c:if test="${chapterRecords.hasPreviousPage==true}">
							<a
								onclick="zhangjie_cuoti_jump_page(${chapterRecords.pageNumber-1},${questionCourse.question_course_id },${zhang_id },${jie_id },${question_id })">上一页</a>
						</c:if>
						<c:forEach items="${chapterRecords.navigatePageNumbers }"
							var="page">
							<c:choose>
								<c:when test="${chapterRecords.pageNumber==page}">
									<a class="active">${page}</a>
								</c:when>
								<c:otherwise>
									<a
										onclick="zhangjie_cuoti_jump_page(${page},${questionCourse.question_course_id },${zhang_id },${jie_id },${question_id })">${page}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${chapterRecords.hasNextPage==true}">
							<a onclick="zhangjie_cuoti_jump_page(${chapterRecords.pageNumber+1},${questionCourse.question_course_id },${zhang_id },${jie_id },${question_id })">下一页</a>
						</c:if>
						<span class="page-num">${chapterRecords.pageNumber}/${chapterRecords.pages }页</span>
						<span class="input"> <input type="text" id="page_number">
						</span> <a onclick="to_zhangjie_cuoti_jump_page(${chapterRecords.pages },${questionCourse.question_course_id },${zhang_id },${jie_id },${question_id })">跳转</a>
					</div>
			</div> 
			</div>
		</div>
	</div>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/videofoot.jsp"></jsp:include>
</body>
</html>