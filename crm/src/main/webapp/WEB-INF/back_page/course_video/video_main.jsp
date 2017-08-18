<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/common/right_content.css" />
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/school/back/course/video.js"></script>
<div class="back_right" style="min-width: 900px;">
	<div class="back_r_top">
		<div class="left">课程列表</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<div class="operation">
			<div class="opera_left left">
				 <button type="button" onclick="jinzhi_dianji()" class="btn left btn-primary" id="add_employee">
					<i class="fa fa-ban"></i>一键禁止
				</button>
			</div>
			<div class="opera_right right">
				<select onchange="change_select()"
					class="form-control select select-primary left top_select"
					id="select_val" data-toggle="select">
					<option value="0">请选择查询条件</option>
					<option value="name">名称</option>
				</select> <input type="text" id="check_val" placeholder="请输入查询内容"
					class="form-control left top_search" />
				<button type="button" onclick="seacher_video()"
					class="btn left btn-primary">查询</button>
			</div>
		</div>

		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>课程名称</th>
						<th>课程分类</th>
						<!-- <th>班型</th> -->
						<!-- <th>价格</th> -->
						<th>课时</th>
						<th>主讲教师</th>
						<th>到期时间</th>
						<th>添加时间</th>
						<th>课件</th>
						<th>资讯</th>
						<th>操作</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty courseVideos.list }">
						<tbody>
							<c:forEach items="${courseVideos.list }" var="video"
								varStatus="vs">
								<c:if test="${vs.count%2 == '0' }">
									<tr class="active" id="zhang${video.video_id}">
								</c:if>
								<c:if test="${vs.count%2 != '0' }">
									<tr id="zhang${video.video_id}">
								</c:if>
								<td><label class="label label-success btn-primary"
									for="minimal-checkbox-1">${vs.index+1+courseVideos.begin }</label></td>
								<td>${video.video_name }</td>
								<td>${video.courseMenu.course_name }</td>
								<%-- <td>${video.banxing }</td> --%>
								<%-- <td>${video.video_money_now }</td> --%>
								<td>${video.keshi_number }</td>
								<td>${video.teacher.teacher_name }</td>
								<td>
								<c:if test="${empty video.daoqi_time }">--</c:if>
								<c:if test="${not empty video.daoqi_time }">
								<fmt:formatDate 
										value="${video.daoqi_time }" />
								</c:if>
								</td>
								<td><fmt:formatDate type="both"
										value="${video.video_time }" /></td>
								<td><button type="button"
										onclick="upload_ware(${video.video_id})"
										class="btn btn-info btn-xs">上传</button>
										<button type="button"
										onclick="check_ware(${video.video_id})"
										class="btn btn-warning btn-xs">查看</button></td>
								<td><c:if test="${video.is_info=='否'  }">
										<button type="button"
											onclick="video_info_shangjia('是',${video.video_id},${courseVideos.pageNumber })"
											class="btn btn-warning btn-xs">发布</button>
									</c:if>
									<c:if test="${video.is_info=='是'  }">
										<button type="button"
											onclick="video_info_shangjia('否',${video.video_id},${courseVideos.pageNumber })"
											class="btn btn-default btn-xs">取消</button>
									</c:if></td>
								<td>
									<button type="button" onclick="add_zhang(${video.video_id})"
										class="btn btn-primary btn-xs">添加</button>
									<button type="button" onclick="chakan_video(${video.video_id})"
										class="btn btn-success btn-xs">查看</button>
									<button type="button"
										onclick="to_update_video(${video.video_id})"
										class="btn btn-warning btn-xs">修改</button>
									<button type="button" onclick="delete_video(${video.video_id},${courseVideos.pageNumber })"
										class="btn btn-danger btn-xs">删除</button> 
										<c:if
										test="${video.is_tuijian=='否'  }">
										<button type="button"
											onclick="video_tuijian('是',${video.video_id},${courseVideos.pageNumber })"
											class="btn btn-info btn-xs">推荐</button>
									</c:if> 
									<c:if test="${video.is_tuijian=='是'  }">
										<button type="button"
											onclick="video_tuijian('否',${video.video_id},${courseVideos.pageNumber })"
											class="btn btn-default btn-xs">取消</button>
									</c:if>
									<c:if test="${video.video_type=='下架'  }">
										<button type="button"
											onclick="video_shangjia(${video.video_id},${courseVideos.pageNumber })"
											class="btn btn-warning btn-xs">发布</button>
									</c:if>
									<c:if test="${video.video_type!='下架'  }">
										<button type="button"
											onclick="video_xiajia(${video.video_id},${courseVideos.pageNumber })"
											class="btn btn-default btn-xs">下架</button>
									</c:if>
								</td>
								<td><button onclick="zhankai_zhang(${video.video_id})"
										type="button" class="btn btn-inverse btn-xs zhang_down">
										<i id="tubiao${video.video_id}" class="fa fa-plus"></i>
									</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
			</table>
			<c:if test="${ empty courseVideos.list }">
				<p class="zanwu">暂无课程信息</p>
			</c:if>
		</div>
		<div class="pages">
			<!-- <div class="whole left">
				<button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
				<button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
			</div> -->
			<ul class="pagination right">
				<c:if test="${courseVideos.hasPreviousPage==true}">
					<li class="previous"
						onclick="video_jump_page(${courseVideos.pageNumber-1})"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${courseVideos.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${courseVideos.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li onclick="video_jump_page(${page})"><a href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${courseVideos.hasNextPage==true}">
					<li class="next" onclick="video_jump_page(${roles.pageNumber+1})"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>