<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:forEach items="${list }" var="li">
 <span style="font-weight: 600;display:inline-block;text-align:left;">${fn:substringBefore(li,":" )}</span>&nbsp;:&nbsp;${fn:substringAfter(li,":" )}<br>
</c:forEach>