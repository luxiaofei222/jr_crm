<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
 <h1>收件箱</h1> 
    <table>
      <tr id="table_title">
        <td width="10%">来信人</td>
        <td width="35%">标题</td>
        <td width="20%">时间</td>
        <td width="25%">附件</td>
        <td width="10%">状态</td>
      </tr>
      <c:if test="${not empty jrMessages.list }">
      <c:forEach items="${jrMessages.list }" var="message">
      <tr>
        <td width="10%">系统</td>
        <td width="35%" style="cursor: pointer;" onclick="get_message_xiangqing(${message.message_id})">${message.message_title }</td>
        <td width="20%"><fmt:formatDate type="BOTH" value="${message.message_time }" /></td>
        <c:if test="${not empty message.annexes }">
        <td width="25%">
        <c:forEach items="${message.annexes }" var="annex">
       	 <p class="fujian">${annex.message_annexes }</p>
       	 </c:forEach> 
        </td>
        </c:if>
        <c:if test="${empty message.annexes }" >
        <td width="25%">没有附件</td>
        </c:if>
        <c:if test="${message.is_read=='是' }">
        <td width="10%" style="cursor: pointer;">已读</td>
        </c:if>
        <c:if test="${message.is_read=='否' }">
        <td width="10%" style="color:#cf0000; cursor: pointer;">未读</td>
        </c:if>
      </tr>
      </c:forEach>
      </c:if>
    </table>
    <div class="clear"></div>
 <c:if test="${ empty jrMessages.list }">
 <p class="without"><img src="/images/school/front/index/user_moren.png"><span>收件箱是空的！</span></p><div class="clear"></div>
 </c:if>
<div class="search_fenye">
	<ul>
		<c:if test="${jrMessages.hasPreviousPage==true}">
			<li class="fenyeda"
				onclick="message_jump_page(${jrMessages.pageNumber-1})"><a
				href="javascript:void(0)"> 上一页 </a></li>
		</c:if>
		<c:forEach items="${jrMessages.navigatePageNumbers }" var="page">
			<c:choose>
				<c:when test="${jrMessages.pageNumber==page}">
					<li class="pageactive"><a href="javascript:void(0)">${page}</a></li>
				</c:when>
				<c:otherwise>
					<li  onclick="message_jump_page(${page})"><a
						href="javascript:void(0)">${page}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${jrMessages.hasNextPage==true}">
			<li onclick="message_jump_page(${jrMessages.pageNumber+1})"
				class="fenyeda"><a href="javascript:void(0)"> 下一页 </a></li>
		</c:if>
	</ul>
</div>
<div class="clear"></div> 