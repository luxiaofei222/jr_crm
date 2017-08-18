<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h2>公司文件</h2>
<ul>
<c:forEach items="${oaFiles.list }" var="file">
	<li onclick="xiazai_time(${file.file_id})">
	  <a href="${file.file_addr }" download="${file.file_name }.${file.houzhui}">
	    <img src="/images/employee/file.png" class="left" />
	    <span title="${file.file_name }" class="left" >${file.file_name }</span>
	    <img src="/images/employee/down.png" class="down left"  />
	  </a>
	</li>
	<div class="clear"></div>
</c:forEach>
</ul>
<div class="clear"></div>
<div class="pages right">
  <c:if test="${oaFiles.hasPreviousPage==true}">
    <a href="javascript:void(0)" onclick="get_file_page(${oaFiles.pageNumber-1})" title="上一页" class="left">
      <i class="fa fa-fast-backward"></i>
    </a>
  </c:if>
  <c:if test="${oaFiles.hasPreviousPage!=true}">
    <a href="javascript:void(0)" class="left huise" title="上一页" class="left">
      <i class="fa fa-fast-backward"></i>
    </a>
  </c:if>
  <a class="left"><span>${oaFiles.pageNumber }</span>&nbsp;/&nbsp;${oaFiles.pages }</a>
  <c:if test="${oaFiles.hasNextPage==true}">
    <a href="javascript:void(0)" onclick="get_file_page(${oaFiles.pageNumber+1})" title="下一页" class="left">
      <i class="fa fa-fast-forward"></i>
    </a>
  </c:if>
  <c:if test="${oaFiles.hasNextPage!=true}">
    <a href="javascript:void(0)" class="left huise" title="下一页" class="left">
      <i class="fa fa-fast-forward"></i>
    </a>
  </c:if>
  <div class="clear"></div>
</div>
<div class="clear"></div>