<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
.bjjs-box table {
  border: solid 1px #ffedd2;
  border-width: 0px 0px 1px 1px;
  width:700px;
  margin:0 auto;
  margin-top:50px;
}

.bjjs-box tr.tr-t td {
  background: #fff2df;
  line-height: 40px;
  font-size: 16px;
  color: #666;
  text-align: center;
  border-width: 1px 1px 0px 0px;
  border: solid 1px #ffedd2;
}

.bjjs-box td {
  text-align: center;
  line-height: 36px;
  border: solid 1px #ffedd2;
  border-width: 1px 1px 0px 0px;
  font-size: 14px;
  padding: 10px;
  color: #666;
}

.bjjs-box td.align-left {
  text-align: left;
}

.bjjs-box td.line28 {
  line-height: 28px;
}

.bjjs-box td.td-bj {
  font-size: 16px;
  color: #ef522b;
  line-height: 24px;
}

.bjjs-box td.td-bj i {
  color: #999;
  display: block;
  font-size: 12px;
  line-height: 18px;
}

.bjjs-box td.td-ts {
  color: #39769c;
  line-height: 20px;
  width: 300px;
  text-align: left;
}

.tab-conBox dd
div h6.tab-title ul li a {
  _position: fixed;
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
	<div class="bjjs-box">
  <table width="100%" border="0" cellspacing="0"
    cellpadding="0">
    <tr class="tr-t">
      <td>班级名称</td>
      <td>班级特色</td>
      <td>内容特色</td>
    </tr>
    <tr>
      <td class="td-bj">${classType.class_name }<i>（${classType.class_beizhu }）</i></td>
      <td class="td-ts">${classType.class_feature }</td>
      <td>${classType.neirong_feature }</td>
    </tr>
  </table>
  <div class="form-group" style="margin-top:15px;">
      <div class="col-xs-11" style="text-align:right;">
      <button type="button" onclick="close_layer()" class="btn btn-primary btn-lg" style="width:100px;">关闭</button>
      </div>
    </div>
</div>
</body>
</html>