<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <div class="title">
              <img src="/images/school/front/course_video/jingpin.png" />
              <span>精品课程推荐</span>
            </div>
            <c:forEach items="${courseVideos }" varStatus="vs" var="cour">
            <div class="clear"></div>
            <div class="r-message">
              <a href="/sc_coursevideo/get_course_video_player.html?video_id=${cour.video_id }"><img src="${cour.video_pic }" width="235" height="135" /></a>
              <p><a href="/sc_coursevideo/get_course_video_player.html?video_id=${cour.video_id }">${cour.video_name }</a></p>
              <div class="price">
                <span class="left">现价：<b style="color:#f94c4c;">￥<!-- 0.00 --> ${cour.video_money_now }</b></span>
                <span class="right">${cour.keshi_number }课时</span>
              </div>
              <div class="clear"></div> 
              <div class="discuss">
                <span class="left"><i class="fa fa-user"></i>${cour.comment_number}条评论</span>
                <span class="right"><i class="fa fa-play-circle-o"></i>${cour.play_times }次</span>  
              </div> 
            </div>
            </c:forEach>
            <div class="clear"></div>