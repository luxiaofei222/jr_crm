<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" type="text/css"
	href="/css/question/back/question_check.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="/css/question/back/jiazaidonghua.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>查看题目</title>
</head>
<script>
function delete_question(chapter_question_id){
	layer.confirm("提示：您确定要删除该题目吗？",function(){
		layer.closeAll('dialog');
		$.post("/back_chapter/delete_question_option.jr",{
			'chapter_question_id':chapter_question_id
		},function(data){
			if(data==1){
				location.reload();
			}else{
				tanchuang("删除失败！");
			}
		});
		})
}
//修改题目信息
function to_update_question(chapter_question_id){
	layer.open({
		type : 2,
		title : [ '修改题目信息' ],
		area : [ '900px', '700px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/back_chapter/to_update_question.jr?chapter_question_id="+chapter_question_id
	});
}
</script>
<body>
<c:if test="${empty chapterQuestions }">
<span class="zanwu">还没有上传题目，快去上传吧！</span>
</c:if>
<c:forEach items="${chapterQuestions }" var="question" varStatus="vs">
	<div class="collect_nei" id="item3">
		
		<div class="collect_title">
			<span class="num">${vs.index+1 }</span>
              <span class='line'></span>
			<em class='type'>[${question.question_type }]</em>
			<p>${question.question_name }</p>
		</div>
		<ul class="multi_choices">
		<c:forEach items="${question.chapterQuerstionOptions }" var="option">
			<li><span class="l">${option.option_number }</span> 
				<div class='r'>${option.option_content }</div></li>
		</c:forEach>
		</ul>
		<div class="jiucuo">
			<div class="jiucuo_answer">
				<div class="answer_inline">
					<span class="as-title">参考答案：</span>
					<div class="as-cont hl-green">${question.answer }</div>
				</div>
			</div>
			<div class='fold-bd'>
				<ul class="list-analyze">
					<li><span class='l'>试题难度：</span>
						<div class="r">
							<i class="icon-pen pen-${question.difficulty }"> <span></span>
							</i>
						</div></li>
					<li><span class="l"> 参考解析： </span>
						<div class="r">${question.analysis }</div></li>
				</ul>
			</div>
			<button class="delete" onclick="to_update_question(${question.chapter_question_id})">修改</button>
			<button class="delete" onclick="delete_question(${question.chapter_question_id})">删 除</button>
			<div style="clear: both;"></div>
		</div>
	</div>
	</c:forEach>
</body>
</html>