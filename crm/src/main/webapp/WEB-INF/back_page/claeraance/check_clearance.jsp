<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>查看班型介绍</title>
<style type="text/css">
.tg_wrapper {
  overflow: hidden;
  border-right:1px solid #ffedd2;
  padding:20px;
}
.tg_wrapper .oneul{
  border-top:1px solid #ffedd2;
  float:left;
  width:95px;
  background: #fff8ef;
}
.tg_wrapper .twoul{
    border-top:1px solid #ffedd2;
  float:left;
}
.tg_wrapper .threeul{
    border-top:1px solid #ffedd2;
  float:left;
}
.tg_wrapper .oneul ul {
  color: #666;
  font-size: 16px;
  width: 95px;
  text-align: center;
}
.tg_wrapper .twoul ul {
  color: #666;
  font-size: 16px;
  width: 328px;
  text-align: center;
}
.tg_wrapper .threeul ul {
  color: #666;
  font-size: 16px;
  width: 326px;
  text-align: center;
}
.tg_wrapper .oneul ul li{
  border-bottom:1px solid #ffedd2;
  border-right:1px solid #ffedd2;
  text-align:center;
  width:95px;

}
.tg_wrapper .twoul ul li{
  border-bottom:1px solid #ffedd2;
  border-right:1px solid #ffedd2;
  text-align:center;
  width:328px;
}
.tg_wrapper .threeul ul li{
  border-bottom:1px solid #ffedd2;
  border-right:1px solid #ffedd2;
  text-align:center;
  width:328px;
}
.tg_wrapper .oneg{
  height:45px;
  line-height:45px;
}
.tg_wrapper .twog{
  height:45px;
  line-height:45px;
}
.tg_wrapper .threeg{
  height:45px;
  line-height:45px;
}
.tg_wrapper .fourg{
  height:240px;
  line-height:20px;
}
.tg_wrapper .fiveg{
  height:45px;
  line-height:45px;
}
.tg_wrapper .sixg{
  height:60px;
  line-height:60px;
}
.yuanjia{
	text-decoration:line-through;
}
.sixgg{
  height:315px;
  line-height:315px;
}
.get_height div{
	margin-top:10px;
}
.get_height div:nth-child(1){
	margin-top:0px;
}
</style>
</head>
<script>
function close_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}
</script>
<body>
<ul class="tg_wrapper">
  <li class="oneul">
    <ul>
      <li class="oneg">套餐</div>
      <li class="twog">原价<br /></li>
      <li class="twog">现价<br /></li>
      <li class="threeg">包含<br /></li>
      <li class="gain_height">所有课程<br /></li>
      <li class="fourg"><br /> 特色</li>
      <li class="fiveg">截止日期</li>
      <li class="fiveg">保障</li>
    </ul>
  </li>
  <li  class="twoul">
    <ul>
      <li class="oneg" style="font-size:22px;">${clearance.clearance_name }</li>    
      <li class="twog yuanjia">
        <p>
          <font>${clearance.clearance_price }</font>
        </p>
      </li>
       <li class="twog">
        <p>
          <font style="color: #FF0000">${clearance.clearance_price }</font>
        </p>
      </li>
      <li class="threeg">${clearance.clearrance_baohan }<br /></li>
      <li class="get_height">
      <c:if test="${empty clearanceVideos }">
      		<div style="color: red;">还没添加课程</div>
      </c:if>
      <c:forEach items="${clearanceVideos }" var="video">
   		  <div>${video.video_name } <button type="button" onclick="jiechu_video(${video.clear_video_id})" class='btn btn-danger'>解除</button></div>
   		</c:forEach>  
   	</li>
   	<script>
   	function jiechu_video(clear_video_id){
   		$.post("/back_clearance/delete_clearance_video.jr",{
   			'clear_video_id':clear_video_id
   		},function(data){
   			if(data==1){
   				location.reload();
   			}else{
   				layer.msg("解除失败！");
   			}
   		})
   	}
   	</script>
      <li class="fourg">
      <c:forEach items="${clearance.tese }" var="ts">
      <p>${ts }</p>
      </c:forEach>
      </li>
       <li class="fiveg"><fmt:formatDate value="${clearance.daoqi_time }"/></li>
      <li class="fiveg">${clearance.clearrance_baozhang }</li>
    </ul>
  </li>
</ul>
</body>
<script>
$(function(){
	var height=0;
 height=$(".get_height").height();
 $(".gain_height").css("height",height);
})
</script>
</html>