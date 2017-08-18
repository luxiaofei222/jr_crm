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

.shaixuan:hover {
	background-color: #2e9e91;
}

.notice_delete {
	float: right;
	width: 100px;
	height: 35px;
	line-height: 35px;
	background: #18d1bb;
	border: none;
	color: white;
	border-radius: 5px;
	margin-left: 40px;
}

.notice_delete:hover {
	background: #2e9e91;
}

.notice_list {
	width: 100%;
	padding: 10px 0px;
}

.notice_info {
	padding: 10px;
	margin-bottom: 10px;
	overflow: hidden;
	position: relative;
}

.notice_number {
	display: block;
	width: 5%;
	font-size: 20px;
	text-align: center;
	position: absolute;
	top: 28%;
}

.notice_cha {
	float: right;
	width: 95%;
	padding: 0 5px;
	border-left: 1px solid #ccc;
}

.notice_cha p {
	text-indent: 2em;
}

.notice_date {
	float: right;
	line-height: 35px;
}

.bgwhite {
	background-color: white;
}

.bggrey {
	background-color: #f7f8fa;
}
.fabu{
	position:absolute;
	left:65%;
	top:9px;
	width: 100px;
	height: 35px;
	line-height: 35px;
	background: #F9D749;
	border: none;
	color: white;
	border-radius: 5px;
}
.fabu:hover{
	background-color:#EABF09;
}
.notice_zanwu{
    text-align: center;
    display: block;
    font-size: 18px;
    color: #18d1bb;
    margin-top: 50px;
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
			<div style="position:relative;">
			<form enctype="multipart/form-data" id="myform"
				class="form-horizontal">
				<div class="form-group clearfix"
					style="padding-top: 10px; margin-bottom: 5px;">
					<label class="col-xs-1 right_wz">开始日期：</label>
					<div class="col-xs-2">
						<input class="layui-input" placeholder="选择开始日期" name="start_time"
							id="start_time">
					</div>
					<label class="col-xs-1 right_wz">结束日期：</label>
					<div class="col-xs-2">
						<input class="layui-input" placeholder="选择结束日期" name="end_time"
							id="end_time">
					</div>
					<div class="col-xs-3">
						<button class="shaixuan" onclick="chaxun_notice_list(this)">筛选</button>
					</div>
				</div>
			</form>
			<button class="fabu" onclick="to_fabu_notice()">发布</button>
			</div>
			<c:if test="${empty notices.list }">
			<span class="notice_zanwu">暂无公告发布</span>
			</c:if>
			<ul class="notice_list" style="margin-bottom:15px!important;">
			<c:forEach items="${notices.list }" var="note" varStatus="vs">
			<c:if test="${vs.index%2==0 }">
				<li class="notice_info bgwhite">
			</c:if>
			<c:if test="${vs.index%2!=0 }">
				<li class="notice_info bggrey">
			</c:if>
				<span class="notice_number">${vs.index+1+notices.begin}</span>
					<div class="notice_cha">
						<p>${note.notice_content }</p>
						<div style="overflow: hidden;">
							<button class="notice_delete" onclick="delete_notice(${note.notice_id},${notices.pageNumber })">删除</button>
							<span class="notice_date">日期：<fmt:formatDate value="${note.notice_time }" /></span>
						</div>
					</div>
					<div style="clear: both"></div>
					</li>
				</c:forEach>
			</ul>
			<div class="pages">
				<ul class="pagination pull-right">
					<c:if test="${notices.hasPreviousPage==true}">
						<li class="previous"
							onclick="notice_jump_page(${notices.pageNumber-1},'${start_time }','${end_time }')"><a
							href="#fakelink" class="fui-arrow-left"></a></li>
					</c:if>
					<c:forEach items="${notices.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${notices.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="notice_jump_page(${page},'${start_time }','${end_time }')"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${notices.hasNextPage==true}">
					<li class="next"><a href="#fakelink" onclick="notice_jump_page(${notices.pageNumber+1},'${start_time }','${end_time }')"  class="fui-arrow-right"></a></li>
					</c:if>
				</ul>
			</div>
		</div>

	</div>
</body>
<script>
	$(function() {
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			var start = {
				festival : true,
				istoday : true
			};
			document.getElementById('end_time').onclick = function() {
				start.elem = this;
				laydate(start);
			}
			document.getElementById('start_time').onclick = function() {
				start.elem = this;
				laydate(start);
			}
		});
	})
	//发布公告弹窗
	function to_fabu_notice(){
		layer.open({
			  type: 2,
			  title: ['发布新公告'],
			  area: ['700px', '430px'],
			  shadeClose: false, //点击遮罩关闭
			  content: "/notice/to_fabu_notice.jr"
			  });
	}
	//筛选条件
	function chaxun_notice_list(obj){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("查询中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/notice/get_notice_list.jr",
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
	//跳转分页
	function notice_jump_page(page,start_time,end_time){
		jiazaidonghua();
		$.post("/notice/get_notice_list.jr",{
			'pageNumber' : page,
			'start_time':start_time,
			'end_time':end_time
		},function(data){
			$("#conten_list").html(data);
		})
	}
	//删除公告
	function delete_notice(notice_id,page){
		layer.confirm("提示：您好，确定要删除吗？",function(){
			layer.closeAll('dialog');
			$.post("/notice/delete_notice.jr",{
				'notice_id':notice_id
			},function(data){
				if(data==1){
					tanchuang("删除成功！");
					notice_jump_page(page);
				}else{
					tanchuang("删除失败！");
				}
			});
			})
	}
</script>
</html>