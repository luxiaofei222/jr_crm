<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<dl>
	<dt>等级:</dt>
	<dd class="dengji">
		<a href="javascript:void(0)" pid="0" onclick="get_dengji_quanbu_neironglist(${course_id },this)" class="green1">全部</a>
		<c:if test="${not empty dictionaries }">
		<c:forEach items="${dictionaries }" var="dic">
			<a href="javascript:void(0)" pid="${dic.dictionary_id }" onclick="get_erji_dengji_biaogelist(${course_id},${dic.dictionary_id },this)" >${dic.dictionary_name }</a>
		</c:forEach>
		</c:if>
	</dd>
</dl>
<dl>
	<dt>班型:</dt>
	<dd class="banxing">
		<a href="javascript:void(0)" onclick="get_banxing_quanbu_neironglist(${course_id },this)" pid="0" class="green1">全部</a>
		<c:forEach items="${dictionaries_banxing }" var="dic">
		 <a href="javascript:void(0)" pid="${dic.dictionary_name }" onclick="get_erji_banxing_biaogelist(${course_id},'${dic.dictionary_name }',this)">${dic.dictionary_name }</a>
		 </c:forEach> 
	</dd>
</dl>
