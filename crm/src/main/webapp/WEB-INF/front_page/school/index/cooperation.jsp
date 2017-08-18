<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/js/school/front/index/jquery-v1.10.2.min.js" type="text/javascript"></script>
<script src="/js/school/front/index/coopercation.js"
	type="text/javascript"></script>
<div class='link_dt' id="link_dt">
	<a href="javascript:void(0);" class="btn_left"></a> <a
		href="javascript:void(0);" class="btn_right"></a>
	<div class="wrap">
		<ul class="wrap_link">
			<c:forEach items="${cooperations }" var="cooper">
				<li><a href="javascript:void(0);"> <img
						src="${cooper.cooper_pic }" onclick="kefu53()"/>
				</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
<script type="text/javascript">
function kefu53(){
	window.open('http://tb.53kf.com/code/client/10147174/1]');
}
</script>