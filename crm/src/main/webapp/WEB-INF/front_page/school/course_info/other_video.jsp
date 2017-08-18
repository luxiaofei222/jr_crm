<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/front/course_info/type.css">
<script>
$(function() {
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
})

function loadedHandler() {
	CKobject.getObjectById('ckplayer_a1').addListener('time', 'timeHandler');
}
var isnum = true;
function timeHandler(t) {// 试看时间监听事件
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
		return isnum;
	}
}
</script>
<c:if test="${not empty myCourse }">
<input type="hidden" value="1" id="is_my_course">
</c:if>
<input type="hidden" value="${video.video_type }" id="is_shoufei" />
<div class="table-box noborder" style="display: block;">
<div class="kcst-box iplay" node-type="menu-play">
	<div class="sp-leftBox">
		<div class="sp-box iplay-box" id="video">
			<!-- 视频播放页面 -->
		</div>
		<div class="finish">
              <span>尊敬的用户：<br/>试看已结束，如果您想继续学习，请购买该课程。</span>
              <div class="f_buy">立即购买</div>
            </div>
		<div class="kcjsBox">
			<h6 class="kcjs-title">课程介绍</h6>
			<div class="kcjs-txtcon clearfix">
				<span>${video.video_name }</span>
				<div class="kcjs-listBox">
					<p>
						<strong><em>主&nbsp;&nbsp;讲：</em>${video.teacher.teacher_name }
						</strong> <strong><em>年&nbsp;&nbsp;份：</em>
						<fmt:formatDate pattern="yyyy" value="${video.video_time }" /></strong>
					</p>
					<p>
						<strong><em>价&nbsp;&nbsp;格：</em><i>￥<!-- 0.00 --> ${video.video_money_now }</i></strong><strong><em>购买数：</em>${video.pay_number }</strong>
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
						<c:forEach items="${video.courseVideos_zhang }" var="zhang">
							<li class="hover"><a class="expanded"
								href="javascript:void(0);">${zhang.video_name } </a>
								<div class="scroll-y">
									<c:forEach items="${zhang.courseVideos_sanji }" var="jie">
										<p class="clearfix" pid="${jie.video_id }">
											<i></i><a href="javascript:void(0);"
												onclick="get_video_player_url(${jie.video_id })">${jie.video_name }</a><em>${jie.video_type }</em>
										</p>
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
		target="_blank">更多课程播放</a> <a
		href="/sc_coursevideo/get_course_video_player.html?video_id=${video.video_id }"
		target="_blank">立即购买</a>
</div>
</div>