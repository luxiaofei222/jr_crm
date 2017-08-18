<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看院校简章</title>
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/dist/summernote.min.js"></script>
<script type="text/javascript" src="/dist/lang/summernote-zh-CN.min.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
  body{
  padding: 20px;
  }
  h3 {
  text-align: center;
  margin-bottom: 20px;
  font-weight: bold;
  }
  hr {
  width: 100%;
  border: dashed #ffa500 1px;
  }
  .content {
  margin-bottom: 30px;
  position: relative;
  }
  .content h4 {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #06c1ae;
  line-height: 30px;
  }
  .content strong {
  font-weight: bold;
  margin: 20px 0px;
  display: inline-block;
  }
  .content input[type="button"] {
  position: absolute;
  top: 0px;
  right: 0px;
  border: none;
  border-radius:3px;
  width: 60px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  color: #fff;
  background-color: orange;
  }
  span.zanwu {
  width: 120px;
  color: orange;
  margin: 20px auto;
  display: block;
  }
</style>
<script>
function delete_jianzhang(jianzhang_id){
	layer.confirm("提示：您好，确定要删除吗？",function(){
		layer.closeAll('dialog');
		$.post("/zs_jianzhang/delete_jianzhang.jr",{
			'jianzhang_id':jianzhang_id
		},function(data){
			if(data==1){
				location.reload();
			}else{
				tanchuang("删除失败！");
			}
		});
		})
}
</script>
</head>
<body>
<h3 class="title">${ckZhaoSheng.xuexiao_name }${ckZhaoSheng.xuexiao_type }招生简章</h3>
<hr />
<c:if test="${empty jianzhangs }">
	<span style="display:block;text-align:center;font-size:19px;letter-spacing:5px;color:rgb(26, 188, 156);margin-top:150px;">暂无${ckZhaoSheng.xuexiao_name }招生简章内容</span>
</c:if>
<c:forEach items="${jianzhangs }" var="jianzhang">
<div class="content">
  <h4>${jianzhang.title }</h4>
  <div class="con_text">
    ${jianzhang.content }
  </div>
  <input type="button" onclick="delete_jianzhang(${jianzhang.jianzhang_id})" value="删除" />
</div>
</c:forEach>
</body>
</html>