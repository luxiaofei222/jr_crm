<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="">
<meta name="keywords" content="">
<link href="/css/school/front/index/reset.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/front/index/index.css">
<link type="text/css" rel="stylesheet"
	href="/css/school/front/course_info/kj.css">
<link type="text/css" rel="stylesheet"
	href="/css/school/front/course_info/sheet.css">
<link rel="stylesheet" href="/css/school/front/course_info/type.css">
<script src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/front/course_info/date.js"></script>
<script src="/js/school/front/course_info/dtgundong.js"></script>
<script src="/js/school/front/course_info/course_info.js"></script>
<script type="text/javascript" src="/js/school/front/index/index.js"></script>
<title>课程资讯</title>
</head>
<script>
	function switchTag(tag, content, k, n, stylea, styleb) {
		for (i = 1; i <= n; i++) {
			if (i == k) {
				document.getElementById(tag + i).className = stylea;
				document.getElementById(content + i).style.display = "block";
			} else {
				document.getElementById(tag + i).className = styleb;
				document.getElementById(content + i).style.display = "none";
			}
		}
	}
	
	$(function(){
		var course_id=$("#course_id_hide").val();
		$.post("/front_info/get_course_video.html",{
			'course_id':course_id
		},function(data){
			$("#video_list").html(data);
		})
	})
</script>
<body>
<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/info_header.jsp"></jsp:include>
	<div class="head1" style="margin:0 auto;">
		<div class="nav1">
			<div class="nav-mid">
				<ul>
					<li class="w1"><a href="javascript:void(0);" class="title">动态</a>
					<!--'考试动态','课程相关','考试试题','热点专题','热点推荐','报考指南'  -->
						<a href="/entry_plan/get_entry_plan.html">考试报名</a> <a
						href="/front_info/get_course_info_erji_list.html?info_type=考试动态&course_id=${courseMenu.course_id }">考试动态</a>
						 <a href="/front_info/get_course_info_erji_list.html?info_type=热点专题&course_id=${courseMenu.course_id }">热点专题</a>
						<a href="/front_info/get_course_info_erji_list.html?info_type=报考指南&course_id=${courseMenu.course_id }">报考指南</a> <a
						href="/person/get_person_index.html">证书领取</a> <a href="/person/get_person_index.html">考试大纲</a>
						<a href="http://www.jingrenedu.cn/front_news/get_xinwen_main.html">政策法规</a><a href="/person/get_person_index.html">考试教材</a>
						</li>
						<%-- <c:if test="${not empty dictionaries }">
						<li class="line"></li>
						<li class="w2"><a href="javascript:void(0);" class="title">等级</a>
							<c:forEach items="${dictionaries }" var="dic">
								<c:if test="${not empty dic.courseInfos }">
								<c:if test="${dic.course_id!=1001 }">
									<a href="javascript:void(0);">${dic.dictionary_name }</a>
								</c:if> 
								<c:if test="${dic.course_id==1001 }">
									<a href="javascript:void(0);">${dic.dictionary_name }${courseMenu.course_name }</a>
								</c:if>
								</c:if>
							</c:forEach>
							</li>
					</c:if> --%>
					<li class="line"></li>
					<li class="w3"><a href="javascript:void(0);" class="title">备考</a>
						<a href="/questionindex/get_question_index.html" class="cyellow">模拟试题</a> <a
						href="/questionindex/get_question_index.html" class="cyellow">历年真题</a> <a
						href="/front_info/get_course_info_erji_list.html?info_type=考试试题&course_id=${courseMenu.course_id }">考试试题</a>
						 <a href="/front_info/get_course_info_erji_list.html?info_type=课程相关&course_id=${courseMenu.course_id }">课程相关</a>
						<a href="/front_info/get_course_info_erji_list.html?info_type=热点推荐&course_id=${courseMenu.course_id }">热点推荐</a>
						<a href="/retrieval/get_retrieval_main.html?course_id=${courseMenu.course_id }" target="_blank" class="cyellow">网校课程</a>
						<a href="javascript:void(0);">技巧心得</a><a href="javascript:void(0);">资讯专题</a>
					</li>
					<li class="line"></li>
					<li class="w4"><a href="javascript:void(0);" class="title">综合</a>
						<a href="/questionindex/get_question_index.html">章节习题</a><a href="/questionindex/get_question_index.html">考试社区</a> <a
						href="javascript:void(0);">职场资讯</a><a href="/retrieval/get_retrieval_main.html?course_id=${courseMenu.course_id }">辅导讲义</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main">
		<div class="content ">
			<div class="fl w300">
				<div class="scrl" id="flash-xlzx" node-type="menu-slide"
					data-type="{main:'#flash-xlzx',img:'.scrl-focus-con li',left:'.scrArrAbsLeft',right:'.scrArrAbsRight',nav:'.focus-list span','cs':'active'}">
					<ul class="scrl-focus-con">
						<c:forEach items="${courseVideos }" var="video">
						<li style="display: block"><a href="/sc_coursevideo/get_course_video_player.html?video_id=${video.video_id }"
							target="_blank"><img
								src="${video.video_pic }" /></a></li>
						</c:forEach>
					</ul>
					<div class="scrl-focus-list clearfix">
						<span class="focus-list"><span></span><span class="active"></span><span></span><span></span></span>
					</div>
					<a href="javascript:void(0);" class="scrArrAbsLeft"></a> <a
						href="javascript:void(0);" class="scrArrAbsRight"></a> <br
						/ clear="all">
				</div>
				<div class="box-lc m10">
					<dl>
						<dt class="time-box">
							<p>
								距预计${year }年<b class="cRed">${month }月${day }日</b>考试还有：
							</p>
							<div class="time-nur">
							<c:if test="${empty courseMenu.exam_time }">
							<input type="hidden" id="exam" value="${tim }">
							</c:if>
							<c:if test="${not empty courseMenu.exam_time }">
							<input type="hidden" id="exam" value="${courseMenu.exam_time }">
							</c:if>
								<script>
								var exam=$("#exam").val();
									new lmj.down_day(
											{
												'stopDate' : exam,
												'replaceStr' : '<span><em>$&</em></span>'
											})
								</script>
								天
							</div>
						</dt>
						<dd>
							<span class="hh"><h3>
									报名<i></i>
								</h3> <a href="javascript:void(0);" target="_blank">2016下半年报名8月-10月</a>
							</span> <span><a href="javascript:void(0);" target="_blank">报名条件</a>
								<a href="javascript:void(0);" target="_blank">报名时间</a> <a
								href="javascript:void(0);" target="_blank">报名官网</a> <a
								href="javascript:void(0);" target="_blank">报名流程</a></span>
						</dd>
						<dd>
							<span class="hh"><h3>
									准考证<i></i>
								</h3> <a href="javascript:void(0);" target="_blank">考前一周打印准考证</a> </span> <span><a
								href="javascript:void(0);" target="_blank">准考证打印时间及入口</a> <a
								href="javascript:void(0);" target="_blank">准考证打印注意事项</a></span>
						</dd>
						<dd>
							<span class="hh"><h3>
									考试<i></i>
								</h3> <a href="javascript:void(0);" target="_blank">2016下半年11月19日考试</a>
							</span> <span><a href="javascript:void(0);" target="_blank">考试教材</a>
								<a href="javascript:void(0);" target="_blank">考试大纲</a> <a
								href="javascript:void(0);" target="_blank">考试科目</a> <a
								href="javascript:void(0);" target="_blank">考试题型</a></span>
						</dd>
						<dd>
							<span class="hh"><h3>
									查分<i></i>
								</h3> <a href="javascript:void(0);" target="_blank">考后2个月</a> </span> <span><a
								href="javascript:void(0);" target="_blank">查询时间</a> <a
								href="javascript:void(0);" target="_blank">有效期</a> <a
								href="javascript:void(0);" target="_blank">合格标准</a> <a
								href="javascript:void(0);" target="_blank">晒分交流</a></span>
						</dd>
						<dd>
							<span class="hh"><h3>
									领证<i></i>
								</h3> <a href="javascript:void(0);" target="_blank">考后半年领取</a> </span> <span><a
								href="javascript:void(0);" target="_blank">证书领取时间</a> <a
								href="javascript:void(0);" target="_blank">证书样本</a> <a
								href="javascript:void(0);" target="_blank">证书的作用</a></span>
						</dd>
					</dl>
					<br / clear="all">
				</div>
			</div>
			<div class="fl w440">
				<ul class="m-headlines">
				<c:if test="${empty exam_question_top }">
				<li style="margin:0 auto;text-align:center;line-height:270px;font-size:20px;color:orange;">暂无内容</li>
				</c:if>
				<c:forEach items="${exam_question_top }" var="info" varStatus="vs">
					<c:if test="${vs.count%2 != '0' }">
					<c:if test="${vs.index==0 }">
					<li>
						<h2>
						<a href="/front_info/get_course_info_detail.html?info_id=${ info.info_id}"title="${info.info_title }" target="_blank" class="cRed">${info.info_title }</a>
						<span class="listdate"><fmt:formatDate
										pattern="MM-dd" value="${info.info_time }" /></span>
						</h2>
					</li>
					</c:if>
					<c:if test="${vs.index!=0 }">
					<li class="mt12">
						<h2>
						<a href="/front_info/get_course_info_detail.html?info_id=${ info.info_id}"title="${info.info_title }" target="_blank" class="cRed">${info.info_title }</a>
						<span class="listdate"><fmt:formatDate
										pattern="MM-dd" value="${info.info_time }" /></span>
						</h2>
					</li>
					</c:if>
					</c:if>
					<c:if test="${vs.count%2 == '0' }">
					<li>
						<h3>
							<a href="/front_info/get_course_info_detail.html?info_id=${ info.info_id}" title="${info.info_title }" target="_blank">
								${info.info_title }
							</a>
							<span class="listdate"><fmt:formatDate
										pattern="MM-dd" value="${info.info_time }" /></span>
						</h3>
					</li>
					</c:if>
					</c:forEach>
				</ul>
				<div class="title-a mt15">
					<a href="javascript:void(0);" class="active" id="zx_1"
						onmouseover="switchTag('zx_','zx_list','1',4,'active','');">最近更新</a>
					<a href="javascript:void(0);" id="zx_2"
						onmouseover="switchTag('zx_','zx_list','2',4,'active','');">考试动态</a>
					<a href="javascript:void(0);" id="zx_3"
						onmouseover="switchTag('zx_','zx_list','3',4,'active','');">试题辅导</a>
					<a href="javascript:void(0);" id="zx_4"
						onmouseover="switchTag('zx_','zx_list','4',4,'active','');">热点专题</a>
				</div>
				<!-- 最近更新 -->
				<div id="zx_list1">
					<ul class="list-a">
						<c:if test="${empty late_update }">
							<li>暂无内容</li>
						</c:if>
						<c:forEach items="${late_update }" var="info">
							<li><a target="_blank" title="${info.info_title }"
								href="/front_info/get_course_info_detail.html?info_id=${ info.info_id}">${info.info_title }</a><span><fmt:formatDate
										pattern="MM-dd" value="${info.info_time }" /></span></li>
						</c:forEach>
					</ul>
				</div>
				<!-- 考试动态 -->
				<div id="zx_list2" class="Hidebox">
					<ul class="list-a">
						<c:if test="${empty denamic_test }">
							<li>暂无内容</li>
						</c:if>
						<c:forEach items="${denamic_test }" var="info">
							<li><a target="_blank" title="${info.info_title }"
								href="/front_info/get_course_info_detail.html?info_id=${ info.info_id}">${info.info_title }</a><span><fmt:formatDate
										pattern="MM-dd" value="${info.info_time }" /></span></li>
						</c:forEach>
					</ul>
				</div>
				<!-- 考试试题 -->
				<div id="zx_list3" class="Hidebox">
					<ul class="list-a">
						<c:if test="${empty exam_question }">
							<li>暂无内容</li>
						</c:if>
						<c:forEach items="${exam_question }" var="info">
							<li><a target="_blank" title="${info.info_title }"
								href="/front_info/get_course_info_detail.html?info_id=${ info.info_id}">${info.info_title }</a><span><fmt:formatDate
										pattern="MM-dd" value="${info.info_time }" /></span></li>
						</c:forEach>
					</ul>
				</div>
				<div id="zx_list4" class="Hidebox"><!-- 热点专题 -->
					<ul class="list-a">
					<c:if test="${empty hot_topics }">
							<li>暂无内容</li>
						</c:if>
						<c:forEach items="${hot_topics }" var="info">
							<li><a target="_blank" title="${info.info_title }"
								href="/front_info/get_course_info_detail.html?info_id=${ info.info_id}">${info.info_title }</a><span><fmt:formatDate
										pattern="MM-dd" value="${info.info_time }" /></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="fr w220">
				<div class="box-tk">
					<div class="box-tk-tit">
						<a href="javascript:void(0);" target="_blank" class="tit">题库</a><!--  <a
							href="javascript:void(0);" target="_blank">我的题库</a> -->
					</div>
					<ul class="clearfix">
						<li><span><a href="/questionindex/get_question_index.html"
								target="_blank" class="btn">开始练习</a> <a
								href="/questionindex/get_question_index.html" target="_blank"> <i></i> 模拟试题
							</a> </span>
							<p>全真模拟无纸化机考流程。</p></li>
						<li><span><a href="javascript:void(0);"
								target="_blank" class="btn">开始练习</a> <a
								href="/questionindex/get_question_index.html" target="_blank"><i></i> 历年真题</a> </span>
							<p>发布真题试卷，模拟练习。</p></li>
						<li><span><a href="javascript:void(0);"
								target="_blank" class="btn">开始练习</a> <a
								href="/questionindex/get_question_index.html" target="_blank"><i></i> 章节练习</a> </span>
							<p>逐章逐节的做题练习。</p></li>
						<li><span><a href="javascript:void(0);"
								target="_blank" class="btn">开始练习</a> <a
								href="/questionindex/get_question_index.html" target="_blank"><i></i> 每日一练</a> </span>
							<p>最新题，每天10 题随机练习。</p></li>
					</ul>
				</div>
				<h2 class="atitle mt10">
					<a href="javascript:void(0);"> <i></i>考试工具
					</a>
				</h2>
				<ul class="tool-box">
					<li><a href="javascript:void(0);" target="_blank"><em
							class="ico-zn"></em>
							<p>新手指南</p></a></li>
					<li><a href="javascript:void(0);" target="_blank"><em
							class="ico-wt"></em>
							<p>常见问题</p></a></li>
					<li><a href="javascript:void(0);" target="_blank"><em
							class="ico-dy"></em>
							<p>在线答疑</p></a></li>
				</ul>
			</div>
			<input type="hidden" id="course_id_hide" value="${courseMenu.course_id }">
			<div class="fl column mt10" id="video_list">
				<!--课程视频  -->
			</div>
			<div class="fl column mt10">
				<h2 class="hd-title">
					<em class="ico-tk"></em><em class="ico-f"></em> <a
						href="javascript:void(0);">考试动态</a> <a href="javascript:void(0);"
						target="_blank" class="mor">更多<i></i></a> <a class="h2"
						href="javascript:void(0);">考试报名</a> <a class="h2"
						href="javascript:void(0);">成绩查询</a> <a class="h2"
						href="javascript:void(0);">准考证</a> <a class="h2"
						href="javascript:void(0);">证书领取</a>
				</h2>
				<!-- 考试动态 -->
				<div class="fl w710 list-box mt10">
					<ul class="fl w710 f14" >
						<c:if test="${empty denamic_test_foot }">
							<li>暂无内容</li>
						</c:if>
						<c:forEach items="${denamic_test_foot }" var="info">
							<li><a target="_blank" title="${info.info_title }"
								href="/front_info/get_course_info_detail.html?info_id=${ info.info_id}">${info.info_title }</a><span><fmt:formatDate
										pattern="MM-dd" value="${info.info_time }" /></span></li>
						</c:forEach>
					</ul>
				</div>
				<div class="fr tjbox list-box1 ">
					<h2 class="atitle">
						<a href="javascript:void(0);"> <i></i>热点推荐
						</a>
					</h2>
					<ul class="fr f12">
						<c:if test="${empty denamic_test_foot_hot }">
							<li>暂无内容</li>
						</c:if>
						<c:forEach items="${denamic_test_foot_hot }" var="info">
							<li><a target="_blank" title="${info.info_title }"
								href="/front_info/get_course_info_detail.html?info_id=${ hot_info.info_id}">${info.info_title }</a><span><fmt:formatDate
										pattern="MM-dd" value="${info.info_time }" /></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- 等级的资讯 -->
			<c:forEach items="${dictionaries }" var="dic">
			<c:if test="${not empty dic.courseInfos }">
			<div class="fl column mt10">
				<h2 class="hd-title">
					<a href="javascript:void(0);">
					<c:if test="${dic.course_id!=1001 }">
					${dic.dictionary_name }
					</c:if>
					<c:if test="${dic.course_id==1001 }">
					<c:if test="${courseMenu.course_id!=31 && courseMenu.course_id!=32}">
					${dic.dictionary_name }
					</c:if>
					${courseMenu.course_name }
					</c:if>
					</a> <a
						href="javascript:void(0);" target="_blank" class="mor">更多<i></i></a>
					<a class="h2" href="javascript:void(0);" target="_blank">模拟考场</a> <a
						class="h2" href="javascript:void(0);" target="_blank">历年真题</a> <a
						class="h2" href="javascript:void(0);" target="_blank">章节练习</a> <a
						class="h2" href="javascript:void(0);" target="_blank">每日一练</a> <a
						class="h2" href="javascript:void(0);">考试辅导</a>
				</h2>
				<div class="fl w710 list-box mt10">
					<ul class="fl f14">
						<c:forEach items="${dic.courseInfos }" var="info">
							<li><a target="_blank" title="${info.info_title }"
								href="/front_info/get_course_info_detail.html?info_id=${ info.info_id}">${info.info_title }</a><span><fmt:formatDate
										pattern="MM-dd" value="${info.info_time }" /></span></li>
						</c:forEach>
					</ul>
				</div>
				<div class="fr tjbox list-box1 ">
					<h2 class="atitle">
						<a href="javascript:void(0);"> <i></i>热点推荐
						</a>
					</h2>
					<ul class="fr f12">
							<c:if test="${empty dic.hot_courseInfos }">
							<li>暂无内容</li>
						</c:if>
						<c:forEach items="${dic.hot_courseInfos  }" var="hot_info">
							<li><a target="_blank" title="${hot_info.info_title }"
								href="/front_info/get_course_info_detail.html?info_id=${ hot_info.info_id}">${hot_info.info_title }</a><span><fmt:formatDate
										pattern="MM-dd" value="${hot_info.info_time }" /></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			</c:if>
			</c:forEach>
		</div>
	</div>
		 <!--登录注册窗口  -->
	<jsp:include page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/footer.jsp"></jsp:include>
</body>
</html>