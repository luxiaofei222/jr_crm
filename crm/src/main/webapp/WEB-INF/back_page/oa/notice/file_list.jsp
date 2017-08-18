<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公告列表</title>
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/page/pages.css" />
<link rel="stylesheet" type="text/css" href="/css/oa/index/finance.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
.select2-chosen {
	color: white !important;
}

.form-group .right_wz {
	text-align: right;
	line-height: 35px;
}

.form-control {
	height: 35px;
}

.form-group {
	margin-bottom: 10xp !important;
}

.form-control:focus {
	border: 2px solid #28A4F4;
}

.shaixuan {
	width: 100px;
	height: 35px;
	line-height: 35px;
	background: #18d1bb;
	border: none;
	color: white;
	float: left;
	border-radius: 5px;
	margin-left: 40px;
}

.all_notice,table.notice {
	width: 1000px;
	margin: 20px auto;
	position: relative;
}
table.notice thead tr {
   width: 1000px;
   height: 35px;
   line-height: 35px;
   color: #fff;
   font-size: 16px;
   text-align: center;
   background-color: #18d1bb;
}
table.notice thead tr th {
   text-align: center;
}
table.notice tbody td {
   border: #f5f5f5 solid 1px;
   text-align: center;
   height: 35px;
   line-height: 35px;
}
table.notice tbody td.notice_title {
   max-width:400px;
   overflow: hidden;
   text-overflow: ellipsis;
   white-space: nowrap;
}
table.notice tbody td a {
   display: inline-block;
   width: 45px;
   height: 25px;
   text-align: center;
   line-height: 25px;
   color: #fff;
   background-color: #18d1bb;
   margin-left: 10px;
   border-radius:3px;
   font-size: 14px;
}
.all_notice .pages {
   position: absolute;
   bottom: -55px;
   right: -20px;
   width: 1000px;
}
</style>
</head>
<body>
	<div class="back_right">
		<div class="back_r_top">
			<div class="left">公司公告</div>
			<div class="right" id="timer"></div>
		</div>
		<div class="right_content">
			<form enctype="multipart/form-data" id="myform"
				class="form-horizontal">
				<div class="form-group clearfix"
					style="padding-top: 10px; margin-bottom: 5px;">
					<label class="col-xs-1 right_wz">开始日期：</label>
					<div class="col-xs-3">
						<input class="layui-input" placeholder="选择开始日期" name="start_time"
							id="start_time">
					</div>
					<label class="col-xs-1 right_wz">结束日期：</label>
					<div class="col-xs-3">
						<input class="layui-input" placeholder="选择结束日期" name="end_time"
							id="end_time">
					</div>
					<div class="col-xs-3">
						<button class="shaixuan" onclick="chaxun_file_list(this)">筛选</button>
					</div>
				</div>
			</form>
			<div class="form-group clearfix"
					style="padding-top: 10px; margin-bottom: 5px;">
					<div class="col-xs-1">
						<button onclick="to_upload_file()" class="shaixuan">上传文件</button>
					</div>
				</div>
			<div class="all_notice">
			    <table class="notice">
				<thead>
					<tr>
						<th>序号</th>
						<th>文件名称</th>
						<th>上传时间</th>
						<th>下载次数</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${empty oaFiles.list }">
				<span style="position: absolute;top: 50px;left: 444px;color:orange;">没有相关文件信息</span>
				</c:if>
				<c:forEach items="${oaFiles.list }" var="file" varStatus="vs">
					<tr>
						<td>${vs.index+1+oaFiles.begin }</td>
						<td class="notice_title" title="${file.file_name }">${file.file_name }</td>
						<td><fmt:formatDate value="${file.file_time }" /></td>
						<td>${file.file_down_time }</td>
						<td><a href="${file.file_addr }" download="${file.file_name }.${file.houzhui}">下载</a><a
							href="javascript:void(0)" onclick="delete_file(${file.file_id},${oaFiles.pageNumber })">删除</a></td>
					</tr>
					</c:forEach>
				</tbody>
				<c:if test="${not empty oaFiles.list }">
				<div class="pages">
				<ul class="pagination pull-right">
					<c:if test="${oaFiles.hasPreviousPage==true}">
						<li class="previous"
							onclick="file_jump_page(${oaFiles.pageNumber-1},'${start_time }','${end_time }')"><a
							href="#fakelink" class="fui-arrow-left"></a></li>
					</c:if>
					<c:forEach items="${oaFiles.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${oaFiles.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="file_jump_page(${page},'${start_time }','${end_time }')"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${oaFiles.hasNextPage==true}">
					<li class="next"><a href="#fakelink" onclick="file_jump_page(${oaFiles.pageNumber+1},'${start_time }','${end_time }')"  class="fui-arrow-right"></a></li>
					</c:if>
				</ul>
			</div>
			</c:if>
			</table>
			</div>
		</div>

	</div>
</body>
<script>
$(function(){
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  var start = {
			festival: true
		  };
		 document.getElementById('end_time').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  }
		  document.getElementById('start_time').onclick = function(){
			    start.elem = this;
			    laydate(start);
			  }
		});
})
//上传文件
function to_upload_file(){
	layer.open({
		  type: 2,
		  title: ['上传文件'],
		  area: ['700px', '400px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/notice/to_add_file.jr"
		  });
}
//跳转分页
function file_jump_page(page,start_time,end_time){
	jiazaidonghua();
	$.post("/notice/get_file_list.jr",{
		'pageNumber' : page,
		'start_time':start_time,
		'end_time':end_time
	},function(data){
		$("#conten_list").html(data);
	})
}

//筛选条件
function chaxun_file_list(obj){
	$(obj).attr({"disabled":"disabled"});
	$(obj).html("查询中");
	$("#myform").ajaxSubmit({
		type : 'POST',
		url : "/notice/get_file_list.jr",
		data:{
			'pageNumber':1
		},
		success : function(data) {
			$("#conten_list").html(data);
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			tanchuang('查询失败');
		}
	});
}
//删除文件
function delete_file(file_id,page){
	layer.confirm("提示：您好，确定要删除吗？",function(){
		layer.closeAll('dialog');
		$.post("/notice/delete_file.jr",{
			'file_id':file_id
		},function(data){
			if(data==1){
				tanchuang("删除成功！");
				file_jump_page(page);
			}else{
				tanchuang("删除失败！");
			}
		});
		})
}
</script>
</html>