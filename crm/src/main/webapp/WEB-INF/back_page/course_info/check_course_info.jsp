<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
<title>查看资讯详情</title>
<style>
.zixun_content {
	width:1000px;
	margin:0 auto;
	} 
.zixun_content .zixun_title h3 {
	text-align:center;
	color:#222;
	font-size:20px;
	margin:30px auto;
	}
.zixun_content .zixun_info {
	width:550px;
	text-align:center;
	margin:0 auto;
	height:25px;
	}
.zixun_content .zixun_info ul li {
	float:left;
	margin-right:70px;
	font-size:14px;
	color:#999;
	}
.zixun_content .zixun_info ul li:last-child {
	margin-right:0px;
	}
.zixun_content .daodu {
	width:968px;
	min-height:60px;
	border:#06c1ae dashed 1px;
	padding:15px;
	background-color:#fafafa;
	margin-top:30px;
	}
.zixun_content .daodu p {
	line-height:30px;
	color:#666;
	font-size:14px;
	}
.zixun_content .zixun_contents p {
	margin-top:15px;
	text-indent:2em;
	font-size:14px;
	color:#444;
	line-height:30px;
	}
</style>
</head>
<body>
<div class="zixun_content">
      <div class="zixun_title">
        <h3>${courseInfo.info_title }</h3>
      </div>
      <div class="zixun_info">
        <ul>
          <li>来源：${courseInfo.info_laiyuan }</li>
          <li>责任编辑：${courseInfo.info_user }</li>
          <li>时间：<fmt:formatDate pattern="yyyy年MM月dd日"
								value="${courseInfo.info_time }" /></li>
        </ul>
      </div>
      <div class="clear"></div>
      <div class="daodu">
        <p><b>导读：</b>${courseInfo.info_zhaiyao }</p>
      </div>
      <div class="zixun_contents">
     <!-- 资讯内容 -->
     ${courseInfo.info_content }
      </div>
    </div>
</body>
</html>