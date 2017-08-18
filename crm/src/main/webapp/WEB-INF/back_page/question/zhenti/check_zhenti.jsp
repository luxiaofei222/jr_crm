<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/common/right_content.css" />
<link rel="stylesheet" type="text/css"
	href="/css/question/zhenti/zhenti_check.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>
//刷新当前页
function shuaxin_dangqian(zhenti_id){
	jiazaidonghua();
	$.post("/back_zhenti/check_zhenti_years.jr",{
		'zhenti_id':zhenti_id
	},function(data){
		$("#conten_list").html(data);
	})
}
//返回上一页
function fanhui(question_course_id){
	jiazaidonghua();
	$.post("/back_zhenti/get_zhenti_years.jr",{
		'question_course_id':question_course_id,
		'pageNumber' : 1,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//修改真题题目
function update_zhenti_question(zhenti_question_id){
	layer.open({
		type : 2,
		title : [ '修改真题题目' ],
		area : [ '1000px', '650px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/back_zhenti/to_update_zhenti_question.jr?zhenti_question_id="+zhenti_question_id
	});
}
//删除真题信息
function delete_zhenti_question(zhenti_question_id,obj){
	layer.confirm("提示：您好，确定要删除吗？",function(){
		layer.closeAll('dialog');
		$.post("/back_zhenti/delete_zhenti_question.jr",{
			'zhenti_question_id':zhenti_question_id
		},function(data){
			if(data==1){
				tanchuang("删除成功！");
				$(obj).parent().parent(".collect_nei").remove();
			}else{
				tanchuang("删除失败！");
			}
		});
		})
}
//技能选择题添加选项
function add_jinengxuanze_question(zhenti_question_id,obj){
	layer.open({
		  type: 2,
		  title: ['添加技能选择题题目'],
		  area: ['900px', '750px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_add_jineng_question_option.jr?zhenti_question_id="+zhenti_question_id
		  });
}
function delete_question(chapter_question_id,zhenti_id){
	layer.confirm("提示：您确定要删除该题目吗？",function(){
		layer.closeAll('dialog');
		$.post("/back_chapter/delete_question_option.jr",{
			'chapter_question_id':chapter_question_id
		},function(data){
			if(data==1){
				shuaxin_dangqian(zhenti_id)
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
//添加案例简答题
function add_anli_jianda_question(zhenti_question_id,obj){
	layer.open({
		  type: 2,
		  title: ['添加案例简答题题目'],
		  area: ['900px', '750px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_add_anli_jianda_question_option.jr?zhenti_question_id="+zhenti_question_id
		  });
}
</script>
<title>查看历年真题</title>
</head>
<body>
<c:if test="${zhentiYears.zhentitype=='历年真题' }">
	<div class="zhenti_title">${zhentiYears.zhenti_year }年
	<c:if test="${not empty zhentiYears.zhenti_yue }">
	（${zhentiYears.zhenti_yue }月）
	</c:if>
	${courseMenu.course_name }《${questionCourse.question_course_name }》真题及答案解析</div>
</c:if>	
<c:if test="${zhentiYears.zhentitype=='模拟考试' }">
	<div class="zhenti_title">${zhentiYears.zhenti_year }年
	<c:if test="${not empty zhentiYears.zhenti_yue }">
	（${zhentiYears.zhenti_yue }月）
	</c:if>
	${courseMenu.course_name }《${questionCourse.question_course_name }》${zhentiYears.zhenti_name }</div>
</c:if>	
	<button type="button" onclick="fanhui(${questionCourse.question_course_id})" class="btn fanhui">返回</button>
	<button type="button" onclick="shuaxin_dangqian(${zhentiYears.zhenti_id })" class="btn shuaxin">刷新</button>
	<div style='clear:both;'>
	<c:forEach items="${zhentiTypeIntroduces }" var="intro" varStatus="vs">
	<div class="exam-well well1">
	<c:if test="${vs.index==0 }">
	一、
	</c:if>
		<c:if test="${vs.index==1 }">
	二、
	</c:if>
		<c:if test="${vs.index==2 }">
	三、
	</c:if>
		<c:if test="${vs.index==3 }">
	四、
	</c:if>
	<c:if test="${vs.index==4 }">
	五、
	</c:if>
	${intro.question_type }(${intro.introduce_content })</div>
	<c:forEach items="${intro.zhentiQuestions }" var="question" varStatus="vs">
	<div class="collect_nei">
		<span class="collect_icon"></span>
		<div class="collect_title">
			<span class="num">${vs.index+1 }</span> <span class='line'></span> <em
				class='type'>[${question.question_type }]</em>
			<p>${question.question_name }</p>
		</div>
		<c:if test="${question.question_type =='职业道德' }">
		<ul class="single_choices">
		<c:forEach items="${question.zhentiQuestionOptions }" var="option">
			<li><span class="l">${option.option_number }</span> <input type="radio" name="single"
				value='' class="singleclass">
				<div class='r'>${option.option_content }</div></li>
		</c:forEach>
		</ul>
		</c:if>
		<c:if test="${question.question_type =='单选题' }">
		<ul class="single_choices">
		<c:forEach items="${question.zhentiQuestionOptions }" var="option">
			<li><span class="l">${option.option_number }</span> <input type="radio" name="single"
				value='' class="singleclass">
				<div class='r'>${option.option_content }</div></li>
		</c:forEach>
		</ul>
		</c:if>
		<c:if test="${question.question_type =='多选题' }">
		<ul class="multi_choices">
		<c:forEach items="${question.zhentiQuestionOptions }" var="option">
			<li><span class="l">${option.option_number }</span> <input type="checkbox" name=""
				value='' class="multiclass">
				<div class='r'>${option.option_content }</div></li>
		</c:forEach>
		</ul>
		</c:if>
		<div class="jiucuo">
		<c:if test="${not empty question.answer }">
			<div class="jiucuo_answer">
				<div class="answer_inline" style="display: block;">
					<span class="as-title">参考答案：</span>
					<div class="as-cont hl-green">${question.answer }</div>
				</div>
			</div>
		</c:if>
			<div class='fold-bd' style="display: block;">
				<ul class="list-analyze">
					<li style="margin-top: 5px;margin-bottom: 10px;"><span class='l'>试题难度：</span>
						<div class="r">
							<i class="icon-pen pen-${question.difficulty }"> <span></span>
							</i>
						</div></li>
					<c:if test="${question.question_type !='技能选择题' && question.question_type !='案例简答题'}">
					<li><span class="l"> 参考解析： </span>
						<div class="r">
							${question.analysis }
						</div></li>
					</c:if>
				</ul>
			</div>
			<c:if test="${question.question_type =='技能选择题' }">
			<div class="btn remove" onclick="add_jinengxuanze_question(${question.zhenti_question_id },this)">添加题目</div>
			</c:if>
			<c:if test="${question.question_type =='案例简答题' }">
			<div class="btn remove" onclick="add_anli_jianda_question(${question.zhenti_question_id },this)">添加题目</div>
			</c:if>
			<div class="btn delete" onclick="delete_zhenti_question(${question.zhenti_question_id },this)">删 除</div>
			<div class="btn remove" onclick="update_zhenti_question(${question.zhenti_question_id })">修 改</div>
			<div style="clear: both;"></div>
			<c:if test="${not empty question.chapterQuestions }">
				<c:forEach items="${question.chapterQuestions }" var="question" varStatus="vs">
	<div class="collect_nei" id="item3">
		<div class="collect_title">
			<span class="num">（${vs.index+1 }）</span>
              <span class='line'></span>
			<em class='type'>[${question.question_type }]</em>
			<p>${question.question_name }</p>
		</div>
		<c:if test="${question.question_type=='多选题' }">
		<ul class="multi_choices">
		<c:forEach items="${question.chapterQuerstionOptions }" var="option">
			<li><span class="l">${option.option_number }</span> 
				<div class='r'>${option.option_content }</div></li>
		</c:forEach>
		</ul>
		</c:if>
			<c:if test="${question.question_type=='单选题' }">
		<ul class="single_choices">
		<c:forEach items="${question.chapterQuerstionOptions }" var="option">
			<li><span class="l">${option.option_number }</span> 
				<div class='r'>${option.option_content }</div></li>
		</c:forEach>
		</ul>
		</c:if>
		<div class="jiucuo">
			<div class="jiucuo_answer">
				<div class="answer_inline">
					<span class="as-title">参考答案：</span>
					<div class="as-cont hl-green">${question.answer }</div>
				</div>
			</div>
			<div class='fold-bd'>
				<ul class="list-analyze">
					<li style="margin-top: 5px;margin-bottom: 10px;"><span class='l'>试题难度：</span>
						<div class="r">
							<i class="icon-pen pen-${question.difficulty }"> <span></span>
							</i>
						</div></li>
					<li><span class="l"> 参考解析： </span>
						<div class="r">${question.analysis }</div></li>
				</ul>
			</div>
			<button class="btn delete" onclick="to_update_question(${question.chapter_question_id})">修改</button>
			<button class="btn remove" onclick="delete_question(${question.chapter_question_id},${zhentiYears.zhenti_id })">删 除</button>
			<div style="clear: both;"></div>
		</div>
	</div>
	</c:forEach>
			</c:if>
		</div>
	</div>
	</c:forEach>
	</c:forEach>
	<div id='zhiding' style="cursor: pointer;">↑</div>
</body>
<script>
$(function(){
	$("#zhiding").click(function(){
		$('body,html').animate({ scrollTop: 0 }, 500);
	})
})

</script>
</html>