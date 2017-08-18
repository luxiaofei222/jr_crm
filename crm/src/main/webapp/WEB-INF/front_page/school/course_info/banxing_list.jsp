<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<script src="/js/school/front/course_info/action.js"></script>
	<div class="tab-con01Box" node-type="menu-tab"
						data-type="{cs1:'hover',tlist:'h6 ul a',clist:'.table-box'}">
						<c:if test="${empty courseVideos_banxing }">
						<span style="display:block;margin:0 auto;text-align:center;font-size:25px;color:#06C1AE;margin-top:200px;">暂无班型信息</span>
						</c:if>
						<c:if test="${not empty courseVideos_banxing }">
						<h6 class="tab-title clearfix">
							<ul class="clearfix">
							<c:forEach items="${courseVideos }" var="banxing" varStatus="vs">
							<c:if test="${vs.index==0 }">
								<li onmouseover="get_banxing_video('${banxing.banxing}',${courseMenu.course_id })"><a class="hover" href="javascript:void(0);"
									target="_blank">${banxing.banxing }</a></li>
							</c:if>
							<c:if test="${vs.index!=0 }">
								<li onmouseover="get_banxing_video('${banxing.banxing}',${courseMenu.course_id })"><a href="javascript:void(0);" target="_blank">${banxing.banxing }</a></li>
							</c:if>
							</c:forEach>
							</ul>
							<a class="a-more-btn"
								href="/retrieval/get_retrieval_main.html?course_id=${courseMenu.course_id }"
								target="_blank">更多课程</a>
						</h6>
						<div id="banxing_sub_list">
						<div class="table-box" style="display: block;" >
							<table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tbody>
									<tr class="title">
										<td>新版课程</td>
										<td>讲师</td>
										<td>课时</td>
										<td>价格</td>
										<td>免费体验</td>
										<td>选课</td>
									</tr>
									<c:forEach items="${courseVideos_banxing }" var="banxing_video">
									<tr>
										<td class="name"><a href="javascript:void(0);"
											target="_blank">${banxing_video.video_name }</a></td>
										<td>${banxing_video.teacher.teacher_name }</td>
										<td>${banxing_video.keshi_number }</td>
										<td>${banxing_video.video_money_now }<!-- 0.00 -->元</td>
										<td><a href="javascript:void(0);" target="_blank"><img
												width="36" height="50"
												src="/images/school/front/courseinfo/st2.gif"></a></td>
										<td><a class="a-btn" href="http://tb.53kf.com/code/client/10147174/1]"
											target="_blank">报名</a></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						</div>
						</c:if>
					</div>