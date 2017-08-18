<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
  .check_send {
  color: #313131;
  }
  .check_send .title {
  min-height:100px; 
  background-color: #eff5fb;
  padding: 10px;
  }
  .check_send .title h3 {
  font-size: 16px;
  font-family: "黑体";
  line-height: 35px;
  }
  .check_send .title p {
  font-size: 14px;
  color: #999;
  line-height: 30px;
  }
  .check_send .title p span {
  color: #06C1AE;
  margin-right: 15px;
  }
  
  .check_send .content {
  margin: 20px 0px;
  min-height:100px; 
  padding: 10px;
  text-indent: 2em;
  }
  .check_send .fujian {
  border: #eff5fb solid 1px;
  min-height: 120px;
  }
  .check_send .fujian .fujian_title {
  font-size: 14px;
  font-weight: bold;
  display: block;
  height: 40px;
  line-height: 40px;
  background-color: #eff5fb;
  }
  .check_send .fujian .fujian_title i {
  margin-left: 10px;
  color: #06c1ae;
  font-size: 18px;
  margin-right: 10px;
  font-weight: bold;
  }
  .check_send .fujian ul li {
  font-size: 14px;
  }
  .check_send .fujian ul li img,.check_send .fujian ul li div {
  float: left;
  margin-top: 15px;
  margin-left: 25px;
  line-height: 49px;
  }
  .check_send .fujian .mail_title:hover {
  cursor: pointer;
  text-decoration: underline;;
  }
  .check_send .fujian .mail_down {
  color: #06c1ae;
  font-weight: bold;
  cursor: pointer;
  }
  .check_send .fujian .mail_size {
  color: #999;
  margin-left: 5px;
  }
</style>
<div style="padding: 20px;">
  <div class="check_send">
    <div class="title">
      <h3>信用管家消费提醒</h3>
      <c:if test="${jrMessage.send_type=='前台' }">
      <p><span>发件人：</span>${jrMessage.user_nickname }</p> 
      <p><span>时&nbsp;&nbsp;&nbsp;间：</span><fmt:formatDate  value="${jrMessage.message_time }" /></p> 
      <p><span>收件人：</span>京人教育</p> 
      </c:if>
       <c:if test="${jrMessage.send_type=='后台' }">
      <p><span>发件人：</span>京人教育</p> 
      <p><span>时&nbsp;&nbsp;&nbsp;间：</span><fmt:formatDate  value="${jrMessage.message_time }" /></p> 
      <p><span>收件人：</span>${jrMessage.user_nickname }</p> 
      </c:if>
    </div>
    <div class="content">
      <p>${jrMessage.message_content }</p>
    </div>
    <c:if test="${annexes_number>0 }">
    <div class="fujian">
      <span class="fujian_title"><i class="fa fa-paperclip"></i>附件（${annexes_number }个）</span> 
      <ul>
      <c:forEach items="${annexes }" var="anne">
        <li class="">
            <img src="/images/school/front/person/doc.png">
            <div class="mail">
              <span class="mail_title">${anne.message_annexes }</span>
              <span class="mail_size">(${anne.file_length })</span>
            </div>
            <div class="mail_down"><span><a href="${anne.annexes_file }" download="${anne.message_annexes }${anne.houzhui}">下载</a></span></div>
          </li>
         </c:forEach>
      </ul> 
    </div>
    </c:if>
  </div>	
</div>    