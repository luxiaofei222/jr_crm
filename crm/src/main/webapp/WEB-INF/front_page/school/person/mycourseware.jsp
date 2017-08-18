<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
     <h1>我的课件</h1>
    <table>
      <tr id="table_title">
        <td width="70%">课件名称</td>
        <td width="30%">下载日期</td>
      </tr>
      <c:if test="${not empty myCourseWares.list }">
      <c:forEach items="${myCourseWares.list }" var="myware">
      <tr>
        <td width="70%">${myware.courseWare.courseware_name }&nbsp;<a href="javascript:void(0)" class="down">下载</a></td>
        <td width="30%"><fmt:formatDate type="BOTH" value="${myware.add_time }" /></td>
      </tr>
      </c:forEach>
      </c:if>
    </table> 
        <div class="clear"></div>
     <c:if test="${empty myCourseWares.list }">
     <p class="without"><img src="/images/school/front/index/user_moren.png"><span>您还没有课件！</span></p><div class="clear"></div>
     </c:if> 
<div class="search_fenye">
	<ul>
		<c:if test="${myCourseWares.hasPreviousPage==true}">
			<li class="fenyeda"
				onclick="ware_jump_page(${myCourseWares.pageNumber-1})"><a
				href="javascript:void(0)"> 上一页 </a></li>
		</c:if>
		<c:forEach items="${myCourseWares.navigatePageNumbers }" var="page">
			<c:choose>
				<c:when test="${myCourseWares.pageNumber==page}">
					<li class="pageactive"><a
						href="javascript:void(0)">${page}</a></li>
				</c:when>
				<c:otherwise>
					<li  onclick="ware_jump_page(${page})"><a href="javascript:void(0)">${page}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${myCourseWares.hasNextPage==true}">
			<li onclick="ware_jump_page(${myCourseWares.pageNumber+1})"
				class="fenyeda"><a href="javascript:void(0)"> 下一页 </a></li>
		</c:if>
	</ul>
</div>
<div class="clear"></div> 