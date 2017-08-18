<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>学历报名信息信息查看</title>
<link rel="stylesheet" href="/css/school/back/common/reset.css" />
<style>
  h3 {
     text-align: center;
     font-size: 16px;
     color: #06c1ae;
      margin-bottom: 30px;
  }
  table {
	  margin:0 auto;
	  width:100%;
	  border:#ccc solid 1px;
	  color:#666;
	  font-size:16px;
	  }
  tr td {
	  line-height:35px;
	  text-align:center;
	  }
  strong {
	  font-weight:bold;
	  }
  
</style>
</head>
<body>
<div style="padding:20px;">
  <h3>用户报名信息</h3>
  <table border="1">
  <tr>
    <td><strong>地点</strong></td>
    <td>${xueLiBaoMing.baoming_diqu }</td>
    <td><strong>学习中心</strong></td>
    <td colspan="3">${xueLiBaoMing.xuexi_zhongxin }</td>
    <td><strong>报名时间</strong></td>
    <td><fmt:formatDate value="${xueLiBaoMing.baoming_time }"/></td>
  </tr>
  <tr>
    <td><strong>报名IP</strong></td>
    <td>${xueLiBaoMing.user_ip }</td>
    <td><strong>姓名</strong></td>
    <td>${xueLiBaoMing.user_name }</td>
    <td><strong>手机号</strong></td>
    <td>${xueLiBaoMing.user_phone }</td>
    <td><strong>身份证号</strong></td>
    <td>${xueLiBaoMing.card_number }</td>
  </tr>
  <tr>
    <td><strong>院校</strong></td>
    <td colspan="3">${xueLiBaoMing.yuanxiao }</td>
    <td><strong>专业</strong></td>
    <td colspan="3">${xueLiBaoMing.zhuanye }</td>
  </tr>
  <tr>
    <td><strong>层次</strong></td>
    <td>${xueLiBaoMing.cnegci }</td>
    <td><strong>电子邮箱</strong></td>
    <td>${xueLiBaoMing.user_mail }</td>
    <td><strong>QQ号</strong></td>
    <td>${xueLiBaoMing.qq }</td>
    <td><strong>微信号</strong></td>
    <td>${xueLiBaoMing.weixin }</td>
  </tr>
   <tr>
    <td><strong>报名地点</strong></td>
    <td colspan="7">${xueLiBaoMing.ip_city }</td>
  </tr>
</table>
</div>
</body>
</html>
