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
function changecolor(say_id){
	$("#down"+say_id).css("color","#6CB7AB");
	$("#tip"+say_id).show();
}
function unchange(say_id){
	$("#down"+say_id).css("color","#333");
	$("#tip"+say_id).hide();
}
function tipshow(say_id){
	$("#tip"+say_id).show();
}
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
	<div class="fabu_content" id="fabu_content${say.say_id}">
		<div class="header_img left">
			<c:if test="${empty say.employee.employee_pic }">
				<img src="/images/employee/picture.jpg" />
			</c:if>
			<c:if test="${not empty say.employee.employee_pic }">
				<img src="${say.employee.employee_pic }" />
			</c:if>
		</div>
		<div class="message left">
			<div class="user_name">
				<div class="user_name1">${say.employee.employee_name }</div>
				<div class="user_name2">
					<i class="fa fa-chevron-down" onmouseover="changecolor(${say.say_id})"   id='down${say.say_id}'></i>
					<div class="tip" id="tip${say.say_id}" onmouseover="tipshow(${say.say_id})" onmouseout="unchange(${say.say_id})" >
						<div class='tip_nei' onclick="tipdelete(${say.say_id})">
							<i class="fa fa-trash" style="margin-left:10px;font-size:17px;"></i><span style="margin-left:14px;">删除</span>
						</div>
					</div>
				</div>
				<div style="clear:both;"></div>
			</div>
			
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
			<c:forEach items="${say.sub_employeeSays }" var="sub_say">
				<div class="clear"></div>
				<div class="comment_text">
					<div class="comment_img left">
						<c:if test="${empty sub_say.employee.employee_pic }">
							<img src="/images/employee/picture.jpg" class="left" />
						</c:if>
						<c:if test="${not empty sub_say.employee.employee_pic }">
							<img src="${sub_say.employee.employee_pic }" class="left" />
						</c:if>
					</div>
					<div class="comment_user left">
						<div class="comment_message">
							<span>${sub_say.employee.employee_name }</span>：${sub_say.say_content }
						</div>
						<div class="comment_time">${sub_say.time_change }<i
								class="fa fa-commenting-o" pid="${sub_say.say_id }" title="回复"></i>
						</div>
					</div>
					<c:forEach items="${sub_say.sub_employeeSays }" var="sanji">
						<div class="erji_pinglun">
							<div class="clear"></div>
							<div class="comment_img left">
								<c:if test="${empty sanji.employee.employee_pic }">
							<img src="/images/employee/picture.jpg" class="left" />
						</c:if>
						<c:if test="${not empty sanji.employee.employee_pic }">
							<img src="${sanji.employee.employee_pic }" class="left" />
						</c:if>
							</div>
							<div class="comment_user left">
								<div class="comment_message">
									<c:if test="${sanji.employee_id==sanji.parent_employee_id }">
										<span>${sanji.employee.employee_name }</span>
									</c:if>
									<c:if test="${sanji.employee_id!=sanji.parent_employee_id }">
										<span>${sanji.employee.employee_name }</span>回复<span>${sanji.parent_employee.employee_name }</span>：
									</c:if>
									${sanji.say_content }
								</div>
								<div class="comment_time">${sanji.time_change }<i
										class="fa fa-commenting-o" pid="${sanji.say_id }" title="回复"></i>
								</div>
							</div>
							<div class="clear"></div>
							<div class="comments none" id="comment_one${sanji.say_id }">
								<textarea id="one_content${sanji.say_id }"></textarea>
								<a href="javascript:void(0)"
									onclick="huifu_say_three_my(${sanji.say_id },${sanji.parent_id },${sanji.employee_id },${employeeSays.pageNumber })">发表</a>
							</div>
						</div>
					</c:forEach>
					<div class="comments none" id="comment_one${sub_say.say_id }">
						<textarea id="one_content${sub_say.say_id }"></textarea>
						<a href="javascript:void(0)"
							onclick="huifu_say_two_my(${sub_say.say_id },${sub_say.employee_id },${employeeSays.pageNumber })">发表</a>
					</div>
					<div class="clear"></div>
				</div>
			</c:forEach>
			<div class="clear"></div>
			<div class="comment none">
				<textarea id="one_content${say.say_id }"></textarea>
				<a href="javascript:void(0)"
					onclick="huifu_say_my(${say.say_id },${employeeSays.pageNumber })">发表</a>
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
				onclick="jump_my_say_page(${employeeSays.pageNumber-1})">上一页</a>
		</c:if>
		<c:if test="${employeeSays.hasPreviousPage!=true}">
			<a href="javascript:void(0)" class="no_page">上一页</a>
		</c:if>
	</div>
	<div class="right">
		<c:if test="${employeeSays.hasNextPage==true}">
			<a href="javascript:void(0)"
				onclick="jump_my_say_page(${employeeSays.pageNumber+1})">下一页</a>
		</c:if>
		<c:if test="${employeeSays.hasNextPage!=true}">
			<a href="javascript:void(0)" class="no_page">下一页</a>
		</c:if>

	</div>
	<div class="clear"></div>
</div>