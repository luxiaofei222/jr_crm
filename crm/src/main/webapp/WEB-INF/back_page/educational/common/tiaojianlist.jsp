<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<label class="col-xs-2 control-label">报名条件：</label>
<div class="col-xs-10">
	<c:forEach items="${subconditions }" var="condition" varStatus="vs">
		<div class="col-xs-12">
			<label style="float: left; display: inline-block;text-align:left;" class="radio"
				for="radio4b${condition.entrycondition_id }">
				<input type="radio" name="optionsRadios" data-toggle="radio"
					value="${condition.entrycondition_id }" onchange="get_yuancheng_zhuanye(${condition.course_id },'${condition.baokao_cengci }',${condition.university_id })"
					id="radio4b${condition.entrycondition_id }" required >${condition.entrycondition_content }
			</label>
			<c:if test="${condition.course_id==20 }">
				<div style="float: left; display: inline-block;margin-left:10px;">报考层次：</div>
				<div style="float: left; display: inline-block;margin-left:3px;">${condition.baokao_cengci }
				</div>
			</c:if>
			<c:if test="${condition.course_id==19}">
				<div style="float: left; display: inline-block;margin-left:10px;">报考科目：</div>
				<div style="float: left; display: inline-block;margin-left:3px;">${condition.chengkao_kemu }
				</div>
			</c:if>
		</div>
	</c:forEach>
</div>