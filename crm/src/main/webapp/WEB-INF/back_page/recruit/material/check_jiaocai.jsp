<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>评论管理</title>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/reset.css" />
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<style>
body {
	padding: 20px;
}

.book_ul {
	margin-left: 280px;
}

.book_ul ul li {
	float: left;
	line-height: 25px;
	color: #313131;
	font-size: 18px;
	margin-right: 30px;
	margin-bottom: 20px;
}
</style>
</head>
<script>
function delete_material(material_id){
	$.post("/material/delete_material.jr",{
		'material_id':material_id
	},function(data){
		if(data==1){
			location.reload();
		}
	})
}
</script>
<body>
	<div class="book_ul">
		<ul>
			<li>入学批次：${material.ruxue_pici }</li>
			<li>层次：${material.cengci }</li>
			<li>专业：${material.zhuanye_name }</li>
		</ul>
	</div>
	<div class="book">
		<table class="table table-hover">
			<thead>
				<tr class="tr_bgcolor warning">
					<th>编号</th>
					<th>课程名称</th>
					<th>电子辅导材料</th>
					<th>课程类别</th>
					<th>书名</th>
					<th>作者</th>
					<th>选修类型</th>
					<th>商品分类</th>
					<th>课程属性</th>
					<th>课程学分</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${materials }" var="mate" varStatus="vs">
					<tr>
						<td><label class="label label-success btn-primary"
							for="minimal-checkbox-1">${vs.index+1 }</label></td>
						<td>${mate.kecheng_name}</td>
						<td>
							<c:if test="${mate.is_dianzi==0 }">
								包含
							</c:if>
							<c:if test="${mate.is_dianzi==1 }">
								不包含
							</c:if>
						</td>
						<td>${mate.kecheng_leibie }</td>
						<td>${mate.shuming }</td>
						<td>${mate.zuozhe }</td>
						<td>${mate.xuanxiu_leixing }</td>
						<td>${mate.shangpin_fenlei }</td>
						<td>${mate.kechengshuxing }</td>
						<td>${mate.kecheng_xuefen }</td>
						<td>${mate.beizhu }</td>
						<td><button type="button" onclick="delete_material(${mate.material_id})" class="btn btn-danger btn-xs">删除</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
