<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
//点赞
function dianzan(obj,say_id){
	var praise_txt = $(obj).children("span");
	var num = parseInt(praise_txt.text());
	$.post("/center/dianzan.jr",{
		'say_id':say_id
	},function(data){
		if(data==1){
			$(obj).addClass("active");
			$(obj).children(".fa").addClass("praise");
			num += 1;
			praise_txt.text(num);
		}
	})
}
$(function(){
	$(".comment_big").click(function(event) {
		$(".comment").addClass("none");
		$(".comments").addClass("none");
		var e = window.event || event;
		if (e.stopPropagation) {
			e.stopPropagation();
		} else {
			e.cancelBubble = true;
		}
		$(this).parent().parent().find(".comment").toggleClass("none");
		$(this).parent().parent().find(".comment").find("textarea").focus();
	});
	$(".comment_time").children("i").click(
			function(event) {
				$(".comment").addClass("none");
				$(".comments").addClass("none");
				var e = window.event || event;
				if (e.stopPropagation) {
					e.stopPropagation();
				} else {
					e.cancelBubble = true;
				}
				var id=$(this).attr("pid");
				$("#comment_one"+id).toggleClass(
						"none");
				$("#comment_one"+id).find(
						"textarea").focus();
			});
	document.onclick = function(event) {
		var e=event||window.event;
		if(e.target.tagName!="TEXTAREA"){
			$(".comment").addClass("none");
			$(".comments").addClass("none");
		}
	};
})
//删除说说
function tipdelete(say_id){
	$("#fabu_content"+say_id).slideUp(500);
	$.post("/center/delete_say.jr",{
		'say_id':say_id
	},function(data){
		if(data==1){
			layer.msg("删除成功！");
		}else{
			layer.msg("系统发生错误！");
		}
	})
}
</script>
<style>
.img-responsive {
  display: inline-block;
  height: auto;
  max-width: 100%;
}
</style>
<c:forEach items="${employeeSays.list }" var="say">
	<div class="fabu_content">
		<div class="header_img left">
			<c:if test="${empty say.employee.employee_pic }">
				<img src="/images/employee/picture.jpg" />
			</c:if>
			<c:if test="${not empty say.employee.employee_pic }">
				<img src="${say.employee.employee_pic }" />
			</c:if>
		</div>
		<div class="message left">
		<c:if test="${sessionScope.employee_session.employee_id==1||sessionScope.employee_session.employee_id==47 }">
			<button onclick="tipdelete(${say.say_id})" class="deletebtn">删除</button>
		</c:if>	
			<div class="user_name">${say.employee.employee_name }</div>
			<div class="fabu_time">${say.time_change }</div>
			<div class="fabu_text">
				<p>${say.say_content }</p>
				<c:if test="${not empty say.say_pic }">
					<img src="${say.say_pic }" class="img-responsive" />
				</c:if>
			</div>
		</div>
		<div class="clear"></div>
		 <div class="liulan">
			<ul>
				<!-- <li><i class="fa fa-eye left"></i><span class="left">浏览</span></li> -->
				<li class="comment_big"><i class="fa fa-commenting-o left"></i><span
					class="left">评论</span></li>
				<c:if test="${say.is_zan==0 }">
					<li id="praise" onclick="dianzan(this,${say.say_id })">
				</c:if>
				<c:if test="${say.is_zan==1 }">
					<li id="praise" class="active">
				</c:if>
				<i class="fa fa-thumbs-o-up left" id="icon"></i>
				<span class="left" id="praise-txt">${say.zan_times }</span>
				</li>
			</ul>
			<div id="sub_say${say.say_id }">
			<script type="text/javascript">
			$(function(){
				var id=${say.say_id };
				$.post("/center/get_sub_say_list.jr?say_id="+id,function(data){
					$("#sub_say"+id).html(data);
				})
			})
			</script>
			</div>
			<div class="clear"></div>
			<div class="comment none">
				<textarea id="one_content${say.say_id }"></textarea>
				<a href="javascript:void(0)"
					onclick="huifu_say(${say.say_id },${employeeSays.pageNumber })">发表</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</c:forEach>
<div class="clear"></div>
<div class="fanye">
	<div class="left">
		<c:if test="${employeeSays.hasPreviousPage==true}">
			<a href="javascript:void(0)"
				onclick="jump_say_page(${employeeSays.pageNumber-1})">上一页</a>
		</c:if>
		<c:if test="${employeeSays.hasPreviousPage!=true}">
			<a href="javascript:void(0)" class="no_page">上一页</a>
		</c:if>
	</div>
	<div class="right">
		<c:if test="${employeeSays.hasNextPage==true}">
			<a href="javascript:void(0)"
				onclick="jump_say_page(${employeeSays.pageNumber+1})">下一页</a>
		</c:if>
		<c:if test="${employeeSays.hasNextPage!=true}">
			<a href="javascript:void(0)" class="no_page">下一页</a>
		</c:if>

	</div>
	<div class="clear"></div>
</div>