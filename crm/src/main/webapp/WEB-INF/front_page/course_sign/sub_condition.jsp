<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
$(".level .levels").click(function(){
	  $(this).next(".pro_message").slideToggle(500);
	  })
</script>

<c:if test="${empty quchongconditions }">
<span style="color: orange;">暂无条件</span>
</c:if>
<c:forEach items="${quchongconditions }" var="quchong">
<span class="levels"><i class="fa fa-envira"></i>
<c:if test="${quchong.course_id==20 }">
						${quchong.university.university_name }-${quchong.baokao_cengci }
</c:if>
<c:if test="${quchong.course_id==19 }">
						${quchong.chengkao_kemu }-${quchong.baokao_cengci }
</c:if>
${quchong.dictionary.dictionary_name }</span>
<div class="pro_message">
	<div class="pro_title">请选择适合的申报条件</div>
	<div class="pro_info">
		<ul>
		<c:forEach items="${quchong.sub_conditionlist }" var="sub">
			<li><input type="radio" onclick="get_check_dition(this)" value="${sub.entrycondition_id }" name="radio" /><label>${sub.entrycondition_content }</label></li>
		   <c:if test="${not empty sub.xiangguang_info }">
            <div class="pro_info xinxi">
              <p><em>相关信息:</em>${sub.xiangguang_info }</p>
            </div>
            </c:if>
		</c:forEach>
		</ul>
	</div>
</div>
</c:forEach>