<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
//banner上的课程分类
$(function(){
	$(".kc_name").mouseover(function(){
		$(this).parent().find(".item_totle").stop(false,true).slideDown(500);
	})
	$(".fenlei").mouseleave(function(){
		$(this).find(".item_totle").stop(false,true).slideUp(500);
	})
		$('.item_totle').hover(function(){
			// $(this).addClass('.xuanzhong');
			$(this).find('.item_info').show();
		},function(){
			// $(this).removeClass('.xuanzhong');
			$(this).find('.item_info').hide();
		});
	});
//跳转检索页
function get_retrieval(course_id){
	location.href="/retrieval/get_retrieval_main.html?course_id="+course_id;
}
</script>
<li class="kc_name"><i class="icon"></i><span>全部课程</span></li>
<c:forEach items="${courseMenus }" var="cour">
	<li class="item_totle">
		<div class="item">
			<em>${cour.course_name }</em><a></a>
		</div>
		<div class="item_info">
			<ul class='item_info_kc'>
				<c:forEach items="${cour.sub_list }" var="cou">
					<li onclick="get_retrieval(${cou.course_id })"><a href="javascript:void(0);" >${cou.course_name } </a></li>
				</c:forEach>
			</ul>
		</div>
	</li>
</c:forEach>