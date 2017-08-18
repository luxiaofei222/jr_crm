<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/school/front/index/flickerplate.css">
<script src="/js/school/front/index/flickerplate.min.js" type="text/javascript"></script>
<script>
	$(function(){
		$('.flicker-example').flicker({
			auto_flick_delay: 3
		});
	})
	//图片跳转
	function to_url(link){
		location.href=link;
	}
</script>
<div class="flicker-example" data-block-text="false">
	<ul>
	<c:forEach items="${getadvers }" var="adver">
		<li onclick="to_url('${adver.adver_link }')" data-background="${adver.adver_pic }"></li>
	</c:forEach>
	</ul>
</div>