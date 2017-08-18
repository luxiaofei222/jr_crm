<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${empty courseVideos }">
<tr class="zanwu">
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <td class="zanwu">还没有添加章节内容</td>
  <!-- <td></td> -->
  <td></td>
  <td></td>
  <td></td>
  <td></td>
</tr>
<!-- <span class="zanwu">还没有添加章节内容</span> -->
</c:if>
<c:if test="${not empty courseVideos }">
	<c:forEach items="${courseVideos }" var="video" varStatus="vs">
		<tr class="success" id="jie${video.video_id}">
                    <td><label class="label label-success btn-info" for="minimal-checkbox-1">第${vs.index+1}章</label></td>
                    <td>${video.video_name }
                    <c:if test="${vs.index!=0 }">
                   	&nbsp;&nbsp;<a href="javascript:void(0)" onclick="xiangshang(${video.video_id})" ><i class="fa fa-arrow-up"></i></a>
                    </c:if>
                    </td>
                    <td>--</td>
                    <!-- <td>--</td> -->
                    <td>--</td>
                    <td>--</td>
                    <td><fmt:formatDate type="both" value="${video.video_time }" /></td>
                    <td>--</td>
                    <td>--</td>
                    <td>
                      <button type="button" onclick="to_add_jie(${video.video_id})" class="btn btn-primary btn-xs">添加</button>
                      <button type="button" onclick="update_zhang(${video.video_id})" class="btn btn-warning btn-xs">修改</button>
                      <button type="button" onclick="delete_zhang(${video.video_id})" class="btn btn-danger btn-xs">删除</button>
                    </td>
                    <td><button type="button" onclick="zhankai_jie(${video.video_id})" class="btn btn-inverse btn-xs zhang_down"><i id="tubiao${video.video_id}" class="fa fa-plus"></i></button></td>
                  </tr>
                  </c:forEach>
</c:if>