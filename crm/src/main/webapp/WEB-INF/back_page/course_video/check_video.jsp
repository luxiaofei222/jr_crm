<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<!-- <link rel="stylesheet" href="/css/school/back/common/right_content.css" /> -->
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<link rel="stylesheet" href="/css/school/back/course_video/course.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script type="text/javascript" src="/ckplayer/ckplayer/ckplayer.js"
	charset="utf-8"></script>
	<script src="/js/school/back/course/chec_video.js"></script>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">课程详情</div>
	</div>
	<div class="right_content">

		<div class="content_message">
			<div class="back_class_info">
				<!--视频 str-->

				<div class="course-video">
					<div class="videos">
						<div class="vleft" id="video"></div>
						<div class="vright">
							<div class="vright_title">课程列表</div>
							<div class="vright_content">
								<ul class="xiangxi">
									<c:forEach items="${courseVideo.courseVideos_zhang }"
										varStatus="vs" var="zhang">
										<li class="chapter"><span class="zhangjie zhankai"><%-- 第${vs.index+1 }章&nbsp; --%>${zhang.video_name }<i
												class="fa fa-chevron-down"></i></span>
											<div class="chapters" style="display: block;">
												<ul id="jie_ul${vs.index+1 }">
													<c:forEach items="${zhang.courseVideos_sanji }" var="jie"
														varStatus="vs">
														<c:if test="${vs.index==0 }">
															<li class="kj_mc play" id="xiaojie${jie.video_id }" onclick="get_video_player_url(${jie.video_id })" value="${jie.video_id }"><span class="s-playing">${vs.index+1 }.${jie.video_name }
																	<div class="playing">
																		<div class="music active">
																			<i></i><i></i><i></i><i></i><i></i>
																		</div>
																	</div>
															</span></li>
														</c:if>
														<c:if test="${vs.index!=0 }">
															<li class="kj_mc" id="xiaojie${jie.video_id }" onclick="get_video_player_url(${jie.video_id })" value="${jie.video_id }"><span class="s-playing">${vs.index+1 }.${jie.video_name }
																	<div class="playing hide">
																		<div class="music active">
																			<i></i><i></i><i></i><i></i><i></i>
																		</div>
																	</div>
															</span></li>
														</c:if>
													</c:forEach>
												</ul>
											</div></li>
									</c:forEach>
								</ul>
							</div>

						</div>
					</div>
				</div>

				<!--视频 end-->
				<!--课程简介 str-->
				<div class="course_jieshao">
					<ul class="ul_class1 clearfix">
						<li><label>课程名称：</label> <span>${courseVideo.video_name }</span></li>
						<li><label>课程分类：</label> <span>${courseMenu.course_name }</span></li>
						<li><label>课时：</label> <span>${courseVideo.keshi_number }课时</span></li>
					</ul>
					<ul class="ul_class2 clearfix">
					    <li><label>主讲教师：</label> <span>${courseVideo.teacher.teacher_name }</span></li>
						<li><label>班型：</label> <span>${courseVideo.banxing }</span></li>
						<li><label>价格：</label> <span>${courseVideo.video_money_now }</span></li>
					</ul>
					<ul class="ul_class3 clearfix">
						<li><label>浏览量：</label> <span>${courseVideo.play_times }</span></li>
						<li><label>评论量：</label> <span>${courseVideo.comment_number }</span></li>
					</ul>
					<div class="class_jianjie clearfix">
						<label>课程简介：</label> 
						<c:if test="${not empty courseVideo.video_dis }">
						<span>${courseVideo.video_dis }</span>
						</c:if>
						<c:if test="${empty courseVideo.video_dis }">
						<span>暂无简介</span>
						</c:if>
					</div>
					<div class="class_kj clearfix">
						<label>课件：</label> <span>
						<c:if test="${not empty courseWares }">
						<c:forEach items="${courseWares }" var="ware" varStatus="vs">
						 <a href="${ware.courseware_file }" download="${ware.courseware_name }" target="_blank">${vs.index+1 }、&nbsp;${ware.courseware_name }</a>
						 </c:forEach>
						  </c:if>
						  <c:if test="${empty courseWares }">
							 <a style="color: orange;">暂无课件内容</a>
						 	</c:if>
						</span>
					</div>
					<div class="clearfix"></div>
				</div>
				<!--课程简介 end-->
				<!--课程详情/学员评价 str-->
				<div class="course_xiangqing">
					<div class="tab_menu">
						<ul class="sub-nav">
							<li class="on">课程详情</li>
							<li onclick="get_comment(${courseVideo.video_id})">学员评价</li>
						</ul>
					</div>
					<div class="tab_box">
					<c:if test="${not empty courseVideo.course_jieshao }">
						<div class="kecheng">
							<!--课程介绍 -->
							${courseVideo.course_jieshao }
						</div>
						</c:if>
							<c:if test="${empty courseVideo.course_jieshao }">
						<div class="kecheng">
							暂无课程介绍
						</div>
						</c:if>
						<div class="pingjia" id="comment_list">
							<!--课程评论  -->
						</div>
					</div>
				</div>
				<!--课程详情/学员评价 end-->
			</div>
		</div>
	</div>
</div>
<div class="class_back" onclick="fanhui()"><i class="fa fa-mail-reply"></i></div>