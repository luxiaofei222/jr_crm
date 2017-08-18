<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="${courseInfo.meta_dis }">
<meta name="keywords" content="${courseInfo.meta_key }">
<title>${courseInfo.meta_title }</title>
<link type="text/css" rel="stylesheet"
	href="/css/school/front/course_info/news.css">
<link type="text/css" rel="stylesheet"
	href="/css/school/front/course_info/top_footer.css">
<script src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/front/course_info/date.js"></script>
</head>
<body>
	<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/info_header.jsp"></jsp:include>
	<div class="head">
		<div class="nav1">
			<div class="nav-mid">
				<ul>
					<li class="w1"><a
						href="/front_info/get_course_info.html?courseid=${courseMenu.sub_course_id }"
						class="title">动态</a><a href="/entry_plan/get_entry_plan.html">考试报名</a> <a
						href="/front_info/get_course_info_erji_list.html?info_type=考试动态&course_id=${courseMenu.course_id }">考试动态</a>
						 <a href="/front_info/get_course_info_erji_list.html?info_type=热点专题&course_id=${courseMenu.course_id }">热点专题</a>
						<a href="/front_info/get_course_info_erji_list.html?info_type=报考指南&course_id=${courseMenu.course_id }">报考指南</a> <a
						href="/person/get_person_index.html">证书领取</a> <a href="/person/get_person_index.html">考试大纲</a>
						<a href="http://www.jingrenedu.cn/front_news/get_xinwen_main.html">政策法规</a><a href="/person/get_person_index.html">考试教材</a></li>
					<!-- <li class="line"></li>
					<li class="w2"><a href="javascript:void(0);" class="title">等级</a>
						<a href="javascript:void(0);">二级心理咨询师</a> <a
						href="javascript:void(0);">三级心理咨询师</a></li> -->
					<li class="line"></li>
					<li class="w3"><a
						href="/front_info/get_course_info.html?courseid=${courseMenu.sub_course_id }"
						class="title">备考</a>	<a href="/questionindex/get_question_index.html" class="cyellow">模拟试题</a> <a
						href="/questionindex/get_question_index.html" class="cyellow">历年真题</a> <a
						href="/front_info/get_course_info_erji_list.html?info_type=考试试题&course_id=${courseMenu.course_id }">考试试题</a>
						 <a href="/front_info/get_course_info_erji_list.html?info_type=课程相关&course_id=${courseMenu.course_id }">课程相关</a>
						<a href="/front_info/get_course_info_erji_list.html?info_type=热点推荐&course_id=${courseMenu.course_id }">热点推荐</a>
						<a href="/retrieval/get_retrieval_main.html?course_id=${courseMenu.course_id }" target="_blank" class="cyellow">网校课程</a>
						<a href="javascript:void(0);">技巧心得</a><a href="javascript:void(0);">资讯专题</a></li>
					<li class="line"></li>
					<li class="w4"><a
						href="/front_info/get_course_info.html?courseid=${courseMenu.sub_course_id }"
						class="title">综合</a><a
						href="/front_info/get_course_info.html?courseid=${courseMenu.sub_course_id }">章节习题</a><a
						href="/front_info/get_course_info.html?courseid=${courseMenu.sub_course_id }">考试社区</a>
						<a
						href="/front_info/get_course_info.html?courseid=${courseMenu.sub_course_id }">职场资讯</a><a
						href="/front_info/get_course_info.html?courseid=${courseMenu.sub_course_id }">辅导讲义</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main">
		<div class="hot-txt ">
			您现在的位置：<a href="/index.jsp">京人网校</a>&nbsp;>>&nbsp;<a
				href="/front_info/get_course_info.html?courseid=${courseMenu.sub_course_id }">${courseMenu.course_name }</a>&nbsp;>>&nbsp;<a
				href="javascript:void(0)">${courseInfo.info_type }</a>&nbsp;>>&nbsp;<strong>${courseInfo.info_title }</strong>
		</div>
		<div class="fl w1000">
			<div class="fl w680 bline">
				<div class="news-con-blk">
					<h1>${courseInfo.info_title }</h1>
					<div class="tit-bar">
						<span><fmt:formatDate pattern="yyyy年MM月dd日"
								value="${courseInfo.info_time }" /></span><span>来源:${courseInfo.info_laiyuan }</span>
					</div>
					<div class="gads1 mt10">
						<div baidu-dup="1117737" class="ggw640h50"></div>
					</div>
					<div id="daodu">
						<strong>导读: </strong>${courseInfo.info_zhaiyao }</a>
					</div>
					<div class="news-body">${courseInfo.info_content }</div>
					<p align="center">
					<div class="fl about-read">
						<h3>相关推荐</h3>
						<ul>
							<c:forEach items="${late_update }" var="late">
								<li><a target="_blank"
									href="/front_info/get_course_info_detail.html?info_id=${ late.info_id}"
									title="${late.info_title }">${late.info_title }</a><span><fmt:formatDate
											pattern="yyyy-MM-dd" value="${courseInfo.info_time }" /></span></li>
							</c:forEach>
						</ul>
					</div>
					<br / clear="all">
				</div>

				<div id="news-pl"></div>
			</div>
			<div class="fr w300 clearfix">
				<div class="gg300">
					<div baidu-dup="1009472" class="ggw300h200"></div>
				</div>
				<div class="gg300 mt20">
					<div baidu-dup="871569" class="ggw300h200"></div>
				</div>
				<h2 class="atitle">
					<a href="#"><i></i>心理咨询师&nbsp;·&nbsp;题库</a><a href="/questionindex/get_question_index.html"
						target="_blank" class="mor">我的题库</a>
				</h2>
				<div class="box-tk mt10">
					<ul class="clearfix">
						<li><span><a href="/questionindex/get_question_index.html" target="_blank" class="btn">开始练习</a><a
								href="/questionindex/get_question_index.html"><i></i> 模拟试题 </a></span>
							<p>全真模拟无纸化机考流程。</p></li>
						<li><span><a href="/questionindex/get_question_index.html" target="_blank" class="btn">开始练习</a><a
								href="/questionindex/get_question_index.html"><i></i> 历年真题</a></span>
							<p>发布真题试卷，模拟练习。</p></li>
						<li><span><a href="/questionindex/get_question_index.html" target="_blank" class="btn">开始练习</a><a
								href="#" target="_blank"><i></i> 章节练习</a></span>
							<p>逐章逐节的做题练习。</p></li>
						<li><span><a href="/questionindex/get_question_index.html" target="_blank" class="btn">开始练习</a><a
								href="/questionindex/get_question_index.html" target="_blank"><i></i> 每日一练</a></span>
							<p>每天10题，随机练习</p></li>
					</ul>
				</div>
				<div class="gg300 mt20">
					<div baidu-dup="1009476" class="ggw300h200"></div>
				</div>
				<h2 class="atitle mt20">
					<i></i>距离考试还有<span class=" nub-tb"> <c:if
							test="${empty courseMenu.exam_time }">
							<input type="hidden" id="exam" value="${tim }">
						</c:if> <c:if test="${not empty courseMenu.exam_time }">
							<input type="hidden" id="exam" value="${courseMenu.exam_time }">
						</c:if> <script>
							var exam = $("#exam").val();
							new lmj.down_day({
								'stopDate' : exam,
								'replaceStr' : '<span><em>$&</em></span>'
							})
						</script>
					</span>天
				</h2>
				<div class="fl box-lc mt10">
					<dl>
						<dd>
							<span class="hh">
								<h3>
									报名<i></i>
								</h3> <a href="#" target="_blank">2016下半年报名8月-10月</a>
							</span> <span><a href="#" target="_blank">报名条件</a><a href="#"
								target="_blank">报名时间</a><a href="#" target="_blank">报名官网</a><a
								href="#" target="_blank">报名流程</a></span>
						</dd>
						<dd>
							<span class="hh">
								<h3>
									准考证<i></i>
								</h3> <a href="#" target="_blank">考前一周打印准考证</a>
							</span> <span><a href="#" target="_blank">准考证打印时间及入口</a><a
								href="#" target="_blank">准考证打印注意事项</a></span>
						</dd>
						<dd>
							<span class="hh">
								<h3>
									考试<i></i>
								</h3> <a href="#" target="_blank">2016下半年11月19日考试</a>
							</span> <span><a href="#" target="_blank">考试教材</a><a href="#"
								target="_blank">考试大纲</a><a href="#" target="_blank">考试科目</a><a
								href="#" target="_blank">考试题型</a></span>
						</dd>
						<dd>
							<span class="hh">
								<h3>
									查分<i></i>
								</h3> <a href="#" target="_blank">考后2个月</a>
							</span> <span><a href="#" target="_blank">查询时间</a><a href="#"
								target="_blank">有效期</a><a href="#" target="_blank">合格标准</a><a
								href="#" target="_blank">晒分交流</a></span>
						</dd>
						<dd>
							<span class="hh">
								<h3>
									领证<i></i>
								</h3> <a href="#" target="_blank">考后半年领取</a>
							</span> <span><a href="#" target="_blank">证书领取时间</a><a href="#"
								target="_blank">证书样本</a><a href="#" target="_blank">证书的作用</a></span>
						</dd>
					</dl>
				</div>
				<ul class="tool-box">
					<li><a href="#"><em class="ico-zn"></em>
							<p>新手指南</p> </a></li>
					<li class="line"></li>
					<li><a href="#"><em class="ico-wt"></em>
							<p>常见问题</p> </a></li>
					<li class="line"></li>
					<li><a href="#"><em class="ico-dy"></em>
							<p>在线答疑</p> </a></li>
				</ul>
				<h2 class="atitle mt20">
					<a href="#"><i></i>最新热点</a><a href="javascript:void(0)" target="_blank" class="mor">更多<i></i></a>
				</h2>
				<div class="news-list mt10">
					<ul class="list-box ">
					<c:forEach items="${late_update_hot }" var="late" varStatus="vs">
						<li><a target="_blank" href="/front_info/get_course_info_detail.html?info_id=${ late.info_id}" title="${late.info_title }">
						<c:if test="${vs.index+1==1 || vs.index+1==2 ||vs.index+1==3 }">
						<span class="bgcZ">${vs.index+1 }</span>
						</c:if>
						<c:if test="${vs.index+1!=1&& vs.index+1!=2&&vs.index+1!=3 }">
						<span>${vs.index+1 }</span>
						</c:if>
						${late.info_title }</a></li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div style="clear: both;"></div>
	<!--登录注册窗口  -->
	<jsp:include
		page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/footer.jsp"></jsp:include>
</body>
</html>