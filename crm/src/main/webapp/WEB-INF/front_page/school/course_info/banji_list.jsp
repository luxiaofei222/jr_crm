<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${empty classTypes}">
<span style="display:block;margin:0 auto;text-align:center;font-size:25px;color:#06C1AE;margin-top:200px;">暂无班级介绍</span>
</c:if>
<c:if test="${not empty classTypes}">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr class="tr-t">
		<td>班级名称</td>
		<td>班级特色</td>
		<td>内容特色</td>
		<td>播放</td>
	</tr>
	<c:forEach items="${classTypes}" var="class_tyoe">
	<tr>
		<td class="td-bj">${class_tyoe.class_name }<i>（${class_tyoe.class_beizhu }）</i></td>
		<td class="td-ts">${class_tyoe.class_feature }</td>
		<td>${class_tyoe.neirong_feature }</td>
		<td><a href="javascript:void(0);" target="_blank"><img
				src="/images/school/front/courseinfo/st2.gif" width="36" height="50"
				alt="" /></a></td>
	</tr>
	</c:forEach>
</table>
</c:if>