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
  <td class="zanwu">还没有添加视频内容</td>
  <!-- <td></td> -->
  <td></td>
  <td></td>
  <td></td>
  <td></td>
</tr>
<!-- <span class="zanwu">还没有添加视频内容</span> -->
</c:if>
<c:if test="${not empty courseVideos }">
	<c:forEach items="${courseVideos }" var="video" varStatus="vs">
		<tr class="info" id="jie${video.video_id}">
                    <td><label class="label label-success btn-success" for="minimal-checkbox-1">第${vs.index+1}节</label></td>
                    <td>${video.video_name }
                    <c:if test="${vs.index>0 }">
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
                    <c:if test="${video.is_jinzhi==1}">
                     <button type="button" onclick="update_is_jinzhi(${video.video_id},0)" class="btn btn-info btn-xs">解除</button>
                     </c:if>
                     <c:if test="${video.is_jinzhi==0}">
                     <button type="button" onclick="update_is_jinzhi(${video.video_id},1)" class="btn btn-default btn-xs">禁止</button>
                     </c:if>
                      <button type="button" onclick="update_jie(${video.video_id})" class="btn btn-warning btn-xs">修改</button>
                      <button type="button" onclick="delete_jie(${video.video_id})" class="btn btn-danger btn-xs">删除</button>
                      <button type="button" onclick="update_jie_video(${video.video_id})" class="btn btn-info btn-xs">修改视频</button>
                    </td>
                    <td></td>
                  </tr>
                  </c:forEach>
</c:if>