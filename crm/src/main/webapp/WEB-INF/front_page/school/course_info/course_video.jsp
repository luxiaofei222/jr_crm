<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="/css/school/front/course_video/css/font-awesome.min.css"
	rel="stylesheet">
<script src="/js/school/front/course_video/jquery-1.11.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="/ckplayer/ckplayer/ckplayer.js"
	charset="utf-8"></script>
<script src="/js/school/front/course_info/action.js"></script>

<script>
$(function() {
	var isjinzhi=$(".scroll-y").children("p:first").attr("jid");
	var is_my_course=$("#is_my_course").val();
	if(is_my_course!=1){//未加入我的课程
		if(isjinzhi==0){
			var id = $(".scroll-y").children("p:first").attr("pid");
			$.post("/sc_coursevideo/get_sanji_diyige_player.html", {
				'video_id' : id
			}, function(data) {
				if (data != null) {
					var flashvars = {
						f : data,
						s : 0,
						c : 0,
						p : 0,
						e : 0,
						m : 1,
						loaded : 'loadedHandler'
					};
					var video = [ data + '->video/mp4' ];
					CKobject.embed('/ckplayer/ckplayer/ckplayer.swf', 'video',
							'ckplayer_a1', '100%', '100%', false, flashvars, video);
				}
			})
			}else{
				$("#video").hide();
				$(".finish").show();
			}
	}else{//已加入我的课程
		var id = $(".scroll-y").children("p:first").attr("pid");
		$.post("/sc_coursevideo/get_sanji_diyige_player.html", {
			'video_id' : id
		}, function(data) {
			if (data != null) {
				var flashvars = {
					f : data,
					s : 0,
					c : 0,
					p : 0,
					e : 0,
					m : 1,
					loaded : 'loadedHandler'
				};
				var video = [ data + '->video/mp4' ];
				CKobject.embed('/ckplayer/ckplayer/ckplayer.swf', 'video',
						'ckplayer_a1', '100%', '100%', false, flashvars, video);
			}
		})
	}
})

function loadedHandler() {
	CKobject.getObjectById('ckplayer_a1').addListener('time', 'timeHandler');
}
var isnum = true;
function timeHandler(t) {// 试看时间监听事件
	var course_id=$("#cours_id_shiting").val();
	if(course_id==31||course_id==32){//一级建造师和二级建造师没有试听
	if (t > 720 && isnum) {//试听时间改为12分钟，原来是2分钟
		var is_my_course=$("#is_my_course").val();
		var is_shoufei = $("#is_shoufei").val();
		if(is_my_course!=1){//判断是否是我的课程，是我的课程则直接可以观看
		if (is_shoufei == '付费') {
			CKobject.getObjectById('ckplayer_a1').videoPause();// 暂停播放
			CKobject.getObjectById('ckplayer_a1').removeListener('time',
					'timeHandler');
			$("#video").hide();
			$(".finish").show();
			// 这时是判断当播放时间大于10，并且没有执行过试看判断的情况下进行判断。
			// 这里可以弹出登陆层或弹幕提示层
			isnum = false;
		} else {
			isnum = false;
		}
		}else{
			isnum = false;
		}
	}
	}else{
		if (t > 720 && isnum) {//试听时间改为12分钟，原来是2分钟
			var is_my_course=$("#is_my_course").val();
			var is_shoufei = $("#is_shoufei").val();
			if(is_my_course!=1){//判断是否是我的课程，是我的课程则直接可以观看
			if (is_shoufei == '付费') {
				CKobject.getObjectById('ckplayer_a1').videoPause();// 暂停播放
				CKobject.getObjectById('ckplayer_a1').removeListener('time',
						'timeHandler');
				$("#video").hide();
				$(".finish").show();
				// 这时是判断当播放时间大于10，并且没有执行过试看判断的情况下进行判断。
				// 这里可以弹出登陆层或弹幕提示层
				isnum = false;
			} else {
				isnum = false;
			}
			}else{
				isnum = false;
			}
		}
	}
	return isnum;
}

//点击获取课程视频
function get_course_other_video(video_id){
	$.post("/front_info/get_course_other_video.html",{
		'video_id':video_id
	},function(data){
		$("#video_list_sub").html(data);
	})
}
// 点击播放视频
function get_video_player_url(video_id,is_jinzhi) {
	if(is_jinzhi==0){
		$.post("/sc_coursevideo/get_sanji_diyige_player.html", {
			'video_id' : video_id
		}, function(data) {
			var flashvars = {
				f : data,
				s : 0,
				c : 0,
				p : 1,
				e : 0,
				loaded : 'loadedHandler'
			};
			var video = [ data + '->video/mp4' ];
			CKobject.embed('/ckplayer/ckplayer/ckplayer.swf', 'video',
					'ckplayer_a1', '100%', '100%', false, flashvars, video);
		})
	}
}
//班型介绍
function get_banxing(course_id){
	$.post("/front_info/get_banxing_list.html",{
		'course_id':course_id
	},function(data){
		$("#banxing_list").html(data);
	})
}
//班型课程
function get_banxing_video(banxing,course_id){
	$.post("/front_info/get_banxing_sub_list.html",{
		'banxing':banxing,
		'course_id':course_id
	},function(data){
		$("#banxing_sub_list").html(data);
	})
}
//班级介绍
function get_banji(course_id){
	$.post("/front_info/get_banji_list.html",{
		'course_id':course_id
	},function(data){
		$("#banji_list").html(data);
	})
}
//通关方案
function get_clarance(course_id){
	$.post("/front_info/get_clearance_list.html",{
		'course_id':course_id
	},function(data){
		$("#clearance_list").html(data);
	})
}
</script>
<div class="curriculumBox">
	<h6 class="curr-title clearfix">
		<span class="title-l"> <strong><i></i><a
				href="javascript:void(0);" target="_blank">${courseMenu.course_name }选课报名中心</a></strong>
		</span>
	</h6>
	<div class="tab-kczxBox">
		<div class="tab-conBox">
			<dl node-type="menu-tab"
				data-type="{cs1:'hover',tlist:'dt ul a',clist:'.tab-RconBox'}">
				<dt class="clearfix">
					<ul>
						<li><a class="hover" href="javascript:void(0);"
							target="_blank"><strong>课程播放</strong><font>协议通关 不过免费重学</font><i></i><em
								class="hot-ico"></em></a></li>
						<li><a href="javascript:void(0);"
							onmouseover="get_banxing(${courseMenu.course_id })"
							target="_blank"><strong>班型分类</strong><font>协议通关 不过免费重学</font><i></i><em
								class="hot-ico"></em></a></li>
						<li><a href="javascript:void(0);"
							onmouseover="get_banji(${courseMenu.course_id })" target="_blank"><strong>班级介绍</strong><em
								class="more-ico">详情点我</em><i></i></a></li>
						<li><a href="javascript:void(0);"
							onmouseover="get_clarance(${courseMenu.course_id })"
							target="_blank"><strong>通关方案</strong><em class="more-ico">详情点我</em><i></i></a></li>
					</ul>
				</dt>
				<input value="${courseMenu.course_id }" id="cours_id_shiting"
					type="hidden" />
				<dd class="tab-RconBox" style="display: block;">
					<c:if test="${empty courseVideos }">
						<span
							style="display: block; margin: 0 auto; text-align: center; font-size: 25px; color: #06C1AE; margin-top: 200px;">暂无课程内容</span>
					</c:if>
					<c:if test="${not empty courseVideos }">
						<div class="tab-con01Box" node-type="menu-tab"
							data-type="{cs1:'hover',tlist:'h6 ul a',clist:'.table-box'}">
							<h6 class="tab-title clearfix">
								<ul class="clearfix">
									<c:forEach items="${courseVideos }" var="video_menu"
										varStatus="vs">
										<c:if test="${vs.index==0 }">
											<li
												onmouseover="get_course_other_video(${video_menu.video_id })"><a
												class="hover"
												href="/sc_coursevideo/get_course_video_player.html?video_id=${video_menu.video_id }"
												target="_blank">${video_menu.video_name }</a></li>
										</c:if>
										<c:if test="${vs.index!=0 }">
											<li
												onmouseover="get_course_other_video(${video_menu.video_id })"><a
												href="/sc_coursevideo/get_course_video_player.html?video_id=${video_menu.video_id }"
												target="_blank">${video_menu.video_name }</a></li>
										</c:if>
									</c:forEach>
								</ul>
								<a class="a-more-btn"
									href="/retrieval/get_retrieval_main.html?course_id=${courseMenu.course_id }"
									target="_blank">更多课程</a>
							</h6>
							<%-- <c:forEach items="${courseVideos }" var="video" varStatus="vs"> --%>
							<%-- <c:if test="${vs.index==0 }"> --%>
							<div id="video_list_sub">
								<c:if test="${not empty myCourse }">
									<input type="hidden" value="1" id="is_my_course">
								</c:if>
								<input type="hidden" value="${video.video_type }"
									id="is_shoufei" />
								<div class="table-box noborder" style="display: block;">
									<%-- </c:if> --%>
									<%-- <c:if test="${vs.index!=0 }">
							<div class="table-box noborder">
							</c:if> --%>
									<div class="kcst-box iplay" node-type="menu-play">
										<div class="sp-leftBox">
											<div class="sp-box iplay-box" id="video">
												<!-- 视频播放页面 -->
											</div>
											<div class="finish">
												<span>尊敬的用户：<br />试看已结束，如果您想继续学习，请购买该课程。
												</span>
												<div class="f_buy">立即购买</div>
											</div>
											<div class="kcjsBox">
												<h6 class="kcjs-title">课程介绍</h6>
												<div class="kcjs-txtcon clearfix">
													<span>${video.video_name }</span>
													<div class="kcjs-listBox">
														<p>
															<strong><em>主&nbsp;&nbsp;讲：</em>${video.teacher.teacher_name }
															</strong> <strong><em>年&nbsp;&nbsp;份：</em> <fmt:formatDate
																	pattern="yyyy" value="${video.video_time }" /></strong>
														</p>
														<p>
															<strong><em>价&nbsp;&nbsp;格：</em><i>￥ ${video.video_money_now }<!-- 0.00 -->
															</i></strong><strong><em>购买数：</em>${video.pay_number }</strong>
														</p>
													</div>
												</div>
											</div>
										</div>
										<div class="kcml-rightBox">
											<h6 class="kcjs-title">课程目录</h6>
											<div class="kcml-tabBox"
												data-type="{cs1:'hover',tlist:'.ls-tabTitle li a',clist:'.ml-listBox .kcml-list'}"
												node-type="menu-tab">
												<ul class="ls-tabTitle">
													<li><a href="javascript:void(0);" class="hover">${video.teacher.teacher_name }
													</a></li>
												</ul>
												<div class="ml-listBox iplay-list">
													<div class="kcml-list">
														<ul>
															<c:forEach items="${video.courseVideos_zhang }"
																var="zhang">
																<li class="hover"><a class="expanded"
																	href="javascript:void(0);">${zhang.video_name } </a>
																	<div class="scroll-y">
																		<c:forEach items="${zhang.courseVideos_sanji }"
																			var="jie">
																			<c:if test="${empty myCourse }"><!--如果没有加入我的课程  -->
																			<c:if test="${jie.is_jinzhi==0}">
																			<p class="clearfix" pid="${jie.video_id }" jid="${jie.is_jinzhi }">
																				<i></i><a href="javascript:void(0);"
																					onclick="get_video_player_url(${jie.video_id },${jie.is_jinzhi })">${jie.video_name }</a><em>${jie.video_type }</em>
																			</p>
																			</c:if>
																			<c:if test="${jie.is_jinzhi==1}">
																			<p class="clearfix" pid="${jie.video_id }" jid="${jie.is_jinzhi }">
																				<i></i><a href="javascript:void(0);">${jie.video_name }</a><em>${jie.video_type }</em>
																			</p>
																			</c:if>
																			</c:if>
																			<c:if test="${not empty myCourse }"><!-- 如果加入我的课程 -->
																			<p class="clearfix" pid="${jie.video_id }" jid="${jie.is_jinzhi }">
																				<i></i><a href="javascript:void(0);"
																					onclick="get_video_player_url(${jie.video_id },0)">${jie.video_name }</a><em>${jie.video_type }</em>
																			</p>
																			</c:if>
																		</c:forEach>
																	</div></li>
															</c:forEach>
														</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="kc-btnBox">
										<a class="gm-btn"
											href="/sc_coursevideo/get_course_video_player.html?video_id=${video.video_id }"
											target="_blank">更多免费课程</a> <a
											href="/sc_coursevideo/get_course_video_player.html?video_id=${video.video_id }"
											target="_blank">立即购买</a>
									</div>
								</div>
							</div>
							<%-- </c:forEach> --%>
						</div>
					</c:if>
				</dd>
				<dd class="tab-RconBox" id="banxing_list">
					<!-- 班型介绍 -->
				</dd>
				<dd class="tab-RconBox">
					<div class="bjjs-box" id="banji_list">
						<!--班级介绍  -->
					</div>
				</dd>
				<dd class="tab-RconBox">
					<div class="ptfw-box" id="clearance_list">
						<!-- 通关方案 -->
					</div>
				</dd>
			</dl>
		</div>
	</div>
</div>