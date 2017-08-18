<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/js/school/front/index/scroll.js"></script>
<script type="text/javascript">
//新闻动态滚动
$(document).ready(function(){ 
	$('.news_myscroll').myScroll({
		speed: 60, //数值越大，速度越慢
		rowHeight: 26 //li的高度
	});
});
</script>
 <c:forEach items="${scNews }" var="scnew">
  <li><i></i><a href="/sc_news/get_news_detail.html?news_id=${scnew.news_id }" title="${scnew.news_title }" target="_blank">${scnew.news_title }</a></li>
 </c:forEach> 
			        
