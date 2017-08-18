<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/common/right_content.css" />
<link rel="stylesheet" type="text/css" href="/css/jiaowu/index/jiaowu.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script>
//翻页上一页下一页
function correction_jump_page(page){
	jiazaidonghua();
	$.post("/back_correction/get_correction_main.jr",{
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//信息弹窗
function tanchuang(content){
	layer.alert(content, {
		  icon: 1,
		  skin: 'layer-ext-moon' 
		})
}
//删除纠错信息
function delte_correction(correction_id,page){
	layer.confirm("提示：您好，确定要删除吗？",function(){
	layer.closeAll('dialog');
	jiazaidonghua();
	$.post("/back_correction/delete_correction.jr",{
		'correction_id':correction_id
	},function(data){
		if(data==1){
			tanchuang("删除成功！");
			correction_jump_page(page);
		}else{
			tanchuang("删除失败！");
		}
	});
	})
}

function check_correction(correction_id){
	layer.open({
		type : 2,
		title : [ '查看纠错信息' ],
		area : [ '1000px', '700px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/back_correction/check_correction.jr?correction_id="+correction_id
	});
}
</script>
<div class="back_right" style="min-width: 900px;">
	<div class="back_r_top">
		<div class="left">纠错信息</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<div class="operation">
		</div>

		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>题目分类</th>
						<th>用户</th>
						<th>错误类型</th>
						<th>信息状态</th>
						<th>提交时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty correctionQuestions }">
						<tbody>
							<c:forEach items="${correctionQuestions.list}" var="correction" varStatus="vs">
								<c:if test="${vs.count%2 == '0' }">
									<tr class="active">
								</c:if>
								<c:if test="${vs.count%2 != '0' }">
									<tr>
								</c:if>
								<td><label class="label label-success btn-primary"
									for="minimal-checkbox-1">${vs.index+1 }</label></td>
								<td>${correction.question_type }</td>
								<td>${correction.user.user_nickname }</td>
								<td>${correction.correction_type }</td>
								<c:if test="${correction.correction_state==0 }">
								<td style="color: red;">未查看</td>
								</c:if>
								<c:if test="${correction.correction_state==1 }">
								<td style="color: green;">已查看</td>
								</c:if>
								<td><fmt:formatDate type="both" value="${correction.correction_time }" /></td>
								<td>
									<button type="button" onclick="check_correction(${correction.correction_id })" class="btn btn-primary btn-xs">查看题目</button>
									<button type="button" onclick="delte_correction(${correction.correction_id },${correctionQuestions.pageNumber})" class="btn btn-info btn-xs">删除记录</button>
								</td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
			</table>
			<c:if test="${ empty correctionQuestions.list }">
				<p class="zanwu">暂无题库纠错信息</p>
			</c:if>
		</div>
		<div class="pages">
				<ul class="pagination right">
					<c:if test="${correctionQuestions.hasPreviousPage==true}">
						<li class="previous"
							onclick="correction_jump_page(${correctionQuestions.pageNumber-1})"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${correctionQuestions.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${correctionQuestions.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="correction_jump_page(${page})"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${correctionQuestions.hasNextPage==true}">
						<li class="next" onclick="correction_jump_page(${correctionQuestions.pageNumber+1})"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
				</ul>
			</div>
	</div>
</div>