<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:forEach items="${organizations }" var="organ">
<option value="${organ.organization_id }">${organ.organization_name }</option>
<c:forEach items="${organ.organizations_sub }" var="organ_sub">
<option value="${organ_sub.organization_id }">${organ_sub.organization_name }</option>
</c:forEach>
</c:forEach>