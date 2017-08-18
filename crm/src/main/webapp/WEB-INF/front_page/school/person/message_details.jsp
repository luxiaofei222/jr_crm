<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h1><a href="javascript:void(0)" style="color: #444" onclick="get_inbox()" >收件箱</a>>收件详情</h1>
<div class="mail_info_top">
	<div class="mail_top1">${jrMessage.message_title }</div>
	<div class="mail_top2">
		来信人：<span>系统</span>
	</div>
	<div class="mail_top3">
		时间：<span><fmt:formatDate type="BOTH" value="${jrMessage.message_time }" /></span>
	</div>
</div>
<div class="mail_info_con">
	<div class="mail_info_con1">
		${jrMessage.message_content }</div>
</div>
<c:if test="${annex_num!=0 }">
<div class="mail_info_bottom">
	<div class="mail_info_bottom1">
		<span style="font-size: 20px;"> <i class="fa fa-paperclip"
			style="color: #06C1AE;"></i>
		</span> 附件(<span>${annex_num }</span>个)
	</div>
	<ul>
	<c:forEach items="${jrMessage.annexes }" var="annex">
		<li class="mail_info_bottom2"><img src="/images/school/front/person/doc.png">
			<div class="info_bottom">
				<span class='info_bottom_one'> ${annex.message_annexes }</span> <span
					class="info_bottom_two"> (${annex.file_length }) </span>
			</div>
			<div class="info_bottom2">
				<span><a style="color:  orange;" href="${annex.annexes_file }">下载</a></span>
			</div></li>
	</c:forEach>
	</ul>
</div>
</c:if>