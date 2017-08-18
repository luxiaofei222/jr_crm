<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<c:if test="${empty courseVideos }">
      <div style="color: orange;text-align:center;margin:0 auto">暂无课程</div>
      </c:if>
      <c:if test="${not empty courseVideos }">
      <c:forEach items="${courseVideos }" varStatus="vs" var="video">
      <div class="form-group">
      <c:if test="${vs.index==0 }">
            <label class="col-xs-3 control-label right_wz">课程列表：</label>
       </c:if>
        <c:if test="${vs.index!=0 }">
            <label class="col-xs-3 control-label right_wz"></label>
       </c:if>
            <div class="col-xs-5">
               <c:if test="${video.is_jiaru_course==false }">
                  <label class="checkbox checkbox-inline" for="checkbox${video.video_id }">
                        <input type="checkbox" data-toggle="checkbox" value="${video.video_id }" name="video_id" id="checkbox${video.video_id }" class="checkbox" required>
                  </label>
                </c:if>
              <label class="control-label">${video.video_name }</label> 
            </div>
            <div class="col-xs-2">
            <label style="color: orange;" class="control-label">￥${video.video_money_now }</label> 
            </div>
             <div class="col-xs-2">
           <c:if test="${video.is_jiaru_course==true }">
                  <label style="color: orange;" class="control-label">已加入</label>
                </c:if> 
            </div>
            </div>
      </c:forEach>
      </c:if>