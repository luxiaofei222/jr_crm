<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css"
	href="/css/school/front/index/index.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/retrieval/exam.css" />
<link rel="shortcut icon" href="/images/school/front/index/logo2.png"
	type="image/x-icon" />
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/retrieval/exam.js"></script>
<script type="text/javascript" src="/js/school/front/index/index.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程检索</title>
</head>
<body>
	<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
	<div id="exam">
		<div class="header1">
			<header>
			<div class="home">
				<a href="/index.jsp"><img
					src="/images/school/front/retrieval/weizhi.png" /></a> <a
					href="/index.jsp">首页 &gt;</a>
			</div>
			<div class="test">
				<select name="course" id="course_par_id"
					onchange="get_yijifenlei_list()">
					<option id="opt_id" value="${courseMenu_par.course_id }">${courseMenu_par.course_name }</option>
					<c:forEach items="${courseMenus }" var="cour_main">
						<option value="${cour_main.course_id }">${cour_main.course_name }</option>
					</c:forEach>
				</select>
			</div>
			</header>
		</div>
		<section>
		<div class="nav_retrieval" id="shaixuantiaojian">
			<dl>
				<dt>方向:</dt>
				<dd>
					<c:if test="${empty courseMenu.course_name }">
						<a href="javascript:void(0)" class="green1">全部</a>
					</c:if>
					<c:if test="${not empty courseMenu.course_name }">
						<a href="javascript:void(0)"
							onclick="yiji_quanbu(${courseMenu.course_parent_id},this)">全部</a>
					</c:if>
					<c:forEach items="${courseMenus_sub }" var="course_menu">
						<c:if test="${courseMenu.course_name==course_menu.course_name }">
							<a href="javascript:void(0)" onclick="get_erji_neironglist(${course_menu.course_id },this)" class="green1">${course_menu.course_name }</a>
						</c:if>
						<c:if test="${courseMenu.course_name!=course_menu.course_name }">
							<a href="javascript:void(0)"
								onclick="get_erji_neironglist(${course_menu.course_id },this)">${course_menu.course_name }</a>
						</c:if>
					</c:forEach>
				</dd>
			</dl>
			<c:if test="${not empty courseMenu.course_name }">
				<div id="erji_shaixuan">
					<dl>
						<dt>等级:</dt>
						<dd>
							<a href="javascript:void(0)" pid="0"
								onclick="get_dengji_quanbu_neironglist(${courseMenu.course_id },this)"
								class="green1">全部</a>
							<c:forEach items="${dictionaries }" var="dic">
								<a href="javascript:void(0)" pid="${dic.dictionary_id }"
									onclick="get_erji_dengji_biaogelist(${courseMenu.course_id},${dic.dictionary_id },this)">${dic.dictionary_name }</a>
							</c:forEach>
						</dd>
					</dl>
					<dl>
						<dt>班型:</dt>
						<dd class="banxing">
							<a href="javascript:void(0)"
								onclick="get_banxing_quanbu_neironglist(${courseMenu.course_id },this)"
								pid="0" class="green1">全部</a>
							<c:forEach items="${dictionaries_banxing }" var="dic">
								<a href="javascript:void(0)" pid="${dic.dictionary_name }"
									onclick="get_erji_banxing_biaogelist(${courseMenu.course_id},'${dic.dictionary_name }',this)">${dic.dictionary_name }</a>
							</c:forEach>
						</dd>
					</dl>
				</div>
			</c:if>
		</div>
		</section>
		<footer>
		<div class="container">
			<div class="left">
				<div class="topd" id="zhuanhuan_liebiao_biaoge">
					<div class="tab" id="paixu_list">
						<a href="javascript:void(0)"
							onclick="get_zonghe_paixu(${courseMenu.course_id },'','',this)"
							class="green3">综合排序</a> <a href="javascript:void(0)"
							onclick="get_zuire_paixu(${courseMenu.course_id },'','',this)">最热</a>
						<a href="javascript:void(0)" id="jiage_jiangxu"
							onclick="paixu_jiage_jiangxu(${courseMenu.course_id },'','','jiangxu',this)">价格
							<img src="/images/school/front/retrieval/price.png" id="p1" class="oneimg">
							<img src="/images/school/front/retrieval/price2.png" id="p2" class="twoimg" />
						</a> 
						<a href="javascript:void(0)" style="display: none;"
							id="jiage_shengxu"
							onclick="paixu_jiage_jiangxu(${courseMenu.course_id },'','','shengxu',this)">价格
							<img src="/images/school/front/retrieval/price.png" id="p1" class="oneimg"><img
							src="/images/school/front/retrieval/price2.png" id="p2" class="twoimg" />
						</a>
					</div>
					<div class="list">
						<div style="float: left">
							<a id="across" onclick="get_biaogeshi(${courseMenu.course_id })"><img
								src="/images/school/front/retrieval/zongxiang2.png" id="lisp"></a>
							<a id="vertical"
								onclick="get_liebiaoshi(${courseMenu.course_id })"> <img
								src="/images/school/front/retrieval/hengxiang2.png" id="lisp2" /></a>
						</div>
						<!-- <div>
							<input name="chkItem" type="checkbox" value="只看免费课程" />只看免费课程
						</div> -->
					</div>
				</div>

				<div id="list_content">
					<div class="content2">
						<!--列表式的课程  -->
						<c:if test="${ empty courseVideos.list }">
							<p>暂无课程内容，敬请期待！</p>
						</c:if>
						<c:if test="${not empty courseVideos.list }">
							<c:forEach items="${courseVideos.list }" var="course_video">
								<div class="kc_con">
									<dl>
										<dt>
											<a target="_blank"
												href="/sc_coursevideo/get_course_video_player.html?video_id=${course_video.video_id }"><img
												src="${course_video.video_pic }" /></a>
										</dt>
										<dd>
											<h3>
												<a target="_blank"
													href="/sc_coursevideo/get_course_video_player.html?video_id=${course_video.video_id }">${course_video.video_name }</a>
											</h3>
											<p class="introduce">
												<c:if test="${not empty course_video.video_dis }">
							${course_video.video_dis }
							</c:if>
												<c:if test="${empty course_video.video_dis }">
							暂无描述
							</c:if>
											</p>
											<p style="margin-top:10px;">
												<span>现价:</span> <span class="green">￥${course_video.video_money_now }<!-- 0.00 --></span>
											</p>
											<p class="keshi" style="margin-top:10px;">
												<span>${course_video.keshi_number }课时</span> <span>${course_video.comment_number }条评论</span>
												<span>${course_video.play_times }次</span>
											</p>
										</dd>
									</dl>
									<a target="_blank"
										href="/sc_coursevideo/get_course_video_player.html?video_id=${course_video.video_id }"
										class="check_xq">查看详情</a>
								</div>
							</c:forEach>
						</c:if>
					</div>
					<div class="paging">
						<ul>
							<c:if test="${courseVideos.hasPreviousPage==true}">
							<c:if test="${empty show }">
							<li onclick="moren_jump_page(${courseVideos.pageNumber-1},'${courseMenu.course_id }')"><a href="javascript:void(0)">上一页</a></li>
							</c:if>
								<c:if test="${show=='yiji' }">
									<li
										onclick="retrieval_yiji_jump_page(${courseVideos.pageNumber-1},'${course_parent_id }','${type }')"><a href="javascript:void(0)">上一页</a></li>
								</c:if>
								<c:if test="${show=='other' }">
									<li
										onclick="retrieval_other_jump_page(${courseVideos.pageNumber-1},'${zuire }','${jiage_paixu }','${type }','${banxing }','${dictionary_id }','${course_id }')"><a href="javascript:void(0)">上一页</a></li>
								</c:if>
							</c:if>
							<c:forEach items="${courseVideos.navigatePageNumbers }" var="page">
								<c:choose>
									<c:when test="${courseVideos.pageNumber==page}">
									<li class="active"><a href="javascript:void(0)">${page}</a></li>
									</c:when>
									<c:otherwise>
									<c:if test="${empty show }">
										<li onclick="moren_jump_page(${page},'${courseMenu.course_id }')"><a href="javascript:void(0)">${page}</a></li>
									</c:if>	
										<c:if test="${show=='yiji' }">
											<li
												onclick="retrieval_yiji_jump_page(${page},'${course_parent_id }','${type }')"><a href="javascript:void(0)">${page}</a></li>
										</c:if>
										<c:if test="${show=='other' }">
											<li
												onclick="retrieval_other_jump_page(${page},'${zuire }','${jiage_paixu }','${type }','${banxing }','${dictionary_id }','${course_id }')"><a href="javascript:void(0)">${page}</a></li>
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${courseVideos.hasNextPage==true}">
								<c:if test="${empty show }">
									<li
										onclick="moren_jump_page(${courseVideos.pageNumber+1},${courseMenu.course_id })"><a href="javascript:void(0)">下一页</a></li>
								</c:if>
								<c:if test="${show=='yiji' }">
									<li
										onclick="retrieval_yiji_jump_page(${courseVideos.pageNumber+1},${course_parent_id },'${type }')"><a href="javascript:void(0)">下一页</a></li>
								</c:if>
								<c:if test="${show=='other' }">
									<li
										onclick="retrieval_other_jump_page(${messages.pageNumber+1},'${zuire }','${jiage_paixu }','${type }','${banxing }',${dictionary_id },${course_id })"><a href="javascript:void(0)">下一页</a></li>
								</c:if>
							</c:if>
						</ul>
					</div>
				</div>
				</div>
				<div class="right" id="course_tuijian_list">
					<!-- 推荐课程 -->
				</div>
			</div>
		</footer>
	</div>
	<!--登录注册窗口  -->
	<jsp:include
		page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/footer.jsp"></jsp:include>
</body>
</html>