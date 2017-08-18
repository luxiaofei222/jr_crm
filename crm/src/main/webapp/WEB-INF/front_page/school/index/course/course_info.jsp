<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/school/front/index/gundong.js"></script>
<script>
//加载动画
function jiazai_donghua(course_parent_id){
	$("#course_video_list"+course_parent_id).html("<div class='page1'><div class='circle-loader'><div class='circle-line'><div class='circle circle-blue'></div>" +
			"<div class='circle circle-blue'></div><div class='circle circle-blue'></div>" +
			"</div><div class='circle-line'><div class='circle circle-yellow'></div><div class='circle circle-yellow'></div>" +
			"<div class='circle circle-yellow'></div></div><div class='circle-line'><div class='circle circle-red'></div>" +
			"<div class='circle circle-red'></div><div class='circle circle-red'></div></div><div class='circle-line'>" +
			"<div class='circle circle-green'></div><div class='circle circle-green'></div><div class='circle circle-green'></div>" +
			"</div></div></div>");
}
//获取课程二级菜单的内容
function get_sub_coursevideo(courseid,course_parent_id,obj){
	var index=$(obj).index();
	$("#course_video_list"+course_parent_id).siblings(".item_nei_bt4");
	s=(index)*130+"px";
	$("#nei_p_id"+course_parent_id).animate({left:s},100);
	jiazai_donghua(course_parent_id);
	$.post("/sc_course_class/get_sub_course_video.html",{
		'course_id':courseid
	},function(data){
		$("#course_video_list"+course_parent_id).html(data);
	})
}
/* //第二块课程分类
 $(function(){
	 $(".kc2_tab").click(function() {
		var index=$(this).index();
		$(".item_nei_h2").eq(index).css("display","block").siblings(".item_nei_h2").css("display","none");
		$(this).addClass("on").siblings().removeClass("on");
	}) 
   $(".kc2_tab").mouseover(function(){
        var index=$(this).index();
        var s=index*130+"px";
        $(".item_nei_p").animate({left:s},100);        
   })
}) */
</script>
<c:forEach items="${courseMenus }" var="cour_menu">
<div class="course_item" id="item${cour_menu.course_id }">
	    		<div class="course_item_nei">
	    			<div class='item_nei_h1'>
	    				<div class="item_nei_bt">${cour_menu.course_name }</div>	    				
	    				<div class='item_nei_bt2'>
	    					<ul>
	    					<c:forEach items="${cour_menu.sub_list }" var="sub" varStatus="vs">
	    					<c:if test="${vs.index+1==1 }">
	    						<li class="kc2_tab" onclick="get_sub_coursevideo(${sub.course_id },${sub.course_parent_id },this)" style='margin-left:0px;'>${sub.course_name }</li>
	    					</c:if>
	    						<c:if test="${vs.index+1!=1 }">
	    						<li class="kc2_tab" onclick="get_sub_coursevideo(${sub.course_id },${sub.course_parent_id },this)">${sub.course_name }</li>
	    					</c:if>
	    					</c:forEach>	
	    					<p class='item_nei_p' id="nei_p_id${cour_menu.course_id }">
	    						</p>
	    					</ul>
	    				</div>
	    			</div>
	    			<div class="item_nei_h2">
	    				<div id="course_video_list${cour_menu.course_id }">
	    				
	    				<div class="item_nei_bt3">
	    					<a href="${cour_menu.courseMenus_video.course_link }" target="_blank"><img src="${cour_menu.courseMenus_video.course_index_pic }"></a>
	    				</div>
	    				<!--课程分类展示内容  -->
	    				<div class='item_nei_bt4'>
		      				<ul class="qianmianul">
		      				<c:if test="${empty cour_menu.courseMenus_video.courseVideos }"><p style="text-align:center;font-size:20px;color:#06C1AE;line-height:465px;">暂无课程，请耐心等待！</p></c:if>
		      				<c:if test="${not empty cour_menu.courseMenus_video.courseVideos }">
		      				<c:forEach items="${cour_menu.courseMenus_video.courseVideos }" var="vide" varStatus="vs">
		      					<c:if test="${vs.index==0 }">
		      					<li class="no-left">
		      					</c:if>
		      						<c:if test="${vs.index==3 }">
		      					<li class="no-left">
		      					</c:if>
		      					<c:if test="${vs.index!=0 &&vs.index!=3}">
		      					<li>
		      					</c:if> 
			      					<a target="_blank" href="/sc_coursevideo/get_course_video_player.html?video_id=${vide.video_id }">
			      						<img src="${vide.video_pic }" class="item_nei_img">
			      					</a>
			      					<a target="_blank" href="/sc_coursevideo/get_course_video_player.html?video_id=${vide.video_id }"><span class="item_nei_title" title="${vide.video_name }">${vide.video_name }</span></a>
			      					<div class="nei_info1">
			      						<span class='info1_le'>现价:<span style="color:#f94c4c;">￥ ${vide.video_money_now }<!-- 0.00 --></span></span>
			      						<span class='info1_ri'>${vide.keshi_number }课时</span>
			      					</div>
			      					<div class="nei_info1">
			      						<span class='info1_le'>
			      							<img src="/images/school/front/index/head.png" style="float:left;margin-right:5px;">
			      							<span style="float:right;">${vide.comment_number}条评论</span>
			      						</span>
			      						<span class='info1_ri'>
			      							<img src="/images/school/front/index/start.png" style="float:left;margin-right:5px;">
			      							<span style="float:right;">${vide.play_times }次</span>
			      						</span>
			      					</div>
		      					</li>
		      				</c:forEach>
		      				</c:if>
		      				</ul>
		      				<div class="nei_bt4_wz">
		      					<div class="bt4_wz1"><a target="_blank" href="/front_info/get_course_info.html?courseid=${cour_menu.sub_course_id }">${cour_menu.sub_info_name }</a></div>
		      					<ul class='bt4_wz2'>
		      					<c:if test="${empty cour_menu.courseInfos }">
		      					<p style="text-align:center;font-size:15px;color:#06C1AE;line-height:300px;">暂无资讯信息！</p>
		      					</c:if>
		      					<c:if test="${not empty cour_menu.courseInfos }">
		      					<c:forEach items="${cour_menu.courseInfos }" var="info">
		      						<li class='bt4_wz3'>
		      							<img src="/images/school/front/index/zixun.png">
		      							<span><a target="_blank" href="/front_info/get_course_info_detail.html?info_id=${ info.info_id}" title="${info.info_title }">${info.info_title }</a></span>
		      						</li>
		      						</c:forEach>	
		      					</c:if>
		      					</ul>
		      				</div>	
	    				</div>
	    				</div>
	    				</div>
	    			</div>
	    		</div>   		
</c:forEach>