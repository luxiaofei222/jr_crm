<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css"/>
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<div class="back_right" style="min-width:900px;">
  <div class="back_r_top">
    <div class="left">用户视频违规下载记录</div>
        <div class="right" id="timer">
</div>
  </div>
  <div class="right_content">
    <div class="operation">
      <div class="opera_left left">
      </div>
      <div class="opera_right right">
      </div>
    </div>
    <div class="content_message">

      <table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
                       <!--  <th></th> -->
                        <th>序号</th>
						<th>用户手机号</th>
						<th>用户邮箱</th>
						<th>下载平台</th>
						<th>下载时间</th>
						<th>用户IP</th>
						<th>视频名称</th>
					</tr>
				</thead>
				<c:if test="${not empty downErrors }">
				<tbody>
				<c:forEach items="${downErrors }" var="down" varStatus="vs">
                    <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
						<td>
                          <label class="label label-success btn-primary" for="minimal-checkbox-1">${vs.index+1 }</label>
                        </td>
						<td>${down.user.user_phone }</td>
						<td>${down.user.user_mail }
						</td>
						<td>${down.down_type }</td>
						<td><fmt:formatDate type="both" value="${down.down_time }" /></td>
						<td>${down.user_ip }</td>
                        <td>${down.courseVideo.video_name }</td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
			</table>
			    <c:if test="${ empty downErrors }">
				<p class="zanwu">暂无记录信息</p>
				</c:if>
    </div>
  </div>  
</div>