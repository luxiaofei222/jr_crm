<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/js/school/front/course_video/jquery-1.11.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="/js/school/front/course_video/jquery.raty.min.js"></script>
<script>
$(function() {
	$.fn.raty.defaults.path = '/images/school/front/course_video';
	$('#scoreName').raty({
		width : 210,
		scoreName : 'entity[score]',
		size : 24,
		click: function(score, evt) {
	            $("#pingfen").val(score);
	        }
	// 星级评分
	});
});
//保存评论信息
function save_comment(id){
	var content_comment=$.trim($("#content_comment").val());
	var pingfen=$("#pingfen").val();
	if(content_comment!=""){
		$.post("/sc_comment/save_comment.html",{
			'v_comment_content':content_comment,
			'pingfen':pingfen,
			'video_id':id
		},function(data){
			if(data==1){
				alert("恭喜您，评论成功！");
				comment_jump_page(1,id);
			}
		})
	}else{
		alert("请输入您要评论的内容！");
	}
}
//跳转分页
function comment_jump_page(page,id){
	pageNumber=page;
	var limit = 8;
	$.post("/sc_comment/get_video_comment.html", {
		'video_id' : id,
		'pageNumber' : pageNumber,
		'limit' : limit
	}, function(data) {
		$("#comment_list").html(data);
	})
}
//提示信息
function tishi_cotent(){
	var content_comment=$("#content_comment").val();
	if(content_comment!=""&&content_comment!=null){
		$("#tishi").html("您已经输入"+content_comment.length+"字");
	}else{
		$("#tishi").html("请输入您要评论的内容！")
	}
}
</script>
<div class="pl_kinds">
	<div class="skin skin-flat">
		<div class="skin-section">
			<ul>
				<li>
					<!-- <input tabindex="1" type="radio" id="flat-radio-1"
					name="flat-radio"> --> <label for="flat-radio-1">评论数量（${comments_number }）</label>
				</li>
				<!-- <li><input tabindex="2" type="radio" id="flat-radio-2"
					name="flat-radio"> <label for="flat-radio-2">好评（1000）</label>
				</li>
				<li><input tabindex="3" type="radio" id="flat-radio-3"
					name="flat-radio"> <label for="flat-radio-3">中评（200）</label>
				</li>
				<li><input tabindex="4" type="radio" id="flat-radio-4"
					name="flat-radio"> <label for="flat-radio-1">差评（34）</label>
				</li> -->
			</ul>
		</div>
	</div>
	<div class="xingji">
		<!-- <span>星级：</span>
		<ul>
			<li class="star-five"><img
				src="/images/school/front/course_video/f-star.png"></li>
			<li class="star-five"><img
				src="/images/school/front/course_video/f-star.png"></li>
			<li class="star-five"><img
				src="/images/school/front/course_video/f-star.png"></li>
			<li class="star-five"><img
				src="/images/school/front/course_video/f-star.png"></li>
			<li class="star-five"><img
				src="/images/school/front/course_video/f-star.png"></li>
		</ul> -->
	</div>
</div>
<!-- ****************************pl-kinds   end************************** -->
<div class="pl_content">
	<c:if test="${empty comments.list }">
		<p class="zanwu">暂无评论信息</p>
	</c:if>
	<c:if test="${not empty comments.list }">
		<ul>
			<c:forEach items="${comments.list }" var="comment">
				<li class="pl_contents">
					<div class="student">
						<div class="st_l left">
							<dl>
								<dt>
									<img src="${comment.user.user_pic }" />
								</dt>
								<dd>
									<div class="st_name">${comment.user.user_nickname }</div>
									<ul>
									<c:forEach begin="1" end="${comment.pingfen}" >
										<li class="star-five"><img
											src="/images/school/front/course_video/y-star.png" width="17"
											height="16"></li>
									</c:forEach>
									</ul>
								</dd>
							</dl>
						</div>
						<div class="st_r right">
							<span><fmt:formatDate type="both"
									value="${comment.v_comment_time }" /></span>
						</div>
					</div>
					<div class="clear"></div>
					<div class="pl_text">
						<p>${comment.v_comment_content }</p>
					</div>
				</li>
			</c:forEach>
		</ul>
	</c:if>
	<!-- ****************************yema str************************** -->
	<div class="search_fenye">
		<ul>
			<c:if test="${comments.hasPreviousPage==true}">
				<li class="fenyeda"
					onclick="comment_jump_page(${comments.pageNumber-1},${video_id })"><a
					href="javascript:void(0)"> 上一页 </a></li>
			</c:if>
			<c:forEach items="${comments.navigatePageNumbers }" var="page">
				<c:choose>
					<c:when test="${comments.pageNumber==page}">
						<li class="fenye_green"><a href="javascript:void(0)">${page}</a></li>
					</c:when>
					<c:otherwise>
						<li onclick="comment_jump_page(${page},${video_id })"><a href="javascript:void(0)">${page}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${courseVideos.hasNextPage==true}">
				<li class="fenyeda"
					onclick="comment_jump_page(${comments.pageNumber+1},${video_id })"><a
					href="javascript:void(0)"> 下一页 </a></li>
			</c:if>
		</ul>
	</div>
	<!-- ****************************yema end************************** -->
	<!-- ****************************fabu str************************** -->
	<div class="bline"></div>
	<div class="fabu">
		<h4>发表评价</h4>
		<input type="hidden" id="pingfen" value="5"/>
		<div id="scoreName"></div>
		<textarea  oninput="tishi_cotent()" id="content_comment"></textarea>
    <p id="tishi">请输入您要评论的内容！</p>
		<c:if test="${not empty sessionScope.user_session}">
		<input type="button" onclick="save_comment(${video_id})" value="发表" />
		</c:if>
			<c:if test="${empty sessionScope.user_session}">
			<input type="button" value="登录" onclick="get_login()" />
			</c:if>
	</div>
	<div class="clear"></div>
	<!-- ****************************fabu end************************** -->
</div>